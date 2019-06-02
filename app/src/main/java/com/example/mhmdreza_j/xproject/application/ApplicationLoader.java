package com.example.mhmdreza_j.xproject.application;

import android.app.Application;

import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobManager;
import com.example.mhmdreza_j.xproject.R;
import com.example.mhmdreza_j.xproject.logic.MyJobCreator;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ApplicationLoader extends Application {
    public static final String SHARED_PREFERENCE_KEY = "xproject shared preference";

    @Override
    public void onCreate() {
        super.onCreate();
        JobManager.create(this).addJobCreator(new MyJobCreator());
        initFont();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
