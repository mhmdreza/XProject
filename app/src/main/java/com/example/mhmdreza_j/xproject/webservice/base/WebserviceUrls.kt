package com.example.mhmdreza_j.xproject.webservice.base

import com.example.mhmdreza_j.xproject.webservice.base.constants.*
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.buy.BuyResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.login.LoginResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.lucky_wheel.LuckyWheelResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.profile.ProfileResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.spin_now.SpinNowRequest
import com.example.mhmdreza_j.xproject.webservice.webservices.spin_now.SpinNowResponse
import com.example.mhmdreza_j.xproject.webservice.webservices.userRecord.RecordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebserviceUrls {
    @POST(LOGIN)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(RECORD)
    fun record(): Call<RecordResponse>

    @GET(PROFILE)
    fun profile(): Call<ProfileResponse>

    @GET(BUY)
    fun buy(@Body request: BuyRequest): Call<BuyResponse>

    @GET(LUCKY_WHEEL)
    fun luckyWheel(): Call<LuckyWheelResponse>

    @POST(SPIN_NOW)
    fun spinNow(request: SpinNowRequest): Call<SpinNowResponse>
}
