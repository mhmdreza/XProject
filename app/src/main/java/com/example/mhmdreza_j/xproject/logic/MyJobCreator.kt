package com.example.mhmdreza_j.xproject.logic

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator
import com.example.mhmdreza_j.xproject.logic.job.login.LoginJob
import com.example.mhmdreza_j.xproject.logic.job.lucky_wheel.LuckyWheelJob
import com.example.mhmdreza_j.xproject.logic.job.profile.GetProfileJob
import com.example.mhmdreza_j.xproject.logic.job.record.GetRecordJob
import com.example.mhmdreza_j.xproject.logic.job.spin_now.SpinNowJob

const val PROFILE = "PROFILE"
const val LOGIN = "LOGIN"
const val MAIN_RECORD = "MAIN_RECORD"
const val LUCKY_WHEEL = "LUCKY_WHEEL"
const val SPIN_NOW = "SPIN_NOW"

class MyJobCreator : JobCreator {

    override fun create(s: String): Job? {
        return when (s) {
            PROFILE -> GetProfileJob()
            LOGIN -> LoginJob()
            MAIN_RECORD -> GetRecordJob()
            LUCKY_WHEEL -> LuckyWheelJob()
            SPIN_NOW -> SpinNowJob()
            else -> null
        }
    }
}
