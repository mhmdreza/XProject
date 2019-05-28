package com.example.mhmdreza_j.xproject.views.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.application.ApplicationLoader;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.main_page.MainFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginFragment extends BaseFragment {
    public static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean isOnLoginPressed = false;
    private SharedPreferences sharedpreferences;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        if (getActivity() == null) return view;
        sharedpreferences = getActivity().getSharedPreferences(ApplicationLoader.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        FacebookSdk.sdkInitialize(getContext());
        ImageView imageView = view.findViewById(R.id.facebookButton);
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

        initFacebookLogin(view);

        TextView guestPlay = view.findViewById(R.id.guestPlay);
        TextView guestLogin = view.findViewById(R.id.guestLogin);
        guestLogin.setOnClickListener(getGuestOnClickListener());
        guestPlay.setOnClickListener(getGuestOnClickListener());
        return view;
    }

    @Override
    public void onStop() {
        LoginManager.getInstance().unregisterCallback(callbackManager);
        super.onStop();
    }

    private void initFacebookLogin(View view) {
        loginButton = view.findViewById(R.id.login_button);
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
                        nextFragment = new MainFragment();
                        onNextPressed();
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
    public void onDestroy() {
        super.onDestroy();
    }

    public View.OnClickListener getGuestOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = sharedpreferences.edit();
                edit.putBoolean(IS_USER_LOGGED_IN, true);
                edit.apply();
                nextFragment = new MainFragment();
                onNextPressed();
            }
        };
    }
}
