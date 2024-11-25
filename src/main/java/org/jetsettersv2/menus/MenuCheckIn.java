package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.models.concrete.*;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;

import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.utilities.JacksonUtil.*;
import static org.jetsettersv2.utilities.Tipografias.*;

public class MenuCheckIn {

    public static void mostrarSubmenuCheckIn(UsuarioCliente usuarioLogueado) {
        Scanner scanner = new Scanner(System.in);

        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();

        try{
            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        while (true) {
            printTitulo("\n--- Submenú de Check-In ---", 30);
            System.out.println("1. Realizar check-in");
            System.out.println("2. Mostrar estado del check-in");
            System.out.println("0. Volver");

            printColoredTitle("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    realizarCheckIn(usuarioLogueado);
                    pausarConTecla();
                    break;
                case 2:
                    mostrarEstadoCheckIn(usuarioLogueado, reservas);
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println(" ");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void realizarCheckIn(UsuarioCliente usuarioLogueado1) {
        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();
        ArrayListGeneric<Vuelo> vuelos = new ArrayListGeneric<>();

        try {
            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return;
        }

        try {
            vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return;
        }

        // Filtrar reservas pendientes del usuario logueado
        ArrayListGeneric<Reserva> reservasPendientes = new ArrayListGeneric<>();
        for (Reserva reserva : reservas) {
            if (reserva.getUsuarioLogueado().getIdPersona().equals(usuarioLogueado1.getIdPersona())) {
                CheckIn checkIn = reserva.getCheckIn();
                if (checkIn != null && !checkIn.getEstadoCheck()) {
                    reservasPendientes.add(reserva);
                }
            }
        }

        // Si no hay reservas pendientes
        if (reservasPendientes.isEmpty()) {
            System.out.println("No tienes reservas pendientes para realizar el check-in.");
            return;
        }

        // Mostrar reservas pendientes
        System.out.println("\n--- Reservas Pendientes ---");
        for (int i = 0; i < reservasPendientes.size(); i++) {
            Reserva reserva = reservasPendientes.get(i);
            System.out.printf("%d. Nro Reserva: %s, Fecha de salida: %s, Destino: %s\n",
                    i + 1,
                    reserva.getNumeroReserva(),
                    reserva.getFechaReserva().toString(),
                    reserva.getVuelo().getRuta().getDestino().getCiudad());
        }

        // Solicitar al usuario que seleccione una reserva
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion < 1 || opcion > reservasPendientes.size()) {
            System.out.print("\nSelecciona el número de la reserva para realizar el check-in: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                if (opcion < 1 || opcion > reservasPendientes.size()) {
                    System.out.println("Opción inválida. Intenta nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido. Intenta nuevamente.");
            }
        }

        // Realizar el check-in para la reserva seleccionada
        Reserva reservaSeleccionada = reservasPendientes.get(opcion - 1);
        CheckIn checkInSeleccionado = reservaSeleccionada.getCheckIn();
        checkInSeleccionado.setFechaCheck(new Fecha()); // Fecha actual
        checkInSeleccionado.estadoCheck(true);

        // Agregar al pasajero a la lista de pasajeros del vuelo
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getNroVuelo().equals(reservaSeleccionada.getVuelo().getNroVuelo())){
                vuelo.getRegistroDeVuelo().getRegistroPasajeros().add(usuarioLogueado1);
            }
        }

        System.out.println("Check-in realizado con éxito para la reserva ID: " + reservaSeleccionada.getNumeroReserva());

        // Actualizar la lista de reservas en el archivo
        try {
            writeListToJsonFile(reservas, PATH_RESOURCES + PATH_RESERVAS);
            //System.out.println("Reservas actualizadas exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }

        // Actualizar la lista de vuelos en el archivo
        try {
            writeListToJsonFile(vuelos, PATH_RESOURCES + PATH_VUELOS);
            //System.out.println("Vuelos actualizados exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    private static void mostrarEstadoCheckIn(UsuarioCliente usuarioLogueado, ArrayListGeneric<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            if (reserva.getUsuarioLogueado().getIdPersona().equals(usuarioLogueado.getIdPersona())) {
                CheckIn checkIn = reserva.getCheckIn();
                if (checkIn != null) {
                    System.out.println("Estado del Check-In: " + (checkIn.getEstadoCheck() ? "Realizado" : "Pendiente"));
                    if (checkIn.getEstadoCheck()) {
                        System.out.println("Fecha del Check-In: " + checkIn.getFechaCheck());
                    }
                    return;
                }
            }
        }
        System.out.println("No se encontró una reserva para el usuario logueado.");
    }


}
