package aed;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 1;
    private int[]_vector;
    //Todo longitud

    public VectorDeInts() {
        _vector = new int[CAPACIDAD_INICIAL - 1];
    }

    public VectorDeInts(VectorDeInts vector) {
        //TODO
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
        for (int i = 0; i < _vector.length; i++){

        }
    }

    public void modificarPosicion(int indice, int valor) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public VectorDeInts copiar() {
        throw new UnsupportedOperationException("No implementada aun");
    }

}
