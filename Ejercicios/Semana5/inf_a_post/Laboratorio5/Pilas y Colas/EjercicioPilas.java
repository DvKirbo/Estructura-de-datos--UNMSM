package Semana5;

import java.text.DecimalFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EjercicioPilas extends javax.swing.JFrame {

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
    
    public Nodo tope;
    public Nodo pFound;
    int num=0;
    
    public EjercicioPilas() {
        initComponents();
        tope=null;
        miModelo = new DefaultTableModel(data,cabecera);
        tblRegistros.setModel(miModelo);
    }
    
    private void push(String cod, String nom, String ape, String sex, float suel){
        Nodo nuevo=new Nodo(cod,nom,ape,sex,suel);
        
        if(tope==null){
            tope=nuevo;
        } else {
            nuevo.sig=tope;
            tope=nuevo;
        }
    }
    
    private String pop(){
        String eliminado="";
        Nodo aux=tope;
        String c=aux.codigo;
        String n=aux.nombre;
        String a=aux.apellidos;
        String s=aux.sexo;
        float su=aux.sueldo;
        eliminado=c+","+n+","+a+","+s+","+String.valueOf(su);
        tope=tope.sig;
        aux.sig=null;
        
        return eliminado;
    }
    
    private Nodo Buscar(Nodo tope, String cod){
        Nodo pos=tope;
        while(pos!=null && !cod.equalsIgnoreCase(pos.codigo)) pos=pos.sig;
        
        return pos;
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
    
    void Resumen(){
        String nom = "", acum="";
        float suma=0, mayor=-9999;
        float s;
        Nodo p=tope;
        while(p!=null){
            s=p.sueldo;
            if(s>mayor){
                mayor=s;
                nom=p.nombre+""+p.apellidos;
            }
            suma=suma+s;
            p=p.sig;
        }
        txtEmpleadoMayor.setText(nom);
    }
    
    void Habilitar(){
        btnActualizar.setEnabled(true);
        btnGuardar.setEnabled(true);
    }
    
    void Deshabilitar(){
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
    }
    
    void VerDatos(){
        String cod,nom,ape,se,su;
        Nodo aux=tope;
        vaciar_tabla();
        num=0;
        Nodo p;
        int nume=Num_elem();
        while(aux!=null){
            cod=aux.codigo;
            nom=aux.nombre;
            ape=aux.apellidos;
            se=aux.sexo;
            num+=aux.sueldo;
            DecimalFormat df2 = new DecimalFormat("####.00");
            su = df2.format(aux.sueldo);
            Object[] fila = {nume,cod,nom,ape,se,su};
            nume--;
            miModelo.addRow(fila);
            aux=aux.sig;
        }
        txtSueldoAcumulado.setText(String.valueOf(num));
    }
    
    private int Num_elem(){
        int num=0;
        Nodo aux=tope;
        while(aux!=null){
            num++;
            aux=aux.sig;
        }
        return num;
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

        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        sexo = new javax.swing.JComboBox<>();
        txtSueldo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnRestaurar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtEmpleadoMayor = new javax.swing.JTextField();
        txtSueldoAcumulado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistros = new javax.swing.JTable();

        jTextField5.setText("jTextField5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("CODIGO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 36, -1, -1));

        jLabel2.setText("NOMBRE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 61, -1, -1));

        jLabel3.setText("APELLIDO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 86, -1, -1));

        jLabel4.setText("SEXO");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 114, -1, -1));

        jLabel5.setText("SUELDO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 145, -1, -1));

        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 34, 168, -1));

        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 59, 168, -1));
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 84, 170, -1));

        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "seleccione", "masculino", "femenino" }));

        getContentPane().add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 109, 170, -1));
        getContentPane().add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 145, 170, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 45, 98, 41));

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 91, 98, 41));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 137, 98, 40));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 45, 90, 41));

        btnRestaurar.setText("Restaruar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReestaurarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 91, 90, 41));

        btnSalir.setText("Salir");
        btnSalir.setToolTipText("");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 137, 90, 40));

        jLabel6.setText("Empleado con el mayor sueldo");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));
        getContentPane().add(txtEmpleadoMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 233, 27));

        txtSueldoAcumulado.setToolTipText("");
        getContentPane().add(txtSueldoAcumulado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 150, 30));

        jLabel7.setText("Monto de sueldos acumulados");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, -1, -1));

        jLabel8.setText("Datos del empleado");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 12, -1, -1));

        tblRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "N°", "CODIGO", "NOMBRES", "APELLIDOS", "SEXO", "SUELDO"
            }
        ));
        jScrollPane2.setViewportView(tblRegistros);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 200, 500, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cod=txtCodigo.getText();
        String nom=txtNombre.getText().toUpperCase();
        String ape=txtApellido.getText().toUpperCase();
        String sex=sexo.getSelectedItem().toString();
        String suel=txtSueldo.getText();
        
        push(cod,nom,ape,sex,Float.parseFloat(suel));
        LimpiarEntradas();
        VerDatos();
        Resumen();
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cod=txtCodigo.getText();
        if(cod.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(this, "Ingrese un codigo por favor");
        }else{
            pFound=Buscar(tope,cod);
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
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LimpiarEntradas();
        Deshabilitar();
        VerDatos();
        Resumen();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(tope==null){
            JOptionPane.showMessageDialog(this, "La Pila está vacía");
        }else{
            String dato=pop();
            mensaje(dato);
            LimpiarEntradas();
            VerDatos();
            if(tope==null){
                JOptionPane.showMessageDialog(this, "La pila esta vacía");
            }
            Deshabilitar();
            Resumen();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnReestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        LimpiarEntradas();
        Deshabilitar();
    }//GEN-LAST:event_btnEliminarActionPerformed
    
    private void formWindowsOpened(java.awt.event.ActionEvent evt) {
        Deshabilitar();
        dispose();
    }//GEN-LAST:event_btnEliminarActionPerformed
    
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
            java.util.logging.Logger.getLogger(EjercicioPilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EjercicioPilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EjercicioPilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EjercicioPilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EjercicioPilas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTable tblRegistros;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmpleadoMayor;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtSueldoAcumulado;
    // End of variables declaration//GEN-END:variables
}
