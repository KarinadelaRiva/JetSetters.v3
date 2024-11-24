package org.jetsettersv2.menus;
import org.jetsettersv2.exceptions.CapacidadExcedidaException;
import org.jetsettersv2.models.abstracts.Equipaje;
import org.jetsettersv2.models.concrete.*;

import java.util.Scanner;

public class MenuRegistroDeVuelo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegistroDeVuelo registroDeVuelo = new RegistroDeVuelo();

        //Ver como relaciionarlo con el archivo
        //Hacer metodo realizar cheeckin
        // agregar estado de checkin COMPLETADO Y NO_REALIZADO

        // Configurar un avión para el registro
        Avion avion = new Avion(); // inicializar el avión con capacidad y otros detalles.
        avion.setCapacidadPasajeros(200);
        avion.setCapacidadEquipaje(500);
        avion.setCapacidadTripulanteCabina(10);
        avion.setCapacidadTripulanteTecnico(5);
        registroDeVuelo.setAvion(avion);

        int opcion;

        do {
            System.out.println("'\n' Menú registro de vuelo: ");
            System.out.println("1. Agregar pasajero");
            System.out.println("2. Agregar equipaje");
            System.out.println("3. Agregar tripulante de cabina");
            System.out.println("4. Agregar tripulante técnico");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nombre del pasajero: ");
                        String nombrePasajero = scanner.nextLine();
                        Pasajero pasajero = new Pasajero(new UsuarioCliente(nombrePasajero)); // Crear pasajero
                        //validar que exista en el archivo, lo hago desp. fijarme si hace falta
                        pasajero.getCheckIn().setEstadoCheck(EstadoCheck.COMPLETAD0); // Simular check-in completado
                        registroDeVuelo.agregarPasajero(pasajero);
                        System.out.println("Pasajero agregado exitosamente.");
                        break;
                    case 2:
                        System.out.print("Ingrese el peso del equipaje: ");
                        double pesoEquipaje = scanner.nextDouble();
                        Equipaje equipaje = new Equipaje(pesoEquipaje); // Crear equipaje
                        registroDeVuelo.agregarEquipaje(equipaje);
                        System.out.println("Equipaje agregado exitosamente.");
                        break;
                    case 3:
                        System.out.print("Ingrese el nombre del tripulante de cabina: ");
                        String nombreCabina = scanner.nextLine();
                        TripulacionCabina tripulanteCabina = new TripulacionCabina(nombreCabina); // Crear tripulante
                        registroDeVuelo.agregarTripulanteCabina(tripulanteCabina);
                        System.out.println("Tripulante de cabina agregado exitosamente.");
                        break;
                    case 4:
                        System.out.print("Ingrese el nombre del tripulante técnico: ");
                        String nombreTecnico = scanner.nextLine();
                        TripulacionTecnica tripulanteTecnico = new TripulacionTecnica(nombreTecnico); // Crear tripulante técnico
                        registroDeVuelo.agregarTripulanteTecnico(tripulanteTecnico);
                        System.out.println("Tripulante técnico agregado exitosamente.");
                        break;
                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (CapacidadExcedidaException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }

        } while (opcion != 5);

        scanner.close();
    }
}