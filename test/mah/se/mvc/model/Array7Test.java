//package mah.se.mvc.model;
//
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class Array7Test {
//
//    private Array7 testArray = new Array7();
//
//    /**
//     * Testa konstruktorn på Array7
//     * att den skapar en i fylld int vektor av 7 nollor.
//     * @throws Exception
//     */
//    @Test
//    public void testDefaultConstruct() throws Exception {
//        Array7 array = new Array7();
//        int[] correct = {0,0,0,0,0,0,0};
//        assertArrayEquals(correct,array.getAll());
//    }
//
//    /**
//     * testar konstruktorn med ett tal man skickar med
//     * i mitt fall 2
//     * @throws Exception
//     */
//    @Test
//    public void testIntConstruct() throws Exception {
//        Array7 array = new Array7(2);
//        int[] correct = {2,2,2,2,2,2,2};
//        assertArrayEquals(correct,array.getAll());
//    }
//
//    /**
//     * Testar set element ifall man sätter en 2:a på
//     * position 0
//     * samt därefter ändra den till en 0:a
//     * @throws Exception
//     */
//    @Test
//    public void testSetElement() throws Exception {
//        testArray.setElement(0,2);
//        assertEquals(2,testArray.getElement(0));
//        testArray.setElement(0,0);
//        assertEquals(0,testArray.getElement(0));
//    }
//
//    /**
//     * Testar get elements. att man får en 0:a på pos 0
//     * @throws Exception
//     */
//    @Test
//    public void testGetElement() throws Exception {
//        assertEquals(0,testArray.getElement(0));
//    }
//
//    /**
//     * testar getAll att när man gör en getall på en ny Array7
//     * så får man tillbaks en vektor med 7 nollor.
//     * @throws Exception
//     */
//    @Test
//    public void testGetAll() throws Exception {
//        int[] correct = {0,0,0,0,0,0,0};
//        assertArrayEquals(correct,testArray.getAll());
//    }
//
//    /**
//     * Testar att getLength ger tillbaks 7 tecken.
//     * @throws Exception
//     */
//    @Test
//    public void testGetLength() throws Exception {
//        assertEquals(7,testArray.getLength());
//    }
//}