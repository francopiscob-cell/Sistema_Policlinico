package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Historial de atenciones de un paciente. Agregar atenciones solo es posible
 * desde el paquete modelo, es decir, a través de Paciente.
 */
public class HistoriaClinica {
    private final List<Atencion> atenciones = new ArrayList<>();

    /** Agrega una atención; rechaza registrar dos veces la misma cita. */
    void agregar(Atencion atencion) {
        boolean yaRegistrada = atenciones.stream()
                .anyMatch(a -> a.getCita() == atencion.getCita());
        if (yaRegistrada) {
            throw new IllegalArgumentException("Ya se registró la atención de esta cita.");
        }
        atenciones.add(atencion);
    }

    /** Copia de solo lectura de las atenciones. */
    public List<Atencion> getAtenciones() {
        return List.copyOf(atenciones);
    }

    public double getCostoTotal() {
        return atenciones.stream()
                .mapToDouble(Atencion::getCostoTotal)
                .sum();
    }
}