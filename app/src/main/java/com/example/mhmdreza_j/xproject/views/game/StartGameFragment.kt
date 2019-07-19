package com.example.mhmdreza_j.xproject.views.game


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.utils.*
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.game.socket_model.StartGameModel
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.google.gson.Gson
import io.socket.client.Socket
import ir.tapsell.sdk.bannerads.TapsellBannerType
import ir.tapsell.sdk.bannerads.TapsellBannerView
import org.json.JSONObject
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class StartGameFragment : BaseFragment() {
    private var socket: Socket? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start_game, container, false)
        initViews(view)
        val bannerView = view.findViewById<TapsellBannerView>(R.id.banner)
        bannerView.loadAd(context, START_GAME_KEY, TapsellBannerType.BANNER_300x250)
        initSocket()
        return view
    }

    private fun initSocket() {
        socket = (getMainActivity()?.startSocket() ?: return)
        val jsonObject = JSONObject()
        jsonObject.put("username", "test${Random().nextInt(1000)}")
        jsonObject.put("category", 2)
        socket!!.emit(MATCH_REQUEST,
                jsonObject.toString())
        toastMessage("MATCH_REQUEST")
        socket!!.on(MATCH_REQUEST_ANSWER) { args ->
            toastMessage("MATCH_REQUEST_ANSWER + ${args[0]}")
            val json = args[0] as JSONObject
            if (json.has(MATCH_REQUEST_STATE) && json.getString(MATCH_REQUEST_STATE) == STATE_PROCESSING) {
                activity?.runOnUiThread {
                    getMainActivity()!!.showLoading()
                }
            }
        }

        socket!!.on(MATCH_OPPONENT_READY) { args ->
            toastMessage("MATCH_OPPONENT_READY")
            activity?.runOnUiThread {
                getMainActivity()!!.hideLoading()
            }
            val json = args[0] as String
            val startGame = Gson().fromJson(json, StartGameModel::class.java)
            goToNextPage(startGame)
        }

    }

    private fun toastMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {
        view.findViewById<View>(R.id.returnView).setOnClickListener { onBackPressed() }
        view.findViewById<View>(R.id.prizeTextView).setOnClickListener {
            goToNextPage()
        }
    }

    private fun goToNextPage(startGameModel: StartGameModel? = null) {
        toastMessage("goToNextPage")
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
        socket?.disconnect()
        (activity as MainActivity).startFragment(MainFragment())
    }

    override fun onDestroy() {
        socket?.off(MATCH_REQUEST_ANSWER)
        socket?.off(MATCH_OPPONENT_READY)
        super.onDestroy()
    }
}
