package com.example.gestordeentrenamiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GestorGymDbHelper dbHelper;
    private SQLiteDatabase db;

    private RecyclerView recyclerView;
    private EditText inputRoutineTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new GestorGymDbHelper(getApplicationContext(), "misentrenos.db");
        db = dbHelper.getWritableDatabase();

        recyclerView = findViewById(R.id.listRoutines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        inputRoutineTitle = findViewById(R.id.inputRoutineTitle);

        updateRoutineList();

        List<Sesion> sesiones = obtenerSesiones();

        // Crea un adaptador y configúralo en el RecyclerView
        SesionAdapter adapter = new SesionAdapter(this, sesiones);
        recyclerView.setAdapter(adapter);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view){
        if (view.getId() == R.id.buttonAdd) {
            insertData();
        }
    }

    private void updateRoutineList() {
        List<Sesion> sesiones = obtenerSesiones();
        SesionAdapter adapter = new SesionAdapter(this, sesiones);
        recyclerView.setAdapter(adapter);
    }

    public void  insertData(){

        String name = inputRoutineTitle.getText().toString();

        if (!name.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put(GestorGymContract.TablaSesion.NOMBRE, name);
            db.insert(GestorGymContract.TablaSesion.TABLE_NAME, null,values);
            showToast("Rutina añadida con éxito");
            updateRoutineList();
            inputRoutineTitle.setText("");
        } else {
            showToast("Por favor, ingresa un título de rutina");
        }
    }


    // Método para obtener sesiones de la base de datos
    private List<Sesion> obtenerSesiones() {
        List<Sesion> sesiones = new ArrayList<>();

        String[] columns = {GestorGymContract.TablaSesion.NOMBRE};
        Cursor cursor = db.query(GestorGymContract.TablaSesion.TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String nombreSesion = cursor.getString(cursor.getColumnIndexOrThrow(GestorGymContract.TablaSesion.NOMBRE));
            sesiones.add(new Sesion(nombreSesion));
        }

        cursor.close();

        return sesiones;
    }


}