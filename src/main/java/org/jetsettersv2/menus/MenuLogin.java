package org.jetsettersv2.menus;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LoginException;
import org.jetsettersv2.models.concrete.Administrador;
import org.jetsettersv2.models.concrete.Direccion;
import org.jetsettersv2.models.concrete.UsuarioCliente;
import org.jetsettersv2.models.concrete.Vuelo;
import org.jetsettersv2.utilities.Fecha;

import java.util.Scanner;

import static org.jetsettersv2.menus.menuUsuario.mostrarMenuUsuario;
import static org.jetsettersv2.utilities.Fecha.fechaActual;
import static org.jetsettersv2.utilities.JacksonUtil.*;

public class MenuLogin {
    private static final Scanner scanner = new Scanner(System.in);
    private static UsuarioCliente usuarioLogueado = new UsuarioCliente();
    private static Administrador adminLogueado = new Administrador();

    public static void login() {
        ArrayListGeneric<UsuarioCliente> usuarios = new ArrayListGeneric<>();
        ArrayListGeneric<Administrador> empleados = new ArrayListGeneric<>();
        int opcion;
        do {
            usuarioLogueado = null;
            adminLogueado = null;

            System.out.println("\n      Bienvenid@ a JetSetters!\n");
            opcion = opcionesLogin();
            switch (opcion) {
                case 1:
                    System.out.println("\n--Iniciar Sesion--");
                    try{
                        usuarioLogueado = inicioSesionUsuario(usuarios);
                        mostrarMenuUsuario(usuarioLogueado);
                    }catch (Exception e){
                        System.out.println("Error al iniciar sesion: " + e.getMessage());
                    }

//                    usuarioLogueado = inicioSesionUsuario(usuarios);
//                    if (usuarioLogueado != null) {
//                        mostrarMenuUsuario(usuarioLogueado);
//                    }
                    break;
                case 2:
                    registrarUsuario(usuarios);
                    break;
                case 3:
                    verVuelosSinLogin();
                    break;
                case 4:
                    adminLogueado = inicioSesionAdmin(empleados);
                    if(adminLogueado != null) {
                        System.out.println("LLAMAR AL MENU ADMINISTRADORES ACÁ");
                    }
                    break;
                case 0:
                    System.out.println("Adios " + usuarioLogueado.getNombre() + ", esperamos verte pronto!");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }while (opcion != 0);
    }

    public static int opcionesLogin() {
        int opcion = -1;

        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.println("3. Ver proximos vuelos");
        System.out.println("4. Ingresar como Administrador");
        System.out.println("0. Salir\n");

        while (opcion < 0 || opcion > 4) {
            try {
                System.out.print("Ingrese una opcion: ");
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida");
            }
        }
        return opcion;
    }

    public static UsuarioCliente inicioSesionUsuario(ArrayListGeneric<UsuarioCliente> usuarios) throws LoginException{
        UsuarioCliente logueado = new UsuarioCliente();

        try{
            usuarios.copiarLista(getJsonToList(PATH_RESOURCES + PATH_USUARIOSCLIENTE, UsuarioCliente.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
        
        try {
            logueado = iniciaUsuario(usuarios);
            System.out.println("\nDatos del usuario logueado: " + logueado.getNombre() + " - " + logueado.getEmail());
        } catch (LoginException e) {
            throw new LoginException("\nContraseña y/o email incorrectos.");
        }
        return logueado;
    }

    public static UsuarioCliente iniciaUsuario(ArrayListGeneric<UsuarioCliente> usuarios) throws LoginException {
        // Solicitar el email
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine().trim();

        // Buscar usuario por email
        UsuarioCliente usuarioEncontrado = null;
        for (UsuarioCliente u : usuarios) {
            if (u.getEmail().trim().equalsIgnoreCase(email)) { // Compara sin importar mayúsculas/minúsculas
                usuarioEncontrado = u;
                break;
            }
        }

        // Verificar si el usuario fue encontrado
        if (usuarioEncontrado == null) {
            throw new LoginException("El email ingresado no está registrado.");
        }

        // Solicitar la contraseña
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine().trim();

        // Verificar la contraseña
        if (!usuarioEncontrado.getPassword().equals(password)) {
            throw new LoginException("La contraseña ingresada es incorrecta.");
        }

        // Si todo es correcto, se muestra un mensaje de bienvenida
        System.out.println("\nInicio de sesión exitoso. Bienvenido, " + usuarioEncontrado.getNombre() + "!");

        // Retornar el usuario logueado
        return usuarioEncontrado;
    }

    public static void registrarUsuario(ArrayListGeneric<UsuarioCliente> usuarios){
        UsuarioCliente nuevoUsuario = new UsuarioCliente();
        try{
            usuarios.copiarLista(getJsonToList(PATH_RESOURCES + PATH_USUARIOSCLIENTE, UsuarioCliente.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        nuevoUsuario = registrarse();
        usuarios.add(nuevoUsuario);

        try{
            writeListToJsonFile(usuarios, PATH_RESOURCES + PATH_USUARIOSCLIENTE);
            System.out.println("Registro completado exitosamente. Bienvenid@, " + nuevoUsuario.getNombre() + "!\n");
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }

    public static UsuarioCliente registrarse() {
        UsuarioCliente nuevoUsuario = new UsuarioCliente();
        int opcion = -1;

        System.out.println("\n----Registro de usuario----\n");
        nuevoUsuario.solicitarNombre();
        nuevoUsuario.solicitarApellido();
        nuevoUsuario.solicitarDni();
        nuevoUsuario.solicitarPasaporte();
        nuevoUsuario.solicitarTelefono();
        nuevoUsuario.solicitarDireccion();
        nuevoUsuario.solicitarEmail();
        nuevoUsuario.solicitarPassword();

        System.out.println("\n-----Datos ingresados:----");
        System.out.println("Nombre: " + nuevoUsuario.getNombre());
        System.out.println("Apellido: " + nuevoUsuario.getApellido());
        System.out.println("DNI: " + nuevoUsuario.getDni());
        System.out.println("Pasaporte: " + nuevoUsuario.getPasaporte());
        System.out.println("Teléfono: " + nuevoUsuario.getTelefono());
        Direccion dir = nuevoUsuario.getDireccion();
        if (dir != null) {
            System.out.println("Dirección: " + dir.getCalle() + " " + dir.getNumero() + ", " + dir.getCiudad() + ", " + dir.getPais() + " (CP: " + dir.getCodigoPostal() + ")");
        } else {
            System.out.println("Dirección no ingresada.");
        }
        System.out.println("Email: " + nuevoUsuario.getEmail());
        System.out.println("Contraseña: " + nuevoUsuario.getPassword());

        do {
            System.out.println("\nMenú:");
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
                case 1 -> nuevoUsuario.solicitarNombre();
                case 2 -> nuevoUsuario.solicitarApellido();
                case 3 -> nuevoUsuario.solicitarDni();
                case 4 -> nuevoUsuario.solicitarPasaporte();
                case 5 -> nuevoUsuario.solicitarTelefono();
                case 6 -> nuevoUsuario.solicitarDireccion();
                case 7 -> nuevoUsuario.solicitarEmail();
                case 8 -> nuevoUsuario.solicitarPassword();
                case 9 -> {
                    System.out.println("\n----Datos ingresados:----");
                    System.out.println("Nombre: " + nuevoUsuario.getNombre());
                    System.out.println("Apellido: " + nuevoUsuario.getApellido());
                    System.out.println("DNI: " + nuevoUsuario.getDni());
                    System.out.println("Pasaporte: " + nuevoUsuario.getPasaporte());
                    System.out.println("Teléfono: " + nuevoUsuario.getTelefono());
                    Direccion dir2 = nuevoUsuario.getDireccion();
                    if (dir != null) {
                        System.out.println("Dirección: " + dir2.getCalle() + " " + dir2.getNumero() + ", " + dir2.getCiudad() + ", " + dir2.getPais() + " (CP: " + dir2.getCodigoPostal() + ")");
                    } else {
                        System.out.println("Dirección no ingresada.");
                    }
                }
                case 0 -> System.out.println("Saliendo...0\n");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion!=0);
        return nuevoUsuario;
    }

    public static Administrador inicioSesionAdmin(ArrayListGeneric<Administrador> admins) throws LoginException{
        Administrador logueado = new Administrador();

        try{
            admins.copiarLista(getJsonToList(PATH_RESOURCES + PATH_USUARIOSEMPLEADOS, Administrador.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        try {
            logueado = iniciaAdministrador(admins);
            System.out.println("Datos del usuario logueado: " + logueado.getNombre() + " - " + logueado.getEmail());
        } catch (LoginException e) {
            throw new LoginException("\nContraseña y/o email incorrectos.");
        }
        return logueado;
    }

    public static Administrador iniciaAdministrador(ArrayListGeneric<Administrador> admins) throws LoginException {
        // Solicitar el email
        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine().trim();

        // Buscar administrador por email
        Administrador administradorEncontrado = null;
        for (Administrador a : admins) {
            if (a.getEmail().trim().equalsIgnoreCase(email)) { // Compara sin importar mayúsculas/minúsculas
                administradorEncontrado = a;
                break;
            }
        }

        // Verificar si el administrador fue encontrado
        if (administradorEncontrado == null) {
            throw new LoginException("\nEl email ingresado no está registrado.");
        }

        // Solicitar la contraseña
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine().trim();

        // Verificar la contraseña
        if (!administradorEncontrado.getPassword().equals(password)) {
            throw new LoginException("\nLa contraseña ingresada es incorrecta.");
        }

        // Si todo es correcto, se muestra un mensaje de bienvenida
        System.out.println("\nInicio de sesión exitoso. Bienvenido, " + administradorEncontrado.getNombre() + "!");

        // Retornar el administrador logueado
        return administradorEncontrado;
    }

    public static void verVuelosSinLogin(){
        // pasar a un arraylist el json de vuelos, y mostrar los vuelos de los proximos dos meses
        ArrayListGeneric<Vuelo> Vuelos = new ArrayListGeneric<>();

        Fecha fechaLimite = fechaActual();
        fechaLimite.sumarDias(60);

        try{
            Vuelos.copiarLista(getJsonToList(PATH_RESOURCES + PATH_VUELOS, Vuelo.class));
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }

        System.out.println("Vuelos disponibles en los próximos dos meses: ");
        for (Vuelo v : Vuelos) {
            if ((v.getFechaSalida().esIgualA(fechaActual()) || v.getFechaSalida().esDespuesDe(fechaActual())) &&
                    v.getFechaSalida().esAntesDe(fechaLimite)) {
                System.out.println("Vuelo: " + v.getNroVuelo());
                System.out.println("Fecha de salida: " + v.getFechaSalida().obtenerFecha());
                System.out.println("Hora de salida: " + v.getHoraSalida());
                System.out.println("Ruta: " + v.getRuta().getOrigen() + " - " + v.getRuta().getDestino());
            }
        }
    }
}
