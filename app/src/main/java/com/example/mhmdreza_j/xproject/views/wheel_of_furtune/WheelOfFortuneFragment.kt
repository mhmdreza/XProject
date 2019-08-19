package com.example.mhmdreza_j.xproject.views.wheel_of_furtune


import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.lucky_wheel.LuckyWheelView
import com.example.mhmdreza_j.xproject.lib.lucky_wheel.model.LuckyItem
import com.example.mhmdreza_j.xproject.logic.job.lucky_wheel.LuckyWheelJob
import com.example.mhmdreza_j.xproject.logic.job.lucky_wheel.OnLuckyWheelJobSuccessEvent
import com.example.mhmdreza_j.xproject.utils.LAST_WHEEL_ROTATE
import com.example.mhmdreza_j.xproject.utils.SharedPrefUtils
import com.example.mhmdreza_j.xproject.views.base_class.EventListenerFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.example.mhmdreza_j.xproject.views.main_page.RANKING_POSITION
import kotlinx.android.synthetic.main.fragment_finish_game.*
import kotlinx.android.synthetic.main.fragment_finish_game.homeButton
import kotlinx.android.synthetic.main.fragment_wheel_of_fortune.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.System.currentTimeMillis

/**
 * A simple [Fragment] subclass.
 */
class WheelOfFortuneFragment : EventListenerFragment() {

    private var index: Int = 0
    private var isSpinAllowed = false
    private var isDataLoaded = false
    private var countDownTimer: CountDownTimer? = null
    private lateinit var mainFragment: MainFragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wheel_of_fortune, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragment = parentFragment as MainFragment
        initViews(view)
        LuckyWheelJob.schedule()
    }

    private fun initViews(view: View) {
        homeButton.setOnClickListener { onBackPressed() }
        luckyWheelView.setRound(13)
        luckyWheelView.setLuckyRoundItemSelectedListener {
            mainFragment.enableBottomNavigation(true)
        }
        luckyWheelView.isTouchEnabled = false
        val data = ArrayList<LuckyItem>()
        for (i in 0..16) {
            val color = if (i % 2 == 0) R.color.beige else R.color.titleTextColor
            val luckyItem = LuckyItem("", "", 0
                    , getColor(context!!, color))
            data.add(luckyItem)
        }
        luckyWheelView.setData(data)
        spin.setOnClickListener {
            if (isSpinAllowed && isDataLoaded)
                spinWheel()
        }
        updateTextView()
    }

    private fun spinWheel() {
        mainFragment.enableBottomNavigation(false)
        saveCurrentTime()
        luckyWheelView.startLuckyWheelWithTargetIndex(index)
        updateTextView()
        (activity as MainActivity).startWheelMusic()
    }

    private fun updateTextView() {
        if (activity == null) return
        val time = SharedPrefUtils.getLong(LAST_WHEEL_ROTATE, 0)
        val dif = (currentTimeMillis() - time) / 1000
        if (dif > 24 * 60 * 60) {
            isSpinAllowed = true
            spin.setText(R.string.spinNow)
        } else {
            if (countDownTimer != null) {
                countDownTimer!!.cancel()
            }
            countDownTimer = object : CountDownTimer((24 * 3600 - dif) * 1000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    val seconds = millisUntilFinished / 1000
                    val h = seconds / 3600
                    val m = seconds % 3600 / 60
                    val s = seconds % 60
                    spin.text = getRemainingTime(h, m, s)
                }

                override fun onFinish() {
                    spin.setText(R.string.spinNow)
                }

            }
            countDownTimer!!.start()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: OnLuckyWheelJobSuccessEvent) {
        val data = ArrayList<LuckyItem>()
        val record = event.record
        for (i in 0 until record.items.size) {
            val icon = if (record.items[i].type == "coin") R.drawable.ic_coin else R.drawable.ic_gem
            val color = if (i % 2 == 0) R.color.beige else R.color.titleTextColor
            val luckyItem = LuckyItem((record.items[i].value + i).toString(), "", icon
                    , getColor(context!!, color))
            if (record.items[i] == record.selectedItem) {
                index = i
            }
            data.add(luckyItem)
        }
        index = 0
        luckyWheelView.setData(data)
        isDataLoaded = true
    }

    private fun getRemainingTime(h: Long, m: Long, s: Long): String {

        return "${getTimeString(h)}:${getTimeString(m)}:${getTimeString(s)} UNTIL NEXT SPIN"
    }

    private fun getTimeString(time: Long): String {
        return if (time < 10) "0$time" else "" + time
    }

    private fun saveCurrentTime() {
        SharedPrefUtils.putLong(LAST_WHEEL_ROTATE, currentTimeMillis())
    }


    override fun onBackPressed() {
        if (activity == null) return
        this.parentFragment!!.navigationView.performOnClick(RANKING_POSITION)
    }
}
