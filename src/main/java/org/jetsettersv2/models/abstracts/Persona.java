package org.jetsettersv2.models.abstracts;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LoginException;
import org.jetsettersv2.models.concrete.Direccion;
import org.jetsettersv2.utilities.JacksonUtil;

import java.util.Scanner;
import java.util.UUID;

import static org.jetsettersv2.utilities.JacksonUtil.getJsonToList;

public abstract class Persona {

    private String idPersona = UUID.randomUUID().toString();
    private String nombre;
    private String apellido;
    private String dni;
    private String pasaporte;
    private String telefono;
    private Direccion direccion;
    private String email;
    private String password;

    Scanner scanner = new Scanner(System.in);


    public Persona() {
    }

    // <<<<<<<GETTERS Y SETTERS>>>>>>>

    public String getIdPersona() {
        return idPersona;
    }

    public Persona idPersona(String idPersona) {
        this.idPersona = idPersona;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Persona nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public Persona apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public Persona dni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public Persona pasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public Persona telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Persona direccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Persona email(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Persona password(String password) {
        this.password = password;
        return this;
    }

    // <<<<<<<METODOS IMPRESION>>>>>>>

    public void imprimir(){
        System.out.println("ID ..................................: " + this.idPersona);
        System.out.println("Nombre...............................: " + this.nombre);
        System.out.println("Apellido.............................: " + this.apellido);
        System.out.println("DNI..................................: " + this.dni);
        System.out.println("Pasaporte............................: " + this.pasaporte);
        System.out.println("Telefono.............................: " + this.telefono);
        System.out.println("<<..........Direccion..............>>: ");
        direccion.mostrar();
    }

    // <<<<<<<TO STRING>>>>>>>

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona='" + idPersona + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", pasaporte='" + pasaporte + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion=" + direccion +
                '}';
    }

    // <<<<<<<REGISTRO>>>>>>>

    public void solicitarNombre() {
        System.out.print("Ingrese el nombre: ");
        this.nombre = scanner.nextLine().trim();
    }

    public void solicitarApellido() {
        System.out.print("Ingrese el apellido: ");
        this.apellido = scanner.nextLine().trim();
    }

    public void solicitarDni() {
        System.out.print("Ingrese el DNI (solo números): ");
        String input = scanner.nextLine().trim();
        if (input.matches("\\d+")) {
            this.dni = input;
        } else {
            System.out.println("DNI inválido. Intente nuevamente.");
            solicitarDni();
        }
    }

    public void solicitarPasaporte() {
        System.out.print("Ingrese el número de pasaporte: ");
        this.pasaporte = scanner.nextLine().trim();
    }

    public void solicitarTelefono() {
        System.out.print("Ingrese el teléfono (incluyendo código de área): ");
        this.telefono = scanner.nextLine().trim();
    }

    public void solicitarDireccion() {
        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.solicitarCalle();
        nuevaDireccion.solicitarNumero();
        nuevaDireccion.solicitarCiudad();
        nuevaDireccion.solicitarCodigoPostal();
        nuevaDireccion.solicitarPais();
        this.direccion = nuevaDireccion;
    }


    public void solicitarEmail() {

        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        boolean emailValido = false;

        while (!emailValido) {
            System.out.print("Ingrese un email válido: ");
            String input = scanner.nextLine();

            if (input.matches(regex)) {
                this.email = input;
                emailValido = true;
                System.out.println("Email registrado correctamente.");
            } else {
                System.out.println("Email inválido. Por favor, intente nuevamente.");
            }
        }

    }

    public void solicitarPassword() {
        // Requisitos de la contraseña:
        // Al menos una letra mayúscula, una minúscula, un número, un carácter especial y longitud mínima de 8
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$";
        boolean contraseñaValida = false;

        while (!contraseñaValida) {
            System.out.print("Ingrese una contraseña válida (mínimo 8 caracteres, al menos una mayúscula, una minúscula, un número y un carácter especial): ");
            String input = scanner.nextLine();

            if (input.matches(regex)) {
                this.password = input;
                contraseñaValida = true;
                System.out.println("Contraseña registrada correctamente.");
            } else {
                System.out.println("Contraseña inválida. Por favor, asegúrese de cumplir los requisitos.");
            }
        }
    }

        // <<<<<<<INICIO DE SESION>>>>>>>

    public static Persona iniciarSesion(ArrayListGeneric<Persona> personas) throws LoginException {

        Scanner scanner = new Scanner(System.in);

//        ArrayListGeneric<Persona> personas = new ArrayListGeneric<>();
//        personas.copiarLista(getJsonToList(JacksonUtil.PATH_RESOURCES + JacksonUtil.PATH_PERSONAS, Persona.class));

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine().trim();

        // Buscar persona por email
        Persona personaEncontrada = null;
        for (Persona p : personas) {
            if (p.getEmail().equals(email)) {
                personaEncontrada = p;
                break;
            }
        }

        if (personaEncontrada == null) {
            throw new LoginException("El email ingresado no está registrado.");
        }

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine().trim();

        if (!personaEncontrada.getPassword().equals(password)) {
            throw new LoginException("La contraseña es incorrecta.");
        }

        System.out.println("Inicio de sesión exitoso. Bienvenido, " + personaEncontrada.getNombre() + "!");
        return personaEncontrada; // Devuelve la persona logueada
    }

    // <<<<<<<MODIFICAR DATOS>>>>>>>

    public void modificarDatos(String atributo, String nuevoValor) {

        switch (atributo.toLowerCase()) {
            case "nombre":
                this.nombre(nuevoValor);
                break;
            case "apellido":
                this.apellido(nuevoValor) ;
                break;
            case "dni":
                this.dni(nuevoValor);
                break;
            case "pasaporte":
                this.pasaporte(nuevoValor);
                break;
            case "telefono":
                this.telefono(nuevoValor);
                break;
            default:
                System.out.println("Atributo no reconocido.");
        }
    }




}
