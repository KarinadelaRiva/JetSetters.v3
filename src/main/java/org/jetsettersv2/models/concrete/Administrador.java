package org.jetsettersv2.models.concrete;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.models.abstracts.Empleado;
import org.jetsettersv2.utilities.Fecha;

import java.util.List;

public class Administrador extends Empleado {

    private ArrayListGeneric<Vuelo> gestionVuelos;
    private ArrayListGeneric<Reserva> gestionReservas;
    private ArrayListGeneric<UsuarioCliente> gestionUsuarios;
    private ArrayListGeneric<Pasajero> gestionPasajeros;
    private ArrayListGeneric<Ruta> gestionRutas;
    private ArrayListGeneric <Avion> gestionAviones;


    public Administrador() {
    }

    // <<<<<<<GETTERS Y SETTERS>>>>>>>

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

    // <--------------------------------------METODOS------------------------------------------->


//    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<VUELOS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<VER VUELOS>>>>>>>
    public List<Vuelo> verCollectionVuelos() {
        return gestionVuelos.getLista();
    }

    // <<<<<<<AÑADIR VUELOS>>>>>>>
    public void añadirVuelosCollection(Vuelo vuelo) {

        gestionVuelos.agregarElemento(vuelo);
    }

    // <<<<<<<BUSCAR VUELO>>>>>>>
    public Vuelo buscarVuelo(String nroVuelo) throws ElementoNoEncontradoException {
        for (Vuelo vuelo : gestionVuelos.getLista()) {
            if (vuelo.getNroVuelo().equals(nroVuelo)) {
                return vuelo;
            }
        }throw new ElementoNoEncontradoException("Vuelo con numero " + nroVuelo + " no encontrado.");
    }


    // <<<<<<<REPROGRAMAR VUELOS>>>>>>>

    public void reprogramarVuelos (String atributo, Fecha nuevoValor) {
        boolean vueloReprogramado = false;

        for (Vuelo vuelo : gestionVuelos.getLista()) {
            if (vuelo.getFechaSalida().equals(nuevoValor)) {


                switch (atributo.toLowerCase()) {
                    case "Fecha de salida":
                        vuelo.fechaSalida(nuevoValor);
                        break;
                    case "Horario de salida":
                        vuelo.horaSalida(nuevoValor);
                        break;
                    default:
                        System.out.println("Atributo no reconocido.");
                        vueloReprogramado = true;
                }
            }
        }if (!vueloReprogramado) {
            throw new ElementoNoEncontradoException("No se encontraron vuelos con la fecha original especificada.");
        }

        System.out.println("Se reprogramaron los vuelos exitosamente");
    }


//    public void reprogramarVuelos(Fecha fechaOriginal, Fecha nuevaFecha) throws ElementoNoEncontradoException {
//        boolean vueloReprogramado = false;
//
//        for (Vuelo vuelo : gestionVuelos.getLista()) {
//            if (vuelo.getFechaSalida().equals(fechaOriginal)) {
//                vuelo.setFechaSalida(nuevaFecha);
//                vueloReprogramado = true;
//            }
//        }
//
//        if (!vueloReprogramado) {
//            throw new ElementoNoEncontradoException("No se encontraron vuelos con la fecha original especificada.");
//        }
//
//        System.out.println("Se reprogramaron los vuelos de la fecha " + fechaOriginal + " a " + nuevaFecha);
//    }


//     <<<<<<<ASIGNAR TRIPULACION A UN VUELO>>>>>>>

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

    // <<<<<<<VER RUTA>>>>>>>
    public List <Ruta> verCollectionRuta(){
        return gestionRutas.getLista();}

    // <<<<<<<AÑADIR RUTA>>>>>>>
    public void añadirRutasCollection (Ruta ruta) {

        gestionRutas.agregarElemento(ruta);
    }





    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<FLOTA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<VER FLOTA>>>>>>>
    public List <Avion> verCollectionAvion () {
        return gestionAviones.getLista();}

    // <<<<<<<ALTA FLOTA>>>>>>>
    public void altaFlota(Avion avion) {
        gestionAviones.agregarElemento(avion);
        System.out.println("El avión ha sido dado de alta.");
    }

    // <<<<<<<BAJA FLOTA>>>>>>>

    public void bajaFlota(Avion avion) throws ElementoNoEncontradoException {
        boolean encontrado = false;

        // Recorre la lista de aviones y elimina el avión si se encuentra
        for (Avion avion1 : gestionAviones.getLista()) {
            if (avion1.equals(avion)) {
                gestionAviones.eliminarElemento(avion1, "");
                encontrado = true;
                System.out.println("El avión ha sido dado de baja.");
                break;
            }
        }

        // Si el avión no se encontró, lanza una excepción
        if (!encontrado) {
            throw new ElementoNoEncontradoException("No se ha encontrado el avión solicitado.");
        }
    }



    // <<<<MODIFICAR FLOTA>>>>>

    public void modificarFlota(String matricula, String atributo, Object nuevoValor) throws ElementoNoEncontradoException {
        Avion avionModificado = null;

        for (Avion avion : gestionAviones.getLista()) {
            if (avion.getMatricula().equals(matricula)) {
                switch (atributo.toLowerCase()) {
                    case "1.modelo":
                        if (nuevoValor instanceof String) {
                            avion.setModelo((String) nuevoValor);
                        }
                        break;
                    case "2.capacidad para Tripulantes":
                        if (nuevoValor instanceof Integer) {
                            avion.setCapacidadTripulantes((Integer) nuevoValor);
                        }
                        break;
                    case "3.capacidad para Pasajeros":
                        if (nuevoValor instanceof Integer) {
                            avion.setCapacidadPasajeros((Integer) nuevoValor);
                        }
                        break;
                    case "4.capacidad de Equipaje":
                        if (nuevoValor instanceof Integer) {
                            avion.setCapacidadEquipaje((Integer) nuevoValor);
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Atributo no válido: " + atributo);
                }
                avionModificado = avion;
                break;
            }
        }

        if (avionModificado == null) {
            throw new ElementoNoEncontradoException("El avión con Matricula " + matricula + " no ha sido encontrado.");
        }

        System.out.println("El avión con Matricula " + matricula + " ha sido modificado.");
    }



    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<PERSONAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // <<<<<<<ALTA PERSONAL (TRIPULANTE)>>>>>>>
    public void altaPersonal(){

    }

    //    <<<<<<<<<<<<<<<<ELIMINAR PERSONAL (TRIPULANTE)>>>>>>>>>>>>>>>>




    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADMINISTRADOR>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //    <<<<<<<<<<<<<<<<ASIGNAR ADMINISTRADOR >>>>>>>>>>>>>>>>>


    //   <<<<<<<<<<<<<<<<ELIMINAR ADMINISTRADOR>>>>>>>>>>>>>>>>>






    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< USUARIOS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //    <<<<<<<<<<<<<<<<VER USUARIOS >>>>>>>>>>>>>>>>>
    public List<UsuarioCliente> verCollectionUsuarios() {
        return gestionUsuarios.getLista();
    }




    //    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<OTROS METODOS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //    <<<<<<<<<<<<<<<<VER PERFIL >>>>>>>>>>>>>>>>>

    public void verPerfil() {
        System.out.println("Ver Perfil de Usuario:");
        super.imprimir();
    }

    //    <<<<<<<<<<<<<<<<VER RESERVAS >>>>>>>>>>>>>>>>>
    public List<Reserva> verCollectionReservas() {
        return gestionReservas.getLista();
    }

    //    <<<<<<<<<<<<<<<<VER PASAJEROS >>>>>>>>>>>>>>>>>
    public List<Pasajero> verCollectionPasajeros() {
        return gestionPasajeros.getLista();
    }


    //    <<<<<<<<<<<<<<<<IMPRIMIR >>>>>>>>>>>>>>>>>

    public void imprimir () {
        super.imprimir();
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "gestionVuelos=" + gestionVuelos +
                ", gestionReservas=" + gestionReservas +
                ", gestionUsuarios=" + gestionUsuarios +
                ", gestionPasajeros=" + gestionPasajeros +
                ", gestionRutas=" + gestionRutas +
                ", gestionAviones=" + gestionAviones +
                '}';
    }
}

