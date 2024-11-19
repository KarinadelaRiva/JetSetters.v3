package org.jetsettersv2.models.abstracts;

public abstract class Equipaje {
    private int id;
    private double peso;
    private boolean conexion;

    public Equipaje(int id, double peso, boolean conexion) {
        this.id = id;
        this.peso = peso;
        this.conexion = conexion;
    }

    public boolean isConexion() {
        return conexion;
    }

    public Equipaje setConexion(boolean conexion) {
        this.conexion = conexion;
        return this;
    }

    public double getPeso() {
        return peso;
    }

    public Equipaje setPeso(double peso) {
        this.peso = peso;
        return this;
    }

    public int getId() {
        return id;
    }

    public Equipaje setId(int id) {
        this.id = id;
        return this;
    }


}
