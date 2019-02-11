package com.example.ejemploscftic.actividades;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.adapter.PagerAdapterCarrusel;
import com.example.ejemploscftic.controller.AnimacionTransformacion1;
import com.example.ejemploscftic.util.BitMapUtil;

import java.io.File;

public class VerFotosActivity extends AppCompatActivity {



    private ViewPager viewPager;
    PagerAdapterCarrusel pagerAdapter;

    /**
     * apaño para limitar el número de fotos a mostrar y evitar un desbordamiento de memoria
     * @param lista_ficheros
     * @return
     */
    private int maxTamanio (File [] lista_ficheros)
    {
        int max = 10;
        int tamanio = -1;

                tamanio = lista_ficheros.length;
                max = (tamanio>5) ? 5 : tamanio;

        return max;
    }

    private String[] obtenerFicheros ()
    {
        String [] lista_nombre_ficheros = null;

        File dir_fotos = new File (BitMapUtil.RUTA_DIR_FOTOS);
        int tamanio = maxTamanio(dir_fotos.listFiles());
        File[] lista_ficheros = dir_fotos.listFiles();
        lista_nombre_ficheros = new String [tamanio];

        String extension_aux;

        int j = 0;
        int i = 0;
            while (j<tamanio)
        {
            String ext = android.webkit.MimeTypeMap.getFileExtensionFromUrl(lista_ficheros[i].getName());
            if (ext.equals("jpg")||ext.equals("jpeg"))
            {
                lista_nombre_ficheros[j] = lista_ficheros[i].getAbsolutePath();
                j++;
            }
            i++;

        }

        return lista_nombre_ficheros;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        String[] lista_nombre_ficheros = obtenerFicheros();
        if (lista_nombre_ficheros == null || lista_nombre_ficheros.length==0)
        {
            Toast.makeText(this, "NO HAY FOTOS EN LA CARPETA DE RECIBIDOS", Toast.LENGTH_LONG).show();
            finish();
            Log.d("MENSAJE", "Sin fotos que mostrar");
        }
        else {
            viewPager = (ViewPager) findViewById(R.id.pager);

            pagerAdapter = new PagerAdapterCarrusel(getSupportFragmentManager());
            pagerAdapter.setM_lista_nombre_ficheros(lista_nombre_ficheros);
            viewPager.setAdapter(pagerAdapter);
            viewPager.setPageTransformer(true, new AnimacionTransformacion1());
            Log.d("MENSAJE", "Pager Adapter Asociado");
        }
        ocultarStatusBar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_fotos);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 800);

    }



    private void gestionarOpcionBorrado ()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("¿De verdad desea eliminar la imagen visible?").setTitle("AVISO");
        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
                String f_actual = PagerAdapterCarrusel.getFotoActual();//null;//FragmentoFoto.getFoto_actual();
                File f = new File (f_actual);
                //borrar fichero
                f.delete();
                //set nueva coleccion en pager adapter
                String[] lista_nombre_ficheros = obtenerFicheros();
                pagerAdapter = new PagerAdapterCarrusel(getSupportFragmentManager());
                pagerAdapter.setM_lista_nombre_ficheros(lista_nombre_ficheros);
                viewPager.setAdapter(pagerAdapter);
            }
        });
        builder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void mostrarLayout(View vista)
    {
        Log.d("MENSAJE", vista.getClass().getCanonicalName());

        if (vista instanceof ViewGroup)
        {
            ViewGroup viewGroup = (ViewGroup) vista;

            for (int i = 0; i<viewGroup.getChildCount(); i++)
            {
                View vistahija = viewGroup.getChildAt(i);
                mostrarLayout(vistahija);

            }
        }
    }
    private void gestionarOpcionCompartir ()
    {

        Intent shareIntent = new Intent ();
        shareIntent.setAction(Intent.ACTION_SEND);

        Uri uri = Uri.fromFile(new File(PagerAdapterCarrusel.getFotoActual()));

        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, "ENVIAR FOTO POR ..."));



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //obtener elemento en curso
        switch (item.getItemId())
        {
            case 1: gestionarOpcionCompartir();
                break;

            case 2: gestionarOpcionBorrado();
                break;
        }





        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem mib = menu.add(Menu.NONE, 2, 2, "BORRAR");
        mib.setIcon(R.drawable.ic_delete_white_24dp);
        mib.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS|MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        MenuItem mi = menu.add(Menu.NONE, 1, 1, "COMPARTIR");
        mi.setIcon(R.drawable.ic_screen_share_white_24dp);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS|MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }

    private void ocultarStatusBar ()
    {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Override
    public void onBackPressed() {

        /**
         * ejemplo de como se puede cambiar el adapter y el númeor de imágenes dinámicamente
         */
        if (viewPager.getCurrentItem() == 0) {
            //Si estoy en el primer elemento, salgo de la app, aplicando el comportamiento por defecto
            super.onBackPressed();
        } else {
            // Si no, cambio la pantalla
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        ocultarStatusBar();
    }
}
