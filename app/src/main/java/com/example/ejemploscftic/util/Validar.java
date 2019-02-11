package com.example.ejemploscftic.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validar {


    private static final String PATRON_MAIL = "\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,4})+";
    private static final String PATRON_NOMBRE = "\\w{6,45}";
    private static final String PATRON_PWD = "\\w{6,45}";
    private static final String PATRON_NICK = "\\w{6,20}";


    public static boolean nickValido (String nick)
    {
        boolean bdev = false;

            Pattern p = Pattern.compile(PATRON_NICK);
            Matcher m = p.matcher(nick);
            bdev = m.matches();

        return bdev;
    }

    public static boolean emailValido (String email)
    {
        boolean bdev = false;

            Pattern p = Pattern.compile(PATRON_MAIL);
            Matcher m = p.matcher(email);
            bdev = m.matches();

        return bdev;
    }

    public static boolean nombreUsuarioValido (String nombre_usuario)
    {
        boolean bdev = false;

            Pattern p = Pattern.compile(PATRON_NOMBRE);
            Matcher m = p.matcher(nombre_usuario);
            bdev = m.matches();

        return bdev;
    }

    public static boolean contrasenaValida (String p1)
    {
        boolean bdev = false;

            Pattern p = Pattern.compile(PATRON_PWD);
            Matcher m = p.matcher(p1);
            bdev = m.matches();

        return bdev;
    }

    public static boolean contrasenasValida (String p1, String p2)
    {
        boolean bdev = false;

                bdev = p1.equals(p2);

        return bdev;
    }


}
