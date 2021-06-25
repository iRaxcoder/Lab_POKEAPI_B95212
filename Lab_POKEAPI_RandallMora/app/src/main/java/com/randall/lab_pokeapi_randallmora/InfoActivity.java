package com.randall.lab_pokeapi_randallmora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class InfoActivity extends AppCompatActivity {
private TextView tvText;
private ImageView ivImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvText = (TextView)findViewById(R.id.etDescription);
        ivImagen=(ImageView)findViewById(R.id.ivImagen) ;
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String imagen="";
        if(b!=null)
        {
            String j =(String) b.get("descripcion");
            imagen=(String) b.get("imagen");
            tvText.setText(j);
        }
        Glide.with(getApplicationContext())
                .load(imagen)

                .into(ivImagen);
    }
}