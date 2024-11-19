package org.jetsettersv2.models.concrete;

import org.jetsettersv2.enums.Aeropuerto;

import java.time.Duration;

public class Ruta {
    private Aeropuerto origen;
    private Aeropuerto destino;
    private double distanciaKM;
    private Duration duracion; // Duración del vuelo en segundos

    // <<<<<<<METODOS IMPRESION>>>>>>>

    public void imprimir(){
        System.out.println("Origen...............................: " + this.origen);
        System.out.println("Destino..............................: " + this.destino);
        System.out.println("Distancia............................: " + this.distanciaKM + " km");
        System.out.println("Duración.............................: " + this.duracion.toHours() + " horas");
    }

    // <<<<<<<CONSTRUCTORS>>>>>>>

    public Ruta() {
    }

    // <<<<<<<GETTERS>>>>>>>

    public Aeropuerto getOrigen() {
        return origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public double getDistanciaKM() {
        return distanciaKM;
    }

    public Duration getDuración() {
        return duracion; // retorna la duración en segundos
    }

    // <<<<<<<SETTERS>>>>>>>

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public void setDistanciaKM(double distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public void setDuración(Duration duracion) {
        this.duracion = duracion;
    }

    // <<<<<<<BUILDERS>>>>>>>

    public Ruta origen(Aeropuerto origen) {
        this.origen = origen;
        return this;
    }

    public Ruta destino(Aeropuerto destino) {
        this.destino = destino;
        return this;
    }

    public Ruta distanciaKM(double distanciaKM) {
        this.distanciaKM = distanciaKM;
        return this;
    }

    public Ruta duración(Duration duración) {
        this.duracion = duración;
        return this;
    }

    // <<<<<<<TO STRING>>>>>>>

    @Override
    public String toString() {
        return "Ruta{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", distanciaKM=" + distanciaKM +
                ", duración=" + duracion +
                '}';
    }

    // <<<<<<<EQUALS>>>>>>>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ruta)) return false;

        Ruta ruta = (Ruta) o;

        if (Double.compare(ruta.distanciaKM, distanciaKM) != 0) return false;
        if (origen != ruta.origen) return false;
        if (destino != ruta.destino) return false;
        return duracion.equals(ruta.duracion);
    }

    // <<<<<<<HASHCODE>>>>>>>

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = origen.hashCode();
        result = 31 * result + destino.hashCode();
        temp = Double.doubleToLongBits(distanciaKM);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + duracion.hashCode();
        return result;
    }

}
