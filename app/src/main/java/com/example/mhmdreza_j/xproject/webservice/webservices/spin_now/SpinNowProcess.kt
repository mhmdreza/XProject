package com.example.mhmdreza_j.xproject.webservice.webservices.spin_now

import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess
import java.io.IOException

class SpinNowProcess(id: String) : BaseProcess() {
    private val request = SpinNowRequest(id)

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): SpinNowResponse = send(MyRetrofit.webserviceUrls.spinNow(request))
}
