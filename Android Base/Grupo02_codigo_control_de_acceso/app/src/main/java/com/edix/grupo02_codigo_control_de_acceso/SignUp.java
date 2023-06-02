package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    Button botonRegistro;
    EditText emailText, passText, nameText, birthText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailText = findViewById(R.id.cajaCorreoS);
        passText = findViewById(R.id.cajaContraseñaS);
        nameText = findViewById(R.id.cajaNombre);
        birthText= findViewById(R.id.cajaCumple);

        botonRegistro = findViewById(R.id.botonRegistroS);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailText.getText().toString();
                String password = passText.getText().toString();
                //Crear usuario en firebase
                if (email.isEmpty()){
                    emailText.setError("Campo sin rellenar");
                }else if (password.isEmpty()){
                    passText.setError("Campo sin rellenar");
                }else{// si las validaciones estan bien
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String cumple = birthText.getText().toString();
                                        String nombre = nameText.getText().toString();

                                        Map<String, Object> user = new HashMap<>();
                                        user.put("nombre", nombre);//nombre del user
                                        user.put("cumple", cumple);//cumple del user
                                        user.put("email", email);//correo del usuario

                                        //añadimos la tarea a la base de datos
                                        db.collection("Users").add(user);
                                        //damos acceso al menu
                                        Toast.makeText(SignUp.this, "Usuario Registrado", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // En caso de que el usuario ya exista.
                                        Toast.makeText(SignUp.this, "Este usuario ya existe.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

    }
}
