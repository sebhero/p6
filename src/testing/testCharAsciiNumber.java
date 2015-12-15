package testing;

import roffe.Color.Color;

/**
 * Created by Sebastian Börebäck on 2015-12-14.
 */
public class testCharAsciiNumber {

    public static void main(String[] args) {


        char ch='A';
        int charAsciiValue = (int)ch;

        System.out.println(ch+": "+charAsciiValue);
        int black = Color.BLACK;
        int white = Color.WHITE;
        System.out.println("black> "+black);
        System.out.println("white> "+white);


    }
}
