package com.example.ejemploscftic.adapter;


import com.example.ejemploscftic.actividades.TabLayoutActivity;
import com.example.ejemploscftic.fragmentos.ScreenFragment1;
import com.example.ejemploscftic.fragmentos.ScreenFragment2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterPropio extends FragmentStatePagerAdapter {

    //Simplemente llamo al contrstructor del padre
    public PagerAdapterPropio(FragmentManager fm) {
        super(fm);
    }

    //De forma similar al GETVIEW, este adpater también devuelve una vista
    //pero en este caso, es un Fragment! (y se llama getItem)
    @Override
    public Fragment getItem(int posicion) {

        Fragment fragment = null;

        switch (posicion)
        {
            case 0: fragment = new ScreenFragment1(); break;
            default: fragment = new ScreenFragment2();
        }

        return  fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    //sobrrescribo este método para que se pinte el tab como parte
    //del fragtment
    @Override
    public CharSequence getPageTitle(int position) {
        return TabLayoutActivity.getTitulo(position);
    }

}
