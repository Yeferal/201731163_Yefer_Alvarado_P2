
package gramatica;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class AnalizadorSintactico {

    private String palabrasReservada [] = {"funcion","principal","retornar","vacio","variable","entero","decimal","booleano","cadena","carácter","si","sino","mientras","para","hacer","imprimir"};
    private String operadores [] = {"+","-","*","/","%","=","==","<",">",">=","<="};
    private String agrupadores [] = {"(",")","{","}"};
    private String signos [] = {"\"",";"};
    private String estados [] = {"E","C","V","D","T","L","PARAMETRO","NUM" ,"DIGITO","OPERADOR"};
    private String vectorTonkens[] = null;
    private ArrayList<String> arreglo= new ArrayList<>();
    private int fila =0;
    private Ventana ventana;
    private int columna=0;
    private boolean cambioOperador = false;
    private int cambioAgrupador = 0;
    
    private boolean cambioEstado = false;//Cambio de Estado (E1, E5)
    private String[] listaPalabras;
    private int[][] tablaTransicion = new int[8][8]; 
    private final char[] OPERADOR = {'+', '-', '*', '/', '%', '=', '>', '<'};
    private final char[] AGRUPACION = {'{', '}', '(', ')'};
    private final char[] SIGNO = {'"', ';'};
    private final String[] PALABRA_RESERVADA = {"funcion", "principal", "retornar", "vacio", "variable", "entero", "decimal", "booleano", "cadena", "carácter", "si", "sino", "mientra", "para", "hacer", "imprimir"};
    private final String[] BOOLEAN = {"VERDADERO", "FALSO"};
    private boolean comentario = false;
    private boolean parteInicialComentario = false;
    private boolean abrioDiagonal = false;
    private boolean asterisco = false;
    private boolean cadena = false;
    private boolean abrioComillas = false;
    private boolean entero = false;
    private boolean flotante = false;
    private boolean caracter = false;
    private boolean error = false;
    private boolean coma = false;
    
    
   
    
    public String [] getVectorCadena(){
        System.out.println("tamanio: "+arreglo.size());
        
        for (int i = 0; i < arreglo.size(); i++) {
            //vectorTonkens[i]=arreglo.get(i);
            ventana.areaTokens.setText(ventana.areaTokens.getText()+"\n"+" TOKEN: " + arreglo.get(i));
        }
        
        return vectorTonkens;
    }
    
    public boolean analizadorLexico(Ventana ventana) {
        this.ventana = ventana;
        asignarValoresTablaTrancision();
        ingresarPalabras(ventana.areaTexto.getText());
        return false;
    
    }
    
    private void ingresarPalabras(String text) {
        listaPalabras = text.split("\n");
        for (int i = 0; i < listaPalabras.length; i++) {
            System.out.println("Fila #" + i + " - " + listaPalabras[i]);
            fila++;
            analizar(listaPalabras[i]);
        }
    }    
    
    private String analizar(String text) {
        nuevo();
        String analizado = "";
        String analizadoEntrada = "";
        String salida = "";
        String[] palabras = text.split(" ");
        int estadoActual = 0;
        int estadoAnterior = 0;
        for (int i = 0; i < palabras.length; i++) {
            analizadoEntrada = palabras[i];//Palabra para analizar
            analizadoEntrada = analizadoEntrada.trim();//Elimina los espacios
            for (int j = 0; j < analizadoEntrada.length(); j++) {
                columna = j;
                estadoAnterior = estadoActual;
                estadoActual = comprobarEstados(analizadoEntrada.charAt(j), estadoActual);
                if (estadoActual != estadoAnterior) {//Comprueba si hubo un cambio
                    if ((estadoActual == 6 || estadoActual == 7) && estadoAnterior == 5) {
                        cambioEstado = true;
                    }
                    if ((estadoActual == 2 || estadoActual == 3 || estadoActual == 4) && estadoAnterior != 0) {
                        cambioEstado = true;
                    }
                    if ((estadoAnterior == 2 || estadoAnterior == 3 || estadoAnterior == 4) && (estadoActual == 1 || estadoActual == 5)) {
                        cambioEstado = true;
                    }
                    if (estadoAnterior == 4 && estadoActual == 3) {
                        cambioEstado = true;
                    }
                    if (estadoActual == 6 && estadoAnterior == 5) {
                        cambioEstado = true;
                        flotante = true;
                    }
                }       
                if (cambioAgrupador == 3) {
                    cambioEstado = true;
                }
                
                if (cambioEstado) {
                    if (error && (flotante)){
                        System.out.println("Error Col:");
                    } else if (estadoActual != 6) {
                        
                        System.out.println("Analizado -> " + comprobarToken(salida, estadoActual));
                        salida = "";
                    }
                } else {
                    if (cambioAgrupador == 1) {
                        System.out.println("Analizado -> " + comprobarToken(salida, estadoActual));
                        salida = "";
                    }
                }
                
                salida += analizadoEntrada.charAt(j);
                
                if (!cambioEstado && j == analizadoEntrada.length() - 1) {
                    System.out.println("Analizado => " + comprobarToken(salida, estadoActual));
                    salida = "";
                    cambioEstado = false;
                }
                if (cambioEstado) {
                    cambioEstado = false;   
                }
            }
            cambioAgrupador = 0;
            estadoActual = 0;
            estadoAnterior = 0;
        }
        return analizado;
    }
     private void asignarValoresTablaTrancision() {
        for (int i = 0; i < tablaTransicion.length; i++) {
            for (int j = 0; j < tablaTransicion.length; j++) {
                tablaTransicion[i][j] = 0;
            }
        }
        //Estado Inicial
        tablaTransicion[0][1] = 1;
        tablaTransicion[0][2] = 2;
        tablaTransicion[0][3] = 3;
        tablaTransicion[0][4] = 4;
        tablaTransicion[0][5] = 5;
        
        //Estado 1 - Letras
        tablaTransicion[1][1] = 1;
        tablaTransicion[1][2] = 2;//Cambio de estado a operador
        tablaTransicion[1][3] = 3;//Cambio de estado a agrupacion
        tablaTransicion[1][4] = 4;//Cambio de estado a un signo
        tablaTransicion[1][5] = 5;
        
        //Estado 2 - Operador
        tablaTransicion[2][2] = 2;
        tablaTransicion[2][4] = 4;
        tablaTransicion[2][5] = 5;
        
        //Estado 3 - Agrupacion
        tablaTransicion[3][1] = 1;
        tablaTransicion[3][3] = 3;
        tablaTransicion[3][4] = 4;
        tablaTransicion[3][5] = 5;//Cambio de estado a numero
        
        //Estado 4 - Signo
        tablaTransicion[4][1] = 1;
        tablaTransicion[4][2] = 2;
        tablaTransicion[4][3] = 3;
        tablaTransicion[4][4] = 4;
        tablaTransicion[4][5] = 5;
        
        //Estado 5 - Numero
        tablaTransicion[5][1] = 1;
        tablaTransicion[5][2] = 2;//Cambio de estado a operador
        tablaTransicion[5][3] = 3;//Cambio de estado a agrupacion
        tablaTransicion[5][4] = 4;//Cambio de estado a signo
        tablaTransicion[5][5] = 5;
        tablaTransicion[5][6] = 6;
        
        //Estado 6 - Punto
        tablaTransicion[6][4] = 4;//Cambio de estado a signo
        tablaTransicion[6][5] = 5;
        
    }
    
    private void nuevo() {
    comentario = false;
    parteInicialComentario = false;
    abrioDiagonal = false;
    asterisco = false;
    cadena = false;
    abrioComillas = false;
    entero = false;
    flotante = false;
    caracter = false;
    error = false;
    
    cambioOperador = false;
    cambioEstado = false;//Cambio de Estado (E1, E5)
    }
    private String comprobarToken(String text, int estado){
        boolean bandera = false;
        int numero = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                numero++;
                bandera = true;
            }
        }
        if (numero == text.length() && bandera == true) {
            arreglo.add(text);
            ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Numero: " + text);
            ventana.ingresarFila(fila+"",columna+"", "", "", "", "", "",text,"");
            return " Número -> " + text;
        }
        for (int i = 0; i < AGRUPACION.length; i++) {
            if (Character.toString(AGRUPACION[i]).equalsIgnoreCase(text)) {
                arreglo.add(text);
                ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Agrupador: " + text);
                ventana.ingresarFila(fila+"",columna+"", "", "", text, "", "","","");
                return " Agrupacion -> " + text;
            } 
        }
        for (int i = 0; i < OPERADOR.length; i++) {
            if (Character.toString(OPERADOR[i]).equalsIgnoreCase(text)) {
                arreglo.add(text);
                ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Operador: " + text);
                ventana.ingresarFila(fila+"",columna+"", "", text, "", "", "","","");
                return " Operador -> " + text;
            } 
        }
        for (int i = 0; i < SIGNO.length; i++) {
            if (Character.toString(SIGNO[i]).equalsIgnoreCase(text)) {
                arreglo.add(text);
                ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Signo: " + text);
                ventana.ingresarFila(fila+"",columna+"", "", "", "", text, "","","");
                return " Signo -> " + text;
            }
        }
        for (int i = 0; i < PALABRA_RESERVADA.length; i++) {
            if (PALABRA_RESERVADA[i].equalsIgnoreCase(text)) {
                arreglo.add(text);
                ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Palabra Reservada: " + text);
                ventana.ingresarFila(fila+"",columna+"", text, "", "", "", "","","");
                return " Palabra Reservada -> " + text;
            }
        }
        for (int i = 0; i < BOOLEAN.length; i++) {
            if (BOOLEAN[i].equalsIgnoreCase(text)) {
                arreglo.add(text);
                ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Booleano: " + text);
                ventana.ingresarFila(fila+"",columna+"", "", "", "", "", "","",text);
                return " Boolean -> " + text;
            }
        }
        
        if (estado != 0) {
            arreglo.add(text);
            ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Identifficador: " + text);
            ventana.ingresarFila(fila+"",columna+"", "", "", "", "", text,"","");
            return " Palabra -> " + text + " ";
            
        }
        arreglo.add(text);
        ventana.areaErrores.setText(ventana.areaErrores.getText()+"\n"+" Error Lexico: " + text);
        ventana.ingresarFila(fila+"",columna+"", "e", "r", "r", "o", "r","-->",text);
        return " Error";
    }
    
    private boolean comprobarComentarioOCaracterOCadena(char caracterComprobar) {
        if ("'".equalsIgnoreCase(Character.toString(caracterComprobar))) {
            System.out.println("Cadena");
        }
        return false;
    }
    
    private int comprobarEstados(char caracterAnalizar, int estadoActual){
        if (Character.isDigit(caracterAnalizar)) {//Es un numero

            return tablaTransicion[estadoActual][5];
        } else if (Character.isLetter(caracterAnalizar)) {//Es una letra
            return tablaTransicion[estadoActual][1];
        } else if (comprobarSiEsOperador(caracterAnalizar)) {
            if (!cambioOperador) {
                cambioOperador = true;
            }
            if (cambioOperador) {
                return 0;
            }

            return tablaTransicion[estadoActual][2];
        } else if (comprobarSiEsAgrupador(caracterAnalizar)) {
            if (estadoActual == 0 || estadoActual == 3) {
                switch (cambioAgrupador) {
                case 0:
                    cambioAgrupador = 1;
                    break;
                case 1:
                    cambioAgrupador = 2;
                    break;
                case 3:
                    return 0;
                default:
                    return tablaTransicion[estadoActual][3];
            }
            }

            return tablaTransicion[estadoActual][3];
        } else if (comprobarSiEsSigno(caracterAnalizar)) {
            if (abrioComillas == false) {
                abrioComillas = true;
            } else {
                cadena = true;
            }
            return tablaTransicion[estadoActual][4];
        } else if (caracterAnalizar == '.') {
            if (flotante) {
                return 0;
            } else {
                return tablaTransicion[estadoActual][6];
            }
        }
        return 0;
    }
    
    private boolean comprobarSiEsOperador(char text) {
        for (int i = 0; i < OPERADOR.length; i++) {
            if (OPERADOR[i] == text) {
                return true;
            }
        }
        return false;
    }
    
    private boolean comprobarSiEsAgrupador(char text) {
        for (int i = 0; i < AGRUPACION.length; i++) {
            if (AGRUPACION[i] == text) {
                return true;
            }
        }
        return false;
    }
    
    private boolean comprobarSiEsSigno(char text) {
        for (int i = 0; i < SIGNO.length; i++) {
            if (SIGNO[i] == text) {
                return true;
            }
        }
        return false;
    }
}
 

