package ir.sharif.vamdeh.webservices.webservices.battleEntrance

import com.google.gson.annotations.SerializedName
import ir.sharif.vamdeh.webservices.base.Constants.Category
import ir.sharif.vamdeh.webservices.base.requestProcess.BaseRequest

class EntranceRequest(
        @field: SerializedName("category")
        val category: Category
): BaseRequest()