package com.edix.grupo02_codigo_control_de_acceso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
import com.edix.grupo02_codigo_control_de_acceso.io.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.io.AccessToken;
import com.edix.grupo02_codigo_control_de_acceso.io.response.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity{

    private Button loginBtn;
    private TextView signinBtn;
    private EditText emailText, passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carga el layout y esconde la barra de navegacion

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        // parametriza los elementos  de la gui

        emailText = findViewById(R.id.mailBox);
        passText = findViewById(R.id.passBox);
        loginBtn = findViewById(R.id.loginBtn);
        signinBtn = findViewById(R.id.signinBtn);

        // anade las acciones a los botones

        loginBtn.setOnClickListener(this::login);
        signinBtn.setOnClickListener(this::goToSignIn);
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
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    if (response.isSuccessful()) {
                        AccessToken accessToken = response.body();
                        AppUtils.setMyVariable(getApplicationContext(), "_token", accessToken.getJwtToken());
                    }
                }
                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {

                }
            });
        } catch (IllegalArgumentException e) {

        }
    }
}