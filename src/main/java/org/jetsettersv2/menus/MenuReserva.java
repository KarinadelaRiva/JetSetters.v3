package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.enums.EstadoReserva;
import org.jetsettersv2.models.concrete.Pasajero;
import org.jetsettersv2.models.concrete.UsuarioCliente;
import org.jetsettersv2.models.concrete.Vuelo;
import org.jetsettersv2.models.concrete.Reserva;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;

import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuReserva {

    private static Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuReservas(UsuarioCliente usuarioLogueado) {

        ArrayListGeneric<Vuelo> vuelosDisponibles = new ArrayListGeneric<>(); // Lista de vuelos
        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();

        try{
            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }


        try{
            vuelosDisponibles.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }



        while (true) {
            System.out.println("\n--- Submenú de Reservas ---");
            System.out.println("1. Mostrar vuelos disponibles");
            System.out.println("2. Seleccionar vuelo y crear reserva");
            System.out.println("3. Salir");

            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    mostrarVuelosDisponibles( vuelosDisponibles);
                    break;
                case 2:
                    crearReserva(vuelosDisponibles, usuarioLogueado);
                    break;
                case 3:
                    System.out.println("Saliendo del submenú...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void mostrarVuelosDisponibles(ArrayListGeneric<Vuelo> vuelosDisponibles ) {
        System.out.println("\n--- Vuelos Disponibles ---");
        for (int i = 0; i < vuelosDisponibles.size(); i++) {
            Vuelo vuelo = vuelosDisponibles.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, "Vuelo numero: " + vuelo.getNroVuelo() + "Fecha Salida: " + vuelo.getFechaSalida().toString());
            System.out.printf("Origen: "+ vuelo.getRuta().getOrigen().getCiudad()+ "- Destino: %s\n", vuelo.getRuta().getDestino().getCiudad());
        }
    }

    private static void crearReserva( ArrayListGeneric<Vuelo> vuelosDisponibles, UsuarioCliente usuarioLogueado) {

        ArrayListGeneric<Reserva> reservas = new ArrayListGeneric<>();

        try{
            reservas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RESERVAS, Reserva.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        mostrarVuelosDisponibles(vuelosDisponibles);
        System.out.print("Selecciona el número del vuelo: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccion < 1 || seleccion > vuelosDisponibles.size()) {
            System.out.println("Selección inválida. Inténtalo de nuevo.");
            return;
        }

        Vuelo vueloSeleccionado = vuelosDisponibles.get(seleccion - 1);

        // Crear la reserva
        Fecha hoy = new Fecha();

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setVuelo(vueloSeleccionado);
        nuevaReserva.setFechaReserva(hoy);
        nuevaReserva.setUsuarioLogueado(usuarioLogueado);
        nuevaReserva.setEstadoReserva(EstadoReserva.PENDIENTE); // Suponiendo un estado inicial
        nuevaReserva.setNumeroReserva(generarNumeroReserva());
        nuevaReserva.setCheckIn(vueloSeleccionado.getFechaSalida());


        System.out.println("Reserva creada exitosamente:");
        System.out.printf("Vuelo: %s\nFecha: %s\nPasajero: %s\nNúmero de reserva: %s\n",
                vueloSeleccionado.getNroVuelo(),
                vueloSeleccionado.getFechaSalida().toString(),
                usuarioLogueado.getNombre(),
                nuevaReserva.getNumeroReserva());

        reservas.add(nuevaReserva);

        try{
            writeListToJsonFile(reservas, PATH_RESOURCES + PATH_RESERVAS);
            System.out.println("Reserva exitosa.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
}

    }

    private static String generarNumeroReserva() {
        return "RES-" + System.currentTimeMillis(); // Generar un número único
    }
}
