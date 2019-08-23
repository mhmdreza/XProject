package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry
import com.example.mhmdreza_j.xproject.utils.BlurryUtil
import com.example.mhmdreza_j.xproject.utils.FINISH_GAME_KEY
import com.example.mhmdreza_j.xproject.utils.dp
import com.example.mhmdreza_j.xproject.utils.setProfileImage
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.socket_model.GameState
import com.example.mhmdreza_j.xproject.views.game.socket_model.QuestionModel
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import ir.tapsell.sdk.bannerads.TapsellBannerType
import kotlinx.android.synthetic.main.fragment_finish_game.*
import kotlin.math.min

const val MY_ANSWER = "MY_ANSWER"
const val CORRECT_ANSWER = "CORRECT_ANSWER"
const val QUESTION = "QUESTION"
const val TOP = "TOP"
const val LEFT = "LEFT"

class FinishGameFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finish_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bannerView.loadAd(activity, FINISH_GAME_KEY, TapsellBannerType.BANNER_300x250)
        initViews()
    }

    private fun initViews() {
        addViews()
        homeButton.setOnClickListener { onBackPressed() }

        setProfileImage(profileImageView, arguments?.getInt(MY_AVATAR))
        setProfileImage(opponentImageView, arguments?.getInt(OPPONENT_AVATAR))

        when (arguments?.getSerializable(GAME_STATE)) {
            GameState.WIN -> myWinImage.visibility = VISIBLE
            GameState.LOSE -> opponentWinImage.visibility = VISIBLE
            else -> toastMessage("بازی مساوی شد!")
        }
    }

    private fun addViews() {
        val answers = arguments?.getSerializable(ANSWERS) as ArrayList<*>
        val myAnswers = arguments?.getSerializable(MY_ANSWERS) as ArrayList<String>
        val questions = arguments?.getSerializable(QUESTIONS) as ArrayList<*>
        normalizeMyAnswer(myAnswers, questions)
        val index = min(questions.size, myAnswers.size)
        for (i in 0..4) {
            val v = View(context)
            val inBoundIndex = i < index
            val isCorrectAnswer = if (inBoundIndex) answers[i] == myAnswers[i] else false
            v.setBackgroundResource(when {
                i >= questions.size -> R.drawable.question_transparent // not shown question
                isCorrectAnswer -> R.drawable.question_green // wrong answered question
                else -> R.drawable.question_red// right answered question
            })
            v.setOnClickListener(View.OnClickListener { v1 ->
                if (activity == null || i >= questions.size) {
                    return@OnClickListener
                }
                val composer = Blurry.with(v1.context)
                        .color(R.color.dark)
                        .capture(view)
                BlurryUtil.saveQuestionComposer(composer)
                val fragment = QuestionFragment()
                val bundle = Bundle()
                bundle.putInt(LEFT, v1.left + questionLayout.left)
                bundle.putInt(TOP, v1.top + questionLayout.top)
                bundle.putSerializable(QUESTION, questions[i] as QuestionModel)
                bundle.putString(CORRECT_ANSWER, answers[i] as String)
                bundle.putString(MY_ANSWER, myAnswers[i])
                fragment.arguments = bundle
                mainActivity?.startFragment(fragment)
            })
            questionLayout.addView(v, dp(40f, questionLayout.context), dp(40f, questionLayout.context))
        }
    }

    private fun normalizeMyAnswer(myAnswers: ArrayList<String>, questions: ArrayList<*>) {
        while (myAnswers.size < questions.size) {
            myAnswers.add("")
        }
    }

    override fun onBackPressed() {
        if (activity == null) return
        mainActivity?.startBackgroundMusic()
        mainActivity!!.startFragment(MainFragment())
    }
}
