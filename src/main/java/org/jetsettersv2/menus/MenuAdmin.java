package org.jetsettersv2.menus;
import org.jetsettersv2.models.concrete.Ruta;
import org.jetsettersv2.collections.ArrayListGeneric;

import java.util.Scanner;

public class MenuAdmin {
    public static void MenuAdmin() {

    ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();
    Scanner scanner = new Scanner(System.in);
    int opcion;

        do {
        System.out.println("\nMenú Administrador:");
        System.out.println("1. Gestionar Perfil");
        System.out.println("2. Gestionar Vuelos");
        System.out.println("3. Gestionar Rutas");
        System.out.println("4. Gestionar Flota (Aviones)");
        System.out.println("5. Gestionar Personal");
        System.out.println("6. Cerrar Sesión");
        System.out.print("Seleccione una opción: ");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1 -> gestionarPerfil();
            case 2 -> gestionarVuelos();
            case 3 -> MenuRutas.menuRutas(rutas);
           // case 4 -> gestionarFlota();
          //  case 5 -> gestionarPersonal();
            case 6 -> System.out.println("Sesión cerrada. Volviendo al menú principal...");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    } while (opcion != 6);

        scanner.close();
}

private static void gestionarPerfil() {
    System.out.println("\nGestión de Perfil:");
    // Aquí puedes agregar opciones para ver o modificar el perfil
}

private static void gestionarVuelos() {
    Scanner scanner = new Scanner(System.in);
    int opcionVuelos;

    do {
        System.out.println("\nGestión de Vuelos:");
        System.out.println("1. Programar vuelo nuevo");
        System.out.println("2. Reprogramar vuelo existente");
        System.out.println("3. Asignar tripulación");
        System.out.println("4. Ver vuelos -> Ver todo");
        System.out.println("5. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        opcionVuelos = scanner.nextInt();

        switch (opcionVuelos) {
            case 1 -> System.out.println("Programando vuelo nuevo...");
            case 2 -> System.out.println("Reprogramando vuelo existente...");
            case 3 -> System.out.println("Asignando tripulación...");
            case 4 -> System.out.println("Mostrando todos los vuelos...");
            case 5 -> System.out.println("Volviendo al menú anterior...");
            default -> System.out.println("Opción inválida. Intente nuevamente.");
        }
    } while (opcionVuelos != 5);
}







}
