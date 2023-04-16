package com.example.myapp.pokeapi;
import com.example.myapp.models.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;




import com.example.myapp.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.Query;

public interface PokeaService {
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset")  int offset);
}
