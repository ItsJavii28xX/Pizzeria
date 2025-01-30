package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Servicios.ServicioDatos;
import yuku.ambilwarna.AmbilWarnaDialog;

public class Configuracion extends AppCompatActivity {
    private int selectedColor = Color.BLUE;
    private View colorPreview;

    private void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, selectedColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(Configuracion.this, "Selección cancelada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                selectedColor = color;
                ServicioDatos.saveData(Configuracion.this, "color", String.valueOf(color));
                colorPreview.setBackgroundColor(color);
                Toast.makeText(Configuracion.this, "Se ha seleccionado un nuevo color", Toast.LENGTH_SHORT).show();
            }
        });

        colorPicker.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_configuracion);


        ServicioDatos.loadData(this, "color");
        if (ServicioDatos.loadData(this, "color") != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
        }

        Switch switchRecordar = findViewById(R.id.switchFavorita);
        colorPreview = findViewById(R.id.colorPreview);
        Button btnPickColor = findViewById(R.id.btnPickColor);
        Button btnGuardarConfiguracion = findViewById(R.id.btnGuardarConfiguracion);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        SharedPreferences prefs = getSharedPreferences("config", MODE_PRIVATE);
        boolean isFavorita = prefs.getBoolean("favorita", false);
        switchRecordar.setChecked(isFavorita);

        switchRecordar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("favorita", isChecked);
            editor.apply();
            ServicioDatos.saveData(this, "favorita", isChecked ? "true" : "false");
        });

        colorPreview.setBackgroundColor(selectedColor);

        btnPickColor.setOnClickListener(v -> openColorPicker());

        btnGuardarConfiguracion.setOnClickListener(v ->{
            ServicioDatos.loadData(this, "color");
            if (ServicioDatos.loadData(this, "color") != null) {
                getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
            }
            Toast.makeText(this, "Se han guardado los cambios", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnCerrarSesion.setOnClickListener(v -> {
            ServicioDatos.deleteData(this, "usuario");
            ServicioDatos.deleteData(this, "contrasena");
            Intent intent = new Intent(Configuracion.this, MainActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}