import java.util.*;

public class sorting {
    // Ejercicio 3 implementacion
    public static int[] kElementosMasChicos(int k, int[]lista){
        int[] res = new int[k];
        // O(N)
        if(k == 1){
            res[0] = encontrarMinimo(lista);
        }
        // Tecnicamente es O(N^2), pero si k es menor a log(n) va a quedar n log n como mucho
        if(k < Math.log(lista.length)){
           res = kMasChicoQueLogN(k, lista);
        }
        else{
            heapSort(lista);
            for(int i = 0; i < k; i++){
                res[i] = lista[i];
            }
        }
        return res;
    }

    public static int encontrarMinimo(int[]l){
        int minimoActual = l[0];
        for(int i = 1; i < l.length; i++){
            if(l[i] < minimoActual){
                minimoActual = l[i];
            }
        }
        return minimoActual;
    }

    public static int[] kMasChicoQueLogN(int k, int[]lista){
        int[] res = new int[k];
        int max = encontrarMaximo(lista);
        for(int i = 0; i < k; i++){
            res[i] = max;
        }
        for(int i = 0; i < k; i++){
            for(int j = 0; j < lista.length; j++){
                if(lista[j] < res[i] && (i == 0 || lista[j] > res[i - 1])){
                    res[i] = lista[j];
                }
            }
        }
        return res;
    }

    public static int encontrarMaximo(int[]l){
        int maximoActual = l[0];
        for(int i = 1; i < l.length; i++){
            if(l[i] > maximoActual){
                maximoActual = l[i];
            }
        }
        return maximoActual;
    }

    public static void heapSort(int l[]){
        int largo = l.length;
        for (int i = largo / 2 - 1; i >= 0; i--){
            heapify(l, largo, i);
        }
        for (int i = largo - 1; i > 0; i--) {
            int temp = l[0];
            l[0] = l[i];
            l[i] = temp;
            heapify(l, i, 0);
        }
    }

    public static void heapify(int l[], int largo, int raiz){
        int masGrande = raiz; 
        int izquierda = 2 * raiz + 1; 
        int derecha = 2 * raiz + 2; 

        if (izquierda < largo && l[izquierda] > l[masGrande]){
            masGrande = izquierda;
        }

        if (derecha < largo && l[derecha] > l[masGrande]){
            masGrande = derecha;
        }

        if (masGrande != raiz) {
            int aux = l[raiz];
            l[raiz] = l[masGrande];
            l[masGrande] = aux;
            heapify(l, largo, masGrande);
        }
    }

    // Ejercicio 5
    public static void ordenarPorApariciones(int[]l){
        
    }
}
