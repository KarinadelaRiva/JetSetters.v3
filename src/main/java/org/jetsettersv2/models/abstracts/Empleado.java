package org.jetsettersv2.models.abstracts;

import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.models.concrete.Administrador;
import org.jetsettersv2.models.concrete.TripulacionCabina;
import org.jetsettersv2.models.concrete.TripulacionTecnica;
import org.jetsettersv2.utilities.Fecha;

import java.util.ArrayList;
import java.util.List;

public abstract class Empleado extends Persona{

    private String legajo;
    private Fecha fechaAlta;
    private Fecha fechaBaja = null;
    private boolean activo;


    public Empleado() {

    }

    public Empleado legajo(char letraInicial, int numeroLegajo) {
        if (numeroLegajo < 0 || numeroLegajo > 9999) {
            throw new IllegalArgumentException("El número de legajo debe estar entre 0 y 9999.");
        }
        if (!Character.isLetter(letraInicial)) {
            throw new IllegalArgumentException("La letra inicial debe ser un carácter alfabético.");
        }
        this.legajo = String.format("%c%04d", Character.toUpperCase(letraInicial), numeroLegajo);
        return this;
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

    public void asignarLegajoAdmin(ArrayListGeneric<Administrador> admins) {
        int maxNumero = 0;

        // Encontrar el número más alto entre los legajos existentes
        for (Administrador empleado : admins) {
            if (empleado != null && empleado.getLegajo() != null) {
                String legajoActual = empleado.getLegajo();
                if (legajoActual.charAt(0) == 'A') {
                    int numero = Integer.parseInt(legajoActual.substring(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                }
            }
        }
        // Asignar el siguiente número disponible
        int siguienteNumero = maxNumero + 1;
        if (siguienteNumero > 9999) {
            throw new IllegalStateException("No hay números de legajo disponibles para la letra " + 'A');
        }
        this.legajo = String.format("%c%04d", 'A', siguienteNumero);
    }

    public void asignarLegajoTCabina(ArrayListGeneric<TripulacionCabina> empleados) {
        int maxNumero = 0;

        // Encontrar el número más alto entre los legajos existentes
        for (TripulacionCabina empleado : empleados) {
            if (empleado != null && empleado.getLegajo() != null) {
                String legajoActual = empleado.getLegajo();
                if (legajoActual.charAt(0) == 'C') {
                    int numero = Integer.parseInt(legajoActual.substring(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                }
            }
        }
        // Asignar el siguiente número disponible
        int siguienteNumero = maxNumero + 1;
        if (siguienteNumero > 9999) {
            throw new IllegalStateException("No hay números de legajo disponibles para la letra " + 'C');
        }
        this.legajo = String.format("%c%04d", 'C', siguienteNumero);
    }

    public void asignarLegajoTTecnica(ArrayListGeneric<TripulacionTecnica> empleados) {
        int maxNumero = 0;

        // Encontrar el número más alto entre los legajos existentes
        for (TripulacionTecnica empleado : empleados) {
            if (empleado != null && empleado.getLegajo() != null) {
                String legajoActual = empleado.getLegajo();
                if (legajoActual.charAt(0) == 'T') {
                    int numero = Integer.parseInt(legajoActual.substring(1));
                    if (numero > maxNumero) {
                        maxNumero = numero;
                    }
                }
            }
        }
        // Asignar el siguiente número disponible
        int siguienteNumero = maxNumero + 1;
        if (siguienteNumero > 9999) {
            throw new IllegalStateException("No hay números de legajo disponibles para la letra " + 'T');
        }
        this.legajo = String.format("%c%04d", 'T', siguienteNumero);
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
