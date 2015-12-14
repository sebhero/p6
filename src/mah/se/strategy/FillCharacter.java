package mah.se.strategy;

import mah.se.Color.Color;
import mah.se.algorithms.Alphabet;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */
public class FillCharacter implements FillAlgorithm {
    @Override
    public Array7x7 fillWithOneType(int type) {
        return null;
    }

    @Override
    public Array7x7 fillWithRandom() {
        Random rnd = new Random();
        Alphabet alphabet = new Alphabet();



        int randomChar = rnd.nextInt('Z' - 'A') + 'A';	//ascii värde 65 - 90 A-Z
//        int randomChar = rnd.nextInt(90 - 65) + 65;	//ascii värde 65 - 90 A-Z
        int[][] character = alphabet.getLetter((char) randomChar);


        int[][] matrix = character;
        for (int row = 0; row < character.length; row++) {

            for (int col = 0; col < character[row].length; col++) {

                switch (character[row][col]) {
                    case 0:
                    default:
                        matrix[row][col] = Color.TRANSPARENT;
                        break;
                    case 1:
                        matrix[row][col] = Color.WHITE;
                        break;
                }
            }
        }


        Array7x7 temp = new Array7x7();
        temp.setMatrix(matrix);


        return temp;
    }

    @Override
    public Array7x7 fillWithInGaining() {
        return null;
    }
}