package com.edix.grupo02_codigo_control_de_acceso;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ScannerQR extends AppCompatActivity{
    private Button scanButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_qr);

        scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanQR();
            }
        });

        ImageButton botonHome = findViewById(R.id.botonHome);
        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerQR.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSearch = findViewById(R.id.botonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerQR.this, ScannerQR.class);
                startActivity(intent);
            }
        });

        ImageButton botonProfile = findViewById(R.id.botonProfile);
        botonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerQR.this, Profile.class);
                startActivity(intent);
            }
        });
    }


    public void scanQR() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Encendiendo linterna");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureQR.class);
        launcher.launch(options);

    }

    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(ScannerQR.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });
}

/*
package com.edix.grupo02_codigo_control_de_acceso;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScannerQR extends AppCompatActivity implements View.OnClickListener {
    private Button scanButton, exitButton;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//orientacion vertical de la pantalla

        scanButton = findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);
        exitButton.setVisibility(View.GONE);

        qrScan = new IntentIntegrator(this);

        ImageButton botonHome = findViewById(R.id.botonHome);
        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerQR.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSearch = findViewById(R.id.botonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerQR.this, ScannerQR.class);
                startActivity(intent);
            }
        });

        ImageButton botonProfile = findViewById(R.id.botonProfile);
        botonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScannerQR.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        /*if (view.getId() == R.id.scanButton) {
            qrScan.initiateScan();
            exitButton.setVisibility(View.VISIBLE); //hacemos visible el boton para cerrar la camara
        } else if (view.getId() == R.id.exitButton) {
            qrScan.stopScan();//si se pulsa el boton se cierra la camara
            finish();//para q no podamos acceder a la evtana de la camara
        }

        if (view.getId() == R.id.scanButton) {
                qrScan.initiateScan();
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                exitButton.setVisibility(View.VISIBLE);
                } else if (view.getId() == R.id.exitButton) {
                // Stop the scanning process
                finish(); // Finish the activity
                }
                }

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
        if (result.getContents() == null) {
        Toast.makeText(this, "Escaneo cancelado", Toast.LENGTH_LONG).show();
        } else {
        String qrContent = result.getContents();
        // Aquí puedes realizar acciones con el contenido del código QR escaneado
        Toast.makeText(this, "Contenido del QR: " + qrContent, Toast.LENGTH_LONG).show();
        }
        exitButton.setVisibility(View.GONE);
        }
        }
        }


 */