package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import POJOs.TipoIngredientes;
import POJOs.TipoTamanoPizzas;

public class SeleccionIngredientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_ingredientes);

        Spinner spinnerSizes = findViewById(R.id.spinnerSizes);
        ListView listViewIngredients = findViewById(R.id.listViewIngredients);
        Button btnConfirmSelection = findViewById(R.id.btnConfirmSelection);

        TipoTamanoPizzas[] sizes = TipoTamanoPizzas.values();
        ArrayAdapter<TipoTamanoPizzas> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSizes.setAdapter(sizeAdapter);

        TipoIngredientes[] ingredientes = TipoIngredientes.values();
        ArrayAdapter<TipoIngredientes> ingredientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, ingredientes);
        listViewIngredients.setAdapter(ingredientAdapter);

        btnConfirmSelection.setOnClickListener(v -> {
            int selectedCount = listViewIngredients.getCheckedItemCount();
            if (selectedCount != 3) {
                Toast.makeText(this, "Please select exactly 3 ingredients", Toast.LENGTH_SHORT).show();
                return;
            }

            TipoTamanoPizzas selectedSize = (TipoTamanoPizzas) spinnerSizes.getSelectedItem();
            StringBuilder selectedIngredients = new StringBuilder();
            for (int i = 0; i < listViewIngredients.getCount(); i++) {
                if (listViewIngredients.isItemChecked(i)) {
                    selectedIngredients.append(listViewIngredients.getItemAtPosition(i).toString()).append(", ");
                }
            }

            if (selectedIngredients.length() > 0) {
                selectedIngredients.setLength(selectedIngredients.length() - 2);
            }

            Intent intent = new Intent(SeleccionIngredientes.this, ConfirmacionPedido.class);
            intent.putExtra("pizza", selectedIngredients.toString());
            intent.putExtra("tamano", selectedSize.getNombre() + " - " + selectedSize.getPrecio() + "€");
            startActivity(intent);

            Toast.makeText(this, "Selected Size: " + selectedSize.getNombre() + " ($" + selectedSize.getPrecio() + ")\nSelected Ingredients: " + selectedIngredients, Toast.LENGTH_LONG).show();
        });
    }
}