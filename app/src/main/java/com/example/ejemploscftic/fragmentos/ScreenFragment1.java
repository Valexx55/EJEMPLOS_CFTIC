package com.example.ejemploscftic.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ejemploscftic.R;

public class ScreenFragment1 extends Fragment {



    public ScreenFragment1() {
        super();
    }


    //Simplemente, inflo en contenido del fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_deslizante1, container, false);

        return rootView;
    }

}
