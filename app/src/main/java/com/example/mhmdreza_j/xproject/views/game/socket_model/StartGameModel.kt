package com.example.mhmdreza_j.xproject.views.game.socket_model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StartGameModel : Serializable {
    @SerializedName("players")
    var players: ArrayList<PlayerModel> = ArrayList()

    @SerializedName("question")
    lateinit var question: QuestionModel

}

class PlayerModel : Serializable {
    @SerializedName("username")
    var username: String? = null
    @SerializedName("avatar")
    var avatar: Int? = null
}

class QuestionModel : Serializable {
    @SerializedName("desc")
    var description: String? = null
    @SerializedName("url")
    var url: String? = null
}

class AnswerValidationModel : Serializable {
    @SerializedName("validation")
    var validation: Int? = null
    @SerializedName("player")
    var player: String? = null
}

class InGameQuestionModel : Serializable {
    @SerializedName("question")
    lateinit var question: QuestionModel
}

class ResultModel : Serializable {
    @SerializedName("result")
    var result: String = ""
    @SerializedName("answers")
    var answers = ArrayList<String>()
}