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

import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.helpers.AccessesHelper;
import com.edix.grupo02_codigo_control_de_acceso.helpers.EventsHelper;
import com.edix.grupo02_codigo_control_de_acceso.helpers.ToastHelper;
import com.edix.grupo02_codigo_control_de_acceso.R;
import com.edix.grupo02_codigo_control_de_acceso.helpers.AppUtils;
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
        TextView itemTextView = convertView.findViewById(R.id.eventTitle);
        Event event = itemList.get(position);

        if(this.isAdmin){
            deleteEvent.setVisibility(View.VISIBLE);
        }else{
            buyAccess.setVisibility(View.VISIBLE);
        }

        View the_view = convertView;
        deleteEvent.setOnClickListener(v->{
            deleteEvent(parent.getContext(), event);
            the_view.setVisibility(View.INVISIBLE);
        });


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
                                AccessesHelper.refresh(parent.getContext());
                                String msg = "Tienes "+access.getAvailables()+" accesos para "+event.getName();
                                ToastHelper.info(parent.getContext(),msg);
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

    public void deleteEvent(Context context, Event event) {
        Call<Boolean> call = ApiAdapter.getApiService().deleteEvent(AppUtils.getAuthToken(context), event.getId());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if (response.isSuccessful()) {
                    DataBaseUtils.getDBManager(context).eventDao().delete(event);
                    String msg = "El evento fue eliminado";
                    ToastHelper.info(context,msg);
                    // no necesita actualizar los accesos porque sulo puede borrar eventos el administrador
                    EventsHelper.refresh(context);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });
    }
}
