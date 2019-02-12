package com.example.ejemploscftic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.ejemploscftic.actividades.AlarmaActivity;

public class AlarmaReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            Log.d(getClass().getCanonicalName(), "Alarma RECIBIDA");
            context.startActivity(new Intent(context, AlarmaActivity.class));


    }
}
