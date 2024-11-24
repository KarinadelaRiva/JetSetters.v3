package org.jetsettersv2.models.concrete;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.enums.EstadoCheck;
import org.jetsettersv2.exceptions.CapacidadExcedidaException;
import org.jetsettersv2.models.abstracts.Equipaje;
import org.jetsettersv2.models.abstracts.Persona;

public class RegistroDeVuelo {
    //Atributos
    private Avion avion;
    private ArrayListGeneric<TripulacionCabina> registroTripulanteCabina;
    private ArrayListGeneric<TripulacionTecnica> registroTripulacionTecnica;
    private ArrayListGeneric<Pasajero>registroPasajeros;
    private ArrayListGeneric<Equipaje>registroEquipaje;

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
    public ArrayListGeneric<Pasajero> getRegistroPasajeros() {
        return registroPasajeros;
    }

    public RegistroDeVuelo registroPasajeros(ArrayListGeneric<Pasajero> registroPasajeros) {
        this.registroPasajeros = registroPasajeros;
        return this;
    }

    public void setRegistroPasajeros(ArrayListGeneric<Pasajero> registroPasajeros) {
        this.registroPasajeros = registroPasajeros;
    }

    //--------------------------------------------------------------------
    public ArrayListGeneric<Equipaje> getRegistroEquipaje() {
        return registroEquipaje;
    }

    public RegistroDeVuelo registroEquipaje(ArrayListGeneric<Equipaje> registroEquipaje) {
        this.registroEquipaje = registroEquipaje;
        return this;
    }

    public void setRegistroEquipaje(ArrayListGeneric<Equipaje> registroEquipaje) {
        this.registroEquipaje = registroEquipaje;
    }

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
    /*Se agrega pasajero al avion solo si tiene el checkin en estado completado y si la capacidad del avion lo permiite.
    El enum EstadoCheck deberia tener como estado COMPLETADO para poder realizarlo.*/
    public void agregarPasajero(Pasajero pasajero) throws CapacidadExcedidaException {
        // Validar si el pasajero realizó el check-in
        if (pasajero.getCheckIn().getEstadoCheck() != EstadoCheck.COMPLETAD0) {
            System.out.println("No se puede agregar el pasajero porque no ha realizado el check-in.");
            return;
        }

        // Validar capacidad
        if (registroPasajeros.getLista().size() >= avion.getCapacidadPasajeros()) {
            throw new CapacidadExcedidaException("No se pueden agregar más pasajeros. Capacidad máxima alcanzada.");
        }

        // Agregar pasajero si cumple ambas condiciones
        registroPasajeros.agregarElemento(pasajero);
        System.out.println("Pasajero agregado exitosamente.");
    }

    /*public void agregarPasajero(Pasajero pasajero) throws CapacidadExcedidaException {
        //Valida capacidad
        if (registroPasajeros.getLista().size() < avion.getCapacidadPasajeros()) {
            registroPasajeros.agregarElemento(pasajero);
        } else {
            throw new CapacidadExcedidaException("No se pueden agregar más pasajeros. Capacidad máxima alcanzada.");
        }
        
    }*/

    // Agregar equipaje
    public void agregarEquipaje(Equipaje equipaje) throws CapacidadExcedidaException {
        if (registroEquipaje.getLista().size() < avion.getCapacidadEquipaje()) {
            registroEquipaje.agregarElemento(equipaje);
        } else {
            throw new CapacidadExcedidaException("No se puede agregar más equipaje. Capacidad máxima alcanzada.");
        }
    }

    //Metodo realizar cheeckIn de la clase checkin
    
}