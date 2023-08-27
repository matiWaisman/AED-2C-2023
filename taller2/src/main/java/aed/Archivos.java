package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {
    float[] leerVector(Scanner entrada, int largo) {
        float[] res = new float[largo];
        for (int i = 0; i < largo; i++){
            res[i] = entrada.nextFloat();
        }
        return res;

    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] res = new float[filas][columnas];
        for (int i = 0; i < filas; i++){
                res[i] = leerVector(entrada, columnas);
        }
        return res;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        String txt = "";
        for(int i = 0; i < alto; i++){
            txt += devolverNSimbolos(alto - i - 1, ' ');
            txt += devolverNSimbolos(2*i + 1, '*');
            txt += devolverNSimbolos(alto - i - 1, ' ');
            txt += '\n'; 
        }
        if (alto == 0) {
            salida.print(""); 
        }
        else{
            salida.println(txt);
        }
    }

    String devolverNSimbolos(int n, char s){
        String res = "";
        for (int i = 1; i <= n; i++){
            res += s;
        }
        return res;
    }
}
