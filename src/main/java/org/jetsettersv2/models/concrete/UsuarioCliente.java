package org.jetsettersv2.models.concrete;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.models.abstracts.Persona;


import java.util.ArrayList;

public class UsuarioCliente extends Persona {

    private ArrayListGeneric<Reserva> misReservas;

    public UsuarioCliente() {
        super();
    }

    public void verPerfil(){
        super.imprimir();
    }

    public ArrayListGeneric<Reserva> getMisReservas() {
        return misReservas;
    }

    public UsuarioCliente misReservas(ArrayListGeneric<Reserva> misReservas) {
        this.misReservas = misReservas;
        return this;
    }

}
