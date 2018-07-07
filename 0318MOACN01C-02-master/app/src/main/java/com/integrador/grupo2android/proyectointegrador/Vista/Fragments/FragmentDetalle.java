package com.integrador.grupo2android.proyectointegrador.Vista.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {
    private TextView textViewTitulo;
    private ImageView imageView;

    private static final String TITULO = "titulo";
    private static final String FOTO = "foto";

    public FragmentDetalle() {
        // Required empty public constructor
    }

    public static Fragment dameUnFragment(Audiovisual unAudiovisual) {
        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        Bundle args = new Bundle();
        args.putString(TITULO, unAudiovisual.getTitulo());
        args.putInt(FOTO, unAudiovisual.getfoto());
        fragmentDetalle.setArguments(args);
        return fragmentDetalle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        Bundle bundle = getArguments();
        Integer numeroFoto = bundle.getInt(FOTO);
        String titulo = bundle.getString(TITULO);
        imageView = view.findViewById(R.id.imageViewAudiovisualCarteleraDetalle);
        textViewTitulo = view.findViewById(R.id.textViewTitulo);

        textViewTitulo.setText(titulo);
        imageView.setImageResource(numeroFoto);

        return view;
    }



}
