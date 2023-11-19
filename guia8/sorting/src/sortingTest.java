import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        int[] listaOriginal = new int[]{5, 6, 8, 9, 10, 7, 8, 9, 20, 15};
        int[] respuestaEsperadaOriginal = new int[]{7, 8, 9, 8, 9, 10, 5, 6, 15, 20};
        assertArrayEquals(respuestaEsperadaOriginal, sorting.ordenarEscaleras(listaOriginal));
    }

    @Test
    public void ordenarPlanilla() {
        alumno ana = new alumno(10, "femenino", "Ana");
        alumno juan = new alumno(6, "masculino", "Juan");
        alumno rita = new alumno(6, "femenino", "Rita");
        alumno paula = new alumno(7, "femenino", "Paula");
        alumno jose = new alumno(7, "masculino", "Jose");
        alumno pedro = new alumno(8, "masculino", "Pedro");

        alumno[] planillaOriginal = {ana, juan, rita, paula, jose, pedro};

        alumno[] planillaEsperada = {rita, paula, ana, juan, jose, pedro};

        // Llamada a la función de ordenación
        planillaOriginal = sorting.ordenarPlanilla(planillaOriginal);

        assertArrayEquals(planillaEsperada, planillaOriginal);
    }

    @Test
    public void testOrdenarEnRango() {
        // Caso original
        int[] listaOriginal1 = {9, 5, 2, 8, 3};
        int[] listaEsperada1 = {2, 3, 5, 8, 9};
        listaOriginal1 = sorting.ordenarEnRango(listaOriginal1, 9);
        assertArrayEquals(listaEsperada1, listaOriginal1);

        int[] listaOriginal2 = {3, 5, 2, 5, 3};
        int[] listaEsperada2 = {2, 3, 3, 5, 5};
        listaOriginal2 = sorting.ordenarEnRango(listaOriginal2, 5);
        assertArrayEquals(listaEsperada2, listaOriginal2);

        int[] listaOriginal3 = {7, 7, 7, 7, 7};
        int[] listaEsperada3 = {7, 7, 7, 7, 7};
        listaOriginal3 = sorting.ordenarEnRango(listaOriginal3, 7);
        assertArrayEquals(listaEsperada3, listaOriginal3);

        int[] listaOriginal4 = {1, 2, 3, 4, 5};
        int[] listaEsperada4 = {1, 2, 3, 4, 5};
        listaOriginal4 = sorting.ordenarEnRango(listaOriginal4, 5);
        assertArrayEquals(listaEsperada4, listaOriginal4);
    }

    @Test
    public void ordenarDatos(){
        int[] listaOriginal1 = {15, 22, 20, 40, 35, 41, 10, 22, 35};
        int[] listaEsperada1 = {10, 15, 20, 22, 22, 35, 35, 40, 41};
        listaOriginal1 = sorting.ordenarDatos(listaOriginal1);
        assertArrayEquals(listaEsperada1, listaOriginal1);
    }

    @Test
    public void ordenarTuplasIntString() {
        // Crear tuplas para la entrada
        tuplaIntStr primerElemento = new tuplaIntStr(29, "Pepito");
        tuplaIntStr segundoElemento = new tuplaIntStr(19, "Ana");
        tuplaIntStr tercerElemento = new tuplaIntStr(25, "Juan");
        tuplaIntStr cuartoElemento = new tuplaIntStr(25, "Maria");
        tuplaIntStr quintoElemento = new tuplaIntStr(29, "Papita");

        // Crear la lista de entrada desordenada
        tuplaIntStr[] listaOriginal = {segundoElemento, quintoElemento, primerElemento, cuartoElemento, tercerElemento};

        // Llamar al método de ordenación
        listaOriginal = sorting.ordenarTuplas(listaOriginal);

        // Crear la lista esperada ordenada por los strings
        tuplaIntStr[] listaEsperada = {segundoElemento, tercerElemento, cuartoElemento, quintoElemento, primerElemento};
        

        // Verificar que la lista de salida es igual a la lista esperada
        assertArrayEquals(listaEsperada, listaOriginal);
    }

    @Test
    public void testOrdenarMultiplos() {
        int[] inputArray1 = {5, 3, 7, 2};
        int k1 = 3;
        int[] expectedResult1 = {2, 3, 4, 5, 6, 6, 7,  9, 10, 14, 15, 21};
        int[] result1 = sorting.ordenarMultiplos(inputArray1, k1);
        assertArrayEquals(expectedResult1, result1);

        
        int[] inputArray2 = {0, 1, 4};
        int k2 = 2;
        int[] expectedResult2 = {0, 0, 1, 2, 4, 8};
        int[] result2 = sorting.ordenarMultiplos(inputArray2, k2);
        assertArrayEquals(expectedResult2, result2);

        
        int[] inputArray3 = {3, 3, 3};
        int k3 = 2;
        int[] expectedResult3 = {3, 3, 3, 6, 6, 6};
        int[] result3 = sorting.ordenarMultiplos(inputArray3, k3);
        assertArrayEquals(expectedResult3, result3);
    }

    @Test 
    public void testHayAgujeros(){
        int[] input1 = {5, 6, 7, 8, 9};
        assertFalse(sorting.tieneAgujeros(input1));

        int[] input2 = {9,5,6,8,8,9,7};
        assertFalse(sorting.tieneAgujeros(input2));

        int[] input3 = {9,4,6,8,8,9,7};
        assertTrue(sorting.tieneAgujeros(input3));
    }

    @Test
    public void testTerminarDeOrdenar(){
        int[] input1 = sorting.terminarDeOrdenar(new int[]{1,2,3,4,5});
        int[] output1 = {1,2,3,4,5};
        assertArrayEquals(input1, output1);

        int[] input2 = sorting.terminarDeOrdenar(new int[]{2,1,4,3,5});
        int[] output2 = {1,2,3,4,5};
        assertArrayEquals(input2, output2);
    }

    @Test
    public void testNMenoresQueN(){
        int[] input = {3,2,4,2,1};
        int[] rtaEsperada = {1,2,2,3,4};
        input = sorting.ordenarNMenoresQueN(input);
        assertArrayEquals(input, rtaEsperada);
    }

    @Test 
    public void testNMenoresQueNCuadrado(){
        int[] input = {40,25,29,83,12,50,67,13,99,76};
        int[] rtaEsperada = {12,13,25,29,40,50,67,76,83,99};
        assertArrayEquals(rtaEsperada, sorting.ordenarNMenoresQueNCuadrado(input));
    }
}
