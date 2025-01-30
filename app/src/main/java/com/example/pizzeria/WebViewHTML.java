package com.example.pizzeria;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Servicios.ServicioDatos;

public class WebViewHTML extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view_html);

        ServicioDatos.loadData(this, "color");
        if (ServicioDatos.loadData(this, "color") != null) {
            getWindow().getDecorView().setBackgroundColor(Integer.parseInt(ServicioDatos.loadData(this, "color")));
        }

        WebView webView = (WebView)findViewById(R.id.webViewPagina);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/index.html");

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