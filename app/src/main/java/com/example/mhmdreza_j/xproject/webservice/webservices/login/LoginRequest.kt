package com.example.mhmdreza_j.xproject.webservice.webservices.login

import com.google.gson.annotations.SerializedName

import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseRequest
import com.google.gson.annotations.Expose

class LoginRequest(@field:SerializedName("login_type") @Expose
                   private val type: String,
                   @field:SerializedName("access_token") @Expose
                   private val access_token: String) : BaseRequest()
