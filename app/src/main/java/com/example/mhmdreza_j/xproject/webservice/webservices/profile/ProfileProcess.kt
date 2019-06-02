package com.example.mhmdreza_j.xproject.webservice.webservices.profile

import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import ir.sharif.vamdeh.webservices.base.MyRetrofit
import ir.sharif.vamdeh.webservices.base.WebserviceException
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseProcess
import java.io.IOException

class ProfileProcess: BaseProcess() {
    private val request: ProfileRequest = ProfileRequest()

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): ProfileResponse {
        val profile = MyRetrofit.webserviceUrls.profile()
        return send(profile)
    }
}