package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.integrador.grupo2android.proyectointegrador.Modelo.Harcodeo;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.ViewPagerDetalleAdapter;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentDetalle;

public class ActivityDetalle extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private static final Integer s = 88;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        ViewPager viewPager = findViewById(R.id.viewPagerDetalle);
        ViewPagerDetalleAdapter viewPagerDetalleAdapter = new ViewPagerDetalleAdapter(fragmentManager, Harcodeo.audiovisualesPeliculas());
        viewPager.setAdapter(viewPagerDetalleAdapter);
        viewPager.setCurrentItem(bundle.getInt("posicion"));

        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        fragmentDetalle.setArguments(bundle);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorDeFragmentosDetalle, fragmentDetalle);

        fragmentTransaction.commit();

    }
}
