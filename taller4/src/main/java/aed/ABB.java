package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int cantElementos = 0;

    private class Nodo {
    private T valor;
    private Nodo padre;
    private Nodo izq;
    private Nodo derecha;

    Nodo(T v, Nodo p, Nodo i, Nodo d) {
        valor = v;
        padre = p;
        izq = i;
        derecha = d;
    }
}

    public ABB() {
        _raiz = new Nodo(null, null, null, null);
    }

    public int cardinal() {
        return cantElementos;
    }

    public T minimo(){
        if(_raiz.valor == null){
            return null;
        }
        if(_raiz.izq != null){ // Si existe una izquierda a la raiz va a estar ahi, asi que no hace falta arrancar x la raiz
            return calcularMinimo(_raiz.izq, _raiz.valor);
        }
        else{ // Si no tiene rama izquierda es pq el menor es la raiz
            return _raiz.valor;
        }
    }

    private T calcularMinimo(Nodo actual, T minActual) {
        if (actual == null) {
            return minActual; 
        }
    
        if (actual.valor.compareTo(minActual) < 0) {
            minActual = actual.valor; 
        }
    
        T minIzquierdo = calcularMinimo(actual.izq, minActual);
        T minDerecho = calcularMinimo(actual.derecha, minActual);
    
        if(minIzquierdo.compareTo(minDerecho) < 0){
            return minIzquierdo;
        }
        else{
            return minDerecho;
        }
    }

    public T maximo(){
        if(_raiz.valor == null){
            return null;
        }
        if(_raiz.derecha != null){ // Si existe una rama derecha a la raiz va a estar ahi, asi que no hace falta arrancar x la raiz
            return calcularMaximo(_raiz.derecha, _raiz.valor);
        }
        else{ // Si no tiene rama derecha es pq el mayor es la raiz
            return _raiz.valor;
        }
    }

     private T calcularMaximo(Nodo actual, T maxActual){
        if (actual == null) {
            return maxActual; 
        }
    
        if (actual.valor.compareTo(maxActual) > 0) {
            maxActual = actual.valor; 
        }
    
        T maxIzquierdo = calcularMaximo(actual.izq, maxActual);
        T maxDerecho = calcularMaximo(actual.derecha, maxActual);
    
        if(maxIzquierdo.compareTo(maxDerecho) > 0){
            return maxIzquierdo;
        }
        else{
            return maxDerecho;
        }
    }

    public void insertar(T elem){
        if(_raiz.valor == null){
            _raiz.valor = elem;
            cantElementos += 1;
        }
        else{
            if(pertenece(elem) == false){
                cantElementos += 1;
            }
            insertarRecursivo(elem, _raiz);
            
        }
    }

    private void insertarRecursivo(T elem, Nodo actual){
        if(elem.compareTo(actual.valor) > 0){
            if (actual.derecha == null) {
                actual.derecha = new Nodo(elem, actual, null, null);
            } else {
                insertarRecursivo(elem, actual.derecha);
            }
        }
        if(elem.compareTo(actual.valor) < 0){
            if (actual.izq == null) {
                actual.izq = new Nodo(elem, actual, null, null);
            } else {
                insertarRecursivo(elem, actual.izq);
            }
        }
    }

    public boolean pertenece(T elem){
        if(_raiz == null){
            return false;
        }
        if(_raiz.valor == null){
            return false;
        }
        if(elem.compareTo(maximo()) > 0 || elem.compareTo(minimo()) < 0){
            return false;
        }
        else{
            return calcularPertenece(_raiz, elem);
        }
    }

    private boolean calcularPertenece(Nodo actual, T elem){
        if(actual == null){
            return false;
        }
        if (actual.valor == null) {
            return false;
        }
        if(elem.compareTo(actual.valor) == 0){
            return true;
        }
        else{
            return calcularPertenece(actual.derecha, elem) || calcularPertenece(actual.izq, elem);
        }
    }

    public void eliminar(T elem){
        Nodo aEliminar = encontrarNodo(_raiz, elem);
        Nodo nodoPadre = aEliminar.padre;
        cantElementos -= 1;
        Boolean esRaiz = false;
        if(aEliminar.valor.compareTo(_raiz.valor) == 0){ 
            esRaiz = true;  
        }
        if(_raiz == null){
            cantElementos = 0;
        }
        else if(aEliminar.izq == null && aEliminar.derecha == null){ // Si el nodo a eliminar no tiene hijos
            if(esRaiz){
                _raiz = null;
            }
            else if(aEliminar.valor.compareTo(nodoPadre.valor) > 0){ // Si el hijo es mayor elimino el de la derecha
                nodoPadre.derecha = null;
            } 
            else{ // Si el hijo es menor elimino el de la izquierda
                nodoPadre.izq = null; 
            }  
        }
        else if(aEliminar.izq != null ^ aEliminar.derecha != null){ // Tiene un solo hijo 
            if(aEliminar.izq != null){ // Tiene hijo izquierdo
                if (esRaiz){
                    _raiz = _raiz.izq; 
                    _raiz.padre = null;
                }
                else if(aEliminar.valor.compareTo(aEliminar.padre.valor) < 0){  
                    aEliminar.padre.izq = aEliminar.izq;
                    aEliminar.izq.padre = aEliminar.padre;
                } else{
                    aEliminar.padre.derecha = aEliminar.izq;
                    aEliminar.izq.padre = aEliminar.padre;
                }
            }
            if(aEliminar.derecha != null){ // Tiene hijo derecho
                if (esRaiz){
                    _raiz = _raiz.derecha; 
                    _raiz.padre = null;
                }
                else if(aEliminar.valor.compareTo(aEliminar.padre.valor) < 0){
                    aEliminar.padre.izq = aEliminar.derecha;
                    aEliminar.derecha.padre = aEliminar.padre;
                }
                else{
                    aEliminar.padre.derecha = aEliminar.derecha;
                    aEliminar.derecha.padre = aEliminar.padre;
                }
            }
        }
        else if(aEliminar.izq != null && aEliminar.derecha != null){ // Tiene dos hijos
            Nodo suc = encontrarNodo(aEliminar, calcularMinimo(aEliminar.derecha, aEliminar.derecha.valor));
            if (suc.padre == aEliminar) {
                suc.padre.derecha  = suc.derecha;
                if(suc.derecha != null){
                    suc.derecha.padre = suc.padre;
                }
            } else {
                suc.padre.izq = suc.derecha;
                if (suc.derecha != null){
                    suc.derecha.padre = suc.padre;
                }
            }
            aEliminar.valor = suc.valor;
            if (esRaiz){
                _raiz = aEliminar;
            }
        }
    }

    private Nodo encontrarNodo(Nodo actual, T valor){
        if(actual != null){
            if(actual.valor.equals(valor)){
                return actual;
            }
            if(valor.compareTo(actual.valor) > 0){ // Si es mayor busco por derecha
                return encontrarNodo(actual.derecha, valor);
            }
            if(valor.compareTo(actual.valor) < 0){ // Si es menor busco por izquierda
                return encontrarNodo(actual.izq, valor);
            }
        }
        return null;
    }

    public String toString() {
        String result = calcularToString(_raiz);
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1); // Eliminar la coma final
        }
        return "{" + result + "}";
    }

    private String calcularToString(Nodo actual) {
        if(actual == null){
            return "";
        }
        else{
            return calcularToString(actual.izq) + actual.valor.toString() + "," + calcularToString(actual.derecha);
        }
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public ABB_Iterador() {
            _actual = null;
        }

        public boolean haySiguiente() {            
            return _actual.valor != maximo();
        }
    
        public T siguiente() {
         T valorSiguiente;
          if(_actual == null){
            valorSiguiente = minimo();
          }
          else{
            valorSiguiente = calcularSiguiente(_actual);
          }
          _actual = encontrarNodo(_raiz, valorSiguiente);
          return valorSiguiente;
        }

        private T calcularSiguiente(Nodo actual){
            if(actual.derecha != null){
               return calcularMinimo(actual.derecha, actual.derecha.valor);
            }
            Nodo padreActual = actual.padre;
            while(padreActual != null && actual == padreActual.derecha){
                actual = padreActual;
                padreActual = padreActual.padre;
            }
            return padreActual.valor;
        }

    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

    // Extra Ejercicio 2 guia 7
    public ABB<T> interseccion(ABB<T> a1, ABB<T> a2){
        ABB<T> res = new ABB<T>();
        Iterador<T> iterador = a1.iterador();
        while(iterador.haySiguiente()){
            T valorActual = iterador.siguiente();
            if(a2.pertenece(valorActual)){
                res.insertar(valorActual);
            }
        }  
        return res;
    }

    public ABB<T> union(ABB<T> a1, ABB<T> a2){
        ABB<T> res = new ABB<T>();
        // Copia los elementos de a1 al nuevo árbol res
        Iterador<T> iteradorA1 = a1.iterador();
        while (iteradorA1.haySiguiente()) {
            T valorActual = iteradorA1.siguiente();
            res.insertar(valorActual);
        }
        // Agrega los elementos de a2 al nuevo árbol res
        Iterador<T> iteradorA2 = a2.iterador();
        while (iteradorA2.haySiguiente()) {
            T valorActual = iteradorA2.siguiente();
            res.insertar(valorActual);
        }
        return res;
    }

}
