package com.example.practica3retrofitmoviles.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.practica3retrofitmoviles.model.client.MovilClient;
import com.example.practica3retrofitmoviles.model.client.ReparacionClient;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.model.entity.Reparacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private MovilClient movilClient;
    private ReparacionClient reparacionClient;
    private MutableLiveData<List<Movil>>listMutableLiveDataMoviles = new MutableLiveData<>();
    private MutableLiveData<List<Reparacion>>listMutableLiveDataReparacion = new MutableLiveData<>();
    private MutableLiveData<Long>longMutableLiveDataInsertar = new MutableLiveData<>();
    private MutableLiveData<Long>longMutableLiveDataInsertarReparacion = new MutableLiveData<>();
    private MutableLiveData<Integer>longMutableLiveDataDelete = new MutableLiveData<>();
    private MutableLiveData<Movil>movilMutableLiveData = new MutableLiveData<>();
    private Movil movilEditar;
    private Movil movilVer;
    private Movil movilRepair;

    public Repository() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://informatica.ieszaidinvergeles.org:9033/laraveles/appTiendaMovilesReparacion/public/api/").addConverterFactory(GsonConverterFactory.create()).build();
        movilClient = retrofit.create(MovilClient.class);
        reparacionClient = retrofit.create(ReparacionClient.class);

    }


    public void getAllMoviles(){
        Call<List<Movil>> call = movilClient.getMoviles();
        call.enqueue(new Callback<List<Movil>>() {
            @Override
            public void onResponse(Call<List<Movil>> call, Response<List<Movil>> response) {
                listMutableLiveDataMoviles.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movil>> call, Throwable t) {
                Log.v("ZZZ",t.getLocalizedMessage());
            }
        });
     }

     public void getAllReparaciones(){
         Call<List<Reparacion>> call = reparacionClient.getReparaciones();
         call.enqueue(new Callback<List<Reparacion>>() {
             @Override
             public void onResponse(Call<List<Reparacion>> call, Response<List<Reparacion>> response) {
                 listMutableLiveDataReparacion.setValue(response.body());

             }

             @Override
             public void onFailure(Call<List<Reparacion>> call, Throwable t) {

             }
         });
     }

     public void deleteMovil(long id){

        Call<Integer>callDelete = movilClient.deleteMovil(id);
        callDelete.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("ZZZ","DELET CORRETCO"+response.body());
                longMutableLiveDataDelete.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

     }
     public void addMovil(Movil movil){
         Call<Long> callAdd = movilClient.postMovil(movil);
         callAdd.enqueue(new Callback<Long>() {
             @Override
             public void onResponse(Call<Long> call, Response<Long> response) {
                 longMutableLiveDataInsertar.setValue(response.body());
             }

             @Override
             public void onFailure(Call<Long> call, Throwable t) {

             }
         });
    }

    public void updateMovil(long id,Movil movil){
        Call<Integer> callUpdate = movilClient.putMovil(id,movil);
        callUpdate.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("ZZZ","update bien");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("ZZZ","update mal");
                Log.v("ZZZ",t.getLocalizedMessage());
            }
        });
    }

    public void insertReparacion(Reparacion reparacion){
        Call<Long> callinsert = reparacionClient.postReparacion(reparacion);
        callinsert.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                longMutableLiveDataInsertarReparacion.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    public void deleteReparacion(long id){
        Call<Integer> callDelete = reparacionClient.deleteReparacion(id);
        callDelete.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("ZZZ","bien");
                longMutableLiveDataDelete.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("ZZZ",t.getLocalizedMessage());
            }
        });
    }
    public void getMovil(long id){
        Call<Movil>call = movilClient.getMovil(id);
        call.enqueue(new Callback<Movil>() {
            @Override
            public void onResponse(Call<Movil> call, Response<Movil> response) {
                movilMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movil> call, Throwable t) {

            }
        });
    }




    public MutableLiveData<Movil> getMovilMutableLiveData() {
        return movilMutableLiveData;
    }

    public void setMovilMutableLiveData(MutableLiveData<Movil> movilMutableLiveData) {
        this.movilMutableLiveData = movilMutableLiveData;
    }

    public MutableLiveData<Long> getLongMutableLiveDataInsertarReparacion() {
        return longMutableLiveDataInsertarReparacion;
    }

    public void setLongMutableLiveDataInsertarReparacion(MutableLiveData<Long> longMutableLiveDataInsertarReparacion) {
        this.longMutableLiveDataInsertarReparacion = longMutableLiveDataInsertarReparacion;
    }

    public Movil getMovilRepair() {
        return movilRepair;
    }

    public void setMovilRepair(Movil movilRepair) {
        this.movilRepair = movilRepair;
    }

    public MutableLiveData<List<Movil>> getListMutableLiveDataMoviles() {
        return listMutableLiveDataMoviles;
    }

    public void setListMutableLiveDataMoviles(MutableLiveData<List<Movil>> listMutableLiveDataMoviles) {
        this.listMutableLiveDataMoviles = listMutableLiveDataMoviles;
    }

    public MutableLiveData<List<Reparacion>> getListMutableLiveDataReparacion() {
        return listMutableLiveDataReparacion;
    }

    public void setListMutableLiveDataReparacion(MutableLiveData<List<Reparacion>> listMutableLiveDataReparacion) {
        this.listMutableLiveDataReparacion = listMutableLiveDataReparacion;
    }

    public MutableLiveData<Long> getLongMutableLiveDataInsertar() {
        return longMutableLiveDataInsertar;
    }

    public void setLongMutableLiveDataInsertar(MutableLiveData<Long> longMutableLiveDataInsertar) {
        this.longMutableLiveDataInsertar = longMutableLiveDataInsertar;
    }

    public Movil getMovilEditar() {
        return movilEditar;
    }

    public void setMovilEditar(Movil movilEditar) {
        this.movilEditar = movilEditar;
    }

    public Movil getMovilVer() {
        return movilVer;
    }

    public void setMovilVer(Movil movilVer) {
        this.movilVer = movilVer;
    }

    public MutableLiveData<Integer> getLongMutableLiveDataDelete() {
        return longMutableLiveDataDelete;
    }

    public void setLongMutableLiveDataDelete(MutableLiveData<Integer> longMutableLiveDataDelete) {
        this.longMutableLiveDataDelete = longMutableLiveDataDelete;
    }
}
