package org.jetsettersv2.models.concrete;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.enums.TurnoEmpleado;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.models.abstracts.Empleado;
import org.jetsettersv2.utilities.Fecha;

import java.util.List;

public class Administrador extends Empleado {

    private TurnoEmpleado turno;
    private ArrayListGeneric<Vuelo> gestionVuelos;
    private ArrayListGeneric<Reserva> gestionReservas;
    private ArrayListGeneric<UsuarioCliente> gestionUsuarios;
    private ArrayListGeneric<Pasajero> gestionPasajeros;
    private ArrayListGeneric<Ruta> gestionRutas;
    private ArrayListGeneric <Avion> gestionAviones;


    public Administrador() {
    }

    // <<<<<<<GETTERS Y SETTERS>>>>>>>

    public TurnoEmpleado getTurno() {
        return turno;
    }

    public Administrador turno(TurnoEmpleado turno) {
        this.turno = turno;
        return this;
    }

    public ArrayListGeneric<Pasajero> getGestionPasajeros() {
        return gestionPasajeros;
    }

    public Administrador gestionPasajeros(ArrayListGeneric<Pasajero> gestionPasajeros) {
        this.gestionPasajeros = gestionPasajeros;
        return this;
    }

    public ArrayListGeneric<Reserva> getGestionReservas() {
        return gestionReservas;
    }

    public Administrador gesionrReservas(ArrayListGeneric<Reserva> gesionrReservas) {
        this.gestionReservas = gesionrReservas;
        return this;
    }

    public ArrayListGeneric<Vuelo> getGestionVuelos() {
        return gestionVuelos;
    }

    public Administrador gestionVuelos(ArrayListGeneric<Vuelo> gestionVuelos) {
        this.gestionVuelos = gestionVuelos;
        return this;
    }

    public ArrayListGeneric<UsuarioCliente> getGestionUsuarios() {
        return gestionUsuarios;
    }

    public Administrador gestionUsuarios(ArrayListGeneric<UsuarioCliente> gestionUsuarios) {
        this.gestionUsuarios = gestionUsuarios;
        return this;
    }

    public ArrayListGeneric<Ruta> getGestionRutas() {
        return gestionRutas;
    }

    public Administrador gestionRutas(ArrayListGeneric<Ruta> gestionRutas) {
        this.gestionRutas = gestionRutas;
        return this;
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<METODOS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<VER COLLECTION>>>>>>>

    public List<Vuelo> verCollectionVuelos() {
        return gestionVuelos.getLista();
    }

    public List<Reserva> verCollectionReservas() {
        return gestionReservas.getLista();
    }

    public List<UsuarioCliente> verCollectionUsuarios() {
        return gestionUsuarios.getLista();
    }

    public List<Pasajero> verCollectionPasajeros() {
        return gestionPasajeros.getLista();
    }

    public List <Ruta> verCollectionRuta(){return gestionRutas.getLista();}

    public List <Avion> verCollectionAvion () { return gestionAviones.getLista();}


//    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<VUELOS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<AÑADIR VUELOS>>>>>>>

    public void añadirVuelosCollection(Vuelo vuelo) {
        gestionVuelos.agregarElemento(vuelo);
    }

    // <<<<<<<REPROGRAMAR VUELOS>>>>>>>

    public void reprogramarVuelos(Fecha fechaOriginal, Fecha nuevaFecha) throws ElementoNoEncontradoException {
        boolean vueloReprogramado = false;

        for (Vuelo vuelo : gestionVuelos.getLista()) {
            if (vuelo.getFechaSalida().equals(fechaOriginal)) {
                vuelo.setFechaSalida(nuevaFecha);
                vueloReprogramado = true;
            }
        }

        if (!vueloReprogramado) {
            throw new ElementoNoEncontradoException("No se encontraron vuelos con la fecha original especificada.");
        }

        System.out.println("Se reprogramaron los vuelos de la fecha " + fechaOriginal + " a " + nuevaFecha);
    }


    // <<<<<<<ASIGNAR TRIPULACION A UN VUELO>>>>>>>

//    public void asignarTripulacionVuelo(String idVuelo, Persona tripulante) throws ElementoNoEncontradoException {
//        RegistroDeVuelo vuelo = null;
//
//        for (RegistroDeVuelo v : gestionVuelos.getLista()) {
//            if (v.getIdRegistroDeVuelo().equals(idVuelo)) {
//                vuelo = v;
//                break;
//            }
//        }
//
//        if (vuelo == null) {
//            throw new ElementoNoEncontradoException("El Vuelo con ID " + idVuelo + " no ha sido encontrado.");
//        }
//
//        // Verifico si el tripulante ya está asignado
//        if (vuelo.getTripulacion().contains(tripulante)) {
//            System.out.println("El tripulante ya está asignado a este vuelo.");
//            return;
//        }
//
//        // Si no esta en el vuelo asigno el tripulante
//        vuelo.getTripulacion().add(tripulante);
//        System.out.println("El tripulante " + tripulante.getNombre() + " ha sido asignado al vuelo con ID " + idVuelo);
//    }


    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<RUTA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<AÑADIR RUTA>>>>>>>
    public void añadirRutasCollection (Ruta ruta) {
        gestionRutas.agregarElemento(ruta);
    }





    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<FLOTA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<PERSONAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //    <<<<<<<<<<<<<<<<ELIMINAR TRIPULANTE>>>>>>>>>>>>>>>>




    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADMINISTRADOR>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //    <<<<<<<<<<<<<<<<ASIGNAR / ELIMINAR ADMINISTRADOR>>>>>>>>>>>>>>>>>


    public void imprimir ()
    {
        super.imprimir();
        System.out.println("Turno....................:" + this.turno);
    }

    @Override
    public String toString() {
        return "PersonalTierra{" +
                ", turno=" + turno +
                '}';
    }
}
