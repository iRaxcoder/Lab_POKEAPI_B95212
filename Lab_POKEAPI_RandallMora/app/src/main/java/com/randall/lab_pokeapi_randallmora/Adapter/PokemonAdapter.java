package com.randall.lab_pokeapi_randallmora.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import com.randall.lab_pokeapi_randallmora.InfoActivity;
import com.randall.lab_pokeapi_randallmora.R;
import com.randall.lab_pokeapi_randallmora.model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>  {
    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokemonAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png")
                .into(holder.fotoImageView);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void actualizarLista(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView fotoImageView;
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);

            fotoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(v.getContext(),InfoActivity.class);
                    int index=getAdapterPosition();
                    i.putExtra("descripcion", dataset.get(index).getDescription());
                    i.putExtra("imagen","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                            + dataset.get(index).getNumber() + ".png");

                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
