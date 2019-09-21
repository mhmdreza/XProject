package com.example.mhmdreza_j.xproject.views.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.logic.job.login.LoginJob
import com.example.mhmdreza_j.xproject.logic.job.login.OnLoginSuccessEvent
import com.example.mhmdreza_j.xproject.utils.IS_USER_LOGGED_IN
import com.example.mhmdreza_j.xproject.utils.SharedPrefUtils
import com.example.mhmdreza_j.xproject.utils.setBackgroundResource
import com.example.mhmdreza_j.xproject.views.base_class.EventListenerFragment
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.fragment_login.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginFragment : EventListenerFragment() {
    private var loginButton: LoginButton? = null
    private var callbackManager: CallbackManager? = null
    private var isOnLoginPressed = false

    private val guestOnClickListener: View.OnClickListener
        get() = View.OnClickListener {
            showLoading()
            LoginJob.schedule()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        if (activity == null) return view
        FacebookSdk.sdkInitialize(context)
        val imageView = view.findViewById<ImageView>(R.id.facebookButton)
        imageView.setOnClickListener {
            if (!isOnLoginPressed) {
                showLoading()
                isOnLoginPressed = true
                loginButton!!.callOnClick()
            }
        }
        callbackManager = CallbackManager.Factory.create()

        initFacebookLogin(view)

        val guestPlay = view.findViewById<TextView>(R.id.guestPlay)
        val guestLogin = view.findViewById<TextView>(R.id.guestLogin)
        guestLogin.setOnClickListener(guestOnClickListener)
        guestPlay.setOnClickListener(guestOnClickListener)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundResource(backgroundIV, R.mipmap.login_background)
    }

    override fun onStop() {
        LoginManager.getInstance().unregisterCallback(callbackManager!!)
        super.onStop()
    }

    private fun initFacebookLogin(view: View) {
        loginButton = view.findViewById(R.id.login_button)
        registerFacebookCallback()
    }

    private fun registerFacebookCallback() {
        LoginManager.getInstance().registerCallback(callbackManager!!,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        isOnLoginPressed = false
                        loginToApp()
                    }

                    override fun onCancel() {
                        isOnLoginPressed = false
                    }

                    override fun onError(exception: FacebookException) {
                        isOnLoginPressed = false
                    }
                })
    }

    private fun loginToApp() {
        hideLoading()
        SharedPrefUtils.putBoolean(IS_USER_LOGGED_IN, true)
        nextFragment = MainFragment()
        onNextPressed()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: OnLoginSuccessEvent) {
        loginToApp()
    }
}

