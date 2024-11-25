package org.jetsettersv2.models.concrete;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.CapacidadExcedidaException;



public class RegistroDeVuelo {
    //Atributos
    private Avion avion;
    private ArrayListGeneric<TripulacionCabina> registroTripulanteCabina;
    private ArrayListGeneric<TripulacionTecnica> registroTripulacionTecnica;
    private ArrayListGeneric<UsuarioCliente>registroPasajeros;


    public void imprimirRegistroDeVuelo() {
        System.out.println("Tripulación Técnica:");
        for (TripulacionTecnica tripulante : getRegistroTripulacionTecnica()) {
            System.out.println("Legajo: " + tripulante.getLegajo() + " - Nombre: " + tripulante.getNombre() + " - Tipo: " + tripulante.getTipoPersonal());
        }

        System.out.println("Tripulación de Cabina:");
        for (TripulacionCabina tripulante : getRegistroTripulanteCabina()) {
            System.out.println("Legajo: " + tripulante.getLegajo() + " - Nombre: " + tripulante.getNombre() + " - Tipo: " + tripulante.getTipoPersonal());
        }

        System.out.println("Pasajeros:");
        for (UsuarioCliente pasajero : getRegistroPasajeros()) {
            System.out.println("DNI: " + pasajero.getDni() + " - Nombre: " + pasajero.getNombre() + " - Apellido: " + pasajero.getApellido());
        }
    }

    //Constructor
    public RegistroDeVuelo() {
        this.registroTripulanteCabina = new ArrayListGeneric<>();
        this.registroTripulacionTecnica = new ArrayListGeneric<>();
        this.registroPasajeros = new ArrayListGeneric<>();
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
}