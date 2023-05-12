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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.Nullable;
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
    private EditText TextEmail;
    String correo, mEmail, sNom, sCump, sEma;
    TextView cambiarContraseña, textCorreo, textCNombre, textCumple;
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


        textCNombre = findViewById(R.id.NombreUsuario);
        textCumple = findViewById(R.id.fechaNac);
        textCorreo = findViewById(R.id.CorreoProfile);
        mEmail = mAuth.getCurrentUser().getEmail();

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
                Intent intent = new Intent(Profile.this, Search.class);
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
        db.collection("Users").whereEqualTo("email", mEmail)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value,
                                    @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        return;
                    }
                    listaDatosUser.clear();
                    for (QueryDocumentSnapshot doc : value) {
                        listaDatosUser.add(doc.getString("nombre"));
                        listaDatosUser.add(doc.getString("cumple"));
                        listaDatosUser.add(doc.getString("email"));
                    }
                    //Map<String, Object> user = new HashMap<>();
                    //listaDatosUser.get(0, (String) user.get("nombre"));//nombre del user
                    sNom = listaDatosUser.get(0).toString();
                    sCump = listaDatosUser.get(1).toString();
                    sEma = listaDatosUser.get(2).toString();
                    //sCump = (String) user.get("cumple");//cumple del user
                    //sEma = (String) user.get("email");//correo del usuario

                    Log.d("Nombre", sNom);
                    Log.d("cumple", sCump);
                    Log.d("email", sEma);
                    textCNombre.setText(sNom);
                    textCumple.setText(sCump);
                    textCorreo.setText(sEma);
                }

            });
    }
}