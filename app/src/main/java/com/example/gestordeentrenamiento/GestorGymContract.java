package com.example.gestordeentrenamiento;

import android.provider.BaseColumns;

public final class GestorGymContract {
    private GestorGymContract() {}

    public static abstract class TablaSesion implements BaseColumns {
        public static final String TABLE_NAME = "Sesion";
        public static final String NOMBRE = "nombre";
        //public static final String COLUMN_NAME_VAL = "valor";
    }

    public static abstract class TablaEjercicios implements BaseColumns {
        public static final String TABLE_NAME = "Ejercicio";

        public static  final String NOMBRE_SESION = "nombreSesion";
        public static final String NOMBRE = "nombre";

        public static final String SERIES = "series";

        public static final String REPETICIONES = "repeticiones";

    }
}
