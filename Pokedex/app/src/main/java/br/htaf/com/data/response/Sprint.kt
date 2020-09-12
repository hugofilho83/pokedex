package br.htaf.com.data.response

import com.google.gson.annotations.SerializedName

data class Sprint(
    @SerializedName("front_default")
   val front_default : String,
    @SerializedName("front_shiny")
    val front_shiny : String
)