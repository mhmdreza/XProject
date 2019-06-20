package com.example.mhmdreza_j.xproject.logic.job.login

import android.util.Log
import com.example.mhmdreza_j.xproject.application.ApplicationLoader
import com.example.mhmdreza_j.xproject.logic.MyJobCreator.Companion.LOGIN
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import ir.sharif.vamdeh.webservices.base.Constants.LoginType
import org.greenrobot.eventbus.EventBus

class LoginJob : BaseJob() {
    override fun onRunJob(params: Params): Result {
        val login = WebserviceHelper.login(ApplicationLoader.getContext(), LoginType.GUEST)
        EventBus.getDefault().post(OnLoginSuccessEvent())
        Log.d("APP APP", "Success login")
        return Result.SUCCESS
    }

    companion object {

        fun schedule() {
            scheduleImmediateJob(LOGIN)
        }
    }
}
