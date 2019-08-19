package com.example.mhmdreza_j.xproject.views.game


import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Base64
import android.util.Log
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


/**
 * A simple [Fragment] subclass.
 */
const val QUESTION_TIME: Long = 10000
const val GAME_STATE = "GAME_STATE"
const val OPPONENT_AVATAR = "OPPONENT_AVATAR"
const val MY_AVATAR = "MY_AVATAR"

class GameFragment : BaseFragment() {
    private lateinit var questionImageView: ImageView
    private lateinit var questionTextView: TextView
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
        val view = inflater.inflate(com.example.mhmdreza_j.xproject.R.layout.fragment_game, container, false)

        initViews(view)
        if (arguments?.containsKey(GAME_MODEL) == true) {
            val startGame = arguments!!.getSerializable(GAME_MODEL) as StartGameModel
            players = startGame.players
            updateUi(startGame)
        }
        username = getUsername(view.context)
        initSocket()
        return view
    }

    private fun updateUi(startGameModel: StartGameModel) {
        showQuestion(startGameModel.question)
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
            val inGameQuestionModel = Gson().fromJson(json.toString(), InGameQuestionModel::class.java)
            showQuestion(inGameQuestionModel.question)
        }
        socket!!.on(MATCH_OVER) { args ->
            val json = args[0] as JSONObject
            val result = Gson().fromJson(json.toString(), ResultModel::class.java)
            endGame(result)
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
        (nextFragment as FinishGameFragment).arguments = bundle
        onNextPressed()
    }

    private fun showQuestion(question: QuestionModel?) {
        isAnswerTime = true
        cancelTimer()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                isAnswerTime = false
                socket?.emit(ON_TIMEOUT, getAnswerJsonObject(validator = 70))
                toastMessage("ON_TIMEOUT")
            }
        }, QUESTION_TIME)
        activity?.runOnUiThread {
            questionTextView.text = question?.description
        }
    }

    private fun cancelTimer() {
        timer.cancel()
        timer.purge()
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
        views[opponentIndex].background = ContextCompat.getDrawable(views[opponentIndex].context, com.example.mhmdreza_j.xproject.R.drawable.question_yellow)
        opponentIndex--
    }

    private fun updateMyAnswersUi(validation: Int?) {
        views[myIndex].background = ContextCompat.getDrawable(views[myIndex].context,
                if (validation == validator) com.example.mhmdreza_j.xproject.R.drawable.question_green else com.example.mhmdreza_j.xproject.R.drawable.question_red)
        myIndex++
    }

    private fun initViews(view: View) {
        questionImageView = view.findViewById(com.example.mhmdreza_j.xproject.R.id.question)
        questionTextView = view.findViewById(com.example.mhmdreza_j.xproject.R.id.questionTextView)

        val progressView = view.findViewById<TextView>(com.example.mhmdreza_j.xproject.R.id.progressView)
        val seekBar = view.findViewById<SeekBar>(com.example.mhmdreza_j.xproject.R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                progressView.text = seekBar.progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        val rightArrow = view.findViewById<ImageView>(com.example.mhmdreza_j.xproject.R.id.rightArrow)
        rightArrow.setOnClickListener(View.OnClickListener {
            val progress = seekBar.progress
            if (progress == seekBar.max) return@OnClickListener
            seekBar.progress = progress + 1
        })

        val leftArrow = view.findViewById<ImageView>(com.example.mhmdreza_j.xproject.R.id.leftArrow)
        leftArrow.setOnClickListener {
            val progress = seekBar.progress
            seekBar.progress = if (progress > 0) progress - 1 else progress
        }

        val homeButton = view.findViewById<ImageView>(com.example.mhmdreza_j.xproject.R.id.homeButton)
        homeButton.setOnClickListener { onBackPressed() }

        val submit = view.findViewById<TextView>(com.example.mhmdreza_j.xproject.R.id.submit)
        submit.setOnClickListener {
            onSubmitClicked(seekBar.progress)
        }

        val questionLayout = view.findViewById<LinearLayout>(com.example.mhmdreza_j.xproject.R.id.questionLayout)
        if (views.size == 0) {
            for (i in 0..9) {
                val v = View(context)
                v.setBackgroundResource(com.example.mhmdreza_j.xproject.R.drawable.question_transparent)
                views.add(v)
                questionLayout.addView(v, dp(20f, view.context), dp(20f, view.context))
            }
        }
    }

    private fun onSubmitClicked(answer: Int) {
        if (isAnswerTime) {
            isAnswerTime = false
            cancelTimer()
            validator = Random.nextInt(1, 100)
            val json = getAnswerJsonObject(answer, validator)
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

    override fun onBackPressed() {
        if (activity == null) return
        socket!!.disconnect()
        (activity as MainActivity).startFragment(MainFragment())
    }

    private fun toastMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}