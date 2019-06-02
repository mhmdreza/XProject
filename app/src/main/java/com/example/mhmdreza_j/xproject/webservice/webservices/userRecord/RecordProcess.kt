package com.example.mhmdreza_j.xproject.webservice.webservices.userRecord

import ir.sharif.vamdeh.webservices.base.MyRetrofit
import ir.sharif.vamdeh.webservices.base.WebserviceException
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseProcess
import java.io.IOException

class RecordProcess: BaseProcess() {
    private val request: RecordRequest = RecordRequest()

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): RecordResponse = send(MyRetrofit.webserviceUrls.record())
}