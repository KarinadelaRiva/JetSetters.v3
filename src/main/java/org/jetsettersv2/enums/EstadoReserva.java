package org.jetsettersv2.enums;

public enum EstadoReserva {
    PENDIENTE("La reserva fue creada pero no confirmada", 1),
    CONFIRMADA("La reserva fue confirmada y está activa", 2),
    CANCELADA("La reserva fue cancelada por la aerolínea o el pasajero", 3),
    CHECK_IN("El pasajero realizó el check-in", 8),
    COMPLETADA("El vuelo se completó con éxito", 5),
    NO_PRESENTADO("Pasajero no se presentó al vuelo", 6);

    private String descripcionER;
    private int nroReserva;

    EstadoReserva(String descripcionER, int nroReserva) {
        this.descripcionER = descripcionER;
        this.nroReserva = nroReserva;
    }

    public int getNroEstado() {
        return nroReserva;
    }
}
