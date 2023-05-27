package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {
    String correo, mEmail;
    TextView cambiarContraseña, textCorreo, textNombre, textCumple;
    List<String> listaDatosUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();


        textNombre = findViewById(R.id.NombreUsuario);
        textCumple = findViewById(R.id.fechaNac);
        textCorreo = findViewById(R.id.CorreoProfile);

        datosUser();
        ImageButton botonHome = findViewById(R.id.botonHome);
        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSearch = findViewById(R.id.botonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, ScannerQR.class);
                startActivity(intent);
            }
        });

        ImageButton botonProfile = findViewById(R.id.botonProfile);
        botonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Profile.class);
                startActivity(intent);
            }
        });


        cambiarContraseña = findViewById(R.id.cambiarContraseña);
        cambiarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = textCorreo.getText().toString();
                if(!correo.isEmpty()){
                    cambiarContraseña(correo);
                }else{
                    Toast.makeText(Profile.this, "Error al recuperar el correo", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void cambiarContraseña(String correo){

    }

    public void datosUser() {

    }
}