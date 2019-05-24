package ir.sharif.vamdeh.webservices.base

import ir.sharif.vamdeh.webservices.base.Constants.WebserviceAdresses
import ir.sharif.vamdeh.webservices.webservices.battleEntrance.EntranceRequest
import ir.sharif.vamdeh.webservices.webservices.battleEntrance.EntranceResponse
import ir.sharif.vamdeh.webservices.webservices.buy.BuyRequest
import ir.sharif.vamdeh.webservices.webservices.buy.BuyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import ir.sharif.vamdeh.webservices.webservices.login.LoginRequest
import ir.sharif.vamdeh.webservices.webservices.login.LoginResponse
import ir.sharif.vamdeh.webservices.webservices.profile.ProfileResponse
import ir.sharif.vamdeh.webservices.webservices.userRecord.RecordResponse
import retrofit2.http.POST

interface WebserviceUrls {
    @POST(WebserviceAdresses.LOGIN)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(WebserviceAdresses.RECORD)
    fun record(): Call<RecordResponse>

    @GET(WebserviceAdresses.PROFILE)
    fun profile(): Call<ProfileResponse>

    @GET(WebserviceAdresses.ENTRANCE)
    fun enterBattle(@Body request: EntranceRequest): Call<EntranceResponse>

    @GET(WebserviceAdresses.BUY)
    fun buy(@Body request: BuyRequest): Call<BuyResponse>
}
