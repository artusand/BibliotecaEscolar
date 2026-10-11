package vista;

import modelo.Usuario;
import interfaces.Administrable;
import dao.PrestamoDAO;
import dao.EstudianteDAO;
import dao.LibroDAO;
import modelo.Prestamo;
import modelo.Estudiante;
import modelo.Libro;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PrestamosForm extends javax.swing.JFrame {

    /**
     * Creates new form PrestamosForm
     */

public PrestamosForm(Usuario usuario) {
    initComponents();
    setLocationRelativeTo(null);

    txtDias.setText("7");
    actualizarDatos();

    boolean esAdministrador =
            usuario instanceof Administrable
            && ((Administrable) usuario).puedeAdministrar();

    btnPrestar.setEnabled(esAdministrador);
    btnDevolver.setEnabled(esAdministrador);

    cmbEstudiante.setEnabled(esAdministrador);
    cmbLibro.setEnabled(esAdministrador);
    txtDias.setEnabled(esAdministrador);
}



private final PrestamoDAO prestamoDAO = new PrestamoDAO();
private final EstudianteDAO estudianteDAO = new EstudianteDAO();
private final LibroDAO libroDAO = new LibroDAO();

private void cargarEstudiantes() {
    cmbEstudiante.removeAllItems();

    for (Estudiante e : estudianteDAO.listar()) {
        cmbEstudiante.addItem(e.getId() + " - " + e.getNombre());
    }
}

private void cargarLibros() {
    cmbLibro.removeAllItems();

    for (Libro libro : libroDAO.listar()) {
        if (libro.getStock() > 0) {
            cmbLibro.addItem(libro.getId() + " - "
                    + libro.getTitulo()
                    + " (Stock: " + libro.getStock() + ")");
        }
    }
}

private int obtenerIdCombo(String seleccion) {
    if (seleccion == null) {
        throw new IllegalArgumentException("Seleccione una opcion");
    }

    return Integer.parseInt(seleccion.split(" - ", 2)[0]);
}

private void cargarPrestamos() {
    DefaultTableModel modelo = new DefaultTableModel(
        new Object[]{
            "ID", "Estudiante", "Libro", "Fecha prestamo",
            "Fecha devolucion", "Devuelto", "Atrasado"
        }, 0
    ) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };

    for (Prestamo p : prestamoDAO.listar()) {
        modelo.addRow(new Object[]{
            p.getId(),
            p.getIdEstudiante(),
            p.getIdLibro(),
            p.getFechaPrestamo(),
            p.getFechaDevolucion(),
            p.isDevuelto() ? "Si" : "No",
            p.estaAtrasado() ? "Si" : "No"
        });
    }

    tblPrestamos.setModel(modelo);
}

private void actualizarDatos() {
    cargarEstudiantes();
    cargarLibros();
    cargarPrestamos();
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        cmbEstudiante = new javax.swing.JComboBox<>();
        cmbLibro = new javax.swing.JComboBox<>();
        txtDias = new javax.swing.JTextField();
        btnPrestar = new javax.swing.JButton();
        btnDevolver = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPrestamos);

        txtDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasActionPerformed(evt);
            }
        });

        btnPrestar.setText("Registrar prestamo");
        btnPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestarActionPerformed(evt);
            }
        });

        btnDevolver.setText("Devolver libro");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar tabla");
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

        jLabel1.setText("Estudiante");

        jLabel2.setText("Libro");

        jLabel3.setText("Dias de prestamo");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Gestion de prestamos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnPrestar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDevolver))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizar)
                            .addComponent(btnVolver))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cmbEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrestar)
                    .addComponent(btnActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDevolver)
                    .addComponent(btnVolver))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasActionPerformed

    private void btnPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestarActionPerformed

        try {
            int idEstudiante = obtenerIdCombo((String) cmbEstudiante.getSelectedItem());
            int idLibro = obtenerIdCombo((String) cmbLibro.getSelectedItem());
            int dias = Integer.parseInt(txtDias.getText().trim());
            if (dias <= 0) {
                throw new IllegalArgumentException("Los dias deben ser mayores que cero");
            }
            LocalDate inicio = LocalDate.now();
            Prestamo prestamo = new Prestamo(0, idEstudiante, idLibro,
                    inicio, inicio.plusDays(dias), false);
            btnPrestar.setEnabled(false);
            new javax.swing.SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() {
                    return prestamoDAO.registrarPrestamo(prestamo);
                }
                @Override
                protected void done() {
                    try {
                        boolean ok = get();
                        JOptionPane.showMessageDialog(PrestamosForm.this,
                                ok ? "Prestamo registrado correctamente" : "No se pudo registrar el prestamo");
                        if (ok) actualizarDatos();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(PrestamosForm.this,
                                "Error al registrar prestamo: " + e.getMessage());
                    } finally {
                        btnPrestar.setEnabled(true);
                    }
                }
            }.execute();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un numero valido de dias");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btnPrestarActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed

        int fila = tblPrestamos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
            this, "Seleccione un prestamo");
            return;
}

int idPrestamo = Integer.parseInt(
        tblPrestamos.getValueAt(fila, 0).toString());

int respuesta = JOptionPane.showConfirmDialog(
        this,
        "¿Confirmar devolucion del libro?",
        "Devolucion",
        JOptionPane.YES_NO_OPTION
);

if (respuesta == JOptionPane.YES_OPTION) {
    if (prestamoDAO.devolverPrestamo(idPrestamo)) {
        JOptionPane.showMessageDialog(
                this, "Libro devuelto correctamente");
        actualizarDatos();
    } else {
        JOptionPane.showMessageDialog(
                this, "No se pudo devolver el libro. "
                + "Puede estar devuelto anteriormente.");
    }
}

    }//GEN-LAST:event_btnDevolverActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
actualizarDatos();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
dispose();
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnPrestar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbEstudiante;
    private javax.swing.JComboBox<String> cmbLibro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPrestamos;
    private javax.swing.JTextField txtDias;
    // End of variables declaration//GEN-END:variables
}
