package com.example.ejemploscftic.remote;

import android.content.Context;
import android.os.AsyncTask;

import android.util.Log;


import com.example.ejemploscftic.actividades.ItunesActivity;
import com.example.ejemploscftic.dto.ResultadoCanciones;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QueryItunes extends AsyncTask<String, Void, ResultadoCanciones> {


    private Context context;
    private final static String URI_ITUNES =
            "https://itunes.apple.com/search/?media=music&term=";
    //&attribute=artistTerm albumTerm songTerm

    public QueryItunes(Context c)
    {
        this.context = c;
    }


    @Override
    protected ResultadoCanciones doInBackground(String... canciones) {
        ResultadoCanciones rc = null;

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStreamReader is = null;

        try {
            url = new URL(URI_ITUNES + canciones[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            String linea = null;

            Gson gson = new Gson();
            //respuesta = httpConn.getResponseCode();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = new InputStreamReader(httpURLConnection.getInputStream());
                rc = gson.fromJson(is, ResultadoCanciones.class);

            }


        } catch (Exception e) {
            Log.e("MIAPP", "Errro al com itunes", e);
        } finally
            {
                try
                    {
                        is.close();
                    } catch (IOException e) {
                    Log.e("MIAPP", "Error al liberar recursos", e);
                }
                httpURLConnection.disconnect();
        }

        return rc;
    }

    @Override
    protected void onPostExecute(ResultadoCanciones s) {

        super.onPostExecute(s);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(s);
        System.out.println(json);
        Log.d("MIAPP", "JSON CANCIONES = " + json);

        ItunesActivity ia = (ItunesActivity)this.context;
        ia.actualizarLista(s);



    }
}
