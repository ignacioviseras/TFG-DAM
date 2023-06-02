package com.edix.grupo02_codigo_control_de_acceso;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.edix.grupo02_codigo_control_de_acceso.adapters.EventsAdapter;
import com.edix.grupo02_codigo_control_de_acceso.helpers.EventsHelper;

public class EventsActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        listView = findViewById(R.id.allEventsList);
        MenuFragment.load(this, R.id.fragment_menu);
        printEvents();
    }

    private void printEvents() {
        Context context = getApplicationContext();
        EventsAdapter adapter = new EventsAdapter(context, EventsHelper.getAll(context));
        listView.setAdapter(adapter);
    }
}