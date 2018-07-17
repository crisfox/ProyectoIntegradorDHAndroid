package com.integrador.grupo2android.proyectointegrador.Modelo;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Usuario;
import com.integrador.grupo2android.proyectointegrador.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian on 21/5/2018.
 */

public final class Harcodeo {

    public static List<Usuario> usuarios() {
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        listaDeUsuarios.add(new Usuario("Cristian", "Cancelo", R.drawable.avengers));
        listaDeUsuarios.add(new Usuario("Eduardo", "Parra", R.drawable.eduperfil));
        listaDeUsuarios.add(new Usuario("José", "Fermoso", R.drawable.googlelogo));
        listaDeUsuarios.add(new Usuario("Maximiliano", "Gnovatto", R.drawable.jurassicworld));
        return listaDeUsuarios;
    }

    public static List<Audiovisual> audiovisualesSeries() {
        List<Audiovisual> listaDeAudiovisualesTusSeries = new ArrayList<>();
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.mrrobot, "Mr. Robot"));
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.lukecage, "Luke Cage"));
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.jurassicworld, "Jurassic World"));
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.elsecretodesusojos, "El secreto de sus ojos"));
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.avengers, "Avengers"));
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.gamesofthrones, "Games of Thrones"));
        listaDeAudiovisualesTusSeries.add(new Audiovisual(R.drawable.elsecretodesusojos, "El secreto de sus ojos"));

        return listaDeAudiovisualesTusSeries;

    }

    public static List<Audiovisual> audiovisualesPeliculas() {
        List<Audiovisual> listaDeAudiovisualesTusPeliculas = new ArrayList<>();
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.elsecretodesusojos, "El secreto de sus ojos"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.avengers, "Avengers"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.mrrobot, "Mr. Robot"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.lukecage, "Luke Cage"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.gamesofthrones, "Games of Thrones"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.jurassicworld, "Jurassic World"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.elsecretodesusojos, "El secreto de sus ojos"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.elsecretodesusojos, "El secreto de sus ojos"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.avengers, "Avengers"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.mrrobot, "Mr. Robot"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.lukecage, "Luke Cage"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.gamesofthrones, "Games of Thrones"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.jurassicworld, "Jurassic World"));
        listaDeAudiovisualesTusPeliculas.add(new Audiovisual(R.drawable.elsecretodesusojos, "El secreto de sus ojos"));

        return listaDeAudiovisualesTusPeliculas;

    }
}

