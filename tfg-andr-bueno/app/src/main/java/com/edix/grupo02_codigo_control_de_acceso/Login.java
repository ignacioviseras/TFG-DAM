package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.apiService.AccessToken;
import com.edix.grupo02_codigo_control_de_acceso.entities.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity{

    private EditText emailText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // comprueba si el usuario ya está logado
        this.isLogged();

        // carga el layout y esconde la barra de navegacion
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // parametriza los elementos  de la gui

        emailText = findViewById(R.id.mailBox);
        passText = findViewById(R.id.passBox);
        Button loginBtn = findViewById(R.id.loginBtn);
        Button signinBtn = findViewById(R.id.signinBtn);

        // anade las acciones a los botones

        loginBtn.setOnClickListener(this::login);
        signinBtn.setOnClickListener(this::goToSignIn);
    }

    private void isLogged(){
        String token = AppUtils.getAuthToken(getApplicationContext());
        welcome(token);
        if(token != null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void goToSignIn(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        finish();
    }

    private void login(View view) {
        try {
            User user = User.get(emailText.getText().toString(), passText.getText().toString());
            Call<AccessToken> call = ApiAdapter.getApiService().getLogin(user);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                    if (response.isSuccessful()) {
                        // se obtiene el token
                        AccessToken accessToken = response.body();
                        if(accessToken != null){
                            AppUtils.setVariable(getApplicationContext(), "_token", accessToken.getJwtToken());
                            welcome(accessToken.getJwtToken());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

                }
            });
        } catch (IllegalArgumentException e) {
            AppToast.show(getApplicationContext(),e.getMessage(),AppToast.FAIL);
        }
    }

    private void welcome(String token){
        Call<User> call = ApiAdapter.getApiService().whoAmI(token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if(user != null){
                        Context context = getApplicationContext();
                        AppUtils.setVariable(context, "_role", user.getRole());
                        AppToast.show(context,"Bienvenida "+user.getName(),AppToast.INFO);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });
    }
}