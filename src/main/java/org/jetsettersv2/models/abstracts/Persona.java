package org.jetsettersv2.models.abstracts;

import org.jetsettersv2.models.concrete.Direccion;

import java.util.Scanner;
import java.util.UUID;


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
        System.out.println("ID ..........................: " + this.idPersona);
        System.out.println("Nombre.......................: " + this.nombre);
        System.out.println("Apellido.....................: " + this.apellido);
        System.out.println("DNI..........................: " + this.dni);
        System.out.println("Pasaporte....................: " + this.pasaporte);
        System.out.println("Telefono.....................: " + this.telefono);
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
        boolean nombreValido = false;
        while (!nombreValido) {
            System.out.print("Ingrese el nombre: ");
            String entrada = scanner.nextLine().trim();
            this.nombre = formatearString(entrada);

            // Validar que el nombre solo contenga letras y espacios
            if (this.nombre.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+")) {
                nombreValido = true; // El nombre es válido
            } else {
                System.out.println("El nombre solo puede contener letras y espacios. Intente nuevamente.");
            }
        }
    }

    public void solicitarApellido() {
        boolean apellidoValido = false;
        while (!apellidoValido) {
            System.out.print("Ingrese el apellido: ");
            String entrada = scanner.nextLine().trim();
            this.apellido = formatearString(entrada);

            // Validar que el apellido solo contenga letras y espacios
            if (this.apellido.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+")) {
                apellidoValido = true; // El apellido es válido
            } else {
                System.out.println("El apellido solo puede contener letras y espacios. Intente nuevamente.");
            }
        }
    }

    public void solicitarDni() {
        boolean dniValido = false;

        while (!dniValido) {
            System.out.print("Ingrese el DNI (número entre 1,000,000 y 100,000,000): ");
            String input = scanner.nextLine().trim();

            // Validar que el DNI sea numérico y esté en el rango permitido
            if (input.matches("\\d+")) { // Verificar que sean solo números
                long dniNumero = Long.parseLong(input); // Convertir a número
                if (dniNumero >= 1_000_000 && dniNumero <= 100_000_000) {
                    this.dni = input; // Asignar el DNI válido
                    dniValido = true; // Salir del bucle
                } else {
                    System.out.println("El DNI debe estar entre 1,000,000 y 100,000,000.");
                }
            } else {
                System.out.println("El DNI solo puede contener números. Intente nuevamente.");
            }
        }
    }

    public void solicitarPasaporte() {
        boolean pasaporteValido = false;

        while (!pasaporteValido) {
            System.out.print("Ingrese el número de pasaporte: ");
            this.pasaporte = scanner.nextLine().trim();

            // Validar formato del pasaporte: comienza con letra opcional, seguido de números (8-9 caracteres)
            if (this.pasaporte.matches("^[A-Z0-9]{1,2}[0-9]{6,9}$"))  {
                pasaporteValido = true; // El pasaporte es válido
            } else {
                System.out.println("\"Número de pasaporte inválido. Debe tener entre 8 y 11 caracteres, comenzar con una o dos letras, seguido de dígitos.\"");
            }
        }
    }

    public void solicitarTelefono() {
        boolean telefonoValido = false;

        while (!telefonoValido) {
            System.out.print("Ingrese el teléfono (incluyendo código de área): ");
            this.telefono = scanner.nextLine().trim();

            // Validar el formato del teléfono
            if (this.telefono.matches("\\+?\\d{10,15}")) {
                telefonoValido = true; // El teléfono es válido
            } else {
                System.out.println("Número de teléfono inválido. Debe contener entre 10 y 15 dígitos, y puede incluir un '+' al inicio.");
            }
        }
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
                this.email = input.toLowerCase();
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
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-_*%#@$!?&])[A-Za-z\\d-_*%#@$!?&]{8,}$";
        boolean contraseniaValida = false;

        while (!contraseniaValida) {
            System.out.print("Ingrese una contraseña válida (mínimo 8 caracteres, al menos una mayúscula, una minúscula, un número y un carácter especial): ");
            String input = scanner.nextLine();

            if (input.matches(regex)) {
                this.password = input;
                contraseniaValida = true;
                System.out.println("Contraseña registrada correctamente.");
            } else {
                System.out.println("Contraseña inválida. Por favor, asegúrese de cumplir los requisitos.");
            }
        }
    }

    // <<<<<<<MODIFICAR DATOS>>>>>>>

    public Persona modificarDatos(String atributo, String nuevoValor) {
        switch (atributo.toLowerCase()) {
            case "nombre":
                if (nuevoValor.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+")) {
                    this.nombre = formatearString(nuevoValor.trim());
                } else {
                    System.out.println("El nombre solo puede contener letras y espacios.");
                }
                break;
            case "apellido":
                if (nuevoValor.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+")) {
                    this.apellido = formatearString(nuevoValor.trim());
                } else {
                    System.out.println("El apellido solo puede contener letras y espacios.");
                }
                break;
            case "dni":
                if (nuevoValor.matches("\\d+") && nuevoValor.length() >= 7 && nuevoValor.length() <= 8) {
                    this.dni = nuevoValor.trim();
                } else {
                    System.out.println("El DNI debe contener solo números y tener entre 7 y 8 dígitos.");
                }
                break;
            case "pasaporte":
                if(nuevoValor.matches("^[A-Z0-9]{1,2}[0-9]{6,9}$")){
                    this.pasaporte = nuevoValor.trim();
                } else {
                    System.out.println("Número de pasaporte inválido. Debe tener entre 8 y 11 caracteres, comenzar con una o dos letras, seguido de dígitos.");
                }
                break;
            case "telefono":
                if (nuevoValor.matches("\\+?\\d{10,15}")) {
                    this.telefono = nuevoValor.trim();
                } else {
                    System.out.println("Número de teléfono inválido. Debe contener entre 10 y 15 dígitos y puede incluir un '+' al inicio.");
                }
                break;
            case "direccion":
                nuevoValor = nuevoValor.trim().toLowerCase();
                this.direccion.solicitarPais();
                this.direccion.solicitarCiudad();
                this.direccion.solicitarCalle();
                this.direccion.solicitarNumero();
                this.direccion.solicitarCodigoPostal();
                break;
            default:
                System.out.println("Atributo no reconocido.");
        }

        return this;
    }

    private String formatearString(String entrada) {
        String[] palabras = entrada.split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (palabra.length() > 0) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)));
                if (palabra.length() > 1) {
                    resultado.append(palabra.substring(1).toLowerCase());
                }
                resultado.append(" ");
            }
        }

        return resultado.toString().trim();
    }

}
