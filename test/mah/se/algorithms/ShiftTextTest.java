//package mah.se.algorithms;
//
//import mah.se.mvc.model.Array7x7;
//import mah.se.patterns.strategy.FillCharacter;
//import org.junit.Test;
//import roffe.Color.Color;
//import testHelpers.TestHelper;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
///**
// * Created by seb on 2016-01-07.
// */
//public class ShiftTextTest {
//
//    @Test
//    public void testStepText() throws Exception {
//
//    }
//
//    /**
//     * Testar skapning av MessageView som tar x antal 7x7
//     * @throws Exception
//     */
//    @Test
//    public void testSetupMessageView() throws Exception {
////        Controller testCtrl = new Controller(new Array7x7(),new ViewWindowsWithFlowText(Color.BLACK, Color.YELLOW));
//        ShiftText testShifter = new ShiftText();
////        ShiftText shiftText = TestHelper.getPrivateField("shiftText", testCtrl);
//
//        //skapar 2 rutor
//        testShifter.setupMessageView(2);
//        ArrayList<int[][]> result = testShifter.getMessageView();
//
//
//        int[][] expect ={
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK}
//        };
//
//        //kollar att varje ruta ar en ny svart ruta
//        //testa forsta rutan
//        assertArrayEquals(expect, result.get(0));
//        //testar andra rutan.
//        assertArrayEquals(expect, result.get(1));
//
//    }
//
//    /**
//     * Testar hamtningen av MessageView. Att man far tillbaks en ruta.
//     * @throws Exception
//     */
//    @Test
//    public void testGetMessageView() throws Exception {
//        ShiftText testShifter = new ShiftText();
//        testShifter.setupMessageView(1);
//        ArrayList<int[][]> result = testShifter.getMessageView();
//
//        int[][] expect ={
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK},
//                {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK}
//        };
//        //kollar ifall man far tillbaks rutan.
//        assertArrayEquals(expect, result.get(0));
//
//
//    }
//
//    @Test
//    public void testLoadText() throws Exception {
//        ShiftText testShifter = new ShiftText();
//        FillCharacter filler = new FillCharacter();
//        testShifter.loadText("   ");
//
//        int result= testShifter.getMessageSize();
//
//        assertEquals(3, result);
//
//        Array7x7 expectitem = new Array7x7(0);
//        ArrayList<Array7x7> expect = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            expect.add(expectitem);
//        }
//
//        ArrayList<Array7x7> message= TestHelper.getPrivateField("message", testShifter);
//        for (int i = 0; i < message.size(); i++) {
//            assertArrayEquals(expect.get(i).getAll(),message.get(i).getAll());
//        }
//
//        testShifter.loadText("hej");
//        expect.clear();
//        expect.add(filler.fillWithOneType('h'));
//        expect.add(filler.fillWithOneType('e'));
//        expect.add(filler.fillWithOneType('j'));
//
//        for (int i = 0; i < message.size(); i++) {
//            assertArrayEquals(expect.get(i).getAll(),message.get(i).getAll());
//        }
//
//
//
//
//    }
//
//    /**
//     * Testar att efter skapningen av en message.
//     * Ifall Message laddas med 12345 sa ska antalet vara 5
//     * @throws Exception
//     */
//    @Test
//    public void testGetMessageSize() throws Exception {
//        ShiftText testShifter = new ShiftText();
//        testShifter.loadText("12345");
//
//        int expect = 5;
//        int result= testShifter.getMessageSize();
//
//        assertEquals(5, result);
//    }
//
//    @Test
//    public void testClearMessage() throws Exception {
//
//    }
//
//    @Test
//    public void testResetMessage() throws Exception {
//
//    }
//
//    @Test
//    public void testCheckIfDoneStepping() throws Exception {
//
//    }
//
//    @Test
//    public void testSetMaxSteps() throws Exception {
//
//    }
//
//    /***
//     * Testar att okning av stepping fungerar.
//     * ifall man okar steppningen 3 steg sa ar stepps 3
//     * @throws Exception
//     */
//    @Test
//    public void testIncreaseSteps() throws Exception {
//        ShiftText testShifter = new ShiftText();
//        testShifter.increaseSteps();
//        testShifter.increaseSteps();
//        testShifter.increaseSteps();
//
//        //hamta den privata variablen
//        int stepps= TestHelper.getPrivateField("stepps", testShifter);
//        int expected = 3;
//
//        assertEquals(expected,stepps);
//    }
//
//    @Test
//    public void testGetMessageView7x7() throws Exception {
//
//    }
//
//    /**
//     * Testar att setStepps
//     * @throws Exception
//     */
//    @Test
//    public void testSetStepps() throws Exception {
//        ShiftText testShifter = new ShiftText();
//        testShifter.setStepps(4);
//
//        int stepps= TestHelper.getPrivateField("stepps", testShifter);
//        int expected = 4;
//
//        assertEquals(expected,stepps);
//    }
//
//    @Test
//    public void testClearMessageView() throws Exception {
//
//    }
//}