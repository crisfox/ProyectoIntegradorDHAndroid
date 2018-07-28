package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentBuscadorPeliculas;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentBuscadorSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmchierchie on 18/7/18.
 */

public class ViewPagerBuscadorAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentos;
    private List<String> pestanias;

    public ViewPagerBuscadorAdapter(Context context, FragmentManager fm) {
        super(fm);
        fragmentos = new ArrayList<>();
        pestanias = new ArrayList<>();

        fragmentos.add(new FragmentBuscadorPeliculas());
        fragmentos.add(new FragmentBuscadorSeries());
        //fragmentos.add(FragmentBuscadorUsuarios.crearFragmentoBuscadorUsuarios());

        this.pestanias.add(context.getString(R.string.peliculas));
        this.pestanias.add(context.getString(R.string.series));
        //this.pestanias.add(context.getString(R.string.usuarios));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return fragmentos.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pestanias.get(position);
    }
}
