package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;

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
        return calcularCardinal(_raiz);
    }

    private int calcularCardinal(Nodo actual) {
        if(actual == null){
            return 0;
        }
        if (actual.valor == null) {
            return 0;
        } else {
            return calcularCardinal(actual.derecha) + calcularCardinal(actual.izq) + 1;
        }
    }

    public T minimo(){
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
        if(_raiz.izq != null){ // Si existe una rama derecha a la raiz va a estar ahi, asi que no hace falta arrancar x la raiz
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
        }
        else{
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
        //if(elem.compareTo(maximo()) > 0 || elem.compareTo(minimo()) < 0){
        //    return false;
        //}
        //else{
            return calcularPertenece(_raiz, elem);
        //}
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
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            return _actual.izq != null || _actual.derecha != null;
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
