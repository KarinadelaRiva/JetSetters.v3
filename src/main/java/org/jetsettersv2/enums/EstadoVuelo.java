package org.jetsettersv2.enums;

public enum EstadoVuelo {
    PROGRAMADO("Programado"),
    ABORDANDO("Abordando"),
    EN_VUELO("En vuelo"),
    ARRIBANDO("Arribando"),
    DEMORADO("Demorado"),
    REPROGRAMADO("Reprogramado");

    private String descripcion;

    // Constructor
    EstadoVuelo(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodo getter
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
