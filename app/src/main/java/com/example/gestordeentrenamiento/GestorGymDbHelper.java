package com.example.gestordeentrenamiento;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gestordeentrenamiento.GestorGymContract;

public class GestorGymDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_ENTRIES_SESION =
            "CREATE TABLE " + GestorGymContract.TablaSesion.TABLE_NAME + " (" +
                    GestorGymContract.TablaSesion._ID + " INTEGER PRIMARY KEY," +
                    GestorGymContract.TablaSesion.NOMBRE + " TEXT UNIQUE" +
                    " )";

    public static final String SQL_DELETE_ENTRIES_SESION =
            "DROP TABLE IF EXISTS " + GestorGymContract.TablaSesion.TABLE_NAME;

    public static final String SQL_CREATE_ENTRIES_EJERCICIOS =
            "CREATE TABLE " + GestorGymContract.TablaEjercicios.TABLE_NAME + " (" +
                    GestorGymContract.TablaEjercicios._ID + " INTEGER PRIMARY KEY," +
                    GestorGymContract.TablaEjercicios.NOMBRE + " TEXT UNIQUE," +
                    GestorGymContract.TablaEjercicios.SERIES + " NUMBER," +
                    GestorGymContract.TablaEjercicios.REPETICIONES + " NUMBER," +
                    GestorGymContract.TablaEjercicios.NOMBRE_SESION + " TEXT" +
                    " )";

    public static final String SQL_DELETE_ENTRIES_EJERCICIOS =
            "DROP TABLE IF EXISTS " + GestorGymContract.TablaEjercicios.TABLE_NAME;

    public GestorGymDbHelper(Context context, String database_name) {
        super(context, database_name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Este método será invocado al establecer la conexión con la BD
        // en el caso de que la creación de la BD sea necesaria
        db.execSQL(SQL_CREATE_ENTRIES_SESION);
        db.execSQL(SQL_CREATE_ENTRIES_EJERCICIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Este método será invocado al establecer la conexión con la BD
        // en el caso de que la versión de la BD almacenada sea inferior a
        // la versión de la BD que queremos abrir (especificada por
        // DATABASE_VERSION proporcionada en el constructor de la clase)
        //
        // Una política de actualización simple: eliminar los datos almacenados
        // y comenzar de nuevo con una BD vacía
        db.execSQL(SQL_CREATE_ENTRIES_SESION);
        db.execSQL(SQL_CREATE_ENTRIES_EJERCICIOS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Este método será invocado al establecer la conexión con la BD
        // en el caso de que la versión de la BD almacenada sea superior a
        // la versión de la BD que queremos abrir (especificada por
        // DATABASE_VERSION proporcionada en el constructor de la clase)
        //
        // Una política de actualización simple: eliminar los datos almacenados
        // y comenzar de nuevo con una BD vacía
        db.execSQL(SQL_CREATE_ENTRIES_SESION);
        db.execSQL(SQL_CREATE_ENTRIES_EJERCICIOS);
        onCreate(db);
    }



}
