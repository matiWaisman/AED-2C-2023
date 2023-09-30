package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo _primero;
    private Nodo _ultimo;

    private class Nodo {
        T valor; 
        Nodo sig;
        Nodo anterior;

        Nodo (T v){
            valor = v;
        }
    }

    public ListaEnlazada() {
        _primero = null;
        _ultimo = null;
    }

    public int longitud() {
        int i = 0;
        Nodo puntero = _primero;
        while(puntero != null){
            i ++;
            puntero = puntero.sig;
        }
        return i;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if(_primero == null){
            _ultimo = nuevo;
            _primero = nuevo;
        }
        else{
            _primero.anterior = nuevo;
            nuevo.sig = _primero;
            _primero = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if(_ultimo == null){
            _ultimo = nuevo;
            _primero = nuevo;
        }
        else{
            nuevo.anterior = _ultimo;
            _ultimo.sig = nuevo;
            _ultimo = nuevo;
        }
    }

    public T obtener(int i) {
        Nodo puntero = _primero;
        for(int j = 0; j < i; j++){
            puntero = puntero.sig;
        }
        return puntero.valor;
    }

    public void eliminar(int i) {
        Nodo aux = _primero;
        for(int j = 0; j < i && aux != null; j++){
            aux = aux.sig;
        }
        if (aux.anterior != null) {
            aux.anterior.sig = aux.sig;
        }
        if (aux.sig != null) {
            aux.sig.anterior = aux.anterior;
        }
        if(i == 0){
            _primero = aux.sig;
        }
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo aux = _primero;
        for(int j = 0; j < indice && aux != null; j++){
            aux = aux.sig;
        }
        aux.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        int i = 0;
        Nodo puntero = _primero;
        String res ="";
        while(puntero != null){
            res += puntero.valor + ",";
            i ++;
            puntero = puntero.sig;
        }
        return res;
    }

    private class ListaIterador implements Iterador<T> {
    	Nodo puntero;

        ListaIterador(){
            puntero = _primero;
        }

        public boolean haySiguiente() {
	        return puntero.sig != null;
        }
        
        public boolean hayAnterior() {
	        return puntero.anterior != null;
        }

        public T siguiente() {
            puntero = puntero.sig;
            return puntero.valor;
        }
        

        public T anterior() {
            puntero = puntero.anterior;
            return puntero.valor;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
