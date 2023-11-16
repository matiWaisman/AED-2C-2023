import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class sortingTest {
    @Test
    public void testKElementosMasChicos() {
        int[] lista = new int[]{100, 25, 3, 989, 50, 99, 78, 10, 100, 1};
        int[] respuestaEsperada = new int[]{1, 3};
        assertArrayEquals(respuestaEsperada, sorting.kElementosMasChicos(2, lista));
    }
}
