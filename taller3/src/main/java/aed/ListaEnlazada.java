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

    // Extra Ejercicio 8 guia 7
    // O(N)
    public boolean estaOrdenado(ListaEnlazada<T> l){
        boolean res = true;
        Nodo puntero = l._primero;
        while(puntero.sig != null){
            if(((int) puntero.valor) > ((int) puntero.sig.valor) ){
                res = false;
            }
            puntero = puntero.sig;
        }
        return res;
    }
    // O(1) si es el primero el ultimo elemento, O(N) si esta en el medio
    public void agregarOrdenado(T v){
        Nodo nuevoNodo = new Nodo(v);
        if(_primero == null){
            _primero = nuevoNodo;
            _ultimo = nuevoNodo;
        }
        else if(_primero.valor == _ultimo.valor){
            if((int)nuevoNodo.valor < (int)_primero.valor){
                _primero.anterior = nuevoNodo;
                nuevoNodo.sig = _primero;
                _ultimo = _primero;
                _primero = nuevoNodo;
            }
            else if((int)nuevoNodo.valor > (int)_primero.valor){
                _primero.sig = nuevoNodo;
                nuevoNodo.anterior = _primero;
                _ultimo = nuevoNodo;
            }
        }
        else if((int)v < (int)_primero.valor){
            _primero.anterior = nuevoNodo;
            nuevoNodo.sig = _primero;
            _primero = nuevoNodo;
        }
        else if((int)v > (int)_ultimo.valor){
            nuevoNodo.anterior = _ultimo;
            _ultimo.sig = nuevoNodo;
            _ultimo = nuevoNodo;
        }
        else{
            Nodo puntero = _primero;
            boolean sigo = true;
            while(puntero.sig != null && sigo){
                if((int)nuevoNodo.valor > (int)puntero.valor && (int)nuevoNodo.valor < (int)puntero.sig.valor){
                    nuevoNodo.sig = puntero.sig;
                    nuevoNodo.anterior = puntero;
                    puntero.sig.anterior = nuevoNodo;
                    puntero.sig = nuevoNodo;
                    sigo = false;
                }
                puntero = puntero.sig;
            }
        }
    }

    public ListaEnlazada<T> unionListas(ListaEnlazada<T> l1, ListaEnlazada<T> l2){
        ListaEnlazada<T> res = new ListaEnlazada<>();
        // Union caso O(1)
        if((int)l1._ultimo.valor < (int)l2._primero.valor){
            res._primero = l1._primero; // O(N)
            l1._ultimo.sig = l2._primero;
            l2._primero.anterior = l1._ultimo;
            res._ultimo = l2._ultimo;
        }
        if((int)l1._primero.valor > (int)l2._ultimo.valor){
            res._primero = l2._primero;
            l1._primero.anterior = l2._ultimo;
            l2._ultimo.sig = res._primero;
            res._ultimo = l1._ultimo;
        }
        // Union caso O(N)
        else{
            Nodo puntero1 = l1._primero;
            Nodo puntero2 = l2._primero;
            if((int)puntero1.valor < (int)puntero2.valor){
                res._primero = puntero1;
                puntero1 = puntero1.sig;
            }
            else{
                res._primero = puntero2;
                puntero2 = puntero2.sig;
            }
            Nodo punteroRes = res._primero;
            while(puntero1 != null && puntero2 != null){
                Nodo anteriorRes = punteroRes;
                if((int)puntero1.valor < (int)puntero2.valor){
                    punteroRes.sig = puntero1;
                    puntero1 = puntero1.sig;
                }
                else{
                    punteroRes.sig = puntero2;
                    puntero2 = puntero2.sig;
                }
                punteroRes = punteroRes.sig;
                punteroRes.anterior = anteriorRes;
            }
            if(puntero1 != null){
                punteroRes.sig = puntero1;
                puntero1.anterior = punteroRes;
            }
            if(puntero2 != null){
                punteroRes.sig = puntero2;
                puntero2.anterior = punteroRes;
            }
            res._ultimo = punteroRes;
        }
        return res;
    }

    public ListaEnlazada<T> interseccionListas(ListaEnlazada<T> l1, ListaEnlazada<T> l2){
        // Caso 0(1)
        if((int)l1._primero.valor > (int)l2._ultimo.valor){
            return null;
        }
        if((int)l2._primero.valor > (int)l1._ultimo.valor){
            return null;
        }
        // Caso O(N)
        else{
            ListaEnlazada<T> res = new ListaEnlazada<>();
            Nodo puntero1 = l1._primero;
            Nodo puntero2 = l2._primero;
            boolean encontrePrimero = false;
            while(puntero1 != null && puntero2 != null && !encontrePrimero){
                if(puntero1.valor == puntero2.valor){
                    encontrePrimero = true;
                    res._primero = puntero1;
                    puntero1 = puntero1.sig;
                    puntero2 = puntero2.sig;
                }
                else if((int)puntero1.valor > (int)puntero2.valor){
                    puntero2 = puntero2.sig;
                }
                else if((int)puntero1.valor < (int)puntero2.valor){
                    puntero1 = puntero1.sig;
                }
                
            }
            if((puntero1 == null || puntero2 == null) && !encontrePrimero){
                return null;
            }
            else{
                Nodo punteroRes = res._primero;
                while(puntero1 != null && puntero2 != null){
                    if(puntero1.valor == puntero2.valor){
                        Nodo punteroResViejo = punteroRes;
                        punteroRes.sig = puntero1;
                        punteroRes = punteroRes.sig;
                        punteroRes.anterior = punteroResViejo;
                        puntero1 = puntero1.sig;
                        puntero2 = puntero2.sig;
                    }
                    else if((int)puntero1.valor > (int)puntero2.valor){
                        puntero2 = puntero2.sig;
                    }
                    else if((int)puntero1.valor < (int)puntero2.valor){
                        puntero1 = puntero1.sig;
                    }
                }
                res._ultimo = punteroRes;
            }
            return res;
        }
    }



}
