package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import DAOs.DAOUsuario;

public class RegistrarseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarse);

        Button btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        TextInputEditText txtInputCorreo = findViewById(R.id.txtInputCorreo);
        TextInputEditText txtInputUsuarioRegistrarse = findViewById(R.id.txtInputUsuarioRegistrarse);
        EditText txtPasswordContrasenaRegistrarse = findViewById(R.id.txtPasswordContrasenaRegistrarse);

        btnRegistrarUsuario.setOnClickListener(v -> {
            if(!Objects.requireNonNull(txtInputCorreo.getText()).toString().isEmpty() && !txtPasswordContrasenaRegistrarse.getText().toString().isEmpty() && !Objects.requireNonNull(txtInputUsuarioRegistrarse.getText()).toString().isEmpty())
            {
                Boolean registroCorrecto = DAOUsuario.getInstancia().registrarUsuario(txtInputUsuarioRegistrarse.getText().toString(), txtPasswordContrasenaRegistrarse.getText().toString(), txtInputCorreo.getText().toString());
                if(registroCorrecto)
                {
                    Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrarseActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }

            }
            else if(txtInputCorreo.getText().toString().isEmpty())
            {
                txtInputCorreo.setError("Introduce un correo");
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
            else if(Objects.requireNonNull(txtInputUsuarioRegistrarse.getText()).toString().isEmpty())
            {
                txtInputUsuarioRegistrarse.setError("Introduce un usuario");
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }
            else if(txtPasswordContrasenaRegistrarse.getText().toString().isEmpty())
            {
                txtPasswordContrasenaRegistrarse.setError("Introduce una contraseña");
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}