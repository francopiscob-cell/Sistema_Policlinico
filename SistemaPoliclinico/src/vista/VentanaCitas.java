
package vista;

import modelo.Cita;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaCitas extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaCitas.class.getName());
    
    // Citas ficticias
     private final List<Cita> citas = List.of(
        new Cita("Dr. Ramírez", "Ana Torres", LocalDateTime.of(2026, 11, 23, 9, 0)),
        new Cita("Dra. Salazar", "Luis Medina", LocalDateTime.of(2026, 11, 23, 10, 30)),
        new Cita("Dr. Ramírez", "Carla Quispe", LocalDateTime.of(2026, 11, 23, 11, 0)),
        new Cita("Dra. Salazar", "Mateo Rojas", LocalDateTime.of(2026, 11, 24, 9, 30)),
        new Cita("Dr. Ramírez", "Elena Vargas", LocalDateTime.of(2026, 11, 24, 15, 0)),
        new Cita("Dra. Salazar", "Diego Paredes", LocalDateTime.of(2026, 11, 24, 16, 30))
    );
       
    public VentanaCitas() {
        initComponents();
        setTitle("Citas del policlínico");
        setLocationRelativeTo(null);
        mostrar(citas);
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
                    .addComponent(spResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMedico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMedico)
                        .addGap(18, 18, 18)
                        .addComponent(btnFiltrar)))
                .addContainerGap(51, Short.MAX_VALUE))
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
                .addComponent(spResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //HANDLER del boton
    
    private void txtMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedicoActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String texto = txtMedico.getText().trim();

        // Campo vacío: se muestran todas las citas.
        if (texto.isEmpty()) {
        mostrar(citas);
        return;
        }

        String criterio = normalizar(texto);

        // Paradigma funcional: filtra sin modificar la lista original.
        List<Cita> filtradas = citas.stream()
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
