
package gramatica;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;


public class Ventana extends javax.swing.JFrame {

    private Lectura leectura = new Lectura();
    Analizador analisis = null;
    
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

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 29, -1, 491));
        panel1.add(areaErrores, new org.netbeans.lib.awtextra.AbsoluteConstraints(712, 530, 453, 230));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        analisis = new Analizador(areaTexto.getText(),this);
//        analisis.insertarCadena(textoArea);
        botonAnalizar.setEnabled(false);
    }//GEN-LAST:event_botonAnalizarMouseClicked

    private void botonSolicitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonSolicitarMouseClicked
        // TODO add your handling code here:
        analisis.verificarCabezaPilaSimbolos();
        
    }//GEN-LAST:event_botonSolicitarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public java.awt.TextArea areaErrores;
    public java.awt.TextArea areaTexto;
    private javax.swing.JButton botonAnalizar;
    private javax.swing.JButton botonLeer;
    private javax.swing.JButton botonSolicitar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTexto;
    private javax.swing.JPanel panel1;
    public javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
