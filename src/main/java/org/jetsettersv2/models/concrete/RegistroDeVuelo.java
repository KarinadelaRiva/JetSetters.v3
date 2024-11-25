package org.jetsettersv2.models.concrete;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.CapacidadExcedidaException;



public class RegistroDeVuelo {
    //Atributos
    private Avion avion;
    private CheckIn checkIn;
    private ArrayListGeneric<TripulacionCabina> registroTripulanteCabina;
    private ArrayListGeneric<TripulacionTecnica> registroTripulacionTecnica;
    private ArrayListGeneric<UsuarioCliente>registroPasajeros;


    //Constructor
    public RegistroDeVuelo() {

    }

    //Getters and setters
    public Avion getAvion() {
        return avion;
    }

    public RegistroDeVuelo avion(Avion avion) {
        this.avion = avion;
        return this;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    //--------------------------------------------------------------------

    public CheckIn getCheckIn() {
        return checkIn;
    }

    public RegistroDeVuelo checkIn(CheckIn checkIn) {
        this.checkIn = checkIn;
        return this;
    }

    public void setCheckIn(CheckIn checkIn) {
        this.checkIn = checkIn;
    }

    //--------------------------------------------------------------------
    public ArrayListGeneric<TripulacionCabina> getRegistroTripulanteCabina() {
        return registroTripulanteCabina;
    }

    public RegistroDeVuelo registroTripulanteCabina(ArrayListGeneric<TripulacionCabina> registroTripulanteCabina) {
        this.registroTripulanteCabina = registroTripulanteCabina;
        return this;
    }

    public void setRegistroTripulanteCabina(ArrayListGeneric<TripulacionCabina> registroTripulanteCabina) {
        this.registroTripulanteCabina = registroTripulanteCabina;
    }

    //--------------------------------------------------------------------
    public ArrayListGeneric<TripulacionTecnica> getRegistroTripulacionTecnica() {
        return registroTripulacionTecnica;
    }

    public RegistroDeVuelo registroTripulacionTecnica(ArrayListGeneric<TripulacionTecnica> registroTripulacionTecnica) {
        this.registroTripulacionTecnica = registroTripulacionTecnica;
        return this;
    }

    public void setRegistroTripulacionTecnica(ArrayListGeneric<TripulacionTecnica> registroTripulacionTecnica) {
        this.registroTripulacionTecnica = registroTripulacionTecnica;
    }

    //--------------------------------------------------------------------
    public ArrayListGeneric<UsuarioCliente> getRegistroPasajeros() {
        return registroPasajeros;
    }

    public RegistroDeVuelo registroPasajeros(ArrayListGeneric<UsuarioCliente> registroPasajeros) {
        this.registroPasajeros = registroPasajeros;
        return this;
    }

    public void setRegistroPasajeros(ArrayListGeneric<UsuarioCliente> registroPasajeros) {
        this.registroPasajeros = registroPasajeros;
    }

    //--------------------------------------------------------------------

    //Metodos propios

    // Agregar tripulacion cabina
    public void agregarTripulanteCabina(TripulacionCabina tripulante) throws CapacidadExcedidaException {
        if (registroTripulanteCabina.getLista().size() < avion.getCapacidadTripulanteCabina()) {
            registroTripulanteCabina.agregarElemento(tripulante);
        } else {
            throw new CapacidadExcedidaException("No se pueden agregar más tripulantes de cabina. Capacidad máxima alcanzada.");
        }
    }

    // Agregar tripulacion tecnica
    public void agregarTripulanteTecnico(TripulacionTecnica tripulante) throws CapacidadExcedidaException {
        if (registroTripulacionTecnica.getLista().size() < avion.getCapacidadTripulanteTecnico()) {
            registroTripulacionTecnica.agregarElemento(tripulante);
        } else {
            throw new CapacidadExcedidaException("No se pueden agregar más tripulantes técnicos. Capacidad máxima alcanzada.");
        }
    }

    // Agregar pasajero
    public void agregarPasajero(UsuarioCliente pasajero) throws CapacidadExcedidaException {
        if (checkIn == null || !checkIn.getEstadoCheck()) {
            throw new IllegalStateException("No se puede agregar el pasajero porque no ha realizado el check-in.");
        }

        if (registroPasajeros.getLista().size() >= avion.getCapacidadPasajeros()) {
            throw new CapacidadExcedidaException("No se pueden agregar más pasajeros. Capacidad máxima alcanzada.");
        }

        registroPasajeros.agregarElemento(pasajero);
        System.out.println("Pasajero agregado exitosamente.");
    }
    
}