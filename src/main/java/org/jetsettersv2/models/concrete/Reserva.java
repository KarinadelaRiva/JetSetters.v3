package org.jetsettersv2.models.concrete;

import org.jetsettersv2.utilities.Fecha;

public class Reserva {
    //Atributos
    private Fecha fechaReserva;
    private UsuarioCliente usuarioLogueado;
    private Vuelo vuelo;
    private String numeroReserva;
    private CheckIn checkIn;

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

    //-------------------------------------------------------------


    public CheckIn getCheckIn() {
        return checkIn;
    }

    public Reserva setCheckIn(Fecha fechaReserva ) {

        CheckIn checkInNuevo = new CheckIn()
                .estadoCheck(false)
                .fechaCheck(null);

        this.checkIn = checkInNuevo;
        return this;
    }

    //toString
    @Override
    public String toString() {
        return "Reserva: " + '\n' +
                "Fecha Reserva: " + fechaReserva + '\n' +
                "Pasajero: " + usuarioLogueado + '\n' +
                "Vuelo: " + vuelo + '\n' +
                //"Estado Reserva: " + estadoReserva + '\n' +
                "Numero Reserva: " + numeroReserva + '\n';
    }

    //Metodos propios
    //Mostrar reserva
    public void mostrar(){
        System.out.println("Fecha de reserva.............: " + this.fechaReserva);
        System.out.println("Nombre Pasajero..............: " + this.usuarioLogueado.getNombre());
        System.out.println("DNI Pasajero.................: " + this.usuarioLogueado.getDni());
        System.out.println("Vuelo........................: " + this.vuelo.getNroVuelo());
        System.out.println("Numero de reserva............: " + this.numeroReserva);
    }




}

