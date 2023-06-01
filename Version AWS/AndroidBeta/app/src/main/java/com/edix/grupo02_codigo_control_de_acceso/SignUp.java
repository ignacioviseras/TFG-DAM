package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.entities.User;
import com.edix.grupo02_codigo_control_de_acceso.helpers.ToastHelper;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private EditText emailText, passText, nameText;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        // parametriza la gui
        emailText = findViewById(R.id.signInMailBox);
        passText = findViewById(R.id.signInPassBox);
        nameText = findViewById(R.id.signInBoxName);
        Button signinBtn = findViewById(R.id.botonRegistroS);
        RadioGroup roleGroup = findViewById(R.id.whatRoleIs);
        role = "customer";
        // anade las acciones de cambiar el rol y registrar
        roleGroup.setOnCheckedChangeListener(this::updateRole);
        signinBtn.setOnClickListener(this::signIn);
    }

    private void signIn(View view) {
        try {
            User user = User.get(
                    emailText.getText().toString(),
                    passText.getText().toString(),
                    nameText.getText().toString(),
                    role);
            Call<Boolean> call = ApiAdapter.getApiService().signin(user);
            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                    if (response.isSuccessful()) {
                        String msg = "Usuario registrado correctamente";
                        ToastHelper.show(getApplicationContext(),msg, ToastHelper.INFO);
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                    ToastHelper.show(getApplicationContext(),t.getMessage(), ToastHelper.FAIL);
                }
            });
        } catch (IllegalArgumentException e) {
            ToastHelper.show(getApplicationContext(),e.getMessage(), ToastHelper.FAIL);
        }
    }

    private void updateRole(RadioGroup group, int checkedId) {
        // Aquí puedes realizar acciones según la opción seleccionada
        if (checkedId == R.id.roleIsAdmin) {
            role = "admin";
        } else if (checkedId == R.id.roleIsCustomer) {
            role = "customer";
        }
    }
}
