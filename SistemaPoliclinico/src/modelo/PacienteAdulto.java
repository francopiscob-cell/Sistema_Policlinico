package modelo;

/** Paciente adulto. Agrega su ocupación. */
public class PacienteAdulto extends Paciente {
    private final String ocupacion;

    public PacienteAdulto(String nombre, String dni, String telefono, String ocupacion) {
        super(nombre, dni, telefono);
        if (ocupacion == null || ocupacion.isBlank()) {
            throw new IllegalArgumentException("La ocupación no puede estar vacía.");
        }
        this.ocupacion = ocupacion.trim();
    }

    public String getOcupacion() {
        return ocupacion;
    }

    @Override
    public String getTipo() {
        return "Adulto";
    }

    @Override
    public String obtenerResumen() {
        return toString() + " - Ocupación: " + ocupacion;
    }
}