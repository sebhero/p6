package mah.se.strategy;

import mah.se.mvc.model.Array7x7;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public interface StrategyFill {

    public int[][] fill(Array7x7 matrix, int inData);

    int[][] shiftRight(Array7x7 model);

    int[][] fill(Array7x7 model, SHOW whatToShow);

    /*
        fill med 1 typ av number
        fillRandom
        fillStegande
        shift med ny tal vektor

        int Color.rgb(red, green, blue); you get the color int
        fill med 1 typ av color
        fillRandom color
        fillstegande color
        shift med ny color vektor

        ascii number you get the signs ascii number
        char ch='A';
        int charAsciiValue = (int)ch;
        fill med 1 typ av char
        fill med random char
        fill med
    * */
}
