package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button botonLogin;
    TextView botonRegistro;
    EditText emailText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        emailText = findViewById(R.id.cajaCorreo);
        passText = findViewById(R.id.cajaContraseña);


        botonLogin = findViewById(R.id.botonLogin);
        botonLogin.setOnClickListener(view -> {
            String email = emailText.getText().toString();
            String password = passText.getText().toString();

            String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(email);
            if (email.isEmpty()) {
                emailText.setError("Campo sin rellenar");
            } else if (!m.find()) {//si no se cumple el filtro regex del correo avisa
                emailText.setError("Hay que colocar un correo");
            } else if (password.isEmpty()) {
                passText.setError("Campo sin rellenar");
            }else if(!(password.length() >= 6)){
                passText.setError("Necesitas 6 o mas caracteres para la contraseña");
            }else {// si las validaciones estan bien
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //En caso de que sea correcto el user/passwd accedemos al main
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(Login.this, "Accediendo",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    //En caso de no dar un user/passwd correcto.
                                    Toast.makeText(Login.this, "Usuario incorrecto.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

        botonRegistro = findViewById(R.id.botonRegistro);
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }

}