package PilasResolver;

import javax.swing.JOptionPane;

public class interfazGrafica extends javax.swing.JFrame {
    PilaArray mipila = new PilaArray();

    public interfazGrafica() {
        mipila.vaciarPila();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        Pilas = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();
        IngresarDatos = new javax.swing.JButton();
        UltimoElemento = new javax.swing.JButton();
        EliminarDatos = new javax.swing.JButton();
        VaciarPilas = new javax.swing.JButton();
        MostrarPila = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrincipal.setBackground(new java.awt.Color(214, 217, 223));
        PanelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Pilas.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Pilas.setText("PILAS");
        PanelPrincipal.add(Pilas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        PanelPrincipal.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 120, 30));

        IngresarDatos.setText("Ingresar Datos");
        IngresarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarDatosActionPerformed(evt);
            }
        });
        PanelPrincipal.add(IngresarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 120, 30));

        UltimoElemento.setText("Ultimo Elemento");
        UltimoElemento.setMargin(new java.awt.Insets(2, 0, 2, 0));
        UltimoElemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UltimoElementoActionPerformed(evt);
            }
        });
        PanelPrincipal.add(UltimoElemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 120, 30));

        EliminarDatos.setText("Eliminar Datos");
        EliminarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarDatosActionPerformed(evt);
            }
        });
        PanelPrincipal.add(EliminarDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 120, 30));

        VaciarPilas.setText("Vaciar Pilas");
        VaciarPilas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VaciarPilasActionPerformed(evt);
            }
        });
        PanelPrincipal.add(VaciarPilas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 120, 30));

        MostrarPila.setText("Mostrar Pila");
        MostrarPila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarPilaActionPerformed(evt);
            }
        });
        PanelPrincipal.add(MostrarPila, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarDatosActionPerformed
        String dato = JOptionPane.showInputDialog("Ingrese el elemetno a agregar");
        mipila.insertarPila(Integer.parseInt(dato));
        
    }//GEN-LAST:event_IngresarDatosActionPerformed

    private void EliminarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarDatosActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro de eliminar un elemento?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            // El usuario seleccionó "Sí"
            mipila.eliminarPila();
        }
    }//GEN-LAST:event_EliminarDatosActionPerformed

    private void MostrarPilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarPilaActionPerformed
        JOptionPane.showMessageDialog(null, mipila.mostrarPila());
    }//GEN-LAST:event_MostrarPilaActionPerformed

    private void UltimoElementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UltimoElementoActionPerformed
        JOptionPane.showMessageDialog(null, mipila.ultimoPila());
    }//GEN-LAST:event_UltimoElementoActionPerformed

    private void VaciarPilasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VaciarPilasActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro de vaciar la pila?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            // El usuario seleccionó "Sí"
            mipila.vaciarPila();
        }
    }//GEN-LAST:event_VaciarPilasActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_SalirActionPerformed

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
            java.util.logging.Logger.getLogger(interfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfazGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EliminarDatos;
    private javax.swing.JButton IngresarDatos;
    private javax.swing.JButton MostrarPila;
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JLabel Pilas;
    private javax.swing.JButton Salir;
    private javax.swing.JButton UltimoElemento;
    private javax.swing.JButton VaciarPilas;
    // End of variables declaration//GEN-END:variables
}
