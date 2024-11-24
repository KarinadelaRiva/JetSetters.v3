package org.jetsettersv2.menus;
import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.CapacidadExcedidaException;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.exceptions.RutaDuplicadaException;
import org.jetsettersv2.models.concrete.*;
import org.jetsettersv2.utilities.Fecha;
import org.jetsettersv2.utilities.Hora;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuVuelo {

    private static final Scanner scanner = new Scanner(System.in);
    public static void mostrarMenuVuelo() {
        Scanner scanner = new Scanner(System.in);

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
                case 1 :
                    try{
                        programarVueloNuevo();
                    } catch (LeerJsonException e) {
                        System.err.println("Error al programar el vuelo: " + e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 2:
                    try {
                        subMenuModificarVuelo();
                    } catch (LeerJsonException e) {
                        System.err.println("Error al reprogramar el vuelo: " + e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 3:
                    //asignarTripulacion(scanner, admin);
                    pausarConTecla();
                    break;
                case 4:
                    mostrarvuelos();
                    pausarConTecla();
                    break;
                case 5:
                    System.out.println(" ");
                    break;
                default :
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);

    }

    public static void programarVueloNuevo() throws LeerJsonException {
        Scanner scanner = new Scanner(System.in);
        ArrayListGeneric<Vuelo> vuelos= new ArrayListGeneric<>();

        try{
            vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

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
            vuelos.add(nuevoVuelo);

            System.out.println("¡Vuelo programado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al programar el vuelo: " + e.getMessage());
        }

        try {
            writeListToJsonFile(vuelos, PATH_RESOURCES + PATH_VUELOS);
            System.out.println("Vuelo programado con éxito.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }

    }


    public static Vuelo reprogramarVueloExistente(Vuelo vuelo) {
        Scanner scanner = new Scanner(System.in);
        Vuelo vueloModificado = vuelo;
        String fechaNueva;
        String horaNueva;
        int opcion;

        do {
            System.out.println("\nSubmenú - Modificar Datos:");
            System.out.println("1. Modificar fecha y hora de salida");
            System.out.println("0. Confirmar y Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            Fecha nuevoValor = new Fecha();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese La nueva fecha de salida (AAAA-MM-DD): ");
                    fechaNueva = scanner.nextLine();
                    vueloModificado.setFechaSalida(new Fecha(fechaNueva));

                    System.out.println("Ingrese La nueva hora de salida (HH:MM:SS)");
                    horaNueva = scanner.nextLine();
                    vueloModificado.setHoraSalida(new Hora(horaNueva));
                }
                case 0 -> System.out.println(" ");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
        return vueloModificado;
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

    public static int buscarPosVueloPorNroVuelo(String nroVuelo, ArrayListGeneric<Vuelo> vuelo) throws ElementoNoEncontradoException {
        for (int i = 0; i < vuelo.size(); i++) {
            if (vuelo.get(i).getNroVuelo().equals(nroVuelo)) {
                return i;  // Retorna la posición del elemento si coincide el nro de vuelo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un Vuelo con el numero: " + nroVuelo);
    }

    public static void subMenuModificarVuelo() throws LeerJsonException{
        String nroVueloBuscado;
        ArrayListGeneric<Vuelo> vuelos= new ArrayListGeneric<>();

        try{
            vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

        System.out.print("Ingrese el NUMERO de vuelo a modificar: ");
        nroVueloBuscado = scanner.nextLine(); // Consumir salto de línea

        System.out.println("Buscando vuelo numero: " + nroVueloBuscado);

        int posVuelo = -1;
        try{
            posVuelo = buscarPosVueloPorNroVuelo(nroVueloBuscado,vuelos);
            //vuelo.get(posVuelo).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posVuelo != -1) {
            Vuelo vueloModificado = reprogramarVueloExistente(vuelos.get(posVuelo));
            vuelos.set(posVuelo, vueloModificado);
            System.out.println("Vuelo modificado con éxito.");
        }

        try {
            writeListToJsonFile(vuelos, PATH_RESOURCES + PATH_VUELOS);
            System.out.println("Modificación exitosa.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
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
    public void agregarTripulantesDesdeJson(String pathJson) throws CapacidadExcedidaException, LeerJsonException {
        try {
            // Leer tripulantes desde el archivo JSON
            List<RegistroDeVuelo> registroDeVuelos = getJsonToList(pathJson, RegistroDeVuelo.class);

            // Verificar si la lista está vacía
            if (registroDeVuelos == null || registroDeVuelos.isEmpty()) {
                throw new LeerJsonException("El archivo JSON no contiene datos de tripulación.");
            }

            // Agregar cada tripulante de la lista
            for (RegistroDeVuelo registroDeVuelo1 : registroDeVuelos) {
                if (avion.etLista().size() < avion.getCapacidadTripulanteCabina()) {
                    registroTripulanteCabina.agregarElemento(tripulante);
                    System.out.println("Tripulante " + tripulante.getNombre() + " agregado correctamente.");
                } else {
                    throw new CapacidadExcedidaException("No se pueden agregar más tripulantes de cabina. Capacidad máxima alcanzada.");
                }
            }
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer o procesar el archivo JSON: " + e.getMessage(), e);
        }
    }




}



