package com.example.mhmdreza_j.xproject.views.game


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.socket_model.StartGameModel
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.google.gson.Gson
import io.socket.client.Socket
import ir.tapsell.sdk.bannerads.TapsellBannerType
import kotlinx.android.synthetic.main.fragment_start_game.*
import org.json.JSONObject
import java.util.*

const val CATEGORY = "CATEGORY"

class StartGameFragment : BaseFragment() {
    private var allowOnBackPressed = false
    private var socket: Socket? = null
    private var category: Int = 1
    private val timeoutTimer = Timer()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_start_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category = arguments?.getInt(CATEGORY, 1) ?: 1
        initViews()
        initSocket(view.context)
        startMusic()
        startTimeoutTimer()
    }

    private fun startTimeoutTimer() {
        timeoutTimer.schedule(object : TimerTask() {
            override fun run() {
                mainActivity?.cancelMatch()
            }
        }, 60000)
    }

    private fun startMusic() {
        mainActivity?.startGameMusic()
    }

    private fun initSocket(context: Context) {
        socket = (mainActivity?.startSocket() ?: return)
        val jsonObject = JSONObject()
        jsonObject.put("username", getUsername(context))
        jsonObject.put("category", category)
        socket!!.emit(MATCH_REQUEST, jsonObject)
        socket!!.on(MATCH_REQUEST_ANSWER) { args ->
            val json = args[0] as JSONObject
            if (json.has(MATCH_REQUEST_STATE) && json.getString(MATCH_REQUEST_STATE) == STATE_PROCESSING) {
                activity?.runOnUiThread {
                    showLoading()
                }
            }
        }

        socket!!.on(MATCH_OPPONENT_READY) { args ->
            activity?.runOnUiThread { hideLoading() }
            val json = args[0] as JSONObject
            Log.d("MATCH_OPPONENT_READY", json.toString())
            val startGame = Gson().fromJson(json.toString(), StartGameModel::class.java)
            goToNextPage(startGame)
        }

        socket!!.on(CLOSE_MATCH) { args ->
            activity?.runOnUiThread { hideLoading() }
            val json = args[0] as JSONObject
            if (json.has(CLOSE_MATCH_RESULT) && json[CLOSE_MATCH_RESULT] == 0) {
                goToBackFragment()
            } else {
                allowOnBackPressed = true
                toastMessage("در خارج شدن از بازی خطایی رخ داده،\n لطفا دوباره تلاش کنید!")
            }
        }
    }

    private fun initViews() {
        returnView.setOnClickListener { onBackPressed() }
        bannerView.loadAd(context, START_GAME_KEY, TapsellBannerType.BANNER_300x250)
    }

    private fun goToNextPage(startGameModel: StartGameModel? = null) {
        val gameFragment = GameFragment()
        if (startGameModel != null) {
            val bundle = Bundle()
            bundle.putSerializable(GAME_MODEL, startGameModel)
            gameFragment.arguments = bundle
        }
        nextFragment = gameFragment
        onNextPressed()
    }

    override fun onBackPressed() {
        if (activity == null) return
        if (allowOnBackPressed)
            goToBackFragment()
        else
            mainActivity!!.cancelMatch()
    }

    private fun goToBackFragment() {
        socket!!.disconnect()
        mainActivity?.startBackgroundMusic()
        mainActivity?.startFragment(MainFragment())
    }

    override fun onDestroy() {
        socket?.off(MATCH_REQUEST_ANSWER)
        socket?.off(MATCH_OPPONENT_READY)
        super.onDestroy()
    }
}
