package com.example.gestordeentrenamiento;

public class Ejercicio {
    private String nombre;
    private String series;
    private String repeticiones;

    public Ejercicio(String nombre,String series,String repeticiones) {
        this.nombre = nombre;
        this.repeticiones=repeticiones;
        this.series=series;
    }

    public String getNombre() {
        return nombre;
    }
    public String getSeries(){
        return series;
    }
    public String getRepeticiones(){
        return repeticiones;
    }
}
