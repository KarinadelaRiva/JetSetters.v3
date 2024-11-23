package org.jetsettersv2.menus;
import java.util.List;
import java.util.Scanner;

import org.jetsettersv2.enums.Aeropuerto;
import java.time.Duration;
import org.jetsettersv2.models.concrete.Ruta;
import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.utilities.JacksonUtil;



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
                case 1 -> mostrarRutas(rutas);
                case 2 ->agregarRuta(rutas);
                case 3 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcionRutas != 3);
    }

    private static ArrayListGeneric<Ruta> cargarRutasDesdeJson() {
        System.out.println("Cargando rutas desde archivo JSON...");
        List<Ruta> listaRutas = JacksonUtil.getJsonToList(JacksonUtil.PATH_RESOURCES + JacksonUtil.PATH_RUTAS, Ruta.class);

        ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();
        rutas.copiarLista(listaRutas);

        System.out.println("Rutas cargadas: " + rutas.size());
        return rutas;
    }

    private static void mostrarRutas(ArrayListGeneric<Ruta> rutas) {
        cargarRutasDesdeJson();
        if (rutas.isEmpty()) {
            System.out.println("\nNo hay rutas disponibles.");
        } else {
            System.out.println("\nListado de rutas:");
            rutas.mostrarLista();
        }
    }

    public static void agregarRuta(ArrayListGeneric<Ruta> rutas) {
        Ruta nuevaRuta = new Ruta();

        System.out.println("\nDefinir Nueva Ruta:");

        // Selección de aeropuerto de origen
        System.out.println("Seleccione el aeropuerto de origen:");
        System.out.println("1. EZE - Aeropuerto Internacional Ministro Pistarini");
        System.out.println("2. AEP - Aeroparque Jorge Newbery");
        System.out.println("3. ROS - Aeropuerto Internacional Rosario Islas Malvinas");
        System.out.println("4. BRC - Aeropuerto Internacional Teniente Luis Candelaria");
        System.out.println("5. FTE - Aeropuerto Internacional Comandante Armando Tola");
        System.out.println("6. MDZ - Aeropuerto Internacional El Plumerillo");
        System.out.println("7. NQN - Aeropuerto Internacional Presidente Perón");
        System.out.println("8. AEP - Aeroparque Jorge Newbery");

        System.out.print("Ingrese el número del aeropuerto de origen: ");
        int origenSeleccionado = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Aeropuerto aeropuertoOrigen = null;

        switch (origenSeleccionado) {
            case 1:
                aeropuertoOrigen = Aeropuerto.EZE;
                break;
            case 2:
                aeropuertoOrigen = Aeropuerto.AEP;
                break;
            case 3:
                aeropuertoOrigen = Aeropuerto.ROS;
                break;
            case 4:
                aeropuertoOrigen = Aeropuerto.BRC;
                break;
            case 5:
                aeropuertoOrigen = Aeropuerto.FTE;
                break;
            case 6:
                aeropuertoOrigen = Aeropuerto.MDZ;
                break;
            case 7:
                aeropuertoOrigen = Aeropuerto.NQN;
                break;
            case 8:
                aeropuertoOrigen = Aeropuerto.AEP; // Duplicado de AEP en la lista
                break;
            default:
                System.out.println("Selección inválida");
                return;
        }

        System.out.println("Aeropuerto de origen seleccionado: " + aeropuertoOrigen);

        nuevaRuta.setOrigen(aeropuertoOrigen);

        // Selección de aeropuerto de destino
        System.out.println("\nSeleccione el aeropuerto de destino:");
        System.out.println("1. COR - Aeropuerto Internacional Ingeniero Ambrosio Taravella");
        System.out.println("2. MDZ - Aeropuerto Internacional El Plumerillo");
        System.out.println("3. SLA - Aeropuerto Internacional Martín Miguel de Güemes");
        System.out.println("4. USH - Aeropuerto Internacional Malvinas Argentinas");
        System.out.println("5. RGL - Aeropuerto Internacional Piloto Civil Norberto Fernández");
        System.out.println("6. AEP - Aeroparque Jorge Newbery");
        System.out.println("7. TUC - Aeropuerto Internacional Teniente Benjamín Matienzo");
        System.out.println("8. REL - Aeropuerto Internacional Almirante Marcos A. Zar");
        System.out.println("9. CRD - Aeropuerto Internacional General Enrique Mosconi");

        System.out.print("Ingrese el número del aeropuerto de destino: ");
        int destinoSeleccionado = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Aeropuerto aeropuertoDestino = null;

        switch (destinoSeleccionado) {
            case 1:
                aeropuertoDestino = Aeropuerto.COR;
                break;
            case 2:
                aeropuertoDestino = Aeropuerto.MDZ;
                break;
            case 3:
                aeropuertoDestino = Aeropuerto.SLA;
                break;
            case 4:
                aeropuertoDestino = Aeropuerto.USH;
                break;
            case 5:
                aeropuertoDestino = Aeropuerto.RGL;
                break;
            case 6:
                aeropuertoDestino = Aeropuerto.AEP;
                break;
            case 7:
                aeropuertoDestino = Aeropuerto.TUC;
                break;
            case 8:
                aeropuertoDestino = Aeropuerto.REL;
                break;
            case 9:
                aeropuertoDestino = Aeropuerto.CRD;
                break;
            default:
                System.out.println("Selección inválida");
                return;
        }

        System.out.println("Aeropuerto de destino seleccionado: " + aeropuertoDestino);
        nuevaRuta.setDestino(aeropuertoDestino);

        // Verificar que el origen y destino no sean el mismo
        if (aeropuertoOrigen != null && aeropuertoDestino != null && aeropuertoOrigen == aeropuertoDestino) {
            System.out.println("El aeropuerto de origen y destino no pueden ser el mismo. Intente nuevamente.");
            return;
        }

        // Verificar que la ruta no exista previamente
        for (Ruta ruta : rutas.getLista()) {
            if (ruta.getOrigen() == aeropuertoOrigen && ruta.getDestino() == aeropuertoDestino) {
                System.out.println("La ruta ya existe en el sistema.");
                return;
            }
        }

        // Establecer distancia
        System.out.print("Ingrese la distancia en kilómetros: ");
        double distanciaKM = scanner.nextDouble();
        nuevaRuta.setDistanciaKM(distanciaKM);

        // Establecer duración
        System.out.print("Ingrese la duración en horas: ");
        long horas = scanner.nextLong();
        scanner.nextLine(); // Consumir nueva línea
        Duration duracion = Duration.ofHours(horas);
        nuevaRuta.setDuracion(duracion);

        // Agregar la nueva ruta a la lista
        rutas.agregarElemento(nuevaRuta);
        System.out.println("¡Ruta agregada exitosamente!");

    }
}

