package br.htaf.com.data.response

import com.google.gson.annotations.SerializedName

data class Types(
    @SerializedName("type")
    val type: Type
)