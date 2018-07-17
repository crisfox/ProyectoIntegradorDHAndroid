package com.integrador.grupo2android.proyectointegrador.Vista.Activitys;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.integrador.grupo2android.proyectointegrador.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ActivityLogin extends AppCompatActivity {
    private static final String EMAIL = "email";
    private  CallbackManager callbackManager;
    private Button loginButton;
    private GoogleApiClient googleApiClient;
    private SignInButton buttonLoginGoogle;
    private Button iniciarSesionConMail;
    public static final int SIGN_IN_CODE = 777;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        mAuth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();
        //BOTON DE FACEBOOK
        loginButton = findViewById(R.id.loginFacebookButton);

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
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(ActivityLogin.this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/

        //CONEXIÓN CON FACEBOOK
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            Toast.makeText(this, "Login exitoso!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ActivityLogin.this,ActivityHome.class));
        }

        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.loginFacebookButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(ActivityLogin.this, Arrays.asList("public_profile"));
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        Intent intent = new Intent(ActivityLogin.this, ActivityHome.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException error) {

                    }
                });
            }
        });

        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
    }

    //METODOS PARA VALIDAR CONEXIÓN CON GOOGLE
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(ActivityLogin.this, "Error en Login. Intentalo nuevamente", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }*/
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userId = user.getUid();

                        } else {

                        }
                    }
                });
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();
        } else {
            Toast.makeText(this, "No se pudo iniciar sesión. Intentalo nuevamente", Toast.LENGTH_SHORT).show();
        }
    }
    private void goMainScreen() {
        Intent intent = new Intent(this, ActivityHome.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
