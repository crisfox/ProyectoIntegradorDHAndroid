package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.ViewPagerBuscadorAdapter;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentBuscadorPeliculas;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentBuscadorSeries;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityBuscador extends AppCompatActivity implements FragmentBuscadorPeliculas.EscuchadorFragmentoBuscadorPelicula, FragmentBuscadorSeries.EscuchadorFragmentoBuscadorSeries {

    @BindView(R.id.imageButtonBack)
    ImageButton imageButtonBack;

    @BindView(R.id.viewPagerBuscador)
    ViewPager viewPagerBuscador;

    @BindView(R.id.tabLayoutBuscador)
    TabLayout tabLayoutBuscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        ViewPagerBuscadorAdapter viewPagerBuscadorAdapter = new ViewPagerBuscadorAdapter(this, fragmentManager);
        viewPagerBuscador.setAdapter(viewPagerBuscadorAdapter);
        tabLayoutBuscador.setupWithViewPager(viewPagerBuscador);

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void seleccionaronPelicula(Integer posicion, String texto) {
        Intent intent = new Intent(this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.POSICION_LISTA, posicion);
        bundle.putString(Constantes.TIPO_DE_LISTA, Constantes.LISTA_FILTRADA_PELICULAS);
        bundle.putString(Constantes.TEXTO_BUSQUEDA, texto);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void seleccionaronSerie(Integer posicion, String texto) {
        Intent intent = new Intent(this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.POSICION_LISTA, posicion);
        bundle.putString(Constantes.TIPO_DE_LISTA, Constantes.LISTA_FILTRADA_SERIES);
        bundle.putString(Constantes.TEXTO_BUSQUEDA, texto);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}



