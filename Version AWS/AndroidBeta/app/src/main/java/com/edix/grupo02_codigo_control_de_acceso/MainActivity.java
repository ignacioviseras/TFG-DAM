package com.edix.grupo02_codigo_control_de_acceso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.edix.grupo02_codigo_control_de_acceso.adapters.AccessAdapter;
import com.edix.grupo02_codigo_control_de_acceso.adapters.EventsAdapter;
import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    private boolean isAdmin = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.boughtAccessesList);
        isAdmin = AppUtils.isAdmin(getApplicationContext());
        refreshEvents(false);
        refreshAccesses(true);
        loadQRMenu();
    }

    private void loadQRMenu(){
        ImageButton eventsBtn = findViewById(R.id.events);
        ImageButton editProfileBtn = findViewById(R.id.editProfile);;
        ImageButton accessBtn = findViewById(R.id.boughtAccesses);;

        accessBtn.setOnClickListener(v->{
            deactivateBtns(eventsBtn, editProfileBtn, accessBtn);
            refreshAccesses(true);
            activateBtn(accessBtn, true);
        });
        editProfileBtn.setOnClickListener(v->{
            deactivateBtns(eventsBtn, editProfileBtn, accessBtn);
            editProfile();
            activateBtn(editProfileBtn, true);
        });
        eventsBtn.setOnClickListener(v->{
            deactivateBtns(eventsBtn, editProfileBtn, accessBtn);
            refreshEvents(true);
        });
    };

    private void deactivateBtns(ImageButton... buttons){
        for (ImageButton btn : buttons) {
            activateBtn(btn, false);
        }
    }

    private void activateBtn(ImageButton btn, Boolean activated){
        int color = R.color.black;
        if(activated){
            color = R.color.light_blue;
        }
        btn.setImageTintList(ContextCompat.getColorStateList(getApplicationContext(), color));
    }

    private void loadAccesses() {
        AccessAdapter adapter = new AccessAdapter(
                getApplicationContext(),
                DataBaseUtils.getDBManager(getApplicationContext()).accessDao().getAll()
        );
        listView.setAdapter(adapter);
    }

    private void loadEvents() {
        EventsAdapter adapter = new EventsAdapter(
                getApplicationContext(),
                DataBaseUtils.getDBManager(getApplicationContext()).eventDao().getAll()
        );
        listView.setAdapter(adapter);
    }

    private void refreshAccesses(boolean print){
        try {
            Call<List<Access>> call = ApiAdapter.getApiService().getAccesses(AppUtils.getAuthToken(getApplicationContext()));
            call.enqueue(new Callback<List<Access>>() {
                @Override
                public void onResponse(@NonNull Call<List<Access>> call, @NonNull Response<List<Access>> response) {
                    if (response.isSuccessful()) {
                        DataBaseUtils.getDBManager(getApplicationContext()).accessDao().reset();
                        DataBaseUtils.getDBManager(getApplicationContext()).accessDao().insertAll(response.body());
                        if(print){
                            loadAccesses();
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<Access>> call, @NonNull Throwable t) {
                    if(print){
                        loadAccesses();
                    }
                }
            });
        } catch (IllegalArgumentException ignored) {

        }
    }
    private void refreshEvents(boolean print) {
        try {
            Call<List<Event>> call = ApiAdapter.getApiService().getEvents();
            call.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
                    if (response.isSuccessful()) {
                        DataBaseUtils.getDBManager(getApplicationContext()).eventDao().reset();
                        DataBaseUtils.getDBManager(getApplicationContext()).eventDao().insertAll(response.body());
                        if (print) {
                            loadEvents();
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<Event>> call, @NonNull Throwable t) {
                    if (print) {
                        loadEvents();
                    }
                }
            });
        } catch (IllegalArgumentException ignored) {

        }
    }

    private void editProfile(){
        Intent intent =  new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }
}