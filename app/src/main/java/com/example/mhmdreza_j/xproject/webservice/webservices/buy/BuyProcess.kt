package com.example.mhmdreza_j.xproject.webservice.webservices.buy

import ir.sharif.vamdeh.webservices.base.MyRetrofit
import ir.sharif.vamdeh.webservices.base.WebserviceException
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseProcess
import java.io.IOException

class BuyProcess(cost: Int): BaseProcess() {
    private val request: BuyRequest = BuyRequest(cost)

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): BuyResponse = send(MyRetrofit.webserviceUrls.buy(request))
}