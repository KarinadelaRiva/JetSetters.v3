package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.models.concrete.*;

import java.util.Scanner;

import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.menus.menuUsuario.mostrarMenuUsuario;
import static org.jetsettersv2.utilities.JacksonUtil.*;
import static org.jetsettersv2.utilities.JacksonUtil.PATH_USUARIOSCLIENTE;

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


}
