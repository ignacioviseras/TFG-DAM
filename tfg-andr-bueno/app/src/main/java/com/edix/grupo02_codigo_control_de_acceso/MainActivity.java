package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String email;
    String nombreRegistro;
    ListView listViewRegistro, listView;
    SearchView searchView;

    List<String> listaRegistros = new ArrayList<>();
    List<String> listaIdRegistros= new ArrayList<>();
    ArrayAdapter<String> mAdapterRegistros;
    Spinner select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewRegistro=findViewById(R.id.listView);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);

        //actualizar la interfaz de usuario con sus propias tareas.
        actualizarUI();
        buscarUno();

        ImageButton botonHome = findViewById(R.id.botonHome);
        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSearch = findViewById(R.id.botonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScannerQR.class);
                startActivity(intent);
            }
        });

        ImageButton botonProfile = findViewById(R.id.botonProfile);
        botonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    private void actualizarUI(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mas:

                final EditText taskEditText= new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Nuevo Registro")
                        .setMessage("Escribe el nuevo Registro")
                        .setView(taskEditText)
                        .setPositiveButton("Añadir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                String miRegistro = taskEditText.getText().toString();


                                Map<String, Object> registro = new HashMap<>();
                                registro.put("nombreRegistro", miRegistro);
                                registro.put("emailUsuario", email);


                            }
                        })

                        .setNegativeButton("Cancelar", null)
                        .create();
                dialog.show();

                return true;
            case R.id.logout:
                // cierre de sesion
                onBackPressed();
                finish();
                return true;
            default:return super.onOptionsItemSelected(item);
        }


    }

    public void borrarTarea (View view){
        View parent = (View) view.getParent();
        TextView registroTextView = parent.findViewById(R.id.registro);
        String registro = registroTextView.getText().toString();
        int posicion = listaRegistros.indexOf(registro);


    }




    public void pantallaqr (View view){
        View parent = (View) view.getParent();
        TextView registroTextView = parent.findViewById(R.id.registro);
        String registro = registroTextView.getText().toString();


        Intent intent = new Intent(MainActivity.this, showqr.class);
        intent.putExtra("txtQr", registro);//pasamos el texto de la lista
        startActivity(intent);
    }


    private void buscarUno(){


        // Configurar el adaptador del ListView
        mAdapterRegistros = new ArrayAdapter<>(MainActivity.this, R.layout.item_registro, listaRegistros);
        listView.setAdapter(mAdapterRegistros);

        // Configurar el listener del SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar la lista de registros según el texto ingresado en el SearchView
                mAdapterRegistros.getFilter().filter(newText);
                return false;
            }
        });
    }


}