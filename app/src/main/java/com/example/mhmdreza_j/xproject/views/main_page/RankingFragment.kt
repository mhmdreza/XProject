package com.example.mhmdreza_j.xproject.views.main_page


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry
import com.example.mhmdreza_j.xproject.logic.job.record.GetRecordJob
import com.example.mhmdreza_j.xproject.logic.job.record.OnRecordJobSuccessEvent
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.views.base_class.EventListenerFragment
import com.example.mhmdreza_j.xproject.views.game.CATEGORY
import com.example.mhmdreza_j.xproject.views.game.StartGameFragment
import com.example.mhmdreza_j.xproject.views.profile.ProfileFragment
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


private const val LAYOUT_RESOURCE_ID = R.layout.fragment_ranking

class RankingFragment : EventListenerFragment() {
    private lateinit var profileImageView: ImageView
    private lateinit var levelTextView: TextView
    private lateinit var gemTextView: TextView
    private lateinit var coinTextView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        val view = inflater.inflate(LAYOUT_RESOURCE_ID, container, false)
        GetRecordJob.schedule()
        initViews(view)
        setData(getRecord())
        return view
    }

    private fun getRecord(): RecordResponse {
        val coin = SharedPrefUtils.getInt(SP_COIN, 0)
        val gem = SharedPrefUtils.getInt(SP_GEM, 0)
        val avatar = SharedPrefUtils.getInt(SP_AVATAR, 0)
        val level = SharedPrefUtils.getInt(SP_LEVEL, 0)
        return RecordResponse(coin, gem, avatar, level)
    }

    private fun initViews(view: View) {
        profileImageView = view.findViewById(R.id.profileImageView)
        coinTextView = view.findViewById(R.id.coinTextView)
        gemTextView = view.findViewById(R.id.gemTextView)
        levelTextView = view.findViewById(R.id.levelTextView)

        view.findViewById<View>(R.id.dicesLayout).setOnClickListener(getStartGameOnClickListener(1))
        view.findViewById<View>(R.id.marblesLayout).setOnClickListener(getStartGameOnClickListener(2))
        view.findViewById<View>(R.id.rabbitsLayout).setOnClickListener(getStartGameOnClickListener(3))
        view.findViewById<View>(R.id.birdsLayout).setOnClickListener(getStartGameOnClickListener(4))

        profileImageView.bringToFront()
        profileImageView.setOnClickListener {
            val composer = Blurry.with(view.context)
                    .color(R.color.dark)
                    .capture(parentFragment!!.view)
            BlurryUtil.saveProfileComposer(composer)
            nextFragment = ProfileFragment()
            onNextPressed()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: OnRecordJobSuccessEvent) {
        val record = event.record
        cacheData(record)
        setData(record)
    }

    private fun cacheData(record: RecordResponse) {
        SharedPrefUtils.putInt(SP_COIN, record.coins)
        SharedPrefUtils.putInt(SP_GEM, record.gem)
        SharedPrefUtils.putInt(SP_AVATAR, record.avatar)
        SharedPrefUtils.putInt(SP_LEVEL, record.level)
    }

    private fun setData(record: RecordResponse) {
        gemTextView.text = getStringOfInt(record.gem)
        coinTextView.text = getStringOfInt(record.coins)
        levelTextView.text = getStringOfInt(record.level)
        setProfileImage(profileImageView, record.avatar)
    }

    private fun getStartGameOnClickListener(category: Int): View.OnClickListener {
        return View.OnClickListener {
            if (activity == null) return@OnClickListener
            val startGameFragment = StartGameFragment()
            val bundle = Bundle()
            bundle.putInt(CATEGORY, category)
            startGameFragment.arguments = bundle
            nextFragment = startGameFragment
            onNextPressed()
        }
    }
}
