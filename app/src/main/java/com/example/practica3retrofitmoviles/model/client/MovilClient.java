package com.example.practica3retrofitmoviles.model.client;

import com.example.practica3retrofitmoviles.model.entity.Movil;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MovilClient {

    @DELETE("moviles/{id}")
    Call<Integer> deleteMovil(@Path("id") long id);

    @GET("moviles/{id}")
    Call<Movil> getMovil(@Path("id") long id);

    @GET("moviles")
    Call<List<Movil>> getMoviles();

    @POST("moviles")
    Call<Long> postMovil(@Body Movil movil);

    @PUT("moviles/{id}")
    Call<Integer> putMovil(@Path("id") long id, @Body Movil movil);
}
