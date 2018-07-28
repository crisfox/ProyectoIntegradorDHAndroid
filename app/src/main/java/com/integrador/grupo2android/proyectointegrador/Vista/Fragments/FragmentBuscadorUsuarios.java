package com.integrador.grupo2android.proyectointegrador.Vista.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorUsuarios;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeUsuarios;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Usuario;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.AdapterUsuariosListado;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentBuscadorUsuarios extends Fragment {

    private ImageButton imageButtonSearch;
    private EditText editTextSearch;

    private String botonSeleccionado;
    private String textBuscarTexto;
    private EditText editTextBuscarTexto;
    private EscuchadorDelFragmentUsuarios escuchadorDelFragmentUsuarios;

    private RecyclerView recyclerViewListadoEnBuscadorUsuarios;
    private AdapterUsuariosListado adapterUsuariosListado;

    public FragmentBuscadorUsuarios() {
    }

    static public FragmentBuscadorUsuarios crearFragmentoBuscadorUsuarios() {
        FragmentBuscadorUsuarios fragmentBuscadorUsuarios = new FragmentBuscadorUsuarios();
        return fragmentBuscadorUsuarios;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscador_usuarios, container, false);

        imageButtonSearch = view.findViewById(R.id.imageButtonSearch);
        editTextSearch = view.findViewById(R.id.editTextSearch);
        recyclerViewListadoEnBuscadorUsuarios = view.findViewById(R.id.recyclerViewListadoEnBuscadorUsuarios);
        adapterUsuariosListado = new AdapterUsuariosListado(getContext());
        recyclerViewListadoEnBuscadorUsuarios.setAdapter(adapterUsuariosListado);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewListadoEnBuscadorUsuarios.setLayoutManager(layoutManager);


        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBuscarTexto = editTextSearch.getText().toString();
                busquedaDeUsuarios(textBuscarTexto);
                /*escuchadorDelFragmentUsuarios.enviarTextoUsuarios(editTextBuscarTexto);*/
            }
        });


        return view;
    }

    public void enviarTextoUsuarios(String editTextBuscarTexto) {
        escuchadorDelFragmentUsuarios.enviarTextoUsuarios(editTextBuscarTexto);
    }

    public interface EscuchadorDelFragmentUsuarios {
        void enviarTextoUsuarios(String editTextBuscarTexto);
    }

    private void busquedaDeUsuarios(final String textoIngresado) {
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        controladorUsuarios.obtenerUsuarios(new ResultListener<ContenedorDeUsuarios>() {
            @Override
            public void finish(ContenedorDeUsuarios resultado) {
                List<Usuario> listaDeUsuarios = new ArrayList<>();
                for (Usuario unUsuario : resultado.getUsuarios()) {
                    String nombreUsuario = unUsuario.getNombre();
                    if (textoIngresado.contains(nombreUsuario)) {
                        System.out.println("Encontrado");
                        listaDeUsuarios.add(unUsuario);
                    }
                }
                adapterUsuariosListado.setUsuarios(listaDeUsuarios);
            }
        });
    }
}
