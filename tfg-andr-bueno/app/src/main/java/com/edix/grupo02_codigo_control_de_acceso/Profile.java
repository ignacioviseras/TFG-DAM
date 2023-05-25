package com.edix.grupo02_codigo_control_de_acceso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile extends AppCompatActivity {
    String correo, mEmail;
    TextView cambiarContraseña, textCorreo, textNombre, textCumple;
    Button bajaUser;

    List<String> listaDatosUser = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        textNombre = findViewById(R.id.NombreUsuario);
        textCumple = findViewById(R.id.fechaNac);
        textCorreo = findViewById(R.id.CorreoProfile);
        mEmail = mAuth.getCurrentUser().getEmail();

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

        bajaUser = findViewById(R.id.botonBaja);
        bajaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bajaUser();
                finishAffinity(); // Cierra la aplicación y todas las actividades relacionadas

            }

        });

    }

    private void cambiarContraseña(String correo){
        //por si se usan letras especiales tenemos que concretar el idioma
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){//si se envio el correo para resetear contraseña
                    Toast.makeText(Profile.this, "Se envio un correo para cambiar la contraseña", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Profile.this, "No se pudo enviar el correo de contraseña", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void datosUser() {
        db.collection("Users")
                .whereEqualTo("email", mEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null) {
                                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                                    String nombre = document.getString("nombre");
                                    String correo = document.getString("email");
                                    String cumple = document.getString("cumple");

                                    textNombre.setText(nombre);
                                    textCumple.setText(cumple);
                                    textCorreo.setText(correo);
                                }
                            }
                        } else {
                            // Manejo de errores
                        }
                    }
                });

    }


    public void bajaUser(){

     FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                            mAuth.signOut();
                            Toast.makeText(Profile.this, "Usuario borrado", Toast.LENGTH_LONG).show();
                            //finishAffinity(); // Close the application and all related activities
                            Intent intent = new Intent(Profile.this, Login.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}