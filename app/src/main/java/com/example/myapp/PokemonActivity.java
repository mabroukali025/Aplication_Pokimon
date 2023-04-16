package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class PokemonActivity extends AppCompatActivity {
    private Intent intent;
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        this.intent = getIntent();
        this.textView = findViewById(R.id.textName);
        this.imageView = findViewById(R.id.imagePok);

        textView.setText(intent.getStringExtra("name"));

        Picasso.get().load(intent.getStringExtra("imagePok")).into(imageView);
    }
}
