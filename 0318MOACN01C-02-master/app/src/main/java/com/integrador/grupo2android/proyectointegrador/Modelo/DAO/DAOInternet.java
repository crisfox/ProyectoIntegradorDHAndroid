package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

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

}
