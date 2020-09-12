package br.htaf.com.data.response

import com.google.gson.annotations.SerializedName

data class PokemonBodyDetailsResponse(
    @SerializedName("abilities")
    val abilities : List<Abilities>,
    @SerializedName("height")
    val height : Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites : Sprint,
    @SerializedName("types")
    val types : List<Types>,
    @SerializedName("weight")
    val weight : Int,
    @SerializedName("moves")
    val moves : List<Moves>
)