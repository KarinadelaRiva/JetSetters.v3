package org.jetsettersv2.models.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetsettersv2.utilities.Fecha;

public class CheckIn {
    //Atributos
    @JsonProperty("fechaCheck")
    private Fecha fechaCheck; // Puede ser null según el JSON.

    private boolean estadoCheck;

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


    public boolean getEstadoCheck() {
        return estadoCheck;
    }

    public CheckIn estadoCheck(boolean estadoCheck) {
        this.estadoCheck = estadoCheck;
        return this;
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
        System.out.println("Estado  de checkIn...........: " + this.imprimirEstadoCheckIn());
    }

    public String imprimirEstadoCheckIn(){
        if(this.estadoCheck){
            return "Realizado";
        }else{
            return "Pendiente";
        }
    }

}