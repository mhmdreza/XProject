package com.example.mhmdreza_j.xproject.application

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.evernote.android.job.JobManager
import com.example.mhmdreza_j.xproject.BuildConfig
import com.example.mhmdreza_j.xproject.R
import com.example.mhmdreza_j.xproject.logic.MyJobCreator
import com.example.mhmdreza_j.xproject.utils.APP_KEY
import com.example.mhmdreza_j.xproject.webservice.pref.WebservicePrefSetting
import io.fabric.sdk.android.Fabric
import ir.tapsell.sdk.Tapsell
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class ApplicationLoader : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        JobManager.create(this).addJobCreator(MyJobCreator())

        WebservicePrefSetting.getInstance(applicationContext)

        Tapsell.initialize(this, APP_KEY)
        initFont()

        appContext = applicationContext
    }

    private fun initFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/LucidaGrandeBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}
