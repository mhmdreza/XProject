package com.example.mhmdreza_j.xproject.views.main_page;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.application.ApplicationLoader;
import com.example.mhmdreza_j.xproject.utils.SharedPrefUtils;
import com.example.mhmdreza_j.xproject.views.LoadingDialog;
import com.example.mhmdreza_j.xproject.views.base_class.BaseFragment;
import com.example.mhmdreza_j.xproject.views.login.LoginFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.mhmdreza_j.xproject.views.login.LoginFragment.IS_USER_LOGGED_IN;
import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private LoadingDialog loadingDialog;
    private BaseFragment fragment;
    private MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_main);

        if (SharedPrefUtils.getBoolean(IS_USER_LOGGED_IN, false)) {
            startFragment(new MainFragment());
        } else {
            startFragment(new LoginFragment());
        }
        loadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        player = MediaPlayer.create(this, R.raw.background_music);
        player.start();
        player.setLooping(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void stopMusic(){
        player.stop();
    }

    public void restartMusic(){
        player.seekTo(0);
    }

    public void startFragment(BaseFragment fragment) {
        this.fragment = fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }

    @Override
    public void onBackPressed() {
        fragment.onBackPressed();
    }

    public void showLoading() {
        loadingDialog.show();
    }

    public void hideLoading() {
        loadingDialog.hide();
    }
}
