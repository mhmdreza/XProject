package com.example.mhmdreza_j.xproject.logic.job.lucky_wheel

import com.example.mhmdreza_j.xproject.logic.LUCKY_WHEEL
import com.example.mhmdreza_j.xproject.logic.MAIN_RECORD
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import org.greenrobot.eventbus.EventBus
import java.io.IOException

class LuckyWheelJob : BaseJob() {

    override fun onRunJob(params: Params): Result {
        try {
            val response = WebserviceHelper.luckyWheel()
            EventBus.getDefault().post(OnLuckyWheelJobSuccessEvent(response))
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
            scheduleImmediateJob(LUCKY_WHEEL)
        }
    }
}
