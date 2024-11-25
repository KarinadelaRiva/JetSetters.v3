package org.jetsettersv2.menus;
import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.ElementoNoEncontradoException;
import org.jetsettersv2.exceptions.LeerJsonException;
import org.jetsettersv2.models.concrete.*;
import org.jetsettersv2.utilities.Fecha;
import org.jetsettersv2.utilities.Hora;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


import static org.jetsettersv2.menus.MenuLogin.pausarConTecla;
import static org.jetsettersv2.menus.SubMenuAsignarTrip.gestionarTripulacion;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuVuelo {

    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuVuelo() {
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\nMenú de Gestión de Vuelos:");
            System.out.println("1. Programar vuelo nuevo");
            System.out.println("2. Reprogramar vuelo existente");
            System.out.println("3. Asignar tripulación a vuelo");
            System.out.println("4. Ver vuelos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    try {
                        programarVueloNuevo();
                    } catch (LeerJsonException e) {
                        System.err.println("Error al programar el vuelo: " + e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 2:
                    try {
                        subMenuModificarVuelo();
                    } catch (LeerJsonException e) {
                        System.err.println("Error al reprogramar el vuelo: " + e.getMessage());
                    }
                    pausarConTecla();
                    break;
                case 3:
                    gestionarTripulacion();
                    pausarConTecla();
                    break;
                case 4:
                    mostrarvuelos();
                    pausarConTecla();
                    break;
                case 5:
                    System.out.println(" ");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 5);

    }

    public static void programarVueloNuevo() throws LeerJsonException {
        Scanner scanner = new Scanner(System.in);
        ArrayListGeneric<Vuelo> vuelos = new ArrayListGeneric<>();
        ArrayListGeneric<Avion> aviones = new ArrayListGeneric<>();
        ArrayListGeneric<Ruta> rutas = new ArrayListGeneric<>();
        Vuelo nuevoVuelo = new Vuelo();

        try {
            vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

        try {
            aviones.copiarLista(getJsonToList(PATH_RESOURCES + PATH_FLOTA, Avion.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

        try {
            rutas.copiarLista(getJsonToList(PATH_RESOURCES + PATH_RUTAS, Ruta.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

        System.out.println("--- Programar Vuelo Nuevo ---");
        try {

            System.out.print("Ingrese el número de vuelo: ");
            String nroVuelo = scanner.nextLine();
            nuevoVuelo.setNroVuelo(nroVuelo);

            boolean flag = true;

            do {
                System.out.print("Ingrese la matrícula del avión: ");
                String matriculaAvion = scanner.nextLine();
                flag = true; // Reinicia la bandera

                boolean encontrado = false;
                for (Avion avion : aviones) {
                    if (avion.getMatricula().equals(matriculaAvion)) {
                        nuevoVuelo.setAvion(avion);
                        encontrado = true;
                        flag = false; // Salir del bucle si se encuentra el avión
                        break;
                    }
                }

                if (!encontrado) {
                    System.out.println("Matrícula inválida. Intente de nuevo.");
                }

            } while (flag);

            boolean flag2;
            do {
                System.out.print("Ingrese la ruta del avión (ID de la ruta): ");
                if (scanner.hasNextLine()) {
                    String idRuta = scanner.nextLine();
                    flag2 = true; // Reinicia la bandera

                    boolean encontrado = false;
                    for (Ruta ruta : rutas) {
                        if (ruta.getIdRuta().equals(idRuta)) {
                            nuevoVuelo.setRuta(ruta);
                            encontrado = true;
                            flag2 = false; // Salir del bucle si se encuentra la ruta
                            break;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("La ruta es inválida. Intente de nuevo.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    scanner.next(); // Limpia la entrada inválida
                    flag2 = true;
                }
            } while (flag2);

            String fechaSalida;
            boolean fechaValida = false;

            // Formateador de fecha estricto
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            // Obtener fecha válida
            do {
                System.out.print("Ingrese la fecha de salida (YYYYMMDD): ");
                fechaSalida = scanner.nextLine().trim(); // Usar trim() para evitar espacios adicionales

                try {
                    // Parsear la fecha
                    LocalDate fecha = LocalDate.parse(fechaSalida, formatter);

                    // Validar que la fecha no sea del pasado
                    if (fecha.isBefore(LocalDate.now())) {
                        System.out.println("La fecha ingresada ya pasó. Por favor, ingrese una fecha futura o actual.");
                    } else {
                        fechaValida = true; // Fecha válida
                        System.out.println("Fecha válida ingresada: " + fecha);
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Por favor, ingrese la fecha en formato yyyyMMdd.");
                }
            } while (!fechaValida);

            // Una vez validada, asignamos la fecha
            Fecha fechaObj = new Fecha(fechaSalida);
            nuevoVuelo.setFechaSalida(fechaObj);

            System.out.println("Fecha de salida registrada: " + fechaSalida);



            String horaSalida;
            boolean horaValida = false;

            do {
                System.out.print("Ingrese la hora de salida (HH:MM): ");
                horaSalida = scanner.nextLine();

                // Intentamos parsear la hora usando LocalTime
                try {
                    LocalTime.parse(horaSalida, DateTimeFormatter.ofPattern("HH:mm"));
                    horaValida = true; // Si no lanza excepción, la hora es válida
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Por favor, ingrese la hora en formato HH:MM.");
                }
            } while (!horaValida);

            // Una vez validada, asignamos la hora
            Hora horaObj = new Hora(horaSalida); // Suponiendo que tienes una clase Hora
            nuevoVuelo.setHoraSalida(horaObj);

            System.out.println("Hora de salida registrada: " + horaSalida);


            vuelos.add(nuevoVuelo);
            System.out.println("¡Vuelo programado con éxito!");
        } catch (Exception e) {
            System.out.println("Error al programar el vuelo: " + e.getMessage());
        }

        try {
            writeListToJsonFile(vuelos, PATH_RESOURCES + PATH_VUELOS);
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static Vuelo reprogramarVueloExistente(Vuelo vuelo) {
        Scanner scanner = new Scanner(System.in);
        Vuelo vueloModificado = vuelo;
        String fechaNueva;
        String horaNueva;
        int opcion;

        do {
            System.out.println("\nSubmenú - Modificar Datos:");
            System.out.println("1. Modificar fecha y hora de salida");
            System.out.println("0. Confirmar y Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            Fecha nuevoValor = new Fecha();
            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese La nueva fecha de salida (AAAA-MM-DD): ");
                    fechaNueva = scanner.nextLine();
                    vueloModificado.setFechaSalida(new Fecha(fechaNueva));

                    System.out.println("Ingrese La nueva hora de salida (HH:MM:SS)");
                    horaNueva = scanner.nextLine();
                    vueloModificado.setHoraSalida(new Hora(horaNueva));
                }
                case 0 -> System.out.println(" ");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
        return vueloModificado;
    }

    public static ArrayListGeneric<Vuelo> cargarvuelosDesdeJson(ArrayListGeneric<Vuelo> vuelos) throws
            LeerJsonException {
        ArrayListGeneric<Vuelo> vuelosCargados = new ArrayListGeneric<>();

        try {
            // Cargar vuelos desde el archivo JSON y copiarlas a la lista
            ArrayList<Vuelo> listaRutas = getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class);
            vuelosCargados.copiarLista(listaRutas);  // Copiar los vuelos a la lista

            System.out.println("VUELOS CARGADOS: " + vuelosCargados.size());

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            throw new LeerJsonException("Error al cargar los vuelos desde el archivo JSON.");
        }

        return vuelosCargados;
    }

    public static int buscarPosVueloPorNroVuelo(String nroVuelo, ArrayListGeneric<Vuelo> vuelo) throws
            ElementoNoEncontradoException {
        for (int i = 0; i < vuelo.size(); i++) {
            if (vuelo.get(i).getNroVuelo().equals(nroVuelo)) {
                return i;  // Retorna la posición del elemento si coincide el nro de vuelo
            }
        }
        throw new ElementoNoEncontradoException("No se encontró un Vuelo con el numero: " + nroVuelo);
    }

    public static void subMenuModificarVuelo() throws LeerJsonException {
        String nroVueloBuscado;
        ArrayListGeneric<Vuelo> vuelos = new ArrayListGeneric<>();

        try {
            vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            throw new LeerJsonException("Error al leer el archivo JSON Vuelos");
        }

        System.out.print("Ingrese el NUMERO de vuelo a modificar: ");
        nroVueloBuscado = scanner.nextLine(); // Consumir salto de línea

        System.out.println("Buscando vuelo numero: " + nroVueloBuscado);

        int posVuelo = -1;
        try {
            posVuelo = buscarPosVueloPorNroVuelo(nroVueloBuscado, vuelos);
            //vuelo.get(posVuelo).imprimir();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        if (posVuelo != -1) {
            Vuelo vueloModificado = reprogramarVueloExistente(vuelos.get(posVuelo));
            vuelos.set(posVuelo, vueloModificado);
            System.out.println("Vuelo modificado con éxito.");
        }

        try {
            writeListToJsonFile(vuelos, PATH_RESOURCES + PATH_VUELOS);
            System.out.println("Modificación exitosa.");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }


    public static void mostrarvuelos() {
        ArrayListGeneric<Vuelo> vuelos = new ArrayListGeneric<>();

        try {
            // Cargar los vuelos desde el archivo JSON
            ArrayListGeneric<Vuelo> vuelosCargados = cargarvuelosDesdeJson(vuelos);

            // Verificar si la lista de vuelos cargados está vacía
            if (vuelosCargados.isEmpty()) {
                System.out.println("No hay vuelos disponibles.");
            } else {
                System.out.println("\nListado de vuelos:");
                for (Vuelo vuelo : vuelosCargados) {
                    vuelo.toString(); // Llama al método imprimir de la clase vuelo
                }
            }
        } catch (LeerJsonException e) {
            System.err.println("Error al cargar loa vuelos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Se ha producido un error inesperado: " + e.getMessage());
        }
    }

}