package br.htaf.com.data.model

data class PokemonDetails(
    var name: String,
    val types: List<String>,
    val skills: List<String>,
    val abilities : List<String>,
    val weight: Float,
    val height: Float
)