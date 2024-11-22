package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LoginException;
import org.jetsettersv2.models.abstracts.Persona;
import org.jetsettersv2.models.concrete.Direccion;
import org.jetsettersv2.models.concrete.UsuarioCliente;

import java.util.Scanner;

import static org.jetsettersv2.models.abstracts.Persona.iniciarSesion;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuLogin {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayListGeneric<Persona> personas = new ArrayListGeneric<>();
    private static Persona usuarioLogueado = new UsuarioCliente();

    public static void login(){

        int opcion = opcionesLogin();
        switch (opcion) {
            case 1:
                usuarioLogueado = inicioSesionUsuario(personas);
                break;
            case 2:
                registrarUsuario();
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
            personas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_PERSONAS, Persona.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        
        try {
            logueado = iniciarSesion(personas);
            System.out.println("Datos del usuario logueado: " + logueado.getNombre() + " - " + logueado.getEmail());
        } catch (LoginException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return logueado;
    }

    public static void registrarUsuario(){
        Persona nuevoUsuario = new UsuarioCliente();
        try{
            personas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_PERSONAS, Persona.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        nuevoUsuario = registrarse();

        personas.agregarElemento(nuevoUsuario);

        try{
            writeListToJsonFile(personas, PATH_RESOURCES + PATH_PERSONAS);
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static Persona registrarse() {
        Persona persona = new UsuarioCliente();
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Ingresar nombre");
            System.out.println("2. Ingresar apellido");
            System.out.println("3. Ingresar DNI");
            System.out.println("4. Ingresar pasaporte");
            System.out.println("5. Ingresar teléfono");
            System.out.println("6. Ingresar dirección");
            System.out.println("7. Mostrar datos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> persona.solicitarNombre();
                case 2 -> persona.solicitarApellido();
                case 3 -> persona.solicitarDni();
                case 4 -> persona.solicitarPasaporte();
                case 5 -> persona.solicitarTelefono();
                case 6 -> persona.solicitarDireccion();
                case 7 -> {
                    System.out.println("\nDatos ingresados:");
                    System.out.println("Nombre: " + persona.getNombre());
                    System.out.println("Apellido: " + persona.getApellido());
                    System.out.println("DNI: " + persona.getDni());
                    System.out.println("Pasaporte: " + persona.getPasaporte());
                    System.out.println("Teléfono: " + persona.getTelefono());
                    Direccion dir = persona.getDireccion();
                    if (dir != null) {
                        System.out.println("Dirección: " + dir.getCalle() + " " + dir.getNumero() + ", " + dir.getCiudad() + ", " + dir.getPais() + " (CP: " + dir.getCodigoPostal() + ")");
                    } else {
                        System.out.println("Dirección no ingresada.");
                    }
                }
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion!=8);
        return persona;
    }
}
