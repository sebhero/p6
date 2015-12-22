package mah.se.mvc.controller;

import mah.se.mvc.model.Array7;

/**
 * Created by seb on 2015-12-18.
 */
public interface controllerImpl {

        /**



        /**
         * Btn call from view
         * Shift right the matrix
         */
        public Array7 shift(Array7 newArray);

        /**
         * Shifta Array7x7 och fyller på med
         * en rad med röda rutor
         * @return det som ramlar över
         */
        public Array7 shiftWithRedColor();


        /**
         * Sätter vilket håll vi ska shifta
         * @param dir riktigt vi shiftar
         */
        public void setDirection(Controller.DIRECTION dir);

        /**
         * btn click
         * Slumpar antal siffror och ritar ut den i vyn
         */
        public void showRandom();

        /**
         * btn click
         * Ritar ut 1-7 i vyn
         * 1234567
         * 1234567
         * 1234567
         * 1234567
         * 1234567
         * 1234567
         * 1234567
         */
        public void showNumbers1_7();

        /**
         * btn click from view
         * Slumpar ett tal och fyller Array7x7 med detta tal
         * därefter visar den i vyn
         */
        public void showRandomSame();

        /**
         * btn click
         * Visa slumpade färger i vyn
         */
        public void showRandomColor();

        /**
         * Visa en färg i på hela Array7x7 i vyn
         */
        public void showSameColor(int color);

        /**
         * Visar en graident färg mellan 2 färger
         * i vyn
         */
        public void showGradiantColor();

        /**
         * Visar en slumpad bokstav i vyn
         */
        public void showRanomChar();


        /**
         * Btn click
         * Visar rinande text på texy som kommer in från vyn
         * @param texy
         */
        public void flowText(String texy);



        /**
         * Btn click
         * Satter en ny rad i modelen.
         */
        public void setRow(int rowPos, int[] newRow);

        /**
         * Btn click
         * Satter en ny kolumn i modelen.
         */
        public void setCol(int colPos, int[] newCol);

        /**
         * Btn click
         * Satter ett nytt element i modelen.
         */
        public void setElement(int rowPos, int colPos, int value);

        /**
         * Btn click
         * Hamtar rad i modelen.
         */
        public int[] getRow(int rowPos);

        /**
         * Btn click
         * Hamtar kolumn i modelen.
         */
        public int[] getCol(int colPos);

        /**
         * Btn click
         * Hamtar element i modelen.
         */
        public int getElement(int rowPos, int colPos);


}
