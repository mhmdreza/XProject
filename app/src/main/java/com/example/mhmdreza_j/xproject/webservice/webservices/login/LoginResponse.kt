package com.example.mhmdreza_j.xproject.webservice.webservices.login

import com.google.gson.annotations.SerializedName

import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse

class LoginResponse(@field:SerializedName("token")
                    val token: String) : BaseResponse()
