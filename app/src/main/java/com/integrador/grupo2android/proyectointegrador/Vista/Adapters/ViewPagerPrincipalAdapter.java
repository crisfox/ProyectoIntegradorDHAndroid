package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentInicio;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerPrincipalAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listaFragmentos = new ArrayList<>();

    public ViewPagerPrincipalAdapter(FragmentManager fm) {
        super(fm);
        listaFragmentos.add(new FragmentInicio());
        listaFragmentos.add(new FragmentInicio());
    }

    @Override
    public Fragment getItem(int position) {
        return this.listaFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return this.listaFragmentos.size();
    }


}