package com.example.ejemploscftic.actividades;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.ejemploscftic.R;
import com.example.ejemploscftic.adapter.PagerAdapterPropio;

public class TabLayoutActivity extends AppCompatActivity {


    /**
     * ViewPager se encarga de la animación y permite la transción entre elmementos (swipe)
     */
    private ViewPager viewPager;

    /**
     * El PagerAdapter, suministra las vistas al ViewPager
     */
    private PagerAdapter pagerAdapter;


    //Este será el título visible por cada fragment
    private static String[] titulo_tab = {"OPTION 1" , "OPTION 2"};


    public static String getTitulo (int posicion)
    {
        return titulo_tab[posicion];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);


        // Obtengo la referencia al viewPager y le asigno su adapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapterPropio(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        //Obtengo referencia al tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablay);
        //creo dinámicamente sus elementos. Sólo 2
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        //Importantísimo: lo asocio al viewpager y automáticamente
        //cuando seleccione un tab, será como pedírselo al viewpager

        tabLayout.setupWithViewPager(viewPager);


    }

    //Sobreescribo este método para darle un comportamiento especial al hecho de que
    //el usuario presione el botón hacia atrás
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            //Si estoy en el primer elemento, salgo de la app, aplicando el comportamiento por defecto
            super.onBackPressed();
        } else {
            // Si no, cambio la pantalla
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}
