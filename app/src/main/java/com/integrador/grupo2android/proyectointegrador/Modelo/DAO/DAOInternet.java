package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeGeneros;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDePeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeTv;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeVideos;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Genero;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Tv;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Video;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;
import com.integrador.grupo2android.proyectointegrador.Util.TMDBHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOInternet {

    private Retrofit retrofit;
    private DAOServicePeliculas daoServicePeliculas;

    public DAOInternet() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.client(httpClient.build()).build();
        daoServicePeliculas = retrofit.create(DAOServicePeliculas.class);
    }

    public void obtenerPeliculasRanking(final ResultListener<List<Movie>> escuchadorControlador, String language, Integer page) {
        Call<ContenedorDePeliculas> escuchadorRetrofit = daoServicePeliculas.getTopRatedMovies(TMDBHelper.apiKey, language, page);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorDePeliculas = response.body();
                escuchadorControlador.finish(contenedorDePeliculas.getMovies());
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Movie>());
            }
        });
    }

    public void obtenerSeriesRanking(final ResultListener<List<Tv>> escuchadorControlador, String language, Integer page) {
        Call<ContenedorDeTv> escuchadorRetrofit = daoServicePeliculas.getTopRatedTv(TMDBHelper.apiKey, language, page);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDeTv>() {
            @Override
            public void onResponse(Call<ContenedorDeTv> call, Response<ContenedorDeTv> response) {
                ContenedorDeTv contenedorDeSeries = response.body();
                escuchadorControlador.finish(contenedorDeSeries.getTv());
            }

            @Override
            public void onFailure(Call<ContenedorDeTv> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Tv>());
            }
        });
    }

    public void obtenerPeliculasFiltrada(final ResultListener<List<Movie>> escuchadorControlado, Integer genres, Integer calification, String language, Integer year, Integer page) {
        Call<ContenedorDePeliculas> esuchadorRetrofit = daoServicePeliculas.getSearchMoviesFilter(TMDBHelper.apiKey, genres, language, calification, year, page);
        esuchadorRetrofit.enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorDePeliculas = response.body();
                escuchadorControlado.finish(contenedorDePeliculas.getMovies());
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                escuchadorControlado.finish(new ArrayList<Movie>());
            }
        });
    }

    public void obtenerSeriesFiltrada(final ResultListener<List<Tv>> escuchadorControlador, Integer genres, Integer calification, String language, Integer page) {
        Call<ContenedorDeTv> esuchadorRetrofit = daoServicePeliculas.getSearchTvFilter(TMDBHelper.apiKey, genres, language, page);
        esuchadorRetrofit.enqueue(new Callback<ContenedorDeTv>() {
            @Override
            public void onResponse(Call<ContenedorDeTv> call, Response<ContenedorDeTv> response) {
                ContenedorDeTv contenedorDeTv = response.body();
                escuchadorControlador.finish(contenedorDeTv.getTv());
            }

            @Override
            public void onFailure(Call<ContenedorDeTv> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Tv>());
            }
        });
    }

    public void buscarPeliculasTexto(final ResultListener<List<Movie>> escuchadorControlador, String language, String query, Integer page) {
        Call<ContenedorDePeliculas> escuchadorRetrofit = daoServicePeliculas.getSearchMovie(TMDBHelper.apiKey, language, query, page);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorDePeliculas = response.body();
                escuchadorControlador.finish(contenedorDePeliculas.getMovies());
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Movie>());
            }
        });
    }

    public void buscarSeriesTexto(final ResultListener<List<Tv>> escuchadorControlador, String language, String query, Integer page) {
        Call<ContenedorDeTv> escuchadorRetrofit = daoServicePeliculas.getSearchTv(TMDBHelper.apiKey, language, query, page);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDeTv>() {
            @Override
            public void onResponse(Call<ContenedorDeTv> call, Response<ContenedorDeTv> response) {
                ContenedorDeTv contenedorDeTv = response.body();
                escuchadorControlador.finish(contenedorDeTv.getTv());
            }

            @Override
            public void onFailure(Call<ContenedorDeTv> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Tv>());
            }
        });
    }

    public void obtenerPeliculasEnCartelera(final ResultListener<List<Movie>> escuchadorControlador, String language, Integer page) {
        Call<ContenedorDePeliculas> escuchadorRetrofit = daoServicePeliculas.getNowPlayingMovies(TMDBHelper.apiKey, language, page);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorDePeliculas = response.body();
                escuchadorControlador.finish(contenedorDePeliculas.getMovies());
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Movie>());
            }
        });
    }

    public void obtenerProximasPeliculas(final ResultListener<List<Movie>> escuchadorControlador, String language, Integer page) {
        Call<ContenedorDePeliculas> escuchadorRetrofit = daoServicePeliculas.getUpComingMovies(TMDBHelper.apiKey, language, page);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDePeliculas>() {
            @Override
            public void onResponse(Call<ContenedorDePeliculas> call, Response<ContenedorDePeliculas> response) {
                ContenedorDePeliculas contenedorDePeliculas = response.body();
                escuchadorControlador.finish(contenedorDePeliculas.getMovies());
            }

            @Override
            public void onFailure(Call<ContenedorDePeliculas> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Movie>());
            }
        });
    }

    public void obtenerVideoPelicula(final ResultListener<ContenedorDeVideos> escuchadorControlador, String idMovie, String language) {
        Call<ContenedorDeVideos> escuchadorRetrofit = daoServicePeliculas.getVideoMovies(idMovie, TMDBHelper.apiKey, language);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDeVideos>() {
            @Override
            public void onResponse(Call<ContenedorDeVideos> call, Response<ContenedorDeVideos> response) {
                ContenedorDeVideos contenedorDeVideos = response.body();
                escuchadorControlador.finish(contenedorDeVideos);
            }

            @Override
            public void onFailure(Call<ContenedorDeVideos> call, Throwable t) {
                escuchadorControlador.finish(new ContenedorDeVideos(new ArrayList<Video>()));
            }
        });
    }

    public void obtenerUnaPelicula(final ResultListener<Movie> escuchadorControlador, String idMovie, String language) {
        Call<Movie> escuchadorRetrofit = daoServicePeliculas.getMovieDetail(idMovie, TMDBHelper.apiKey, language);
        escuchadorRetrofit.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movieObtenida = response.body();
                escuchadorControlador.finish(movieObtenida);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

    public void obtenerVideoSerie(final ResultListener<ContenedorDeVideos> escuchadorControlador, String idTv, String language) {
        Call<ContenedorDeVideos> escuchadorRetrofit = daoServicePeliculas.getVideoTv(idTv, TMDBHelper.apiKey, language);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDeVideos>() {
            @Override
            public void onResponse(Call<ContenedorDeVideos> call, Response<ContenedorDeVideos> response) {
                ContenedorDeVideos contenedorDeVideos = response.body();
                escuchadorControlador.finish(contenedorDeVideos);
            }

            @Override
            public void onFailure(Call<ContenedorDeVideos> call, Throwable t) {
                escuchadorControlador.finish(new ContenedorDeVideos(new ArrayList<Video>()));
            }
        });
    }

    public void obtenerListadoDeGenerosPelicula(final ResultListener<List<Genero>> escuchadorControlador, String language) {
        Call<ContenedorDeGeneros> escuchadorRetrofit = daoServicePeliculas.getGenreListMovie(TMDBHelper.apiKey, language);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDeGeneros>() {
            @Override
            public void onResponse(Call<ContenedorDeGeneros> call, Response<ContenedorDeGeneros> response) {
                ContenedorDeGeneros contenedorDeGeneros = response.body();
                escuchadorControlador.finish(contenedorDeGeneros.getListaGeneros());
            }

            @Override
            public void onFailure(Call<ContenedorDeGeneros> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Genero>());
            }
        });
    }

    public void obtenerListadoDeGenerosSerie(final ResultListener<List<Genero>> escuchadorControlador, String language) {
        Call<ContenedorDeGeneros> escuchadorRetrofit = daoServicePeliculas.getGenreListTv(TMDBHelper.apiKey, language);
        escuchadorRetrofit.enqueue(new Callback<ContenedorDeGeneros>() {
            @Override
            public void onResponse(Call<ContenedorDeGeneros> call, Response<ContenedorDeGeneros> response) {
                ContenedorDeGeneros contenedorDeGeneros = response.body();
                escuchadorControlador.finish(contenedorDeGeneros.getListaGeneros());
            }

            @Override
            public void onFailure(Call<ContenedorDeGeneros> call, Throwable t) {
                escuchadorControlador.finish(new ArrayList<Genero>());
            }
        });
    }

}
