package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry
import com.example.mhmdreza_j.xproject.utils.BlurryUtil
import com.example.mhmdreza_j.xproject.utils.FINISH_GAME_KEY
import com.example.mhmdreza_j.xproject.utils.UIUtils.dp
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.QuestionFragment.Mode.GREEN
import com.example.mhmdreza_j.xproject.views.game.QuestionFragment.Mode.YELLOW
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import ir.tapsell.sdk.bannerads.TapsellBannerType
import ir.tapsell.sdk.bannerads.TapsellBannerView
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class FinishGameFragment : BaseFragment() {


    private val views = ArrayList<View>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_finish_game, container, false)
        val bannerView = view.findViewById<TapsellBannerView>(R.id.banner)
        bannerView.loadAd(activity, FINISH_GAME_KEY, TapsellBannerType.BANNER_300x250)
        val questionLayout = view.findViewById<LinearLayout>(R.id.questionLayout)
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
                    fragment.setMode(if (i % 2 == 0) YELLOW else GREEN)

                    (activity as MainActivity).startFragment(fragment)
                })
                questionLayout.addView(v, dp(30f, questionLayout.context), dp(30f, questionLayout.context))
            }
        }

        val homeButton = view.findViewById<ImageView>(R.id.homeButton)
        homeButton.setOnClickListener { onBackPressed() }
        return view
    }

    override fun onBackPressed() {
        if (activity == null) return
        (activity as MainActivity).startFragment(MainFragment())
    }
}// Required empty public constructor
