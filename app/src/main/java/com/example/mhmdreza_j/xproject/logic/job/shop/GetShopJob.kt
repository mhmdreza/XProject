package com.example.mhmdreza_j.xproject.logic.job.shop

import com.example.mhmdreza_j.xproject.logic.MAIN_RECORD
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import java.io.IOException

class GetShopJob : BaseJob() {

    override fun onRunJob(params: Params): Result {
        try {
//            val record = WebserviceHelper.shop()
//            EventBus.getDefault().post(OnShopJobSuccessEvent(record))
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
