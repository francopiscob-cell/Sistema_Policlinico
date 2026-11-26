package modelo;

/**
 * Tipos de tratamiento que ofrece el policlínico.
 */
public enum TipoTratamiento {
    CONSULTA("Consulta general"),
    LIMPIEZA("Limpieza dental");

    private final String nombre;

    TipoTratamiento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
