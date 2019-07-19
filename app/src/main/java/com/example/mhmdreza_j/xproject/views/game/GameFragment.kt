package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.utils.UIUtils.dp
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.socket_model.*
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.google.gson.Gson
import io.socket.client.Socket
import java.util.*
import kotlin.random.Random
import kotlin.random.nextUInt

/**
 * A simple [Fragment] subclass.
 */
const val QUESTION_TIME: Long = 10000

class GameFragment : BaseFragment() {
    private lateinit var questionImageView: ImageView
    private val views = ArrayList<View>()
    private var timer = Timer()
    private var socket: Socket? = null
    private var solvedQuestions: Int = 0
    private var validator: Int = -1
    private var isAnswerTime = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        initViews(view)
        if (arguments?.containsKey(GAME_MODEL) == true) {
            val startGame = arguments!!.getSerializable(GAME_MODEL) as StartGameModel
            updateUi(startGame)
        }
        initSocket()
        return view
    }

    private fun updateUi(startGameModel: StartGameModel) {
        showQuestion(startGameModel.question)
    }

    private fun initSocket() {
        socket = getMainActivity()?.socket ?: return
        socket!!.on(ANSWER_VALIDATION) { args ->
            val json = args[0] as String
            val answerValidationModel = Gson().fromJson(json, AnswerValidationModel::class.java)
            updateAnswersUi(answerValidationModel)
        }
        socket!!.on(NEXT_QUESTION) { args ->
            val json = args[0] as String
            val inGameQuestionModel = Gson().fromJson(json, InGameQuestionModel::class.java)
            showQuestion(inGameQuestionModel.question)
        }
        socket!!.on(MATCH_OVER) { args ->
            val json = args[0] as String
            val result = Gson().fromJson(json, ResultModel::class.java)
            endGame(result)
        }
    }

    private fun endGame(result: ResultModel) {
        if ("EQUAL" == result.result) {
        }
    }

    private fun showQuestion(question: QuestionModel?) {
        isAnswerTime = true
        timer.cancel()
        timer.purge()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                isAnswerTime = false
                solvedQuestions++
                socket?.emit(ON_TIMEOUT)
            }
        }, QUESTION_TIME)
        Glide.with(questionImageView)
                .load(question?.image)
                .into(questionImageView)
    }

    private fun updateAnswersUi(answerValidation: AnswerValidationModel) {

    }

    private fun initViews(view: View) {
        questionImageView = view.findViewById(R.id.question)

        val progressView = view.findViewById<TextView>(R.id.progressView)
        val seekBar = view.findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                progressView.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val rightArrow = view.findViewById<ImageView>(R.id.rightArrow)
        rightArrow.setOnClickListener(View.OnClickListener {
            val progress = seekBar.progress
            if (progress == seekBar.max) return@OnClickListener
            seekBar.progress = progress + 1
        })

        val leftArrow = view.findViewById<ImageView>(R.id.leftArrow)
        leftArrow.setOnClickListener {
            val progress = seekBar.progress
            seekBar.progress = if (progress > 0) progress - 1 else progress
        }

        val homeButton = view.findViewById<ImageView>(R.id.homeButton)
        homeButton.setOnClickListener { onBackPressed() }

        val submit = view.findViewById<TextView>(R.id.submit)
        submit.setOnClickListener {
            onSubmitClicked(seekBar.progress)
        }

        val questionLayout = view.findViewById<LinearLayout>(R.id.questionLayout)
        if (views.size == 0) {
            for (i in 0..9) {
                val v = View(context)
                v.setBackgroundResource(R.drawable.question_transparent)
                views.add(v)
                questionLayout.addView(v, dp(20f, view.context), dp(20f, view.context))
            }
        }
    }

    private fun onSubmitClicked(answer: Int) {
        if (isAnswerTime) {
            isAnswerTime = false
            solvedQuestions++
            validator = Random.nextInt(1, 100)
            socket?.emit(QUESTION_SOLVED, AnswerModel(answer, validator, "playerrrrrrrrrrrrrrrrrrrr"))
        }
    }

    override fun onBackPressed() {
        if (activity == null) return
        (activity as MainActivity).startFragment(MainFragment())
    }
}
