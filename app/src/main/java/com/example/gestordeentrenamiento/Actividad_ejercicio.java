package com.example.gestordeentrenamiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Actividad_ejercicio extends AppCompatActivity {
    private GestorGymDbHelper dbHelper;
    private SQLiteDatabase db;

    private RecyclerView recyclerView;
    private EditText inputNombreEjercicio;
    private EditText inputSeries ;
    private EditText inputRepeticiones;

    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_ejercicio);

        String parametroRecibido = getIntent().getStringExtra("clave_parametro");


        textView =findViewById(R.id.tNombreRutina);
        textView.setText(parametroRecibido);

        dbHelper = new GestorGymDbHelper(getApplicationContext(), "misentrenos.db");
        db = dbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.listaDeEjercicios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inputNombreEjercicio = findViewById(R.id.entradaNombreEjercicio);
        inputRepeticiones = findViewById(R.id.entradaRepeticiones);
        inputSeries=findViewById(R.id.entradaSeries);

        //updateRoutineList();

        List<Ejercicio> ejercicios = obtenerEjercicios((String)textView.getText());
        // Crea un adaptador y configúralo en el RecyclerView
        EjercicioAdapter adapter = new EjercicioAdapter(this, ejercicios);


        recyclerView.setAdapter(adapter);
    }
    private List<Ejercicio> obtenerEjercicios(String nombreSesion) {
        List<Ejercicio> ejercicios = new ArrayList<>();

        // Obtener los ejercicios asociados a la sesión
        String[] columnsEjercicio = {
                GestorGymContract.TablaEjercicios.NOMBRE,
                GestorGymContract.TablaEjercicios.SERIES,
                GestorGymContract.TablaEjercicios.REPETICIONES
        };

        String selectionEjercicio = GestorGymContract.TablaEjercicios.NOMBRE_SESION + "=?";
        String[] selectionArgsEjercicio = {nombreSesion};

        Cursor cursorEjercicio = db.query(
                GestorGymContract.TablaEjercicios.TABLE_NAME,
                columnsEjercicio,
                selectionEjercicio,
                selectionArgsEjercicio,
                null,
                null,
                null
        );

        while (cursorEjercicio.moveToNext()) {
            String nombreEjercicio = cursorEjercicio.getString(cursorEjercicio.getColumnIndexOrThrow(GestorGymContract.TablaEjercicios.NOMBRE));
            String series = cursorEjercicio.getString(cursorEjercicio.getColumnIndexOrThrow(GestorGymContract.TablaEjercicios.SERIES));
            String repeticiones = cursorEjercicio.getString(cursorEjercicio.getColumnIndexOrThrow(GestorGymContract.TablaEjercicios.REPETICIONES));

            ejercicios.add(new Ejercicio(nombreEjercicio, series, repeticiones));
        }

        cursorEjercicio.close();

        return ejercicios;
    }

    public void onClick(View view ){
        if(view.getId() == R.id.buttonCrearEjercicio){
            insertarEjercicio();
        }
    }
    public void  insertarEjercicio(){

        String nombreEjercicio = inputNombreEjercicio.getText().toString();
        String reps = inputRepeticiones.getText().toString();
        String series = inputSeries.getText().toString();

        if (nombreEjercicio.isEmpty() ) {
            showToast("Por favor, ingresa un nombre de ejercicio");
        } else if(reps.isEmpty()){
            showToast("Por favor, ingresa numero repeticiones");

        }else  if(series.isEmpty()){
            showToast("Por favor, ingresa numero series");
        }else{
            ContentValues values = new ContentValues();
            values.put(GestorGymContract.TablaEjercicios.NOMBRE_SESION, textView.getText().toString());
            values.put(GestorGymContract.TablaEjercicios.NOMBRE, nombreEjercicio);
            values.put(GestorGymContract.TablaEjercicios.SERIES, reps);
            values.put(GestorGymContract.TablaEjercicios.REPETICIONES, series);

            db.insert(GestorGymContract.TablaEjercicios.TABLE_NAME, null,values);
            showToast("Rutina añadida con éxito");
            updateRoutineList();
            inputSeries.setText("");
            inputRepeticiones.setText("");
            inputNombreEjercicio.setText("");
        }
        hideSoftKeyboard(inputNombreEjercicio);
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void updateRoutineList() {
        List<Ejercicio> ejercicios = obtenerEjercicios(textView.getText().toString());
        EjercicioAdapter adapter = new EjercicioAdapter(this, ejercicios);
        recyclerView.setAdapter(adapter);
    }
    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}