package com.integrador.grupo2android.proyectointegrador.Controlador;


import android.content.Context;

import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAORoom;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesMovie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesSerie;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;

import java.util.List;

public class ControladorVerDespuesPeliculas {

    private Context context;

    public ControladorVerDespuesPeliculas(Context context) {
        this.context = context;
    }

    //CONTROLADOR ROOM

    //ROOM PELICULAS

    public List<VerDespuesMovie> getVerDespuesMovie() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.getAllVerDespuesMovies();
    }

    public void addVerDespuesARoom(String id, String modo) {
        DAORoom dataBase = new DAORoom(context);

        if (modo.equals(Constantes.PELICULAS)) {
            VerDespuesMovie verDespuesMovie = new VerDespuesMovie(id);
            dataBase.insertVerDespuesAll(verDespuesMovie);
        } else {
            VerDespuesSerie verDespuesSerie = new VerDespuesSerie(id);
            dataBase.insertVerDespuesAll(verDespuesSerie);
        }
    }

    public void addVerDespuesMovieARoom(List<VerDespuesMovie> verDespuesMovies) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertVerDespuesMovieAll(verDespuesMovies);
    }

    public void addVerDespuesSerieARoom(List<VerDespuesSerie> verDespuesSeries) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertVerDespuesSerieAll(verDespuesSeries);
    }


    public void removeVerDespues(String id, String modo) {
        DAORoom dataBase = new DAORoom(context);
        if (modo.equals(Constantes.PELICULAS)) {
            dataBase.deleteVerDespuesMovie(id);
        } else {
            dataBase.deleteVerDespuesSerie(id);
        }
    }

    public List<VerDespuesMovie> obtenerVerDespuesDeLasMovieVerDespues() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.obtenerVerDespuesDeLasMovieVerDespues();

    }

    public List<VerDespuesSerie> obtenerVerDespuesDeLasSerieVerDespues() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.obtenerVerDespuesDeLasSerieVerDespues();
    }


    public VerDespuesSerie consultaSiEstaVerDespuesSerieId(String id) {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.consultaSiEstaVerDespuesSerieId(id);

    }

    public VerDespuesMovie consultaSiEstaVerDespuesMovieId(String id) {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.consultaSiEstaVerDespuesMovieId(id);
    }

}

