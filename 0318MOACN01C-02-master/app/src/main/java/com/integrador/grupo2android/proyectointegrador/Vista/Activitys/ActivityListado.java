package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.integrador.grupo2android.proyectointegrador.Modelo.Harcodeo;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.AdapterAudiovisual;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityListado extends AppCompatActivity  {


    @BindView(R.id.recyclerListado)
    RecyclerView recyclerViewListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_audiovisual);
        ButterKnife.bind(this);

        AdapterAudiovisual adapterAudiovisual = new AdapterAudiovisual(Harcodeo.audiovisualesPeliculas());
        recyclerViewListado.setAdapter(adapterAudiovisual);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ActivityListado.this, 2);
        recyclerViewListado.setLayoutManager(layoutManager);

    }

}

