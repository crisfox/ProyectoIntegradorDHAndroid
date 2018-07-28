package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.FavoritoMovie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.FavoritoSerie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Genero;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Tv;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesMovie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesSerie;
import com.integrador.grupo2android.proyectointegrador.Util.RoomAppDatabase;

import java.util.List;

/**
 * Created by Cristian on 21/7/2018.
 */

public class DAORoom {
    RoomAppDatabase roomAppDatabase;
    private List<Movie> carteleraMovie;

//MOVIE

    public DAORoom(Context context) {
        roomAppDatabase = Room.databaseBuilder(context,
                RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    public List<Movie> getAllMovies() {
        return roomAppDatabase.daoRoomMovie().getAllMovie();
    }

    public List<Movie> getRankingMovies(String lugarAMostrar) {
        return roomAppDatabase.daoRoomMovie().getRankingMovies(lugarAMostrar);
    }

    public Movie getMovieTitle(String title) {
        return roomAppDatabase.daoRoomMovie().getMovieTitle(title);
    }

    public void insertMovieAll(Movie... movies) {
        roomAppDatabase.daoRoomMovie().insertMovieAll(movies);
    }

    public void insertMovieAll(List<Movie> movies) { //Movie
        roomAppDatabase.daoRoomMovie().insertMovieAll(movies);
    }

    public void delete(Movie movies) {
        roomAppDatabase.daoRoomMovie().delete(movies);
    }


//VER DESPUES MOVIE

    public List<VerDespuesMovie> getAllVerDespuesMovies() {
        return roomAppDatabase.daoRoomVerDespuesMovieInterface().getAllVerDespuesMovie();
    }

    public List<VerDespuesMovie> obtenerVerDespuesDeLasMovieVerDespues() {
        return roomAppDatabase.daoRoomVerDespuesMovieInterface().getVerDespuesMovie();

    }

    public void insertVerDespuesAll(VerDespuesMovie... verDespuesMovies) {
        roomAppDatabase.daoRoomVerDespuesMovieInterface().insertVerDespuesAll(verDespuesMovies);
    }

    public void insertVerDespuesAll(VerDespuesSerie... verDespuesSeries) {
        roomAppDatabase.daoRoomVerDespuesSerieInterface().insertVerDespuesAll(verDespuesSeries);
    }

    public void insertVerDespuesMovieAll(List<VerDespuesMovie> verDespuesMovies) {
        roomAppDatabase.daoRoomVerDespuesMovieInterface().insertVerDespuesAll(verDespuesMovies);
    }

    public void insertVerDespuesSerieAll(List<VerDespuesSerie> verDespuesSeries) {
        roomAppDatabase.daoRoomVerDespuesSerieInterface().insertVerDespuesAll(verDespuesSeries);
    }

    public void deleteVerDespuesMovie(String id) {
        roomAppDatabase.daoRoomVerDespuesMovieInterface().delete(id);
    }

    public VerDespuesMovie consultaSiEstaVerDespuesMovieId(String id) {
        return roomAppDatabase.daoRoomVerDespuesMovieInterface().traemeTodasVerDespuesMovieConMismoIdMovie(id);
    }


    //FAVORITO MOVIE

    public List<FavoritoMovie> getAllFavoritoMovie() {
        return roomAppDatabase.daoRoomFavoritoMovieInterface().getAllFavoritoMovie();
    }

    public List<FavoritoMovie> obtenerFavoritoMovieDeLasMovieFavorito() {
        return roomAppDatabase.daoRoomFavoritoMovieInterface().getFavoritoMovie();

    }

    public void insertFavoritoMovieAll(FavoritoMovie... favoritoMovies) {
        roomAppDatabase.daoRoomFavoritoMovieInterface().insertFavoritoAll(favoritoMovies);
    }

    public void insertFavoritoMovieAll(List<FavoritoMovie> favoritoMovies) {
        roomAppDatabase.daoRoomFavoritoMovieInterface().insertFavoritoAll(favoritoMovies);
    }

    public void insertVFavoritoMovieAll(List<FavoritoMovie> favoritoMovies) {
        roomAppDatabase.daoRoomFavoritoMovieInterface().insertFavoritoAll(favoritoMovies);
    }

    public void deleteFavoritoMovie(String id) {
        roomAppDatabase.daoRoomFavoritoMovieInterface().delete(id);
    }

    public FavoritoMovie consultaSiEstaFavoritoMovieId(String id) {
        return roomAppDatabase.daoRoomFavoritoMovieInterface().traemeTodasFavoritoMovieConMismoIdMovie(id);
    }


//SERIE


    public List<Tv> getAllSeries() {
        return roomAppDatabase.daoRoomSerie().getAllSerie();
    }

    public List<Tv> getRankingSeries(String ranking) {
        return roomAppDatabase.daoRoomSerie().getRankingSerie(ranking);
    }

    public Tv getSerieTitle(String title) {
        return roomAppDatabase.daoRoomSerie().getSerieTitle(title);
    }

    public void insertSerieAll(Tv... series) {
        roomAppDatabase.daoRoomSerie().insertSerieAll(series);
    }

    public void insertSerieAll(List<Tv> series) { //Movie
        roomAppDatabase.daoRoomSerie().insertSerieAll(series);
    }

    public void delete(Tv movies) {
        roomAppDatabase.daoRoomSerie().delete(movies);
    }


//VER DESPUES SERIE

    public VerDespuesSerie consultaSiEstaVerDespuesSerieId(String id) {
        return roomAppDatabase.daoRoomVerDespuesSerieInterface().traemeTodasVerDespuesSerieConMismoIdSerie(id);
    }

    public List<VerDespuesSerie> obtenerVerDespuesDeLasSerieVerDespues() {
        return roomAppDatabase.daoRoomVerDespuesSerieInterface().getVerDespuesSerie();

    }

    public void deleteVerDespuesSerie(String id) {
        roomAppDatabase.daoRoomVerDespuesSerieInterface().delete(id);
    }


//FAVORITO Serie

    public List<FavoritoSerie> getAllFavoritoSerie() {
        return roomAppDatabase.daoRoomFavoritoSerieInterface().getAllFavoritoSerie();
    }

    public List<FavoritoSerie> obtenerFavoritoSerieDeLasSerieFavorito() {
        return roomAppDatabase.daoRoomFavoritoSerieInterface().getFavoritoSerie();

    }

    public void insertFavoritoSerieAll(FavoritoSerie... favoritoSerie) {
        roomAppDatabase.daoRoomFavoritoSerieInterface().insertFavoritoAll(favoritoSerie);
    }

    public void insertFavoritoSerieAll(List<FavoritoSerie> favoritoSerie) {
        roomAppDatabase.daoRoomFavoritoSerieInterface().insertFavoritoAll(favoritoSerie);
    }

    public void deleteFavoritoSerie(String id) {
        roomAppDatabase.daoRoomFavoritoSerieInterface().delete(id);
    }

    public FavoritoSerie consultaSiEstaFavoritoSerieId(String id) {
        return roomAppDatabase.daoRoomFavoritoSerieInterface().traemeTodasFavoritoSerieConMismoIdSerie(id);
    }

    public List<Movie> getMovieCartelera(String lugar) {
        return roomAppDatabase.daoRoomMovie().getMovieCartelera(lugar);
    }

    public List<Movie> getMovieProximosEstrenos(String lugar) {
        return roomAppDatabase.daoRoomMovie().getMovieProximosEstrenos(lugar);
    }

    public void insertGeneroAll(List<Genero> generos) {
        roomAppDatabase.daoRoomGeneroInterface().insertGeneroAll(generos);
    }

    public void insertGeneroAll(Genero... generos) {
        roomAppDatabase.daoRoomGeneroInterface().insertGeneroAll(generos);
    }

    public List<Genero> getGenero(String tipo) {
        return roomAppDatabase.daoRoomGeneroInterface().getGeneroTipo(tipo);
    }

    public List<Movie> getAllMoviesPorGenero(Integer genres) {
        return roomAppDatabase.daoRoomMovie().getAllMoviePorGenero(genres);
    }

    public List<Tv> getAllSeriesPorGenero(Integer genres) {
        return roomAppDatabase.daoRoomSerie().getAllSeriePorGenero(genres);
    }
}
