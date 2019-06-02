package com.example.mhmdreza_j.xproject.webservice.webservices.buy

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseRequest

class BuyRequest(
        @field: SerializedName("cost")
        val cost: Int = 0
): BaseRequest()