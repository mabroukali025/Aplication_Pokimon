package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;

import com.example.myapp.connection.RetrofitConnection;
import com.example.myapp.models.Pokemon;
import com.example.myapp.models.PokemonRespuesta;
import com.example.myapp.pokeapi.PokeaService;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG="POKEDEX";
    private ArrayList<Pokemon> listaPokemon=new ArrayList<>();
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private int offset;
    private boolean aptoParaCargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){
                    int visibleItemCount=layoutManager.getItemCount();
                    int totalItemCount=layoutManager.getItemCount();
                    int pastVisibleItems=layoutManager.findFirstVisibleItemPosition();
                   if(aptoParaCargar){
                       if(visibleItemCount+pastVisibleItems >= totalItemCount){
                           Log.i(TAG,"Llegamos al final.");
                           aptoParaCargar=false;
                           offset+=20;
                           obtenerDatos(offset);
                       }
                   }
                }
            }


        });

        offset=0;
        aptoParaCargar=true;
        obtenerDatos(offset);

    }

    private void obtenerDatos(int  offset) {
       /* PokeaService service =retrofit.create(PokeaService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall=service.obtenerListaPokemon(20,offset);*/
        RetrofitConnection.getPokemonRespuestaCall(20, offset).enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
               aptoParaCargar=true;
                if(response.isSuccessful()){
                   PokemonRespuesta pokemonRespuesta=response.body();
                   listaPokemon=pokemonRespuesta.getResults();
                   listaPokemonAdapter.adicionarListaPokemon(listaPokemon);
                }else{
                    Log.e(TAG,"onResponse"+ response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar=true;
                Log.e(TAG,"onFailure"+t.getMessage());
            }
        });
    }

}