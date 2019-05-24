package ir.sharif.vamdeh.webservices.webservices.battleEntrance

import com.google.gson.annotations.SerializedName
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseResponse

class EntranceResponse(
        @field: SerializedName("opponent")
        val opponentID: String
): BaseResponse()