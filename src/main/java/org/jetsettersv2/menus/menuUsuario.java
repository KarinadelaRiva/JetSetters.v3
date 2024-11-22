package org.jetsettersv2.menus;

import org.jetsettersv2.models.concrete.UsuarioCliente;

import java.util.Scanner;

public class menuUsuario{

    public static void mostrarMenuUsuario(UsuarioCliente logueado) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú Usuario:");
            System.out.println("1. PERFIL (ver/modificar)");
            System.out.println("2. MIS RESERVAS");
            System.out.println("3. BUSCAR VUELO / HACER RESERVA");
            System.out.println("4. MIS VUELOS");
            System.out.println("5. CERRAR SESIÓN");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.println("\nPERFIL:");
                    System.out.println("1. Ver perfil");
                    System.out.println("2. Modificar perfil");
                    System.out.print("Seleccione una opción: ");
                    int subOpcion = scanner.nextInt();
                    scanner.nextLine(); // Consumir salto de línea

                    if (subOpcion == 1) {
                        verPerfil(logueado); // Llamar al método para ver perfil
                    } else if (subOpcion == 2) {
                        mostrarSubmenuModificacion(logueado); // Llamar al método para modificar perfil
                    } else {
                        System.out.println("Opción inválida.");
                    }
                }
                case 2 -> mostrarReservas();
                case 3 -> buscarVuelo();
                case 4 -> mostrarVuelos();
                case 5 -> System.out.println("Cerrando sesión...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);
    }

    public static void mostrarSubmenuModificacion(UsuarioCliente logueado) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nSubmenú - Modificar Datos:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar DNI");
            System.out.println("4. Modificar pasaporte");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoValor = scanner.nextLine();
                    logueado.modificarDatos("nombre", nuevoValor);
                }
                case 2 -> {
                    System.out.print("Ingrese el nuevo apellido: ");
                    String nuevoValor = scanner.nextLine();
                    logueado.modificarDatos("apellido", nuevoValor);
                }
                case 3 -> {
                    System.out.print("Ingrese el nuevo DNI: ");
                    String nuevoValor = scanner.nextLine();
                    logueado.modificarDatos("dni", nuevoValor);
                }
                case 4 -> {
                    System.out.print("Ingrese el nuevo pasaporte: ");
                    String nuevoValor = scanner.nextLine();
                    logueado.modificarDatos("pasaporte", nuevoValor);
                }
                case 5 -> {
                    System.out.print("Ingrese el nuevo teléfono: ");
                    String nuevoValor = scanner.nextLine();
                    logueado.modificarDatos("telefono", nuevoValor);
                }
                case 6 -> System.out.println("Saliendo del submenú...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }



    private static void verPerfil(UsuarioCliente logueado) {
        logueado.verPerfil();
        System.out.println("Mostrando perfil del usuario...");
    }

    private static void mostrarReservas() {
        // Lógica para mostrar las reservas del usuario
        System.out.println("Mostrando reservas del usuario...");
    }

    private static void buscarVuelo() {
        // Lógica para buscar un vuelo o hacer una reserva
        System.out.println("Buscando vuelos o realizando una reserva...");
    }

    private static void mostrarVuelos() {
        // Lógica para mostrar los vuelos del usuario
        System.out.println("Mostrando vuelos del usuario...");
    }
}
