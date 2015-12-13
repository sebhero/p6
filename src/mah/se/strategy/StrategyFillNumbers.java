package mah.se.strategy;

import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

import java.util.Arrays;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class StrategyFillNumbers implements StrategyFill {

    @Override
    public int[][] fill(Array7x7 matrix, int inData) {
        int[][] theMatrix = matrix.getMatrix();
        for (int row = 0; row < theMatrix.length; row++) {
            for (int col = 0; col < theMatrix[row].length; col++) {
                theMatrix[row][col] = inData;
            }
        }
        return theMatrix;
    }

    @Override
    public int[][] shiftRight(Array7x7 model) {
        Array7 theArray = new Array7(9);
        //TODO should be returned
        Array7 fellOver = model.getCol(6);

        Array7x7 temp = model;

        Array7 current = model.getCol(0);
        Array7 next = theArray;
        for (int i = 0; i < 6; i++) {
            current = model.getCol(i);
            temp.setCol(i, next);
            next = current;

        }


        return temp.getMatrix();

    }

    @Override
    public int[][] fill(Array7x7 matrix) {
        int[][] theMatrix = matrix.getMatrix();
        for (int row = 0; row < theMatrix.length; row++) {
            for (int col = 0; col < theMatrix[row].length; col++) {
                theMatrix[row][col] = col;

            }

        }
        return theMatrix;
    }


}
