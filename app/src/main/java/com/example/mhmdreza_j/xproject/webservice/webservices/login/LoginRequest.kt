package ir.sharif.vamdeh.webservices.webservices.login

import com.google.gson.annotations.SerializedName

import ir.sharif.vamdeh.webservices.base.requestProcess.BaseRequest
import ir.sharif.vamdeh.webservices.base.Constants.LoginType

class LoginRequest(@field:SerializedName("type")
                   private val type: LoginType,
                   @field:SerializedName("access_token")
                   private val access_token: String = "") : BaseRequest()
