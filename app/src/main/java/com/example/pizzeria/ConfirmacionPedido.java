package com.example.pizzeria;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Servicios.ServicioDatos;

public class ConfirmacionPedido extends AppCompatActivity {

    private static final String CHANNEL_ID = "pedido_channel";
    TextView pizzaSeleccionada;
    TextView tamanoSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmacion_pedido);

        createNotificationChannel();

        ServicioDatos.loadData(this, "color");
        if (ServicioDatos.loadData(this, "color") != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
        }

        String pizza = getIntent().getStringExtra("pizza");
        String tamano = getIntent().getStringExtra("tamano");

        Button btnConfirmarPedido = findViewById(R.id.btnConfirmarPedido);
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

        btnConfirmarPedido.setOnClickListener(v -> {
            sendNotification();
            Intent intent = new Intent(ConfirmacionPedido.this, MenuPrincipal.class);
            startActivity(intent);
        });

        getSharedPreferences("config", MODE_PRIVATE).edit()
                .putString("lastPizza", pizza)
                .putString("lastTamano", tamano)
                .apply();

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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Pedido Channel";
            String description = "Channel for pedido notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_confirmacion_pedido)
                .setContentTitle("Pedido Confirmado")
                .setContentText("Tu pedido ha sido confirmado.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

}