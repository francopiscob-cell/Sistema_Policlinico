package modelo;

import util.Enmascarador;

/** Paciente del policlínico. Los datos sensibles nunca se exponen completos. */
public abstract class Paciente {
    private final String nombre;
    private final String dni;        // privado: no existe getter del DNI completo
    private String telefono;

    protected Paciente(String nombre, String dni, String telefono) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
        }
        Enmascarador.enmascararDni(dni);   // valida el formato
        Enmascarador.enmascararTelefono(telefono);
        this.nombre = nombre.trim();
        this.dni = dni;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDniEnmascarado() {
        return Enmascarador.enmascararDni(dni);
    }

    public String getTelefonoEnmascarado() {
        return Enmascarador.enmascararTelefono(telefono);
    }

    public void setTelefono(String telefono) {
        Enmascarador.enmascararTelefono(telefono);   // valida antes de asignar
        this.telefono = telefono;
    }

    /** Permite comparar un DNI sin necesidad de exponerlo. */
    public boolean tieneDni(String otroDni) {
        return dni.equals(otroDni);
    }
    
    /** Indica si otro paciente tiene el mismo DNI, sin exponerlo. */
    public boolean mismoDni(Paciente otro) {
        return otro != null && this.dni.equals(otro.dni);
    }
    
    public abstract String getTipo();

    public abstract String obtenerResumen();

    @Override
    public String toString() {
        return getTipo() + ": " + nombre + " - DNI " + getDniEnmascarado();
    }
}