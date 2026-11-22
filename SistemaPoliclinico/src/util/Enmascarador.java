
package util;

/** Oculta datos sensibles para mostrarlos sin exponerlos (Ley N.° 29733). */

public final class Enmascarador {
    
    private Enmascarador() { }

    /** Deja visibles solo los últimos 3 dígitos del DNI. */
    public static String enmascararDni(String dni) {
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos.");
        }
        return "*****" + dni.substring(5);
    }

    /** Deja visibles solo los últimos 3 dígitos del teléfono. */
    public static String enmascararTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{9}")) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
        }
        return "******" + telefono.substring(6);
    }
    
}
