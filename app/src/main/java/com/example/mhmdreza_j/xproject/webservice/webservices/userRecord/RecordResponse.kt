package com.example.mhmdreza_j.xproject.webservice.webservices.userRecord

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse

class RecordResponse(
        @field: SerializedName("coins")
        var coins: Int = 0,

        @field: SerializedName("gem")
        var gem: Int = 0,

        @field: SerializedName("avatar")
        var avatar: Int = 0,

        @field: SerializedName("level")
        var level: Int = 0
): BaseResponse()