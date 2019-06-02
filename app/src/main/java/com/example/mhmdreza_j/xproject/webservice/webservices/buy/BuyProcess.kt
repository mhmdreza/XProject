package com.example.mhmdreza_j.xproject.webservice.webservices.buy

import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess
import java.io.IOException

class BuyProcess(cost: Int): BaseProcess() {
    private val request: BuyRequest = BuyRequest(cost)

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): BuyResponse = send(MyRetrofit.webserviceUrls.buy(request))
}