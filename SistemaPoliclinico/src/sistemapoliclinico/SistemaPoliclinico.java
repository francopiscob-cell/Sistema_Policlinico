package sistemapoliclinico;

import vista.VentanaPrincipal;

/** Punto de entrada del sistema: abre la ventana principal del policlínico. */
public class SistemaPoliclinico {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}