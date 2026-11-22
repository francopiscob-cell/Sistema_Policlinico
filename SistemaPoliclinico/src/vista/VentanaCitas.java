
package vista;

import modelo.Cita;
import modelo.CitaDuplicadaException;
import modelo.Paciente;
import modelo.PacienteAdulto;
import modelo.PacienteNino;
import servicio.GestorPoliclinico;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaCitas extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaCitas.class.getName());
    
    // Citas ficticias
     private final GestorPoliclinico gestor = GestorPoliclinico.getInstancia();
       
    public VentanaCitas() {
        initComponents();
        setTitle("Citas del policlínico");
        setLocationRelativeTo(null);
        cargarDatosDePrueba();
        mostrar(gestor.getCitas());
    }
    
    /** Muestra en el área de texto la lista de citas recibida, una por línea. */
    private void mostrar(List<Cita> lista) {
        areaResultado.setText("");
        for (Cita c : lista) {
            areaResultado.append(c + "\n");
        }
    }
    /** Quita tildes y pasa a minúsculas, para que "ramirez" encuentre a "Ramírez". */    
    private String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
    /** Carga pacientes y citas ficticias, solo si el gestor aún está vacío. */
    private void cargarDatosDePrueba() {
        if (!gestor.getCitas().isEmpty()) {
            return;
        }
        Paciente ana = new PacienteAdulto("Ana Torres", "70000001", "900000001", "Docente");
        Paciente luis = new PacienteAdulto("Luis Medina", "70000002", "900000002", "Contador");
        Paciente carla = new PacienteAdulto("Carla Quispe", "70000003", "900000003", "Diseñadora");
        Paciente mateo = new PacienteNino("Mateo Rojas", "70000004", "900000004", 8, "Rosa Rojas");
        Paciente elena = new PacienteAdulto("Elena Vargas", "70000005", "900000005", "Enfermera");
        Paciente diego = new PacienteNino("Diego Paredes", "70000006", "900000006", 12, "Pedro Paredes");

        try {
            for (Paciente p : List.of(ana, luis, carla, mateo, elena, diego)) {
                gestor.registrarPaciente(p);
            }
            gestor.programarCita(new Cita("Dr. Ramírez", ana, LocalDateTime.of(2026, 11, 23, 9, 0)));
            gestor.programarCita(new Cita("Dra. Salazar", luis, LocalDateTime.of(2026, 11, 23, 10, 30)));
            gestor.programarCita(new Cita("Dr. Ramírez", carla, LocalDateTime.of(2026, 11, 23, 11, 0)));
            gestor.programarCita(new Cita("Dra. Salazar", mateo, LocalDateTime.of(2026, 11, 24, 9, 30)));
            gestor.programarCita(new Cita("Dr. Ramírez", elena, LocalDateTime.of(2026, 11, 24, 15, 0)));
            gestor.programarCita(new Cita("Dra. Salazar", diego, LocalDateTime.of(2026, 11, 24, 16, 30)));
        } catch (CitaDuplicadaException e) {
            areaResultado.setText("Error al cargar los datos de prueba: " + e.getMessage());
        }
    }

    // CODIGO GENERADO POR NETBEANS
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMedico = new javax.swing.JLabel();
        txtMedico = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        spResultados = new javax.swing.JScrollPane();
        areaResultado = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMedico.setText("Médico:");

        txtMedico.addActionListener(this::txtMedicoActionPerformed);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(this::btnFiltrarActionPerformed);

        areaResultado.setEditable(false);
        areaResultado.setColumns(20);
        areaResultado.setLineWrap(true);
        areaResultado.setRows(5);
        areaResultado.setWrapStyleWord(true);
        spResultados.setViewportView(areaResultado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrar)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMedico)
                    .addComponent(txtMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addGap(18, 18, 18)
                .addComponent(spResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //HANDLER del boton
    
    private void txtMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedicoActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String texto = txtMedico.getText().trim();
        List<Cita> todas = gestor.getCitas();

        // Campo vacío: se muestran todas las citas.
        if (texto.isEmpty()) {
            mostrar(todas);
            return;
        }

        String criterio = normalizar(texto);

        // Paradigma funcional: filtra sin modificar la lista original.
        List<Cita> filtradas = todas.stream()
                .filter(c -> normalizar(c.getMedico()).contains(criterio))
                .collect(Collectors.toList());

        if (filtradas.isEmpty()) {
            areaResultado.setText("No se encontraron citas para: " + texto);
        } else {
            mostrar(filtradas);
        }
    }//GEN-LAST:event_btnFiltrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaCitas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaResultado;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JLabel lblMedico;
    private javax.swing.JScrollPane spResultados;
    private javax.swing.JTextField txtMedico;
    // End of variables declaration//GEN-END:variables
}
