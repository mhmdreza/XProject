package com.example.mhmdreza_j.xproject.application;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.logic.MyJobCreator;
import com.example.mhmdreza_j.xproject.webservice.pref.WebservicePrefSetting;

import ir.tapsell.sdk.Tapsell;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ApplicationLoader extends Application {
    private static final String APP_KEY = "tharadhgrppggoiepabigobtrdkettlgcejomnkttptnneqcisoadammdmpfgrjfcfjaci";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        JobManager.create(this).addJobCreator(new MyJobCreator());

        WebservicePrefSetting.Companion.getInstance(getApplicationContext());

        Tapsell.initialize(this, APP_KEY);
        initFont();
        context = getApplicationContext();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public static Context getContext() {
        return context;
    }
}
