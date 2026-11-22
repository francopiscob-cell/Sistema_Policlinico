package modelo;

/** Tratamiento aplicable en una atención. Cada tipo calcula su propio costo. */
public abstract class Tratamiento {
    private final String nombre;

    protected Tratamiento(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del tratamiento no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getNombre() {
        return nombre;
    }

    /** Costo en soles; cada subclase lo calcula a su manera. */
    public abstract double calcularCosto();

    /** Descripción corta del tratamiento. */
    public abstract String getDescripcion();

    @Override
    public String toString() {
        return nombre + " (S/ " + String.format("%.2f", calcularCosto()) + ")";
    }
}