package mah.se.patterns.strategy;

import java.util.Random;

import mah.se.algorithms.Alphabet;
import mah.se.mvc.model.Array7x7;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
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

    @Override
    public Array7x7 fillWithOneType(int charValue) {
        Array7x7 character = alphabet.getLetter((char) charValue);
        return character;
    }

    //TODO move this out of the Algorithm
    @Override
    public Array7x7 fillWithInGaining() {
        return null;
    }
}