package vista;

import modelo.Libro;
import modelo.Prestamo;
import controlador.BibliotecaController;
import javax.swing.SwingWorker;
import java.util.List;

public class ReportesForm extends javax.swing.JFrame {
    
    public ReportesForm() {
        initComponents();
        setLocationRelativeTo(null);
        cargarReportesAsync();
    }

private final BibliotecaController controller = new BibliotecaController();
private int tipoReporte = 0; // 0: prestamos activos, 1: mas prestados, 2: historial
private int filtroEstudiante = -1;

private void cargarReportesAsync() {


    btnActualizar.setEnabled(false);

    SwingWorker<Void, Void> worker = new SwingWorker<>() {

        private List<Libro> libros;
        private List<modelo.Estudiante> estudiantes;
        private List<Prestamo> prestamos;

        @Override
        protected Void doInBackground() {
            libros = controller.obtenerLibros();
            estudiantes = controller.obtenerEstudiantes();
            prestamos = controller.obtenerPrestamos();
            return null;
        }

        @Override
        protected void done() {
            try {
                get();

                int disponibles = 0;
                for (Libro libro : libros) {
                    disponibles += libro.getStock();
                }

                int activos = 0;
                int atrasados = 0;

                javax.swing.table.DefaultTableModel tabla;
                if (tipoReporte == 1) {
                    java.util.Map<Integer, Integer> conteos = new java.util.HashMap<>();
                    for (Prestamo p : prestamos) {
                        conteos.merge(p.getIdLibro(), 1, Integer::sum);
                    }
                    tabla = nuevaTabla("ID libro", "Titulo", "Veces prestado");
                    java.util.List<Libro> ordenados = new java.util.ArrayList<>(libros);
                    ordenados.sort((a, b) -> Integer.compare(
                            conteos.getOrDefault(b.getId(), 0), conteos.getOrDefault(a.getId(), 0)));
                    for (Libro libro : ordenados) {
                        tabla.addRow(new Object[]{libro.getId(), libro.getTitulo(),
                                conteos.getOrDefault(libro.getId(), 0)});
                    }
                } else {
                    tabla = nuevaTabla("ID", "Estudiante", "Libro", "Fecha prestamo",
                            "Fecha devolucion", "Estado");
                    for (Prestamo p : prestamos) {
                        if (!p.isDevuelto()) activos++;
                        if (p.estaAtrasado()) atrasados++;
                        if (tipoReporte == 0 && p.isDevuelto()) continue;
                        if (tipoReporte == 2 && filtroEstudiante >= 0
                                && p.getIdEstudiante() != filtroEstudiante) continue;
                        String estado = p.isDevuelto() ? "Devuelto"
                                : (p.estaAtrasado() ? "Atrasado" : "Activo");
                        tabla.addRow(new Object[]{p.getId(), p.getIdEstudiante(),
                                p.getIdLibro(), p.getFechaPrestamo(),
                                p.getFechaDevolucion(), estado});
                    }
                }
                if (tipoReporte == 1) {
                    for (Prestamo p : prestamos) {
                        if (!p.isDevuelto()) activos++;
                        if (p.estaAtrasado()) atrasados++;
                    }
                }
                lblTitulo.setText(tipoReporte == 1 ? "Libros mas prestados"
                        : tipoReporte == 2 ? "Historial por estudiante" : "Libros en prestamo");

                lblTotalLibros.setText(
                        "Ejemplares disponibles: " + disponibles);

                lblTotalEstudiantes.setText(
                        "Total de estudiantes: " + estudiantes.size());

                lblPrestamosActivos.setText(
                        "Prestamos activos: " + activos);

                lblPrestamosAtrasados.setText(
                        "Prestamos atrasados: " + atrasados);

                tblReportes.setModel(tabla);

            } 
            catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(
                        ReportesForm.this,
                        "Error al cargar reportes: " + e.getMessage());
            } 
            finally {
                btnActualizar.setEnabled(true);
            }
        }
    };

    worker.execute();
}

private javax.swing.table.DefaultTableModel nuevaTabla(
        String... columnas) {

    return new javax.swing.table.DefaultTableModel(
            columnas, 0) {

        @Override
        public boolean isCellEditable(
                int fila, int columna) {
            return false;
        }
    };
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblReportes = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        lblTotalLibros = new javax.swing.JLabel();
        lblTotalEstudiantes = new javax.swing.JLabel();
        lblPrestamosActivos = new javax.swing.JLabel();
        lblPrestamosAtrasados = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblReportes);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTitulo.setText("Reportes de biblioteca");

        lblTotalLibros.setText("Total de libros: 0");

        lblTotalEstudiantes.setText("Total de estudiantes: 0");

        lblPrestamosActivos.setText("Prestamos activos: 0");

        lblPrestamosAtrasados.setText("Préstamos atrasados: 0");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(121, 121, 121)
                                    .addComponent(lblTitulo))
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTotalLibros)
                                        .addComponent(lblTotalEstudiantes))
                                    .addGap(44, 44, 44)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPrestamosAtrasados)
                                        .addComponent(lblPrestamosActivos))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(btnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVolver)))
                        .addGap(0, 227, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalLibros)
                    .addComponent(lblPrestamosActivos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalEstudiantes)
                    .addComponent(lblPrestamosAtrasados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(btnActualizar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
cargarReportesAsync();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReportesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportesForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPrestamosActivos;
    private javax.swing.JLabel lblPrestamosAtrasados;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotalEstudiantes;
    private javax.swing.JLabel lblTotalLibros;
    private javax.swing.JTable tblReportes;
    // End of variables declaration//GEN-END:variables
}
