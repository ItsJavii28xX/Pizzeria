package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import DAOs.DAOUsuario;
import Servicios.ServicioDatos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ServicioDatos.loadData(this, "color");
        if (ServicioDatos.loadData(this, "color") != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
        }

        if (ServicioDatos.loadData(this, "usuario") != null && ServicioDatos.loadData(this, "contrasena") != null) {
            Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
            startActivity(intent);
        }

        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView txtInputUsuario = findViewById(R.id.txtInputUsuario);
        TextView txtPasswordContrasena = findViewById(R.id.txtPasswordContrasena);
        Switch switchRecordar = findViewById(R.id.switchRecordar);

        btnRegistrarse.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrarseActivity.class);
            startActivity(intent);
        });

        btnIniciarSesion.setOnClickListener(v -> {
            if(txtInputUsuario.getText().toString().isEmpty())
            {
                txtInputUsuario.setError("Introduce un usuario");
            }
            else if(txtPasswordContrasena.getText().toString().isEmpty())
            {
                txtPasswordContrasena.setError("Introduce una contraseña");
            }

            Boolean inicioCorrecto = DAOUsuario.getInstancia().comprobarUsuarioContrasena(txtInputUsuario.getText().toString(), txtPasswordContrasena.getText().toString());

            if(inicioCorrecto)
            {
                if (switchRecordar.isChecked()) {

                    ServicioDatos.saveData(this, "usuario", txtInputUsuario.getText().toString());
                    ServicioDatos.saveData(this, "contrasena", txtPasswordContrasena.getText().toString());

                }

                Intent intent = new Intent(MainActivity.this, MenuPrincipal.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
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