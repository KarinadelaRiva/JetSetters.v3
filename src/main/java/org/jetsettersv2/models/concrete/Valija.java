package org.jetsettersv2.models.concrete;

import org.jetsettersv2.models.abstracts.Equipaje;

public class Valija extends Equipaje {
    private String etiquetaIdentificacion;
    private boolean esPrioritaria; // Indica si tiene prioridad en el manejo

    private Valija(Builder builder) {
        super(builder.id, builder.peso, builder.conexion);
        this.etiquetaIdentificacion = builder.etiquetaIdentificacion;
        this.esPrioritaria = builder.esPrioritaria;
    }

    public String getEtiquetaIdentificacion() {
        return etiquetaIdentificacion;
    }

    public Valija setEtiquetaIdentificacion(String etiquetaIdentificacion) {
        this.etiquetaIdentificacion = etiquetaIdentificacion;
        return this;
    }

    public boolean isEsPrioritaria() {
        return esPrioritaria;
    }

    public Valija setEsPrioritaria(boolean esPrioritaria) {
        this.esPrioritaria = esPrioritaria;
        return this;
    }

    public static class Builder {
        private int id;
        private double peso;
        private boolean conexion;
        private String etiquetaIdentificacion;
        private boolean esPrioritaria;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPeso(double peso) {
            this.peso = peso;
            return this;
        }

        public Builder setConexion(boolean conexion) {
            this.conexion = conexion;
            return this;
        }

        public Builder setEtiquetaIdentificacion(String etiquetaIdentificacion) {
            this.etiquetaIdentificacion = etiquetaIdentificacion;
            return this;
        }

        public Builder setEsPrioritaria(boolean esPrioritaria) {
            this.esPrioritaria = esPrioritaria;
            return this;
        }

        public Valija build() {
            return new Valija(this);
        }
    }
}
