package org.jetsettersv2.menus;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.Administrador;
import org.jetsettersv2.collections.ArrayListGeneric;

import java.util.Scanner;

import static org.jetsettersv2.menus.MenuFlota.menuFlota;
import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.menus.MenuPersonal.buscarPosAdminElementoPorLegajo;
import static org.jetsettersv2.menus.MenuPersonal.menuPersonal;
import static org.jetsettersv2.menus.MenuRutas.menuRutas;
import static org.jetsettersv2.menus.MenuVuelo.mostrarMenuVuelo;
import static org.jetsettersv2.utilities.JacksonUtil.*;
import static org.jetsettersv2.utilities.Tipografias.*;

public class MenuAdmin {
    public static void mostrarMenuAdmin(Administrador admin) {
    Scanner scanner = new Scanner(System.in);
    int opcion;

        do {
        printTitulo("\nMenú Administrador:", 30);
        System.out.println("1. Ver y Gestionar Perfil");
        System.out.println("2. Gestionar Vuelos");
        System.out.println("3. Gestionar Rutas");
        System.out.println("4. Gestionar Flota (Aviones)");
        System.out.println("5. Gestionar Personal");
        System.out.println("0. Cerrar Sesión");
        printColoredTitle("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1 -> {
                gestionarPerfil(admin);
                pausarConTecla();
            }
            case 2 -> {
                mostrarMenuVuelo();
                pausarConTecla();
            }
            case 3 -> {
                menuRutas();
                pausarConTecla();
            }
            case 4 -> {
                menuFlota();
                pausarConTecla();
            }
            case 5 -> {
                menuPersonal();
                pausarConTecla();
            }
            case 0 -> System.out.println(" ");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    } while (opcion != 0);
}

private static void gestionarPerfil(Administrador admin) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nGestión de Perfil:" );
    String legajo = admin.getLegajo();

    ArrayListGeneric<Administrador> admins = new ArrayListGeneric<>();
    try{
        admins.copiarLista(getJsonToList(PATH_RESOURCES + PATH_ADMINISTRADORES, Administrador.class));
    } catch (Exception e) {
        throw new LeerJsonException("Error al leer el archivo JSON Administradores: " + e.getMessage());
    }

    int posAdmin = -1;
    try{
        posAdmin = buscarPosAdminElementoPorLegajo(legajo, admins);
        System.out.println("\n----Datos registrados:----");
        admins.get(posAdmin).imprimir();
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }

    if (posAdmin != -1) {
        int opcion;
        do {
            System.out.println("\n      Modificar tus de un Administrador\n");
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

}
