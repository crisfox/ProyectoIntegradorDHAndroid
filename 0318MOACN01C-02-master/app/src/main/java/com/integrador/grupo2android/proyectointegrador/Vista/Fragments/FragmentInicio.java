package com.integrador.grupo2android.proyectointegrador.Vista.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.integrador.grupo2android.proyectointegrador.Modelo.Harcodeo;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.AdapterAudiovisual;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInicio extends Fragment {

    private int celdaLayout = R.layout.celda_audiovisual;
    private ButtonBarLayout buttonPrimeraMasVotada;
    private ButtonBarLayout buttonSegundaMasVotada;
    private ButtonBarLayout buttonTerceraMasVotada;

    public FragmentInicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        buttonPrimeraMasVotada = view.findViewById(R.id.buttonPrimeraMasVotada);
        buttonSegundaMasVotada = view.findViewById(R.id.buttonSegundaMasVotada);
        buttonTerceraMasVotada = view.findViewById(R.id.buttonTerceraMasVotada);
        //roundedButton = view.findViewById(R.id.buttonRounded);

        List<Audiovisual> listaDeAudiovisualesTusPeliculas = Harcodeo.audiovisualesPeliculas();
        List<Audiovisual> listaDeAudiovisualesTusSeries = Harcodeo.audiovisualesSeries();

        RecyclerView recyclerViewAudiovisualPeliculas = view.findViewById(R.id.recyclerProximosEstrenos);
        RecyclerView recyclerViewAudiovisualSeries = view.findViewById(R.id.recyclerTuSeleccion);

        final AdapterAudiovisual adapterPeliculas = new AdapterAudiovisual(listaDeAudiovisualesTusPeliculas);
        final AdapterAudiovisual adapterSeries = new AdapterAudiovisual(listaDeAudiovisualesTusSeries);

        recyclerViewAudiovisualPeliculas.setAdapter(adapterPeliculas);
        recyclerViewAudiovisualSeries.setAdapter(adapterSeries);


        RecyclerView.LayoutManager layoutManagerPeliculas = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManagerSeries = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewAudiovisualPeliculas.setLayoutManager(layoutManagerPeliculas);
        recyclerViewAudiovisualSeries.setLayoutManager(layoutManagerSeries);

        return view;
    }

}
