package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentDetalle;

/**
 * Created by Cristian on 21/7/2018.
 */
@Entity
public class FavoritoSerie implements FragmentDetalle.Identificable {
    @PrimaryKey
    @NonNull
    private String idSerie;

    public FavoritoSerie(String idSerie) {
        this.idSerie = idSerie;
    }

    @NonNull
    public String getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(@NonNull String idSerie) {
        this.idSerie = idSerie;
    }

    @Override
    public String getIdIdentificable() {
        return getIdSerie();
    }
}
