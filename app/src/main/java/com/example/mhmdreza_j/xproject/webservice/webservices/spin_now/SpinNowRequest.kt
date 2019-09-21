package com.example.mhmdreza_j.xproject.webservice.webservices.spin_now

import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseRequest
import com.google.gson.annotations.SerializedName

class SpinNowRequest(
        @field: SerializedName("item_id")
        val id: String = ""
) : BaseRequest()
