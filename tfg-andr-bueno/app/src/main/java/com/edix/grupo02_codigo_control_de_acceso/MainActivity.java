package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.edix.grupo02_codigo_control_de_acceso.adapters.AccessAdapter;
import com.edix.grupo02_codigo_control_de_acceso.adapters.EventsAdapter;
import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
import com.edix.grupo02_codigo_control_de_acceso.io.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.io.response.Access;
import com.edix.grupo02_codigo_control_de_acceso.io.response.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String email;
    ListView listView;
    SearchView searchView;

    List<Access> accessesList = new ArrayList<>();
    List<Event> eventsList = new ArrayList<>();
    List<String> listaRegistros = new ArrayList<>();
    ArrayAdapter<String> mAdapterRegistros;
    Spinner select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.boughtAccessesList);
        loadAccesses();
    }

    private void loadAccesses() {
        try {
            Call<List<Access>> call = ApiAdapter.getApiService().getAccesses(AppUtils.getAuthToken(getApplicationContext()));
            call.enqueue(new Callback<List<Access>>() {
                @Override
                public void onResponse(Call<List<Access>> call, Response<List<Access>> response) {
                    if (response.isSuccessful()) {
                        AccessAdapter adapter = new AccessAdapter(getApplicationContext(), response.body());
                        listView.setAdapter(adapter);
                    }
                }
                @Override
                public void onFailure(Call<List<Access>> call, Throwable t) {

                }
            });
        } catch (IllegalArgumentException e) {

        }
    }

    private void loadEvents() {
        try {
            Call<List<Event>> call = ApiAdapter.getApiService().getEvents();
            call.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                    if (response.isSuccessful()) {
                        EventsAdapter adapter = new EventsAdapter(getApplicationContext(), response.body());
                        listView.setAdapter(adapter);
                    }
                }
                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {

                }
            });
        } catch (IllegalArgumentException e) {

        }
    }

    private void editProfile(){
        Intent intent =  new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

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
            case R.id.logout:
                // cierre de sesion
                onBackPressed();
                finish();
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }

    public void pantallaqr (View view){
        View parent = (View) view.getParent();
        TextView registroTextView = parent.findViewById(R.id.accessQR);
        String registro = registroTextView.getText().toString();


        Intent intent = new Intent(MainActivity.this, showqr.class);
        intent.putExtra("txtQr", registro);//pasamos el texto de la lista
        startActivity(intent);
    }
}