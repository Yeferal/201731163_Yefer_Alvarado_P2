
package gramatica;

import java.util.Stack;

public class Analizador {
    
    private Stack pila = new Stack();
    private Stack pilaCadena = new Stack();
    private Verificacion verificador = new Verificacion();
    
    private String matriz [][] = {{null       ,"1"                      ,"2"                                       ,"3"              ,"4"            ,"5"            ,"6"            ,"7" ,"8"      ,"9"      ,"10"      ,"11"    ,"12"   ,"13"       ,"14"  ,"15"                      ,"16","17","18"},
                                  {"E"        ,"funcion principal { C }","funcion ID ( PARAMETRO ) { C }"          ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
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
    
    
    public Analizador(String cadena){
        this.cadena = cadena;
        vector = cadena.split(" ");
        ver();
    }
    
    
    public void ver(){
        for (int i = 0; i < vector.length; i++) {
            System.out.print(vector[i]+",");
        }
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
    
    
}
