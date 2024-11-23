package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.enums.EstadoReserva;
import org.jetsettersv2.models.concrete.Pasajero;
import org.jetsettersv2.models.concrete.UsuarioCliente;
import org.jetsettersv2.models.concrete.Vuelo;
import org.jetsettersv2.models.concrete.Reserva;

import java.util.Scanner;

public class MenuReserva {

    private ArrayListGeneric<Vuelo> vuelosDisponibles; // Lista de vuelos
    private UsuarioCliente usuarioLogueado;     // Pasajero actualmente logueado

    public MenuReserva(UsuarioCliente usuarioLogueado, ArrayListGeneric<Vuelo> vuelosDisponibles) {
        this.usuarioLogueado = usuarioLogueado;
        this.vuelosDisponibles = vuelosDisponibles;
    }

    public void mostrarMenuReservas() {
        Scanner scanner = new Scanner(System.in);
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
                    mostrarVuelosDisponibles();
                    break;
                case 2:
                    crearReserva(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del submenú...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private void mostrarVuelosDisponibles() {
        System.out.println("\n--- Vuelos Disponibles ---");
        for (int i = 0; i < vuelosDisponibles.size(); i++) {
            Vuelo vuelo = vuelosDisponibles.get(i);
            System.out.printf("%d. %s - Fecha: %s - Origen: %s - Destino: %s\n",
                    i + 1, vuelo.getNroVuelo(), vuelo.getFechaSalida().toString(), vuelo.getHoraSalida(),
                    vuelo.getRuta());
        }
    }

    private void crearReserva(Scanner scanner) {
        mostrarVuelosDisponibles();
        System.out.print("Selecciona el número del vuelo: ");
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (seleccion < 1 || seleccion > vuelosDisponibles.size()) {
            System.out.println("Selección inválida. Inténtalo de nuevo.");
            return;
        }

        Vuelo vueloSeleccionado = vuelosDisponibles.get(seleccion - 1);

        // Crear la reserva
        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setVuelo(vueloSeleccionado);
        nuevaReserva.setFechaReserva(vueloSeleccionado.getFechaSalida());
        nuevaReserva.setUsuarioLogueado(usuarioLogueado);
        nuevaReserva.setEstadoReserva(EstadoReserva.PENDIENTE); // Suponiendo un estado inicial
        nuevaReserva.setNumeroReserva(generarNumeroReserva());

        System.out.println("Reserva creada exitosamente:");
        System.out.printf("Vuelo: %s\nFecha: %s\nPasajero: %s\nNúmero de reserva: %s\n",
                vueloSeleccionado.getNroVuelo(),
                vueloSeleccionado.getFechaSalida().toString(),
                usuarioLogueado.getNombre(),
                nuevaReserva.getNumeroReserva());
    }

    private String generarNumeroReserva() {
        return "RES-" + System.currentTimeMillis(); // Generar un número único
    }
}
