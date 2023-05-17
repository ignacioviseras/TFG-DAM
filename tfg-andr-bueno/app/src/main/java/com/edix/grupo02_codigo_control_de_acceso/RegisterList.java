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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterList extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseFirestore db;
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

        db = FirebaseFirestore.getInstance();
        mAuth= FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();
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
                Intent intent = new Intent(RegisterList.this, RegisterList.class);
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
        db.collection("Registros")
                .whereEqualTo("emailUsuario", email)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {

                            return;
                        }
                        listaRegistros.clear();
                        listaRegistros.clear();
                        for (QueryDocumentSnapshot doc : value) {
                            listaIdRegistros.add(doc.getId());
                            listaRegistros.add(doc.getString("nombreRegistro"));
                            if (listaRegistros.size() == 0) {
                                listViewRegistro.setAdapter(null);
                            }else {
                                mAdapterRegistros= new ArrayAdapter<String>(RegisterList.this, R.layout.item_registro, R.id.registro, listaRegistros);
                                listViewRegistro.setAdapter(mAdapterRegistros);
                            }
                        }

                    }
                });



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

                                db.collection("Registros").add(registro);

                            }
                        })

                        .setNegativeButton("Cancelar", null)
                        .create();
                dialog.show();

                return true;
            case R.id.logout:
                // cierre de sesion por firebase
                mAuth.signOut();
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

        db.collection("Registros").document(listaIdRegistros.get(posicion)).delete();

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
        startActivity(intent);

    }



}

