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
        ciudad = ciudad;
        return this;
    }

    public void setCiudad(String ciudad) {
        ciudad = ciudad;
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
        System.out.println("<<< Direccion >>>");
        System.out.println("Calle........................: " + this.calle);
        System.out.println("Numero.......................: " + this.numero);
        System.out.println("Ciudad.......................: " + this.ciudad);
        System.out.println("Codigo postal................: " + this.codigoPostal);
        System.out.println("Pais.........................: " + this.pais);
    }

    //Solicitar calle
    public void solicitarCalle() {
        System.out.print("Ingrese la calle: ");
        this.calle = scanner.nextLine().trim();
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
        System.out.print("Ingrese la ciudad: ");
        this.ciudad = scanner.nextLine().trim();
    }

    //Solicitar codigo postal
    public void solicitarCodigoPostal() {
        System.out.print("Ingrese el código postal: ");
        this.codigoPostal = scanner.nextLine().trim();
    }

    //Solicitar pais
    public void solicitarPais() {
        System.out.print("Ingrese el país: ");
        this.pais = scanner.nextLine().trim();
    }
}
