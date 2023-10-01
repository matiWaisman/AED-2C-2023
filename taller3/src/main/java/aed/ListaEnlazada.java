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
        ListaEnlazada<T> res = new ListaEnlazada();
        Nodo puntero = _primero;
        for(int i = 0; i < longitud(); i++){
            res.agregarAtras(puntero.valor);
            puntero = puntero.sig;
        }
        return res;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
            Iterador <T> iterador = lista.iterador();   
            while(iterador.haySiguiente()){
                this.agregarAtras(iterador.siguiente());
            }
    }
    
    @Override
    public String toString() {
        Nodo puntero = _primero;
        String res ="[";
        while(puntero != null){
            res += puntero.valor;
            if(puntero.sig != null){
                res += ", ";
            }
            puntero = puntero.sig;
        }
        res += "]";
        return res;
    }

    private class ListaIterador implements Iterador<T> {
    	private int _indice;

        ListaIterador(){
            _indice = 0;
        }

        public boolean haySiguiente() {
            return _indice < longitud();
            
        }
        
        public boolean hayAnterior() {
            return _indice > 0;
        }

        public T siguiente() {
            T res = obtener(_indice);
            _indice++;
            return res;
        }
        

        public T anterior() {
            _indice--;
            T res = obtener(_indice);
            return res;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
