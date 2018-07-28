package com.integrador.grupo2android.proyectointegrador.Controlador;


import android.content.Context;

import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAORoom;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.FavoritoMovie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.FavoritoSerie;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;

import java.util.List;

public class ControladorFavoritos {

    private Context context;

    public ControladorFavoritos(Context context) {
        this.context = context;
    }

    //CONTROLADOR ROOM

    //ROOM PELICULAS

    public List<FavoritoMovie> getFavoritoMovie() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.getAllFavoritoMovie();
    }

    public void addFavoritoARoom(String id, String modo) {
        DAORoom dataBase = new DAORoom(context);

        if (modo.equals(Constantes.PELICULAS)) {
            FavoritoMovie favoritoMovie = new FavoritoMovie(id);
            dataBase.insertFavoritoMovieAll(favoritoMovie);
        } else {
            FavoritoSerie favoritoSerie = new FavoritoSerie(id);
            dataBase.insertFavoritoSerieAll(favoritoSerie);
        }
    }

    public void addFavoritoMovieARoom(List<FavoritoMovie> favoritoMovies) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertFavoritoMovieAll(favoritoMovies);
    }

    public void addFavoritoSerieARoom(List<FavoritoSerie> favoritoSeries) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertFavoritoSerieAll(favoritoSeries);
    }


    public void removeFavorito(String id, String modo) {
        DAORoom dataBase = new DAORoom(context);
        if (modo.equals(Constantes.PELICULAS)) {
            dataBase.deleteFavoritoMovie(id);
        } else {
            dataBase.deleteFavoritoSerie(id);
        }
    }

    public List<FavoritoMovie> obtenerFavoritoDeLasMovieFavorito() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.obtenerFavoritoMovieDeLasMovieFavorito();

    }

    public List<FavoritoSerie> obtenerFavoritoDeLasSerieFavorito() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.obtenerFavoritoSerieDeLasSerieFavorito();
    }


    public FavoritoMovie consultaSiEstaFavoritoMovieId(String id) {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.consultaSiEstaFavoritoMovieId(id);
    }

    public FavoritoSerie consultaSiEstaFavoritoSerieId(String id) {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.consultaSiEstaFavoritoSerieId(id);

    }


}

