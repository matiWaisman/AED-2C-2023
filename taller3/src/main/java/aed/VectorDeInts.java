package aed;

import java.util.Arrays;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;
    private int[]_vector;

    public VectorDeInts() {
        _vector = new int[CAPACIDAD_INICIAL - 1];
    }

    public VectorDeInts(VectorDeInts vector) {
        _vector = new int[vector._vector.length];
        for (int i = 0; i < vector._vector.length; i++) {
            _vector[i] = vector._vector[i];
        }
    }

    public int longitud() {
        return _vector.length;
    }

    public void agregarAtras(int i) {
        int[] aux = new int[_vector.length + 1] ;
        for(int j = 0; j < _vector.length; j++){
            aux[j] = _vector[j];
        }
        aux[_vector.length ] = i;
        _vector = aux;
    }

    public int obtener(int i) {
        return _vector[i];
    }

    public void quitarAtras() {
        int[] aux = new int[_vector.length - 1];
        for (int i = 0; i < _vector.length - 1; i++){
            aux[i] = _vector[i];
        }
        _vector = aux;
    }

    public void modificarPosicion(int indice, int valor) {
        _vector[indice] = valor;
    }

    public VectorDeInts copiar() {
        VectorDeInts aux = new VectorDeInts();
        aux._vector = Arrays.copyOf(_vector, _vector.length);
        return aux;
    }

}
