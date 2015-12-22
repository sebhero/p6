package mah.se.mvc.controller;

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillNumbers;
import org.junit.Test;
import roffe.Color.Color;

import java.lang.reflect.Field;
import java.util.Arrays;

import static org.junit.Assert.*;

import static testHelper.TestHelper.getPrivateField;


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
     * Testar att shift med röd kolumn fungerar
     * förväntar mig få tillbaks en array med nollor
     * och att det läggs till en array på första kolumn med röd färg.
     * @throws Exception
     */
    @Test
    public void testShiftWithRedColor() throws Exception {
        Controller testCtrl = new Controller(new Array7x7(),new ViewWindows(1,1));
        testCtrl.setDirection(Controller.DIRECTION.RIGHT);
        Array7x7 model =getPrivateField("model", testCtrl);

        Array7 returnArr = testCtrl.shiftWithRedColor();

        //expected
        int[] expReturn = {0, 0, 0, 0, 0, 0, 0};
        assertArrayEquals(expReturn,returnArr.getAll());

        int[][] expModel = {
                {-65536, 0, 0, 0, 0, 0, 0},
                {-65536, 0, 0, 0, 0, 0, 0},
                {-65536, 0, 0, 0, 0, 0, 0},
                {-65536, 0, 0, 0, 0, 0, 0},
                {-65536, 0, 0, 0, 0, 0, 0},
                {-65536, 0, 0, 0, 0, 0, 0},
                {-65536, 0, 0, 0, 0, 0, 0}
        };

        assertArrayEquals(expModel, model.getAll());

    }

    /**
     * Testar ifall showNumber1_7 fyller på med tal från 1-7
     * @throws Exception
     */
    @Test
    public void testShowNumbers1_7() throws Exception {
        Controller testCtrl = new Controller(new Array7x7(),new ViewWindows(1,1));
        testCtrl.showNumbers1_7();
        Array7x7 model =getPrivateField("model", testCtrl);

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
        Controller testCtrl = new Controller(new Array7x7(),new ViewWindows(1,1));
        testCtrl.showSameColor(Color.BLUE);
        Array7x7 model =getPrivateField("model", testCtrl);

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



}