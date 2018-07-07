//package com.example.digitalhouse.mvcjsonguiado.refactor.dao;
package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;


import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeGeneros;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDePeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by DH on 3/4/2018.
 */

public interface DAOServicePeliculas {

    @GET("movie/{movieId}")
    Call<Movie> getMovieDetail(@Path("movieId") String movieId,
                               @Query("api_key") String apiKey,
                               @Query("language") String language);

    @GET("genre/list")
    Call<ContenedorDeGeneros> getGenreList(@Query("api_key") String apiKey,
                                           @Query("language") String language);

    @GET("movie/popular")
    Call<ContenedorDePeliculas> getPopularMovies(@Query("api_key") String apiKey,
                                                 @Query("language") String language,
                                                 @Query("page") Integer page);


    @GET("/movie/557?api_key=3b8e7c41ac3060fb9b20eb3a64d165e5&language=en-US")
    Call<ContenedorDePeliculas> getAlgo();
}