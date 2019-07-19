package com.example.mhmdreza_j.xproject.logic.job.record

import com.example.mhmdreza_j.xproject.logic.MyJobCreator.Companion.MAIN_RECORD
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import org.greenrobot.eventbus.EventBus
import java.io.IOException

class GetRecordJob : BaseJob() {

    override fun onRunJob(params: Params): Result {
        try {
            val record = WebserviceHelper.record()
            EventBus.getDefault().post(OnRecordJobSuccessEvent(record))
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
            scheduleImmediateJob(MAIN_RECORD)
        }
    }
}
