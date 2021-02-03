  package com.example.practica3retrofitmoviles.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.practica3retrofitmoviles.model.Repository;
import com.example.practica3retrofitmoviles.model.entity.Movil;
import com.example.practica3retrofitmoviles.model.entity.Reparacion;

import java.util.List;

public class ViewModel extends AndroidViewModel {

private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }


    public void getAllMoviles() {
        repository.getAllMoviles();
    }

    public MutableLiveData<List<Movil>> getListMutableLiveDataMoviles() {
        return repository.getListMutableLiveDataMoviles();
    }

    public void setListMutableLiveDataMoviles(MutableLiveData<List<Movil>> listMutableLiveDataMoviles) {
        repository.setListMutableLiveDataMoviles(listMutableLiveDataMoviles);
    }

    public void getAllReparaciones() {
        repository.getAllReparaciones();
    }

    public MutableLiveData<List<Reparacion>> getListMutableLiveDataReparacion() {
        return repository.getListMutableLiveDataReparacion();
    }

    public void setListMutableLiveDataReparacion(MutableLiveData<List<Reparacion>> listMutableLiveDataReparacion) {
        repository.setListMutableLiveDataReparacion(listMutableLiveDataReparacion);
    }


    public void deleteMovil(long id) {
        repository.deleteMovil(id);
    }

    public void addMovil(Movil movil) {
        repository.addMovil(movil);
    }

    public MutableLiveData<Long> getLongMutableLiveDataInsertar() {
        return repository.getLongMutableLiveDataInsertar();
    }

    public void setLongMutableLiveDataInsertar(MutableLiveData<Long> longMutableLiveDataInsertar) {
        repository.setLongMutableLiveDataInsertar(longMutableLiveDataInsertar);
    }

    public Movil getMovilEditar() {
        return repository.getMovilEditar();
    }

    public void setMovilEditar(Movil movilEditar) {
        repository.setMovilEditar(movilEditar);
    }

    public void updateMovil(long id, Movil movil) {
        repository.updateMovil(id, movil);
    }

    public Movil getMovilVer() {
        return repository.getMovilVer();
    }

    public void setMovilVer(Movil movilVer) {
        repository.setMovilVer(movilVer);
    }

    public Movil getMovilRepair() {
        return repository.getMovilRepair();
    }

    public void setMovilRepair(Movil movilRepair) {
        repository.setMovilRepair(movilRepair);
    }

    public void insertReparacion(Reparacion reparacion) {
        repository.insertReparacion(reparacion);
    }

    public MutableLiveData<Long> getLongMutableLiveDataInsertarReparacion() {
        return repository.getLongMutableLiveDataInsertarReparacion();
    }

    public void setLongMutableLiveDataInsertarReparacion(MutableLiveData<Long> longMutableLiveDataInsertarReparacion) {
        repository.setLongMutableLiveDataInsertarReparacion(longMutableLiveDataInsertarReparacion);
    }

    public void deleteReparacion(long id) {
        repository.deleteReparacion(id);
    }

    public void getMovil(long id) {
        repository.getMovil(id);
    }

    public MutableLiveData<Movil> getMovilMutableLiveData() {
        return repository.getMovilMutableLiveData();
    }

    public void setMovilMutableLiveData(MutableLiveData<Movil> movilMutableLiveData) {
        repository.setMovilMutableLiveData(movilMutableLiveData);
    }

    public MutableLiveData<Integer> getLongMutableLiveDataDelete() {
        return repository.getLongMutableLiveDataDelete();
    }

    public void setLongMutableLiveDataDelete(MutableLiveData<Integer> longMutableLiveDataDelete) {
        repository.setLongMutableLiveDataDelete(longMutableLiveDataDelete);
    }
}
