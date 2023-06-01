package com.edix.grupo02_codigo_control_de_acceso;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.edix.grupo02_codigo_control_de_acceso.adapters.AccessAdapter;
import com.edix.grupo02_codigo_control_de_acceso.helpers.AccessesHelper;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        listView = findViewById(R.id.boughtAccessesList);
        MenuFragment.load(this, R.id.fragment_menu);
        printAccesses(context);
    }

    private void printAccesses(Context context) {
        AccessAdapter adapter = new AccessAdapter(context,AccessesHelper.getAll(context));
        listView.setAdapter(adapter);
    }
}