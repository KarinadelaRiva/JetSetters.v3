package org.jetsettersv2.enums;

public enum EstadoCheck {
    ABIERTO("Abierto."),
    CERRADO("Cerrado.");

    private String descripcion;

    EstadoCheck(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

