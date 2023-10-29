package com.example.cards1;


public class Medicamento {
    private int id;
    private String nombre;
    private String dosis;
    private String hora;
    private int frecuencia;
    public String getNombre() {
        return nombre;
    }

    public String getDosis() {
        return dosis;
    }

    public String getHora() {
        return hora;
    }
    public int getFreq() {return frecuencia;}
    public int getId(){
        return id;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    public void setFreq(int frecuencia){this.frecuencia = frecuencia;}
}