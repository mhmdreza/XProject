package com.example.mhmdreza_j.xproject.webservice

import android.content.Context
import ir.sharif.vamdeh.webservices.base.Constants.Category
import ir.sharif.vamdeh.webservices.base.Constants.LoginType

import java.io.IOException

import ir.sharif.vamdeh.webservices.base.WebserviceException
import ir.sharif.vamdeh.webservices.pref.WebservicePrefSetting
import com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance.EntranceProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance.EntranceResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordProcess
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse

object WebserviceHelper {
    @Throws(IOException::class, WebserviceException::class)
    fun login(context: Context, type: LoginType): LoginResponse {
        val process = LoginProcess(type)
        val response = process.process()
        WebservicePrefSetting.getInstance(context).saveToken(response.token)
        return response
    }

    @Throws(IOException::class, WebserviceException::class)
    fun record(): RecordResponse = RecordProcess().process()

    @Throws(IOException::class, WebserviceException::class)
    fun profile(): ProfileResponse = ProfileProcess().process()

    @Throws(IOException::class, WebserviceException::class)
    fun buy(cost: Int): BuyResponse = BuyProcess(cost).process()

    @Throws(IOException::class, WebserviceException::class)
    fun enterBattle(category: Category): EntranceResponse = EntranceProcess(category).process()

}
