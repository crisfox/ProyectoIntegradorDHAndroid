package com.integrador.grupo2android.proyectointegrador.Controlador;


import android.content.Context;

import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAOInternet;
import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAORoom;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeVideos;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.FavoritoMovie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Genero;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesMovie;
import com.integrador.grupo2android.proyectointegrador.Util.HTTPConnectionManager;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.CARTELERA;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.LISTA_RANKING_PELICULAS;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.PELICULAS;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.PROXIMOS_ESTRENOS;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.RANKING_PELICULAS_POSICION_DOS;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.RANKING_PELICULAS_POSICION_TRES;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.RANKING_PELICULAS_POSICION_UNO;

public class ControladorPeliculas {

    private DAOInternet daoInternet = new DAOInternet();

    private Context context;


    public ControladorPeliculas(Context context) {
        this.context = context;
    }

    public void obtenerPeliculasRanking(final ResultListener<List<Movie>> escuchadorVista, String language, Integer page) {
        //Creo escuchador del controlador
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Movie>> escuchadorControlador = new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> resultado) {
                    if (resultado != null && resultado.size() > 0) {
                        Movie movie1 = resultado.get(0);
                        Movie movie2 = resultado.get(1);
                        Movie movie3 = resultado.get(2);

                        movie1.setPosicionAMostrar(3);
                        movie2.setPosicionAMostrar(2);
                        movie3.setPosicionAMostrar(1);

                        List<Movie> listaDeRankingAMostrar = new ArrayList<>();
                        for (Movie movie : resultado) {
                            movie.setLugarAMostrar(LISTA_RANKING_PELICULAS);
                            listaDeRankingAMostrar.add(movie);
                        }

                        DAORoom daoRoom = new DAORoom(context);
                        daoRoom.insertMovieAll(listaDeRankingAMostrar);
                        escuchadorVista.finish(listaDeRankingAMostrar);
                    }
                }
            };
            daoInternet.obtenerPeliculasRanking(escuchadorControlador, language, page);
        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Movie> listaDeMovies = daoRoom.getRankingMovies(LISTA_RANKING_PELICULAS);
            escuchadorVista.finish(listaDeMovies);
        }


    }

    public void obtenerPeliculasFiltradas(final ResultListener<List<Movie>> escuchadorVista, final Integer genres, Integer calification, Integer year, String language, Integer page) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Movie>> escuchadorControlador = new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> resultado) {
                    DAORoom daoRoom = new DAORoom(context);
                    List<Movie> listaACargar = new ArrayList<>();
                    for (Movie movie : resultado) {
                        movie.setIdGenero(genres);
                        listaACargar.add(movie);
                    }

                    daoRoom.insertMovieAll(listaACargar);
                    escuchadorVista.finish(resultado);
                }
            };
            daoInternet.obtenerPeliculasFiltrada(escuchadorControlador, genres, calification, language, year, page);
        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Movie> listaDeMovies = daoRoom.getAllMoviesPorGenero(genres);
            if (listaDeMovies.size() >0){
            escuchadorVista.finish(listaDeMovies);
            }
        }

    }

    public void searchPeliculasTexto(final ResultListener<List<Movie>> escuchadorVista, String language, String query, Integer page) {

        if (HTTPConnectionManager.isNetworkingOnline(context)){
            ResultListener<List<Movie>> escuchadorControlador = new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> resultado) {
                    DAORoom daoRoom = new DAORoom(context);
                    daoRoom.insertMovieAll(resultado);
                    escuchadorVista.finish(resultado);
                }
            };
            daoInternet.buscarPeliculasTexto(escuchadorControlador, language, query, page);
        }else {
            DAORoom daoRoom = new DAORoom(context);
            List<Movie> listaDeMovies = daoRoom.getAllMovies();

            List<Movie> listaAMostar = new ArrayList<>();
            String aguja = query;
            for (Movie movie : listaDeMovies) {
                String pajar = movie.getTitle();
                Pattern regex = Pattern.compile("\\b" + Pattern.quote(aguja) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher match = regex.matcher(pajar);
                if (match.find()) {
                    listaAMostar.add(movie);
                }
            }
            escuchadorVista.finish(listaAMostar);
        }
    }

    public void obtenerPeliculasEnCartelera(final ResultListener<List<Movie>> escuchadorVista, String language, Integer page) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {

            ResultListener<List<Movie>> escuchadorControlador = new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> resultado) {
                    if (resultado != null && resultado.size() > 0) {
                        List<Movie> listaParaRoom = new ArrayList<>();
                        for (Movie movie : resultado) {
                            movie.setLugarAMostrar(CARTELERA);
                            listaParaRoom.add(movie);
                        }

                        DAORoom daoRoom = new DAORoom(context);
                        daoRoom.insertMovieAll(listaParaRoom);
                        escuchadorVista.finish(resultado);
                    }
                }
            };
            daoInternet.obtenerPeliculasEnCartelera(escuchadorControlador, language, page);
        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Movie> listaDeMovies = daoRoom.getMovieCartelera(CARTELERA);
            escuchadorVista.finish(listaDeMovies);
        }

    }

    public void obtenerProximasPeliculas(final ResultListener<List<Movie>> escuchadorVista, String langugage, Integer page) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {

            ResultListener<List<Movie>> escuchadorControlador = new ResultListener<List<Movie>>() {
                @Override
                public void finish(List<Movie> resultado) {
                    if (resultado != null && resultado.size() > 0) {
                        List<Movie> listaParaRoom = new ArrayList<>();
                        for (Movie movie : resultado) {
                            movie.setLugarAMostrar(PROXIMOS_ESTRENOS);
                            listaParaRoom.add(movie);
                        }
                        DAORoom daoRoom = new DAORoom(context);
                        daoRoom.insertMovieAll(listaParaRoom);
                        escuchadorVista.finish(resultado);
                    }
                }
            };
            daoInternet.obtenerProximasPeliculas(escuchadorControlador, langugage, page);
        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Movie> listaDeMovies = daoRoom.getMovieProximosEstrenos(PROXIMOS_ESTRENOS);
            escuchadorVista.finish(listaDeMovies);
        }
    }

    public void obtenerVideoPelicula(final ResultListener<ContenedorDeVideos> escuchadorVista, String idMovie, String langugage) {
        ResultListener<ContenedorDeVideos> escuchadorControlador = new ResultListener<ContenedorDeVideos>() {
            @Override
            public void finish(ContenedorDeVideos resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoInternet.obtenerVideoPelicula(escuchadorControlador, idMovie, langugage);
    }

    public void obtenerUnaPelicula(final ResultListener<Movie> escuchadorVista, String idMovie, String langugage) {
        ResultListener<Movie> escuchadorControlador = new ResultListener<Movie>() {
            @Override
            public void finish(Movie resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoInternet.obtenerUnaPelicula(escuchadorControlador, idMovie, langugage);
    }

    public List<Movie> consultoListaPorVerDespues() {
        ControladorVerDespuesPeliculas controladorVerDespuesPeliculas = new ControladorVerDespuesPeliculas(context);
        List<VerDespuesMovie> listaVerDespuesMovie = controladorVerDespuesPeliculas.obtenerVerDespuesDeLasMovieVerDespues();

        List<Movie> listaAMostrar = new ArrayList<>();

        List<String> listDeIdsMoviesParaMostrar = new ArrayList<>();

        for (VerDespuesMovie verDespuesMovie : listaVerDespuesMovie) {
            listDeIdsMoviesParaMostrar.add(verDespuesMovie.getIdMovie());
        }
        List<Movie> listaDeMovies = getMovies();

        for (Movie movie : listaDeMovies) {
            if (listDeIdsMoviesParaMostrar.contains(movie.getId().toString())) {
                listaAMostrar.add(movie);
            }
        }
        return listaAMostrar;
    }

    public void obtenerListadoDeGenerosPelicula(final ResultListener<List<Genero>> escuchadorVista, String language) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {

            final ResultListener<List<Genero>> escuchadorControlador = new ResultListener<List<Genero>>() {
                @Override
                public void finish(List<Genero> resultado) {
                    List<Genero> listaParaRoom = new ArrayList<>();
                    for (Genero genero : resultado) {
                        genero.setTipoDeGenero(PELICULAS);
                        listaParaRoom.add(genero);
                    }
                    DAORoom daoRoom = new DAORoom(context);
                    daoRoom.insertGeneroAll(listaParaRoom);
                    escuchadorVista.finish(resultado);

                }
            };
            daoInternet.obtenerListadoDeGenerosPelicula(escuchadorControlador, language);
        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Genero> listaDeGenero = daoRoom.getGenero(PELICULAS);
            escuchadorVista.finish(listaDeGenero);
        }
    }

    public List<Movie> consultoListaPorFavorito() {
        ControladorFavoritos controladorFavoritos = new ControladorFavoritos(context);
        List<FavoritoMovie> listaFavoritoMovie = controladorFavoritos.obtenerFavoritoDeLasMovieFavorito();

        List<Movie> listaAMostrar = new ArrayList<>();

        List<String> listDeIdsMoviesParaMostrar = new ArrayList<>();

        for (FavoritoMovie favoritoMovie : listaFavoritoMovie) {
            listDeIdsMoviesParaMostrar.add(favoritoMovie.getIdMovie());
        }

        List<Movie> listaDeMovies = getMovies();

        for (Movie movie : listaDeMovies) {
            if (listDeIdsMoviesParaMostrar.contains(movie.getId().toString())) {
                listaAMostrar.add(movie);
            }
        }
        return listaAMostrar;
    }


    //CONTROLADOR ROOM

    //ROOM PELICULAS

    public List<Movie> getMovies() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.getAllMovies();
    }

    public void addMovieARoom(Movie... movie) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertMovieAll(movie);
    }

    public void addMovieARoom(List<Movie> movie) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertMovieAll(movie);
    }


    public void removeMovie(Movie movie) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.delete(movie);
    }

    public Movie getMovieTitle(String title) {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.getMovieTitle(title);
    }
}

