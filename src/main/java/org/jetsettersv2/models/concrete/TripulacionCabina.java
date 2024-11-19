package org.jetsettersv2.models.concrete;

import org.jetsettersv2.models.abstracts.PersonalAereo;

public class TripulacionCabina extends PersonalAereo {

   private String desempeño; //calificacion
   private String certificaciones ; //certificados adicionales que la tripulacion puede tener


    public String getCertificaciones() {
        return certificaciones;
    }

    public TripulacionCabina certificaciones(String certificaciones) {
        this.certificaciones = certificaciones;
        return this;
    }

    public String getDesempeño() {
        return desempeño;
    }

    public TripulacionCabina desempeño(String desempeño) {
        this.desempeño = desempeño;
        return this;
    }

    public void imprimir()
    {
        super.imprimir();
        System.out.println("Desempeño...........:" + this.desempeño);
        System.out.println("Certificaciones.................:"  + this.certificaciones);

    }


    @Override
    public String toString() {
        return "TripulacionCabina{" +
                "desempeño='" + desempeño + '\'' +
                ", certificaciones='" + certificaciones + '\'' +
                '}';
    }
}
