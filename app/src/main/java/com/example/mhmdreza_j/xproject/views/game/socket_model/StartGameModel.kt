package com.example.mhmdreza_j.xproject.views.game.socket_model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StartGameModel : Serializable {
    @SerializedName("players")
    var players: ArrayList<PlayerModel> = ArrayList()

    @SerializedName("question")
    var question: QuestionModel? = null

}

class PlayerModel : Serializable {
    @SerializedName("username")
    var username: String? = null
    @SerializedName("avatar")
    var avatar: Int? = null
}

class QuestionModel : Serializable {
//    @SerializedName("image")
//    var image: ByteArray = ByteArray(1024)
    @SerializedName("desc")
    var description: String? = null
}

class AnswerValidationModel : Serializable {
    @SerializedName("validation")
    var validation: Int? = null
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