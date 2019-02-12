package com.example.ejemploscftic.actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.dao.BaseDatosCochesPersona;
import com.example.ejemploscftic.dto.Coche;
import com.example.ejemploscftic.dto.Persona;

import java.util.List;

public class BaseDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);

        //creo el objeto de la base de datos
        BaseDatosCochesPersona baseDatosCochesPersona = new BaseDatosCochesPersona(this, "MiDB", null, 1);

        Persona persona1 = new Persona(1, "Juan");
        Persona persona2 = new Persona(2, "Conchi");
        Persona persona3 = new Persona(3, "Manolo");

        //inserto las personas
        baseDatosCochesPersona.insertarPersona(persona1);
        baseDatosCochesPersona.insertarPersona(persona2);
        baseDatosCochesPersona.insertarPersona(persona3);

        Coche coche1 = new Coche("Ferrari", persona2);
        Coche coche2 = new Coche("Renault", persona2);
        Coche coche3 = new Coche("Fiat", persona1);

        //inserto los coches
        baseDatosCochesPersona.insertarCoche(coche1);
        baseDatosCochesPersona.insertarCoche(coche2);
        baseDatosCochesPersona.insertarCoche(coche3);

        //consulto los coches de la persona
        List<Coche> listacoches = baseDatosCochesPersona.buscarCochesPersona(persona2);

        Log.d("MIAPP", "Los coches de " + persona2.getNombre() + " son :");
        for (Coche coche: listacoches)
        {
            Log.d("MIAPP", coche.getModelo());
        }

    }
}
