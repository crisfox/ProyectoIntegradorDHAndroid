package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesMovie;

import java.util.List;

/**
 * Created by Cristian on 21/7/2018.
 */
@Dao
public interface DAORoomVerDespuesMovieInterface {
    @Query("SELECT * FROM VerDespuesMovie")
    List<VerDespuesMovie> getAllVerDespuesMovie();

    @Query("SELECT * FROM VerDespuesMovie")
    List<VerDespuesMovie> getVerDespuesMovie();

    @Query("SELECT * FROM VerDespuesMovie WHERE idMovie = :idMovie")
    VerDespuesMovie traemeTodasVerDespuesMovieConMismoIdMovie(String idMovie);

    @Insert
    void insertVerDespuesAll(VerDespuesMovie... movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVerDespuesAll(List<VerDespuesMovie> movies);

    @Query("DELETE FROM VerDespuesMovie WHERE idMovie = :idMovie")
    void delete(String idMovie);
}
