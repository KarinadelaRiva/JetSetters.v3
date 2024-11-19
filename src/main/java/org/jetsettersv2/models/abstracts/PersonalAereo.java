package org.jetsettersv2.models.abstracts;

import org.jetsettersv2.enums.TipoPersonalAereo;

public abstract class PersonalAereo extends Empleado{

    private TipoPersonalAereo tipoPersonal;
    private int horasVueloAcumuladas;

    public PersonalAereo() {
    }

    public int getHorasVueloAcumuladas() {
        return horasVueloAcumuladas;
    }

    public PersonalAereo horasVueloAcumuladas(int horasVueloAcumuladas) {
        this.horasVueloAcumuladas = horasVueloAcumuladas;
        return this;
    }

    public TipoPersonalAereo getTipoPersonal() {
        return tipoPersonal;
    }

    public PersonalAereo tipoPersonal(TipoPersonalAereo tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
        return this;
    }

    public void imprimir()
    {
//        super.imprimir();
        System.out.println("Tipo de personal.............:" + this.tipoPersonal);
        System.out.println("Horas de vuelo acumuladas:...:" + this.horasVueloAcumuladas);
    }

    @Override
    public String toString() {
        return "PersonalAereo{" +
                "tipoPersonal=" + tipoPersonal +
                ", horasVueloAcumuladas=" + horasVueloAcumuladas +
                '}';
    }
}
