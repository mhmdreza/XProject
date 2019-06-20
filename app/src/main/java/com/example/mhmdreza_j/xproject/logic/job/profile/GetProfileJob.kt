package com.example.mhmdreza_j.xproject.logic.job.profile

import com.example.mhmdreza_j.xproject.logic.MyJobCreator.Companion.PROFILE
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import org.greenrobot.eventbus.EventBus
import java.io.IOException

class GetProfileJob : BaseJob() {
    override fun onRunJob(params: Params): Result {
        try {
            val profile = WebserviceHelper.profile()
            EventBus.getDefault().post(OnProfileJobSuccessEvent(profile))
            return Result.SUCCESS
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: WebserviceException) {
            e.printStackTrace()
        }

        return Result.FAILURE
    }

    companion object {

        fun schedule() {
            scheduleImmediateJob(PROFILE)
        }
    }
}
