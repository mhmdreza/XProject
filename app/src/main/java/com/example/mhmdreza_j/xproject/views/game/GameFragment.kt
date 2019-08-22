package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.socket_model.*
import com.example.mhmdreza_j.xproject.views.game.socket_model.GameState.*
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.google.gson.Gson
import io.socket.client.Socket
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import com.bumptech.glide.Glide
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.webservice.base.constants.BASE_URL
import kotlinx.android.synthetic.main.fragment_game.*


/**
 * A simple [Fragment] subclass.
 */
const val QUESTION_TIME: Long = 10000
const val GAME_STATE = "GAME_STATE"
const val OPPONENT_AVATAR = "OPPONENT_AVATAR"
const val MY_AVATAR = "MY_AVATAR"
const val QUESTIONS = "QUESTIONS"
const val ANSWERS = "ANSWERS"
const val MY_ANSWERS = "MY_ANSWERS"

class GameFragment : BaseFragment() {
    private val myAnswers = ArrayList<String>()
    private val questions = ArrayList<QuestionModel>()
    private val views = ArrayList<View>()
    private var timer = Timer()
    private var socket: Socket? = null
    private var myIndex = 0
    private var validator: Int = -1
    private var isAnswerTime = false
    private var username = ""
    private var opponentIndex = 9
    private var players = ArrayList<PlayerModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        if (arguments?.containsKey(GAME_MODEL) == true) {
            val startGame = arguments!!.getSerializable(GAME_MODEL) as StartGameModel
            players = startGame.players
            updateUi(startGame)
        }
        username = getUsername(view.context)
        initSocket()
    }

    private fun initViews() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                progressView.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        rightArrow.setOnClickListener(View.OnClickListener {
            val progress = seekBar.progress
            if (progress == seekBar.max) return@OnClickListener
            seekBar.progress = progress + 1
        })

        leftArrow.setOnClickListener {
            val progress = seekBar.progress
            seekBar.progress = if (progress > 0) progress - 1 else progress
        }

        homeButton.setOnClickListener { onBackPressed() }

        submit.setOnClickListener {
            onSubmitClicked(seekBar.progress)
        }

        if (views.size == 0) {
            for (i in 0..9) {
                val v = View(context)
                v.setBackgroundResource(R.drawable.question_transparent)
                views.add(v)
                questionLayout.addView(v, dp(20f, context!!), dp(20f, context!!))
            }
        }
    }

    private fun updateUi(startGameModel: StartGameModel) {
        showQuestion(startGameModel.question)
    }

    private fun updateAnswersUi(answerValidation: AnswerValidationModel) {
        activity?.runOnUiThread {
            if (answerValidation.player == username) {
                updateMyAnswersUi(answerValidation.validation)
            } else {
                updateOpponentAnswersUi()
            }
        }

    }

    private fun updateOpponentAnswersUi() {
        views[opponentIndex].background = ContextCompat.getDrawable(views[opponentIndex].context, R.drawable.question_yellow)
        opponentIndex--
    }

    private fun updateMyAnswersUi(validation: Int?) {
        views[myIndex].background = ContextCompat.getDrawable(views[myIndex].context,
                if (validation == validator) R.drawable.question_green else R.drawable.question_red)
        myIndex++
    }

    private fun showQuestion(question: QuestionModel) {
        isAnswerTime = true
        questions.add(question)
        cancelTimer()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                isAnswerTime = false
                myAnswers.add("")
                socket?.emit(ON_TIMEOUT, getAnswerJsonObject(validator = 70))
                toastMessage("ON_TIMEOUT")
            }
        }, QUESTION_TIME)
        activity?.runOnUiThread {
            questionTextView.text = question.description
            Glide.with(questionImageView).load(BASE_URL + question.url).into(questionImageView)
        }
    }

    private fun initSocket() {
        socket = getMainActivity()?.socket ?: return
        socket!!.on(ANSWER_VALIDATION) { args ->
            val json = args[0] as JSONObject
            val answerValidationModel = Gson().fromJson(json.toString(), AnswerValidationModel::class.java)
            updateAnswersUi(answerValidationModel)
        }
        socket!!.on(NEXT_QUESTION) { args ->
            val json = args[0] as JSONObject
            val inGameQuestionModel = customGson.fromJson(json.toString(), InGameQuestionModel::class.java)
            showQuestion(inGameQuestionModel.question)
        }
        socket!!.on(MATCH_OVER) { args ->
            val json = args[0] as JSONObject
            val result = Gson().fromJson(json.toString(), ResultModel::class.java)
            endGame(result)
        }
    }

    private fun cancelTimer() {
        timer.cancel()
        timer.purge()
    }

    private fun onSubmitClicked(answer: Int) {
        if (isAnswerTime) {
            isAnswerTime = false
            cancelTimer()
            validator = Random.nextInt(1, 100)
            val json = getAnswerJsonObject(answer, validator)
            myAnswers.add(answer.toString())
            socket?.emit(QUESTION_SOLVED, json)
        }
    }

    private fun getAnswerJsonObject(answer: Int = -1, validator: Int): JSONObject {
        val json = JSONObject()
        json.put("answer", answer.toString())
        json.put("validator", validator)
        json.put("player", username)
        return json
    }

    private fun toastMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun endGame(result: ResultModel) {
        val gameState = when (result.result) {
            getUsername(context) -> WIN
            "__ EQUAL __" -> EQUAL
            else -> LOSE
        }
        nextFragment = FinishGameFragment()
        val bundle = Bundle()
        bundle.putSerializable(GAME_STATE, gameState)
        for (player in players) {
            when {
                player.username == getUsername(context) -> bundle.putSerializable(MY_AVATAR, player.avatar)
                else -> bundle.putSerializable(OPPONENT_AVATAR, player.avatar)
            }
        }
        bundle.putSerializable(QUESTIONS, questions)
        bundle.putSerializable(ANSWERS, result.answers)
        bundle.putSerializable(MY_ANSWERS, myAnswers)
        (nextFragment as FinishGameFragment).arguments = bundle
        onNextPressed()
    }

    override fun onBackPressed() {
        if (activity == null) return
        socket!!.disconnect()
        (activity as MainActivity).startBackgroundMusic()
        (activity as MainActivity).startFragment(MainFragment())
    }
}