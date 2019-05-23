package ir.sharif.vamdeh.webservices.webservices.login

import java.io.IOException

import ir.sharif.vamdeh.webservices.base.Constants.LoginType
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseProcess
import ir.sharif.vamdeh.webservices.base.MyRetrofit
import ir.sharif.vamdeh.webservices.base.WebserviceException

class LoginProcess(type: LoginType) : BaseProcess() {
    private val request: LoginRequest = LoginRequest(type)

    @Throws(IOException::class, WebserviceException::class)
    override fun process(): LoginResponse {
        val login = MyRetrofit.webserviceUrls.login(request)
        return send(login)
    }
}
