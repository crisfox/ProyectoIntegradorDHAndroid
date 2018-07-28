package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorDeTv {

    @SerializedName("results")
    private List<Tv> tv;

    public ContenedorDeTv(List<Tv> listaTv) {
        this.tv = listaTv;
    }

    public List<Tv> getTv() {
        return tv;
    }

    public void setTv(List<Tv> tv) {
        this.tv = tv;
    }

}


