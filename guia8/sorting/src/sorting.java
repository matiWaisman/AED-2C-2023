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
    // Idea: armar tuplas con el primer elemento y despues la longitud de la escalera, ahi ordeno por la longitud de la escalera con merge y despues desarollo la lista de nuevo y la paso a array
    //O(NLog(N))
    public static int[] ordenarEscaleras(int[]l){
        if(l.length == 0){
            return new int[0];
        }
        int[] res = new int[l.length];
        for(int i = 0; i < res.length; i++){ // O(N)
            res[i] = l[i];
        }
        Queue<tupla> listaEscaleras = armarListaEscaleras(l); // O(N)
        tupla[] arrayEscaleras = queueToArray(listaEscaleras); // O(N)
        tupla[] escalerasOrdenadasPorLargo = ordenarTuplasPorApariciones(arrayEscaleras); // O(NLog(N))
        Queue<Integer> listaFinal = rearmarEscaleras(escalerasOrdenadasPorLargo); // O(N)
        res = listaToArray(listaFinal); // O(N)
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

    // Ejercicio 8

    // Ejercicio 9
    // Idea: Hago counting sort dos veces, como la cantidad de notas esta acotada del 1 al 10, toda la parte de categorizar las notas es O(N), y como tambien los generos estan acotados tambien es O(N)
    public static alumno[] ordenarPlanilla(alumno[] original){
        alumno[] res = new alumno[original.length];
        for(int i = 0; i < original.length; i++){ // O(N)
            res[i] = original[i];
        }
        Queue<alumno>[] bucketsPorNotas = bucketearPorNotas(res); // O(N)
        alumno[] notasOrdenadasSinDisticionDeGenero = desbucketearNotas(bucketsPorNotas, original.length); // O(N)
        Queue<alumno>[] bucketsPorGenero = bucketearPorGenero(notasOrdenadasSinDisticionDeGenero); // O(N)
        res = desbucketearGenero(bucketsPorGenero, original.length); // O(N)
        return res; 
    }

    public static Queue<alumno>[] bucketearPorNotas(alumno[] l){
        Queue<alumno>[] res = new LinkedList[10]; 
        for(int i = 0; i < 10; i++){
            res[i] = new LinkedList<>();
        }
        for(int i = 0; i < l.length; i++){
            res[l[i].getNota() - 1].offer(l[i]);
        }
        return res;
    } 

    public static alumno[] desbucketearNotas(Queue<alumno>[] buckets, int cantidadDeAlumnos){
        alumno[] res = new alumno[cantidadDeAlumnos];
        int elementoActual = 0;
        for(int i = 0; i < buckets.length; i++){
            while(buckets[i].isEmpty() != true){
                res[elementoActual] = buckets[i].poll();
                elementoActual ++;
            }
        }
        return res;
    }

    // Requiere que ya venga ordenado por notas
    public static Queue<alumno>[] bucketearPorGenero(alumno[] alumnos){
        Queue<alumno>[] res = new LinkedList[2]; // Como son entre muchas comillas dos generos la primera lista es para femenino y la segunda para masculino. En el B que te piden que hayan mas de un genero haria una lista de nGeneros que sigue estando acotado
        for(int i = 0; i < 2; i++){
            res[i] = new LinkedList<>();
        }
        for(int i = 0; i < alumnos.length; i++){
            alumno actual = alumnos[i];
            if(actual.getGenero() == "masculino"){
                res[1].offer(actual);
            }
            else{
                res[0].offer(actual);
            }
        }
        return res;
    }

    public static alumno[] desbucketearGenero(Queue<alumno>[] buckets, int cantidadAlumnos){
        alumno[] res = new alumno[cantidadAlumnos];
        int alumnoActual = 0;
        for(int i = 0; i < buckets.length; i++){
            while(buckets[i].isEmpty() != true){
                res[alumnoActual] = buckets[i].poll();
                alumnoActual ++;
            }
        }
        return res;
    }

    // Ejercicio 10

    // Ejercicio 11
    // Idea: Counting sort de cabeza
    // Complejidad: O(N + K) donde K al ser una constante queda O(N), porque la cantidad de operaciones esta acotada
    public static int[] ordenarEnRango(int[] original, int k){
        int[] res;
        int[] arrayApariciones = contarApariciones(original, k); // O(K)
        Queue<Integer> cola = armarCola(arrayApariciones); // O(K)
        res = colaToArray(cola, original.length); // O(N)
        return res;
    }

    public static int[] contarApariciones(int[] original, int k){
        int[] res = new int[k]; 
        for(int i = 0; i < k; i++){
            res[i] = 0;
        }
        for(int i = 0; i < original.length; i++){
            int posicionActual = original[i] - 1;
            res[posicionActual] += 1;
        }
        return res;
    }

    public static Queue<Integer> armarCola(int[] apariciones){
        Queue<Integer> res = new LinkedList();
        for(int i = 0; i < apariciones.length; i++){
            int aparicionesActual = apariciones[i];
            for(int j = 0; j < aparicionesActual; j++){
                res.offer(i + 1);
            }
        }
        return res;
    }

    public static int[] colaToArray(Queue<Integer> cola, int cantidadElementos){
        int[] res = new int[cantidadElementos];
        int elementoActual = 0;
        while(cola.isEmpty() != true){
            res[elementoActual] = cola.poll();
            elementoActual ++;
        }
        return res;
    }

    // Ejercicio 12
    // Requiere que la cantidad de elementos tenga raiz cuadrada
    // Idea: Hacer counting sort sobre la mayoria de los elementos que estan en el rango (20,40) y ordenarlos, despues hacer algun metodo O(NLog(N)) sobre los elementos que quedan, como va a ser raiz de n log raiz de n eso pertenece a O(N)
    public static int[] ordenarDatos(int[] datos){
        int[] countingEnRango = countingEnRango(datos, 20, 40); // O(N)
        int[] elementosFueraDeRango = crearListaFueraDeRango(datos, 20, 40); // O(N)
        heapSort(elementosFueraDeRango); // O(N)
        Queue<Integer> colaEnRango = armarColaEnRango(countingEnRango, 20); // O(N)
        int[] arrayEnRango = colaToArray(colaEnRango, colaEnRango.size()); // O(Elementos en rango)
        int[] res = mergearListas(arrayEnRango, elementosFueraDeRango); // O(N)
        return res;
    }

    public static int[] countingEnRango(int[] datos, int minimo, int maximo){
        int rango = maximo - minimo + 1;
        int[] res = new int[rango];
        for(int i = 0; i < rango; i++){
            res[i] = 0;
        }
        for(int i = 0; i < datos.length; i++){
            int elementoActual = datos[i];
            if(elementoActual >= minimo && elementoActual <= maximo){
                res[elementoActual - minimo] ++;
            }
        }
        return res;
    }

    // Requiere que la longitud de la lista sea el cuadrado de un numero 
    public static int[] crearListaFueraDeRango(int[] datos, int minimo, int maximo){
        int[] res = new int[(int)Math.sqrt(datos.length)];
        int posActual = 0;
        for(int i = 0; i < datos.length; i++){
            if(datos[i] < minimo || datos[i] > maximo){
                res[posActual] = datos[i];
                posActual++;
            }
        }
        return res;
    }

    public static Queue<Integer> armarColaEnRango(int[] apariciones, int minimo){
        Queue<Integer> res = new LinkedList();
        for(int i = 0; i < apariciones.length; i++){
            int aparicionesActual = apariciones[i];
            for(int j = 0; j < aparicionesActual; j++){
                res.offer(i + minimo);
            }
        }
        return res;
    }

    // Mergeo las dos listas ordenadas 
    // O(Elementos en rango)
    public static int[] mergearListas(int[] listaEnRango, int[] listaFueraDeRango){
        int[] res = new int[listaEnRango.length + listaFueraDeRango.length];
        int elementoActual = 0;
        int i = 0;
        // Agrego al principio de res a los elementos abajo del rango
        while(i < listaFueraDeRango.length && listaFueraDeRango[i] < listaEnRango[0]){
            res[elementoActual] = listaFueraDeRango[i];
            elementoActual ++;
            i++;
        }
        // Agrego a los elementos en rango que estarian "en el medio"
        for(int j = 0; j < listaEnRango.length; j++){
            res[elementoActual] = listaEnRango[j];
            elementoActual++;
        }
        // Agrego al final a los elementos fuera de rango mayores
        while(i < listaFueraDeRango.length){
            res[elementoActual] = listaFueraDeRango[i];
            elementoActual++;
            i++;
        }
        return res;
    }

    // Ejercicio 13
    // Idea: Usar radix + counting sort para ordenar por strings, despues uso algun algoritmo de ordenacion estable para que en caso de empatar en numero desempata que string es mas chico que va a antes.
    // Complejidad: 0(NLog(N))
    // Si los numeros tambien estan acotados hago counting sort sobre los numeros, va a seguir siendo estable
    public static tuplaIntStr[] ordenarTuplas(tuplaIntStr[] original){
        tuplaIntStr[] res = new tuplaIntStr[original.length];
        for(int i = 0; i < original.length; i++){ // O(N)
            res[i] = original[i];
        }
        int maximaLong = maximaLongitud(original); // O(N)
        for(int i = maximaLong; i >=0; i--){ // O(L) 
            Queue<tuplaIntStr>[] buckets = bucketearPorString(original, i); // O(LN) = O(N)
            res = desbucketearPorString(buckets, original.length); // O(LN) = O(N)
        }
        mergeSortPorNumero(res, 0 , res.length - 1); // O(NLog(N))
        return res;
    }

    public static int maximaLongitud(tuplaIntStr[] l){
        int res = 0;
        for(int i = 0; i < l.length; i++){
            if(l[i].getTexto().length() > res){
                res = l[i].getTexto().length();
            }
        }
        return res;
    }

    public static Queue<tuplaIntStr>[] bucketearPorString(tuplaIntStr[] original, int posActual){
        Queue<tuplaIntStr>[] buckets = new LinkedList[257]; // Creo 257 buckets, el primero para cuando no tiene un caracter esa posicion y los 256 restantes para todos los caracteres ascii
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for(int i = 0; i < original.length; i ++){
            tuplaIntStr actual = original[i];
            // Si tiene un caracter en esa posicion lo añado a su bucket de valor ascii
            if(posActual < actual.getTexto().length()){
                char caracterActual = actual.getTexto().charAt(posActual);
                int valorAscii = (int) caracterActual;
                buckets[valorAscii + 1].offer(actual);
            }
            // Si no tiene un caracter lo mando al primer bucket
            else{
                buckets[0].offer(actual);
            }
        }
        return buckets;
    }

    public static tuplaIntStr[] desbucketearPorString(Queue<tuplaIntStr>[] buckets, int cantidadElementos){
        tuplaIntStr[] res = new tuplaIntStr[cantidadElementos];
        int elementoActual = 0;
        for(int i = 0; i < buckets.length; i++){
            while(buckets[i].isEmpty() != true){
                res[elementoActual] = buckets[i].poll();
                elementoActual++;
            }
        }
        return res;
    }


    public static void mergeSortPorNumero(tuplaIntStr arr[], int izq, int derecha)
    {
        if (izq < derecha) {
            int medio = izq + (derecha - izq) / 2;
            mergeSortPorNumero(arr, izq, medio);
            mergeSortPorNumero(arr, medio + 1, derecha);
            mergePorNumero(arr, izq, medio, derecha);
        }
    }

    public static void mergePorNumero(tuplaIntStr arr[], int izq, int medio, int derecha)
    {
        int n1 = medio - izq + 1;
        int n2 = derecha - medio;

        tuplaIntStr L[] = new tuplaIntStr[n1];
        tuplaIntStr R[] = new tuplaIntStr[n2];
 
        for (int i = 0; i < n1; i++)
            L[i] = arr[izq + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[medio + 1 + j];

        int i = 0, j = 0;
 
        int k = izq;
        while (i < n1 && j < n2) {
            if (L[i].getNum() <= R[j].getNum()) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }



    
}
