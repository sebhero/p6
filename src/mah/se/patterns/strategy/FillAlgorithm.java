package mah.se.patterns.strategy;


import mah.se.mvc.model.TestArray7x7;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */
public interface FillAlgorithm {

    /**
     * Fill Array7x7 with 1 type of numbers/color/char
     * @param type the color number/number or char
     * @return
     */
    public TestArray7x7 fillWithOneType(int type);

    /**
     * Fill Array7x7 with random numbers/color/char
     * @return a random filled Array7x7
     */
    public TestArray7x7 fillWithRandom();

    /**
     * Fill the Array7x7 with gaining from 1-7 numbers/color
     * @return a increasing filled Array7x7
     */
    public TestArray7x7 fillWithInGaining();

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
