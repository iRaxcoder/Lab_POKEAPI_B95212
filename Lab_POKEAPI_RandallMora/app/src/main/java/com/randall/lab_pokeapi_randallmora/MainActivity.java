package com.randall.lab_pokeapi_randallmora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.randall.lab_pokeapi_randallmora.Adapter.PokemonAdapter;
import com.randall.lab_pokeapi_randallmora.model.Pokemon;
import com.randall.lab_pokeapi_randallmora.Response.PokemonResponse;
import com.randall.lab_pokeapi_randallmora.Services.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private int offset;
    private boolean datosListos;
    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //ponerle el adaptador al reciclador
        this.pokemonAdapter = new PokemonAdapter(this);
        this.recyclerView.setAdapter(pokemonAdapter);
        this.recyclerView.setHasFixedSize(true);
        //usar layout manager para poder facilitar el scroll y manejarlo
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        this.scrollear(layoutManager);
        //usar retrofit para obtener la fuente principal de los datos (api)
        this.initRetrofit();
        datosListos = true;
        offset = 0;
        cargarLista(offset);
    }

    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void scrollear(final GridLayoutManager layoutManager){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int itemsVisibles = layoutManager.getChildCount();
                    int totalItems = layoutManager.getItemCount();
                    int itemsScrolleados = layoutManager.findFirstVisibleItemPosition();

                    if (datosListos) {
                        if ((itemsVisibles + itemsScrolleados) >= totalItems) {

                            offset += 20;
                            datosListos = false;
                            cargarLista(offset);
                        }
                    }
                }
            }
        });
    }

    private void cargarLista(int offset) {

        PokeapiService service = retrofit.create(PokeapiService.class);
        //se realiza el get correspondiente
        Call<PokemonResponse> pokemonRespuestaCall = service.obtenerListaPokemon(20, offset);

        //se maneja la respuesta que viene del API
        pokemonRespuestaCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                datosListos = true;
                //si la solicitud tiene exito entonces se actualiza el recycler en resumidas cuentas (dataset)
                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonResponse.getResults();
                    pokemonAdapter.actualizarLista(listaPokemon);
                } else {
                    Log.e("ERROR", " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                datosListos = true;
                Log.e("ERROR", " onFailure: " + t.getMessage());
            }
        });
    }

}