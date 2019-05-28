package com.example.mhmdreza_j.xproject.views.main_page;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.application.ApplicationLoader;
import com.example.mhmdreza_j.xproject.views.base_class.BaseActivity;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.coin.CoinFragment;
import com.example.mhmdreza_j.xproject.views.login.LoginFragment;
import com.example.mhmdreza_j.xproject.views.market.MarketFragment;
import com.example.mhmdreza_j.xproject.views.wheel_of_furtune.WheelOfFortuneFragment;

import static com.example.mhmdreza_j.xproject.views.login.LoginFragment.IS_USER_LOGGED_IN;

public class MainActivity extends BaseActivity {


    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(ApplicationLoader.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(IS_USER_LOGGED_IN)
                && sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false)) {
            startFragment(new MainFragment());
        }
        else {
            startFragment(new LoginFragment());
        }
    }

    public void startFragment(BaseFragment fragment) {
        this.fragment = fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        fragment.onBackPressed();
    }
}
