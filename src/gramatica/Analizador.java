
package gramatica;

import java.util.Stack;

public class Analizador {
    
    Ventana v = null;
    AnalizadorSintactico sintactico = new AnalizadorSintactico();
    private Stack pila = new Stack();
    private Stack pilaCadena = new Stack();
    private Verificacion verificador = new Verificacion();
    private int tokens;
    private String matriz [][] = {{null       ,"1"                      ,"2"                                       ,"3"              ,"4"            ,"5"            ,"6"            ,"7" ,"8"      ,"9"      ,"10"      ,"11"    ,"12"   ,"13"       ,"14"  ,"15"                      ,"16","17","18"},
                                  {"E"        ,"funcion principal ( PARAMETRO ) { C }"       ,"funcion ID ( PARAMETRO ) { C }"          ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
                                  {"C"        ,null                     ,"for ( V = NUM ; ID < NUM ; ID++ ) { C } ","V ;"            ,"V = T L ;"    ,"imprimir ( );","while( ){ C }","if ( ) { C }",null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
                                  {"V"        ,null                     ,null                                      ,"variable D ID"  ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
                                  {"D"        ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,"entero" ,"decimal","caracter","cadena",null   ,null       ,null  ,null                      ,null,null,null},
                                  {"T"        ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,"false","NUM . NUM","true","NUM"                     ,null,null,null},
                                  {"L"        ,null                     ,"ID OPERADOR ID"                          ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,"e" ,null,null},
                                  {"PARAMETRO",null                     ,null                                      ,"e"              ,"e"            ,"e"            ,""             ,null,"D ID"   ,"e"      ,"e"       ,"e"     ,"e"    ,null       ,null  ,null                      ,null,null,null},
                                  {"NUM"      ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,"DIGITO DIGITO"           ,null,null,null},
                                  {"DIGITO"   ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,"0|1|2|3|4|5|6|7|8|9"     ,null,null,null},
                                  {"OPERADOR" ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,"+|-|*|/|%|=|==|<|>|>=|<=",null,null,null},
    };
    private String cadena;
    private String [] vector = null;
    private String [] palabrasReservadas = {};
    
    
    public Analizador(String cadena, Ventana v){
        this.cadena = cadena;
        this.v = v;
        sintactico.analizadorLexico(v);
        insertarCadena(sintactico.getVectorCadena());
        tokens = 0;
        vector = cadena.split(" ");
        String texto [] = matriz[1][1].split(" ");
        insertarSimbolo(texto);
        tokens++;
    }
    
   
    //verifica si la pila de simbolos esta vacia
    public boolean isVacion(){
        if(pila.empty()){
            return true;
        }
        return false;
    }
    //muestra el ultimo dato que esta en la pila de simbolos
    public String verUltimoPila(){
        return (String) pila.peek();
    }
    //muestra el ultimo dato de la pila de la cadena
    public String verUltimoPilaCadena(){
        return (String) pilaCadena.peek();
    }
    //agrega un dato a la pila pero este agrega una cadena a la pila de simbolos
    public void apilarEnPila(String cadena){
        String v1 [] = cadena.split(" ");
        for (int i = 0; i < v1.length; i++) {
            pila.push(v1[i]);
        }
    }
    //quita el ultimo elemento de la pila de simbolos
    public void desapilarPila(){
        pila.pop();
    }
    //agrega un dato a la pila pero este agrega una cadena a la pila de cadenas
    public void apilarEnPilaCadena(String cadena){
        String v1 [] = cadena.split(" ");
        for (int i = 0; i < v1.length; i++) {
            pilaCadena.push(v1[i]);
        }
    }
    //desempila el ultimo elemento de la pila de cadenas
    public void desapilarPilaCadena(){
         pilaCadena.pop();
    }
    
    public void insertarCadena(String textoCompleto []){
        
        for (int i = textoCompleto.length-1; i >=0 ; i--) {
            apilarEnPilaCadena(textoCompleto[i]);
        }
        System.out.println("la ultima pilada cadena es: "+pilaCadena.peek());
    }
    
    public void insertarSimbolo(String textoCompleto []){
        
        for (int i = textoCompleto.length-1; i >=0 ; i--) {
            apilarEnPila(textoCompleto[i]);
        }
        System.out.println("la ultima pilada simbolos es: "+pila.peek());
    }
    
    //verifica si las cabezas de las pilas son iguales
    public void verificarPilas(){
        
        if(verificador.isPalabraReservada(verUltimoPilaCadena())){
            if(verificador.verificarIgualdad(verUltimoPila(), verUltimoPilaCadena())){
                System.out.println(verUltimoPila()+"=="+verUltimoPilaCadena());
                v.areaErrores.setText(v.areaErrores.getText()+"\n"+verUltimoPila()+"=="+verUltimoPilaCadena());
                desapilarPila();
                desapilarPilaCadena();
            }else{
                System.out.println("ERROR: se esperaba una palabra reservada de tipo "+verUltimoPila()+" y el que se encontro fue: "+verUltimoPilaCadena());
                desapilarPilaCadena();
            }
            
            
        }else if(verificador.isOperador(verUltimoPilaCadena())){
            if(verificador.verificarIgualdad(verUltimoPila(), verUltimoPilaCadena())){
                System.out.println(verUltimoPila()+"=="+verUltimoPilaCadena());
                v.areaErrores.setText(v.areaErrores.getText()+"\n"+verUltimoPila()+"=="+verUltimoPilaCadena());
                desapilarPila();
                desapilarPilaCadena();
            }else{
                System.out.println("ERROR: se esperaba una palabra reservada de tipo "+verUltimoPila()+" y el que se encontro fue: "+verUltimoPilaCadena());
                desapilarPilaCadena();
            }
        }else if(verificador.isAgrupador(verUltimoPilaCadena())){
            if(verificador.verificarIgualdad(verUltimoPila(), verUltimoPilaCadena())){
                System.out.println(verUltimoPila()+"=="+verUltimoPilaCadena());
                v.areaErrores.setText(v.areaErrores.getText()+"\n"+verUltimoPila()+"=="+verUltimoPilaCadena());
                desapilarPila();
                desapilarPilaCadena();
            }else{
                System.out.println("ERROR: se esperaba una palabra reservada de tipo "+verUltimoPila()+" y el que se encontro fue: "+verUltimoPilaCadena());
                desapilarPilaCadena();
            }
        }else if(verificador.isSigno(verUltimoPilaCadena())){
            if(verificador.verificarIgualdad(verUltimoPila(), verUltimoPilaCadena())){
                System.out.println(verUltimoPila()+"=="+verUltimoPilaCadena());
                v.areaErrores.setText(v.areaErrores.getText()+"\n"+verUltimoPila()+"=="+verUltimoPilaCadena());
                desapilarPila();
                desapilarPilaCadena();
            }else{
                System.out.println("ERROR: se esperaba una palabra reservada de tipo "+verUltimoPila()+" y el que se encontro fue: "+verUltimoPilaCadena());
                desapilarPilaCadena();
            }
        }else{
            //cuando puede ser un identificador
                System.out.println(verUltimoPila()+"=="+verUltimoPilaCadena());
                v.areaErrores.setText(v.areaErrores.getText()+"\n"+verUltimoPila()+"=="+verUltimoPilaCadena());
                desapilarPila();
                desapilarPilaCadena();
        } 
            
        
        
        
        
    }
    //verifica si si la pila de simbolos es un estado o no
    public void verificarCabezaPilaSimbolos(){
        System.out.println("pilaCadena: "+verUltimoPilaCadena()+"      pilaFila: "+verUltimoPila());
        if(verificador.isEstado(verUltimoPila())){
            System.out.println("Columna : "+verificador.regresarPosicionColumna(verUltimoPilaCadena())+"      fila: "+verificador.regresarposicionFila(verUltimoPila()));
            System.out.println("pilaCadena: "+verUltimoPilaCadena()+"      pilaFila: "+verUltimoPila());
            if(verificador.regresarPosicionColumna(verUltimoPilaCadena())==0 || verificador.regresarposicionFila(verUltimoPila())==0){
                desapilarPila();
                verificarCabezaPilaSimbolos();
            }else{
                String texto [] = matriz[verificador.regresarposicionFila(verUltimoPila())][verificador.regresarPosicionColumna(verUltimoPilaCadena())].split(" ");
                desapilarPila();
                insertarSimbolo(texto);
                verificarCabezaPilaSimbolos();
            }
            
        }else{
            verificarPilas();
        }
        
    }
    
    
}
