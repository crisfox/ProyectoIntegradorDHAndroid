package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.VerDespuesSerie;

import java.util.List;

/**
 * Created by Cristian on 21/7/2018.
 */
@Dao
public interface DAORoomVerDespuesSerieInterface {
    @Query("SELECT * FROM VerDespuesSerie")
    List<VerDespuesSerie> getAllVerDespuesSerie();

    @Query("SELECT * FROM VerDespuesSerie")
    List<VerDespuesSerie> getVerDespuesSerie();

    @Query("SELECT * FROM VerDespuesSerie WHERE idSerie = :idSerie")
    VerDespuesSerie traemeTodasVerDespuesSerieConMismoIdSerie(String idSerie);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVerDespuesAll(VerDespuesSerie... verDespuesSeries);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVerDespuesAll(List<VerDespuesSerie> series);

    @Query("DELETE FROM VerDespuesSerie WHERE idSerie = :idSerie")
    void delete(String idSerie);
}
