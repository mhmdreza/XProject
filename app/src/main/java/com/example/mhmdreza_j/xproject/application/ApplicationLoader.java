package com.example.mhmdreza_j.xproject.application;

import android.app.Application;

import com.example.mhmdreza_j.xproject.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ApplicationLoader extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFont();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
