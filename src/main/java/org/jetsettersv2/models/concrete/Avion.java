package org.jetsettersv2.models.concrete;

import java.util.Objects;

public class Avion implements Comparable<Avion>{
    private String modelo;
    private String matricula;
    private int capacidadTripulanteCabina;
    private int capacidadTripulanteTecnico;
    private int capacidadPasajeros;
    private boolean enMantenimiento;

    // <<<<<<<METODOS IMPRESION>>>>>>>

    public void imprimir(){
        System.out.println("Modelo...............................: " + this.modelo);
        System.out.println("Matricula............................: " + this.matricula);
        System.out.println("Capacidad de tripulante cabina.......: " + this.capacidadTripulanteCabina);
        System.out.printf("Capacidad de tripulante tecnico.......: " + this.capacidadTripulanteTecnico);
        System.out.println("Capacidad de pasajeros...............: " + this.capacidadPasajeros);
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

    public int getCapacidadTripulanteCabina() {
        return capacidadTripulanteCabina;
    }

    public int getCapacidadTripulanteTecnico() {return capacidadTripulanteTecnico; }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
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

    public void setCapacidadTripulanteCabina(int capacidadTripulanteCabina) {
        this.capacidadTripulanteCabina = capacidadTripulanteCabina;
    }

    public void setCapacidadTripulanteTecnico(int capacidadTripulanteTecnico) {
        this.capacidadTripulanteTecnico = capacidadTripulanteTecnico;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
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

    public Avion capacidadTripulantesCabina(int capacidadTripulanteCabina) {
        this.capacidadTripulanteCabina = capacidadTripulanteCabina;
        return this;
    }

    public Avion capacidadTripulantesTecnicos(int capacidadTripulanteTecnico) {
        this.capacidadTripulanteTecnico = capacidadTripulanteTecnico;
        return this;
    }

    public Avion capacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
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
                ", capacidadTripulanteCabina=" + capacidadTripulanteCabina +
                ", capacidadTripulanteTecnico=" + capacidadTripulanteTecnico +
                ", capacidadPasajeros=" + capacidadPasajeros +
                ", enMantenimiento=" + enMantenimiento +
                '}';
    }

    // <<<<<<<EQUALS & HASHCODE>>>>>>>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Avion)) return false;
        Avion avion = (Avion) o;
        return capacidadTripulanteCabina == avion.capacidadTripulanteCabina &&
                capacidadTripulanteTecnico == avion.capacidadTripulanteTecnico &&
                capacidadPasajeros == avion.capacidadPasajeros &&
                enMantenimiento == avion.enMantenimiento &&
                modelo.equals(avion.modelo) &&
                matricula.equals(avion.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelo, matricula, capacidadTripulanteCabina, capacidadTripulanteTecnico ,capacidadPasajeros, enMantenimiento);
    }

    // <<<<<<<COMPARE TO>>>>>>>

    @Override
    public int compareTo(Avion o) {
        return this.matricula.compareTo(o.matricula);
    }

}
