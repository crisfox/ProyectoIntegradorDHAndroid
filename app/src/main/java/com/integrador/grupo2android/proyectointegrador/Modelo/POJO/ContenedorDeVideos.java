package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorDeVideos {

    @SerializedName("results")
    private List<Video> listaDeVideos;

    public ContenedorDeVideos(List<Video> listaDeVideos) {
        this.listaDeVideos = listaDeVideos;
    }

    public List<Video> getListaDeVideos() {
        return listaDeVideos;
    }

    public void setListaDeVideos(List<Video> listaDeVideos) {
        this.listaDeVideos = listaDeVideos;
    }
}
