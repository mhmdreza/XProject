package com.example.mhmdreza_j.xproject.webservice.webservices.buy

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse

class BuyResponse(
        @field: SerializedName("result")
        val result: String
): BaseResponse()