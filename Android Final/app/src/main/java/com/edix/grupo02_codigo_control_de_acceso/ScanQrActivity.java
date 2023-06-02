package com.edix.grupo02_codigo_control_de_acceso;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.databinding.ActivityMainBinding;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.helpers.ToastHelper;
import com.edix.grupo02_codigo_control_de_acceso.helpers.AppUtils;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQrActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), this::onActivityResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scanQr();
    }

    public void scanQr() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("Escanee un código de acceso. Si en 15 segundos no se ha detectado ningún QR, volverá a la pantalla principal");
        options.setBeepEnabled(true);
        options.setCameraId(0);
        options.setOrientationLocked(false);
        options.setBeepEnabled(false);
        options.setCaptureActivity(CaptureActivityPortrait.class);
        options.setBarcodeImageEnabled(false);
        // si la cámara no detecta nada lanza un mensaje a los 10 segundos y a los 14 cambia de vista
        new Handler().postDelayed(() -> {
            ToastHelper.warn(getApplicationContext(), "No se ha detectado ningún QR");
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(ScanQrActivity.this, EventsActivity.class);
                startActivity(intent);
            }, 4000);
        }, 10000);
        barcodeLauncher.launch(options);
    }

    private void onActivityResult(ScanIntentResult result) {
        if (result.getContents() == null) {
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show();
        } else {

            try {
                int access_id = AppUtils.decodeAccessId(result.getContents());
                Call<Access> call = ApiAdapter.getApiService().validateAccess(
                        AppUtils.getAuthToken(getApplicationContext()),
                        access_id);
                call.enqueue(new Callback<Access>() {
                    @Override
                    public void onResponse(@NonNull Call<Access> call, @NonNull Response<Access> response) {
                        if (response.isSuccessful()) {
                            ToastHelper.info(getApplicationContext(), "acceso permitido");
                            Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Access> call, @NonNull Throwable t) {
                        ToastHelper.fail(getApplicationContext(), "acceso no permitido");
                    }
                });
            } catch (IllegalArgumentException e) {
                ToastHelper.fail(getApplicationContext(), "acceso no permitido");
            }
        }
    }
}