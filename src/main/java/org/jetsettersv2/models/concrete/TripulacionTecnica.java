package org.jetsettersv2.models.concrete;

import org.jetsettersv2.models.abstracts.PersonalAereo;

public class TripulacionTecnica extends PersonalAereo {
    private int nroLicencia;
    private String especialidad;
    private int  experiencia; // en años


    public TripulacionTecnica() {
    }

    public int getNroLicencia() {
        return nroLicencia;
    }

    public TripulacionTecnica nroLicencia(int nroLicencia) {
        this.nroLicencia = nroLicencia;
        return this;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public TripulacionTecnica especialidad(String especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public TripulacionTecnica experiencia(int experiencia) {
        this.experiencia = experiencia;
        return this;
    }

    public void imprimir()
    {
        super.imprimir();
        System.out.println("Numero de licencia...........:" + this.nroLicencia);
        System.out.println("Especialidad.................:"  + this.especialidad);
        System.out.println("Experiencia..................:" + this.experiencia);
    }

    @Override
    public String toString() {
        return "TripulacionCabina{" +
                "nroLicencia=" + nroLicencia +
                ", especialidad='" + especialidad + '\'' +
                ", experiencia=" + experiencia +
                '}';
    }
}
