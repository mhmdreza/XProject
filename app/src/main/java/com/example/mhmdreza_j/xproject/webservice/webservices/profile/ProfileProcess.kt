package ir.sharif.vamdeh.webservices.webservices.profile

import ir.sharif.vamdeh.webservices.base.MyRetrofit
import ir.sharif.vamdeh.webservices.base.WebserviceException
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseProcess
import java.io.IOException

class ProfileProcess: BaseProcess() {
    private val request: ProfileRequest = ProfileRequest()

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): ProfileResponse{
        val profile = MyRetrofit.webserviceUrls.profile()
        return send(profile)
    }
}