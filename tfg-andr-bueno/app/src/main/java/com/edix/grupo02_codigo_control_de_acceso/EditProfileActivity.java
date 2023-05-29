package com.edix.grupo02_codigo_control_de_acceso;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.edix.grupo02_codigo_control_de_acceso.adapters.AccessAdapter;
import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
import com.edix.grupo02_codigo_control_de_acceso.io.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.io.response.Access;
import com.edix.grupo02_codigo_control_de_acceso.io.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private Button signinBtn;
    private EditText emailText, passText, nameText, passTextBis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        loadProfile();
    }

    private void loadProfile() {
        Call<User> call = ApiAdapter.getApiService().whoAmI(AppUtils.getAuthToken(getApplicationContext()));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    nameText.setText(user.getName());
                    emailText.setText(user.getMail());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
