package com.edix.grupo02_codigo_control_de_acceso.helpers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsHelper {
    public static void refresh(Context context){
        try {
            Call<List<Event>> call = ApiAdapter.getApiService().getEvents();
            call.enqueue(new Callback<List<Event>>() {
                @Override
                public void onResponse(@NonNull Call<List<Event>> call, @NonNull Response<List<Event>> response) {
                    if (response.isSuccessful()) {
                        DataBaseUtils.getDBManager(context).eventDao().reset();
                        DataBaseUtils.getDBManager(context).eventDao().insertAll(response.body());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<Event>> call, @NonNull Throwable t) {

                }
            });
        } catch (IllegalArgumentException ignored) {

        }
    }
    public static List<Event> getAll(Context context){
        return DataBaseUtils.getDBManager(context).eventDao().getAll();
    }

    public static Event get(Context context, int eventId){
        return DataBaseUtils.getDBManager(context).eventDao().findById(eventId);
    }
}
