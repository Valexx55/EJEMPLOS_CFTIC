package com.example.ejemploscftic.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vale on 25/08/17.
 */

public class BitMapUtil {



    private static final String SUFIJO_FOTO = ".jpg";
    private static final String PREFIJO_FOTO = "MY_PIC";
    //public static final String RUTA_DIR_FOTOS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath()+"/Camera";
    public static final String RUTA_DIR_FOTOS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    //public static final String RUTA_DIR_FOTOS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/BDIASBEBE";



    public static boolean crearDirectorioPics ()
    {
        boolean creado = false;


        try
        {
            String ruta_fotos = RUTA_DIR_FOTOS; //ruta donde se descargan, eliminan o reproducen
            File directorio_general = new File(ruta_fotos);

            creado = directorio_general.mkdirs();
            if (creado)
                Log.d("MENSAJE", "Directorio creado");
            else
                Log.d("MENSAJE", "Directorio NO creado");

        } catch (Exception ex)
        {
            Log.e("MENSAJE", ex.getMessage());
        }

        return  creado;

    }


    private static String crearNombreFichero ()
    {
        String ruta_captura_foto, momento_actual, nombre_fichero = null;
        File f = null;

            momento_actual = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); //así nos garantizamos emplear un sufijo aleatorio: el nombre del archivo de la imagen incluirá el momento exacto
            nombre_fichero = PREFIJO_FOTO + momento_actual + SUFIJO_FOTO;
            ruta_captura_foto = RUTA_DIR_FOTOS+"/"+nombre_fichero;

            Log.d("MENSAJE", "RUTA FOTO = " + ruta_captura_foto);


        return ruta_captura_foto;
    }


    private static boolean quedaEspacio (int bytes_foto)
    {
        /*boolean hay_espacio = false;

            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            int blockSize = stat.getBlockSize();
            int availableBlocks = stat.getAvailableBlocks();
            hay_espacio = (bytes_foto<= (blockSize*availableBlocks));


        return hay_espacio;*/
        return true;

    }

    /**
     * Este método recibo una imagen en base64 y la almacena en la memoria
     * externa del teléfono, devolviendo la URI en que se guarda
     *
     * @param bmbase64
     * @return
     */
    public static Uri crearFicheroFromBitMap (String bmbase64) {
        //TODO considerar excepción por falta de memoria

        Uri uri_dev = null;

        try{
            Bitmap bp = decodeBitMap64(bmbase64);
            if (quedaEspacio(bp.getByteCount()))
            {
                File f = new File(crearNombreFichero());
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(Base64.decode(bmbase64, Base64.NO_WRAP));
                fos.close();
                uri_dev = Uri.fromFile(f);
                Log.d("MENSAJE", uri_dev.toString());
            } else {
                Log.d("MENSAJE", "NO HAY ESPACIO PARA GUARDAR LA FOTO");
            }


        }catch (Exception e)
        {
            Log.e("ERROR", "AL CREAR EL FICHERO DESDE EL BITMAP base 64", e);
        }


        return uri_dev;
    }

    public static Bitmap decodeBitMap64 (String bmbase64)
    {
        Bitmap img = null;

            byte[] decodedString = Base64.decode(bmbase64, Base64.DEFAULT);
            img = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            //img.getByteCount();

        return img;
    }
}
