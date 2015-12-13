package mah.se.strategy;

import mah.se.mvc.controller.SHOW;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class StrategyFillNumbers implements StrategyFill {

    /**
     * Fill the matrix with the same number
     * @param matrix
     * @param inData the number to fill the matrix with
     * @return
     */
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

    /**
     * Shift the matrix to the right
     * @param model
     * @return
     */
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
    public int[][] fill(Array7x7 matrix, SHOW whatToShow) {
        switch (whatToShow) {
            case ONE_SEVEN:
                return one_seven(matrix);
            case RANDOM:
                return allRandom(matrix);
            case SAME:
            default:
                return fill(matrix, new Random().nextInt(7) + 1);
        }

    }

    /**
     * Createas a total random matrix
     * @param matrix
     * @return
     */
    private int[][] allRandom(Array7x7 matrix) {

        Random rnd = new Random();
        int[][] theMatrix = matrix.getMatrix();
        for (int row = 0; row < theMatrix.length; row++) {
            for (int col = 0; col < theMatrix[row].length; col++) {
                theMatrix[row][col] = rnd.nextInt(7)+1;
            }

        }
        return theMatrix;
    }

    /**
     * creates a 1 - 7 matrix
     * @param matrix
     * @return
     */
    private int[][] one_seven(Array7x7 matrix) {
        int[][] theMatrix = matrix.getMatrix();
        for (int row = 0; row < theMatrix.length; row++) {
            for (int col = 0; col < theMatrix[row].length; col++) {
                theMatrix[row][col] = col;
            }

        }
        return theMatrix;
    }


}
