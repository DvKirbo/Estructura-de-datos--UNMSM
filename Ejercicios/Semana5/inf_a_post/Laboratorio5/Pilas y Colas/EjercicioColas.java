package Semana5;

import java.text.DecimalFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EjercicioColas extends javax.swing.JFrame {

    public class Nodo{
        String codigo;
        String nombre;
        String apellidos;
        String sexo;
        float sueldo;
        Nodo sig;
        
        public Nodo(String cod, String nom, String ape, String sex, float suel){
            codigo=cod;
            nombre=nom;
            apellidos=ape;
            sexo=sex;
            sueldo=suel;
            sig=null;
        }
    }
    
    DefaultTableModel miModelo;
    String[] cabecera={"N°","Codigo","Nombres","Apellidos","Sexo","Sueldo"};
    String[][] data={};
    
    public Nodo frente, fincola;
    public Nodo pFound;
    int num=0, tam;
    
    public EjercicioColas() {
        initComponents();
        fincola=null;
        tam=0;
        miModelo = new DefaultTableModel(data,cabecera);
        tblRegistros.setModel(miModelo);
        sexo.removeAllItems();
        sexo.addItem("Masculino");
        sexo.addItem("Femenino");
    }
    
    private void mensaje(String data){
        StringTokenizer st = new StringTokenizer(data, ",");
        
        String c=st.nextToken();
        String n=st.nextToken();
        String a=st.nextToken();
        String s=st.nextToken();
        String su=st.nextToken();
        String datos="Descripción del dato eliminado: \n"+
                     "Codigo        : "+c+"\n"+
                     "Nombre        : "+n+"\n"+
                     "Apellidos     : "+a+"\n"+
                     "Sexo          : "+s+"\n"+
                     "Sueldo        : "+su+"\n";
        JOptionPane.showMessageDialog(this, datos);
    }
    
    private void encolar(String cod, String nom, String ape, String sex, float suel){
        Nodo nuevo=new Nodo(cod,nom,ape,sex,suel);
        
        if(frente==null){
            frente=nuevo;
            fincola=nuevo;
        } else {
            fincola.sig=nuevo;
            fincola=nuevo;
        }
    }
    
    private String frente(){
        String eliminado="";
        Nodo aux=frente;
        String c=aux.codigo;
        String n=aux.nombre;
        String a=aux.apellidos;
        String s=aux.sexo;
        float su=aux.sueldo;
        eliminado=c+","+n+","+a+","+s+","+String.valueOf(su);
        frente=frente.sig;
        //aux.sig=null;
        
        return eliminado;
    }
    
    private Nodo Buscar(Nodo tope, String cod){
        Nodo pos=frente;
        while(pos!=null && !cod.equalsIgnoreCase(pos.codigo)) pos=pos.sig;
        
        return pos;
    }
    
    void VerDatos(){
        String cod,nom,ape,se,su;
        Nodo aux=frente;
        vaciar_tabla();
        num=0;
        while(aux!=null){
            cod=aux.codigo;
            nom=aux.nombre;
            ape=aux.apellidos;
            se=aux.sexo;
            DecimalFormat df2 = new DecimalFormat("####.00");
            su = df2.format(aux.sueldo);
            num++;
            Object[] fila = {num,cod,nom,ape,se,su};
            miModelo.addRow(fila);
            aux=aux.sig;
        }
        txtTamanio.setText(String.valueOf(num));
    }

    void Resumen(){
        String nom = "", acum="";
        float suma=0, mayor=-9999;
        float s;
        Nodo p=frente;
        while(p!=null){
            s=p.sueldo;
            if(s>mayor){
                mayor=s;
                nom=p.nombre+" "+p.apellidos;
            }
            suma=suma+s;
            p=p.sig;
        }
        txtEmpleadoMayor.setText(nom);
        
        DecimalFormat df2 = new DecimalFormat("####.00");
        acum = df2.format(suma);
        txtMontoAcum.setText(acum);
    }
    
    void Habilitar(){
        btnActualizar.setEnabled(true);
        btnGuardar.setEnabled(true);
    }
    
    void Deshabilitar(){
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }
    
    void LimpiarEntradas(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtSueldo.setText("");
        txtApellido.setText("");
        sexo.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }
    
    void vaciar_tabla(){
        int filas=tblRegistros.getRowCount();
        for(int i=0;i<filas;i++){
            miModelo.removeRow(0);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtSueldo = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnReestaurar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmpleadoMayor = new javax.swing.JTextField();
        txtMontoAcum = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTamanio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Codigo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel3.setText("Apellidos");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel4.setText("Sexo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel5.setText("Sueldo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel6.setText("Datos del empleado");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 150, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 150, -1));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 150, -1));
        jPanel1.add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 150, -1));

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 150, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 270, 200));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, 30));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 100, 30));

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        jPanel2.add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 90, 30));

        btnReestaurar.setText("Reestaurar");
        btnReestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReestaurarActionPerformed(evt);
            }
        });
        jPanel2.add(btnReestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 100, 30));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, 30));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 100, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 260, 170));

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(tblRegistros);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 670, 230));

        jPanel3.setBackground(new java.awt.Color(0, 51, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Empleado con el mayor sueldo");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Montos de sueldos acumulados");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));
        jPanel3.add(txtEmpleadoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 210, -1));
        jPanel3.add(txtMontoAcum, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 220, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 500, 90));

        jPanel4.setBackground(new java.awt.Color(0, 51, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tamaño");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel4.add(txtTamanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 90, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, 140, 90));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cod=txtCodigo.getText();
        String nom=txtNombre.getText().toUpperCase();
        String ape=txtApellido.getText().toUpperCase();
        String sex=sexo.getSelectedItem().toString();
        String suel=txtSueldo.getText();
        
        encolar(cod,nom,ape,sex,Float.parseFloat(suel));
        tam=tam+1;
        LimpiarEntradas();
        VerDatos();
        Resumen();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        LimpiarEntradas();
        Deshabilitar();
        VerDatos();
        Resumen();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        String cod=txtCodigo.getText();
        if(cod.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Ingrese un codigo por favor");
        }else{
            pFound=Buscar(fincola,cod);
            if(pFound!=null){
                txtNombre.setText(pFound.nombre);
                txtApellido.setText(pFound.apellidos);
                if(pFound.sexo.equalsIgnoreCase("MASCULINO")){
                    sexo.setSelectedIndex(2);
                }else{
                    sexo.setSelectedIndex(1);
                }
                txtSueldo.setText(String.valueOf(pFound.sueldo));
                Habilitar();
            }else{
                JOptionPane.showMessageDialog(this, "El codigo: "+cod+", no está en la pila..");
            }
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnReestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReestaurarActionPerformed
        LimpiarEntradas();
        Deshabilitar();
    }//GEN-LAST:event_btnReestaurarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(frente==null){
            JOptionPane.showMessageDialog(this, "La Pila está vacía");
        }else{
            String dato=frente();
            mensaje(dato);
            LimpiarEntradas();
            VerDatos();
            if(frente==null){
                JOptionPane.showMessageDialog(this, "La pila esta vacía");
            }
            Deshabilitar();
            Resumen();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(EjercicioColas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EjercicioColas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EjercicioColas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EjercicioColas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EjercicioColas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnReestaurar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmpleadoMayor;
    private javax.swing.JTextField txtMontoAcum;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTamanio;
    // End of variables declaration//GEN-END:variables
}
