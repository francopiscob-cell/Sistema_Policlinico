package util;

import modelo.Paciente;
import modelo.PacienteAdulto;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas de la protección de datos personales (Ley N.° 29733).
 */
public class EnmascaradorTest {

    @Test
    public void enmascaraElDniDejandoSoloLosUltimosTresDigitos() {
        assertEquals("*****678", Enmascarador.enmascararDni("12345678"));
    }

    @Test
    public void rechazaUnDniQueNoTieneOchoDigitos() {
        assertThrows(IllegalArgumentException.class, () -> Enmascarador.enmascararDni("1234567"));
    }

    @Test
    public void elPacienteNuncaMuestraSuDniCompleto() {
        Paciente ana = new PacienteAdulto("Ana Torres", "70000001", "900000001", "Docente");
        assertFalse(ana.toString().contains("70000001"));
        assertFalse(ana.obtenerResumen().contains("70000001"));
        assertEquals("*****001", ana.getDniEnmascarado());
    }
}
