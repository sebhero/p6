package mah.se.patterns.strategy;

import java.util.Random;

import mah.se.algorithms.Alphabet;
import mah.se.mvc.model.Array7x7;

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

    @Override
    public Array7x7 fillWithRandom() {
        int randomChar = rnd.nextInt('Z' - 'A') + 'A';	//ascii värde 65 - 90 A-Z
        Array7x7 character = alphabet.getLetter((char) randomChar);
        return character;
    }

    /**
     * @return null
     * @deprecated använd inte. är inte implementerad
     */
    public Array7x7 fillWithInGaining() {
        return null;
    }

    @Override
    public Array7x7 fillWithOneType(int charValue) {
        Array7x7 character = alphabet.getLetter((char) charValue);
        return character;
    }

}