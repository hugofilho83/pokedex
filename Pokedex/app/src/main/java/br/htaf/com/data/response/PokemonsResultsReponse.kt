package br.htaf.com.data.response

import br.htaf.com.data.model.Pokemon
import com.google.gson.annotations.SerializedName

data class PokemonsResultsReponse(
    @SerializedName("name")
    val name : String,
    @SerializedName("url")
    val url : String
){
    fun getPokemonModel() = Pokemon(
        number = getNumeroPokemon(url),
        name = name,
        photo =  null
    )

    private fun getNumeroPokemon(velue : String):String{
        val separado: List<String> = velue.split("/").map { it.trim() };

        return  separado[6]
    }
}