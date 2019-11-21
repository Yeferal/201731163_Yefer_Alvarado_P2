/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramatica;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO-PC
 */
public class Gramatica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            
            
            Ventana ventana = new Ventana();
        } catch (SQLException ex) {
            Logger.getLogger(Gramatica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
