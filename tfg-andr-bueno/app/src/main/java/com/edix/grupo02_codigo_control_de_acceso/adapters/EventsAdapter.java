package com.edix.grupo02_codigo_control_de_acceso.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.edix.grupo02_codigo_control_de_acceso.R;
import com.edix.grupo02_codigo_control_de_acceso.io.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.io.response.Access;
import com.edix.grupo02_codigo_control_de_acceso.io.response.Event;

import java.util.List;

public class EventsAdapter  extends ArrayAdapter<Event> {
    private List<Event> itemList;

    public EventsAdapter(Context context, List<Event> itemList) {
        super(context, 0, itemList);
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.access_row, parent, false);
        }

        TextView itemTextView = convertView.findViewById(R.id.accessTitle);
        Event event = itemList.get(position);
        itemTextView.setText(event.getName());
        return convertView;
    }
}
