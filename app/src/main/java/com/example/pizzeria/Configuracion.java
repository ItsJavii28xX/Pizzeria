package com.example.pizzeria;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        colorPreview = findViewById(R.id.colorPreview);
        Button btnPickColor = findViewById(R.id.btnPickColor);
        Button btnGuardarConfiguracion = findViewById(R.id.btnGuardarConfiguracion);

        // Establecer color inicial
        colorPreview.setBackgroundColor(selectedColor);

        btnPickColor.setOnClickListener(v -> openColorPicker());

        btnGuardarConfiguracion.setOnClickListener(v ->{
            //TODO: Guardar la configuración
            Toast.makeText(this, "Se han guardado los cambios", Toast.LENGTH_SHORT).show();
            finish();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}