package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.*;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;

import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuPersonal {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuPersonal() {
        ArrayListGeneric<Administrador> admins = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();
        int opcion;
        do {
            System.out.println("\n      Menu administración de personal\n");
            opcion = opcionesMenuPersonal();
            switch (opcion) {
                case 1:
                    System.out.println("\n--Alta Nuevo Empleado--");
                    subMenuRegistrarEmpleado(admins, tripCabina, tripTecnica);
                    pausarConTecla();
                    break;
                case 2:
                    try {
                        subMenuVerEmpleados();
                    } catch (LeerJsonException e) {
                        System.err.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 3:
                    try {
                        subMenuModificarEmpleado();
                    } catch (LeerJsonException e) {
                        System.err.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 4:
                    try {
                        subMenuBajaEmpleado();
                    } catch (LeerJsonException e) {
                        System.err.println(e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println(" ");

                    break;
                default:
                    System.out.println("Opcion no valida");
                    pausarConTecla();
                    break;
            }
        }while (opcion != 0);
    }

    public static int opcionesMenuPersonal(){
        int opcion;
        System.out.println("1. Alta Nuevo Empleado");
        System.out.println("2. Ver Nomina de Empleados");
        System.out.println("3. Modificar datos de un Empleado");
        System.out.println("4. Dar de baja/ recuperar un Empleado");
        System.out.println("0. Volver");
        System.out.print("\nSeleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        return opcion;
    }

    public static void subMenuRegistrarEmpleado(ArrayListGeneric<Administrador> admins, ArrayListGeneric<TripulacionCabina> tCabina, ArrayListGeneric<TripulacionTecnica> tTecnica){

        int opcion;
        do {
            System.out.println("\n      Registro de Empleado\n");
            System.out.println("1. Registrar Administrador");
            System.out.println("2. Registrar Tripulante de Cabina");
            System.out.println("3. Registrar Tripulante Técnico");
            System.out.println("0. Volver");
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    try {
                        registrarNuevoAdmin(admins);
                    }catch (LeerJsonException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        registrarNuevoTCabina(tCabina);
                    }catch (LeerJsonException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        registrarNuevoTTecnica(tTecnica);
                    }catch (LeerJsonException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("\npción inválida. Intente nuevamente.");
            }
        } while (opcion!=0);
    }

    public static void subMenuVerEmpleados() throws LeerJsonException{
        ArrayListGeneric<Administrador> admins = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();

        try{
            admins.copiarLista(getJsonToList(PATH_RESOURCES + PATH_ADMINISTRADORES, Administrador.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores: " + e.getMessage());
        }

        try{
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        try{
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Cabina");
        }

        int opcion;
        do {
            System.out.println("\n      Ver Nomina de Empleados\n");
            System.out.println("1. Ver solo empleados activos");
            System.out.println("2. Ver todos los empleados");
            System.out.println("0. Volver");
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("\n--Ver solo empleados activos--");
                    mostrarEmpleadosActivos(admins, tripCabina, tripTecnica);
                    pausarConTecla();
                    break;
                case 2:
                    System.out.println("\n--Ver todos los empleados--");
                    mostrarTodosLosEmpleados(admins, tripCabina, tripTecnica);
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println("  ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    pausarConTecla();
                    break;
            }
        }while (opcion != 0);
    }

    public static void subMenuModificarEmpleado() throws LeerJsonException{
        ArrayListGeneric<Administrador> admins = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();

        try{
            admins.copiarLista(getJsonToList(PATH_RESOURCES + PATH_ADMINISTRADORES, Administrador.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores: " + e.getMessage());
        }

        try{
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        try{
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Cabina");
        }

        int opcion;
        do {
            System.out.println("\n      Modificar datos de un Empleado\n");
            System.out.println("1. Modificar datos de un Administrador");
            System.out.println("2. Modificar datos de un Tripulante de Cabina");
            System.out.println("3. Modificar datos de un Tripulante Técnico");
            System.out.println("0. Volver");
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("\n--Modificar datos de un Administrador--");
                    modificarAdmin(admins);
                    pausarConTecla();
                    break;
                case 2:
                    System.out.println("\n--Modificar datos de un Tripulante de Cabina--");
                    modificarTripulanteCabina(tripCabina);
                    pausarConTecla();
                    break;
                case 3:
                    System.out.println("\n--Modificar datos de un Tripulante Técnico--");
                    modificarTripulanteTecnico(tripTecnica);
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println("  ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    pausarConTecla();
                    break;
            }
        }while (opcion != 0);
    }

    public static void subMenuBajaEmpleado() throws LeerJsonException{
        ArrayListGeneric<Administrador> admins = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionTecnica> tripTecnica = new ArrayListGeneric<>();
        ArrayListGeneric<TripulacionCabina> tripCabina = new ArrayListGeneric<>();

        try{
            admins.copiarLista(getJsonToList(PATH_RESOURCES + PATH_ADMINISTRADORES, Administrador.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores: " + e.getMessage());
        }

        try{
            tripTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Tecnica");
        }

        try{
            tripCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Tripulacion Cabina");
        }

        int opcion;
        do {
            System.out.println("\n      Baja de Empleado\n");
            System.out.println("1. Dar de baja/recuperar a un Administrador");
            System.out.println("2. Dar de baja/recuperar a un Tripulante de Cabina");
            System.out.println("3. Dar de baja/recuperar a un Tripulante Técnico");
            System.out.println("0. Volver");
            System.out.print("\nSeleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("\n--Dar de baja a un Administrador--");
                    bajaAdmin(admins);
                    pausarConTecla();
                    break;
                case 2:
                    System.out.println("\n--Dar de baja a un Tripulante de Cabina--");
                    bajaTripulanteCabina(tripCabina);
                    pausarConTecla();
                    break;
                case 3:
                    System.out.println("\n--Dar de baja a un Tripulante Técnico--");
                    bajaTripulanteTecnico(tripTecnica);
                    pausarConTecla();
                    break;
                case 0:
                    System.out.println("  ");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    pausarConTecla();
                    break;
            }
        }while (opcion != 0);
    }

    public static void bajaAdmin(ArrayListGeneric<Administrador> admins) {
        int numeroLegajo;
        System.out.print("Ingrese el NUMERO de legajo del Administrador: ");
        numeroLegajo = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String legajo = String.format("A%04d", numeroLegajo);

        // Validación para evitar que se dé de baja el administrador principal con legajo A0001
        if (legajo.equals("A0001")) {
            System.out.println("No se puede dar de baja al administrador principal con legajo A0001.");
            return;
        }

        int posAdmin = -1;
        try {
            posAdmin = buscarPosAdminElementoPorLegajo(legajo, admins);
            admins.get(posAdmin).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posAdmin != -1) {
            int opcion;
            do {
                System.out.println("\n      Opciones para el Administrador\n");
                System.out.println("1. Dar de baja empleado activo");
                System.out.println("2. Recuperar empleado inactivo");
                System.out.println("0. Volver");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        if (!admins.get(posAdmin).isActivo()) {
                            System.out.println("El administrador ya está inactivo.");
                        } else {
                            admins.get(posAdmin).activo(false);
                            admins.get(posAdmin).fechaBaja(new Fecha());
                            try {
                                writeListToJsonFile(admins, PATH_RESOURCES + PATH_ADMINISTRADORES);
                                System.out.println("Baja exitosa.");
                            } catch (Exception e) {
                                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        if (admins.get(posAdmin).isActivo()) {
                            System.out.println("El administrador ya está activo.");
                        } else {
                            admins.get(posAdmin).activo(true);
                            admins.get(posAdmin).fechaBaja(null);
                            try {
                                writeListToJsonFile(admins, PATH_RESOURCES + PATH_ADMINISTRADORES);
                                System.out.println("Recuperación exitosa. El administrador ahora está activo.");
                            } catch (Exception e) {
                                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                            }
                        }
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("\nOpción no válida.");
                        break;
                }
            } while (opcion != 0);
        }
    }

    public static void bajaTripulanteCabina(ArrayListGeneric<TripulacionCabina> tCabina) {
        int numeroLegajo;
        System.out.print("Ingrese el NUMERO de legajo del Tripulante de Cabina: ");
        numeroLegajo = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String legajo = String.format("C%04d", numeroLegajo);

        int posTC= -1;
        try {
            posTC = buscarPosTCabinaElementoPorLegajo(legajo, tCabina);
            tCabina.get(posTC).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posTC != -1) {
            int opcion;
            do {
                System.out.println("\n      Opciones para el Tripulante de Cabina\n");
                System.out.println("1. Dar de baja empleado activo");
                System.out.println("2. Recuperar empleado inactivo");
                System.out.println("0. Volver");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        if (!tCabina.get(posTC).isActivo()) {
                            System.out.println("El Tripulante de Cabina ya está inactivo.");
                        } else {
                            tCabina.get(posTC).activo(false);
                            tCabina.get(posTC).fechaBaja(new Fecha());
                            try {
                                writeListToJsonFile(tCabina, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
                                System.out.println("Baja exitosa.");
                            } catch (Exception e) {
                                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        if (tCabina.get(posTC).isActivo()) {
                            System.out.println("El Tripulante de Cabina ya está activo.");
                        } else {
                            tCabina.get(posTC).activo(true);
                            tCabina.get(posTC).fechaBaja(null);
                            try {
                                writeListToJsonFile(tCabina, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
                                System.out.println("Recuperación exitosa. El Tripulante de Cabina ahora está activo.");
                            } catch (Exception e) {
                                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                            }
                        }
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("\nOpción no válida.");
                        break;
                }
            } while (opcion != 0);
        }


    }

    public static void bajaTripulanteTecnico(ArrayListGeneric<TripulacionTecnica> tTecnico) {
        int numeroLegajo;
        System.out.print("Ingrese el NUMERO de legajo del Tripulante Tecnico: ");
        numeroLegajo = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String legajo = String.format("T%04d", numeroLegajo);

        int posTT= -1;
        try {
            posTT = buscarPosTTecnicoElementoPorLegajo(legajo, tTecnico);
            tTecnico.get(posTT).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posTT != -1) {
            int opcion;
            do {
                System.out.println("\n      Opciones para el Tripulante de Cabina\n");
                System.out.println("1. Dar de baja empleado activo");
                System.out.println("2. Recuperar empleado inactivo");
                System.out.println("0. Volver");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        if (!tTecnico.get(posTT).isActivo()) {
                            System.out.println("El Tripulante de Cabina ya está inactivo.");
                        } else {
                            tTecnico.get(posTT).activo(false);
                            tTecnico.get(posTT).fechaBaja(new Fecha());
                            try {
                                writeListToJsonFile(tTecnico, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
                                System.out.println("Baja exitosa.");
                            } catch (Exception e) {
                                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                            }
                        }
                        break;
                    case 2:
                        if (tTecnico.get(posTT).isActivo()) {
                            System.out.println("El Tripulante de Cabina ya está activo.");
                        } else {
                            tTecnico.get(posTT).activo(true);
                            tTecnico.get(posTT).fechaBaja(null);
                            try {
                                writeListToJsonFile(tTecnico, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
                                System.out.println("Recuperación exitosa. El Tripulante de Cabina ahora está activo.");
                            } catch (Exception e) {
                                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                            }
                        }
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("\nOpción no válida.");
                        break;
                }
            } while (opcion != 0);
        }


    }

    public static int buscarPosAdminElementoPorLegajo(String legajo, ArrayListGeneric<Administrador> admins) throws ElementoNoEncontradoException {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getLegajo().equals(legajo)) {
                return i;  // Retorna la posición del elemento si coincide el legajo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un Administrador con el legajo: " + legajo);
    }

    public static void modificarAdmin(ArrayListGeneric<Administrador> admins){
        int numeroLegajo;
        System.out.println("Ingrese el NUMERO de legajo del Administrador a modificar: ");
        numeroLegajo = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String legajo = String.format("A%04d", numeroLegajo);
        System.out.println("Buscando administrador con el legajo: " + legajo);

        int posAdmin = -1;
        try{
            posAdmin = buscarPosAdminElementoPorLegajo(legajo, admins);
            admins.get(posAdmin).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posAdmin != -1) {
            int opcion;
            do {
                System.out.println("\n      Modificar datos de un Administrador\n");
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar apellido");
                System.out.println("3. Modificar DNI");
                System.out.println("4. Modificar pasaporte");
                System.out.println("5. Modificar teléfono");
                System.out.println("6. Modificar dirección");
                System.out.println("7. Modificar Email");
                System.out.println("8. Modificar Contraseña");
                System.out.println("9. Mostrar datos nuevamente");
                System.out.println("0. Guardar cambios y salir");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        admins.get(posAdmin).solicitarNombre();
                        break;
                    case 2:
                        admins.get(posAdmin).solicitarApellido();
                        break;
                    case 3:
                        admins.get(posAdmin).solicitarDni();
                        break;
                    case 4:
                        admins.get(posAdmin).solicitarPasaporte();
                        break;
                    case 5:
                        admins.get(posAdmin).solicitarTelefono();
                        break;
                    case 6:
                        admins.get(posAdmin).solicitarDireccion();
                        break;
                    case 7:
                        admins.get(posAdmin).solicitarEmail();
                        break;
                    case 8:
                        admins.get(posAdmin).solicitarPassword();
                        break;
                    case 9:
                        System.out.println("\n----Datos ingresados:----");
                        admins.get(posAdmin).imprimir();
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } while (opcion != 0);

            try {
                writeListToJsonFile(admins, PATH_RESOURCES + PATH_ADMINISTRADORES);
                System.out.println("Modificación exitosa.");
            } catch (Exception e) {
                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
            }

        }

    }

    public static int buscarPosTCabinaElementoPorLegajo(String legajo, ArrayListGeneric<TripulacionCabina> admins) throws ElementoNoEncontradoException {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getLegajo().equals(legajo)) {
                return i;  // Retorna la posición del elemento si coincide el legajo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un empleado de Tripulación de Cabina con el legajo: " + legajo);
    }

    public static void modificarTripulanteCabina(ArrayListGeneric<TripulacionCabina> tCabina){
        int numeroLegajo;
        System.out.print("Ingrese el NUMERO de legajo del Empleado de Tripulación de Cabina a modificar: ");
        numeroLegajo = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String legajo = String.format("C%04d", numeroLegajo);
        System.out.println("Buscando Tripulante de Cabina con el legajo: " + legajo);

        int posTC = -1;
        try{
            posTC = buscarPosTCabinaElementoPorLegajo(legajo, tCabina);
            tCabina.get(posTC).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posTC != -1) {
            int opcion;
            do {
                System.out.println("\n      Modificar datos de un Tripulante de Cabina\n");
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar apellido");
                System.out.println("3. Modificar DNI");
                System.out.println("4. Modificar pasaporte");
                System.out.println("5. Modificar teléfono");
                System.out.println("6. Modificar dirección");
                System.out.println("7. Modificar Email");
                System.out.println("8. Modificar Contraseña");
                System.out.println("9. Mostrar datos nuevamente");
                System.out.println("0. Guardar cambios y salir");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        tCabina.get(posTC).solicitarNombre();
                        break;
                    case 2:
                        tCabina.get(posTC).solicitarApellido();
                        break;
                    case 3:
                        tCabina.get(posTC).solicitarDni();
                        break;
                    case 4:
                        tCabina.get(posTC).solicitarPasaporte();
                        break;
                    case 5:
                        tCabina.get(posTC).solicitarTelefono();
                        break;
                    case 6:
                        tCabina.get(posTC).solicitarDireccion();
                        break;
                    case 7:
                        tCabina.get(posTC).solicitarEmail();
                        break;
                    case 8:
                        tCabina.get(posTC).solicitarPassword();
                        break;
                    case 9:
                        System.out.println("\n----Datos ingresados:----");
                        tCabina.get(posTC).imprimir();
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } while (opcion != 0);

            try {
                writeListToJsonFile(tCabina, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
                System.out.println("Modificación exitosa.");
            } catch (Exception e) {
                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
            }

        }

    }

    public static int buscarPosTTecnicoElementoPorLegajo(String legajo, ArrayListGeneric<TripulacionTecnica> admins) throws ElementoNoEncontradoException {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getLegajo().equals(legajo)) {
                return i;  // Retorna la posición del elemento si coincide el legajo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un Empleado de Tripulación Técnica con el legajo: " + legajo);
    }

    public static void modificarTripulanteTecnico(ArrayListGeneric<TripulacionTecnica> tTecnica){
        int numeroLegajo;
        System.out.print("Ingrese el NUMERO de legajo del Empleado de Tripulación Técnica a modificar: ");
        numeroLegajo = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        String legajo = String.format("T%04d", numeroLegajo);
        System.out.println("Buscando Tripulante Técnico con el legajo: " + legajo);

        int posTT = -1;
        try{
            posTT = buscarPosTTecnicoElementoPorLegajo(legajo, tTecnica);
            tTecnica.get(posTT).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posTT != -1) {
            int opcion;
            do {
                System.out.println("\n      Modificar datos de un Tripulante Tecnico\n");
                System.out.println("1. Modificar nombre");
                System.out.println("2. Modificar apellido");
                System.out.println("3. Modificar DNI");
                System.out.println("4. Modificar pasaporte");
                System.out.println("5. Modificar teléfono");
                System.out.println("6. Modificar dirección");
                System.out.println("7. Modificar Email");
                System.out.println("8. Modificar Contraseña");
                System.out.println("9. Mostrar datos nuevamente");
                System.out.println("0. Guardar cambios y salir");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        tTecnica.get(posTT).solicitarNombre();
                        break;
                    case 2:
                        tTecnica.get(posTT).solicitarApellido();
                        break;
                    case 3:
                        tTecnica.get(posTT).solicitarDni();
                        break;
                    case 4:
                        tTecnica.get(posTT).solicitarPasaporte();
                        break;
                    case 5:
                        tTecnica.get(posTT).solicitarTelefono();
                        break;
                    case 6:
                        tTecnica.get(posTT).solicitarDireccion();
                        break;
                    case 7:
                        tTecnica.get(posTT).solicitarEmail();
                        break;
                    case 8:
                        tTecnica.get(posTT).solicitarPassword();
                        break;
                    case 9:
                        System.out.println("\n----Datos ingresados:----");
                        tTecnica.get(posTT).imprimir();
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            } while (opcion != 0);

            try {
                writeListToJsonFile(tTecnica, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
                System.out.println("Modificación exitosa.");
            } catch (Exception e) {
                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
            }

        }

    }

    public static void mostrarTodosLosEmpleados( ArrayListGeneric<Administrador> admins, ArrayListGeneric<TripulacionCabina> tripCabina, ArrayListGeneric<TripulacionTecnica> tripTecnica){

        System.out.println("\n       --ADMINISTRADORES--\n");
        for (Administrador admin : admins) {
            admin.imprimir();
            System.out.println("-------------------*-------------------");
        }

        System.out.println("\n    --TRIPULANTES DE CABINA--\n");
        for (TripulacionCabina tc : tripCabina) {
            tc.imprimir();
            System.out.println("-------------------*-------------------");
        }

        System.out.println("\n     --TRIPULANTES TECNICOS--\n");
        for (TripulacionTecnica tt : tripTecnica) {
            tt.imprimir();
            System.out.println("-------------------*-------------------");
        }

    }

    public static void mostrarEmpleadosActivos(ArrayListGeneric<Administrador> admins, ArrayListGeneric<TripulacionCabina> tripCabina, ArrayListGeneric<TripulacionTecnica> tripTecnica){

        System.out.println("\n       --ADMINISTRADORES--\n");
        for (Administrador admin : admins) {
            if (admin.isActivo()) {
                admin.imprimir();
                System.out.println("-------------------*-------------------");
            }
        }

        System.out.println("\n    --TRIPULANTES DE CABINA--\n");
        for (TripulacionCabina tc : tripCabina) {
            if (tc.isActivo()) {
                tc.imprimir();
                System.out.println("-------------------*-------------------");
            }
        }

        System.out.println("\n     --TRIPULANTES TECNICOS--\n");
        for (TripulacionTecnica tt : tripTecnica) {
            if (tt.isActivo()) {
                tt.imprimir();
                System.out.println("-------------------*-------------------");
            }
        }
    }

    public static void registrarNuevoAdmin(ArrayListGeneric<Administrador> admins) throws LeerJsonException {
        Administrador nuevoAdmin = new Administrador();
        int opcion;

        try{
            admins.copiarLista(getJsonToList(PATH_RESOURCES + PATH_ADMINISTRADORES, Administrador.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores");
        }

        System.out.println("\n----Registro de Empleado----\n");

        nuevoAdmin.asignarLegajoAdmin(admins);
        nuevoAdmin.solicitarNombre();
        nuevoAdmin.solicitarApellido();
        nuevoAdmin.solicitarDni();
        nuevoAdmin.solicitarPasaporte();
        nuevoAdmin.solicitarTelefono();
        nuevoAdmin.solicitarDireccion();
        nuevoAdmin.solicitarEmail();
        nuevoAdmin.solicitarPassword();
        nuevoAdmin.fechaAlta(Fecha.fechaActual());
        nuevoAdmin.activo(true);


        System.out.println("\n----Datos ingresados:----");
        nuevoAdmin.imprimir();

        do{
            System.out.println("\nMenú Modificar Datos:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar DNI");
            System.out.println("4. Modificar pasaporte");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Modificar dirección");
            System.out.println("7. Modificar Email");
            System.out.println("8. Modificar Contraseña");
            System.out.println("9. Mostrar datos nuevamente");
            System.out.println("0. Confirmar y Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> nuevoAdmin.solicitarNombre();
                case 2 -> nuevoAdmin.solicitarApellido();
                case 3 -> nuevoAdmin.solicitarDni();
                case 4 -> nuevoAdmin.solicitarPasaporte();
                case 5 -> nuevoAdmin.solicitarTelefono();
                case 6 -> nuevoAdmin.solicitarDireccion();
                case 7 -> nuevoAdmin.solicitarEmail();
                case 8 -> nuevoAdmin.solicitarPassword();
                case 9 -> {
                    System.out.println("\n----Datos ingresados:----");
                    nuevoAdmin.imprimir();
                }
                case 0 -> System.out.println(" ");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        } while (opcion!=0);

        admins.add(nuevoAdmin);

        try{
            writeListToJsonFile(admins, PATH_RESOURCES + PATH_ADMINISTRADORES);
            System.out.println("Registro completado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static void registrarNuevoTCabina(ArrayListGeneric<TripulacionCabina> tCabina) throws LeerJsonException {
        TripulacionCabina nuevoTCabina = new TripulacionCabina();
        int opcion;

        try{
            tCabina.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONCABINA, TripulacionCabina.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores");
        }

        System.out.println("\n----Registro de Empleado----\n");

        nuevoTCabina.asignarLegajoTCabina(tCabina);
        nuevoTCabina.solicitarNombre();
        nuevoTCabina.solicitarApellido();
        nuevoTCabina.solicitarDni();
        nuevoTCabina.solicitarPasaporte();
        nuevoTCabina.solicitarTelefono();
        nuevoTCabina.solicitarDireccion();
        nuevoTCabina.solicitarEmail();
        nuevoTCabina.solicitarPassword();
        nuevoTCabina.fechaAlta(Fecha.fechaActual());
        nuevoTCabina.activo(true);


        System.out.println("\n----Datos ingresados:----");
        nuevoTCabina.imprimir();

        do{
            System.out.println("\nMenú Modificar Datos:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar DNI");
            System.out.println("4. Modificar pasaporte");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Modificar dirección");
            System.out.println("7. Modificar Email");
            System.out.println("8. Modificar Contraseña");
            System.out.println("9. Mostrar datos nuevamente");
            System.out.println("0. Confirmar y Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> nuevoTCabina.solicitarNombre();
                case 2 -> nuevoTCabina.solicitarApellido();
                case 3 -> nuevoTCabina.solicitarDni();
                case 4 -> nuevoTCabina.solicitarPasaporte();
                case 5 -> nuevoTCabina.solicitarTelefono();
                case 6 -> nuevoTCabina.solicitarDireccion();
                case 7 -> nuevoTCabina.solicitarEmail();
                case 8 -> nuevoTCabina.solicitarPassword();
                case 9 -> {
                    System.out.println("\n----Datos ingresados:----");
                    nuevoTCabina.imprimir();
                }
                case 0 -> System.out.println(" ");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        } while (opcion!=0);

        tCabina.add(nuevoTCabina);

        try{
            writeListToJsonFile(tCabina, PATH_RESOURCES + PATH_TRIPULACIONCABINA);
            System.out.println("Registro completado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static void registrarNuevoTTecnica(ArrayListGeneric<TripulacionTecnica> tTecnica) throws LeerJsonException {
        TripulacionTecnica nuevoTTecnica = new TripulacionTecnica();
        int opcion;

        try{
            tTecnica.copiarLista(getJsonToList(PATH_RESOURCES + PATH_TRIPULACIONTECNICA, TripulacionTecnica.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Administradores");
        }

        System.out.println("\n----Registro de Empleado----\n");

        nuevoTTecnica.asignarLegajoTTecnica(tTecnica);
        nuevoTTecnica.solicitarNombre();
        nuevoTTecnica.solicitarApellido();
        nuevoTTecnica.solicitarDni();
        nuevoTTecnica.solicitarPasaporte();
        nuevoTTecnica.solicitarTelefono();
        nuevoTTecnica.solicitarDireccion();
        nuevoTTecnica.solicitarEmail();
        nuevoTTecnica.solicitarPassword();
        nuevoTTecnica.fechaAlta(Fecha.fechaActual());
        nuevoTTecnica.activo(true);


        System.out.println("\n----Datos ingresados:----");
        nuevoTTecnica.imprimir();

        do{
            System.out.println("\nMenú Modificar Datos:");
            System.out.println("1. Modificar nombre");
            System.out.println("2. Modificar apellido");
            System.out.println("3. Modificar DNI");
            System.out.println("4. Modificar pasaporte");
            System.out.println("5. Modificar teléfono");
            System.out.println("6. Modificar dirección");
            System.out.println("7. Modificar Email");
            System.out.println("8. Modificar Contraseña");
            System.out.println("9. Mostrar datos nuevamente");
            System.out.println("0. Confirmar y Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> nuevoTTecnica.solicitarNombre();
                case 2 -> nuevoTTecnica.solicitarApellido();
                case 3 -> nuevoTTecnica.solicitarDni();
                case 4 -> nuevoTTecnica.solicitarPasaporte();
                case 5 -> nuevoTTecnica.solicitarTelefono();
                case 6 -> nuevoTTecnica.solicitarDireccion();
                case 7 -> nuevoTTecnica.solicitarEmail();
                case 8 -> nuevoTTecnica.solicitarPassword();
                case 9 -> {
                    System.out.println("\n----Datos ingresados:----");
                    nuevoTTecnica.imprimir();
                }
                case 0 -> System.out.println(" ");
                default -> System.out.println("\nOpción inválida. Intente nuevamente.");
            }
        } while (opcion!=0);

        tTecnica.add(nuevoTTecnica);

        try{
            writeListToJsonFile(tTecnica, PATH_RESOURCES + PATH_TRIPULACIONTECNICA);
            System.out.println("Registro completado exitosamente.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }


}
