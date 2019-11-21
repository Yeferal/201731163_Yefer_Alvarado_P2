
package gramatica;


public class Verificacion {
    
    private String palabrasReservada [] = {"funcion","principal","retornar","vacio","variable","entero","decimal","booleano","cadena","carácter","si","sino","mientras","para","hacer","imprimir"};
    private String operadores [] = {"+","-","*","/","%","=","==","<",">",">=","<="};
    private String agrupadores [] = {"(",")","{","}"};
    private String signos [] = {"\"",";"};
    private String estados [] = {"E","C","V","D","T","L","PARAMETRO","NUM" ,"DIGITO","OPERADOR"};
    
    
    //verifica si pertenece a las palabras reservadas
    public boolean isPalabraReservada(String palabra){
        for (int i = 0; i < palabrasReservada.length; i++) {
            if(palabrasReservada[i].equals(palabra)){
                return true;
            }
        }
        return false;
    }
    //verifica si es un operador
    public boolean isOperador(String palabra){
        for (int i = 0; i < operadores.length; i++) {
            if(operadores[i].equals(palabra)){
                return true;
            }
        }
        return false;
    }
    //verifica si es un agrupador
    public boolean isAgrupador(String palabra){
        for (int i = 0; i < agrupadores.length; i++) {
            if(agrupadores[i].equals(palabra)){
                return true;
            }
        }
        return false;
    }
    //verifica si es un signo
    public boolean isSigno(String palabra){
        for (int i = 0; i < signos.length; i++) {
            if(signos[i].equals(palabra)){
                return true;
            }
        }
        return false;
    }
    //verifica si es un estado
    public boolean isEstado(String palabra){
        for (int i = 0; i < estados.length; i++) {
            if(estados[i].equals(palabra)){
                return true;
            }
        }
        return false;
    }
    //verifica si la palabra y el ultimo simbolo de la pila de simbolos son iguales
    public boolean verificarIgualdad(String simboloPila,String simboloCadena){
        
        if(simboloPila.equals(simboloCadena)){
            return true;
        }
        return false;
    }
    
    
}
