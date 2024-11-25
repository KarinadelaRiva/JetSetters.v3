package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.enums.TipoPersonalAereo;
import org.jetsettersv2.exceptions.CapacidadExcedidaException;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.Administrador;
import org.jetsettersv2.models.concrete.TripulacionCabina;
import org.jetsettersv2.models.concrete.TripulacionTecnica;
import org.jetsettersv2.models.concrete.Vuelo;

import java.util.Scanner;

import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.menus.MenuVuelo.buscarPosVueloPorNroVuelo;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class SubMenuAsignarTrip {
    private static final Scanner scanner = new Scanner(System.in);

    public static void gestionarTripulacion(){
        ArrayListGeneric<Vuelo> vuelos = new ArrayListGeneric<>();

        try{
            vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON");
        }

        System.out.println("Ingrese el número de vuelo a buscar: ");
        String nroVuelo = scanner.nextLine();

        try {
            int posicion = buscarPosVueloPorNroVuelo(nroVuelo, vuelos);
            vuelos.set(posicion, asignarTripulacion(vuelos.get(posicion)));
        } catch (ElementoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        try {
            writeListToJsonFile(vuelos, PATH_RESOURCES + PATH_VUELOS);
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static Vuelo asignarTripulacion(Vuelo vuelo) {
        int opcion;

        do {
            System.out.println("\nMenú de Gestion Tripulación:");
            System.out.println("1. Asignar piloto a vuelo");
            System.out.println("2. Asignar azafata a vuelo");
            System.out.println("3. Asignar otro tipo de tripulante tecnico a vuelo");
            System.out.println("4. Asignar otro tipo de tripulante de cabina a vuelo");
            System.out.println("5. Ver tripulación asignada de un vuelo");
            System.out.println("6. Retirar tripulación de un vuelo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    try {
                        vuelo = asignarPiloto(vuelo);
                    } catch (LeerJsonException e) {
                        System.out.println(e.getMessage());
                    } catch (CapacidadExcedidaException e ) {
                        System.out.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 2:
                    try {
                        vuelo = asignarAzafata(vuelo);
                    } catch (LeerJsonException e) {
                        System.out.println(e.getMessage());
                    } catch (CapacidadExcedidaException e ) {
                        System.out.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 3:
                    try {
                        vuelo = asignarOtroTripulanteTecnico(vuelo);
                    } catch (LeerJsonException e) {
                        System.out.println(e.getMessage());
                    } catch (CapacidadExcedidaException e ) {
                        System.out.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 4:
                    try {
                        vuelo = asignarOtroTripulanteCabina(vuelo);
                    } catch (LeerJsonException e) {
                        System.out.println(e.getMessage());
                    } catch (CapacidadExcedidaException e ) {
                        System.out.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 5:
                    verTripulacionAsignada(vuelo);
                    pausarConTecla();
                    break;
                case 6:
                    vuelo = retirarTripulante(vuelo);
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 0);

        return vuelo;
    }

    public static Vuelo asignarPiloto(Vuelo vuelo) throws LeerJsonException, CapacidadExcedidaException {
        TripulacionTecnica pilotoAsignado = new TripulacionTecnica();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        try {
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        for (TripulacionTecnica tripulacionTecnica : tripTecnica) {
            if (tripulacionTecnica.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)
                    && !vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulacionTecnica)) {
                System.out.println("Legajo: " + tripulacionTecnica.getLegajo() + " - Nombre: " + tripulacionTecnica.getNombre());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo del piloto a asignar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("T%04d", numeroLegajo);

            for (TripulacionTecnica tripulante : tripTecnica) {
                if (tripulante.getLegajo().equals(legajo) && tripulante.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                    pilotoAsignado = tripulante;
                    flag = 1;
                    break;
                } else if (vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulante)){
                    System.out.println("Tripulante ya asignado a este vuelo");
                    flag = 0;
                } else {
                    System.out.println("Legajo no encontrado entre los pilotos, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        if (vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().size() < vuelo.getAvion().getCapacidadTripulanteTecnico()) {
            vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().add(pilotoAsignado);
        } else {
            throw new CapacidadExcedidaException("Tripulación Tecnica Completada. No se puede agregar más tripulantes.");
        }

        return vuelo;
    }

    public static Vuelo asignarAzafata(Vuelo vuelo) throws LeerJsonException, CapacidadExcedidaException{
        TripulacionCabina azafataAsignada = new TripulacionCabina();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();
        try {
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Cabina");
        }

        for (TripulacionCabina tripulacionCabina : tripCabina) {
            if (tripulacionCabina.getTipoPersonal().equals(TipoPersonalAereo.AZAFATA)
                    && !vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulacionCabina)) {
                System.out.println("Legajo: " + tripulacionCabina.getLegajo() + " - Nombre: " + tripulacionCabina.getNombre());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo de la azafata a asignar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("C%04d", numeroLegajo);

            for (TripulacionCabina tripulante : tripCabina) {
                if (tripulante.getLegajo().equals(legajo) && tripulante.getTipoPersonal().equals(TipoPersonalAereo.AZAFATA)) {
                    azafataAsignada = tripulante;
                    flag = 1;
                    break;
                } else if (vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulante)){
                    System.out.println("Tripulante ya asignado a este vuelo");
                    flag = 0;
                } else {
                    System.out.println("Legajo no encontrado entre las azafatas, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        if (vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().size() < vuelo.getAvion().getCapacidadTripulanteCabina()) {
            vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().add(azafataAsignada);
        } else {
            throw new CapacidadExcedidaException("Tripulación de Cabina Completada. No se puede agregar más tripulantes.");
        }

        return vuelo;
    }



    public static Vuelo asignarOtroTripulanteTecnico(Vuelo vuelo) throws LeerJsonException, CapacidadExcedidaException{
        TripulacionTecnica tripulanteAsignado = new TripulacionTecnica();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        try {
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        for (TripulacionTecnica tripulacionTecnica : tripTecnica) {
            if(tripulacionTecnica.getTipoPersonal().equals(TipoPersonalAereo.INGENIERO_DE_VUELO)
                    && !vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulacionTecnica)) {
                System.out.println("Legajo: " + tripulacionTecnica.getLegajo() + " - Nombre: " + tripulacionTecnica.getNombre() + " - Tipo: " + tripulacionTecnica.getTipoPersonal());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo del tripulante tecnico a asignar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("T%04d", numeroLegajo);

            for (TripulacionTecnica tripulante : tripTecnica) {
                if (tripulante.getLegajo().equals(legajo) && !vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulante)) {
                    tripulanteAsignado = tripulante;
                    flag = 1;
                    break;
                } else if (vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().contains(tripulante)){
                    System.out.println("Tripulante ya asignado a este vuelo");
                    flag = 0;
                } else {
                    System.out.println("Legajo no encontrado entre los tripulantes tecnicos, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        if (vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().size() < vuelo.getAvion().getCapacidadTripulanteTecnico()) {
            vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().add(tripulanteAsignado);
        } else {
            System.out.println("Tripulación Tecnica Completada. No se puede agregar más tripulantes.");
        }

        return vuelo;
    }

    public static Vuelo asignarOtroTripulanteCabina(Vuelo vuelo) throws LeerJsonException, CapacidadExcedidaException{
        TripulacionCabina tripulanteAsignado = new TripulacionCabina();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();
        try {
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Cabina");
        }

        for (TripulacionCabina tripulacionCabina : tripCabina) {
            if (!tripulacionCabina.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)
                    && !vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().contains(tripulacionCabina)) {
                System.out.println("Legajo: " + tripulacionCabina.getLegajo() + " - Nombre: " + tripulacionCabina.getNombre() + " - Tipo: " + tripulacionCabina.getTipoPersonal());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo del tripulante de cabina a asignar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("C%04d", numeroLegajo);

            for (TripulacionCabina tripulante : tripCabina) {
                if (tripulante.getLegajo().equals(legajo) && !vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().contains(tripulante)) {
                    tripulanteAsignado = tripulante;
                    flag = 1;
                    break;
                } else if (vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().contains(tripulante)) {
                    System.out.println("Tripulante ya asignado a este vuelo");
                    flag = 0;
                } else {
                    System.out.println("Legajo no encontrado entre los tripulantes de cabina, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        if (vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().size() < vuelo.getAvion().getCapacidadTripulanteCabina()) {
            vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().add(tripulanteAsignado);
        } else {
            System.out.println("Tripulación de Cabina Completada. No se puede agregar más tripulantes.");
        }

        return vuelo;
    }

    public static void verTripulacionAsignada(Vuelo vuelo) {
        System.out.println("Tripulación Técnica:");
        for (TripulacionTecnica tripulante : vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica()) {
            System.out.println("Legajo: " + tripulante.getLegajo() + " - Nombre: " + tripulante.getNombre() + " - Tipo: " + tripulante.getTipoPersonal());
        }

        System.out.println("Tripulación de Cabina:");
        for (TripulacionCabina tripulante : vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina()) {
            System.out.println("Legajo: " + tripulante.getLegajo() + " - Nombre: " + tripulante.getNombre() + " - Tipo: " + tripulante.getTipoPersonal());
        }
    }

    public static Vuelo retirarTripulante(Vuelo vuelo) {
        int opcion;
        do {
            System.out.println("\nMenú de Retiro de Tripulante:");
            System.out.println("1. Retirar piloto de vuelo");
            System.out.println("2. Retirar azafata de vuelo");
            System.out.println("3. Retirar otro tipo de tripulante tecnico de vuelo");
            System.out.println("4. Retirar otro tipo de tripulante de cabina de vuelo");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    retirarPiloto(vuelo);
                    pausarConTecla();
                    break;
                case 2:
                    retirarAzafata(vuelo);
                    pausarConTecla();
                    break;
                case 3:
                    retirarOtroTripulanteTecnico(vuelo);
                    pausarConTecla();
                    break;
                case 4:
                    retirarOtroTripulanteCabina(vuelo);
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);

        return vuelo;
    }

    public static Vuelo retirarPiloto(Vuelo vuelo) {
        TripulacionTecnica pilotoRetirado = new TripulacionTecnica();
        pilotoRetirado = null;
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        try {
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        for (TripulacionTecnica tripulacionTecnica : vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica()) {
            if (tripulacionTecnica.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                System.out.println("Legajo: " + tripulacionTecnica.getLegajo() + " - Nombre: " + tripulacionTecnica.getNombre());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo del piloto a retirar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("T%04d", numeroLegajo);

            for (TripulacionTecnica tripulante : tripTecnica) {
                if (tripulante.getLegajo().equals(legajo) && tripulante.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                    pilotoRetirado = tripulante;
                    flag = 1;
                    break;
                } else {
                    System.out.println("Legajo no encontrado entre los pilotos, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().remove(pilotoRetirado);

        return vuelo;
    }

    public static Vuelo retirarAzafata(Vuelo vuelo) {
        TripulacionCabina azafataRetirada = new TripulacionCabina();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();
        try {
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Cabina");
        }

        for (TripulacionCabina tripulacionCabina : vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina()) {
            if (tripulacionCabina.getTipoPersonal().equals(TipoPersonalAereo.AZAFATA)) {
                System.out.println("Legajo: " + tripulacionCabina.getLegajo() + " - Nombre: " + tripulacionCabina.getNombre());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo de la azafata a retirar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("C%04d", numeroLegajo);

            for (TripulacionCabina tripulante : tripCabina) {
                if (tripulante.getLegajo().equals(legajo) && tripulante.getTipoPersonal().equals(TipoPersonalAereo.AZAFATA)) {
                    azafataRetirada = tripulante;
                    flag = 1;
                    break;
                } else {
                    System.out.println("Legajo no encontrado entre las azafatas, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().remove(azafataRetirada);

        return vuelo;
    }

    public static Vuelo retirarOtroTripulanteTecnico(Vuelo vuelo) {
        TripulacionTecnica tripulanteRetirado = new TripulacionTecnica();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        try {
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        for (TripulacionTecnica tripulacionTecnica : vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica()) {
            if (!tripulacionTecnica.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                System.out.println("Legajo: " + tripulacionTecnica.getLegajo() + " - Nombre: " + tripulacionTecnica.getNombre() + " - Tipo: " + tripulacionTecnica.getTipoPersonal());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo del tripulante tecnico a retirar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("T%04d", numeroLegajo);

            for (TripulacionTecnica tripulante : tripTecnica) {
                if (tripulante.getLegajo().equals(legajo) && !tripulante.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                    tripulanteRetirado = tripulante;
                    flag = 1;
                    break;
                } else {
                    System.out.println("Legajo no encontrado entre los tripulantes tecnicos, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        vuelo.getRegistroDeVuelo().getRegistroTripulacionTecnica().remove(tripulanteRetirado);

        return vuelo;
    }

    public static Vuelo retirarOtroTripulanteCabina(Vuelo vuelo) {
        TripulacionCabina tripulanteRetirado = new TripulacionCabina();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();
        try {
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            System.out.println("Error al leer el archivo JSON Tripulacion Cabina");
        }

        for (TripulacionCabina tripulacionCabina : vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina()) {
            if (!tripulacionCabina.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                System.out.println("Legajo: " + tripulacionCabina.getLegajo() + " - Nombre: " + tripulacionCabina.getNombre() + " - Tipo: " + tripulacionCabina.getTipoPersonal());
            }
        }

        int flag = 0;
        do {
            System.out.println("Ingrese el número de legajo del tripulante de cabina a retirar: ");
            int numeroLegajo = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea
            String legajo = String.format("C%04d", numeroLegajo);

            for (TripulacionCabina tripulante : tripCabina) {
                if (tripulante.getLegajo().equals(legajo) && !tripulante.getTipoPersonal().equals(TipoPersonalAereo.PILOTO)) {
                    tripulanteRetirado = tripulante;
                    flag = 1;
                    break;
                } else {
                    System.out.println("Legajo no encontrado entre los tripulantes de cabina, intente nuevamente");
                    flag = 0;
                }
            }
        } while (flag == 0);

        vuelo.getRegistroDeVuelo().getRegistroTripulanteCabina().remove(tripulanteRetirado);

        return vuelo;
    }

}


