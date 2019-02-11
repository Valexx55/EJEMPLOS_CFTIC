package com.example.ejemploscftic.remote;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ejemploscftic.dto.UsrDto;
import com.example.ejemploscftic.util.Constantes;



public class ValidarLoginServidor extends AsyncTask<UsrDto, Void, Boolean> {


    private Context context;
    private ProgressDialog pd;

    public ValidarLoginServidor(Context context)
    {
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();



        pd = new ProgressDialog(context);
        pd.setMessage("Transfiriendo al servidor ...");
        pd.setTitle("Subiendo publicación");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.show();
    }



    @Override
    protected Boolean doInBackground(UsrDto... usrDtos) {
        boolean bdev = true;

            Log.d(Constantes.TAG_APP, "Validando en el servidor login a " + usrDtos[0].toString());
            try {
                Thread.sleep(2500);//simulamos la comunicación con el servidor
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pd.dismiss();

        return bdev;
    }
}
