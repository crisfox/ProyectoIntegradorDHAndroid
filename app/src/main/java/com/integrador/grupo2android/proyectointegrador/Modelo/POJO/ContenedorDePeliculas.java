package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContenedorDePeliculas {

    @SerializedName("results")
    private List<Movie> movies;


    public ContenedorDePeliculas(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
