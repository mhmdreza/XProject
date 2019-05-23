package ir.sharif.vamdeh.webservices.webservices.buy

import com.google.gson.annotations.SerializedName
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseResponse

class BuyResponse(
        @field: SerializedName("result")
        val result: String
): BaseResponse()