import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import org.junit.Test;

public class sortingTest {
    @Test
    public void testKElementosMasChicos() {
        int[] lista = new int[]{100, 25, 3, 989, 50, 99, 78, 10, 100, 1};
        int[] respuestaEsperada = new int[]{1, 3};
        assertArrayEquals(respuestaEsperada, sorting.kElementosMasChicos(2, lista));
    }

    @Test
    public void testOrdenarPorApariciones() {
        // Caso de prueba original
        int[] listaOriginal = new int[]{7, 3, 1, 1, 2, 7, 1, 7, 3};
        int[] respuestaEsperadaOriginal = new int[]{1, 1, 1, 7, 7, 7, 3, 3, 2};
        assertArrayEquals(respuestaEsperadaOriginal, sorting.ordenarPorApariciones(listaOriginal));

        // Caso de prueba con lista vacía
        int[] listaVacia = new int[]{};
        int[] respuestaEsperadaVacia = new int[]{};
        assertArrayEquals(respuestaEsperadaVacia, sorting.ordenarPorApariciones(listaVacia));

        // Caso de prueba con un solo elemento
        int[] listaUnElemento = new int[]{5};
        int[] respuestaEsperadaUnElemento = new int[]{5};
        assertArrayEquals(respuestaEsperadaUnElemento, sorting.ordenarPorApariciones(listaUnElemento));

        // Caso de prueba con todos los elementos iguales
        int[] listaTodosIguales = new int[]{4, 4, 4, 4, 4};
        int[] respuestaEsperadaTodosIguales = new int[]{4, 4, 4, 4, 4};
        assertArrayEquals(respuestaEsperadaTodosIguales, sorting.ordenarPorApariciones(listaTodosIguales));

        // Caso de prueba con algunos elementos iguales y otros diferentes
        int[] listaConDuplicados = new int[]{2, 1, 3, 1, 2, 3, 4, 4, 5};
        int[] respuestaEsperadaConDuplicados = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5};
        assertArrayEquals(respuestaEsperadaConDuplicados, sorting.ordenarPorApariciones(listaConDuplicados));
    }

    @Test
    public void testOrdenarEscaleras() {
        // Caso de prueba original
        int[] listaOriginal = new int[]{5,6,8,9,10,7,8,9,20,15};
        int[] respuestaEsperadaOriginal = new int[]{1, 1, 1, 7, 7, 7, 3, 3, 2};
        assertArrayEquals(respuestaEsperadaOriginal, sorting.ordenarEscaleras(listaOriginal));

        
    }


}
