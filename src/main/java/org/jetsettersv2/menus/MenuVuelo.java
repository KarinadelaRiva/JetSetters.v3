package org.jetsettersv2.menus;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.models.abstracts.Persona;
import org.jetsettersv2.models.abstracts.PersonalAereo;
import org.jetsettersv2.models.concrete.Administrador;
import org.jetsettersv2.models.concrete.TripulacionCabina;
import org.jetsettersv2.models.concrete.Vuelo;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;
/*
public class MenuVuelo {
    public static void mostrarMenuVuelo() {
        Scanner scanner = new Scanner(System.in);
        Administrador admin = new Administrador();
        int opcion;

        do {
            System.out.println("\nMenú de Gestión de Vuelos:");
            System.out.println("1. Programar vuelo nuevo");
            System.out.println("2. Reprogramar vuelo existente");
            System.out.println("3. Asignar tripulación a vuelo");
            System.out.println("4. Ver vuelos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> programarVueloNuevo(scanner, admin);
                case 2 -> reprogramarVueloExistente(scanner, admin);
//                case 3 -> asignarTripulacion(scanner, admin);
                case 4 -> verVuelos(admin);
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);

    }

    public static void programarVueloNuevo(Scanner scanner, Administrador admin) {
        System.out.println("--- Programar Vuelo Nuevo ---");
        try {
            System.out.print("Ingrese el número de vuelo: ");
            String nroVuelo = scanner.nextLine();

            System.out.print("Ingrese el origen: ");
            String origen = scanner.nextLine();

            System.out.print("Ingrese el destino: ");
            String destino = scanner.nextLine();

            System.out.print("Ingrese la fecha de salida (YYYY-MM-DD): ");
            String fechaSalidaStr = scanner.nextLine();
            Fecha fechaSalida = Fecha.fechaActual(); // Asume un método estático parse en Fecha

            System.out.print("Ingrese la hora de salida (HH:MM): ");
            String horaSalida = scanner.nextLine();

            System.out.print("Ingrese la duración del vuelo (en horas): ");
            double duracion = scanner.nextDouble();
            scanner.nextLine(); // Consumir salto de línea

            Vuelo nuevoVuelo = new Vuelo();
            admin.añadirVuelosCollection(nuevoVuelo);

            System.out.println("¡Vuelo programado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al programar el vuelo: " + e.getMessage());
        }
    }


        public static void reprogramarVueloExistente (Scanner scanner, Administrador admin){
            System.out.println("\n--- Reprogramar Vuelo Existente ---");
            try {
                System.out.print("Ingrese la fecha original del vuelo (YYYY-MM-DD): ");
                String fechaOriginalStr = scanner.nextLine();
                Fecha fechaOriginal = Fecha.fechaActual();

                System.out.print("Ingrese la nueva fecha del vuelo (YYYY-MM-DD): ");
                String nuevaFechaStr = scanner.nextLine();
                Fecha nuevaFecha = Fecha.fechaActual();

                admin.reprogramarVuelos(fechaOriginal, nuevaFecha);

                System.out.println("Vuelo reprogramado exitosamente.");
            } catch (ElementoNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }

        private static void asignarTripulacion (Scanner scanner, Administrador admin){
            System.out.println("\n--- Asignar Tripulación a Vuelo ---");
            try {
                System.out.print("Ingrese el ID del vuelo: ");
                String nroVuelo = scanner.nextLine();

                System.out.print("Ingrese el nombre del tripulante: ");
                String nombreTripulante = scanner.nextLine();

                System.out.print("Ingrese el rol del tripulante (piloto, azafata, etc.): ");
                String rol = scanner.nextLine();

                 // Asume un constructor de Persona
                TripulacionCabina tripulante = new TripulacionCabina();
                admin.asignarTripulacionVuelo(nroVuelo, tripulante);

                System.out.println("Tripulación asignada con éxito.");
            } catch (ElementoNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }

        private static void verVuelos (Administrador admin) {
            admin.verCollectionAvion().forEach(System.out::println);


    }


}






//        private static void programarVueloNuevo(Scanner scanner, Administrador admin) {
//            System.out.print("Ingrese el ID del vuelo: ");
//            String idVuelo = scanner.nextLine();
//            System.out.print("Ingrese la fecha de salida (YYYY-MM-DD HH:MM): ");
//            String fechaSalida = scanner.nextLine();
//            // Asumimos que existe un método para convertir el String a un objeto Fecha y crear un vuelo
//            Fecha fecha = new Fecha(fechaSalida);
//            Vuelo vuelo = new Vuelo(nroVuelo, fecha);
//            admin.añadirVuelosCollection(vuelo);
//            System.out.println("Vuelo programado exitosamente.");
//        }
//
//        private static void reprogramarVueloExistente(Scanner scanner, Administrador admin) {
//            System.out.print("Ingrese la fecha original del vuelo (YYYY-MM-DD HH:MM): ");
//            String fechaOriginalStr = scanner.nextLine();
//            System.out.print("Ingrese la nueva fecha del vuelo (YYYY-MM-DD HH:MM): ");
//            String nuevaFechaStr = scanner.nextLine();
//            Fecha fechaOriginal = new Fecha(fechaOriginalStr);
//            Fecha nuevaFecha = new Fecha(nuevaFechaStr);
//            try {
//                admin.reprogramarVuelos(fechaOriginal, nuevaFecha);
//                System.out.println("Vuelo reprogramado exitosamente.");
//            } catch (ElementoNoEncontradoException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        private static void asignarTripulacion(Scanner scanner, Administrador admin) {
//            System.out.print("Ingrese el ID del vuelo: ");
//            String idVuelo = scanner.nextLine();
//            System.out.print("Ingrese el nombre del tripulante: ");
//            String nombreTripulante = scanner.nextLine();
//            // Asumimos que hay una forma de obtener un objeto Persona (tripulante) a partir del nombre
//            Persona tripulante = new Persona(nombreTripulante);
//            try {
//                admin.asignarTripulacionVuelo(idVuelo, tripulante);
//                System.out.println("Tripulación asignada exitosamente.");
//            } catch (ElementoNoEncontradoException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        private static void verVuelos(Administrador admin) {
//            System.out.println("Vuelos programados:");
//            for (Vuelo vuelo : admin.verCollectionVuelos()) {
//                System.out.println(vuelo);
//            }


*/
