package com.example.mhmdreza_j.xproject.views.main_page;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.application.ApplicationLoader;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.login.LoginFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.mhmdreza_j.xproject.views.login.LoginFragment.IS_USER_LOGGED_IN;

public class MainActivity extends AppCompatActivity {


    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
