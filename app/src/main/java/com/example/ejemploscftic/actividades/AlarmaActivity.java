package com.example.ejemploscftic.actividades;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.receiver.AlarmaReceiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmaActivity extends AppCompatActivity {


    private void programarAlarma ()
    {
        Calendar calendar_actual = Calendar.getInstance();

        long tiempo = calendar_actual.getTimeInMillis() + 6000; //en 1 min, 60 mil ms, saltará la alarma

        Intent intentAlarm = new Intent(this, AlarmaReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(this, 55, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, tiempo, pi);//TIempo, No es el tiempo que falta. Es el tiempo expresado en milisegundos equivalente a la fecha y hora del omento en que se quiere ejecutar


        SimpleDateFormat dateformatter = new SimpleDateFormat("E dd/MM/yyyy 'a las' hh:mm:ss");

        String mensaje = "Alarma programada para "+ dateformatter.format(tiempo);
        Log.d(getClass().getCanonicalName(), mensaje);


        Toast.makeText(this,mensaje, Toast.LENGTH_LONG ).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);
        programarAlarma();

    }
}
