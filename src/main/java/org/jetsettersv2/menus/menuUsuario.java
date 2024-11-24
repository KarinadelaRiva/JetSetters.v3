package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.models.concrete.Reserva;
import org.jetsettersv2.models.concrete.TripulacionCabina;
import org.jetsettersv2.models.concrete.UsuarioCliente;
import org.jetsettersv2.utilities.Fecha;

import java.util.ArrayList;
import java.util.Scanner;

import static org.jetsettersv2.utilities.JacksonUtil.*;

public class menuUsuario{
    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuUsuario(UsuarioCliente logueado) {
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\nMenú Usuario:");
            System.out.println("1. PERFIL (ver/modificar)");
            System.out.println("2. MIS RESERVAS");
            System.out.println("3. BUSCAR VUELO Y HACER RESERVA");
            System.out.println("4. MIS VUELOS");
            System.out.println("0. CERRAR SESIÓN");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> subMenuGestionarPerfilUsuario(logueado);
                case 2 -> mostrarReservas(logueado);
                case 3 -> buscarVuelo();
//                case 4 -> mostrarVuelos(logueado);
                case 0 -> System.out.println(" ");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    public static int buscarPosUsuarioElementoPorLegajo(String idUsuario, ArrayListGeneric< UsuarioCliente > usuarios) throws ElementoNoEncontradoException {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getIdPersona().equals(idUsuario)) {
                return i;  // Retorna la posición del elemento si coincide el legajo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un el usuario");
    }

    public static void subMenuGestionarPerfilUsuario(UsuarioCliente logueado) {

        ArrayListGeneric<UsuarioCliente> usuarios = new ArrayListGeneric<>();
        try{
            usuarios.copiarLista(getJsonToList(PATH_RESOURCES + PATH_USUARIOSCLIENTE, UsuarioCliente.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        int posUsuario = -1;
        try {
            posUsuario = buscarPosUsuarioElementoPorLegajo(logueado.getIdPersona(), usuarios);
        } catch (ElementoNoEncontradoException e) {
            System.err.println("Error al buscar el usuario: " + e.getMessage());
        }

        System.out.println("\nPERFIL:");
        System.out.println("1. Ver perfil");
        System.out.println("2. Modificar perfil");
        System.out.print("Seleccione una opción: ");
        int subOpcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        if (subOpcion == 1) {
            verPerfil(usuarios.get(posUsuario)); // Llamar al método para ver perfil
        } else if (subOpcion == 2) {
            mostrarSubmenuModificacion(usuarios.get(posUsuario)); // Llamar al método para modificar perfil
        } else {
            System.out.println("Opción inválida.");
        }

    }

    public static void mostrarSubmenuModificacion(UsuarioCliente logueado) {
        Scanner scanner = new Scanner(System.in);

        ArrayListGeneric<UsuarioCliente> usuarios = new ArrayListGeneric<>();
        try{
            usuarios.copiarLista(getJsonToList(PATH_RESOURCES + PATH_USUARIOSCLIENTE, UsuarioCliente.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        int posUsuario = -1;
        try {
            posUsuario = buscarPosUsuarioElementoPorLegajo(logueado.getIdPersona(), usuarios);
        } catch (ElementoNoEncontradoException e) {
            System.err.println("Error al buscar el usuario: " + e.getMessage());
        }

        int opcion;

        do {
            System.out.println("\nSubmenú - Modificar Datos:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar DNI");
            System.out.println("4. Modificar pasaporte");
            System.out.println("5. Modificar teléfono");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoValor = scanner.nextLine();
                    usuarios.get(posUsuario).modificarDatos("nombre", nuevoValor);
                }
                case 2 -> {
                    System.out.print("Ingrese el nuevo apellido: ");
                    String nuevoValor = scanner.nextLine();
                    usuarios.get(posUsuario).modificarDatos("apellido", nuevoValor);
                }
                case 3 -> {
                    System.out.print("Ingrese el nuevo DNI: ");
                    String nuevoValor = scanner.nextLine();
                    usuarios.get(posUsuario).modificarDatos("dni", nuevoValor);
                }
                case 4 -> {
                    System.out.print("Ingrese el nuevo pasaporte: ");
                    String nuevoValor = scanner.nextLine();
                    usuarios.get(posUsuario).modificarDatos("pasaporte", nuevoValor);
                }
                case 5 -> {
                    System.out.print("Ingrese el nuevo teléfono: ");
                    String nuevoValor = scanner.nextLine();
                    usuarios.get(posUsuario).modificarDatos("telefono", nuevoValor);
                }
                case 0 -> System.out.println("Salir y guardar cambios.");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private static void verPerfil(UsuarioCliente logueado) {
        logueado.verPerfil();
        System.out.println("Mostrando perfil del usuario...");
    }

    private static void mostrarReservas(UsuarioCliente logueado) {
        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();
        Fecha hoy = new Fecha();
        try{
            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        for (Reserva reserva : reservas) {
            if ((reserva.getVuelo().getFechaSalida().esDespuesDe(hoy) || (reserva.getVuelo().getFechaSalida().esIgualA(hoy)) && (reserva.getUsuarioLogueado().getIdPersona().equals(logueado.getIdPersona())))) {
                System.out.println("\nReservas pendientes: ");
                reserva.mostrar();
            }
        }

    }

    private static void buscarVuelo() {
        // Lógica para buscar un vuelo y hacer una reserva
        System.out.println("Buscando vuelos o realizando una reserva...");
    }

//    private static void mostrarVuelos(UsuarioCliente logueado) {
//        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();
//        Fecha hoy = new Fecha();
//        try{
//            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
//        } catch (Exception e) {
//            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
//        }
//
//        //mostrar vuelos pendientes de salida con el check in realizado
//        for (Reserva reserva : reservas) {
//            if ((reserva.getVuelo().getFechaSalida().esDespuesDe(hoy) || (reserva.getVuelo().getFechaSalida().esIgualA(hoy))
//                    && (reserva.getUsuarioLogueado().getIdPersona().equals(logueado.getIdPersona()))
//                    && (reserva.getCheckIn().getEstadoCheck()))) {
//                System.out.println("\nVuelos pendientes de salida: ");
//                reserva.getVuelo().imprimirDatosVuelo();
//            }
//        }
//
//        //mostrar vuelos pasados con el check in realizado
//        for (Reserva reserva : reservas) {
//            if ((reserva.getVuelo().getFechaSalida().esAntesDe(hoy))
//                    && (reserva.getUsuarioLogueado().getIdPersona().equals(logueado.getIdPersona()))
//                    && (reserva.getCheckIn().getEstadoCheck())) {
//                System.out.println("\nVuelos pasados: ");
//                reserva.mostrar();
//            }
//        }
//    }
}
