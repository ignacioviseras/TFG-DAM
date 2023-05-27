package com.edix.grupo02_codigo_control_de_acceso.io;

import com.edix.grupo02_codigo_control_de_acceso.io.response.Access;
import com.edix.grupo02_codigo_control_de_acceso.io.response.Event;
import com.edix.grupo02_codigo_control_de_acceso.io.response.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/login")
    Call<AccessToken> getLogin(@Body User user);

    @POST("/signin")
    Call<User> signin(@Body User user);

    @GET("/events")
    Call<List<Event>> getEvents();

    @GET("/customer/accesses")
    Call<List<Access>> getAccesses(@Header("Authorization") String token);
}
