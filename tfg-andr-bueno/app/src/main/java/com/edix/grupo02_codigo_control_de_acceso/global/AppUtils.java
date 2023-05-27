package com.edix.grupo02_codigo_control_de_acceso.global;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*   Esta clase es una librería de utiles para la aplicacion
* */
public class AppUtils {
    /*
    *   chequea la valided de un eamil.
    * */
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidMail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /*
    *   Estas dos funciones se utilizan para guardar y recuperar variables de la aplicación de tipo string
    * */
    private static final String PREF_NAME = "MyAppVariables";

    public static void setMyVariable(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getMyVariable(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }

    public static String getAuthToken(Context context){
        return "Bearer "+ AppUtils.getMyVariable(context, "_token");
    }
}
