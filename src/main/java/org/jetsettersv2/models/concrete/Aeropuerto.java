package org.jetsettersv2.models.concrete;
import org.jetsettersv2.collections.ArrayListGeneric;
import org.jetsettersv2.exceptions.LeerJsonException;

import java.util.ArrayList;
import java.util.List;


public class Aeropuerto {
    private String codigo;
    private String nombre;
    private String ciudad;

    // Lista estática que contiene todos los aeropuertos
    public static final List<Aeropuerto> aeropuertos = new ArrayList<>();

    // Constructor
    public Aeropuerto() {
    }

    // Métodos getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }



    // Método para confirmar si un aeropuerto existe
    public boolean esCodigoValido(String codigo, List<Aeropuerto> aeropuertos) {
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (aeropuerto.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    // Método para buscar un aeropuerto por código
    public boolean buscarAeropuertoPorCodigo(String codigo, List<Aeropuerto> aeropuertos) {
        boolean aux = false;
        for (Aeropuerto aeropuerto : aeropuertos) {
            if (aeropuerto.getCodigo().equalsIgnoreCase(codigo.toUpperCase().trim())) {
                aux = true;
            }
        }
        return aux;
    }


    @Override
    public String toString() {
        return "Código...............................: " + this.codigo + "\n" +
                "Nombre...............................: " + this.nombre + "\n" +
                "Ciudad...............................: " + this.ciudad;
    }

}