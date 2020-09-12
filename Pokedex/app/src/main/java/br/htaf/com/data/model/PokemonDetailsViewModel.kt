package br.htaf.com.data.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.htaf.com.R
import br.htaf.com.data.PokeApiService
import br.htaf.com.data.response.PokemonBodyDetailsResponse
import br.htaf.com.data.response.Types
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailsViewModel : ViewModel() {
    val pokemonDetails : MutableLiveData<PokemonDetails> = MutableLiveData();
    val viewFlipperPokemnDetails : MutableLiveData<Pair<Int, Int?>> = MutableLiveData();

    fun getPokemonDetails(number: Int){
        PokeApiService.iniRetrofit().getPokemonDetails(number).enqueue(object : Callback<PokemonBodyDetailsResponse>{

            override fun onResponse(
                call: Call<PokemonBodyDetailsResponse>,
                response: Response<PokemonBodyDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemonsDetails : PokemonDetails;
                    response.body()?.let {pokemonBodyDetailsResponse ->

                        val tipos = pokemonBodyDetailsResponse.types.map { it.type.name }

                        pokemonsDetails = PokemonDetails(
                           name = pokemonBodyDetailsResponse.name,
                           types =  pokemonBodyDetailsResponse.types.map { it.type.name },
                           skills = pokemonBodyDetailsResponse.moves.map { moves -> moves.move.name } ,
                           abilities = pokemonBodyDetailsResponse.abilities.map { abilities ->  abilities.ability.name  },
                           weight = (pokemonBodyDetailsResponse.weight / 10).toFloat(),
                           height = (pokemonBodyDetailsResponse.height / 10).toFloat()
                        );

                        pokemonDetails.value = pokemonsDetails;
                        viewFlipperPokemnDetails.value = Pair(VIEW_POKEMON_DETAILS, null);
                    }
                }else if(response.code() == 401){
                    viewFlipperPokemnDetails.value = Pair(VIEW_POKEMON_DETAILS_ERROR, R.string.pokeapi_erro_401);
                }else if(response.code() == 400){
                    viewFlipperPokemnDetails.value = Pair(VIEW_POKEMON_DETAILS_ERROR, R.string.pokeapi_erro_400);
                }
            }

            override fun onFailure(call: Call<PokemonBodyDetailsResponse>, t: Throwable) {
                viewFlipperPokemnDetails.value = Pair(VIEW_POKEMON_DETAILS_ERROR, R.string.pokeapi_erro_400);
            }
        })
    }

    companion object{
        private const val VIEW_POKEMON_DETAILS = 1;
        private const val VIEW_POKEMON_DETAILS_ERROR = 2;
    }

}