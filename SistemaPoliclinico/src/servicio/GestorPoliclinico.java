package servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;
import modelo.CitaDuplicadaException;
import modelo.Paciente;
import modelo.Atencion;
import modelo.Tratamiento;

/**
 * Gestor central del policlínico (patrón Singleton): existe una única instancia
 * que guarda los pacientes y las citas, y aplica las reglas de negocio.
 */
public final class GestorPoliclinico {
    private static final GestorPoliclinico INSTANCIA = new GestorPoliclinico();

    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Cita> citas = new ArrayList<>();

    private GestorPoliclinico() { }

    public static GestorPoliclinico getInstancia() {
        return INSTANCIA;
    }

    /** Registra un paciente nuevo; rechaza DNI repetidos. */
    public void registrarPaciente(Paciente nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        boolean existe = pacientes.stream().anyMatch(p -> p.mismoDni(nuevo));
        if (existe) {
            throw new IllegalArgumentException("Ya existe un paciente registrado con ese DNI.");
        }
        pacientes.add(nuevo);
    }

    /** Programa una cita; lanza CitaDuplicadaException si el médico ya está ocupado. */
    public void programarCita(Cita nueva) throws CitaDuplicadaException {
        if (nueva == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
        verificarDisponibilidad(nueva, null);
        citas.add(nueva);
    }

    /** Cancela una cita; devuelve true si existía. */
    public boolean cancelarCita(Cita cita) {
        return citas.remove(cita);
    }

    /** Reprograma una cita creando una nueva con la fecha indicada. */
    public Cita reprogramarCita(Cita original, LocalDateTime nuevaFechaHora)
            throws CitaDuplicadaException {
        if (original == null || !citas.contains(original)) {
            throw new IllegalArgumentException("La cita a reprogramar no existe.");
        }
        Cita nueva = new Cita(original.getMedico(), original.getPaciente(), nuevaFechaHora);
        verificarDisponibilidad(nueva, original);
        citas.remove(original);
        citas.add(nueva);
        return nueva;
    }
    
    /** Registra la atención de una cita programada en la historia clínica del paciente. */
    public Atencion registrarAtencion(Cita cita, String diagnostico, List<Tratamiento> tratamientos) {
        if (cita == null || !citas.contains(cita)) {
            throw new IllegalArgumentException("La cita no está programada en el policlínico.");
        }
        Atencion atencion = new Atencion(cita, diagnostico, tratamientos);
        cita.getPaciente().registrarAtencion(atencion);
        return atencion;
}

    /** Verifica que no haya choque; la cita "ignorada" no se compara (caso de reprogramar). */
    private void verificarDisponibilidad(Cita nueva, Cita ignorada) throws CitaDuplicadaException {
        boolean choque = citas.stream()
                .filter(c -> c != ignorada)
                .anyMatch(c -> c.chocaCon(nueva));
        if (choque) {
            throw new CitaDuplicadaException(
                    "El médico " + nueva.getMedico() + " ya tiene una cita en ese horario.");
        }
    }

    /** Devuelve copias de solo lectura, para que nadie modifique las listas internas. */
    public List<Paciente> getPacientes() {
        return List.copyOf(pacientes);
    }

    public List<Cita> getCitas() {
        return List.copyOf(citas);
    }

    /** Borra todos los datos. Solo para uso en pruebas. */
    public void limpiarDatos() {
        pacientes.clear();
        citas.clear();
    }
}