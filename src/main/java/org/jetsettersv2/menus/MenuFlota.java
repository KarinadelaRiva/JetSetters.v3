package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.Avion;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuFlota {
    private static final Scanner scanner = new Scanner(System.in);

    public static void menuFlota() {
        int opcion;
        do {
            opcion = mostrarMenuFlota();
            switch (opcion) {
                case 1:
                    verFlota();
                    break;
                case 2:
                    agregarAvionAFlota();
                    break;
                case 3:
                    modificarAvion();
                    break;
                case 4:
                    eliminarAvion();
                    break;
                case 0:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    public static int mostrarMenuFlota() {
        int opcion;
        System.out.println("\nMenú de Gestión de Flota:");
        System.out.println("1. Ver flota");
        System.out.println("2. Agregar avión");
        System.out.println("3. Modificar datos de avión");
        System.out.println("4. Eliminar avión");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
        System.out.print("\nSeleccione una opción: ");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        return opcion;
    }

    public static void eliminarAvion() {
        ArrayListGeneric<Avion> flota = new ArrayListGeneric<>();
        try{
            flota.copiarLista(getJsonToList(PATH_RESOURCES + PATH_FLOTA, Avion.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON: " + e.getMessage());
        }

        String matricula;
        System.out.print("Ingrese la MATRÍCULA del Avión a eliminar: ");
        matricula = scanner.nextLine().trim(); // Consumir el salto de línea

        System.out.println("Buscando avión con la matrícula: " + matricula);

        int posAvion = -1;
        try {
            posAvion = buscarPosAvionElementoPorMatricula(matricula, flota);
            flota.get(posAvion).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posAvion != -1) {
            System.out.print("¿Está seguro que desea eliminar este avión? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();
            if (confirmacion.equals("s")) {
                flota.remove(posAvion);
                try {
                    writeListToJsonFile(flota, PATH_RESOURCES + PATH_FLOTA);
                    System.out.println("Avión eliminado con éxito.");
                } catch (Exception e) {
                    System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
                }
            } else {
                System.out.println("Operación cancelada.");
            }
        }
    }

    public static int buscarPosAvionElementoPorMatricula(String matricula, ArrayListGeneric<Avion> flota) throws ElementoNoEncontradoException {
        for (int i = 0; i < flota.size(); i++) {
            if (flota.get(i).getMatricula().equals(matricula)) {
                return i;  // Retorna la posición del elemento si coincide el legajo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un avión con la matrícula " + matricula);
    }

    public static void modificarAvion() {
        ArrayListGeneric<Avion> flota = new ArrayListGeneric<>();
        try{
            flota.copiarLista(getJsonToList(PATH_RESOURCES + PATH_FLOTA, Avion.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON: " + e.getMessage());
        }

        String matricula;
        System.out.print("Ingrese la MATRÍCULA del Avión a modificar: ");
        matricula = scanner.nextLine().trim(); // Consumir el salto de línea

        System.out.println("Buscando avión con la matrícula: " + matricula);

        int posAvion = -1;
        try {
            posAvion = buscarPosAvionElementoPorMatricula(matricula, flota);
            flota.get(posAvion).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posAvion != -1) {
            int opcion;
            do {
                System.out.println("\n      Modificar datos de un Avión\n");
                System.out.println("1. Modificar modelo");
                System.out.println("2. Modificar matrícula");
                System.out.println("3. Modificar capacidad de tripulante de cabina");
                System.out.println("4. Modificar capacidad de tripulante técnico");
                System.out.println("5. Modificar capacidad de pasajeros");
                System.out.println("6. Modificar estado de mantenimiento");
                System.out.println("8. Mostrar datos nuevamente");
                System.out.println("0. Guardar cambios y salir");
                System.out.print("\nSeleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese el nuevo modelo: ");
                        flota.get(posAvion).setModelo(scanner.nextLine().trim());
                        break;
                    case 2:
                        System.out.print("Ingrese la nueva matrícula: ");
                        flota.get(posAvion).setMatricula(scanner.nextLine().trim());
                        break;
                    case 3:
                        // Validación para capacidad de tripulante de cabina (número entero positivo)
                        int capacidadTripCabina;
                        do {
                            System.out.print("Ingrese la nueva capacidad de tripulante de cabina: ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Error: debe ingresar un número entero.");
                                scanner.next(); // Limpiar el buffer
                            }
                            capacidadTripCabina = scanner.nextInt();
                            if (capacidadTripCabina < 0) {
                                System.out.println("Error: la capacidad no puede ser negativa.");
                            }
                        } while (capacidadTripCabina < 0);
                        flota.get(posAvion).setCapacidadTripulanteCabina(capacidadTripCabina);
                        scanner.nextLine(); // Consumir salto de línea
                        break;
                    case 4:
                        // Validación para capacidad de tripulante técnico (número entero positivo)
                        int capacidadTripTecnico;
                        do {
                            System.out.print("Ingrese la nueva capacidad de tripulante técnico: ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Error: debe ingresar un número entero.");
                                scanner.next(); // Limpiar el buffer
                            }
                            capacidadTripTecnico = scanner.nextInt();
                            if (capacidadTripTecnico < 0) {
                                System.out.println("Error: la capacidad no puede ser negativa.");
                            }
                        } while (capacidadTripTecnico < 0);
                        flota.get(posAvion).setCapacidadTripulanteTecnico(capacidadTripTecnico);
                        scanner.nextLine(); // Consumir salto de línea
                        break;
                    case 5:
                        // Validación para capacidad de pasajeros (número entero positivo)
                        int capacidadPasajeros;
                        do {
                            System.out.print("Ingrese la nueva capacidad de pasajeros: ");
                            while (!scanner.hasNextInt()) {
                                System.out.println("Error: debe ingresar un número entero.");
                                scanner.next(); // Limpiar el buffer
                            }
                            capacidadPasajeros = scanner.nextInt();
                            if (capacidadPasajeros < 0) {
                                System.out.println("Error: la capacidad no puede ser negativa.");
                            }
                        } while (capacidadPasajeros < 0);
                        flota.get(posAvion).setCapacidadPasajeros(capacidadPasajeros);
                        scanner.nextLine(); // Consumir salto de línea
                        break;
                    case 7:
                        // Validación para estado de mantenimiento (solo "s" o "n")
                        String enMantenimientoInput;
                        do {
                            System.out.print("¿Está en mantenimiento? (s/n): ");
                            enMantenimientoInput = scanner.nextLine().trim().toLowerCase();
                            if (!enMantenimientoInput.equals("s") && !enMantenimientoInput.equals("n")) {
                                System.out.println("Error: debe ingresar 's' para sí o 'n' para no.");
                            }
                        } while (!enMantenimientoInput.equals("s") && !enMantenimientoInput.equals("n"));
                        if (enMantenimientoInput.equals("s")) {
                            flota.get(posAvion).setEnMantenimiento(true);
                            System.out.println("El avión está en mantenimiento.");
                        } else {
                            flota.get(posAvion).setEnMantenimiento(false);
                            System.out.println("El avión no está en mantenimiento.");
                        }
                        break;
                    case 8:
                        System.out.println("\n----Datos ingresados:----");
                        flota.get(posAvion).imprimir(); // Método que imprime los detalles del avión
                        break;
                    case 0:
                        System.out.println(" ");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } while (opcion != 0);

            try {
                // Guardar los cambios en el archivo JSON
                writeListToJsonFile(flota, PATH_RESOURCES + PATH_FLOTA);
                System.out.println("Modificación exitosa.");
            } catch (Exception e) {
                System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
            }
        }
    }

    public static void agregarAvionAFlota(){
        ArrayListGeneric<Avion> flota = new ArrayListGeneric<>();
        boolean entradaValida;
        int capacidadTripulantes = 0;
        int capacidadTripulanteTecnico = 0;
        int capacidadPasajeros = 0;
        String entrada;
        boolean enMantenimiento = false;

        try{
            flota.copiarLista(getJsonToList(PATH_RESOURCES + PATH_FLOTA, Avion.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON: " + e.getMessage());
        }

        Avion nuevoAvion = new Avion();

        System.out.println("Ingrese los datos del avión:");
        System.out.print("Modelo: ");
        nuevoAvion.setModelo(scanner.nextLine());
        System.out.print("Matrícula: ");
        nuevoAvion.setMatricula(scanner.nextLine());

        // Validar capacidad de tripulante de cabina
        do {
            entradaValida= false;
            try {

                System.out.print("Capacidad de tripulante de cabina: ");
                capacidadTripulantes = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea restante

                if (capacidadTripulantes < 0) {
                    System.out.println("La capacidad no puede ser un número negativo. Inténtelo nuevamente.");
                } else {
                    entradaValida = true; // La entrada es válida si no hay excepciones
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido. Inténtelo nuevamente.");
                scanner.nextLine(); // Consumir el texto no válido
            }
        } while (!entradaValida);
        nuevoAvion.setCapacidadTripulanteCabina(capacidadTripulantes);

        // Validar capacidad de tripulante técnico
        do {
            entradaValida = false;
            try {
                System.out.print("Capacidad de tripulante técnico: ");
                capacidadTripulanteTecnico = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea
                if (capacidadTripulanteTecnico < 0) {
                    System.out.println("La capacidad no puede ser un número negativo. Inténtelo nuevamente.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido. Inténtelo nuevamente.");
                scanner.nextLine(); // Consumir el texto no válido
            }
        } while (!entradaValida);

        nuevoAvion.setCapacidadTripulanteTecnico(capacidadTripulanteTecnico);

        // Validar capacidad de pasajeros
        do {
            entradaValida = false;
            try {
                System.out.print("Capacidad de pasajeros: ");
                capacidadPasajeros = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea
                if (capacidadPasajeros < 0) {
                    System.out.println("La capacidad no puede ser un número negativo. Inténtelo nuevamente.");
                } else {
                    entradaValida = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido. Inténtelo nuevamente.");
                scanner.nextLine(); // Consumir el texto no válido
            }
        } while (!entradaValida);

        nuevoAvion.setCapacidadPasajeros(capacidadPasajeros);

        do {
            entradaValida = true;
            System.out.print("¿Está en mantenimiento? (s/n): ");
            entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("s")) {
                enMantenimiento = true;
            } else if (entrada.equalsIgnoreCase("n")) {
                System.out.println(" ");
            } else {
                System.out.println("Entrada inválida. Debe ingresar 's' para sí o 'n' para no.");
                entradaValida = false;
            }
        } while (!entradaValida);
        nuevoAvion.setEnMantenimiento(enMantenimiento);

        flota.add(nuevoAvion);

        try{
            writeListToJsonFile(flota,PATH_RESOURCES + PATH_FLOTA);
            nuevoAvion.imprimir();
        } catch (Exception e) {
            throw new LeerJsonException("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static void verFlota() {
        ArrayListGeneric<Avion> flota = new ArrayListGeneric<>();

        try{
            flota.copiarLista(getJsonToList(PATH_RESOURCES + PATH_FLOTA, Avion.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON: " + e.getMessage());
        }

        System.out.println("Flota de aviones:");
        for (Avion avion : flota) {
            avion.imprimir();
        }
    }
}
