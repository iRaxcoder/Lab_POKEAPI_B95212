package com.randall.lab_pokeapi_randallmora.Services;

import com.randall.lab_pokeapi_randallmora.Response.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonResponse> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
