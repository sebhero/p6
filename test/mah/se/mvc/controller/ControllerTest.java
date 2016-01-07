package mah.se.mvc.controller;

import mah.se.algorithms.ShiftArray;
import mah.se.algorithms.ShiftText;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillNumbers;

import org.junit.Test;

import roffe.Color.Color;
import testHelpers.TestHelper;
import testing.views.ViewWindowsWithFlowText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

import static org.junit.Assert.*;


public class ControllerTest {

    Array7x7 model;
    FillAlgorithm filler;
    private ShiftArray shifter;



    private void initTest() {
        model = new Array7x7();
        filler = new FillNumbers();
        model = filler.fillWithOneType(3);
        shifter = new ShiftArray();
    }

    /**
     * Testar shifter att den fyller i ratt
     * testar att return po shiften stammer
     * @throws Exception
     */
    @Test
    public void testShift() throws Exception {
        initTest();

        //Shift Right
        Array7 newArray = new Array7();
        Array7 returnArr;
        //shift right
        Controller.DIRECTION dir = Controller.DIRECTION.RIGHT;
        model = filler.fillWithInGaining();
        returnArr = shifter.shift(model, newArray, dir);

        //expected
        int[][] expectedModelRight = {
                {0,1,2,3,4,5,6},
                {0,1,2,3,4,5,6},
                {0,1,2,3,4,5,6},
                {0,1,2,3,4,5,6},
                {0,1,2,3,4,5,6},
                {0,1,2,3,4,5,6},
                {0,1,2,3,4,5,6},
        };
        //check model
        assertArrayEquals(expectedModelRight,model.getAll());

        int[] expectedReturn = new int[7];
        Arrays.fill(expectedReturn,7);
        //check return
        assertArrayEquals(expectedReturn,returnArr.getAll());

        //shift Left
        newArray = new Array7();
        //shift right
        dir = Controller.DIRECTION.LEFT;
        model = filler.fillWithInGaining();
        returnArr = shifter.shift(model, newArray, dir);

        //expected left shift
        int[][] expectedModelLeft = {
                {2,3,4,5,6,7,0},
                {2,3,4,5,6,7,0},
                {2,3,4,5,6,7,0},
                {2,3,4,5,6,7,0},
                {2,3,4,5,6,7,0},
                {2,3,4,5,6,7,0},
                {2,3,4,5,6,7,0},
        };
        //check model
        assertArrayEquals(expectedModelLeft,model.getAll());
        Arrays.fill(expectedReturn,1);
        //check return
        assertArrayEquals(expectedReturn,returnArr.getAll());
    }







    /**
     * Testar ifall showNumber1_7 fyller på med tal från 1-7
     * @throws Exception
     */
    @Test
    public void testShowNumbers1_7() throws Exception {
        Controller testCtrl = new Controller(new Array7x7(),new ViewWindowsWithFlowText(Color.BLACK, Color.YELLOW));
        testCtrl.showNumbers1_7();
        Array7x7 model =TestHelper.getPrivateField("model", testCtrl);

        //exp
        int[][] expect ={
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 2, 3, 4, 5, 6, 7}
        };

        assertArrayEquals(expect, model.getAll());
    }

    /**
     * Testar ifall showSameColor fyller på med en färg i hela modelen
     * använder mig utav blå färg
     * @throws Exception
     */
    @Test
    public void testShowSameColor() throws Exception {
        Controller testCtrl = new Controller(new Array7x7(),new ViewWindowsWithFlowText(Color.BLACK, Color.YELLOW));
        testCtrl.showSameColor(Color.BLUE);
        Array7x7 model =TestHelper.getPrivateField("model", testCtrl);

        //exp
        int[][] expect ={
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961},
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961},
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961},
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961},
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961},
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961},
                {-16776961, -16776961, -16776961, -16776961, -16776961, -16776961, -16776961}
        };

        assertArrayEquals(expect, model.getAll());
    }

    @Test
    public void testAddStringToFlowText() throws Exception{

    }

    @Test
    public void testClearAll() throws Exception {

        Controller testCtrl = new Controller(new Array7x7(),new ViewWindowsWithFlowText(Color.BLACK, Color.YELLOW));
        testCtrl.showSameColor(Color.BLUE);
        ShiftText shiftText = TestHelper.getPrivateField("shiftText", testCtrl);
        Timer timer =TestHelper.getPrivateField("timer", testCtrl);


        //ladda in bara en string och kor inget. Darefter testa
        testCtrl.loadFlowText("hej");

        ArrayList<Array7x7> message = TestHelper.getPrivateField("message", shiftText);
        System.out.println(message);
        
        shiftText.getMessageView();

        testCtrl.clearAll();
        assertNull(timer);


        

        //testCtrl.clearAll();

//        if (this.timer != null) {
//            this.pause();
//            //TODO removes timer need to be check if still works with rest
//            this.timer =null;
//        }
//        this.shiftText.resetMessage();
//        this.shiftText.clearMessageView();
//        this.setupMessageView();
//        this.shiftText.setStepps(0);

    }


    @Test
    public void testGetDirection() throws Exception {

    }

    @Test
    public void testSetMainPanel() throws Exception {

    }

    @Test
    public void testRefreshMainPanel() throws Exception {

    }

    @Test
    public void testSetDirection() throws Exception {

    }

    @Test
    public void testShowRandom() throws Exception {

    }

    @Test
    public void testShowRandomSame() throws Exception {

    }

    @Test
    public void testShowRandomColor() throws Exception {

    }

    @Test
    public void testShowGradiantColor() throws Exception {

    }

    @Test
    public void testShowRanomChar() throws Exception {

    }

    @Test
    public void testSetRow() throws Exception {

    }

    @Test
    public void testSetCol() throws Exception {

    }

    @Test
    public void testSetElement() throws Exception {

    }

    @Test
    public void testGetRow() throws Exception {

    }

    @Test
    public void testGetCol() throws Exception {

    }

    @Test
    public void testGetElement() throws Exception {

    }

    @Test
    public void testShowShift() throws Exception {

    }

    @Test
    public void testSimpleShift() throws Exception {

    }

    @Test
    public void testLoadFlowText() throws Exception {

    }

    @Test
    public void testShowStepText() throws Exception {

    }

    @Test
    public void testFlowText() throws Exception {

    }

    @Test
    public void testSetSpeed() throws Exception {

    }

    @Test
    public void testShiftOutAll() throws Exception {

    }

    @Test
    public void testPause() throws Exception {

    }

    @Test
    public void testResume() throws Exception {

    }

    @Test
    public void testSetView() throws Exception {

    }
}
