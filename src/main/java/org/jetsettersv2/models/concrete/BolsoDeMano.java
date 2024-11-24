package org.jetsettersv2.models.concrete;

import org.jetsettersv2.models.abstracts.Equipaje;

public class BolsoDeMano extends Equipaje {
    private boolean cumpleReglamentoCabina; // Indica si cumple las restricciones de cabina (peso y tamaño)

    private BolsoDeMano(Builder builder) {
        super(builder.id, builder.peso, builder.conexion);
        this.cumpleReglamentoCabina = builder.cumpleReglamentoCabina;
    }

    public boolean isCumpleReglamentoCabina() {
        return cumpleReglamentoCabina;
    }

    public BolsoDeMano setCumpleReglamentoCabina(boolean cumpleReglamentoCabina) {
        this.cumpleReglamentoCabina = cumpleReglamentoCabina;
        return this;
    }

    public static class Builder {
        private int id;
        private double peso;
        private boolean conexion;
        private boolean cumpleReglamentoCabina;

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

        public Builder setCumpleReglamentoCabina(boolean cumpleReglamentoCabina) {
            this.cumpleReglamentoCabina = cumpleReglamentoCabina;
            return this;
        }

        public BolsoDeMano build() {
            return new BolsoDeMano(this);
        }
    }
}