package com.example.mhmdreza_j.xproject.webservice.webservices.lucky_wheel

import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess
import java.io.IOException

class LuckyWheelProcess : BaseProcess() {
    @Throws(IOException::class, WebserviceException::class)
    override fun process(): LuckyWheelResponse = send(MyRetrofit.webserviceUrls.luckyWheel())
}