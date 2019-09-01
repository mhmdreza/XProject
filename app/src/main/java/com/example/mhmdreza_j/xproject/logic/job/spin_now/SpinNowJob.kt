package com.example.mhmdreza_j.xproject.logic.job.spin_now

import com.example.mhmdreza_j.xproject.logic.SPIN_NOW
import com.example.mhmdreza_j.xproject.logic.job.BaseJob
import com.example.mhmdreza_j.xproject.webservice.WebserviceHelper
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import org.greenrobot.eventbus.EventBus
import java.io.IOException

class SpinNowJob : BaseJob() {

    override fun onRunJob(params: Params): Result {
        try {
            val response = WebserviceHelper.spinNow(id)
            id = ""
            EventBus.getDefault().post(OnSpinNowJobSuccessEvent(response))
            return Result.SUCCESS
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: WebserviceException) {
            e.printStackTrace()
        }

        return Result.FAILURE
    }

    companion object {
        private var id = ""

        fun schedule(id: String) {
            SpinNowJob.id = id
            scheduleImmediateJob(SPIN_NOW)
        }
    }
}
