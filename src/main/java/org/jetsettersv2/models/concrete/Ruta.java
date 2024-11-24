package org.jetsettersv2.models.concrete;

import org.jetsettersv2.models.concrete.Aeropuerto;

public class Ruta {
    private Aeropuerto origen;
    private Aeropuerto destino;
    private double distanciaKM;
    private long duracion; // Duración del vuelo en segundos

    // <<<<<<<METODOS IMPRESION>>>>>>>

    public void imprimir() {
        System.out.println("Ruta "+this.origen.getCodigo()+"-"+this.destino.getCodigo());
        System.out.println("Origen............................:" + this.origen.getCiudad());
        System.out.println("Aeropuerto............................:" + this.origen.getNombre());
        System.out.println("Destino............................:" + this.destino.getCiudad());
        System.out.println("Aeropuerto............................:" + this.destino.getNombre());
        System.out.println("Distancia............................: " + this.distanciaKM + " km");
        // Convertimos los segundos en horas, minutos y segundos
        long horas = duracion / 3600;
        long minutos = (duracion % 3600) / 60;
        long segundos = duracion % 60;
        System.out.println("Duración.............................: " + horas + " horas " + minutos + " minutos " + segundos + " segundos");
        System.out.println("....................................."+ "\n");
    }

    // <<<<<<<CONSTRUCTORES>>>>>>>

    public Ruta() {
    }

    public Ruta(Aeropuerto origen, Aeropuerto destino, double distanciaKM, long duracion) {
        this.origen = origen;
        this.destino = destino;
        this.distanciaKM = distanciaKM;
        this.duracion = duracion;
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

    public long getDuracion() {
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

    public void setDuracion(long duracion) {
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

    public Ruta duracion(long duracion) {
        this.duracion = duracion;
        return this;
    }

    // <<<<<<<TO STRING>>>>>>>

    @Override
    public String toString() {
        return "....................................."+"\n"+
                "Origen" +"\n"+ this.origen + "\n" +
                "Destino" +"\n"+ this.destino + "\n" +
                "Distancia............................: " + distanciaKM + " km\n" +
                "Duración.............................: " + duracion / 3600 + " horas " + (duracion % 3600) / 60 + " minutos " + duracion % 60 + " segundos\n"+
                "....................................."+ "\n";
    }

    // <<<<<<<EQUALS>>>>>>>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ruta)) return false;

        Ruta ruta = (Ruta) o;

        if (Double.compare(ruta.distanciaKM, distanciaKM) != 0) return false;
        if (!origen.equals(ruta.origen)) return false;
        if (!destino.equals(ruta.destino)) return false;
        return duracion == ruta.duracion;
    }

    // <<<<<<<HASHCODE>>>>>>>

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = origen != null ? origen.hashCode() : 0;
        result = 31 * result + (destino != null ? destino.hashCode() : 0);
        temp = Double.doubleToLongBits(distanciaKM);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + Long.hashCode(duracion);
        return result;
    }
}
