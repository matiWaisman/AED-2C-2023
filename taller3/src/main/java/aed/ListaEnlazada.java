package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo _primero;
    private int _longitud;
    private Nodo _ultimo;

    private class Nodo {
        T _valor; 
        Nodo _sig;
        Nodo _anterior;

        Nodo (T v){
            _valor = v;
            _sig = null;
            _anterior = null;
        }
    }

    public ListaEnlazada() {
        _primero = null;
        _longitud = 0;
        _ultimo = null;
    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if(_longitud == 0){
            _primero = nuevo;
        }
        else{
            _ultimo = _primero;
            nuevo._anterior = _primero;
            _primero = nuevo;
        }
        _longitud +=1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        _primero = nuevo;
        _longitud +=1;
    }

    public T obtener(int i) {
        T res;
        for(int j = 0; j < i; j++){
            _primero = _primero._sig;
        }
        res = _primero._valor;
        return res;
    }

    public void eliminar(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada<T> copiar() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	Nodo puntero;

        ListaIterador(){
            puntero = _primero;
        }

        public boolean haySiguiente() {
	        return puntero._sig != null;
        }
        
        public boolean hayAnterior() {
	        return puntero._anterior != null;
        }

        public T siguiente() {
            puntero = puntero._sig;
            return puntero._valor;
        }
        

        public T anterior() {
            puntero = puntero._anterior;
            return puntero._valor;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
