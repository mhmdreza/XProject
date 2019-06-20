package com.example.mhmdreza_j.xproject.logic

import com.evernote.android.job.Job
import com.evernote.android.job.JobCreator
import com.example.mhmdreza_j.xproject.logic.job.battle.EnterBattleJob
import com.example.mhmdreza_j.xproject.logic.job.login.LoginJob
import com.example.mhmdreza_j.xproject.logic.job.profile.GetProfileJob
import com.example.mhmdreza_j.xproject.logic.job.record.GetRecordJob
import com.example.mhmdreza_j.xproject.webservice.base.constants.WebserviceAdresses.LOGIN

class MyJobCreator : JobCreator {

    override fun create(s: String): Job? {
        return when (s) {
            PROFILE -> GetProfileJob()
            LOGIN -> LoginJob()
            MAIN_RECORD -> GetRecordJob()
            ENTER_BATTLE -> EnterBattleJob()
            else -> null
        }
    }

    companion object {
        const val PROFILE = "PROFILE"
        const val LOGIN = "LOGIN"
        const val ENTER_BATTLE = "ENTER_BATTLE"
        const val MAIN_RECORD = "MAIN_RECORD"
    }
}
