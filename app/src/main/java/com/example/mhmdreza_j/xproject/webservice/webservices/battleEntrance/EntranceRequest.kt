package com.example.mhmdreza_j.xproject.webservice.webservices.battleEntrance

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.constants.Category
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseRequest

class EntranceRequest(
        @field: SerializedName("category")
        val category: Category
): BaseRequest()