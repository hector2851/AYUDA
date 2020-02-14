package com.example.coretec.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coretec.R;
import com.example.coretec.ui.reciclar.RaeeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ResiduosActivity extends AppCompatActivity {


    private List<String> residuo;
    private List<Integer> resiImage;
    private EditText numero;
    private Button mas;
    private Button menos;
    int contador = 0;
    Context context;
    GridView gridView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residuos);

        getSupportActionBar().setTitle("Residuos Reciclables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView2 = (GridView) findViewById(R.id.gridView2);
        residuo = new ArrayList<String>();
        residuo.add("Camara Fotografica");
        residuo.add("Cargador USB");
        residuo.add("Camara de Vigilancia");
        residuo.add("Celular");
        residuo.add("Consola de Videojuego");
        residuo.add("Control Remoto");
        residuo.add("CPU");
        residuo.add("Disco Duro");
        residuo.add("Fuente de Poder");
        residuo.add("Hervidor");
        residuo.add("Impresora");
        residuo.add("Juguetes Electronicos");
        residuo.add("Laptop");
        residuo.add("Lavadora");
        residuo.add("Licuadora");
        residuo.add("Microondas");
        residuo.add("Mouse");
        residuo.add("Monitor LCD");
        residuo.add("Plancha");
        residuo.add("Proyector");
        residuo.add("Reproductor DVD");
        residuo.add("Reproductor MP3/MP4");
        residuo.add("Reproductor VHS");
        residuo.add("Modem / router");
        residuo.add("Scanner");
        residuo.add("Tablet/Ipad");
        residuo.add("Tarjeta Electronica");
        residuo.add("Teclado");
        residuo.add("Telefono");
        residuo.add("Smart Tv / tv");
        residuo.add("Unidad de CD");
        residuo.add("Ventilador");


        resiImage = new ArrayList<Integer>();
        resiImage.add(R.drawable.ic_camara);
        resiImage.add(R.drawable.ic_cargador);
        resiImage.add(R.drawable.ic_cctv);
        resiImage.add(R.drawable.ic_celular);
        resiImage.add(R.drawable.ic_consola);
        resiImage.add(R.drawable.ic_control);
        resiImage.add(R.drawable.ic_cpu);
        resiImage.add(R.drawable.ic_disco);
        resiImage.add(R.drawable.ic_fuente);
        resiImage.add(R.drawable.ic_hervidor);
        resiImage.add(R.drawable.ic_impresora);
        resiImage.add(R.drawable.ic_juguete);
        resiImage.add(R.drawable.ic_laptop);
        resiImage.add(R.drawable.ic_lavadora);
        resiImage.add(R.drawable.ic_licuadora);
        resiImage.add(R.drawable.ic_microondas);
        resiImage.add(R.drawable.ic_mouse);
        resiImage.add(R.drawable.ic_monitor);
        resiImage.add(R.drawable.ic_plancha);
        resiImage.add(R.drawable.ic_proyector);
        resiImage.add(R.drawable.ic_dvd);
        resiImage.add(R.drawable.ic_mp3);
        resiImage.add(R.drawable.ic_vhs);
        resiImage.add(R.drawable.ic_router);
        resiImage.add(R.drawable.ic_scanner);
        resiImage.add(R.drawable.ic_tablet);
        resiImage.add(R.drawable.ic_tarjeta);
        resiImage.add(R.drawable.ic_teclado);
        resiImage.add(R.drawable.ic_telefono);
        resiImage.add(R.drawable.ic_tv);
        resiImage.add(R.drawable.ic_cd);
        resiImage.add(R.drawable.ic_ventilador);


        RaeeAdapter raeeAdapter = new RaeeAdapter(this, R.layout.raee_item, residuo, resiImage);
        gridView2.setAdapter(raeeAdapter);

        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            showDialog(view, position);
            }
        });

        numero = (EditText) findViewById(R.id.edit_Numero);
        mas = (Button) findViewById(R.id.btn_mas);
        menos = (Button) findViewById(R.id.btn_menos);
    }
    public  void showDialog(View view, int position){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.residuo_dialogo);
        dialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
        TextView textView = dialog.findViewById(R.id.dialog_residuo);
        ImageView imageView = dialog.findViewById(R.id.dialog_residuoImg);
        final EditText numero = (EditText) dialog.findViewById(R.id.edit_Numero);
        Button mas = (Button) dialog.findViewById(R.id.btn_mas);
        Button menos = (Button) dialog.findViewById(R.id.btn_menos);
        imageView.setImageResource(resiImage.get(position));
        textView.setText(residuo.get(position));

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            contador++;
            numero.setText(Integer.toString(contador));
            }
        });

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador--;
                numero.setText(Integer.toString(contador));
            }
        });
        imageView.setImageResource(resiImage.get(position));
        textView.setText(residuo.get(position));
        dialog.show();
    }
}
