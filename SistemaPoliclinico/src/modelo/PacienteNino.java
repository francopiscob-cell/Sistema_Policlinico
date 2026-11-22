package modelo;

/** Paciente niño. Agrega su edad y el nombre de su apoderado. */
public class PacienteNino extends Paciente {
    private final int edad;
    private final String apoderado;

    public PacienteNino(String nombre, String dni, String telefono, int edad, String apoderado) {
        super(nombre, dni, telefono);
        if (edad < 0 || edad > 17) {
            throw new IllegalArgumentException("La edad de un paciente niño debe estar entre 0 y 17 años.");
        }
        if (apoderado == null || apoderado.isBlank()) {
            throw new IllegalArgumentException("El nombre del apoderado no puede estar vacío.");
        }
        this.edad = edad;
        this.apoderado = apoderado.trim();
    }

    public int getEdad() {
        return edad;
    }

    public String getApoderado() {
        return apoderado;
    }

    @Override
    public String getTipo() {
        return "Niño";
    }

    @Override
    public String obtenerResumen() {
        return toString() + " - Edad: " + edad + " - Apoderado: " + apoderado;
    }
}