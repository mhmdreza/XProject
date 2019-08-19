package com.example.mhmdreza_j.xproject.webservice.base

import android.util.Log
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.mhmdreza_j.xproject.webservice.pref.WebservicePrefSetting

import com.example.mhmdreza_j.xproject.webservice.base.constants.BASE_URL

object MyRetrofit {

    var webserviceUrls: WebserviceUrls = getUrls()

    val token: String
        get() {
            return WebservicePrefSetting.instanceWithoutContext.token
        }


    private fun getUrls(): WebserviceUrls {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        addAuthHeader(builder)
        val client = builder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        return retrofit.create(WebserviceUrls::class.java)
    }

    private fun addLogginInterceptor(client: OkHttpClient.Builder) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(interceptor)
    }

    private fun addAuthHeader(client: OkHttpClient.Builder) {
        if (WebservicePrefSetting.instanceWithoutContext.isRegister) {
            client.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .build()

                chain.proceed(request)
            }
        }
    }
}
