package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una reserva de atención médica en el policlínico, registrando qué
 * paciente será atendido, por qué médico y en qué fecha y hora exacta.
 *
 * Validaciones: Al crearse, exige que el nombre del médico y del paciente no
 * sean nulos ni estén en blanco, y que la fecha y hora sean obligatorias; de lo
 * contrario, impide crear el objeto.
 *
 * Propiedad de la clase: Es inmutable; Una vez creada la cita, sus datos solo
 * se pueden leer (con los métodos get) y no se pueden cambiar, lo que evita que
 * se alteren por error.
 */
public class Cita {

    private final String medico;
    private final String paciente;
    private final LocalDateTime fechaHora;
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Crea una nueva cita médica validando que todos los datos obligatorios
     * sean correctos.
     *
     * @param medico Nombre del médico asignado; no puede ser nulo ni estar en
     * blanco.
     * @param paciente Nombre del paciente que asistirá; no puede ser nulo ni
     * estar en blanco.
     * @param fechaHora Fecha y hora programada para la cita; no puede ser nula.
     * @throws IllegalArgumentException Si el médico o el paciente son nulos o
     * están en blanco, o si la fecha y hora son nulas.
     *
     */
    public Cita(String medico, String paciente, LocalDateTime fechaHora) {
        // Validar que medico y paciente no sean null ni estén vacíos (o solo espacios)
        if (medico == null || medico.isBlank()) {
            throw new IllegalArgumentException("El nombre del médico no puede estar vacío.");
        }
        if (paciente == null || paciente.isBlank()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
        }
        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora de la cita son obligatorias.");
        }

        // Asignar los tres atributos.
        this.medico = medico;
        this.paciente = paciente;
        this.fechaHora = fechaHora;

    }

    //Getters de los tres atributos.
    public String getMedico() {
        return medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "Cita: " + "Médico: " + medico + " - " + "Paciente: " + paciente + " - " + fechaHora.format(FORMATO);
    }

}
