package br.htaf.com.data.response

import com.google.gson.annotations.SerializedName

data class Moves(
    @SerializedName("move")
    val move: Move
)