package mah.se.patterns.strategy;

import roffe.Color.Color;
import mah.se.mvc.model.Array7x7;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */


/**
 * Hjälp klass för Array7x7.
 * För att Fyller Array7x7 med färg.
 * Med hjälp av Strategypattern
 */
public class FillColor implements FillAlgorithm {

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
                theMatrix.setElement(row,col, Color.rgb(
                        rnd.nextInt(256),
                        rnd.nextInt(256),
                        rnd.nextInt(256)));
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

        int color1 = Color.RED;
        int color2 = Color.BLUE;

        Array7x7 theMatrix = new Array7x7();
        for (int row = 0; row < theMatrix.getLength(); row++) {
            for (int col = 0; col < theMatrix.getRow(row).getLength(); col++) {
                float ratio = (float) col / (float) theMatrix.getRow(row).getLength();
                int red = (int) (Color.red(color2) * ratio + Color.red(color1) * (1 - ratio));
                int green = (int) (Color.green(color2) * ratio + Color.green(color1)* (1 - ratio));
                int blue = (int) (Color.blue(color2) * ratio + Color.blue(color2) * (1 - ratio));

                theMatrix.setElement(row, col, Color.rgb(red,green,blue));
            }

        }
        return theMatrix;
    }
}