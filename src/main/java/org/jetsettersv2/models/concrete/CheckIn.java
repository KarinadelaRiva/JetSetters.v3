package org.jetsettersv2.models.concrete;

import org.jetsettersv2.enums.EstadoCheck;
import org.jetsettersv2.utilities.Fecha;

public class CheckIn {
    //Atributos
    private Fecha fechaCheck;
    private EstadoCheck estadoCheck;

    //Constructor
    public CheckIn() {

    }

    //Getters and Setters
    public Fecha getFechaCheck() {
        return fechaCheck;
    }

    public CheckIn fechaCheck(Fecha fechaCheck) {
        this.fechaCheck = fechaCheck;
        return this;
    }

    public void setFechaCheck(Fecha fechaCheck) {
        this.fechaCheck = fechaCheck;
    }

    //-------------------------------------------------------------
    public EstadoCheck getEstadoCheck() {
        return estadoCheck;
    }

    public CheckIn estadoCheck(EstadoCheck estadoCheck) {
        this.estadoCheck = estadoCheck;
        return this;
    }

    public void setEstadoCheck(EstadoCheck estadoCheck) {
        this.estadoCheck = estadoCheck;
    }

    //toString
    @Override
    public String toString() {
        return "CheckIn: " + '\n' +
                "Fecha checkIn: " + fechaCheck + '\n' +
                "Estado checkIn: " + estadoCheck + '\n';
    }

    //Metodos propios
    public void mostrar(){
        System.out.println("<<< CheckIn >>>");
        System.out.println("Fecha de checkIn.............: " + this.fechaCheck);
        System.out.println("Estado  de checkIn...........: " + this.estadoCheck);
    }

}
