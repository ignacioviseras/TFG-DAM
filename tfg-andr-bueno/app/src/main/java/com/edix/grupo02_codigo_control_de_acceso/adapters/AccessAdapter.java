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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccessAdapter extends ArrayAdapter<Access> {
    private List<Access> itemList;

    public AccessAdapter(Context context, List<Access> itemList) {
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
        Access access = itemList.get(position);       

        Call<Event> call = ApiAdapter.getApiService().getEvents(access.getEvent_id());
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.isSuccessful()) {
                    Event eventResponse = response.body();
                    itemTextView.setText(eventResponse.getName()+" (x"+access.getAvailables()+")");
                } else {

                }
            }
            @Override
            public void onFailure(Call<Event> call, Throwable t) {

            }
        });

        return convertView;
    }
}
