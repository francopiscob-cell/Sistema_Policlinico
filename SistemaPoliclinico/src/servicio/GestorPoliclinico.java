package servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;
import modelo.CitaDuplicadaException;
import modelo.Paciente;
import modelo.Atencion;
import modelo.Tratamiento;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Optional;
import modelo.Medico;
import modelo.TipoTratamiento;

/**
 * Gestor central del policlínico (patrón Singleton): existe una única instancia
 * que guarda los pacientes y las citas, y aplica las reglas de negocio.
 */
public final class GestorPoliclinico {

    private static final GestorPoliclinico INSTANCIA = new GestorPoliclinico();

    /**
     * Médicos del policlínico y el servicio que atiende cada uno.
     */
    private static final List<Medico> MEDICOS = List.of(
            new Medico("Dr. Ramírez", TipoTratamiento.CONSULTA),
            new Medico("Dra. Salazar", TipoTratamiento.LIMPIEZA));
    private final List<Paciente> pacientes = new ArrayList<>();
    private final List<Cita> citas = new ArrayList<>();

    private GestorPoliclinico() {
    }

    public static GestorPoliclinico getInstancia() {
        return INSTANCIA;
    }

    /**
     * Registra un paciente nuevo; rechaza DNI repetidos.
     */
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

    /**
     * Programa una cita; lanza CitaDuplicadaException si el médico ya está
     * ocupado.
     */
    public void programarCita(Cita nueva) throws CitaDuplicadaException {
        if (nueva == null) {
            throw new IllegalArgumentException("La cita no puede ser nula.");
        }
        verificarDisponibilidad(nueva, null);
        citas.add(nueva);
    }

    /**
     * Cancela una cita; devuelve true si existía. No permite cancelar una cita
     * ya atendida.
     */
    public boolean cancelarCita(Cita cita) {
        if (cita != null && estaAtendida(cita)) {
            throw new IllegalArgumentException("No se puede cancelar una cita que ya fue atendida.");
        }
        return citas.remove(cita);
    }

    /**
     * Reprograma una cita creando una nueva con la fecha indicada.
     */
    public Cita reprogramarCita(Cita original, LocalDateTime nuevaFechaHora)
            throws CitaDuplicadaException {
        if (original == null || !citas.contains(original)) {
            throw new IllegalArgumentException("La cita a reprogramar no existe.");
        }
        if (estaAtendida(original)) {
            throw new IllegalArgumentException("No se puede reprogramar una cita que ya fue atendida.");
        }
        Cita nueva = new Cita(original.getMedico(), original.getPaciente(), nuevaFechaHora);
        verificarDisponibilidad(nueva, original);
        citas.remove(original);
        citas.add(nueva);
        return nueva;
    }

    /**
     * Registra la atención de una cita programada en la historia clínica del
     * paciente.
     */
    public Atencion registrarAtencion(Cita cita, String diagnostico, List<Tratamiento> tratamientos) {
        if (cita == null || !citas.contains(cita)) {
            throw new IllegalArgumentException("La cita no está programada en el policlínico.");
        }
        Atencion atencion = new Atencion(cita, diagnostico, tratamientos);
        cita.getPaciente().registrarAtencion(atencion);
        return atencion;
    }

    /**
     * Indica si la cita ya tiene su atención registrada en la historia clínica.
     */
    public boolean estaAtendida(Cita cita) {
        return cita.getPaciente().getAtenciones().stream()
                .anyMatch(a -> a.getCita() == cita);
    }

    /**
     * Verifica que no haya choque; la cita "ignorada" no se compara (caso de
     * reprogramar).
     */
    private void verificarDisponibilidad(Cita nueva, Cita ignorada) throws CitaDuplicadaException {
        boolean choque = citas.stream()
                .filter(c -> c != ignorada)
                .anyMatch(c -> c.chocaCon(nueva));
        if (choque) {
            throw new CitaDuplicadaException(
                    "El médico " + nueva.getMedico() + " ya tiene una cita en ese horario.");
        }
    }

    /**
     * Devuelve copias de solo lectura, para que nadie modifique las listas
     * internas.
     */
    public List<Paciente> getPacientes() {
        return List.copyOf(pacientes);
    }

    public List<Cita> getCitas() {
        return List.copyOf(citas);
    }

    /**
     * Reúne las atenciones de todos los pacientes en una sola lista de solo
     * lectura.
     */
    public List<Atencion> getAtenciones() {
        return pacientes.stream()
                .flatMap(p -> p.getAtenciones().stream())
                .collect(Collectors.toList());

    }

    /**
     * Busca un paciente por su DNI sin exponerlo.
     */
    public Optional<Paciente> buscarPacientePorDni(String dni) {
        return pacientes.stream()
                .filter(p -> p.tieneDni(dni))
                .findFirst();
    }

    /**
     * Citas de un paciente, ordenadas por fecha y hora.
     */
    public List<Cita> getCitasDePaciente(Paciente paciente) {
        return citas.stream()
                .filter(c -> c.getPaciente().mismoDni(paciente))
                .sorted(Comparator.comparing(Cita::getFechaHora))
                .collect(Collectors.toList());
    }

    /**
     * Médicos sin cita en la fecha y hora indicadas.
     */
    public List<Medico> getMedicosDisponibles(LocalDateTime fechaHora) {
        return MEDICOS.stream()
                .filter(m -> citas.stream().noneMatch(
                        c -> c.getMedico().equalsIgnoreCase(m.getNombre()) && c.getFechaHora().equals(fechaHora)))
                .collect(Collectors.toList());
    }

    /**
     * Busca un médico por su nombre.
     */
    public Optional<Medico> buscarMedico(String nombre) {
        return MEDICOS.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    /**
     * Borra todos los datos. Solo para uso en pruebas.
     */
    public void limpiarDatos() {
        pacientes.clear();
        citas.clear();
    }
}
