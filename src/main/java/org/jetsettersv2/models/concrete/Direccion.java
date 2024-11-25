package org.jetsettersv2.models.concrete;

import java.util.Scanner;

public class Direccion {
    //Atributos
    private String calle;
    private int numero;
    private String ciudad;
    private String codigoPostal;
    private String pais;

    private Scanner scanner = new Scanner(System.in);

    //Constructor
    public Direccion() {

    }

    //Getters and Setters
    public String getCalle() {
        return calle;
    }

    public Direccion calle(String calle) {
        this.calle = calle;
        return this;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    //-------------------------------------------------------------
    public int getNumero() {
        return numero;
    }

    public Direccion numero(int numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    //-------------------------------------------------------------
    public String getCiudad() {
        return ciudad;
    }

    public Direccion ciudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    //-------------------------------------------------------------
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public Direccion codigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
        return this;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    //-------------------------------------------------------------
    public String getPais() {
        return pais;
    }

    public Direccion pais(String pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    //toString
    @Override
    public String toString() {
        return "Direccion: " + '\n' +
                "Calle: " + calle + '\n' +
                "Numero: " + numero + '\n' +
                "Ciudad: " + ciudad + '\n' +
                "CodigoPostal: " + codigoPostal + '\n' +
                "Pais: " + pais + '\n';
    }

    //Metodos propios
    //Mostrar direccion:
    public void mostrar(){
        System.out.println("Direccion: ");
        System.out.println("    Calle....................: " + this.calle);
        System.out.println("    Numero...................: " + this.numero);
        System.out.println("    Ciudad...................: " + this.ciudad);
        System.out.println("    Codigo postal............: " + this.codigoPostal);
        System.out.println("    Pais.....................: " + this.pais);
    }

    //Solicitar calle
    public void solicitarCalle() {
        boolean esValido = false;

        while (!esValido) {
            System.out.print("Ingrese la calle: ");
            String entrada = scanner.nextLine().trim();

            // Validar que no esté vacío
            if (entrada.isEmpty()) {
                System.out.println("El nombre de la calle no puede estar vacío. Intente nuevamente.");
                continue;
            }

            // Validar que contenga solo caracteres permitidos (letras, números, espacios, puntos y guiones)
            if (!entrada.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 .-]+")) {
                System.out.println("El nombre de la calle solo puede contener letras, números, espacios, puntos y guiones. Intente nuevamente.");
                continue;
            }

            // Formatear el nombre de la calle con mayúscula inicial
            this.calle = formatearString(entrada);
            esValido = true;
        }

    }

    //Solicitar numero
    public void solicitarNumero() {
        System.out.print("Ingrese el número: ");
        String input = scanner.nextLine().trim();
        if (input.matches("\\d+")) {
            this.numero = Integer.parseInt(input);
        } else {
            System.out.println("Número inválido. Intente nuevamente.");
            solicitarNumero();
        }
    }

    //Solicitar ciudad
    public void solicitarCiudad() {
        boolean esValido = false;

        while (!esValido) {
            System.out.print("Ingrese la ciudad: ");
            String entrada = scanner.nextLine().trim();

            // Validar que no esté vacío
            if (entrada.isEmpty()) {
                System.out.println("El nombre de la ciudad no puede estar vacío. Intente nuevamente.");
                continue;
            }

            // Validar que no contenga números
            if (entrada.matches(".*\\d.*")) {
                System.out.println("El nombre de la ciudad no debe contener números. Intente nuevamente.");
                continue;
            }

            // Validar que solo contenga letras y espacios
            if (!entrada.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("El nombre de la ciudad solo puede contener letras y espacios. Intente nuevamente.");
                continue;
            }

            // Formatear el nombre de la ciudad con mayúscula inicial
            this.ciudad = formatearString(entrada);
            esValido = true;
        }
    }

    //Solicitar codigo postal
    public void solicitarCodigoPostal() {
        boolean esValido = false;

        while (!esValido) {
            System.out.print("Ingrese el código postal: ");
            String entrada = scanner.nextLine().trim();

            // Verificar si está vacío
            if (entrada.isEmpty()) {
                System.out.println("El código postal no puede estar vacío. Intente nuevamente.");
                continue;
            }

            // Verificar si contiene solo números
            if (!entrada.matches("\\d+")) {
                System.out.println("El código postal solo debe contener números. Intente nuevamente.");
                continue;
            }

            // Verificar la longitud (por ejemplo, entre 4 y 6 dígitos)
            if (entrada.length() < 4 || entrada.length() > 6) {
                System.out.println("El código postal debe tener entre 4 y 6 dígitos. Intente nuevamente.");
                continue;
            }

            // Si pasa todas las validaciones
            this.codigoPostal = entrada;
            esValido = true;
        }
    }

    //Solicitar pais

    public void solicitarPais() {
        boolean esValido = false;

        while (!esValido) {
            System.out.print("Ingrese el país: ");
            String entrada = scanner.nextLine().trim();

            // Verificar que no contenga números
            if (entrada.matches(".*\\d.*")) {
                System.out.println("El nombre del país no debe contener números. Intente nuevamente.");
                continue;
            }

            // Asegurar que no esté vacío
            if (entrada.isEmpty()) {
                System.out.println("El nombre del país no puede estar vacío. Intente nuevamente.");
                continue;
            }

            // Formatear a formato "Primera Letra Mayúscula"
            this.pais = formatearString(entrada);
            esValido = true; // Salir del bucle si es válido
        }
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
