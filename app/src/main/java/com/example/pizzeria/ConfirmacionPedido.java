package com.example.pizzeria;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmacionPedido extends AppCompatActivity {

    TextView pizzaSeleccionada;
    TextView tamanoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmacion_pedido);

        String pizza = getIntent().getStringExtra("pizza");
        String tamano = getIntent().getStringExtra("tamano");

        pizzaSeleccionada = (TextView) findViewById(R.id.txtViewPizzaSeleccionadaConfirmacion);
        tamanoSeleccionado = (TextView) findViewById(R.id.txtViewTamanoSeleccionadoConfirmacion);
        TextView precio = (TextView) findViewById(R.id.txtViewPrecio);

        pizzaSeleccionada.setText(pizza);
        assert tamano != null;
        int index = tamano.indexOf(" - ");
        if(index != -1)
        {
            tamanoSeleccionado.setText(tamano.substring(0, index));
            precio.setText(tamano.substring(index + 3));
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}