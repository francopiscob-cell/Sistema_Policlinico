package modelo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Atención médica realizada a partir de una cita: diagnóstico y tratamientos aplicados.
 * Es inmutable: sus datos no cambian después de creada.
 */
public class Atencion {
    private final Cita cita;
    private final String diagnostico;
    private final List<Tratamiento> tratamientos;

    public Atencion(Cita cita, String diagnostico, List<Tratamiento> tratamientos) {
        if (cita == null) {
            throw new IllegalArgumentException("La atención debe estar asociada a una cita.");
        }
        if (diagnostico == null || diagnostico.isBlank()) {
            throw new IllegalArgumentException("El diagnóstico no puede estar vacío.");
        }
        if (tratamientos == null || tratamientos.isEmpty()) {
            throw new IllegalArgumentException("Una atención debe incluir al menos un tratamiento.");
        }
        this.cita = cita;
        this.diagnostico = diagnostico.trim();
        this.tratamientos = List.copyOf(tratamientos);
    }

    public Cita getCita() {
        return cita;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    /** Suma el costo de todos los tratamientos; cada uno calcula el suyo. */
    public double getCostoTotal() {
        return tratamientos.stream()
                .mapToDouble(Tratamiento::calcularCosto)
                .sum();
    }

    @Override
    public String toString() {
        String lista = tratamientos.stream()
                .map(Tratamiento::toString)
                .collect(Collectors.joining(", "));
        return "Atención - " + cita + " - Diagnóstico: " + diagnostico
                + " - Tratamientos: " + lista
                + " - Total: S/ " + String.format("%.2f", getCostoTotal());
    }
}