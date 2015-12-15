package mah.se.mvc.model;

import mah.se.patterns.strategy.FillNumbers;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class Array7x7Test {

    Array7x7 testMatrix;
    @Test
    public void testSetRow() throws Exception {
        testMatrix = new Array7x7();
        Array7 array = new Array7(1);
        testMatrix.setRow(2,array);
        int[] correct = new int[7];
        Arrays.fill(correct,1);
        assertArrayEquals(correct,testMatrix.getRow(2).getAll());
    }

    @Test
    public void testGetRow() throws Exception {
        testMatrix = new Array7x7();
        Array7 array = new Array7(1);
        testMatrix.setRow(2,array);
        int[] correct = new int[7];
        Arrays.fill(correct,1);
        assertArrayEquals(correct,testMatrix.getRow(2).getAll());
    }

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