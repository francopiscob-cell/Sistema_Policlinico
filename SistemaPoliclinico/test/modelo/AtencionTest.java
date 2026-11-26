package modelo;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Factory de tratamientos y del cálculo del costo de una atención.
 */
public class AtencionTest {

    @Test
    public void elFactoryCreaElTratamientoDelTipoPedido() {
        assertTrue(TratamientoFactory.crear(TipoTratamiento.CONSULTA) instanceof ConsultaGeneral);
        assertTrue(TratamientoFactory.crear(TipoTratamiento.LIMPIEZA) instanceof LimpiezaDental);
    }

    @Test
    public void elCostoTotalSumaElCostoDeCadaTratamiento() {
        Paciente ana = new PacienteAdulto("Ana Torres", "70000001", "900000001", "Docente");
        Cita cita = new Cita("Dr. Ramírez", ana, LocalDateTime.of(2026, 12, 1, 9, 0));
        Atencion atencion = new Atencion(cita, "Control", List.of(
                TratamientoFactory.crear(TipoTratamiento.CONSULTA),
                TratamientoFactory.crear(TipoTratamiento.LIMPIEZA)));
        assertEquals(140.0, atencion.getCostoTotal(), 0.001);
    }
}
