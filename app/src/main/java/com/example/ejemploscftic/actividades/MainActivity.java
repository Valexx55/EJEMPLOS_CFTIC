package com.example.ejemploscftic.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.actividades.ItunesActivity;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, BuenosDiasActivity.class));
        //startActivity(new Intent(this, LoginTILActivity.class));
        //startActivity(new Intent (this, ItunesActivity.class));
        //startActivity(new Intent (this, RedDisponibleActivity.class));
        //startActivity(new Intent (this, MenuLateralActivity.class));
        //startActivity(new Intent (this, DescargaActivity.class));
        //startActivity(new Intent (this, VerFotosActivity.class));
        //startActivity(new Intent (this, AlarmaActivity.class));
        //startActivity(new Intent (this, BaseDatosActivity.class));
        startActivity(new Intent (this, TabLayoutActivity.class));

    }
}
