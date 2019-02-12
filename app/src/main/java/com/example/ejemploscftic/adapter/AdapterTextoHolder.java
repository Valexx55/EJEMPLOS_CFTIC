package com.example.ejemploscftic.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.holder.TextoHolder;

import java.util.List;

public class AdapterTextoHolder extends RecyclerView.Adapter<TextoHolder> {

    private List<String> lista_palabras;

    public AdapterTextoHolder (List<String> lista_palabras)
    {
        this.lista_palabras = lista_palabras;
    }

    @NonNull
    @Override
    public TextoHolder  onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextoHolder textoHolder = null;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.fila_texto_holder, viewGroup, false);
        textoHolder = new TextoHolder(itemView);

        return textoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TextoHolder textoHolder, int i) {


        String texto_actual = lista_palabras.get(i);
        textoHolder.setTextoPrueba(texto_actual);


    }

    @Override
    public int getItemCount() {
        return lista_palabras.size();
    }
}
