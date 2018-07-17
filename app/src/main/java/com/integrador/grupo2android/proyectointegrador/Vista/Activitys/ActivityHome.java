package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.integrador.grupo2android.proyectointegrador.R;
import com.integrador.grupo2android.proyectointegrador.Util.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ButtonIngresoCasa)
    ImageButton buttonCasa;

    @BindView(R.id.ButtonIngresoCine)
    ImageButton buttonCine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        buttonCine.setOnClickListener(this);
        buttonCasa.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonIngresoCasa:
                startActivityPrincipal(Constantes.MODO_CASA);
                this.finish();
                break;
            case R.id.ButtonIngresoCine:
                startActivityPrincipal(Constantes.MODO_CINE);
                this.finish();
                break;
            default:
                Toast.makeText(this, "Hubo un error. Volvé a intentarlo", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void startActivityPrincipal(String valor) {
        Intent irACasa = new Intent(this, ActivityPrincipal.class);
        Bundle bundleCasa = new Bundle();
        bundleCasa.putString("modo", valor);
        irACasa.putExtras(bundleCasa);
        startActivity(irACasa);
    }
}
