package servicio;

import java.time.LocalDateTime;
import java.util.List;
import modelo.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/** Pruebas de las reglas de negocio del gestor del policlínico. */
public class GestorPoliclinicoTest {

    private GestorPoliclinico gestor;
    private Paciente ana;
    private Paciente luis;
    private final LocalDateTime horario = LocalDateTime.of(2026, 12, 1, 9, 0);

    @Before
    public void preparar() {
        gestor = GestorPoliclinico.getInstancia();
        gestor.limpiarDatos();   // el Singleton conserva datos entre pruebas
        ana = new PacienteAdulto("Ana Torres", "70000001", "900000001", "Docente");
        luis = new PacienteAdulto("Luis Medina", "70000002", "900000002", "Contador");
        gestor.registrarPaciente(ana);
        gestor.registrarPaciente(luis);
    }

    @Test
    public void rechazaCitaDuplicadaDelMismoMedicoYHorario() throws CitaDuplicadaException {
        gestor.programarCita(new Cita("Dr. Ramírez", ana, horario));
        assertThrows(CitaDuplicadaException.class,
                () -> gestor.programarCita(new Cita("Dr. Ramírez", luis, horario)));
    }

    @Test
    public void permiteElMismoHorarioConOtroMedico() throws CitaDuplicadaException {
        gestor.programarCita(new Cita("Dr. Ramírez", ana, horario));
        gestor.programarCita(new Cita("Dra. Salazar", luis, horario));
        assertEquals(2, gestor.getCitas().size());
    }

    @Test
    public void rechazaPacienteConDniRepetido() {
        Paciente repetido = new PacienteAdulto("Otra Persona", "70000001", "900000009", "Chofer");
        assertThrows(IllegalArgumentException.class, () -> gestor.registrarPaciente(repetido));
    }

    @Test
    public void noPermiteCancelarUnaCitaYaAtendida() throws CitaDuplicadaException {
        Cita cita = new Cita("Dr. Ramírez", ana, horario);
        gestor.programarCita(cita);
        gestor.registrarAtencion(cita, "Control",
                List.of(TratamientoFactory.crear(TipoTratamiento.CONSULTA)));
        assertThrows(IllegalArgumentException.class, () -> gestor.cancelarCita(cita));
    }

    @Test
    public void noPermiteReprogramarUnaCitaYaAtendida() throws CitaDuplicadaException {
        Cita cita = new Cita("Dr. Ramírez", ana, horario);
        gestor.programarCita(cita);
        gestor.registrarAtencion(cita, "Control",
                List.of(TratamientoFactory.crear(TipoTratamiento.CONSULTA)));
        assertThrows(IllegalArgumentException.class,
                () -> gestor.reprogramarCita(cita, horario.plusDays(1)));
    }

    @Test
    public void reprogramarLiberaElHorarioAnterior() throws CitaDuplicadaException {
        Cita cita = new Cita("Dr. Ramírez", ana, horario);
        gestor.programarCita(cita);
        gestor.reprogramarCita(cita, horario.plusDays(1));
        gestor.programarCita(new Cita("Dr. Ramírez", luis, horario));   // el horario quedó libre
        assertEquals(2, gestor.getCitas().size());
    }
}