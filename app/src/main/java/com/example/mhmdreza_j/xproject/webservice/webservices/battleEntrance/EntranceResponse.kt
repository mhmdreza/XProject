package com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse

class EntranceResponse(
        @field: SerializedName("opponent")
        val opponentID: String
): BaseResponse()