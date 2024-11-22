package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LoginException;
import org.jetsettersv2.models.abstracts.Persona;
import org.jetsettersv2.models.concrete.UsuarioCliente;

import java.util.Scanner;

import static org.jetsettersv2.models.abstracts.Persona.iniciarSesion;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuLogin {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayListGeneric<Persona> personas = new ArrayListGeneric<>();
    private static final Persona usuarioLogueado = new UsuarioCliente();

    public static void login(){

        int opcion = opcionesLogin();
        switch (opcion) {
            case 1:
                inicioSesionUsuario(personas);
                break;
            case 2:
                System.out.println("Registrarse");
                break;
            case 3:
                System.out.println("Continuar sin iniciar sesion");
                break;
            case 0:
                System.out.println("Ingresar como Administrador");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public static int opcionesLogin() {
        int opcion = -1;

        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Continuar sin iniciar sesion");
        System.out.println("\n");
        System.out.println("0. Ingresar como Administrador");

        while (opcion < 0 || opcion > 3) {
            try {
                System.out.print("Ingrese una opcion: ");
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida");
            }
        }
        return opcion;
    }

    public static Persona inicioSesionUsuario(ArrayListGeneric<Persona> personas){
        Persona logueado = new UsuarioCliente();

        try{
            personas.addAll(getJsonToList(PATH_RESOURCES + PATH_PERSONAS, Persona.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        
        try {
            logueado = iniciarSesion(ArrayListGeneric<Persona> personas);
            System.out.println("Datos del usuario logueado: " + logueado.getNombre() + " - " + logueado.getEmail());
        } catch (LoginException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return logueado;
    }


}
