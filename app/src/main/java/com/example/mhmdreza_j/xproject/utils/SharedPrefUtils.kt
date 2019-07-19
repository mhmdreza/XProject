package com.example.mhmdreza_j.xproject.utils

import android.content.Context
import android.content.SharedPreferences

import com.example.mhmdreza_j.xproject.application.ApplicationLoader


object SharedPrefUtils {
    private const val SHARED_PREFERENCE_KEY = "XPROJECT_SHARED_PREFERENCE"
    private val sharedPref = ApplicationLoader.appContext!!.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE)

    fun getLong(key: String, defaultValue: Long): Long {
        return sharedPref.getLong(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        sharedPref.edit().putLong(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPref.edit().putBoolean(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPref.getInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        sharedPref.edit().putInt(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPref.getString(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }
}
