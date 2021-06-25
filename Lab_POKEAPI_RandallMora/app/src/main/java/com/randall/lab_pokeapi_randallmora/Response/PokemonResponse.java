package com.randall.lab_pokeapi_randallmora.Response;

import com.randall.lab_pokeapi_randallmora.model.Pokemon;

import java.util.ArrayList;

public class PokemonResponse {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

}
