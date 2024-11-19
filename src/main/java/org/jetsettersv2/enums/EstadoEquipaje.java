package org.jetsettersv2.enums;

public enum EstadoEquipaje {
    PERDIDO("Equipaje perdido", 1),
    EN_TRANSITO("Equipaje en transito", 2),
    ENTREGADO("Equipaje entregado", 3);

    private String descripcionEE;
    private int estadoEE;

    EstadoEquipaje(String descripcionEE, int estadoEE) {
        this.descripcionEE = descripcionEE;
        this.estadoEE = estadoEE;
    }

    public String getDescripcionEE() {
        return descripcionEE;
    }

    public int getEstadoEE() {
        return estadoEE;
    }
}
