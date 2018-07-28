package com.integrador.grupo2android.proyectointegrador.Controlador;

import android.content.Context;

import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAOFirebaseUsuarios;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeUsuarios;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

import java.util.List;

/**
 * Created by Cristian on 18/7/2018.
 */

public class ControladorUsuarios {

    Context context;
    private DAOFirebaseUsuarios daoFirebaseUsuarios;

    //Peticiones
    public void obtenerUsuarios(final ResultListener<ContenedorDeUsuarios> escuchadorDeLaVista) {
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        ResultListener<ContenedorDeUsuarios> escuchadorDelControlador = new ResultListener<ContenedorDeUsuarios>() {
            @Override
            public void finish(ContenedorDeUsuarios resultado) {

                escuchadorDeLaVista.finish(resultado);
            }
        };
        daoFirebaseUsuarios.obtenerUsuariosDeFirebase(escuchadorDelControlador);
    }


    public void obtenerListaIdPeliculasVerDespues(final ResultListener<List<String>> escuchadorVista, String idUser) {
        ResultListener<List<String>> escuchadorControlador = new ResultListener<List<String>>() {
            @Override
            public void finish(List<String> resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        daoFirebaseUsuarios.obtenerListaVerDespuesPelicula(escuchadorControlador, idUser);
    }

    public void obtenerListaIdPeliculasFavoritas(final ResultListener<List<String>> escuchadorVista, String idUser) {
        ResultListener<List<String>> escuchadorControlador = new ResultListener<List<String>>() {
            @Override
            public void finish(List<String> resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        daoFirebaseUsuarios.obtenerListaFavoritosPelicula(escuchadorControlador, idUser);
    }

    public void obtenerListaIdSeriesVerDespues(final ResultListener<List<String>> escuchadorVista, String idUser) {
        ResultListener<List<String>> escuchadorControlador = new ResultListener<List<String>>() {
            @Override
            public void finish(List<String> resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        daoFirebaseUsuarios.obtenerListaVerDespuesSerie(escuchadorControlador, idUser);
    }

    public void obtenerListaIdSeriesFavoritas(final ResultListener<List<String>> escuchadorVista, String idUser) {
        ResultListener<List<String>> escuchadorControlador = new ResultListener<List<String>>() {
            @Override
            public void finish(List<String> resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        daoFirebaseUsuarios.obtenerListaFavoritosSerie(escuchadorControlador, idUser);
    }


    public void cargarAFirebaseVerDespues(String uid, String idVerDespues, String modo) {
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        if (modo.equals(Constantes.PELICULAS)) {
            daoFirebaseUsuarios.cargarAFirebaseVerDespuesMovie(uid, idVerDespues);
        } else {
            daoFirebaseUsuarios.cargarAFirebaseVerDespuesSerie(uid, idVerDespues);
        }

    }

    public void cargarAFirebaseFavorito(String uid, String idFavorito, String modo) {
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        if (modo.equals(Constantes.PELICULAS)) {
            daoFirebaseUsuarios.cargarAFirebaseFavoritoMovie(uid, idFavorito);
        } else {
            daoFirebaseUsuarios.cargarAFirebaseFavoritoSerie(uid, idFavorito);
        }
    }

    public void eliminarDeFirebaseVerDespues(String user, String id, String modo) {
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        if (modo.equals(Constantes.PELICULAS)) {
            daoFirebaseUsuarios.eliminarDeFirebaseVerDespuesMovie(user, id);
        } else {
            daoFirebaseUsuarios.eliminarDeFirebaseVerDespuesSerie(user, id);
        }
    }

    public void eliminarDeFirebaseFavorito(String uid, String idFavorito, String modo) {
        daoFirebaseUsuarios = new DAOFirebaseUsuarios();
        if (modo.equals(Constantes.PELICULAS)) {
            daoFirebaseUsuarios.eliminarDeFirebaseFavoritoMovie(uid, idFavorito);
        } else {
            daoFirebaseUsuarios.eliminarDeFirebaseFavoritoSerie(uid, idFavorito);
        }

    }
}
