package com.example.myapp.connection;

import com.example.myapp.models.PokemonRespuesta;
import com.example.myapp.pokeapi.PokeaService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    private static Retrofit retrofit;

    private RetrofitConnection(){
        retrofit=new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static Retrofit getRetrofit(){
        if(Objects.isNull(retrofit)) new RetrofitConnection();
        return retrofit;
    }

    private static PokeaService getPokeaService(){
        return getRetrofit().create(PokeaService.class);
    }

    public static Call<PokemonRespuesta> getPokemonRespuestaCall(int limit, int offset){
        return getPokeaService().obtenerListaPokemon(20,offset);
    }

}
