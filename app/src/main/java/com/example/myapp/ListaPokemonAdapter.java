package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

     private ArrayList<Pokemon> dataset;
     private Context context;
     public ListaPokemonAdapter(Context context){
         this.context=context;
         dataset=new ArrayList<>();
     }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p=dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"+p.getNumber()+".png";

        Picasso.get().load(imageUrl).into(holder.fotoImageView);

        holder.itemView.setOnClickListener((view) -> {
            Log.i("onClick", "click " + String.valueOf(p.getNumber()));

            Intent intent = new Intent(context, PokemonActivity.class);
            intent.putExtra("name", p.getName());
            intent.putExtra("imagePok", imageUrl);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
          dataset.addAll(listaPokemon);
          notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fotoImageView;
        private TextView nombreTextView;
        public ViewHolder(View v) {
            super(v);
            fotoImageView=(ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView=(TextView) itemView.findViewById(R.id.nombreTextView);
        }
    }
}
