package com.example.mhmdreza_j.xproject.views.main_page

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.utils.CANCEL_MATCH
import com.example.mhmdreza_j.xproject.utils.IS_USER_LOGGED_IN
import com.example.mhmdreza_j.xproject.utils.SharedPrefUtils
import com.example.mhmdreza_j.xproject.views.LoadingDialog
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment
import com.example.mhmdreza_j.xproject.views.login.LoginFragment
import com.example.mhmdreza_j.xproject.webservice.base.constants.BASE_URL
import com.example.mhmdreza_j.xproject.webservice.pref.WebservicePrefSetting
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null
    private var fragment: BaseFragment? = null
    private var player: MediaPlayer? = null
    var socket: Socket? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )

        setContentView(R.layout.activity_main)

        if (SharedPrefUtils.getBoolean(IS_USER_LOGGED_IN, false)) {
            startFragment(MainFragment())
        } else {
            startFragment(LoginFragment())
        }
        loadingDialog = LoadingDialog(this)
    }

    fun startSocket(): Socket? {
        if (socket?.connected() != true) {
            try {
                val opt = IO.Options()
                if (WebservicePrefSetting.getInstance(this).token.isNotEmpty()) {
                    opt.query = "token=${WebservicePrefSetting.getInstance(this).token}"
                }
                socket = IO.socket(BASE_URL, opt)
            } catch (e: URISyntaxException) {
                return null
            }
        }
        socket!!.connect()
        return socket
    }

    fun cancelMatch() {
        socket?.emit(CANCEL_MATCH, JSONObject())
        runOnUiThread { showLoading() }
    }

    override fun onResume() {
        super.onResume()
        startBackgroundMusic()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private fun stopMusic() {
        if (player != null)
            player!!.stop()
    }

    fun startBackgroundMusic() {
        stopMusic()
        player = MediaPlayer.create(this, R.raw.background_music)
        player!!.start()
        player!!.isLooping = true
    }

    fun startFragment(fragment: BaseFragment) {
        this.fragment = fragment
        val manager = supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFragment, fragment)
        fragmentTransaction.commit()
    }

    fun startWheelMusic() {
        stopMusic()
        player = MediaPlayer.create(this, R.raw.wheel_of_fortune)
        player!!.start()
        player!!.setOnCompletionListener { startBackgroundMusic() }
        player!!.isLooping = false
    }

    fun startGameMusic() {
        stopMusic()
        player = MediaPlayer.create(this, R.raw.game)
        player!!.start()
        player!!.isLooping = true
    }

    override fun onStop() {
        player?.release()
        super.onStop()
    }

    override fun onBackPressed() {
        fragment!!.onBackPressed()
    }

    fun showLoading() {
        loadingDialog!!.show()
    }

    fun hideLoading() {
        loadingDialog!!.hide()
    }
}
