package com.edix.grupo02_codigo_control_de_acceso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.edix.grupo02_codigo_control_de_acceso.io.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.io.response.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    private Button signinBtn;
    private EditText emailText, passText, nameText;
    private RadioGroup roleGroup;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        // parametriza la gui
        emailText = findViewById(R.id.signInMailBox);
        passText = findViewById(R.id.signInPassBox);
        nameText = findViewById(R.id.signInBoxName);
        signinBtn = findViewById(R.id.botonRegistroS);
        roleGroup = findViewById(R.id.whatRoleIs);
        role = "customer";
        // anade las acciones de cambiar el rol y registrar
        roleGroup.setOnCheckedChangeListener(this::updateRole);
        signinBtn.setOnClickListener(this::signIn);
    }

    private void signIn(View view) {
        try {
            Call<User> call = ApiAdapter.getApiService().signin(
                    User.get(
                        emailText.getText().toString(),
                        passText.getText().toString(),
                        nameText.getText().toString(),
                        role)
            );
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User newUser = response.body();
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        } catch (IllegalArgumentException e) {

        }
        this.goToLogin(view);
    }

    private void updateRole(RadioGroup group, int checkedId) {
        // Aquí puedes realizar acciones según la opción seleccionada
        if (checkedId == R.id.roleIsAdmin) {
            role = "admin";
        } else if (checkedId == R.id.roleIsCustomer) {
            role = "customer";
        }
    }

    private void goToLogin(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        finish();
    }
}
