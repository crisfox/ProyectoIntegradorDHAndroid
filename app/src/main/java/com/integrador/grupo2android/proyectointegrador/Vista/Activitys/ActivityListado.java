package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.AdapterMovieListadoVerMas;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityListado extends AppCompatActivity  {


    @BindView(R.id.recyclerListado)
    RecyclerView recyclerViewListado;

    @BindView(R.id.toolbarConBuscador)
    Toolbar toolbarConBuscador;

    AdapterMovieListadoVerMas adapterMovieListadoVerMas;


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
        //getSupportActionBar().setTitle(null);

        adapterMovieListadoVerMas = new AdapterMovieListadoVerMas(this);
        recyclerViewListado.setAdapter(adapterMovieListadoVerMas);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ActivityListado.this, 2);
        recyclerViewListado.setLayoutManager(layoutManager);

        ControladorPeliculas controladorPeliculas = new ControladorPeliculas();
        controladorPeliculas.obtenerListaPeliculas(new ResultListener<List<Movie>>() {
            @Override
            public void finish(List<Movie> resultado) {
                adapterMovieListadoVerMas.setMovies(resultado);
            }
        });





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

}

