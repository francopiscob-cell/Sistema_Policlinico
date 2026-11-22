package modelo;

/** Limpieza dental con tarifa fija. */
public class LimpiezaDental extends Tratamiento {
    private static final double COSTO = 80.0;

    public LimpiezaDental() {
        super("Limpieza dental");
    }

    @Override
    public double calcularCosto() {
        return COSTO;
    }

    @Override
    public String getDescripcion() {
        return "Limpieza dental con tarifa fija.";
    }
}