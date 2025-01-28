package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_pedido);

        Switch switchFavorita = findViewById(R.id.switchFavorita);
        Button btnPizzaFavorita = findViewById(R.id.btnPizzaFavorita);
        Button btnPersonalizada = findViewById(R.id.btnPersonalizada);
        Button btnPredeterminadas = findViewById(R.id.btnPredeterminadas);
        Button btnVolverInicio = findViewById(R.id.btnVolverInicio);


        btnPizzaFavorita.setOnClickListener(v -> {

            if (switchFavorita.isChecked()) {
                //todo
            } else {
                Toast.makeText(this, "Activa la pizza favorita en la configuracion", Toast.LENGTH_SHORT).show();
                try {
                    wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Intent intent = new Intent(MenuPedido.this, Configuracion.class);
                startActivity(intent);
            }


        });

        btnPredeterminadas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPedido.this, ListaPizzasPredeterminadas.class);
            startActivity(intent);
        });

        btnVolverInicio.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPedido.this, MenuPrincipal.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}