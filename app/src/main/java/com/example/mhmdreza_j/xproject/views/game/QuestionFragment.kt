package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView

import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.lib.blurry.Blurry
import com.example.mhmdreza_j.xproject.utils.BlurryUtil
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.facebook.FacebookActivity

import com.example.mhmdreza_j.xproject.utils.UIUtils.dp

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : BaseFragment() {

    private var left = 0
    private var top = 0
    private var mode: Mode? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        val rootLayout = view.findViewById<FrameLayout>(R.id.rootLayout)

        val layout = FrameLayout.LayoutParams(dp(30f, context), dp(30f, context))
        layout.setMargins(left, top, 0, 0)
        val v = View(context)
        v.setBackgroundResource(if (mode == Mode.YELLOW) R.drawable.question_yellow else R.drawable.question_green)
        rootLayout.addView(v, layout)

        val questionComposer = BlurryUtil.getQuestionComposer()
        questionComposer?.into(rootLayout)


        val homeButton = view.findViewById<ImageView>(R.id.homeButton)
        homeButton.setOnClickListener(View.OnClickListener {
            if (activity == null) return@OnClickListener
            (activity as MainActivity).startFragment(MainFragment())
        })

        return view
    }

    fun setLeft(left: Int) {
        this.left = left
    }

    fun setTop(top: Int) {
        this.top = top
    }

    override fun onBackPressed() {
        if (activity == null) return
        (activity as MainActivity).startFragment(FinishGameFragment())
    }

    fun setMode(mode: Mode) {
        this.mode = mode
    }

    enum class Mode {
        YELLOW, GREEN
    }
}// Required empty public constructor
