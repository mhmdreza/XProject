package com.example.mhmdreza_j.xproject.webservice

import android.content.Context
import com.example.mhmdreza_j.xproject.webservice.base.MyRetrofit
import com.example.mhmdreza_j.xproject.webservice.base.WebserviceException
import com.example.mhmdreza_j.xproject.webservice.base.constants.LoginType
import com.example.mhmdreza_j.xproject.webservice.pref.WebservicePrefSetting
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.lucky_wheel.LuckyWheelProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.lucky_wheel.LuckyWheelResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.spin_now.SpinNowProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.spin_now.SpinNowResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse
import java.io.IOException

object WebserviceHelper {

    @Throws(IOException::class, WebserviceException::class)
    fun login(context: Context, type: LoginType, token: String = ""): LoginResponse {
        val process = LoginProcess(type, token)
        val response = process.process()
        WebservicePrefSetting.getInstance(context).saveToken(response.token)
        WebservicePrefSetting.getInstance(context).isRegister = true
        MyRetrofit.recreateWebserviceUrls()
        return response
    }

    @Throws(IOException::class, WebserviceException::class)
    fun record(): RecordResponse = RecordProcess().process()

    @Throws(IOException::class, WebserviceException::class)
    fun profile(): ProfileResponse = ProfileProcess().process()

    @Throws(IOException::class, WebserviceException::class)
    fun buy(cost: Int): BuyResponse = BuyProcess(cost).process()

    @Throws(IOException::class, WebserviceException::class)
    fun spinNow(id: String): SpinNowResponse = SpinNowProcess(id).process()

    @Throws(IOException::class, WebserviceException::class)
    fun luckyWheel(): LuckyWheelResponse = LuckyWheelProcess().process()

}
