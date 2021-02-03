package com.example.practica3retrofitmoviles.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movil {


    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("modelo")
    @Expose
    private String modelo;
    @SerializedName("descripcion_movil")
    @Expose
    private String descripcionMovil;
    @SerializedName("precio_movil")
    @Expose
    private Double precioMovil;
    @SerializedName("numero_reparaciones")
    @Expose
    private int numeroReparaciones;


    public Movil(long id, String url, String marca, String modelo, String descripcionMovil, Double precioMovil, int numeroReparaciones) {
        this.id = id;
        this.url = url;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcionMovil = descripcionMovil;
        this.precioMovil = precioMovil;
        this.numeroReparaciones = numeroReparaciones;
    }

    public Movil() {
        this(0,"","","","",0.0,0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcionMovil() {
        return descripcionMovil;
    }

    public void setDescripcionMovil(String descripcionMovil) {
        this.descripcionMovil = descripcionMovil;
    }

    public Double getPrecioMovil() {
        return precioMovil;
    }

    public void setPrecioMovil(Double precioMovil) {
        this.precioMovil = precioMovil;
    }

    public int getNumeroReparaciones() {
        return numeroReparaciones;
    }

    public void setNumeroReparaciones(int numeroReparaciones) {
        this.numeroReparaciones = numeroReparaciones;
    }

    @Override
    public String toString() {
        return "Movil{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", descripcionMovil='" + descripcionMovil + '\'' +
                ", precioMovil=" + precioMovil +
                ", numeroReparaciones=" + numeroReparaciones +
                '}';
    }
}
