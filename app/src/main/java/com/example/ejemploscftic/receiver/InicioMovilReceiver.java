package com.example.ejemploscftic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ejemploscftic.actividades.NotificaMensajeBuenosDias;


public class InicioMovilReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("MENSAJE", "El movil se ha reiniciado");

        NotificaMensajeBuenosDias.lanzarNotificiacion(context);

    }
}
