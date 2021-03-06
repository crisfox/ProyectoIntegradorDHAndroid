package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorPeliculas;
import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorSeries;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDePeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.ContenedorDeTv;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Tv;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;
import com.integrador.grupo2android.proyectointegrador.Util.TMDBHelper;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.AdapterMovieListado;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityListado extends AppCompatActivity implements AdapterMovieListado.EscuchadorDelAdapterMovieListado {

    @BindView(R.id.recyclerListado)
    RecyclerView recyclerViewListado;

    @BindView(R.id.toolbarConBuscador)
    Toolbar toolbarConBuscador;

    private AdapterMovieListado adapterMovieListado;
    private Integer genero;
    private String tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_audiovisual);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarConBuscador);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarConBuscador.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent unIntent = getIntent();
        Bundle unBundle = unIntent.getExtras();

        if (unBundle != null) {
            genero = unBundle.getInt(Constantes.GENERO);
            tipo = unBundle.getString(Constantes.MODO_INTERNO);
            adapterMovieListado = new AdapterMovieListado(this, tipo, this);
            recyclerViewListado.setAdapter(adapterMovieListado);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ActivityListado.this, 3);
            recyclerViewListado.setLayoutManager(layoutManager);

            switch (tipo) {
                case Constantes.PELICULAS:
                    ControladorPeliculas controladorPeliculas = new ControladorPeliculas(getApplicationContext());
                    ResultListener<List<Movie>> escuchadorVista = new ResultListener<List<Movie>>() {
                        @Override
                        public void finish(List<Movie> resultado) {
                            adapterMovieListado.setMovies(resultado);
                        }
                    };

                    controladorPeliculas.obtenerPeliculasFiltradas(escuchadorVista, genero, null, null, TMDBHelper.language_SPANISH, 1);
                    break;

                case Constantes.SERIES:
                    ControladorSeries controladorSeries = new ControladorSeries(getApplicationContext());
                    ResultListener<List<Tv>> escuchadorVista2 = new ResultListener<List<Tv>>() {
                        @Override
                        public void finish(List<Tv> resultado) {
                            adapterMovieListado.setTv(resultado);
                        }
                    };

                    controladorSeries.obtenerSeriesFiltradas(escuchadorVista2, genero, null, TMDBHelper.language_SPANISH, 1);
                    break;
            }
        } else {
            Toast.makeText(this, "Bundle nulo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_toolbar_con_buscador, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint(getText(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ActivityListado.this, R.string.submitted, Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                action(R.string.search);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void action(int resid) {
        Toast.makeText(this, getText(resid), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void seleccionaronMovie(Integer posicion) {
        Intent intent = new Intent(this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.POSICION_LISTA, posicion);
        bundle.putString(Constantes.TIPO_DE_LISTA, Constantes.LISTA_PORGENERO_PELICULAS);
        bundle.putInt(Constantes.GENERO, genero);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void seleccionaronTv(Integer posicion) {
        Intent intent = new Intent(this, ActivityDetalle.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Constantes.POSICION_LISTA, posicion);
        bundle.putString(Constantes.TIPO_DE_LISTA, Constantes.LISTA_PORGENERO_SERIES);
        bundle.putInt(Constantes.GENERO, genero);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

