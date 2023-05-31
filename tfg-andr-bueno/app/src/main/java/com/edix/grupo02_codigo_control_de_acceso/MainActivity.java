package com.edix.grupo02_codigo_control_de_acceso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        refreshEvents();
    }

    private void loadAccesses() {
        try {
            Call<List<Access>> call = ApiAdapter.getApiService().getAccesses(AppUtils.getAuthToken(getApplicationContext()));
            call.enqueue(new Callback<List<Access>>() {
                @Override
                public void onResponse(@NonNull Call<List<Access>> call, @NonNull Response<List<Access>> response) {
                    if (response.isSuccessful()) {
                        AccessAdapter adapter = new AccessAdapter(getApplicationContext(), response.body());
                        listView.setAdapter(adapter);
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<Access>> call, @NonNull Throwable t) {

                }
            });
        } catch (IllegalArgumentException ignored) {

        }
    }

    private void loadEvents() {
        EventsAdapter adapter = new EventsAdapter(
                getApplicationContext(),
                DataBaseUtils.getDBManager(getApplicationContext()).eventDao().getAll()
        );
        listView.setAdapter(adapter);
    }
    private void refreshEvents() {
        try {
            Call<List<Event>> call = ApiAdapter.getApiService().getEvents();
            call.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
                    if (response.isSuccessful()) {
                        DataBaseUtils.getDBManager(getApplicationContext()).eventDao().reset();
                        DataBaseUtils.getDBManager(getApplicationContext()).eventDao().insertAll(response.body());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<Event>> call, @NonNull Throwable t) {

                }
            });
        } catch (IllegalArgumentException ignored) {

        }
    }

    private void editProfile(){
        Intent intent =  new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        if(isAdmin){
            menu.findItem(R.id.checkAccess).setVisible(true);
        }else{
            menu.findItem(R.id.boughtAccesses).setVisible(true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.events:
                loadEvents();
                return true;
            case R.id.boughtAccesses:
                loadAccesses();
                return true;
            case R.id.editProfile:
                editProfile();
                return true;
            case R.id.checkAccess:
                Intent intent =  new Intent(this, ScanQrActivity.class);
                startActivity(intent);
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