package aed;
import java.lang.Math;

class Funciones {
    int cuadrado(int x) {
        int res = x*x;
        return res;
    }

    double distancia(double x, double y) {
        double res = Math.sqrt(x*x + y*y);
        return res;
    }

    boolean esPar(int n) {
        boolean res = false;
        if(n % 2 == 0){
            res = true;
        }
        return res;
    }

    boolean esBisiesto(int n) {
        boolean res = false;
        if((n % 400 == 0) || (n % 4 == 0 && n % 100 != 0)){
            res = true;
        }
        return res;
    }

    int factorialIterativo(int n) {
        int res = 1;
        for(int i = 1; i <= n; i ++){
            res = res * i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        int res = 1;
        if(n != 0){
            res = factorialRecursivo(n - 1) * n;
        }
        return res;
    }

    boolean esPrimo(int n) {
        boolean res = true;
        int i = 2;
        while (i < n && res == true){
            if(n % i == 0){
                res = false;
            }
            i += 1;
        }
        if(n == 0 || n == 1){
            res = false;
        }
        return res;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for(int i = 0; i < numeros.length; i++){
            res += numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = -1;
         for(int i = 0; i < numeros.length; i++){
            if(numeros[i] == buscado){
                res = i;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;
        for(int i = 0; i < numeros.length; i++){
            if(esPrimo(numeros[i])){
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res = true;
        for(int i = 0; i < numeros.length; i++){
            if(!esPar(numeros[i])){
                res = false;
            }
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        boolean res = true;
        if(s1.length() > s2.length()){
            res = false;
        }
        else{
            for(int i = 0; i < s1.length(); i++){
                if(s1.charAt(i) != s2.charAt(i)){
                    res = false;
                }
            }
        } 
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        boolean res = true;
        if(s1.length() > s2.length()){
            res = false;
        }
        else{
            for(int i = s1.length() - 1; i >= 0; i--){
                if(s1.charAt(i) != s2.charAt(i + diferenciaLongitud(s1, s2))){
                    res = false;
                }
            }
        } 
        return res;
    }

    int diferenciaLongitud(String s1, String s2) {
        int res = s2.length() - s1.length();
        return res;
    }
}
