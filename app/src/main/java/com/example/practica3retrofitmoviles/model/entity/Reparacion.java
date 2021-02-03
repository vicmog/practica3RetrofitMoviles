package com.example.practica3retrofitmoviles.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reparacion {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("idMovil")
    @Expose
    private long idMovil;
    @SerializedName("descripcion_reparacion")
    @Expose
    private String descripcionReparacion;
    @SerializedName("precio_reparacion")
    @Expose
    private Double precioReparacion;


    public Reparacion(long id, long idMovil, String descripcionReparacion, Double precioReparacion) {
        this.id = id;
        this.idMovil = idMovil;
        this.descripcionReparacion = descripcionReparacion;
        this.precioReparacion = precioReparacion;
    }

    public Reparacion() {
        this(0,0,"",0.0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdMovil() {
        return idMovil;
    }

    public void setIdMovil(long idMovil) {
        this.idMovil = idMovil;
    }

    public String getDescripcionReparacion() {
        return descripcionReparacion;
    }

    public void setDescripcionReparacion(String descripcionReparacion) {
        this.descripcionReparacion = descripcionReparacion;
    }

    public Double getPrecioReparacion() {
        return precioReparacion;
    }

    public void setPrecioReparacion(Double precioReparacion) {
        this.precioReparacion = precioReparacion;
    }

    @Override
    public String toString() {
        return "Reparacion{" +
                "id=" + id +
                ", idMovil=" + idMovil +
                ", descripcionReparacion='" + descripcionReparacion + '\'' +
                ", precioReparacion=" + precioReparacion +
                '}';
    }
}
