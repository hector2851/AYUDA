package com.example.coretec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().setTitle("Registro de Usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
