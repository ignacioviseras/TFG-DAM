package com.edix.grupo02_codigo_control_de_acceso.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.edix.grupo02_codigo_control_de_acceso.R;

public class ToastHelper {
    public final static String INFO = "info";
    public final static String WARN = "warn";
    public final static String FAIL = "fail";


    @SuppressLint("InflateParams")
    public void show(Context context, String message, String severity) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout;
        if (severity.equals(ToastHelper.INFO)) {
            layout = inflater.inflate(R.layout.toast_info, null);
        } else if (severity.equals(ToastHelper.WARN)) {
            layout = inflater.inflate(R.layout.toast_warning, null);
        } else {
            layout = inflater.inflate(R.layout.toast_error, null);
        }

        TextView textView = layout.findViewById(R.id.toast_message);
        textView.setText(message);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
    public static void info(Context context, String message){
        new ToastHelper().show(context, message, ToastHelper.INFO);
    }
    public static void warn(Context context, String message){
        new ToastHelper().show(context, message, ToastHelper.WARN);
    }
    public static void fail(Context context, String message){
        new ToastHelper().show(context, message, ToastHelper.FAIL);
    }
}
