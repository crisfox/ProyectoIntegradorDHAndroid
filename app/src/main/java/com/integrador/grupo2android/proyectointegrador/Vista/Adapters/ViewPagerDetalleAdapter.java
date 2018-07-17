package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentDetalle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian on 17/7/2018.
 */

public class ViewPagerDetalleAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listaFragmentos = new ArrayList<>();

    public ViewPagerDetalleAdapter(FragmentManager fm, List<Audiovisual> listaDeAudiovisuales) {
        super(fm);

        for (Audiovisual unAudiovisual : listaDeAudiovisuales){
            listaFragmentos.add(FragmentDetalle.dameUnFragment(unAudiovisual));
        }
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
