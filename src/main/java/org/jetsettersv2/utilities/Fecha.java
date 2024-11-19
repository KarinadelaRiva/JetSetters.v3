package org.jetsettersv2.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Fecha {
    private LocalDate fecha;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor sin parámetros (fecha actual)
    public Fecha() {
        this.fecha = LocalDate.now();
    }

    // Constructor con parámetros (año, mes, día)
    public Fecha(int anio, int mes, int dia) {
        this.fecha = LocalDate.of(anio, mes, dia);
    }

    // Constructor con cadena de texto
    public Fecha(String fecha) {
        this.fecha = LocalDate.parse(fecha, formatter);
    }

    // Obtener la fecha actual
    public static Fecha fechaActual() {
        return new Fecha();
    }

    // Obtener la fecha en formato de cadena
    public String obtenerFecha() {
        return this.fecha.format(formatter);
    }

    // Modificar la fecha (establecer nueva fecha)
    public void modificarFecha(int anio, int mes, int dia) {
        this.fecha = LocalDate.of(anio, mes, dia);
    }

    // Sumar días a la fecha
    public void sumarDias(long dias) {
        this.fecha = this.fecha.plusDays(dias);
    }

    // Restar días a la fecha
    public void restarDias(long dias) {
        this.fecha = this.fecha.minusDays(dias);
    }

    // Calcular diferencia en días entre dos fechas
    public long calcularDiferencia(Fecha otraFecha) {
        return ChronoUnit.DAYS.between(this.fecha, otraFecha.fecha);
    }

    // Verificar si es antes de otra fecha
    public boolean esAntesDe(Fecha otraFecha) {
        return this.fecha.isBefore(otraFecha.fecha);
    }

    // Verificar si es después de otra fecha
    public boolean esDespuesDe(Fecha otraFecha) {
        return this.fecha.isAfter(otraFecha.fecha);
    }

    // Verificar si es igual a otra fecha
    public boolean esIgualA(Fecha otraFecha) {
        return this.fecha.isEqual(otraFecha.fecha);
    }

    // Obtener el día de la semana
    public String obtenerDiaDeLaSemana() {
        return this.fecha.getDayOfWeek().toString();
    }

    // Obtener el día del mes
    public int obtenerDiaDelMes() {
        return this.fecha.getDayOfMonth();
    }

    // Obtener el mes
    public int obtenerMes() {
        return this.fecha.getMonthValue();
    }

    // Obtener el año
    public int obtenerAnio() {
        return this.fecha.getYear();
    }

    @Override
    public String toString() {
        return fecha.toString();
    }

}