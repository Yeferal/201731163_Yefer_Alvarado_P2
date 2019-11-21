/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gramatica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author LENOVO-PC
 */
public class Lectura {
    
    private String texto;
    Analizador analisis = null;
    
    public void leer(String fichero) throws IOException{

        try{
            FileReader tx = new FileReader(fichero);
            BufferedReader br = new BufferedReader(tx);
                texto = "";
                String cadena;
                while((cadena=br.readLine())!=null){   
                    texto += cadena+"\n";

                }             
              System.out.println(texto);
            br.close();     
        }catch(Exception e){
            
        }
    }
    
    public String getTexto(){
        analisis = new Analizador(texto);
        
        return texto;
    }
    
}
