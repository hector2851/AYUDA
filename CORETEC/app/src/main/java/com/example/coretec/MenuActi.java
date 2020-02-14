package com.example.coretec;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.coretec.ui.ajustes.Ajustes;
import com.example.coretec.ui.contactanos.Contactanos;
import com.example.coretec.ui.historial.Historial;
import com.example.coretec.ui.planta.Planta;
import com.example.coretec.ui.porqueReci.PorqueReci;
import com.example.coretec.ui.raee.Raee;
import com.example.coretec.ui.reciclar.Reciclar;
import com.example.coretec.ui.somos.Somos;
import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActi extends AppCompatActivity {

    private TextView user;
    private TextView email;
    private TextView last;
     ImageView foto;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        user = (TextView) findViewById(R.id.profile_firstName);
        String nameUser = getIntent().getStringExtra("nameUser");
        user.setText(nameUser);

        last = (TextView) findViewById(R.id.profile_lastName);
        String lastName = getIntent().getStringExtra("lastName");
        last.setText(lastName);

        email = (TextView) findViewById(R.id.profile_email);
        String emailUser = getIntent().getStringExtra("email");
        email.setText(emailUser);

        foto = (ImageView) findViewById(R.id.profile_pic);
        String photoUser = getIntent().getStringExtra("photoUser");
        Picasso.get().load(photoUser).into(foto);




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        //HABILITAR COLORES DE ICONOCOS
        navigationView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_reciclar,
                R.id.nav_raee,
                R.id.nav_porqueReci,
                R.id.nav_historial,
                R.id.nav_somos,
                R.id.nav_planta,
                R.id.nav_contactanos,
                R.id.nav_ajustes,
                R.id.nav_cerrarSesion)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //ASIGNAR EL FRAGMENT A MOSTRAR en INICIO
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new Reciclar()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                FragmentManager fragmentManager = getSupportFragmentManager();
                if (id ==R.id.nav_reciclar){
                    fragmentManager.beginTransaction().replace(R.id.contenedor,new Reciclar()).commit();
                    getSupportActionBar().setTitle("CORETEC");
                } else if (id == R.id.nav_historial) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Historial()).commit();
                    getSupportActionBar().setTitle("Historial de Reciclaje");
                } else if (id == R.id.nav_raee) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Raee()).commit();
                    getSupportActionBar().setTitle("Que son los RAEE");
                } else if (id == R.id.nav_porqueReci) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new PorqueReci()).commit();
                    getSupportActionBar().setTitle("Porque Reciclar RAEE");
                }  else if (id == R.id.nav_planta) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Planta()).commit();
                    getSupportActionBar().setTitle("Puntos de Acopio");
                } else if (id == R.id.nav_somos) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Somos()).commit();
                    getSupportActionBar().setTitle("Conocenos un poco más");
                } else if (id == R.id.nav_contactanos) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Contactanos()).commit();
                    getSupportActionBar().setTitle("Contactanos");
                } else if (id == R.id.nav_ajustes) {
                    fragmentManager.beginTransaction().replace(R.id.contenedor, new Ajustes()).commit();
                    getSupportActionBar().setTitle("Ajustes de Usuario");
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
