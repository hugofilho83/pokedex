package br.htaf.com.data.response

import com.google.gson.annotations.SerializedName


data class PokemonsBodyResponse (
    @SerializedName("count")
    val count: Int,
    @SerializedName("next:")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val pokemonResults: List<PokemonsResultsReponse>
)