package com.integrador.grupo2android.proyectointegrador.Controlador;


import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAOInternet;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDePeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ControladorPeliculas {

    public void obtenerPeliculas(final ResultListener<Movie> escuchadorVista) {
        //Creo escuchador del controlador

        ResultListener<Movie> escuchadorControlador = new ResultListener<Movie>() {
            @Override
            public void finish(Movie resultado) {
                escuchadorVista.finish(resultado);
            }
        };

        DAOInternet daoInternet = new DAOInternet();
        daoInternet.obtenerPeliculasInternet(escuchadorControlador);


    }

    public void obtenerListaPeliculas(final ResultListener<List<Movie>> escuchadorVista) {
        //Creo escuchador del controlador

        ResultListener<List<Movie>> escuchadorControlador = new ResultListener<List<Movie>>() {
            @Override
            public void finish(List<Movie> resultado) {
                escuchadorVista.finish(resultado);
            }
        };

        DAOInternet daoInternet = new DAOInternet();
        daoInternet.obtenerListaPeliculasInternet(escuchadorControlador);


    }
}

