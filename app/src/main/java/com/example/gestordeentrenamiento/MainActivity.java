package com.example.gestordeentrenamiento;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private GestorGymDbHelper dbHelper;
    private SQLiteDatabase db;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new GestorGymDbHelper(getApplicationContext(), "misentrenos.db");
        db = dbHelper.getWritableDatabase();
        recyclerView = findViewById(R.id.listRoutines);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        insertData("Rutina de pecho");
        List<Sesion> sesiones = obtenerSesiones();

        // Crea un adaptador y configúralo en el RecyclerView
        SesionAdapter adapter = new SesionAdapter(this, sesiones);
        recyclerView.setAdapter(adapter);
    }


    public void  insertData(String name){
        ContentValues values = new ContentValues();
        values.put(GestorGymContract.TablaSesion.NOMBRE, name);
        db.insert(GestorGymContract.TablaSesion.TABLE_NAME, null,values);
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

   /* public void viewData() {

        String[] columns = {GestorGymContract.TablaSesion.NOMBRE};
        Cursor cursor = db.query(GestorGymContract.TablaSesion.TABLE_NAME, columns, null, null, null, null, null);

    }

    */

}