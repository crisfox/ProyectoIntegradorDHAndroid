package com.integrador.grupo2android.proyectointegrador.Vista.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorFavoritos;
import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorPeliculas;
import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorSeries;
import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorUsuarios;
import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorVerDespuesPeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeVideos;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Tv;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Usuario;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;
import com.integrador.grupo2android.proyectointegrador.Util.TMDBHelper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentDetalle extends Fragment {
    private TextView textViewTitulo;
    private TextView textViewResena;
    private ImageButton buttonPlayTrailer;
    private ImageButton imageButtonFavorito;
    private ImageButton imageButtonAddToList;
    private ImageButton imageButtonShare;
    private RatingBar ratingBar;


    private EscuchadorFragmentDetalle escuchadorFragmentDetalle;

    private String url_foto;
    private String titulo;
    private String keyYoutube;
    private String id;
    private String resena;
    private String modo;
    private Integer ranking;
    private ImageView imageView;

    private static final String TITULO = "titulo";
    private static final String FOTO = "foto";
    private static final String RESENA = "RESENA";
    private static final String VIDEO = "VIDEO";
    private static final String ID_LLAVE = "ID";
    private static final String RANKING = "RANKING";

    private Boolean estadoFavorito;
    private Boolean estadoVerDespues;

    private FirebaseUser user;

    private String verDespuesActivo;
    private String favoritoActivo;

    public void onAttach(Context context) {
        super.onAttach(context);
        this.escuchadorFragmentDetalle = (FragmentDetalle.EscuchadorFragmentDetalle) context;
    }

    public FragmentDetalle() {
        // Required empty public constructor
    }

    public static Fragment dameUnFragment(Movie movie) {
        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        Bundle args = new Bundle();
        args.putString(TITULO, movie.getTitle());
        args.putString(FOTO, movie.getBackdrop_path());
        args.putString(ID_LLAVE, movie.getId().toString());
        args.putString(RESENA, movie.getOverview());
        args.putString(Constantes.MODO_INTERNO, Constantes.PELICULAS);
        args.putString(RANKING, movie.getVote_average());
        fragmentDetalle.setArguments(args);
        return fragmentDetalle;
    }

    public static Fragment dameUnFragment(Tv tv) {
        FragmentDetalle fragmentDetalle = new FragmentDetalle();
        Bundle args = new Bundle();
        args.putString(TITULO, tv.getName());
        args.putString(FOTO, tv.getPoster_path());
        args.putString(ID_LLAVE, tv.getId().toString());
        args.putString(RESENA, tv.getOverview());
        args.putString(RANKING, tv.getVote_average());
        args.putString(Constantes.MODO_INTERNO, Constantes.SERIES);
        fragmentDetalle.setArguments(args);
        return fragmentDetalle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //Boton para volver atras
                getActivity().onBackPressed();
            }

        });

        initViews(view);

        final Bundle bundle = getArguments();

        if (bundle != null) {
            cargosValores(bundle);
        }


        setearOnClickImageViewVideo();
        setearOnClickButtonShare();
        setearOnClickButtonListaPorVer();
        setearOnClickButtonFavoritos();
        return view;
    }

    private void initViews(View view) {
        imageButtonFavorito = view.findViewById(R.id.imageButtonFavorito);
        imageButtonAddToList = view.findViewById(R.id.buttonAddToList);
        imageButtonShare = view.findViewById(R.id.imageButtonShare);
        imageView = view.findViewById(R.id.imageViewAudiovisualCarteleraDetalle);
        textViewTitulo = view.findViewById(R.id.textViewTitulo);
        textViewResena = view.findViewById(R.id.textViewResena);
        ratingBar = view.findViewById(R.id.ratingBar);
        buttonPlayTrailer = view.findViewById(R.id.buttonPlayTrailer);
    }

    private void setearOnClickImageViewVideo() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyYoutube.isEmpty()) {
                    Toast.makeText(getContext(), "Video no disponible", Toast.LENGTH_SHORT).show();
                } else {
                    escuchadorFragmentDetalle.envioUrl(keyYoutube);
                }
            }
        });
    }

    private void setearOnClickButtonShare() {
        imageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                if (keyYoutube.isEmpty()) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Hey! te recomiendo " + titulo + " Buscala aca: " + Constantes.URL_BASE_GOOGLE + titulo.replace(" ", "+") + "+pelicula");
                } else {

                    intent.putExtra(Intent.EXTRA_TEXT, "Hey! te recomiendo " + titulo + " mirala aca: " + Constantes.URL_BASE_YOUTUBE + keyYoutube);
                }
                startActivity(Intent.createChooser(intent, "COMPARTÍ POR MOVIE RANK"));
            }
        });

        buttonPlayTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (keyYoutube.isEmpty()) {
                    Toast.makeText(getContext(), "Video no disponible", Toast.LENGTH_SHORT).show();
                } else {
                    escuchadorFragmentDetalle.envioUrl(keyYoutube);
                }
            }
        });
        setearOnClickButtonListaPorVer();
        setearOnClickButtonFavoritos();
    }


    private boolean estasLogeado() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        return (user != null);
    }


    private void consultarSiEstaEnVerDespues(String id) {
        ControladorVerDespuesPeliculas controladorVerDespuesPeliculas = new ControladorVerDespuesPeliculas(getActivity().getApplicationContext());
        //verDespuesActivo

        Identificable identificable;
        if (modo.equals(Constantes.PELICULAS)) {
            identificable = controladorVerDespuesPeliculas.consultaSiEstaVerDespuesMovieId(id);
        } else {
            identificable = controladorVerDespuesPeliculas.consultaSiEstaVerDespuesSerieId(id);
        }


        if (identificable != null) {
            verDespuesActivo = identificable.getIdIdentificable();
        } else {
            verDespuesActivo = null;
        }

        if (verDespuesActivo == null) {
            estadoVerDespues = false;
            imageButtonAddToList.setImageResource(R.drawable.ic_playlist_add_white_24dp);
        } else {
            estadoVerDespues = true;
            imageButtonAddToList.setImageResource(R.drawable.ic_playlist_add_check_green_24dp);
        }
    }

    private void setearOnClickButtonListaPorVer() {
        imageButtonAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estasLogeado()) {
                    if (estadoVerDespues) {
                        estadoVerDespues = false;
                        imageButtonAddToList.setImageResource(R.drawable.ic_playlist_add_white_24dp);
                        eliminarDeRoomVerDespues(id);
                    } else {
                        estadoVerDespues = true;
                        imageButtonAddToList.setImageResource(R.drawable.ic_playlist_add_check_green_24dp);
                        cargarARoomVerDespues(id);

                    }
                } else {
                    Toast.makeText(getActivity(), "Para usar esta función tenés que iniciar sesion", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void cargarARoomVerDespues(String id) {

        ControladorVerDespuesPeliculas controladorVerDespuesPeliculas = new ControladorVerDespuesPeliculas(getActivity().getApplicationContext());
        controladorVerDespuesPeliculas.addVerDespuesARoom(id, modo);
        cargarAFirebaseVerDespues(id);
    }

    public void cargarAFirebaseVerDespues(String idVerDespues) {

        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        controladorUsuarios.cargarAFirebaseVerDespues(user.getUid(), idVerDespues, modo);

    }

    private void eliminarDeRoomVerDespues(String id) {
        ControladorVerDespuesPeliculas controladorVerDespuesPeliculas = new ControladorVerDespuesPeliculas(getActivity().getApplicationContext());
        controladorVerDespuesPeliculas.removeVerDespues(id, modo);
        eliminarDeFirebaseVerDespues(id);
    }

    private void eliminarDeFirebaseVerDespues(String id) {
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        controladorUsuarios.eliminarDeFirebaseVerDespues(user.getUid(), id, modo);
    }


    private void consultarSiEstaFavorito(String id) {
        ControladorFavoritos controladorFavoritos = new ControladorFavoritos(getActivity().getApplicationContext());
        //verDespuesActivo

        Identificable identificable;
        if (modo.equals(Constantes.PELICULAS)) {
            identificable = controladorFavoritos.consultaSiEstaFavoritoMovieId(id);
        } else {
            identificable = controladorFavoritos.consultaSiEstaFavoritoSerieId(id);
        }


        if (identificable != null) {
            favoritoActivo = identificable.getIdIdentificable();
        } else {
            favoritoActivo = null;
        }

        if (favoritoActivo == null) {
            estadoFavorito = false;
            imageButtonFavorito.setImageResource(R.drawable.ic_favorite_white_24dp);
        } else {
            estadoFavorito = true;
            imageButtonFavorito.setImageResource(R.drawable.ic_favorite_red_24dp);
        }
    }

    private void setearOnClickButtonFavoritos() {
        imageButtonFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estasLogeado()) {
                    if (estadoFavorito) {
                        estadoFavorito = false;
                        imageButtonFavorito.setImageResource(R.drawable.ic_favorite_white_24dp);
                        eliminarDeRoomFavorito(id);
                    } else {
                        estadoFavorito = true;
                        imageButtonFavorito.setImageResource(R.drawable.ic_favorite_red_24dp);
                        cargarARoomFavorito(id);

                    }
                } else {
                    Toast.makeText(getActivity(), "Para usar esta función tenés que iniciar sesion", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void cargarARoomFavorito(String id) {
        ControladorFavoritos controladorFavoritos = new ControladorFavoritos(getActivity().getApplicationContext());
        controladorFavoritos.addFavoritoARoom(id, modo);
        cargarAFirebaseFavorito(id);
    }

    private void cargarAFirebaseFavorito(String idFavorito) {
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        controladorUsuarios.cargarAFirebaseFavorito(user.getUid(), idFavorito, modo);
    }

    private void eliminarDeRoomFavorito(String id) {
        ControladorFavoritos controladorFavoritos = new ControladorFavoritos(getActivity().getApplicationContext());
        controladorFavoritos.removeFavorito(id, modo);
        eliminarDeFirebaseFavorito(id);
    }

    private void eliminarDeFirebaseFavorito(String id) {
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        controladorUsuarios.eliminarDeFirebaseFavorito(user.getUid(), id, modo);
    }


    private void cargosValores(Bundle bundle) {
        url_foto = bundle.getString(FOTO);
        titulo = bundle.getString(TITULO);
        id = bundle.getString(ID_LLAVE);
        resena = bundle.getString(RESENA);
        textViewResena.setText(resena);
        textViewTitulo.setText(titulo);
        String stringRanking = bundle.getString(RANKING);
        modo = bundle.getString(Constantes.MODO_INTERNO);
        consultarSiEstaFavorito(id);
        consultarSiEstaEnVerDespues(id);
        Glide.with(getActivity()).load(Constantes.URL_BASE_IMAGE + url_foto).into(imageView);
        if (modo.equals(Constantes.SERIES)) {
            obtenerUrlVideoSerie(id);
        } else {
            obtenerUrlVideoPelicula(id);
        }
        ratingBar.setRating(conviertoRanking(stringRanking));
        ratingBar.setIsIndicator(true);
    }

    private float conviertoRanking(String ranking) {
        Float floatRanking = Float.parseFloat(ranking) / 2;
        return floatRanking;
    }

    public void obtenerUrlVideoPelicula(String id) {
        ControladorPeliculas controladorPeliculas = new ControladorPeliculas(getActivity().getApplicationContext());
        ResultListener<ContenedorDeVideos> escuchadorVista = new ResultListener<ContenedorDeVideos>() {
            @Override
            public void finish(ContenedorDeVideos resultado) {
                if (resultado.getListaDeVideos().isEmpty()) {
                    keyYoutube = "";
                } else {
                    keyYoutube = resultado.getListaDeVideos().get(0).getKey();
                }
            }
        };
        controladorPeliculas.obtenerVideoPelicula(escuchadorVista, id, TMDBHelper.language_SPANISH);
    }

    public void obtenerUrlVideoSerie(String id) {
        ControladorSeries controladorSeries = new ControladorSeries(getActivity().getApplicationContext());
        ResultListener<ContenedorDeVideos> escuchadorVista = new ResultListener<ContenedorDeVideos>() {
            @Override
            public void finish(ContenedorDeVideos resultado) {
                if (resultado.getListaDeVideos().isEmpty()) {
                    keyYoutube = "";
                } else {
                    keyYoutube = resultado.getListaDeVideos().get(0).getKey();
                }
            }
        };
        controladorSeries.obtenerVideoTv(escuchadorVista, id, TMDBHelper.language_SPANISH);
    }


    public interface Identificable {
        String getIdIdentificable();
    }


    public interface EscuchadorFragmentDetalle {
        void envioUrl(String urlVideo);
    }
}
