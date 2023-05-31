package com.edix.grupo02_codigo_control_de_acceso.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.edix.grupo02_codigo_control_de_acceso.AppToast;
import com.edix.grupo02_codigo_control_de_acceso.R;
import com.edix.grupo02_codigo_control_de_acceso.global.AppUtils;
import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.edix.grupo02_codigo_control_de_acceso.apiService.response.EventQuantity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsAdapter  extends ArrayAdapter<Event> {
    private final List<Event> itemList;
    private final Boolean isAdmin;

    public EventsAdapter(Context context, List<Event> itemList) {
        super(context, 0, itemList);
        this.itemList = itemList;
        this.isAdmin = AppUtils.isAdmin(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_row, parent, false);
        }
        ImageButton deleteEvent = convertView.findViewById(R.id.eventDelete);
        ImageButton buyAccess = convertView.findViewById(R.id.eventBuy);
        ImageButton editEvent = convertView.findViewById(R.id.eventEdit);

        if(this.isAdmin){
            editEvent.setVisibility(View.VISIBLE);
            deleteEvent.setVisibility(View.VISIBLE);
        }else{
            buyAccess.setVisibility(View.VISIBLE);
        }

        TextView itemTextView = convertView.findViewById(R.id.eventTitle);
        Event event = itemList.get(position);
        itemTextView.setText(event.getName());
        buyAccess.setOnClickListener(v -> {
            try {
                Call<Access> call = ApiAdapter.getApiService().buyAccess(AppUtils.getAuthToken(parent.getContext()), event.getId(), new EventQuantity(1));
                call.enqueue(new Callback<Access>() {
                    @Override
                    public void onResponse(@NonNull Call<Access> call, @NonNull Response<Access> response) {
                        if (response.isSuccessful()) {
                            Access access = response.body();
                            if(access != null){
                                String msg = "Tienes "+access.getAvailables()+" accesos para "+event.getName();
                                AppToast.show(parent.getContext(),msg,AppToast.INFO);
                            }
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Access> call, @NonNull Throwable t) {

                    }
                });
            } catch (IllegalArgumentException ignored) {

            }
        });
        return convertView;
    }
}