package com.example.mhmdreza_j.xproject.webservice.webservices.profile

import com.google.gson.annotations.SerializedName
import com.example.mhmdreza_j.xproject.webservice.base.requestProcess.BaseResponse
import com.google.gson.annotations.Expose

class ProfileResponse(
        @field: SerializedName("show") @Expose
        val showData: ShowModel?,

        @field: SerializedName("level") @Expose
        val level: Int?,

        @field: SerializedName("experience") @Expose
        val experience: Int?,

        @field: SerializedName("flag") @Expose
        val flag: String?,

        @field: SerializedName("coins") @Expose
        val coins: Int?,

        @field: SerializedName("gem") @Expose
        val gem: Int?,

        @field: SerializedName("win_strike") @Expose
        val winStrike: Int?,

        @field: SerializedName("average_score") @Expose
        val averageScore: Int?,

        @field: SerializedName("game_number") @Expose
        val gameNumber: Int?,

        @field: SerializedName("won_number") @Expose
        val wonNumber: Int?,

        @field: SerializedName("friends") @Expose
        val friends: List<Friend>?

) : BaseResponse()

class ShowModel(
        @field: SerializedName("name") @Expose
        val name: String?,

        @field: SerializedName("username") @Expose
        val username: String?,

        @field: SerializedName("avatar") @Expose
        val avatar: Int?
)


