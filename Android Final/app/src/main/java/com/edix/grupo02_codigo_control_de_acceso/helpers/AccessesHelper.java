package com.edix.grupo02_codigo_control_de_acceso.helpers;

import android.content.Context;

import androidx.annotation.NonNull;

import com.edix.grupo02_codigo_control_de_acceso.apiService.ApiAdapter;
import com.edix.grupo02_codigo_control_de_acceso.database.DataBaseUtils;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccessesHelper {
    public static void refresh(Context context){
        try {
            Call<List<Access>> call = ApiAdapter.getApiService().getAccesses(AppUtils.getAuthToken(context));
            call.enqueue(new Callback<List<Access>>() {
                @Override
                public void onResponse(@NonNull Call<List<Access>> call, @NonNull Response<List<Access>> response) {
                    if (response.isSuccessful()) {
                        DataBaseUtils.getDBManager(context).accessDao().reset();
                        DataBaseUtils.getDBManager(context).accessDao().insertAll(response.body());
                    }
                }
                @Override
                public void onFailure(@NonNull Call<List<Access>> call, @NonNull Throwable t) {
                }
            });
        } catch (IllegalArgumentException ignored) {

        }
    }
    public static List<Access> getAll(Context context){
        return DataBaseUtils.getDBManager(context).accessDao().getAll();
    }

    public static Access get(Context context, int eventId){
        return DataBaseUtils.getDBManager(context).accessDao().findById(eventId);
    }
}
