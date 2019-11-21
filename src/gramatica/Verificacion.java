
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
    
    public int regresarPosicionColumna(String palabra){
        switch(palabra){
            case "funcion":
                return 1;
            case "for":
                return 2;
            case "V":
                return 3;
            case "imprimir":
                return 5;
            case "while":
                return 7;
            case "if":
                return 6;
            case "variable":
                return 2;
            case "entero":
                return 7;
            case "decimal":
                return 8;
            case "booleano":
                return 9;
            case "caracter":
                return 10;
            case "false":
                return 12;
            case ".":
                return 13;
            case "true":
                return 14;
            case "NUM":
                return 15;
            case "ID":
                return 0;
            case "e":
                return 3;
            case "D":
                return 7;
            case "DIGITO":
                return 15;
                default:
                    return 0;
        }
        
    }
        
        public int regresarposicionFila(String estado){
            
            switch(estado){
                case "E":
                    return 1;
                case "C":
                    return 2;
                case "V":
                    return 3;
                case "D":
                    return 4;
                case "T":
                    return 5;
                case "L":
                    return 6;
                case "PARAMENTRO":
                    return 7;
                case "NUM":
                    return 8;
                case "DIGITO":
                    return 9;
                case "OPERADOR":
                    return 10;
                    default:
                        return 0;
            }
        }
        
    
    
}
