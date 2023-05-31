package com.edix.grupo02_codigo_control_de_acceso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AppToast {
    final public static String INFO = "info";
    final public static String WARN = "warn";
    final public static String FAIL = "fail";

    @SuppressLint("InflateParams")
    public static void show(Context context, String message, String severity) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout;
        if (severity.equals(AppToast.INFO)) {
            layout = inflater.inflate(R.layout.toast_info, null);
        } else if (severity.equals(AppToast.WARN)) {
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

       // Handler handler = new Handler();
        // Ocultar el Toast después de la duración personalizada
       // handler.postDelayed(toast::cancel, duration);
    }
}
