package com.example.mhmdreza_j.xproject.webservice.webservices.login

import java.io.IOException

import com.example.mhmdreza_j.xproject.webservice.base.constants.LoginType
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess
import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException

class LoginProcess(type: LoginType) : BaseProcess() {
    private val request: LoginRequest = LoginRequest(type)

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): LoginResponse {
        val login = MyRetrofit.webserviceUrls.login(request)
        return send(login)
    }
}
