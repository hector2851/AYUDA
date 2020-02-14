package com.example.coretec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.metrics.Tag;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Files;
import java.util.Arrays;

public class InicioActivity extends AppCompatActivity {

    private View btn;
    private View btn1;
    private CallbackManager mCallbackManager;
    private static final String TAG = "FACELOG";
    private FirebaseAuth mAuth;

    private Button bfacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //BOTON  ENVIAR A CREAR CUENTA
        btn = (Button) findViewById(R.id.btn_crear);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InicioActivity.this, "¡Genial! Ahora juntos crearemos tu cuenta", Toast.LENGTH_SHORT).show();
                //ACCEDER A ACTIVITY REGISTRO
                Intent btn = new Intent(InicioActivity.this, RegistroActivity.class);
                startActivity(btn);
            }
        });

        //BOTON  ENVIAR A INICIAR SESION
        btn1 = (Button) findViewById(R.id.btn_iniciar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InicioActivity.this, "¡Ingresa tu cuenta!", Toast.LENGTH_SHORT).show();
                //ACCEDER A ACTIVITY REGISTRO
                Intent btn1 = new Intent(InicioActivity.this, LoginActivity.class);
                startActivity(btn1);
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();

        bfacebook = (Button) findViewById(R.id.btn_login_facebook);
        bfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bfacebook.setEnabled(true);

                LoginManager.getInstance().logInWithReadPermissions(InicioActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        updateUI();

                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        // ...
                    }
                });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser !=null){
            updateUI();
        }
    }

    private void updateUI() {

        final Intent intent = new Intent(InicioActivity.this, MenuActi.class);
        GraphRequest request = GraphRequest.newMeRequest( AccessToken.getCurrentAccessToken(), new
                GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) { Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            intent.putExtra("nameUser", first_name);
                            intent.putExtra("lastName", last_name);
                            intent.putExtra("email", email);
                            intent.putExtra("photoUser", image_url);
                            Toast.makeText(InicioActivity.this, "Bienvenido " + first_name , Toast.LENGTH_LONG).show();
                            startActivity(intent);

                            handleFacebookAccessToken(AccessToken.getCurrentAccessToken());
                        } catch (JSONException e) { e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name, last_name,email,id");
        request.setParameters(parameters); request.executeAsync();
       // Toast.makeText(InicioActivity.this, "Bienvenido"  +  "CORETEC", Toast.LENGTH_LONG).show();
        //Intent account = new Intent (InicioActivity.this, MenuActi.class);
        //startActivity(account);
        //finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            bfacebook.setEnabled(true);
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(InicioActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            bfacebook.setEnabled(true);
                            updateUI();
                        }

                        // ...
                    }
                });
    }
}

