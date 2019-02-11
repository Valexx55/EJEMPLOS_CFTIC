package com.example.ejemploscftic.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ejemploscftic.fragmentos.FragmentoFoto;

public class PagerAdapterCarrusel extends FragmentStatePagerAdapter {

    //En el construcutor, recojo el FragmentManager, que intermanete,
    //se encargará de suministrar los fragmentos o trozos pantallas



    private static int visible_actual;

    public static void setVisible_actual(int visible_actual) {
        PagerAdapterCarrusel.visible_actual = visible_actual;
    }

    public static String getFotoActual ()
    {
        return m_lista_nombre_ficheros[visible_actual];
    }

    private static String [] m_lista_nombre_ficheros;

    public void setM_lista_nombre_ficheros(String[] m_lista_nombre_ficheros) {
        this.m_lista_nombre_ficheros = m_lista_nombre_ficheros;
    }

    public PagerAdapterCarrusel(FragmentManager fm) {
        super(fm);


    }


    //Este método es análogo a nuestro getView del Adapter que usábamos con ListView
    //Con la salvedad de que aquí, en vez de vistas, devolveremos fragments
    @Override
    public Fragment getItem(int position) {

        FragmentoFoto fragment = null;
        Bundle bundle = null;

        fragment = new FragmentoFoto();
        bundle = new Bundle();
        //foto_actual = m_lista_nombre_ficheros[position];
        bundle.putString("RUTA_FOTO", m_lista_nombre_ficheros[position]);
        bundle.putInt("CLAVE", position);//al fragment le damos un tag, que valdrá a la postre para la id del la foto en curso
        fragment.setArguments(bundle);


        return  fragment;

    }

    //Este método se usa sólo para saber cuántas pantallas tengo y si el adpter ha llegado al final
    @Override
    public int getCount() {
        return m_lista_nombre_ficheros.length;
    }
}
