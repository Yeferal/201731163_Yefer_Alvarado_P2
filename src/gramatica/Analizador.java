
package gramatica;

public class Analizador {
    
    private String matriz [][] = {{null       ,"1"                      ,"2"                                       ,"3"              ,"4"            ,"5"            ,"6"            ,"7" ,"8"      ,"9"      ,"10"      ,"11"    ,"12"   ,"13"       ,"14"  ,"15"                      ,"16","17","18"},
                                  {"E"        ,"funcion principal { C }","funcion ID ( PARAMETRO ) { C }"          ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
                                  {"C"        ,null                     ,"for ( V = NUM ; ID < NUM ; ID++ ) { C } ","V ;"            ,"V = T L ;"    ,"imprimir ( );","while( ){ C }","if ( ) { C }",null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
                                  {"V"        ,null                     ,null                                      ,"variable D ID"  ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,null,null,null},
                                  {"D"        ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,"entero" ,"decimal","caracter","cadena",null   ,null       ,null  ,null                      ,null,null,null},
                                  {"T"        ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,"false","NUM . NUM","true","NUM"                     ,null,null,null},
                                  {"L"        ,null                     ,"ID OPERADOR ID"                          ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,null                      ,"e" ,null,null},
                                  {"parametro",null                     ,null                                      ,"e"              ,"e"            ,"e"            ,""             ,null,"D ID"   ,"e"      ,"e"       ,"e"     ,"e"    ,null       ,null  ,null                      ,null,null,null},
                                  {"NUM"      ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,"DIGITO DIGITO"           ,null,null,null},
                                  {"dig"      ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,"0|1|2|3|4|5|6|7|8|9"     ,null,null,null},
                                  {"ope"      ,null                     ,null                                      ,null             ,null           ,null           ,null           ,null,null     ,null     ,null      ,null    ,null   ,null       ,null  ,"+|-|*|/|%|=|==|<|>|>=|<=",null,null,null},
    };
    private String cadena;
    public Analizador(String cadena){
        this.cadena = cadena;
        
        
        
        
    }
}
