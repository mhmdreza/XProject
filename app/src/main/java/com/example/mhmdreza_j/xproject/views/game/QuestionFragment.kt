package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.utils.BlurryUtil
import com.example.mhmdreza_j.xproject.utils.dp
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import kotlinx.android.synthetic.main.fragment_question.*


/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val top = arguments?.getInt(TOP)
        val left = arguments?.getInt(LEFT)
        val myAnswer = arguments?.getString(MY_ANSWER)
        val correctAnswer = arguments?.getString(CORRECT_ANSWER)
        if (left != null && top != null) {
            val layoutParams = FrameLayout.LayoutParams(dp(40f, rootLayout.context), dp(40f, rootLayout.context))
            layoutParams.setMargins(left, top, 0, 0)
            val v = View(context)
            v.setBackgroundResource(if (correctAnswer == myAnswer) R.drawable.question_green else R.drawable.question_red)
            rootLayout.addView(v, layoutParams)
        }

        val questionComposer = BlurryUtil.getQuestionComposer()
        questionComposer?.into(rootLayout)


        homeButton.setOnClickListener {
            mainActivity?.startFragment(MainFragment())
        }
    }

    override fun onBackPressed() {
        mainActivity?.startFragment(FinishGameFragment())
    }
}// Required empty public constructor