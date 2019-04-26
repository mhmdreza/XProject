package com.example.mhmdreza_j.xproject.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.utils.ActivityHelper;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.example.mhmdreza_j.xproject.views.main_page.MainActivity;
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
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean isOnLoginPressed = false;
    private boolean isUserLoggedIn = false;

    public LoginActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);
        ImageView imageView = findViewById(R.id.facebookButton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnLoginPressed && !isUserLoggedIn) {
                    isOnLoginPressed = true;
                    loginButton.callOnClick();
                }
            }
        });
        callbackManager = CallbackManager.Factory.create();

        initFacebookLogin();
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
                        isUserLoggedIn = true;
                        String userId = loginResult.getAccessToken().getUserId();
                        WebservicePrefSetting.Companion.getInstance(getApplicationContext()).saveToken(userId);
                        try {
                            WebserviceHelper.INSTANCE.login(getApplicationContext(), LoginType.FACEBOOK);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (WebserviceException e) {
                            e.printStackTrace();
                        }
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
}
