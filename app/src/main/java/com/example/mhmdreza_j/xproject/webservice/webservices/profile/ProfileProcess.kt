package com.example.mhmdreza_j.xproject.webservice.webservices.profile

import android.util.Log
import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseProcess
import java.io.IOException

class ProfileProcess: BaseProcess() {
    @Throws(IOException::class, WebserviceException::class)
    override fun process(): ProfileResponse {
        val profile = MyRetrofit.webserviceUrls.profile()
        return send(profile)
    }
}