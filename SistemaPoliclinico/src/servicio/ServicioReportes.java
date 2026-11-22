package servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import modelo.Atencion;
import modelo.Cita;

/**
 * Genera los reportes de atención del policlínico (RF-05).
 * Usa programación funcional: agrupa, transforma y suma colecciones sin modificarlas.
 */
public final class ServicioReportes {
    private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");

    private ServicioReportes() { }

    /** Agrupa las citas por día, ordenadas por fecha. */
    public static Map<LocalDate, List<Cita>> citasPorDia(List<Cita> citas) {
        return citas.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getFechaHora().toLocalDate(),
                        TreeMap::new,
                        Collectors.toList()));
    }

    /** Cuenta las citas de cada médico, ordenado por nombre. */
    public static Map<String, Long> cantidadCitasPorMedico(List<Cita> citas) {
        return citas.stream()
                .collect(Collectors.groupingBy(
                        Cita::getMedico,
                        TreeMap::new,
                        Collectors.counting()));
    }

    /** Suma lo facturado por cada médico en sus atenciones. */
    public static Map<String, Double> ingresosPorMedico(List<Atencion> atenciones) {
        return atenciones.stream()
                .collect(Collectors.groupingBy(
                        a -> a.getCita().getMedico(),
                        TreeMap::new,
                        Collectors.summingDouble(Atencion::getCostoTotal)));
    }

    /** Suma el total facturado de todas las atenciones usando reduce. */
    public static double totalIngresos(List<Atencion> atenciones) {
        return atenciones.stream()
                .map(Atencion::getCostoTotal)
                .reduce(0.0, Double::sum);
    }

    /** Reporte de citas por día: cada día con sus citas ordenadas por hora. */
    public static String reportePorDia(List<Cita> citas) {
        if (citas.isEmpty()) {
            return "No hay citas registradas.";
        }
        return citasPorDia(citas).entrySet().stream()
                .map(dia -> dia.getKey().format(FORMATO_DIA) + " - Citas: " + dia.getValue().size() + "\n"
                        + dia.getValue().stream()
                                .sorted(Comparator.comparing(Cita::getFechaHora))
                                .map(c -> "   - " + c.getFechaHora().format(FORMATO_HORA) + " "
                                        + c.getMedico() + " - " + "Paciente: " + c.getPaciente().getNombre())
                                .collect(Collectors.joining("\n")))
                .collect(Collectors.joining("\n\n"));
    }

    /** Reporte por médico: cantidad de citas, lo facturado y el total general. */
    public static String reportePorMedico(List<Cita> citas, List<Atencion> atenciones) {
        Map<String, Long> cantidades = cantidadCitasPorMedico(citas);
        if (cantidades.isEmpty()) {
            return "No hay citas registradas.";
        }
        Map<String, Double> ingresos = ingresosPorMedico(atenciones);
        String lineas = cantidades.entrySet().stream()
                .map(m -> m.getKey() + " - Citas: " + m.getValue()
                        + " | Facturado: S/ " + String.format("%.2f", ingresos.getOrDefault(m.getKey(), 0.0)))
                .collect(Collectors.joining("\n"));
        return lineas + "\nTotal facturado: S/ " + String.format("%.2f", totalIngresos(atenciones));
    }
}