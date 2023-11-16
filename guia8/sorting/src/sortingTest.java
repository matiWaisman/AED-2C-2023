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
        int[] lista = new int[]{1, 3, 1, 7, 2, 7, 1, 7, 3};
        int[] respuestaEsperada = new int[]{1, 1,1,2,3,3,7,7,7};

        assertArrayEquals(respuestaEsperada, sorting.ordenarPorApariciones(lista));

        List<Integer>[] matrizDeListas = sorting.bucketearPorApariciones(respuestaEsperada);
       
        List<Integer> listaPos0 = Arrays.asList(2);
        List<Integer> listaPos1 = Arrays.asList(3);
        List<Integer> listaPos2 = Arrays.asList(1, 7);
        
        assertEquals(listaPos0, matrizDeListas[0]);
        assertEquals(listaPos1, matrizDeListas[1]);
        assertEquals(listaPos2, matrizDeListas[2]);
    }
}
