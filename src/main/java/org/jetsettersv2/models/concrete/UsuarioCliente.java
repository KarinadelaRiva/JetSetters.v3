package org.jetsettersv2.models.concrete;

import org.jetsettersv2.models.abstracts.Persona;

public class UsuarioCliente extends Persona {


    public UsuarioCliente() {
        super();
    }

    public void verPerfil(){
        super.imprimir();
    }


    public void modificarDatos(String atributo, String nuevoValor) {

        switch (atributo.toLowerCase()) {
            case "nombre":
                this.nombre(nuevoValor);
                break;
            case "apellido":
                this.apellido(nuevoValor) ;
                break;
            case "dni":
                this.dni(nuevoValor);
                break;
            case "pasaporte":
                this.pasaporte(nuevoValor);
                break;
            case "telefono":
                this.telefono(nuevoValor);
                break;
            default:
                System.out.println("Atributo no reconocido.");
        }
    }

//    public Reserva hacerReserva(){
//        Reserva reserva = new Reserva();
//    }


//    +verPerfil(); / modificarDatos();
//+hacerReserva();
//+registrarEquipaje() : Equipaje;
//+consultarEstadoReserva();
//+RealizarCheckIn() : Pasajero;
//+RegistrarEquipaje() : Equipaje
}
