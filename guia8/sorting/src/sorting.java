import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    // Complejidad O(NLog(N))
    public static int[] ordenarPorApariciones(int[]l){
        if(l.length == 0){
            return new int[0];
        }
        // Creo una lista nueva y la ordeno con heapSort O(Log(N))
        int[] listaOrdenada = new int[l.length];
        for(int i = 0; i < l.length; i++){
            listaOrdenada[i] = l[i];
        }
        heapSort(listaOrdenada);

        Queue<tupla> colaElementosApariciones = armarListaElementoApariciones(listaOrdenada); // O(N)
        tupla[] arrayElementosApariciones = queueToArray(colaElementosApariciones); // O(N)
        tupla[] ordenadoPorApariciones = ordenarTuplasPorApariciones(arrayElementosApariciones); //O(NLog(N))
        Queue<Integer> listaFinal = armarListaPorCantidad(ordenadoPorApariciones); // O(N)
        listaOrdenada = listaToArray(listaFinal); // O(N)
        return listaOrdenada;
    }

    public static Queue<tupla> armarListaElementoApariciones(int[]l){
        Queue<tupla> res = new LinkedList<>(); 
        int elementoActual = l[0];
        int aparicionesActuales = 1;
        for(int i = 1; i < l.length; i++){
            if(l[i] == elementoActual){
                aparicionesActuales++;
            }
            else{
                res.offer(new tupla(elementoActual, aparicionesActuales));
                aparicionesActuales = 1;
                elementoActual = l[i];
            }
        }
        // Para que agregue al ultimo de la lista
        res.offer(new tupla(elementoActual, aparicionesActuales));
        return res;
    } 

    public static tupla[] queueToArray(Queue<tupla> l){
        tupla[] res = new tupla[l.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = l.poll();
        }
        return res;
    }

    public static tupla[] ordenarTuplasPorApariciones(tupla[] l){
        tupla[] res = new tupla[l.length];
        for(int i = 0; i < res.length; i++){
            res[i] = l[i];
        }
        mergeSortPorApariciones(res, 0, res.length - 1);
        return res;
    }


    public static void mergeSortPorApariciones(tupla arr[], int comienzo, int ultimo) {
        if (comienzo < ultimo) {
            int medio = comienzo + (ultimo - comienzo) / 2;
            mergeSortPorApariciones(arr, comienzo, medio);
            mergeSortPorApariciones(arr, medio + 1, ultimo);
            merge(arr, comienzo, medio, ultimo);
        }
    }

    public static void merge(tupla arr[], int inicioPrimero, int finPrimero, int finSegundo) {
        int n1 = finPrimero - inicioPrimero + 1;
        int n2 = finSegundo - finPrimero;
        tupla arrayPrimero[] = new tupla[n1];
        tupla arraySegundo[] = new tupla[n2];
        for (int i = 0; i < n1; i++){
            arrayPrimero[i] = arr[inicioPrimero + i];
        }
        for (int j = 0; j < n2; j++){
            arraySegundo[j] = arr[finPrimero + 1 + j];
        }
        int i = 0, j = 0;
        int k = inicioPrimero;
        while (i < n1 && j < n2) {
            if (arrayPrimero[i].getApariciones() > arraySegundo[j].getApariciones()) {
                arr[k] = arrayPrimero[i];
                i++;
            } 
            else if(arrayPrimero[i].getApariciones() == arraySegundo[j].getApariciones()){
                if(arrayPrimero[i].getValor() < arraySegundo[j].getValor()){
                    arr[k] = arrayPrimero[i];
                    i++;
                }
                else{
                    arr[k] = arraySegundo[j];
                    j++;
                }
            }
            else {
                arr[k] = arraySegundo[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = arrayPrimero[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = arraySegundo[j];
            j++;
            k++;
        }
    }
    

    public static Queue<Integer> armarListaPorCantidad(tupla[] lista){
        Queue<Integer> res = new LinkedList<>();
        for(int i = 0; i < lista.length; i++){
            int cantidadActual = lista[i].getApariciones();
            int elementoActual = lista[i].getValor();
            for(int j = 0; j < cantidadActual; j++){
                res.offer(elementoActual);
            }
        }
        return res;
    }

    public static int[] listaToArray(Queue<Integer> l){
        int[] res = new int[l.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = l.poll();
        }
        return res;
    }

    // Ejercicio 6
    // Idea: armar tuplas con el primer elemento y despues la longitud de la escalera, ahi ordeno por la longitud de la escalera con merge.
    public static int[] ordenarEscaleras(int[]l){
        int[] res = new int[l.length];
        for(int i = 0; i < res.length; i++){
            res[i] = l[i];
        }
        Queue<tupla> listaEscaleras = armarListaEscaleras(l);
        tupla[] arrayEscaleras = queueToArray(listaEscaleras);
        tupla[] escalerasOrdenadasPorLargo = ordenarTuplasPorApariciones(arrayEscaleras);
        Queue<Integer> listaFinal = rearmarEscaleras(escalerasOrdenadasPorLargo);
        res = listaToArray(listaFinal);
        return res;
    } 

    public static Queue<tupla> armarListaEscaleras(int[]l){
        Queue<tupla> res = new LinkedList<>();
        int primerElemento = l[0];
        int cantidadActual = 1;
        for(int i = 1; i < l.length; i++){
            if(l[i] == l[i-1] + 1){
                cantidadActual++;
            }
            else{
                res.offer(new tupla(primerElemento, cantidadActual));
                primerElemento = l[i];
                cantidadActual = 1;
            }
        }
        res.offer(new tupla(primerElemento, cantidadActual));
        return res;
    }

    public static Queue<Integer> rearmarEscaleras(tupla[] l){
        Queue<Integer> res = new LinkedList<>();
        for(int i = 0; i < l.length; i++){
            int elementoActual = l[i].getValor();
            for(int j = 0; j < l[i].getApariciones(); j++){
                res.offer(elementoActual);
                elementoActual += 1;
            }
        }
        return res;
    }
}
