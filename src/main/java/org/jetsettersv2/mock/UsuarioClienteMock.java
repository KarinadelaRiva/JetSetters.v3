package org.jetsettersv2.mock;
import org.jetsettersv2.models.concrete.Direccion;
import org.jetsettersv2.models.concrete.UsuarioCliente;

import java.util.Random;

public class UsuarioClienteMock {

    private static final String[] NOMBRES = {"Juan", "María", "Carlos", "Ana", "Pedro", "Laura"};
    private static final String[] APELLIDOS = {"Pérez", "Gómez", "Rodríguez", "López", "Martínez", "García"};
    private static final String[] CIUDADES = {"Buenos Aires", "Córdoba", "Rosario", "Mendoza", "Salta"};
    private static final Random RANDOM = new Random();

    public static UsuarioCliente generarUsuarioAleatorio() {
        Direccion direccion = new Direccion()
                .calle("Calle " + RANDOM.nextInt(1000))
                .numero(RANDOM.nextInt(500))
                .ciudad(CIUDADES[RANDOM.nextInt(CIUDADES.length)])
                .codigoPostal("CP" + RANDOM.nextInt(9999))
                .pais("Argentina");

        return (UsuarioCliente) new UsuarioCliente()
                .nombre(NOMBRES[RANDOM.nextInt(NOMBRES.length)])
                .apellido(APELLIDOS[RANDOM.nextInt(APELLIDOS.length)])
                .dni("DNI-" + RANDOM.nextInt(99999999))
                .pasaporte("PAS-" + RANDOM.nextInt(9999999))
                .telefono("+54-9-" + RANDOM.nextInt(9999999))
                .direccion(direccion)
                .email("usuario" + RANDOM.nextInt(1000) + "@mail.com")
                .password("pass" + RANDOM.nextInt(10000));
    }
}
