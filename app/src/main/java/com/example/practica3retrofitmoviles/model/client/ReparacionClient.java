package com.example.practica3retrofitmoviles.model.client;

import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.model.entity.Reparacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReparacionClient {
    @DELETE("reparaciones/{id}")
    Call<Integer> deleteReparacion(@Path("id") long id);

    @GET("reparaciones/{id}")
    Call<Reparacion> getReparacion(@Path("id") long id);

    @GET("reparaciones")
    Call<List<Reparacion>> getReparaciones();

    @POST("reparaciones")
    Call<Long> postReparacion(@Body Reparacion reparacion);

    @PUT("reparaciones/{id}")
    Call<Integer> putReparacion(@Path("id") long id, @Body Reparacion reparacion);

}
