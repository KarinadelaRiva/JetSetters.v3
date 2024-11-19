package org.jetsettersv2.models.concrete;

import java.util.Objects;

public class Avion implements Comparable<Avion>{
    private String modelo;
    private String matricula;
    private int capacidadTripulantes;
    private int capacidadPasajeros;
    private int capacidadEquipaje;
    private boolean enMantenimiento;

    // <<<<<<<METODOS IMPRESION>>>>>>>

    public void imprimir(){
        System.out.println("Modelo...............................: " + this.modelo);
        System.out.println("Matricula............................: " + this.matricula);
        System.out.println("Capacidad de tripulantes.............: " + this.capacidadTripulantes);
        System.out.println("Capacidad de pasajeros...............: " + this.capacidadPasajeros);
        System.out.println("Capacidad de equipaje................: " + this.capacidadEquipaje);
        System.out.println("Estado disponibilidad................: " + this.imprimirMantenimiento());
    }

    public String imprimirMantenimiento(){
        if(this.enMantenimiento){
            return "Avión en mantenimiento";
        }else{
            return "Avión habilitado para volar";
        }
    }

    // <<<<<<<CONSTRUCTORS>>>>>>>

    public Avion() {
    }

    // <<<<<<<GETTERS>>>>>>>

    public String getModelo() {
        return modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getCapacidadTripulantes() {
        return capacidadTripulantes;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getCapacidadEquipaje() {
        return capacidadEquipaje;
    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    // <<<<<<<SETTERS>>>>>>>

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setCapacidadTripulantes(int capacidadTripulantes) {
        this.capacidadTripulantes = capacidadTripulantes;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public void setCapacidadEquipaje(int capacidadEquipaje) {
        this.capacidadEquipaje = capacidadEquipaje;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }

    // <<<<<<<BUILDERS>>>>>>>

    public Avion modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public Avion matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }

    public Avion capacidadTripulantes(int capacidadTripulantes) {
        this.capacidadTripulantes = capacidadTripulantes;
        return this;
    }

    public Avion capacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
        return this;
    }

    public Avion capacidadEquipaje(int capacidadEquipaje) {
        this.capacidadEquipaje = capacidadEquipaje;
        return this;
    }

    public Avion enMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
        return this;
    }

    // <<<<<<<TO STRING>>>>>>>

    @Override
    public String toString() {
        return "Avion{" +
                "modelo='" + modelo + '\'' +
                ", matricula='" + matricula + '\'' +
                ", capacidadTripulantes=" + capacidadTripulantes +
                ", capacidadPasajeros=" + capacidadPasajeros +
                ", capacidadEquipaje=" + capacidadEquipaje +
                ", enMantenimiento=" + enMantenimiento +
                '}';
    }

    // <<<<<<<EQUALS & HASHCODE>>>>>>>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;
        Avion avion = (Avion) o;
        return capacidadTripulantes == avion.capacidadTripulantes &&
                capacidadPasajeros == avion.capacidadPasajeros &&
                capacidadEquipaje == avion.capacidadEquipaje &&
                enMantenimiento == avion.enMantenimiento &&
                modelo.equals(avion.modelo) &&
                matricula.equals(avion.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo, matricula, capacidadTripulantes, capacidadPasajeros, capacidadEquipaje, enMantenimiento);
    }

    // <<<<<<<COMPARE TO>>>>>>>

    @Override
    public int compareTo(Avion o) {
        return this.matricula.compareTo(o.matricula);
    }

}
