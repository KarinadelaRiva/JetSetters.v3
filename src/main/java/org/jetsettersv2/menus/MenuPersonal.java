package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.*;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;

import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuPersonal {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuPersonal(/*Administrador admin*/) {
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
                    registrarEmpleado(admins, tripCabina, tripTecnica);
                    pausarConTecla();
                    break;
                case 2:
                    System.out.println("\n--Ver Nomina de Empleados--");

                    pausarConTecla();
                    break;
                case 3:
                    System.out.println("\n--Modificar datos de un Empleado--");

                    pausarConTecla();
                    break;
                case 4:
                    System.out.println("\n--Baja de Empleado--");

                    pausarConTecla();
                    break;
                case 5:
                    System.out.println("\n--Designar nuevo administrador--");

                    pausarConTecla();
                    break;
                case 6:
                    System.out.println("\n--Quitar derechos administrador--");

                    pausarConTecla();
                    break;
                case 0:
                    System.out.println("\n--Volver--");

                    break;
                default:
                    System.out.println("Opcion no valida");
                    pausarConTecla();
                    break;
            }
        }while (opcion != 0);
    }


    public static int opcionesMenuPersonal() {
        System.out.println("1. Alta Nuevo Empleado");
        System.out.println("2. Ver Nomina de Empleados");
        System.out.println("3. Modificar datos de un Empleado");
        System.out.println("4. Baja de Empleado");
        System.out.println("5. Designar nuevo administrador");
        System.out.println("6. Quitar derechos administrador");
        System.out.println("0. Volver");
        System.out.print("\nSeleccione una opción: ");
        return scanner.nextInt();
    }

    public static void registrarEmpleado(ArrayListGeneric<Administrador> admins, ArrayListGeneric<TripulacionCabina> tCabina, ArrayListGeneric<TripulacionTecnica> tTecnica){

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
                    System.out.println("Volver");
                    break;
                default:
                    System.out.println("\npción inválida. Intente nuevamente.");
            }
        } while (opcion!=0);
    }

    public static void registrarNuevoAdmin(ArrayListGeneric<Administrador> admins) throws LeerJsonException {
        Administrador nuevoAdmin = new Administrador();
        int opcion = -1;

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
        int opcion = -1;

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
        int opcion = -1;

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
