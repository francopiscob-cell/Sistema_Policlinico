package modelo;

/**
 * Médico del policlínico y el servicio que atiende.
 */
public final class Medico {

    private final String nombre;
    private final TipoTratamiento especialidad;

    public Medico(String nombre, TipoTratamiento especialidad) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del médico no puede estar vacío.");
        }
        if (especialidad == null) {
            throw new IllegalArgumentException("El médico debe tener una especialidad.");
        }
        this.nombre = nombre.trim();
        this.especialidad = especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoTratamiento getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return nombre + " - " + especialidad.getNombre();
    }
}
