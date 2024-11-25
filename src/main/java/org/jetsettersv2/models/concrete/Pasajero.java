package org.jetsettersv2.models.concrete;
import org.jetsettersv2.models.abstracts.Persona;

public class Pasajero extends Persona {

    private UsuarioCliente usuario;

    public Pasajero(UsuarioCliente usuario) {
        this.usuario = usuario;
    }

    public UsuarioCliente getUsuario() {
        return usuario;
    }

    public Pasajero usuario(UsuarioCliente usuario) {
        this.usuario = usuario;
        return this;
    }

}
