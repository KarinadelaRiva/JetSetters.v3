package org.jetsettersv2.models.abstracts;

import org.jetsettersv2.utilities.Fecha;

import java.util.ArrayList;
import java.util.List;

public abstract class Empleado extends Persona{
    private static int contadorLegajos = 1;

    private String legajo;
    private Fecha fechaAlta;
    private Fecha fechaBaja = null;
    private boolean activo;


    public Empleado() {
        this.legajo = generarLegajo(); // Genera el legajo automáticamente
    }

    private static String generarLegajo() {
        return String.format("L%04d", contadorLegajos++);
    }

    public String getLegajo() {
        return legajo;
    }


    public Fecha getFechaAlta() {
        return fechaAlta;
    }

    public Empleado fechaAlta(Fecha fechaAlta) {
        this.fechaAlta = fechaAlta;
        return this;
    }

    public Fecha getFechaBaja() {
        return fechaBaja;
    }

    public Empleado fechaBaja(Fecha fechaBaja) {
        this.fechaBaja = fechaBaja;
        return this;
    }

    public boolean isActivo() {
        return activo;
    }

    public Empleado activo(boolean activo) {
        this.activo = activo;
        return this;
    }

    public void imprimir ()
    {
        System.out.println("Legajo..................:" + this.legajo);
        super.imprimir();
        System.out.println("Fecha de alta...........:" + this.fechaAlta);
        if(this.fechaBaja != null){
            System.out.println("Fecha de baja...........:" + this.fechaBaja);
        }
        if(this.activo){
            System.out.println("Situación...............: Activo");
        }else{
            System.out.println("Situación...............: Inactivo");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "legajo='" + legajo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", activo=" + activo +
                '}';
    }
}
