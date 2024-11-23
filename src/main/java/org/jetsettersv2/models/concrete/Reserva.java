package org.jetsettersv2.models.concrete;

import org.jetsettersv2.enums.EstadoReserva;
import org.jetsettersv2.utilities.Fecha;

public class Reserva {
    //Atributos
    private Fecha fechaReserva;
    private UsuarioCliente usuarioLogueado;
    private Vuelo vuelo;
    private EstadoReserva estadoReserva;
    private String numeroReserva;

    //Constructor
    public Reserva() {

    }

    //Getters and Setters
    public Fecha getFechaReserva() {
        return fechaReserva;
    }

    public Reserva fechaReserva(Fecha fechaReserva) {
        this.fechaReserva = fechaReserva;
        return this;
    }


    public void setFechaReserva(Fecha fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    //-------------------------------------------------------------


    public UsuarioCliente getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(UsuarioCliente usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    //-------------------------------------------------------------
    public Vuelo getVuelo() {
        return vuelo;
    }

    public Reserva vuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
        return this;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    //-------------------------------------------------------------
    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public Reserva estadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
        return this;
    }

    public void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    //-------------------------------------------------------------
    public String getNumeroReserva() {
        return numeroReserva;
    }

    public Reserva numeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
        return this;
    }

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    //toString
    @Override
    public String toString() {
        return "Reserva: " + '\n' +
                "Fecha Reserva: " + fechaReserva + '\n' +
                "Pasajero: " + usuarioLogueado + '\n' +
                "Vuelo: " + vuelo + '\n' +
                "Estado Reserva: " + estadoReserva + '\n' +
                "Numero Reserva: " + numeroReserva + '\n';
    }

    //Metodos propios
    //Mostrar reserva
    public void mostrar(){
        System.out.println("<<< Reserva >>>");
        System.out.println("Fecha de reserva.............: " + this.fechaReserva);
        System.out.println("Pasajero.....................: " + this.usuarioLogueado);
        System.out.println("Vuelo........................: " + this.vuelo);
        System.out.println("Estado de reserva............: " + this.estadoReserva);
        System.out.println("Numero de reserva............: " + this.numeroReserva);
}


}

