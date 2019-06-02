package com.example.mhmdreza_j.xproject.webservice.webservices.profile

import com.google.gson.annotations.SerializedName

class Friend (
        @field: SerializedName("username")
        var username: String?,
        @field: SerializedName("avatar")
        var avatar: Int?
)