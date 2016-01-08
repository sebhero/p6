package mah.se.patterns.strategy;

import mah.se.mvc.model.Array7x7;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */

/**
 * Hjälp klass för Array7x7.
 * För att Fyller Array7x7 med nummer.
 * Med hjälp av Strategypattern
 */
public class FillNumbers implements FillAlgorithm {

    /**
     * Fyller Array7x7 med en typ av nummer/färg/tecken
     * @param type tar ett nummer värde av nummer/färg/tecken
     * @return en fylld Array7x7
     */
    @Override
    public Array7x7 fillWithOneType(int type) {
        Array7x7 theMatrix = new Array7x7();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).getLength(); col++) {
                theMatrix.setElement(row,col,type);
            }
        }
        return theMatrix;
    }
    /**
     * Fyller Array7x7 med slumpat nummer/färg/tecken
     * @return en fylld Array7x7 med slumpade värden
     */
    @Override
    public Array7x7 fillWithRandom() {
        Array7x7 theMatrix = new Array7x7();
        Random rnd = new Random();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).getLength(); col++) {
                theMatrix.setElement(row,col,rnd.nextInt(7)+1);
            }
        }
        return theMatrix;
    }

    /**
     * Fyller Array7x7 med ökande från 1-7 nummer
     * eller en färg från röd till blå
     * @return Array7x7 med ökande värden
     */
    @Override
    public Array7x7 fillWithInGaining() {
        Array7x7 theMatrix = new Array7x7();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).getLength(); col++) {
                //col is the 0+1...6+1
                theMatrix.setElement(row,col,col+1);
            }

        }
        return theMatrix;
    }
}
