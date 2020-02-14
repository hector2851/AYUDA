package com.example.coretec;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private View btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Iniciar Sesion");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //CREAR MENSAJE AL PRESIONAR BOTON
        btn2 = (Button) findViewById(R.id.btn_ingresar);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(LoginActivity.this, "¡Bienvenido a CORETEC!", Toast.LENGTH_SHORT).show();
                //ACCEDER A ACTIVITY REGISTRO
                Intent btn2 = new Intent(LoginActivity.this, MenuActi.class);
                startActivity(btn2);
            }
        });
    }


}
