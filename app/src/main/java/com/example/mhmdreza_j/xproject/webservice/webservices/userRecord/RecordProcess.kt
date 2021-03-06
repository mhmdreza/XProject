package com.example.mhmdreza_j.xproject.webservice.webservices.userRecord

import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess
import java.io.IOException

class RecordProcess: BaseProcess() {

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): RecordResponse = send(MyRetrofit.webserviceUrls.record())
}