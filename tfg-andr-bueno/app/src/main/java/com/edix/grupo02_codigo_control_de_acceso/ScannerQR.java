package com.edix.grupo02_codigo_control_de_acceso;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ScannerQR extends AppCompatActivity{
    private Button scanButton;
    private String qrResult, validacion;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_qr);
        getSupportActionBar().hide();
        db = FirebaseFirestore.getInstance();

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
        options.setBeepEnabled(true);//sonido al scannear el qr
        options.setOrientationLocked(true);//orientacion de la pantalla
        options.setCaptureActivity(CaptureQR.class);//abrimos la pantalla Capture
        launcher.launch(options);//arrancamos la accion de scaneo
    }

    /*ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
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
    });*/

    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            qrResult = result.getContents(); // texto del qr leido

            if (qrResult != null && !qrResult.isEmpty()) {
                Toast.makeText(ScannerQR.this, qrResult, Toast.LENGTH_SHORT).show();
                //System.out.println("El resultado del QR es: " + qrResult);
            }

            //validamos que el qr exista en la bd
            CollectionReference registrosRef = db.collection("Registros");

            registrosRef.whereEqualTo("nombreRegistro", qrResult)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot snapshot = task.getResult();
                            for (DocumentSnapshot document : snapshot.getDocuments()) {
                                validacion = document.getString("nombreRegistro");
                                if (validacion.equals(qrResult))
                                    ventana(validacion);
                            }

                        } else {//error al obtener los datos
                            ventana("QR no valido");
                            Exception exception = task.getException();
                            Log.w("error firestore", exception);
                            // Manejar el error...
                        }
                    });


        }
    });

    public void ventana(String validacion) {
        //ventana emergente para decirel contenido
        AlertDialog.Builder builder = new AlertDialog.Builder(ScannerQR.this);
        builder.setTitle("Result");
        //builder.setMessage(result.getContents());
        builder.setMessage(validacion);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }


    public String verificarNombreRegistro(String valor) {
        //usamos CollectionReference para
        CollectionReference registrosRef = db.collection("Registros");

        registrosRef.whereEqualTo("nombreRegistro", valor)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        for (DocumentSnapshot document : snapshot.getDocuments()) {
                            String nombreRegistro = document.getString("nombreRegistro");
                            // Hacer algo con el nombreRegistro...

                        }
                    } else {
                        // Ocurrió un error al obtener los datos de Firestore
                        Exception exception = task.getException();
                        Log.w("error firestore", exception);
                        // Manejar el error...
                    }
                });

        // Si llegamos hasta aquí, retornamos un valor predeterminado en caso de que haya algún problema
        return "Ocurrio un problema";
    }


}