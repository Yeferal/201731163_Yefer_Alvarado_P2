
package gramatica;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


public class Ventana extends javax.swing.JFrame {

    private Lectura leectura = new Lectura();
    Analizador analisis = null;
    AnalizadorSintactico sintactico = new AnalizadorSintactico();
    
    public Ventana() {
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        botonAnalizar.setVisible(true);
        botonSolicitar.setVisible(false);
        //botonAnalizar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new javax.swing.JPanel();
        areaTexto = new java.awt.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        areaErrores = new java.awt.TextArea();
        labelTexto = new javax.swing.JLabel();
        botonLeer = new javax.swing.JButton();
        botonAnalizar = new javax.swing.JButton();
        botonSolicitar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaTokens = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel1.add(areaTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 48, 564, 502));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabla);

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, 260));
        panel1.add(areaErrores, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, 453, 450));

        labelTexto.setText("Texto");
        panel1.add(labelTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, -1, -1));

        botonLeer.setText("Leer Archivo");
        botonLeer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonLeerMouseClicked(evt);
            }
        });
        panel1.add(botonLeer, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 6, -1, -1));

        botonAnalizar.setText("Analizar");
        botonAnalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonAnalizarMouseClicked(evt);
            }
        });
        panel1.add(botonAnalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 594, -1, -1));

        botonSolicitar.setText("Solicitar");
        botonSolicitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonSolicitarMouseClicked(evt);
            }
        });
        panel1.add(botonSolicitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 659, -1, -1));

        jButton1.setText("Sintaxis");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, -1, -1));

        areaTokens.setColumns(20);
        areaTokens.setRows(5);
        jScrollPane2.setViewportView(areaTokens);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 20, 420, 730));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1575, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonLeerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLeerMouseClicked
        // TODO add your handling code here:
        
        JFileChooser choose = new JFileChooser();
        int seleccion = choose.showOpenDialog(this);
        if(seleccion == JFileChooser.APPROVE_OPTION){
            try {
                File fichero = choose.getSelectedFile();
                leectura.leer(fichero.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }
        areaTexto.setText(leectura.getTexto());
        
        //botonAnalizar.setEnabled(true);
        //botonSolicitar.setVisible(false);
        
        
    }//GEN-LAST:event_botonLeerMouseClicked

    private void botonAnalizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAnalizarMouseClicked
        // TODO add your handling code here:
        botonSolicitar.setVisible(true);
        String textoArea [] = areaTexto.getText().split(" ");
//        analisis = new Analizador(areaTexto.getText(),this);
//        analisis.insertarCadena(textoArea);
        sintactico.analizadorLexico(this);
        botonAnalizar.setEnabled(false);
    }//GEN-LAST:event_botonAnalizarMouseClicked

    private void botonSolicitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSolicitarMouseClicked
        // TODO add your handling code here:
        //analisis.verificarCabezaPilaSimbolos();
        
        
        
    }//GEN-LAST:event_botonSolicitarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
        
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public java.awt.TextArea areaErrores;
    public java.awt.TextArea areaTexto;
    private javax.swing.JTextArea areaTokens;
    private javax.swing.JButton botonAnalizar;
    private javax.swing.JButton botonLeer;
    private javax.swing.JButton botonSolicitar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTexto;
    private javax.swing.JPanel panel1;
    public javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
