package com.example.pizzeria;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        mostrarDialogo();
    }

    private void mostrarDialogo()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Deseas salir?");
        builder.setMessage("Elige una opción:");

        builder.setPositiveButton("Salir", (dialog, which) -> finishAffinity());

        builder.setNegativeButton("Continuar", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);

        Button btnIrWeb = findViewById(R.id.btnIrWeb);
        Button btnIrPedido = findViewById(R.id.btnIrPedido);
        Button btnConfiguracion = findViewById(R.id.btnConfiguracion);

        btnIrWeb.setOnClickListener( v -> {
            Intent intent = new Intent(this, WebViewHTML.class);
            startActivity(intent);
        });

        btnIrPedido.setOnClickListener( v -> {
            Intent intent = new Intent(this, MenuPedido.class);
            startActivity(intent);
        });

        btnConfiguracion.setOnClickListener( v -> {
           Intent intent = new Intent(this, Configuracion.class);
           startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}