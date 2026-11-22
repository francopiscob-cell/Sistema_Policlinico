
package modelo;

/** Se lanza cuando un médico ya tiene una cita en el mismo horario. */
public class CitaDuplicadaException extends Exception {

    public CitaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}