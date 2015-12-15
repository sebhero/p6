package mah.se.patterns.strategy;

import mah.se.mvc.model.TestArray7x7;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */
public class FillNumbers implements FillAlgorithm {
    @Override
    public TestArray7x7 fillWithOneType(int type) {
        TestArray7x7 theMatrix = new TestArray7x7();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).length; col++) {
                theMatrix.setElement(row,col,type);
            }
        }
        return theMatrix;
    }

    @Override
    public TestArray7x7 fillWithRandom() {
        TestArray7x7 theMatrix = new TestArray7x7();
        Random rnd = new Random();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).length; col++) {
                theMatrix.setElement(row,col,rnd.nextInt(7)+1);
            }
        }
        return theMatrix;
    }

    @Override
    public TestArray7x7 fillWithInGaining() {
        TestArray7x7 theMatrix = new TestArray7x7();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).length; col++) {
                //col is the 0+1...6+1
                theMatrix.setElement(row,col,col+1);
            }

        }
        return theMatrix;
    }
}
