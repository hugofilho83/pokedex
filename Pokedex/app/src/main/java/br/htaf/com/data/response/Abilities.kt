package br.htaf.com.data.response

import com.google.gson.annotations.SerializedName

data class Abilities(
    @SerializedName("ability")
    val ability: Ability
)