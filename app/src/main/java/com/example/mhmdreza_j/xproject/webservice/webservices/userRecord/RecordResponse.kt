package ir.sharif.vamdeh.webservices.webservices.userRecord

import com.google.gson.annotations.SerializedName
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseResponse

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