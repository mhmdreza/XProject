package com.example.mhmdreza_j.xproject.views.profile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.logic.job.profile.GetProfileJob
import com.example.mhmdreza_j.xproject.logic.job.profile.OnProfileJobSuccessEvent
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.views.base_class.EventListenerFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ShowModel
import kotlinx.android.synthetic.main.custom_seekbar.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * A simple [Fragment] subclass.
 */

class ProfileFragment : EventListenerFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GetProfileJob.schedule()
        initViews()
        setBackground(view)
        setProfileData(getCachedProfile())
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
        val username = SharedPrefUtils.getString(SP_USERNAME, "")
        return ProfileResponse(ShowModel(name, username, avatar), level, experience, null, coin, gem, winStrike, avgScore, gameNum, wonNum, null)
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

    private fun initViews() {
        closeButton.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        if (activity == null) return
        mainActivity!!.startFragment(MainFragment())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: OnProfileJobSuccessEvent) {
        val profile = event.profile
        cacheProfile(profile)
        setProfileData(profile)
    }

    private fun setProfileData(profile: ProfileResponse) {
        coinTextView.text = getStringOfInt(profile.coins)
        gemTextView.text = getStringOfInt(profile.gem)
        setProfileImage(profileImageView, getInt(profile.showData?.avatar))
        gamesWonTextView.text = getFormattedString(R.string.gamesWon, profile.wonNumber)
        rankTextView.text = getFormattedString(R.string.rank, profile.level)
        averageScoreTextView.text = getFormattedString(R.string.avgScore, profile.averageScore)
        wonInARowScoreTextView.text = getFormattedString(R.string.wonInARow, profile.winStrike)
        gamesPlayedTextView.text = getFormattedString(R.string.gamePlayed, profile.gameNumber)
        progressTextView.text = getStringOfInt(profile.experience)
        titleTextView.text = String.format("%s %s", resources.getString(R.string.title), profile.showData?.name)
        usernameTextView.text = profile.showData?.username
    }

    private fun cacheProfile(profile: ProfileResponse) {
        SharedPrefUtils.putInt(SP_COIN, profile.coins ?: 0)
        SharedPrefUtils.putInt(SP_GEM, profile.gem ?: 0)
        SharedPrefUtils.putInt(SP_AVATAR, profile.showData?.avatar ?: 0)
        SharedPrefUtils.putInt(SP_WON_NUM, profile.wonNumber ?: 0)
        SharedPrefUtils.putInt(SP_LEVEL, profile.level ?: 0)
        SharedPrefUtils.putInt(SP_AVG_SCORE, profile.averageScore ?: 0)
        SharedPrefUtils.putInt(SP_WIN_STRIKE, profile.winStrike ?: 0)
        SharedPrefUtils.putInt(SP_GAME_NUM, profile.gameNumber ?: 0)
        SharedPrefUtils.putInt(SP_EXPERIENCE, profile.experience ?: 0)
        SharedPrefUtils.putString(SP_NAME, profile.showData?.name ?: "")
        SharedPrefUtils.putString(SP_USERNAME, profile.showData?.username ?: "")
    }

    private fun getFormattedString(resID: Int, number: Int?): String {
        val title = resources.getString(resID)
        return String.format("%s %s", title, getStringOfInt(number))
    }
}// Required empty public constructor
