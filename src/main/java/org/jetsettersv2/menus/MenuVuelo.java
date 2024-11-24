package org.jetsettersv2.menus;
import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.*;
import org.jetsettersv2.utilities.Fecha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuVuelo {

    private static final Scanner scanner = new Scanner(System.in);
    public static void mostrarMenuVuelo(Vuelo vuelo, Administrador  admin) {
        Scanner scanner = new Scanner(System.in);
        Administrador adminMenu = new Administrador();
        adminMenu = admin;

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
                case 1 -> programarVueloNuevo(adminMenu);
                case 2 -> reprogramarVueloExistente(adminMenu);
//                case 3 -> asignarTripulacion(scanner, admin);
                case 4 -> verVuelos(adminMenu);
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
            String fechaSalida = scanner.nextLine();

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
        Fecha fechaNueva = new Fecha();
        fechaNueva = null;
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

    public static ArrayListGeneric<Vuelo> cargarvuelosDesdeJson(ArrayListGeneric<Vuelo> vuelos) throws LeerJsonException {
        ArrayListGeneric<Vuelo> vuelosCargados = new ArrayListGeneric<>();

        try {
            // Cargar vuelos desde el archivo JSON y copiarlas a la lista
            ArrayList<Vuelo> listaRutas = getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class);
            vuelosCargados.copiarLista(listaRutas);  // Copiar los vuelos a la lista

            System.out.println("VUELOS CARGADOS: " + vuelosCargados.size());

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            throw new LeerJsonException("Error al cargar los vuelos desde el archivo JSON.");
        }

        return vuelosCargados;
    }

    public static void subMenuModificarVuelo() throws LeerJsonException{
        ArrayListGeneric<Administrador> admin = new ArrayListGeneric<>();
        ArrayListGeneric<Vuelo> vuelo = new ArrayListGeneric<>();


        try{
            admin.copiarLista(getJsonToList(PATH_RESOURCES + PATH_ADMINISTRADORES, Administrador.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores: " + e.getMessage());
        }

        try{
            vuelo.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

            int opcion;

            do {
                System.out.println("\nSubmenú - Modificar Datos de Vuelo:");
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
                        reprogramarVueloExistente("Fecha de salida",nuevoValor );
                    }
                    case 2 -> {
                        System.out.print("Ingrese el nuevo horario de salida: ");
                        reprogramarVueloExistente(admin);
                    }
                    case 6 -> System.out.println("Saliendo del submenú...");
                    default -> System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (opcion != 6);
        }


    public static void mostrarvuelos() {
        ArrayListGeneric<Vuelo> vuelos = new ArrayListGeneric<>();

        try {
            // Cargar los vuelos desde el archivo JSON
            ArrayListGeneric<Vuelo> vuelosCargados = cargarvuelosDesdeJson(vuelos);

            // Verificar si la lista de vuelos cargados está vacía
            if (vuelosCargados.isEmpty()) {
                System.out.println("No hay vuelos disponibles.");
            } else {
                System.out.println("\nListado de vuelos:");
                for (Vuelo vuelo: vuelosCargados) {
                    vuelo.toString(); // Llama al método imprimir de la clase vuelo
                }
            }
        } catch (LeerJsonException e) {
            System.err.println("Error al cargar loa vuelos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Se ha producido un error inesperado: " + e.getMessage());
        }
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

//        private static void asignarTripulacion (Administrador admin){
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("\n--- Asignar Tripulación a Vuelo ---");
//            try {
//                System.out.print("Ingrese el numero del vuelo: ");
//                String nroVuelo = scanner.nextLine();
//
//                System.out.print("Ingrese el nombre del tripulante: ");
//                String nombreTripulante = scanner.nextLine();
//
//                System.out.print("Ingrese el Tipo del tripulante (piloto, azafata, etc.): ");
//                TipoPersonalAereo tipoTripulante = null;
//                boolean valido = false;
//                String tipo = scanner.nextLine().toUpperCase(); // Convertir a mayúsculas para coincidir con el enum
//                tipoTripulante = TipoPersonalAereo.valueOf(tipo); // Convertir el texto al enum
//                valido = true;
//
//                 // Asume un constructor de Persona
//                TripulacionCabina tripulante = new TripulacionCabina();
//                admin.asignarTripulacionVuelo(nroVuelo, tripulante);
//
//                System.out.println("Tripulación asignada con éxito.");
//            } catch (ElementoNoEncontradoException e) {
//                System.out.println("Error: " + e.getMessage());
//            } catch (Exception e) {
//                System.out.println("Error inesperado: " + e.getMessage());
//            }
//        }

        private static void verVuelos (Administrador admin) {
            admin.verCollectionAvion().forEach(System.out::println);


    }


}



