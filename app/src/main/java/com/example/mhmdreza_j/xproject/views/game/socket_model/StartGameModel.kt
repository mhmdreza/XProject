package com.example.mhmdreza_j.xproject.views.game.socket_model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

class StartGameModel : Serializable {
    @SerializedName("username_1")
    var firstPerson: String? = null
    @SerializedName("username_2")
    var secondPerson: String? = null
    @SerializedName("avatar_1")
    var firstavatar: String? = null
    @SerializedName("avatar_2")
    var secondAvatar: String? = null

    @SerializedName("question")
    var question: QuestionModel? = null

}

class QuestionModel : Serializable {
    @SerializedName("image")
    var image: String? = null
    @SerializedName("desc")
    var description: String? = null
}

class AnswerValidationModel : Serializable {
    @SerializedName("validation")
    var validation: String? = null
    @SerializedName("player")
    var player: String? = null
}

class InGameQuestionModel : Serializable {
    @SerializedName("question")
    var question: QuestionModel? = null
}

class ResultModel : Serializable {
    @SerializedName("result")
    var result: String = ""
}


internal class AnswerModel(private val answer: Int, private val validator: Int, private val player: String)