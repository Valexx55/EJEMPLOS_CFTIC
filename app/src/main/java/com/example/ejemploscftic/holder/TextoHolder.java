package com.example.ejemploscftic.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ejemploscftic.R;

public class TextoHolder extends RecyclerView.ViewHolder {

    private TextView textView;


    public TextoHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textprueba);
    }

    public void setTextoPrueba (String str_prueba)
    {
        this.textView.setText(str_prueba);
    }


}
