package com.example.mhmdreza_j.xproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mhmdreza_j.xproject.application.ApplicationLoader;


public class SharedPrefUtils {
    private static final String SHARED_PREFERENCE_KEY = "XPROJECT_SHARED_PREFERENCE";
    private static final SharedPreferences sharedPref = ApplicationLoader.getContext()
            .getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);

    public static long getLong(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    public static void putLong(String key, long value) {
        sharedPref.edit().putLong(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPref.getBoolean(key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        sharedPref.edit().putBoolean(key, value).apply();
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        sharedPref.edit().putInt(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }

    public static void putString(String key, String value) {
        sharedPref.edit().putString(key, value).apply();
    }
}
