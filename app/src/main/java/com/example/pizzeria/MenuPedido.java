package com.example.pizzeria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Servicios.ServicioDatos;

public class MenuPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_pedido);

        ServicioDatos.loadData(this, "color");
        if (ServicioDatos.loadData(this, "color") != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
        }


        Button btnPizzaFavorita = findViewById(R.id.btnPizzaFavorita);
        Button btnPersonalizada = findViewById(R.id.btnPersonalizada);
        Button btnPredeterminadas = findViewById(R.id.btnPredeterminadas);
        Button btnVolverInicio = findViewById(R.id.btnVolverInicio);


        btnPizzaFavorita.setOnClickListener(v -> {

            if (ServicioDatos.loadData(this, "favorita").equalsIgnoreCase("true")) {

                if (ServicioDatos.loadData(this, "favorita").equalsIgnoreCase("true")) {
                    SharedPreferences prefs = getSharedPreferences("config", MODE_PRIVATE);
                    String lastPizza = prefs.getString("lastPizza", null);
                    String lastTamano = prefs.getString("lastTamano", null);

                    if (lastPizza != null && lastTamano != null) {

                        Intent intent = new Intent(MenuPedido.this, ConfirmacionPedido.class);
                        intent.putExtra("pizza", lastPizza);
                        intent.putExtra("tamano", lastTamano);
                        startActivity(intent);

                    }

                }

            } else {
                Toast.makeText(this, "Activa la pizza favorita en la configuracion", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuPedido.this, Configuracion.class);
                startActivity(intent);
            }


        });

        btnPredeterminadas.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPedido.this, ListaPizzasPredeterminadas.class);
            startActivity(intent);
        });

        btnPersonalizada.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPedido.this, SeleccionIngredientes.class);
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

    @Override
    protected void onResume() {
        super.onResume();
        String color = ServicioDatos.loadData(this, "color");
        if (color != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(color));
        }
    }

}