package com.example.ejemploscftic.fragmentos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ejemploscftic.R;


public class FragmentoFoto extends Fragment {

    private String foto_actual;
    private int clave;

    public FragmentoFoto() {
        super();
    }



    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        Log.d(getClass().getCanonicalName(), "SetArgumentsInvocado");
        foto_actual = args.getString("RUTA_FOTO");
        clave = args.getInt("CLAVE");
    }

    //Sobreescribo este método para devolver la vista raíz del fragment
    //inflando para ello el layout que representa la vista de dicho fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragmento_foto, container, false);

        try {



            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse("file://"+foto_actual));
            ImageView imageView = rootView.findViewById(R.id.foto_act);
            imageView.setImageBitmap(bitmap);
            imageView.setTag(clave);

        }catch (Exception e)
        {
            Log.e("MENSAJE", e.getMessage());
        }


        return rootView;
    }
}
