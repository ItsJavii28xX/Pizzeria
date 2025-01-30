package com.example.pizzeria;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import POJOs.TipoTamanoPizzas;
import POJOs.TipoPizzasPredefinidas;
import Servicios.ServicioDatos;

public class ListaPizzasPredeterminadas extends AppCompatActivity {
    private ListView listView;
    private ListView listViewTamanos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_pizzas_predeterminadas);

        ServicioDatos.loadData(this, "color");
        if (ServicioDatos.loadData(this, "color") != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
        }

        ListView listView = findViewById(R.id.listViewPizzas);
        ListView listViewTamanos = findViewById(R.id.listViewTamanos);
        Button btnSeleccionarPizza = findViewById(R.id.btnSeleccionarPizza);

       btnSeleccionarPizza.setOnClickListener(v -> {
            int selectedPizzaPosition = listView.getCheckedItemPosition();
            int selectedTamanoPosition = listViewTamanos.getCheckedItemPosition();

            if (selectedPizzaPosition != ListView.INVALID_POSITION && selectedTamanoPosition != ListView.INVALID_POSITION)
            {
                String selectedPizza = (String) listView.getItemAtPosition(selectedPizzaPosition);
                String selectedTamano = (String) listViewTamanos.getItemAtPosition(selectedTamanoPosition);

                Intent intent = new Intent(ListaPizzasPredeterminadas.this, ConfirmacionPedido.class);
                intent.putExtra("pizza", selectedPizza);
                intent.putExtra("tamano", selectedTamano);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(ListaPizzasPredeterminadas.this, "Selecciona una pizza y un tamaño", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayList<String> nombresPizzas = new ArrayList<String>();

        for (int i = 0; i < TipoPizzasPredefinidas.values().length; i++)
        {
            String nombrePizza = TipoPizzasPredefinidas.values()[i].name();
            nombrePizza = nombrePizza.replace("_", " ");

            if (!nombrePizza.isEmpty())
            {
                nombrePizza = nombrePizza.toLowerCase();
                nombrePizza = nombrePizza.substring(0, 1).toUpperCase() + nombrePizza.substring(1);
            }

            nombresPizzas.add(nombrePizza);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, nombresPizzas.toArray(new String[0]));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayList<String> nombresTamanos = new ArrayList<String>();

        for (int i = 0; i < TipoTamanoPizzas.values().length; i++)
        {
            nombresTamanos.add(TipoTamanoPizzas.values()[i].getNombre() + " - " + TipoTamanoPizzas.values()[i].getPrecio() + "€");
        }

        ArrayAdapter<String> adapterTamanos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, nombresTamanos.toArray(new String[0]));

        listViewTamanos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView.setAdapter(adapter);
        listViewTamanos.setAdapter(adapterTamanos);


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