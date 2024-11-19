package org.jetsettersv2.models.abstracts;

import org.jetsettersv2.utilities.Fecha;

import java.util.ArrayList;
import java.util.List;

public abstract class Empleado extends Persona{

    private String legajo;
    private Fecha fechaAlta;
    private Fecha fechaBaja;
    private boolean activo;
    private List<String> idiomas;


    public Empleado() {
        this.idiomas= new ArrayList<>();
    }

    public String getLegajo() {
        return legajo;
    }

    public Empleado legajo(String legajo) {
        this.legajo = legajo;
        return this;
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

    public List<String> getIdiomas() {
        return idiomas;
    }

    public Empleado idiomas(List<String> idiomas) {
        this.idiomas = idiomas;
        return this;
    }

    public void imprimir ()
    {
//        super.imprimir();
        System.out.println("Legajo..................:" + this.legajo);
        System.out.println("Fecha de alta...........:" + this.fechaAlta);
        System.out.println("Fecha de baja...........:" + this.fechaBaja);
        System.out.println("Activo..................:" + this.activo);
        System.out.println("Idionas.................:" + this.idiomas);
        System.out.println();
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "legajo='" + legajo + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", fechaBaja=" + fechaBaja +
                ", activo=" + activo +
                ", idiomas=" + idiomas +
                '}';
    }
}
