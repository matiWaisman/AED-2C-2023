package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoberturaTests {
    Cobertura cobertura = new Cobertura();

    @Test
    void testFizzBuzz() {
        assertEquals("Fizz",cobertura.fizzBuzz(3));
        assertEquals("FizzBuzz",cobertura.fizzBuzz(15));
        assertEquals("Buzz",cobertura.fizzBuzz(5));
        assertEquals("2",cobertura.fizzBuzz(2));
    }

    @Test
    void testNumeroCombinatorio() {
        assertEquals(1,cobertura.numeroCombinatorio(5,0));
        assertEquals(1,cobertura.numeroCombinatorio(5,5));
        assertEquals(0,cobertura.numeroCombinatorio(5,6));
        assertEquals(15,cobertura.numeroCombinatorio(6,4));
    }

    @Test
    void testRepeticionesConsecutivas() {
        assertEquals(0,cobertura.repeticionesConsecutivas(new int[]{}));
        assertEquals(1,cobertura.repeticionesConsecutivas(new int[]{1,2}));
        assertEquals(3,cobertura.repeticionesConsecutivas(new int[]{1,1,2,2,2}));
        assertEquals(3,cobertura.repeticionesConsecutivas(new int[]{2,2,2,1,1,2}));
    }
}
