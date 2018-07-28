package com.integrador.grupo2android.proyectointegrador.Modelo.DAO;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeUsuarios;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Usuario;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;

import java.util.ArrayList;
import java.util.List;

import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.LISTA_FAVORITOS_PELICULAS;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.LISTA_FAVORITOS_SERIES;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.LISTA_POR_VER_PELICULAS;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.LISTA_POR_VER_SERIES;
import static com.integrador.grupo2android.proyectointegrador.Util.Constantes.USUARIOS;

/**
 * Created by Cristian on 19/7/2018.
 */

public class DAOFirebaseUsuarios {
    private DatabaseReference mDataBase;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;


    //PEDIDOS FIREBASE
    public void obtenerUsuariosDeFirebase(final ResultListener<ContenedorDeUsuarios> escuchadorDelControlador) {

        final ContenedorDeUsuarios listado = new ContenedorDeUsuarios(new ArrayList<Usuario>());
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDataBase = firebaseDatabase.getReference();
        reference = mDataBase.child(USUARIOS);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                    Usuario usuarioLeido = dataSnapshotChild.getValue(Usuario.class);
                    listado.getUsuarios().add(usuarioLeido);
                }
                escuchadorDelControlador.finish(listado);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                escuchadorDelControlador.finish(listado);
            }
        };
        reference.addValueEventListener(valueEventListener);

    }


    public void obtenerListaFavoritosPelicula(final ResultListener<List<String>> escuchadorControlador, String idUser) {
        final List<String> lista = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDataBase = firebaseDatabase.getReference();
        reference = mDataBase.child(USUARIOS).child(idUser).child(LISTA_FAVORITOS_PELICULAS);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                    String unaPelicula = dataSnapshotChild.getValue(String.class);
                    lista.add(unaPelicula);
                }
                escuchadorControlador.finish(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                escuchadorControlador.finish(lista);
            }
        };
        reference.addValueEventListener(valueEventListener);
    }

    public void obtenerListaFavoritosSerie(final ResultListener<List<String>> escuchadorControlador, String idUser) {
        final List<String> lista = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDataBase = firebaseDatabase.getReference();
        reference = mDataBase.child(USUARIOS).child(idUser).child(LISTA_FAVORITOS_SERIES);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                    String unaSerie = dataSnapshotChild.getValue(String.class);
                    lista.add(unaSerie);
                }
                escuchadorControlador.finish(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                escuchadorControlador.finish(lista);
            }
        };
        reference.addValueEventListener(valueEventListener);
    }

    public void obtenerListaVerDespuesPelicula(final ResultListener<List<String>> escuchadorControlador, String idUser) {
        final List<String> lista = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDataBase = firebaseDatabase.getReference();
        reference = mDataBase.child(USUARIOS).child(idUser).child(LISTA_POR_VER_PELICULAS);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                    String unaPelicula = dataSnapshotChild.getValue(String.class);
                    lista.add(unaPelicula);
                }
                escuchadorControlador.finish(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                escuchadorControlador.finish(lista);
            }
        };
        reference.addValueEventListener(valueEventListener);
    }

    public void obtenerListaVerDespuesSerie(final ResultListener<List<String>> escuchadorControlador, String idUser) {
        final List<String> lista = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDataBase = firebaseDatabase.getReference();
        reference = mDataBase.child(USUARIOS).child(idUser).child(LISTA_POR_VER_SERIES);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                    String unaSerie = dataSnapshotChild.getValue(String.class);
                    lista.add(unaSerie);
                }
                escuchadorControlador.finish(lista);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                escuchadorControlador.finish(lista);
            }
        };
        reference.addValueEventListener(valueEventListener);
    }


    public void cargarAFirebaseVerDespuesMovie(String uid, String idVerDespues) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_POR_VER_PELICULAS).child(idVerDespues).setValue(idVerDespues);

    }

    public void cargarAFirebaseVerDespuesSerie(String uid, String idVerDespues) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_POR_VER_SERIES).child(idVerDespues).setValue(idVerDespues);

    }

    public void eliminarDeFirebaseVerDespuesMovie(String uid, String idVerDespues) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_POR_VER_PELICULAS).child(idVerDespues).removeValue();
    }

    public void eliminarDeFirebaseVerDespuesSerie(String uid, String idVerDespues) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_POR_VER_SERIES).child(idVerDespues).removeValue();
    }


    public void cargarAFirebaseFavoritoMovie(String uid, String idFavorito) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_FAVORITOS_PELICULAS).child(idFavorito).setValue(idFavorito);
    }

    public void cargarAFirebaseFavoritoSerie(String user, String id) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(user).child(LISTA_FAVORITOS_SERIES).child(id).setValue(id);

    }

    public void eliminarDeFirebaseFavoritoMovie(String uid, String idFavorito) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_FAVORITOS_PELICULAS).child(idFavorito).removeValue();
    }

    public void eliminarDeFirebaseFavoritoSerie(String uid, String idVerDespues) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = firebaseDatabase.getReference();
        mDatabase.child(USUARIOS).child(uid).child(LISTA_FAVORITOS_SERIES).child(idVerDespues).removeValue();
    }


}

