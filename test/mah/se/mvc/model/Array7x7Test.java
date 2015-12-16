package mah.se.mvc.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Array7x7Test {

    private Array7x7 testMatrix;

    /**
     * Testar att setRow.
     * sätter en rad med 1:or och kollar att alla är 1:or
     * @throws Exception
     */
    @Test
    public void testSetRow() throws Exception {
        testMatrix = new Array7x7();
        Array7 array = new Array7(1);
        testMatrix.setRow(2,array);
        int[] correct = new int[7];
        Arrays.fill(correct,1);
        assertArrayEquals(correct,testMatrix.getRow(2).getAll());
    }

    /**
     * Kollar att getRow fungerar
     * sätter en row till ettor och kollar att man får det tillbaka.
     * @throws Exception
     */
    @Test
    public void testGetRow() throws Exception {
        testMatrix = new Array7x7();
        Array7 array = new Array7(1);
        testMatrix.setRow(2,array);
        int[] correct = new int[7];
        Arrays.fill(correct,1);
        assertArrayEquals(correct,testMatrix.getRow(2).getAll());
    }

    /**
     * Testar setCol
     * @throws Exception
     */
    @Test
    public void testSetCol() throws Exception {
        testMatrix = new Array7x7();
        Array7 array = new Array7(1);
        Array7 correct = new Array7(1);

        testMatrix.setCol(2, array);

        assertArrayEquals(correct.getAll(),testMatrix.getCol(2).getAll());
    }

    @Test
    public void testGetCol() throws Exception {
        testMatrix = new Array7x7();
        Array7 array = new Array7(1);
        Array7 correct = new Array7(1);

        testMatrix.setCol(2, array);

        assertArrayEquals(correct.getAll(),testMatrix.getCol(2).getAll());
    }

    @Test
    public void testGetElement() throws Exception {
        testMatrix = new Array7x7();
        int got = testMatrix.getElement(0, 0);

        assertEquals(0, got);
    }

    @Test
    public void testSetElement() throws Exception {
        testMatrix = new Array7x7();
        testMatrix.setElement(0,0,2);
        int got = testMatrix.getElement(0, 0);

        assertEquals(2,got);
    }

    @Test
    public void testGetMatrix() throws Exception {
        testMatrix = new Array7x7();
        Array7x7 correctMatrix = new Array7x7();
        assertEquals(correctMatrix,testMatrix);
    }

    @Test
    public void testGetLength() throws Exception {
        testMatrix = new Array7x7();
        assertEquals(7,testMatrix.getLength());
    }
}