package com.edix.grupo02_codigo_control_de_acceso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterList extends AppCompatActivity {

    String email;
    String nombreRegistro;
    ListView listViewRegistro;

    List<String> listaRegistros = new ArrayList<>();
    List<String> listaIdRegistros= new ArrayList<>();
    ArrayAdapter<String> mAdapterRegistros;
    Spinner select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_list);

        listViewRegistro=findViewById(R.id.listView);

        //actualizar la interfaz de usuario con sus propias tareas.
        actualizarUI();

        /*//@SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton btnqr = findViewById(R.id.btnqr);
        btnqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterList.this, showqr.class);
                startActivity(intent);
            }
        });*/

        ImageButton botonHome = findViewById(R.id.botonHome);
        botonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton botonSearch = findViewById(R.id.botonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterList.this, ScannerQR.class);
                startActivity(intent);
            }
        });

        ImageButton botonProfile = findViewById(R.id.botonProfile);
        botonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterList.this, Profile.class);
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


/*
    public void vistaSelect(){
        select = findViewById(R.id.select);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.itemselect));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(myadapter);
    }*/


    public void pantallaqr (View view){
        Intent intent = new Intent(RegisterList.this, showqr.class);

        TextView registro = findViewById(R.id.registro);
        intent.putExtra("txtQr", registro.getText().toString());//pasamos el texto de la lista
        startActivity(intent);

    }



}

