package ir.sharif.vamdeh.webservices.webservices.buy

import com.google.gson.annotations.SerializedName
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseRequest

class BuyRequest(
        @field: SerializedName("cost")
        val cost: Int = 0
): BaseRequest()