package com.integrador.grupo2android.proyectointegrador.Controlador;


import android.content.Context;

import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAOInternet;
import com.integrador.grupo2android.proyectointegrador.Modelo.DAO.DAORoom;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeVideos;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.FavoritoSerie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Genero;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Tv;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesSerie;
import com.integrador.grupo2android.proyectointegrador.Util.HTTPConnectionManager;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.LISTA_RANKING_SERIES;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.SERIES;

public class ControladorSeries {
    private DAOInternet daoInternet = new DAOInternet();
    private Context context;

    public ControladorSeries(Context context) {
        this.context = context;
    }

    public void obtenerSeriesRanking(final ResultListener<List<Tv>> escuchadorVista, String language, Integer page) {

        //Creo escuchador del controlador
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Tv>> escuchadorControlador = new ResultListener<List<Tv>>() {
                @Override
                public void finish(List<Tv> resultado) {
                    if (resultado != null && resultado.size() > 0) {


                        Tv serie1 = resultado.get(0);
                        Tv serie2 = resultado.get(1);
                        Tv serie3 = resultado.get(2);

                        serie1.setPosicionAMostrar(3);
                        serie2.setPosicionAMostrar(2);
                        serie3.setPosicionAMostrar(1);

                        List<Tv> listaDeRankingAMostrar = new ArrayList<>();
                        for (Tv serie : resultado) {
                            serie.setLugarAMostrar(LISTA_RANKING_SERIES);
                            listaDeRankingAMostrar.add(serie);
                        }

                        DAORoom daoRoom = new DAORoom(context);
                        daoRoom.insertSerieAll(listaDeRankingAMostrar);
                        escuchadorVista.finish(listaDeRankingAMostrar);
                    }
                }
            };
            daoInternet.obtenerSeriesRanking(escuchadorControlador, language, page);

        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Tv> listaDeSeries = daoRoom.getRankingSeries(LISTA_RANKING_SERIES);
            escuchadorVista.finish(listaDeSeries);
        }
    }

    public void obtenerSeriesFiltradas(final ResultListener<List<Tv>> escuchadorVista, final Integer genres, Integer calification, String language, Integer page) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            ResultListener<List<Tv>> escuchadorControlador = new ResultListener<List<Tv>>() {
                @Override
                public void finish(List<Tv> resultado) {
                    DAORoom daoRoom = new DAORoom(context);
                    List<Tv> listaACargar = new ArrayList<>();
                    for (Tv serie : resultado) {
                        serie.setIdGenero(genres);
                        listaACargar.add(serie);
                    }

                    daoRoom.insertSerieAll(listaACargar);
                    escuchadorVista.finish(resultado);
                }
            };
            daoInternet.obtenerSeriesFiltrada(escuchadorControlador, genres, calification, language, page);
        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Tv> listaDeMovies = daoRoom.getAllSeriesPorGenero(genres);
            if (listaDeMovies.size() > 0) {
                escuchadorVista.finish(listaDeMovies);
            }
        }

    }

    public void searchSeriesText(final ResultListener<List<Tv>> escuchadorVista, String language, String query, Integer page) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {

            ResultListener<List<Tv>> escuchadorControlador = new ResultListener<List<Tv>>() {
                @Override
                public void finish(List<Tv> resultado) {
                    DAORoom daoRoom = new DAORoom(context);
                    daoRoom.insertSerieAll(resultado);
                    escuchadorVista.finish(resultado);
                }
            };
            daoInternet.buscarSeriesTexto(escuchadorControlador, language, query, page);

        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Tv> listaDeSeries = daoRoom.getAllSeries();

            String aguja = query;
            List<Tv> listaAMostar = new ArrayList<>();
            for (Tv serie : listaDeSeries) {
                String pajar = serie.getName();
                Pattern regex = Pattern.compile("\\b" + Pattern.quote(aguja) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher match = regex.matcher(pajar);
                if (match.find()) {
                    listaAMostar.add(serie);
                }
            }
            escuchadorVista.finish(listaAMostar);
        }
    }

    public void obtenerVideoTv(final ResultListener<ContenedorDeVideos> escuchadorVista, String idTv, String language) {
        ResultListener<ContenedorDeVideos> escuchadorControlador = new ResultListener<ContenedorDeVideos>() {
            @Override
            public void finish(ContenedorDeVideos resultado) {
                escuchadorVista.finish(resultado);
            }
        };
        daoInternet.obtenerVideoSerie(escuchadorControlador, idTv, language);
    }

    public List<Tv> consultoListaPorVerDespues() {
        ControladorVerDespuesPeliculas controladorVerDespuesPeliculas = new ControladorVerDespuesPeliculas(context);
        List<VerDespuesSerie> listaVerDespuesSerie = controladorVerDespuesPeliculas.obtenerVerDespuesDeLasSerieVerDespues();

        List<Tv> listaAMostrar = new ArrayList<>();

        List<String> listDeIdsMoviesParaMostrar = new ArrayList<>();

        for (VerDespuesSerie verDespuesSerie : listaVerDespuesSerie) {
            listDeIdsMoviesParaMostrar.add(verDespuesSerie.getIdSerie());
        }
        List<Tv> listaDeSeries = getSeries();
        for (Tv serie : listaDeSeries) {
            if (listDeIdsMoviesParaMostrar.contains(serie.getId().toString())) {
                listaAMostrar.add(serie);
            }
        }
        return listaAMostrar;
    }

    public List<Tv> consultoListaPorFavorito() {
        ControladorFavoritos controladorFavoritos = new ControladorFavoritos(context);
        List<FavoritoSerie> listaFavoritoSerie = controladorFavoritos.obtenerFavoritoDeLasSerieFavorito();

        List<Tv> listaAMostrar = new ArrayList<>();

        List<String> listDeIdsMoviesParaMostrar = new ArrayList<>();

        for (FavoritoSerie favoritoSerie : listaFavoritoSerie) {
            listDeIdsMoviesParaMostrar.add(favoritoSerie.getIdSerie());
        }
        List<Tv> listaDeSeries = getSeries();
        for (Tv serie : listaDeSeries) {
            if (listDeIdsMoviesParaMostrar.contains(serie.getId().toString())) {
                listaAMostrar.add(serie);
            }
        }
        return listaAMostrar;
    }


    public List<Tv> getSeries() {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.getAllSeries();
    }


    public void addSerieARoom(Tv... serie) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertSerieAll(serie);
    }

    public void addSerieARoom(List<Tv> series) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.insertSerieAll(series);
    }


    public void removeTv(Tv movie) {
        DAORoom dataBase = new DAORoom(context);
        dataBase.delete(movie);
    }

    public Tv getTvTitle(String title) {
        DAORoom dataBase = new DAORoom(context);
        return dataBase.getSerieTitle(title);
    }

    public void obtenerListadoDeGenerosSeries(final ResultListener<List<Genero>> escuchadorVista, String language) {
        if (HTTPConnectionManager.isNetworkingOnline(context)) {

            final ResultListener<List<Genero>> escuchadorControlador = new ResultListener<List<Genero>>() {
                @Override
                public void finish(List<Genero> resultado) {
                    List<Genero> listaParaRoom = new ArrayList<>();
                    for (Genero genero : resultado) {
                        genero.setTipoDeGenero(SERIES);
                        listaParaRoom.add(genero);
                    }
                    DAORoom daoRoom = new DAORoom(context);
                    daoRoom.insertGeneroAll(listaParaRoom);
                    escuchadorVista.finish(resultado);
                }
            };
            daoInternet.obtenerListadoDeGenerosSerie(escuchadorControlador, language);

        } else {
            DAORoom daoRoom = new DAORoom(context);
            List<Genero> listaDeGenero = daoRoom.getGenero(SERIES);
            escuchadorVista.finish(listaDeGenero);
        }
    }
}
