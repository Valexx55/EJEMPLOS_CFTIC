package com.example.ejemploscftic.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.adapter.AdapterTextoHolder;

import java.util.ArrayList;
import java.util.List;

public class ScreenFragment1 extends Fragment {



    private AdapterTextoHolder adapterTextoHolder;
    private RecyclerView recyclerView;

    public ScreenFragment1() {
        super();
    }




    //Simplemente, inflo en contenido del fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_deslizante1, container, false);

        this.recyclerView = rootView.findViewById(R.id.recviewfragmetn);
        List<String> lista_palabras = new ArrayList<String>();
        lista_palabras.add("Palabra 1");
        lista_palabras.add("Palabra 2");
        lista_palabras.add("Palabra 3");
        lista_palabras.add("Palabra 4");
        lista_palabras.add("Palabra 5");
        lista_palabras.add("Palabra 6");
        this.adapterTextoHolder = new AdapterTextoHolder(lista_palabras);

        this.recyclerView.setAdapter(adapterTextoHolder);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));

        return rootView;
    }

}
