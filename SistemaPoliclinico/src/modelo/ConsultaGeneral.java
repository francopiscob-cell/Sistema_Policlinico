package modelo;

/** Consulta médica general con tarifa fija. */
public class ConsultaGeneral extends Tratamiento {
    private static final double COSTO = 60.0;

    public ConsultaGeneral() {
        super("Consulta general");
    }

    @Override
    public double calcularCosto() {
        return COSTO;
    }

    @Override
    public String getDescripcion() {
        return "Consulta médica general con tarifa fija.";
    }
}