package com.edix.grupo02_codigo_control_de_acceso.apiService;

import com.edix.grupo02_codigo_control_de_acceso.apiService.response.AccessToken;
import com.edix.grupo02_codigo_control_de_acceso.entities.Access;
import com.edix.grupo02_codigo_control_de_acceso.entities.Event;
import com.edix.grupo02_codigo_control_de_acceso.apiService.response.EventQuantity;
import com.edix.grupo02_codigo_control_de_acceso.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/login")
    Call<AccessToken> getLogin(@Body User user);

    @POST("/signin")
    Call<Boolean> signin(@Body User user);

    @GET("/events")
    Call<List<Event>> getEvents();

    @GET("/whoami")
    Call<User> whoAmI(@Header("Authorization") String token);

    @POST("/customer/update")
    Call<User> updateCustomer(@Header("Authorization") String token, @Body User user);

    @DELETE("/customer/access/{accessId}}")
    Call<Boolean> deleteAccess(@Header("Authorization") String token, @Path("accessId") int access_id);

    @POST("/admin/update")
    Call<User> updateAdmin(@Header("Authorization") String token, @Body User user);

    @GET("/admin/validateAccess/{accessId}")
    Call<Access> validateAccess(@Header("Authorization") String token, @Path("accessId") int access_id);

    @GET("/events/{id}")
    Call<Event> getEvents(@Path("id") int id);

    @GET("/customer/accesses")
    Call<List<Access>> getAccesses(@Header("Authorization") String token);

    @POST("/customer/buyevent/{event_id}")
    Call<Access> buyAccess(@Header("Authorization") String token, @Path("event_id") int id, @Body EventQuantity q);
}
