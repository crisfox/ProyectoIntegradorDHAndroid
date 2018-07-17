package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import android.util.Log;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeGeneros;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDePeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

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

    public void obtenerPeliculasInternet(final ResultListener<Movie> escuchadorControlador) {

        Call<Movie> escuchadorRetrofit = daoServicePeliculas.getMovieDetail("557", "3b8e7c41ac3060fb9b20eb3a64d165e5", "en-US");

        escuchadorRetrofit.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                Movie movie = response.body();
                escuchadorControlador.finish(movie);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                escuchadorControlador.finish(null);
            }
        });
    }

    public void obtenerListaPeliculasInternet(final ResultListener<List<Movie>> escuchadorControlador) {

        Call<List<Movie>> escuchadorRetrofit = daoServicePeliculas.getPopularMoviesArray("3b8e7c41ac3060fb9b20eb3a64d165e5", "en-US",1);

        escuchadorRetrofit.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){

                    List<Movie> contenedorDePeliculas = response.body();

                    Log.d("onResponse ","tamanio: " + contenedorDePeliculas.size());
                    escuchadorControlador.finish(contenedorDePeliculas);
                }

            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                escuchadorControlador.finish(null);
            }
        });
    }

}
