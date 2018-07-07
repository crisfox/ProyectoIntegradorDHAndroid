package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.integrador.grupo2android.proyectointegrador.R;

import java.util.Arrays;

public class ActivityLogin extends AppCompatActivity {
    private static final String EMAIL = "email";
    private  CallbackManager callbackManager;
    private LoginButton loginButton;
    private GoogleApiClient googleApiClient;
    private SignInButton buttonLoginGoogle;
    private Button iniciarSesionConMail;
    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        //BOTON DE FACEBOOK
        loginButton = findViewById(R.id.loginFacebookButton);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        //BOTON DE GOOGLE
        buttonLoginGoogle = (SignInButton) findViewById(R.id.buttonLoginGoogle);
        buttonLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent unIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(unIntent, SIGN_IN_CODE);
            }
        });
        //BOtÓN DE CUENTA PROPIA
        iniciarSesionConMail = (Button) findViewById(R.id.buttonIniciarSesionMail);
        iniciarSesionConMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityLogin.this,ActivityRegistro.class));
            }
        });

        //CONEXIÓN CON GOOGLE
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //CONEXIÓN CON FACEBOOK
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            Toast.makeText(this, "Login exitoso!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ActivityLogin.this,ActivityPrincipal.class));
            this.finish();
        }

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            //METODOS PARA VALIDAR CONEXIÓN CON FACEBOOK
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(ActivityLogin.this, "Login exitoso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityLogin.this,ActivityPrincipal.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(ActivityLogin.this, "No se pudo iniciar sesión. Intentalo nuevamente", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(ActivityLogin.this, "Error en Login. Intentalo nuevamente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //METODOS PARA VALIDAR CONEXIÓN CON GOOGLE
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(ActivityLogin.this, "Error en Login. Intentalo nuevamente", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();
        } else {
            Toast.makeText(this, "No se pudo iniciar sesión. Intentalo nuevamente", Toast.LENGTH_SHORT).show();
        }
    }
    private void goMainScreen() {
        Intent intent = new Intent(this, ActivityPrincipal.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
