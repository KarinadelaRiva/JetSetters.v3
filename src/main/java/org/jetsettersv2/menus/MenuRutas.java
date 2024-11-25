package org.jetsettersv2.menus;
import java.io.IOException;
import java.util.Scanner;

import org.jetsettersv2.exceptions.RutaDuplicadaException;
import org.jetsettersv2.models.concrete.Aeropuerto;

import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.Ruta;
import org.jetsettersv2.collections.ArrayListGeneric;

import static org.jetsettersv2.utilities.JacksonUtil.*;


public class MenuRutas {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuRutas() {
        int opcionRutas;

        do {
            System.out.println("\nGestión de Rutas:");
            System.out.println("1. Ver rutas");
            System.out.println("2. Definir nueva ruta");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcionRutas = scanner.nextInt();

            switch (opcionRutas) {
                case 1 -> mostrarRutas();
                case 2 -> agregarRuta();
                case 3 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcionRutas != 3);
    }

    public static void mostrarRutas() throws LeerJsonException {
        ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();

        try {
            rutas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RUTAS, Ruta.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON: " + e.getMessage());
        }

        // Verificar si la lista de rutas cargadas está vacía
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas disponibles.");
        } else {
            System.out.println("\nListado de rutas:");
            for (Ruta ruta : rutas) {
                ruta.imprimir(); // Llama al método imprimir de la clase Ruta
            }
        }

    }

    public static void agregarRuta() throws ElementoNoEncontradoException, LeerJsonException,RutaDuplicadaException {
        Ruta nuevaRuta = new Ruta();
        ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();
        ArrayListGeneric<Aeropuerto> aeropuertos = new ArrayListGeneric<>();

        try {
            rutas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RUTAS, Ruta.class));
            System.out.println("Rutas cargadas correctamente "+ rutas.size());
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON de rutas: " + e.getMessage());
        }


        try {
            aeropuertos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_AER, Aeropuerto.class));
            System.out.println("Aeropuertos cargados correctamente "+ aeropuertos.size());
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON de aeropuertos: " + e.getMessage());
        }


        System.out.println("\nDefinir Nueva Ruta:");

        // Ingresar y validar aeropuerto de origen
        boolean flag = false;
        do {
            System.out.print("Ingrese el código del aeropuerto de origen: ");
            String codigoOrigen = scanner.next().trim();

            for (Aeropuerto aeropuertoOrigen : aeropuertos) {
                if (aeropuertoOrigen.getCodigo().equalsIgnoreCase(codigoOrigen)) {
                    flag = true;
                    nuevaRuta.setOrigen(aeropuertoOrigen);
                }
            }

        }while (!flag);



        // Ingresar y validar aeropuerto de destino

        boolean flag2 = false;
        do {
            System.out.print("Ingrese el código del aeropuerto de destino: ");
            String codigoDestino = scanner.next().trim();

            for (Aeropuerto aeropuertoDestino : aeropuertos) {
                if (aeropuertoDestino.getCodigo().equalsIgnoreCase(codigoDestino)) {
                    flag2 = true;
                    if (aeropuertoDestino.equals(nuevaRuta.getOrigen())) {
                        System.out.println("El aeropuerto de destino no puede ser igual al de origen. Inténtelo de nuevo.");
                        flag2 = false;
                    } else {
                        nuevaRuta.setDestino(aeropuertoDestino);
                    }
                }
            }

        }while (!flag2);

        nuevaRuta.setIdRuta(nuevaRuta.getOrigen().getCodigo()+nuevaRuta.getDestino().getCodigo());

            // Verificamos si la ruta ya existe
        for (Ruta rutaExistente : rutas) {
            if (rutaExistente.getOrigen().getCodigo().equals(nuevaRuta.getOrigen().getCodigo()) &&
                    rutaExistente.getDestino().getCodigo().equals(nuevaRuta.getDestino().getCodigo())) {
                System.out.println("La ruta entre " + nuevaRuta.getOrigen().getCodigo() + " y " + nuevaRuta.getDestino().getCodigo() + " ya ha sido registrada.");
                System.out.println("Regresando al menú principal...");
                return; // Salir del método y regresar al menú principal
            }
        }



        // Ingresar y validar distancia
        double distanciaKM;
        do {
            try {
                System.out.print("Ingrese la distancia en kilómetros: ");
                distanciaKM = Double.parseDouble(scanner.next().trim());
                if (distanciaKM <= 0) {
                    System.out.println("La distancia debe ser mayor a 0. Inténtelo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La distancia debe ser un número válido. Inténtelo de nuevo.");
                distanciaKM = -1; // Reiniciar distancia
            }
        } while (distanciaKM <= 0);
        nuevaRuta.setDistanciaKM(distanciaKM);

        // Ingresar y validar duración
        long duracion;
        do {
            try {
                System.out.print("Ingrese la duración del vuelo en segundos: ");
                duracion = Long.parseLong(scanner.next().trim());
                if (duracion <= 0) {
                    System.out.println("La duración debe ser mayor a 0. Inténtelo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("La duración debe ser un número válido. Inténtelo de nuevo.");
                duracion = -1; // Reiniciar duración
            }
        } while (duracion <= 0);
        nuevaRuta.setDuracion(duracion);

        // Agregar la nueva ruta a la lista y escribir en el archivo JSON
        rutas.agregarElemento(nuevaRuta);
        rutas.add(nuevaRuta);
        try {
            writeListToJsonFile(rutas, PATH_RESOURCES + PATH_RUTAS);
            System.out.println("Ruta agregada exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }
}