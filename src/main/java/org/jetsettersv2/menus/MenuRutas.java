package org.jetsettersv2.menus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jetsettersv2.models.concrete.Aeropuerto;
import java.time.Duration;

import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.Ruta;
import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.utilities.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

import static org.jetsettersv2.utilities.JacksonUtil.*;


public class MenuRutas {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuRutas(ArrayListGeneric<Ruta> rutas) {
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

    public static ArrayListGeneric<Ruta> cargarRutasDesdeJson(ArrayListGeneric<Ruta> rutas) throws LeerJsonException {
        ArrayListGeneric<Ruta> rutasCargadas = new ArrayListGeneric<>();

        try {
            // Cargar rutas desde el archivo JSON y copiarlas a la lista
            ArrayList<Ruta> listaRutas = getJsonToList(PATH_RESOURCES + PATH_RUTAS, Ruta.class);
            rutasCargadas.copiarLista(listaRutas);  // Copiar las rutas a la lista

            System.out.println("Rutas cargadas: " + rutasCargadas.size());

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            throw new LeerJsonException("Error al cargar las rutas desde el archivo JSON.");
        }

        return rutasCargadas;
    }

    public static ArrayListGeneric<Aeropuerto> cargarAEPDesdeJson(ArrayListGeneric<Aeropuerto> aeropuertos) throws LeerJsonException {
        ArrayListGeneric<Aeropuerto> aeropuertosCargados = new ArrayListGeneric<>();

        try {
            // Cargar aeropuertos desde el archivo JSON y copiarlos a la lista
            ArrayList<Aeropuerto> listaAeropuertos = getJsonToList(PATH_RESOURCES + PATH_AER, Aeropuerto.class);
            aeropuertosCargados.copiarLista(listaAeropuertos);  // Copiar los aeropuertos a la lista

            System.out.println("Aeropuertos cargados: " + aeropuertosCargados.size());

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            throw new LeerJsonException("Error al cargar los aeropuertos desde el archivo JSON.");
        }

        return aeropuertosCargados;
    }

    public static void mostrarRutas() {
        ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();

        try {
            // Cargar las rutas desde el archivo JSON
            ArrayListGeneric<Ruta> rutasCargadas = cargarRutasDesdeJson(rutas);

            // Verificar si la lista de rutas cargadas está vacía
            if (rutasCargadas.isEmpty()) {
                System.out.println("No hay rutas disponibles.");
            } else {
                System.out.println("\nListado de rutas:");
                for (Ruta ruta : rutasCargadas) {
                    ruta.imprimir(); // Llama al método imprimir de la clase Ruta
                }
            }
        } catch (LeerJsonException e) {
            System.err.println("Error al cargar las rutas: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Se ha producido un error inesperado: " + e.getMessage());
        }
    }

    public static void agregarRuta() throws ElementoNoEncontradoException {
        Ruta nuevaRuta = new Ruta();
        ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();
        ArrayListGeneric<Aeropuerto> aeropuertos = new ArrayListGeneric<>();

        try {
            ArrayList<Ruta> rutasExistentes = getJsonToList(PATH_RESOURCES + PATH_RUTAS, Ruta.class);
            if (rutasExistentes != null) {
                rutas.copiarLista(rutasExistentes);
            }

            // Cargar aeropuertos desde JSON
            aeropuertos = cargarAEPDesdeJson(aeropuertos);
        } catch (ElementoNoEncontradoException e) {
            System.err.println("No se pudieron cargar las rutas existentes: " + e.getMessage());
        }

        System.out.println("\nDefinir Nueva Ruta:");
        System.out.print("Ingrese el aeropuerto de origen: ");
        String aeropuertoOrigen = scanner.next().trim();

        Aeropuerto origen = null;
        for (Aeropuerto aep : aeropuertos) {
            if (aep.getCodigo().equals(aeropuertoOrigen)) {
                origen = aep;
                break;
            }
        }

        if (origen == null) {
            throw new ElementoNoEncontradoException("El código del aeropuerto de origen no es válido.");
        } else {
            nuevaRuta.setOrigen(origen);
        }

        System.out.print("Ingrese el aeropuerto de destino: ");
        String aeropuertoDestino = scanner.next().trim();

        Aeropuerto destino = null;
        for (Aeropuerto aep : aeropuertos) {
            if (aep.getCodigo().equals(aeropuertoDestino)) {
                destino = aep;
                break;
            }
        }

        if (destino == null) {
            throw new ElementoNoEncontradoException("El código del aeropuerto de destino no es válido.");
        } else {
            nuevaRuta.setDestino(destino);
        }

        if (aeropuertoOrigen.equalsIgnoreCase(aeropuertoDestino)) {
            throw new ElementoNoEncontradoException("El aeropuerto de origen y destino no pueden ser el mismo.");
        }

        System.out.print("Ingrese la distancia en kilómetros: ");
        double distanciaKM;
        try {
            distanciaKM = Double.parseDouble(scanner.next().trim());
            nuevaRuta.setDistanciaKM(distanciaKM);
        } catch (NumberFormatException e) {
            throw new ElementoNoEncontradoException("La distancia debe ser un número entero válido.");
        }

        System.out.print("Ingrese la duración en segundos: ");
        long duracion;
        try {
            duracion = Long.parseLong(scanner.next().trim());
            nuevaRuta.setDuracion(duracion);
        } catch (NumberFormatException e) {
            throw new ElementoNoEncontradoException("La duración debe ser un número entero válido.");
        }

        rutas.agregarElemento(nuevaRuta);
        rutas.mostrarLista();

        try {
            writeListToJsonFile(rutas, PATH_RESOURCES + PATH_RUTAS);
            System.out.println("Ruta agregada exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }
}
