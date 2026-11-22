package modelo;

/** Fábrica de tratamientos (patrón Factory): centraliza la creación de cada tipo. */
public final class TratamientoFactory {

    private TratamientoFactory() { }

    /**
     * Crea el tratamiento del tipo indicado.
     *
     * @param tipo tipo de tratamiento; no puede ser nulo
     */
    public static Tratamiento crear(TipoTratamiento tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de tratamiento es obligatorio.");
        }
        return switch (tipo) {
            case CONSULTA -> new ConsultaGeneral();
            case LIMPIEZA -> new LimpiezaDental();
        };
    }
}