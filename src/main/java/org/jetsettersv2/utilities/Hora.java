package org.jetsettersv2.utilities;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Hora {
    private LocalTime hora;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Nuevo getter para Jackson
    public String getHora() {
        return this.hora.format(formatter);
    }

    // Constructor sin parámetros (hora actual)
    public Hora() {
        this.hora = LocalTime.now();
    }

    // Constructor con parámetros (hora, minuto, segundo)
    public Hora(int hora, int minuto, int segundo) {
        this.hora = LocalTime.of(hora, minuto, segundo);
    }

    // Constructor con cadena de texto
    public Hora(String hora) {
        this.hora = LocalTime.parse(hora, formatter);
    }

    // Obtener la hora actual
    public static Hora horaActual() {
        return new Hora();
    }

    // Obtener la hora en formato de cadena
    public String obtenerHora() {
        return this.hora.format(formatter);
    }

    // Modificar la hora (establecer nueva hora)
    public void modificarHora(int hora, int minuto, int segundo) {
        this.hora = LocalTime.of(hora, minuto, segundo);
    }

    // Sumar segundos a la hora
    public void sumarSegundos(long segundos) {
        this.hora = this.hora.plusSeconds(segundos);
    }

    // Restar segundos a la hora
    public void restarSegundos(long segundos) {
        this.hora = this.hora.minusSeconds(segundos);
    }

    // Calcular diferencia en segundos entre dos horas
    public long calcularDiferenciaEnSegundos(Hora otraHora) {
        return ChronoUnit.SECONDS.between(this.hora, otraHora.hora);
    }

    // Verificar si es antes de otra hora
    public boolean esAntesDe(Hora otraHora) {
        return this.hora.isBefore(otraHora.hora);
    }

    // Verificar si es después de otra hora
    public boolean esDespuesDe(Hora otraHora) {
        return this.hora.isAfter(otraHora.hora);
    }

    // Verificar si es igual a otra hora
    public boolean esIgualA(Hora otraHora) {
        return this.hora.equals(otraHora.hora);
    }

    // Obtener la hora
    public int obtenerHoraDelDia() {
        return this.hora.getHour();
    }

    // Obtener los minutos
    public int obtenerMinutos() {
        return this.hora.getMinute();
    }

    // Obtener los segundos
    public int obtenerSegundos() {
        return this.hora.getSecond();
    }

    @Override
    public String toString() {
        return hora.toString();
    }
}