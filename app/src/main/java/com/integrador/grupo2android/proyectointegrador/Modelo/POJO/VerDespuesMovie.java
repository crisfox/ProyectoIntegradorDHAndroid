package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentDetalle;

/**
 * Created by Cristian on 21/7/2018.
 */
@Entity
public class VerDespuesMovie implements FragmentDetalle.Identificable {
    @PrimaryKey
    @NonNull
    private String idMovie;

    public VerDespuesMovie(@NonNull String idMovie) {
        this.idMovie = idMovie;
    }

    @NonNull
    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(@NonNull String idMovie) {
        this.idMovie = idMovie;
    }

    @Override
    public String getIdIdentificable() {
        return getIdMovie();
    }
}
