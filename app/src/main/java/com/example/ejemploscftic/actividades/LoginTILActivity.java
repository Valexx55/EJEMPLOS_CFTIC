package com.example.ejemploscftic.actividades;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejemploscftic.R;
import com.example.ejemploscftic.dto.UsrDto;
import com.example.ejemploscftic.remote.ValidarLoginServidor;
import com.example.ejemploscftic.util.Constantes;
import com.example.ejemploscftic.util.Validar;

public class LoginTILActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_til);

    }



    private void borrarMensajesError (TextInputLayout... tils)
    {
        for (TextInputLayout til : tils)
        {
            til.setErrorEnabled(false);//así borramos los errores sin que deje el espacio. al poner
        }
    }

    private void borrarCampos (EditText ... etc )
    {
        for (EditText caja:etc)
        {
            caja.setText("");
        }
    }

    public void borrarFormulario (View v)
    {
        borrarCampos((EditText)findViewById(R.id.cajamaillogin),
                (EditText)findViewById(R.id.cajapwdlogin));

        borrarMensajesError ((TextInputLayout) findViewById(R.id.tilcajamaillogin),
                (TextInputLayout) findViewById(R.id.tilcajapwdlogin));

    }



    private void informarErrorValidacion()
    {
        String mensaje_error_envio = "ERROR AL VALIDAR EN EL SERVIDOR";//getResources().getString(R.string.mensajeerrorvalidacionlogin);
        Toast noti = Toast.makeText(this, mensaje_error_envio, Toast.LENGTH_LONG);
        noti.show();
    }



    private void procesarValidacionServidor (UsrDto usrDto)
    {
        boolean resultado = false;

        try {
            ValidarLoginServidor vls = new ValidarLoginServidor(this);
            resultado = vls.execute(usrDto).get();
        } catch (Exception e) {
            Log.e(Constantes.TAG_APP, "ERROR al validar el login del usuario", e);
        }

        if (resultado)
        {
           Log.d(Constantes.TAG_APP, "La validación fue bien");
        } else
        {
            informarErrorValidacion();
        }
    }

    public void aceptarFormulario (View v)
    {
        String mail = null;
        String contrasenia = null;

        mail = ((EditText)findViewById(R.id.cajamaillogin)).getText().toString();
        contrasenia = ((EditText)findViewById(R.id.cajapwdlogin)).getText().toString();

        if (Validar.emailValido(mail))
        {
            UsrDto usrDto = new UsrDto(mail, contrasenia);
            boolean ok_credenciales = true;//mejora de autenticar localmente // AutenticarLocal.credencialesCorrectas(usrDto, this) || this.nuevo_dispositivo;
            if (ok_credenciales)
            {
                Log.d(Constantes.TAG_APP, "Credenciales correctas locales " + usrDto.toString());
                borrarMensajesError ((TextInputLayout) findViewById(R.id.tilcajamaillogin), (TextInputLayout) findViewById(R.id.tilcajapwdlogin));// estaba aml y se corrije y se envía
                procesarValidacionServidor (usrDto);
            } else
            {
                informarErrorValidacion();
            }
        } else {

            String mensaje_error = "ERROR EN EL FORMULARIO";//getResources().getString(R.string.error_crear_cuenta_formulario);
            Toast mensaje_toast = Toast.makeText(this, mensaje_error, Toast.LENGTH_LONG );
            mensaje_toast.show();
        }

    }
}
