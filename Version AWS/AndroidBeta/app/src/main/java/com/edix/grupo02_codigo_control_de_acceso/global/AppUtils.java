package com.edix.grupo02_codigo_control_de_acceso.global;

import android.content.Context;

import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.entities.Variable;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*   Esta clase es una librería de utiles para la aplicacion
* */
public class AppUtils {
    /*
    *   chequea la valided de un eamil.
    * */
    private static final String EMAIL_REGEX = "^[A-Za-z\\d+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidMail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /*
    *   Estas dos funciones se utilizan para guardar y recuperar variables de la aplicación de tipo string
    * */
    public static void setVariable(Context context, String key, String value) {
        DataBaseUtils.getDBManager(context).variableDao().insert(new Variable(key, value));
    }

    public static String getVariable(Context context, String key) {
        Variable variable = DataBaseUtils.getDBManager(context).variableDao().findByKey(key);
        if(variable == null) return null;
        else return variable.value;
    }

    public static String getAuthToken(Context context){
        String token = AppUtils.getVariable(context, "_token");
        if(token == null){
            return null;
        }else{
            return "Bearer " + token;
        }
    }

    public static boolean isAdmin(Context context){
       // Log.d(">>>>>>>>>>>>>>>>>>>>>>", AppUtils.getVariable(context, "_role"));
        String role = AppUtils.getVariable(context, "_role");
        if(role ==null){
            return false;
        }else{
            return role.equals("ADMIN");
        }
    }

    public static String encodeAccessId(int accessId){
        String encodedInteger = Base64.getEncoder().encodeToString(String.valueOf(accessId).getBytes());
        return encodedInteger;
    }

    public static int decodeAccessId(String encoded){
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        String decodedIntegerString = new String(decodedBytes);
        int decodedInteger = Integer.parseInt(decodedIntegerString);
        return decodedInteger;
    }

}
