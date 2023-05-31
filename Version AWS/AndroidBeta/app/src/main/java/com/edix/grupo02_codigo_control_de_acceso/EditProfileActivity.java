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
import com.edix.grupo02_codigo_control_de_acceso.global.AppToast;
import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
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
        Button cancelBtn = findViewById(R.id.cancelUpdateBtn);

        updateBtn.setOnClickListener(this::updateProfile);
        cancelBtn.setOnClickListener(this::goToMainPage);

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
                AppToast.show(getApplicationContext(), "las contraseñas no coinciden", AppToast.FAIL);
                return;
            }
            User user = User.get(passText.getText().toString(), nameText.getText().toString(), "CUSTOMER", emailText.getText().toString());
            user.setPassword(null);
            Call<User> call;
            if (isAdmin) {
                call = ApiAdapter.getApiService().updateCustomer(AppUtils.getAuthToken(getApplicationContext()), user);
            }else{
                call = ApiAdapter.getApiService().updateAdmin(AppUtils.getAuthToken(getApplicationContext()), user);
            }
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                }
            });

        }catch(IllegalArgumentException ignored) {

        }

    }

    private void goToMainPage(View view) {
        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if(isAdmin){
            menu.findItem(R.id.checkAccess).setVisible(true);
        }else{
            menu.findItem(R.id.boughtAccesses).setVisible(true);
        }

        menu.findItem(R.id.logout).setVisible(false);

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.events:
            case R.id.boughtAccesses:
                Intent eventsIntent =  new Intent(this, MainActivity.class);
                startActivity(eventsIntent);
                return true;
            case R.id.editProfile:
                return true;
            case R.id.logout:
                // cierre de sesion
                DataBaseUtils.removeDB(getApplicationContext());
                onBackPressed();
                finish();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
}
