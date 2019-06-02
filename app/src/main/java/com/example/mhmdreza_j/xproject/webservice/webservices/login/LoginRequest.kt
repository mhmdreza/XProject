package com.example.mhmdreza_j.xproject.webservice.webservices.login

import com.google.gson.annotations.SerializedName

import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseRequest
import com.example.mhmdreza_j.xproject.webservice.base.constants.LoginType

class LoginRequest(@field:SerializedName("type")
                   private val type: LoginType,
                   @field:SerializedName("access_token")
                   private val access_token: String = "") : BaseRequest()
