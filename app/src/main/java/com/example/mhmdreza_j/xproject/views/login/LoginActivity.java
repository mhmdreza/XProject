package com.example.mhmdreza_j.xproject.views.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.application.ApplicationLoader;
import com.example.mhmdreza_j.xproject.utils.ActivityHelper;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;

import ir.sharif.vamdeh.webservices.WebserviceHelper;
import ir.sharif.vamdeh.webservices.base.Constants.LoginType;
import ir.sharif.vamdeh.webservices.base.WebserviceException;
import ir.sharif.vamdeh.webservices.pref.WebservicePrefSetting;

public class LoginActivity extends BaseActivity {
    private static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean isOnLoginPressed = false;
    private SharedPreferences sharedpreferences;
    public LoginActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(ApplicationLoader.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        if (sharedpreferences.getBoolean(IS_USER_LOGGED_IN, false)){
            ActivityHelper.startMainActivity(this);
        }
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);
        ImageView imageView = findViewById(R.id.facebookButton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnLoginPressed) {
                    isOnLoginPressed = true;
                    loginButton.callOnClick();
                }
            }
        });
        callbackManager = CallbackManager.Factory.create();

        initFacebookLogin();

        TextView guestPlay = findViewById(R.id.guestPlay);
        TextView guestLogin = findViewById(R.id.guestLogin);
        guestLogin.setOnClickListener(getGuestOnClickListener());
        guestPlay.setOnClickListener(getGuestOnClickListener());
    }

    private void initFacebookLogin() {
        loginButton = findViewById(R.id.login_button);
        registerFacebookCallback();
    }

    private void registerFacebookCallback() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        isOnLoginPressed = false;
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putBoolean(IS_USER_LOGGED_IN, true);
                        editor.apply();
                        String userId = loginResult.getAccessToken().getUserId();
//                        WebservicePrefSetting.Companion.getInstance(getApplicationContext()).saveToken(userId);
//                        try {
//                            WebserviceHelper.INSTANCE.login(getApplicationContext(), LoginType.FACEBOOK);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (WebserviceException e) {
//                            e.printStackTrace();
//                        }
                        ActivityHelper.startMainActivity(LoginActivity.this);
                    }

                    @Override
                    public void onCancel() {
                        isOnLoginPressed = false;
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        isOnLoginPressed = false;
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        LoginManager.getInstance().unregisterCallback(callbackManager);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public View.OnClickListener getGuestOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = sharedpreferences.edit();
                edit.putBoolean(IS_USER_LOGGED_IN, true);
                edit.apply();
                ActivityHelper.startMainActivity(getApplicationContext());
            }
        };
    }
}
