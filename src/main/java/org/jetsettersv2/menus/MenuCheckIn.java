package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.models.abstracts.Persona;
import org.jetsettersv2.models.concrete.CheckIn;
import org.jetsettersv2.models.concrete.Reserva;
import org.jetsettersv2.models.concrete.UsuarioCliente;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;

import static org.jetsettersv2.utilities.JacksonUtil.*;

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
            System.out.println("\n--- Submenú de Check-In ---");
            System.out.println("1. Realizar check-in");
            System.out.println("2. Mostrar estado del check-in");
            System.out.println("3. Salir");

            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    realizarCheckIn(usuarioLogueado);
                    break;
                case 2:
                    mostrarEstadoCheckIn(usuarioLogueado, reservas);
                    break;
                case 3:
                    System.out.println("Saliendo del submenú de check-in...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void realizarCheckIn(UsuarioCliente usuarioLogueado) {

        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();

        try {
            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        for (Reserva reserva : reservas) {
            if (reserva.getUsuarioLogueado().equals(usuarioLogueado)) {
                CheckIn checkIn = reserva.getCheckIn();
                if (checkIn != null && !checkIn.getEstadoCheck()) {
                    checkIn.setFechaCheck(new Fecha()); // Fecha actual
                    checkIn.estadoCheck(true);
                    System.out.println("Check-in realizado con éxito.");
                    return;
                } else if (checkIn != null && checkIn.getEstadoCheck()) {
                    System.out.println("El check-in ya fue realizado anteriormente.");
                    return;
                }
            }
        }
        System.out.println("No se encontró una reserva activa para el usuario logueado.");

        try {
            writeListToJsonFile(reservas, PATH_RESOURCES + PATH_RESERVAS);
            System.out.println("Reserva exitosa.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    private static void mostrarEstadoCheckIn(UsuarioCliente usuarioLogueado, ArrayListGeneric<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            if (reserva.getUsuarioLogueado().equals(usuarioLogueado)) {
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
        System.out.println("No se encontró una reserva activa para el usuario logueado.");
    }


}
