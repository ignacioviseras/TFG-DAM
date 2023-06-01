package com.edix.grupo02_codigo_control_de_acceso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.helpers.ToastHelper;
import com.edix.grupo02_codigo_control_de_acceso.helpers.AppUtils;
import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.entities.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText passText, passTextBis;
    private EditText nameText;
    private boolean isAdmin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAdmin = AppUtils.isAdmin(getApplicationContext());
        setContentView(R.layout.activity_edit_profile);
        nameText = findViewById(R.id.editBoxName);
        emailText = findViewById(R.id.editBoxMail);
        passText = findViewById(R.id.editPassBox);
        passTextBis = findViewById(R.id.editPassBoxBis);
        Button updateBtn = findViewById(R.id.updateProfileBtn);
        updateBtn.setOnClickListener(this::updateProfile);
        MenuFragment.load(this, R.id.fragment_menu);

        loadProfile();
    }

    private void loadProfile() {
        Call<User> call = ApiAdapter.getApiService().whoAmI(AppUtils.getAuthToken(getApplicationContext()));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if(user != null){
                        nameText.setText(user.getName());
                        emailText.setText(user.getMail());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });
    }

    private void updateProfile(View view) {
        try {
            if(!passTextBis.getText().toString().equals(passText.getText().toString())){
                ToastHelper.warn(getApplicationContext(), "las contraseñas no coinciden");
                return;
            }
            User user = User.getForUpdate(emailText.getText().toString(), passText.getText().toString(), nameText.getText().toString());
            if(user == null){ // no pongo ningún mensaje porque si están todos los campos vacios esta claro que es una pulsacion erronea
                return;
            }
            Call<User> call;
            if (isAdmin) {
                call = ApiAdapter.getApiService().updateAdmin(AppUtils.getAuthToken(getApplicationContext()), user);
            }else{
                call = ApiAdapter.getApiService().updateCustomer(AppUtils.getAuthToken(getApplicationContext()), user);
            }
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        String msg = "Se ha actualizado el perfil";
                        ToastHelper.info(getApplicationContext(),msg);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    ToastHelper.fail(getApplicationContext(),"La petición no ha podido resolverse");
                }
            });

        }catch(IllegalArgumentException e) {
            ToastHelper.fail(getApplicationContext(),e.getMessage());
        }

    }
}
