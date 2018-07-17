package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.integrador.grupo2android.proyectointegrador.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivityPerfil extends AppCompatActivity {

    @BindView(R.id.textViewNombre)
    TextView textViewNombre;

    @BindView(R.id.imageViewFotoPerfil)
    ImageView imageViewFotoPerfil;

    @BindView(R.id.buttonSeguirPerfil)
    Button buttonSeguirPerfil;

    @BindView(R.id.buttonVerPeliculasPerfil)
    Button buttonVerPeliculasPerfil;

    @BindView(R.id.buttonLogoutFacebook)
    Button buttonLogoutFacebook;

    private Button buttonCerrarSesion;
    private Button buttonRevocarDatos;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ButterKnife.bind(this);

       /* mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String idDelUsuario = user.getUid();

        if (idDelUsuario == null) {
            Intent intent = new Intent(ActivityPerfil.this, ActivityLogin.class);
            startActivity(intent);
            this.finish();
        }*/

        buttonLogoutFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent intentLogin = new Intent(ActivityPerfil.this, ActivityLogin.class);
                startActivity(intentLogin);
                finish();
            }
        });

        buttonCerrarSesion = (Button) findViewById(R.id.buttonCerrarSesion);
        buttonRevocarDatos = (Button) findViewById(R.id.buttonRevocarDatos);
        textViewNombre = findViewById(R.id.textViewNombre);

        /*textViewNombre.setText(user.getDisplayName());
        Glide.with(ActivityPerfil.this).load(user.getPhotoUrl()).into(imageViewFotoPerfil);*/



       /* buttonCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut(view);
            }
        });

        buttonRevocarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revocarDatos(view);
            }
        });*/
    }


    /*public void logOut(View view) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo cerrar la sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/

    /*public void revocarDatos (View view) {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "No se pudo revocar el acceso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
    private void goLogInScreen() {
        Intent intent = new Intent(this, ActivityPrincipal.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
