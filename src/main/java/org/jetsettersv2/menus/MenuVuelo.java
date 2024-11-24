package org.jetsettersv2.menus;
import org.jetsettersv2.enums.TipoPersonalAereo;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.models.abstracts.Persona;
import org.jetsettersv2.models.abstracts.PersonalAereo;
import org.jetsettersv2.models.concrete.Administrador;
import org.jetsettersv2.models.concrete.TripulacionCabina;
import org.jetsettersv2.models.concrete.UsuarioCliente;
import org.jetsettersv2.models.concrete.Vuelo;
import org.jetsettersv2.utilities.Fecha;
import org.jetsettersv2.models.concrete.Avion;

import java.util.Scanner;

public class MenuVuelo {
    public static void mostrarMenuVuelo(Vuelo vuelo) {
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
                case 1 -> programarVueloNuevo(admin);
                case 2 -> reprogramarVueloExistente(admin);
//                case 3 -> asignarTripulacion(scanner, admin);
                case 4 -> verVuelos(admin);
                case 5 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);

    }

    public static void programarVueloNuevo(Administrador admin) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("--- Programar Vuelo Nuevo ---");
        try {
            System.out.print("Ingrese el número de vuelo: ");
            String nroVuelo = scanner.nextLine();

            System.out.print("Ingrese un Avion para este vuelo: ");
            String avion = scanner.nextLine();




            System.out.print("Ingrese la ruta: ");
            String ruta = scanner.nextLine();

            System.out.print("Ingrese la fecha de salida (YYYY-MM-DD): ");
            //admin.añadirVuelosCollection();


            System.out.print("Ingrese la hora de salida (HH:MM): ");
            String horaSalida = scanner.nextLine();


            Vuelo nuevoVuelo = new Vuelo();
            admin.añadirVuelosCollection(nuevoVuelo);

            System.out.println("¡Vuelo programado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al programar el vuelo: " + e.getMessage());
        }
    }


    public static void reprogramarVueloExistente(Administrador admin) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nSubmenú - Modificar Datos:");
            System.out.println("1. Modificar fecha de salida");
            System.out.println("2. Modificar horario de salida");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            Fecha nuevoValor = new Fecha();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese La nueva fecha de salida: ");
                    admin.reprogramarVuelos("Fecha de salida",nuevoValor );
                }
                case 2 -> {
                    System.out.print("Ingrese el nuevo horario de salida: ");
                    admin.reprogramarVuelos("Horario de salida", nuevoValor);
                }
                case 6 -> System.out.println("Saliendo del submenú...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }


//        public static void reprogramarVueloExistente (Administrador admin){
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("\n--- Reprogramar Vuelo Existente ---");
//            try {
//                System.out.print("Ingrese la fecha original del vuelo (YYYY-MM-DD): ");
//                String fechaOriginalStr = scanner.nextLine();
//                Fecha fechaOriginal = Fecha.fechaActual();
//
//                System.out.print("Ingrese la nueva fecha del vuelo (YYYY-MM-DD): ");
//                String nuevaFechaStr = scanner.nextLine();
//                Fecha nuevaFecha = Fecha.fechaActual();
//
//                admin.reprogramarVuelos(fechaOriginal, nuevaFecha);
//
//                System.out.println("Vuelo reprogramado exitosamente.");
//            } catch (ElementoNoEncontradoException e) {
//                System.out.println("Error: " + e.getMessage());
//            } catch (Exception e) {
//                System.out.println("Error inesperado: " + e.getMessage());
//            }
//        }

        private static void asignarTripulacion (Administrador admin){
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n--- Asignar Tripulación a Vuelo ---");
            try {
                System.out.print("Ingrese el numero del vuelo: ");
                String nroVuelo = scanner.nextLine();

                System.out.print("Ingrese el nombre del tripulante: ");
                String nombreTripulante = scanner.nextLine();

                System.out.print("Ingrese el Tipo del tripulante (piloto, azafata, etc.): ");
                TipoPersonalAereo tipoTripulante = null;
                boolean valido = false;
                String tipo = scanner.nextLine().toUpperCase(); // Convertir a mayúsculas para coincidir con el enum
                tipoTripulante = TipoPersonalAereo.valueOf(tipo); // Convertir el texto al enum
                valido = true;

                 // Asume un constructor de Persona
                TripulacionCabina tripulante = new TripulacionCabina();
                //admin.asignarTripulacionVuelo(nroVuelo, tripulante);

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



