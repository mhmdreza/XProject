package com.example.mhmdreza_j.xproject.webservice.base

import com.example.mhmdreza_j.xproject.webservice.base.constants.LoginType
import com.example.mhmdreza_j.xproject.webservice.base.constants.WebserviceAdresses
import com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance.EntranceRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance.EntranceResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyResponse
import retrofit2.Call
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse
import retrofit2.http.*

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
