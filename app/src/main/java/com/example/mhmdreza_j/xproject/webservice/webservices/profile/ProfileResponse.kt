package com.example.mhmdreza_j.xproject.webservice.webservices.profile

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse

class ProfileResponse(
    @field: SerializedName("name")
    var name: String? ,

    @field: SerializedName("avatar")
    var avatar: Int? ,

    @field: SerializedName("level")
    var level: Int? ,

    @field: SerializedName("experience")
    var experience: Int? ,

    @field: SerializedName("flag")
    var flag: String? ,

    @field: SerializedName("coins")
    var coins: Int? ,

    @field: SerializedName("gem")
    var gem: Int? ,

    @field: SerializedName("win_strike")
    var winStrike: Int? ,

    @field: SerializedName("average_score")
    var averageScore: Int? ,

    @field: SerializedName("game_number")
    var gameNumber: Int? ,

    @field: SerializedName("won_number")
    var wonNumber: Int? ,

    @field: SerializedName("friends")
    var friends: List<Friend>?

): BaseResponse()
