package ir.sharif.vamdeh.webservices

import android.content.Context
import ir.sharif.vamdeh.webservices.base.Constants.Category
import ir.sharif.vamdeh.webservices.base.Constants.LoginType

import java.io.IOException

import ir.sharif.vamdeh.webservices.base.WebserviceException
import ir.sharif.vamdeh.webservices.pref.WebservicePrefSetting
import ir.sharif.vamdeh.webservices.webservices.battleEntrance.EntranceProcess
import ir.sharif.vamdeh.webservices.webservices.battleEntrance.EntranceResponse
import ir.sharif.vamdeh.webservices.webservices.buy.BuyProcess
import ir.sharif.vamdeh.webservices.webservices.buy.BuyResponse
import ir.sharif.vamdeh.webservices.webservices.login.LoginProcess
import ir.sharif.vamdeh.webservices.webservices.login.LoginResponse
import ir.sharif.vamdeh.webservices.webservices.profile.ProfileProcess
import ir.sharif.vamdeh.webservices.webservices.profile.ProfileResponse
import ir.sharif.vamdeh.webservices.webservices.userRecord.RecordProcess
import ir.sharif.vamdeh.webservices.webservices.userRecord.RecordResponse

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
