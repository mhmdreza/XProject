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
import com.example.mhmdreza_j.xproject.utils.LAST_WHEEL_ROTATE
import com.example.mhmdreza_j.xproject.utils.SharedPrefUtils
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.example.mhmdreza_j.xproject.views.main_page.RANKING_POSITION
import java.lang.System.currentTimeMillis
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class WheelOfFortuneFragment : BaseFragment() {

    private var countDownTimer: CountDownTimer? = null
    private lateinit var mainFragment: MainFragment


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wheel_of_fortune, container, false)
        mainFragment = parentFragment as MainFragment
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val homeButton = view.findViewById<ImageView>(R.id.homeButton)
        homeButton.setOnClickListener { onBackPressed() }

        val data = ArrayList<LuckyItem>()
        for (i in 0..15) {
            val luckyItem = LuckyItem()
            luckyItem.topText = "Item $i"
            luckyItem.icon = if (i % 2 == 0) R.drawable.ic_coin else R.drawable.ic_gem
            val color = if (i % 2 == 0) R.color.beige else R.color.titleTextColor
            luckyItem.color = getColor(view.context, color)
            data.add(luckyItem)
        }

        val luckyWheelView = view.findViewById<LuckyWheelView>(R.id.luckyWheel)
        luckyWheelView.setData(data)
        luckyWheelView.setRound(13)
        luckyWheelView.setLuckyRoundItemSelectedListener {
            mainFragment.enableBottomNavigation(true)
        }
        luckyWheelView.isTouchEnabled = false

        val spin = view.findViewById<TextView>(R.id.spin)
        spin.setOnClickListener {
            mainFragment.enableBottomNavigation(false)
            saveCurrentTime()
            luckyWheelView.startLuckyWheelWithRandomTarget()
            updateTextView(spin)
            (activity as MainActivity).startWheelMusic()
        }
        updateTextView(spin)
    }

    private fun updateTextView(spin: TextView) {
        if (activity == null) return
        val time = SharedPrefUtils.getLong(LAST_WHEEL_ROTATE, 0)
        val dif = (currentTimeMillis() - time) / 1000
        if (dif > 24 * 60 * 60) {
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
}// Required empty public constructor
