package com.example.mhmdreza_j.xproject.webservice.webservices.lucky_wheel

import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse
import com.google.gson.annotations.SerializedName

class LuckyWheelResponse(
        @field: SerializedName("items")
        val items: ArrayList<LuckyWheelItem>,
        @field: SerializedName("selectedItem")
        val selectedItem: LuckyWheelItem
) : BaseResponse()

class LuckyWheelItem(
        @field: SerializedName("type")
        val type: String,
        @field: SerializedName("value")
        val value: Int) {

    override fun equals(other: Any?): Boolean {
        return if (other !is LuckyWheelItem) false
        else other.type == type && other.value == value
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}
