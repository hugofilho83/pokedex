package br.htaf.com.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeApiService{

     const val BASA_URL = "https://pokeapi.co/api/v2/";

     fun iniRetrofit(): IPokeApiService {
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl(BASA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit.create(IPokeApiService::class.java);
    }

   //val service : IPokeApiService = iniRetrofit().create(IPokeApiService::class.java);
}