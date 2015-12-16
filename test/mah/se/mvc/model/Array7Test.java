package mah.se.mvc.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class Array7Test {

    private Array7 testArray = new Array7();

    @Test
    public void testDefaultConstruct() throws Exception {
        Array7 array = new Array7();
        int[] correct = {0,0,0,0,0,0,0};
        assertArrayEquals(correct,array.getAll());
    }

    @Test
    public void testIntConstruct() throws Exception {
        Array7 array = new Array7(2);
        int[] correct = {2,2,2,2,2,2,2};
        assertArrayEquals(correct,array.getAll());
    }

    @Test
    public void testSetElement() throws Exception {
        testArray.setElement(0,2);
        assertEquals(2,testArray.getElement(0));
        testArray.setElement(0,0);
        assertEquals(0,testArray.getElement(0));
    }

    @Test
    public void testGetElement() throws Exception {
        assertEquals(0,testArray.getElement(0));
    }

    @Test
    public void testGetAll() throws Exception {
        int[] correct = {0,0,0,0,0,0,0};
        assertArrayEquals(correct,testArray.getAll());
    }

    @Test
    public void testGetLength() throws Exception {
        assertEquals(7,testArray.getLength());
    }
}