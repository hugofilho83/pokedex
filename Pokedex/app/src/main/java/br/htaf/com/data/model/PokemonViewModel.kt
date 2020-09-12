package br.htaf.com.data.model

import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.htaf.com.data.PokeApiService
import br.htaf.com.data.response.PokemonsBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import br.htaf.com.R


class PokemonViewModel : ViewModel() {

    val pokemonsLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData();
    val viewFlipperLiveData : MutableLiveData<Pair<Int, Int?>> = MutableLiveData();

    fun GetPokemons()
    {
        //pokemonsLiveData.value = getListFakePokemon();

        PokeApiService.iniRetrofit().getPokemonsResponse(0, 1050).enqueue(object :
            Callback<PokemonsBodyResponse> {
            override fun onResponse(
                call: Call<PokemonsBodyResponse>,
                response: Response<PokemonsBodyResponse>
            ) {
                if (response.isSuccessful) {
                    val pokemons: MutableList<Pokemon> = mutableListOf();

                    response.body()?.let { pokemonsbodyResponse ->

                        for (result in pokemonsbodyResponse.pokemonResults) {
                            val pokemon = result.getPokemonModel();
                            pokemons.add(pokemon);
                        }
                    }

                    pokemonsLiveData.value = pokemons;
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_POKEMON, null);

                }else if(response.code() == 401){
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.pokeapi_erro_401);
                }else if(response.code() == 400){
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.pokeapi_erro_400);
                }
            }

            override fun onFailure(call: Call<PokemonsBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.pokeapi_erro_500);
            }
        })
    }

    companion object{
        private const val VIEW_FLIPPER_POKEMON = 1;
        private const val VIEW_FLIPPER_ERROR = 2;
    }
}