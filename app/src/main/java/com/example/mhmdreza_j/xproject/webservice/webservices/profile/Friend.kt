package ir.sharif.vamdeh.webservices.webservices.profile

import com.google.gson.annotations.SerializedName

class Friend (
        @field: SerializedName("username")
        var username: String?,
        @field: SerializedName("avatar")
        var avatar: Int?
)