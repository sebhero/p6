package mah.se.patterns.strategy;

import mah.se.algorithms.Alphabet;
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
public class FillCharacter implements FillAlgorithm {
    private final Alphabet alphabet = new Alphabet();
    private final Random rnd = new Random();

    /**
     * Fyller Array7x7 med slumpat nummer/färg/tecken
     * @return en fylld Array7x7 med slumpade värden
     */
    @Override
    public Array7x7 fillWithRandom() {
        int randomChar = rnd.nextInt('Z' - 'A') + 'A';	//ascii värde 65 - 90 A-Z
        return alphabet.getLetter((char) randomChar);
    }

    /**
     * @return null
     * @deprecated använd inte. är inte implementerad
     */
    public Array7x7 fillWithInGaining() {
        return null;
    }

    /**
     * Fyller Array7x7 med en typ av nummer/färg/tecken
     * @param charValue tar ett nummer värde av nummer/färg/tecken
     * @return en fylld Array7x7
     */
    @Override
    public Array7x7 fillWithOneType(int charValue) {
        return alphabet.getLetter((char) charValue);
    }

}