package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry
import com.example.mhmdreza_j.xproject.utils.BlurryUtil
import com.example.mhmdreza_j.xproject.utils.FINISH_GAME_KEY
import com.example.mhmdreza_j.xproject.utils.dp
import com.example.mhmdreza_j.xproject.utils.setProfileImage
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.socket_model.GameState
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import ir.tapsell.sdk.bannerads.TapsellBannerType
import kotlinx.android.synthetic.main.fragment_finish_game.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FinishGameFragment : BaseFragment() {


    private val views = ArrayList<View>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bannerView.loadAd(activity, FINISH_GAME_KEY, TapsellBannerType.BANNER_300x250)
        if (views.size == 0) {
            for (i in 0..9) {
                val v = View(context)
                v.setBackgroundResource(if (i % 2 == 0) R.drawable.question_yellow else R.drawable.question_green)
                views.add(v)
                v.setOnClickListener(View.OnClickListener { v1 ->
                    if (activity == null) {
                        return@OnClickListener
                    }
                    val composer = Blurry.with(v1.context)
                            .color(R.color.dark)
                            .capture(view)
                    BlurryUtil.saveQuestionComposer(composer)
                    val fragment = QuestionFragment()
                    fragment.setLeft(v1.left + questionLayout.left)
                    fragment.setTop(v1.top + questionLayout.top)
                    fragment.setMode(if (i % 2 == 0) QuestionFragment.Mode.YELLOW else QuestionFragment.Mode.GREEN)

                    (activity as MainActivity).startFragment(fragment)
                })
                questionLayout.addView(v, dp(30f, questionLayout.context), dp(30f, questionLayout.context))
            }
        }
        homeButton.setOnClickListener { onBackPressed() }
        setProfileImage(profileImageView, arguments?.getInt(MY_AVATAR))
        setProfileImage(opponentImageView, arguments?.getInt(OPPONENT_AVATAR))
        when (arguments?.getSerializable(GAME_STATE)) {
            GameState.WIN -> myWinImage.visibility = VISIBLE
            GameState.LOSE -> opponentWinImage.visibility = VISIBLE
            else -> toastMessage("EQUAL")
        }
    }


    private fun toastMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (activity == null) return
        (activity as MainActivity).startFragment(MainFragment())
    }
}
