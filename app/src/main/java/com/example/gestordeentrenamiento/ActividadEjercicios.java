package com.example.gestordeentrenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActividadEjercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ejercicios);

        String parametroRecibido = getIntent().getStringExtra("clave_parametro");

        TextView textView = findViewById(R.id.NombreRutina);
        textView.setText(parametroRecibido);
    }
}