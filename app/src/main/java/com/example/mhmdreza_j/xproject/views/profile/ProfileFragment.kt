package com.example.mhmdreza_j.xproject.views.profile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.logic.job.profile.GetProfileJob
import com.example.mhmdreza_j.xproject.logic.job.profile.OnProfileJobSuccessEvent
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.utils.Utils.getInt
import com.example.mhmdreza_j.xproject.utils.Utils.getStringOfInt
import com.example.mhmdreza_j.xproject.views.base_class.EventListenerFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * A simple [Fragment] subclass.
 */

class ProfileFragment : EventListenerFragment() {

    private var coinTextView: TextView? = null
    private var gemTextView: TextView? = null
    private var profileImageView: ImageView? = null
    private var gamesWonTextView: TextView? = null
    private var rankTextView: TextView? = null
    private var averageScoreTextView: TextView? = null
    private var wonInARowScoreTextView: TextView? = null
    private var gamesPlayedTextView: TextView? = null
    private var titleTextView: TextView? = null
    private var progressTextView: TextView? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        GetProfileJob.schedule()
        initViews(view)
        setBackground(view)
        setProfileData(getCachedProfile())
        return view
    }

    private fun getCachedProfile(): ProfileResponse {
        val coin = SharedPrefUtils.getInt(SP_COIN, 0)
        val gem = SharedPrefUtils.getInt(SP_GEM, 0)
        val avatar = SharedPrefUtils.getInt(SP_AVATAR, 0)
        val wonNum = SharedPrefUtils.getInt(SP_WON_NUM, 0)
        val level = SharedPrefUtils.getInt(SP_LEVEL, 0)
        val avgScore = SharedPrefUtils.getInt(SP_AVG_SCORE, 0)
        val winStrike = SharedPrefUtils.getInt(SP_WIN_STRIKE, 0)
        val gameNum = SharedPrefUtils.getInt(SP_GAME_NUM, 0)
        val experience = SharedPrefUtils.getInt(SP_EXPERIENCE, 0)
        val name = SharedPrefUtils.getString(SP_NAME, "")
        return ProfileResponse(name, avatar, level, experience, null, coin, gem, winStrike, avgScore, gameNum, wonNum, null)
    }

    private fun setBackground(view: View) {
        val composer = BlurryUtil.getProfileComposer()

        val rootLayout = view.findViewById<LinearLayout>(R.id.rootLayout)
        if (composer != null) {
            composer.into(rootLayout)
        } else {
            rootLayout.setBackgroundResource(R.mipmap.background)
        }
    }

    private fun initViews(view: View) {
        val closeButton = view.findViewById<TextView>(R.id.closeButton)
        closeButton.setOnClickListener { onBackPressed() }
        coinTextView = view.findViewById(R.id.coinTextView)
        gemTextView = view.findViewById(R.id.gemTextView)
        profileImageView = view.findViewById(R.id.profileImageView)
        gamesWonTextView = view.findViewById(R.id.gamesWonTextView)
        rankTextView = view.findViewById(R.id.rankTextView)
        averageScoreTextView = view.findViewById(R.id.averageScoreTextView)
        wonInARowScoreTextView = view.findViewById(R.id.wonInARowScoreTextView)
        gamesPlayedTextView = view.findViewById(R.id.gamesPlayedTextView)
        titleTextView = view.findViewById(R.id.titleTextView)
        progressTextView = view.findViewById(R.id.progressTextView)
    }

    override fun onBackPressed() {
        if (activity == null) return
        (activity as MainActivity).startFragment(MainFragment())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: OnProfileJobSuccessEvent) {
        val profile = event.profile ?: return
        cacheProfile(profile)
        setProfileData(profile)
    }

    private fun setProfileData(profile: ProfileResponse) {
        coinTextView!!.text = getStringOfInt(profile.coins)
        gemTextView!!.text = getStringOfInt(profile.gem)
        UIUtils.setProfileImage(profileImageView, getInt(profile.avatar))
        gamesWonTextView!!.text = getFormattedString(R.string.gamesWon, profile.wonNumber)
        rankTextView!!.text = getFormattedString(R.string.rank, profile.level)
        averageScoreTextView!!.text = getFormattedString(R.string.avgScore, profile.averageScore)
        wonInARowScoreTextView!!.text = getFormattedString(R.string.wonInARow, profile.winStrike)
        gamesPlayedTextView!!.text = getFormattedString(R.string.gamePlayed, profile.gameNumber)
        progressTextView!!.text = getStringOfInt(profile.experience)
        titleTextView!!.text = String.format("%s %s", resources.getString(R.string.title), profile.name)
    }

    private fun cacheProfile(profile: ProfileResponse) {
        SharedPrefUtils.putInt(SP_COIN, profile.coins ?: 0)
        SharedPrefUtils.putInt(SP_GEM, profile.gem ?: 0)
        SharedPrefUtils.putInt(SP_AVATAR, profile.avatar ?: 0)
        SharedPrefUtils.putInt(SP_WON_NUM, profile.wonNumber ?: 0)
        SharedPrefUtils.putInt(SP_LEVEL, profile.level ?: 0)
        SharedPrefUtils.putInt(SP_AVG_SCORE, profile.averageScore ?: 0)
        SharedPrefUtils.putInt(SP_WIN_STRIKE, profile.winStrike ?: 0)
        SharedPrefUtils.putInt(SP_GAME_NUM, profile.gameNumber ?: 0)
        SharedPrefUtils.putInt(SP_EXPERIENCE, profile.experience ?: 0)
        SharedPrefUtils.putString(SP_NAME, profile.name)
    }

    private fun getFormattedString(resID: Int, number: Int?): String {
        val title = resources.getString(resID)
        return String.format("%s %s", title, getStringOfInt(number))
    }
}// Required empty public constructor
