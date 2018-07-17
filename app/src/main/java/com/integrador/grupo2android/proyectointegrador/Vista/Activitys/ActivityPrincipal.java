package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.integrador.grupo2android.proyectointegrador.Controlador.ControladorPeliculas;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Audiovisual;
import com.integrador.grupo2android.proyectointegrador.Modelo.POJO.Movie;
import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;
import com.integrador.grupo2android.proyectointegrador.Util.ResultListener;
import com.integrador.grupo2android.proyectointegrador.Vista.Adapters.ViewPagerPrincipalAdapter;
import com.integrador.grupo2android.proyectointegrador.Vista.Fragments.FragmentInicio;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivityPrincipal extends AppCompatActivity implements FragmentInicio.EscuchadorDelFragmentInicio {


    private FragmentManager fragmentManager;
    private String modoSeleccionado;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPagerPrincipal)
    ViewPager viewPager;

    @BindView(R.id.bottomBar)
    LinearLayout bottomBar;

    @BindView(R.id.buttonPerfil)
    ImageButton buttonPerfil;

    @BindView(R.id.buttonHome)
    ImageButton buttonHome;

    @BindView(R.id.buttonBuscador)
    ImageButton buttonBuscador;

    @BindView(R.id.buttonRandom)
    ImageButton buttonRandom;

    @BindView(R.id.buttonNotificacion)
    ImageButton buttonNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        modoSeleccionado = bundle.getString("modo");


        setSupportActionBar(myToolbar);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        tabLayout.setupWithViewPager(viewPager);
        cargarFragment(modoSeleccionado,fragmentTransaction);
        setearEscuchadoresBottomBar();

      ControladorPeliculas controladorPeliculas2 = new ControladorPeliculas();
        controladorPeliculas2.obtenerPeliculas(new ResultListener<Movie>() {
            @Override
            public void finish(Movie resultado) {
                Toast.makeText(ActivityPrincipal.this, resultado.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.buttonCasa:
                if(!modoSeleccionado.equals(Constantes.MODO_CASA)){
                    Intent intent = new Intent(this,ActivityPrincipal.class);
                    intent.putExtra("modo",Constantes.MODO_CASA);
                    startActivity(intent);
                    this.finish();
                }
                break;
            case R.id.buttonCine:
                if(!modoSeleccionado.equals(Constantes.MODO_CINE)){
                    Intent intent = new Intent(this,ActivityPrincipal.class);
                    intent.putExtra("modo",Constantes.MODO_CASA);
                    startActivity(intent);
                    this.finish();
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void cargarFragment(String modoSeleccionado, FragmentTransaction fragmentTransaction) {

            switch (modoSeleccionado) {
                case Constantes.MODO_CASA:
                    tabLayout.setupWithViewPager(viewPager);
                    viewPager.setAdapter(new ViewPagerPrincipalAdapter(fragmentManager));
                    tabLayout.getTabAt(0).setText("Peliculas");
                    tabLayout.getTabAt(1).setText("Series");
                    break;
                case Constantes.MODO_CINE:
                    tabLayout.setupWithViewPager(viewPager);
                    viewPager.setAdapter(new ViewPagerPrincipalAdapter(fragmentManager));
                    tabLayout.getTabAt(0).setText("En cartelera");
                    tabLayout.getTabAt(1).setText("Proximos estrenos");
                    break;
                default:
                    break;
            }
        }

    private void setearEscuchadoresBottomBar(){



        buttonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityPrincipal.this,ActivityPerfil.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void seleccionaronVerMas() {
       Intent intent = new Intent(ActivityPrincipal.this,ActivityListado.class);
        startActivity(intent);
    }

    @Override
    public void seleccionaronAudiovisual(Integer posicion, String tituloDeLaLista) {

        Intent intent = new Intent(ActivityPrincipal.this,ActivityDetalle.class);

        Bundle bundle = new Bundle();
        bundle.putInt("posicion",posicion);
        bundle.putString("tituloDeLaLista",tituloDeLaLista);

        intent.putExtras(bundle);
        startActivity(intent);
    }

}