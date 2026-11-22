package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una reserva de atención médica: qué paciente será atendido,
 * por qué médico y en qué fecha y hora.
 *
 * Validaciones: el médico no puede ser nulo ni estar en blanco, y el paciente
 * y la fecha y hora son obligatorios.
 *
 * Los atributos son privados y finales, y no hay setters: una cita no se
 * modifica, se reprograma creando una nueva desde el gestor.
 */
public class Cita {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final String medico;
    private final Paciente paciente;
    private final LocalDateTime fechaHora;

    /**
     * Crea una cita validando los datos obligatorios.
     *
     * @param medico nombre del médico; no puede ser nulo ni estar en blanco
     * @param paciente paciente que será atendido; no puede ser nulo
     * @param fechaHora fecha y hora de la cita; no puede ser nula
     * @throws IllegalArgumentException si algún dato no cumple lo anterior
     */
    public Cita(String medico, Paciente paciente, LocalDateTime fechaHora) {
        if (medico == null || medico.isBlank()) {
            throw new IllegalArgumentException("El nombre del médico no puede estar vacío.");
        }
        if (paciente == null) {
            throw new IllegalArgumentException("La cita debe tener un paciente.");
        }
        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora de la cita son obligatorias.");
        }
        this.medico = medico.trim();
        this.paciente = paciente;
        this.fechaHora = fechaHora;
    }

    public String getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /** Dos citas chocan si son del mismo médico y a la misma fecha y hora. */
    public boolean chocaCon(Cita otra) {
        return medico.equalsIgnoreCase(otra.medico) && fechaHora.equals(otra.fechaHora);
    }

    @Override
    public String toString() {
        return "Cita - Médico: " + medico + " - Paciente: " + paciente.getNombre()
                + " - " + fechaHora.format(FORMATO);
    }
}