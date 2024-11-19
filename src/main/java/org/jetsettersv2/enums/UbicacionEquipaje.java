package org.jetsettersv2.enums;

public enum UbicacionEquipaje {
        CABINA("Equipaje de mano",1),
        BODEGA("Equipaje despachado",2);

        private String descipcionUE;
        private int estadoUE;

        UbicacionEquipaje(String descipcionUE, int estadoUE) {
            this.descipcionUE = descipcionUE;
            this.estadoUE = estadoUE;
        }

        public String getDescipcionUE() {
            return descipcionUE;
        }

        public int getEstadoUE() {
            return estadoUE;
        }
    }


