package mah.se.mvc.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;
import mah.se.mvc.view.viewImpl;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillCharacter;
import mah.se.patterns.strategy.FillColor;
import mah.se.patterns.strategy.FillNumbers;
import roffe.Color.Color;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Kontroller styr kommunikationen mellan Vyn och modelen.
 */
public class Controller implements controllerImpl{

    //för flowtext hålla kolla på vilket tecken i string
    private int shiftCounter;
    //för flowtext håller koll på vilken kolumn vi är i tecknet
    private int shiftArrayIdx;

    //håller koll på vilket håll vi shiftar
    public static enum DIRECTION {
		RIGHT,
		LEFT,
		UP,
		DOWN
	}

    /**
     * Väljer vilken filler metod
     */
    public enum FILLERTYPE{
        COLORS, CHARACTERS, NUMBERS
    }

    //vilket håll vi ska rita ut
	private DIRECTION dir = DIRECTION.LEFT;
    //våran algoritm för att skifta a7x7
    private final ShiftArray shifter;
    private FillAlgorithm filler;
    private Array7x7 model;
    private viewImpl view;
    private ArrayList<Array7x7> message = new ArrayList<>();
    private Timer timer;
    
//    ViewAndroid view;

    /**
     * A controller to handles communication with the Array7x7 model and diffrent strategies for filling it.
     * @param model the Array7x7 model
     * @param view the view we are displaying the matrix on
     */
    public Controller(Array7x7 model, ViewWindows view) {
        this.model = model;
        this.view = view;
        this.view.setCtrl(this);
        filler = getFiller(FILLERTYPE.NUMBERS);
        shifter = new ShiftArray();
        updateView();
    }


     /**
     * Update the view med Array7x7 av nummer
     */
    private void updateView() {
        view.updateView(model.getAll());
    }



    /**
     * Btn call from view
     * Shift right the matrix
     */    
    public Array7 shift(Array7 newArray) {

        return shifter.shift(model, newArray, dir);

    }

    /**
     * Shifta Array7x7 och fyller på med
     * en rad med röda rutor
     * @return det som ramlar över
     */
    public Array7 shiftWithRedColor() {
        Array7 newArray = new Array7(Color.RED);
        newArray = shift(newArray);
        updateViewColor();
        return newArray;
    }


    /**
     * FlowText timern kallar på denna var 50ms
     * för att få en rullande text
     */
    private void shiftString() {
        //hämta nästa a7x7
        Array7x7 next = message.get(shiftCounter);
        //hämta nästa kolumn
        Array7 nextArray = next.getCol(shiftArrayIdx);
        //do shift
        shift(nextArray);

        //update index to shift
        shiftArrayIdx++;
        if (shiftArrayIdx >= message.get(shiftCounter).getLength()) {
            shiftCounter++;
            shiftArrayIdx=0;
            if (shiftCounter >= message.size()) {
                //done flowing
                message.clear();
                timer.cancel();
            }
        }
    }

    /**
     * Sätter vilket håll vi ska shifta
     * @param dir riktigt vi shiftar
     */
    public void setDirection(DIRECTION dir) {
    	this.dir = dir;
    }
    
    /**
     * btn click
     * Slumpar antal siffror och ritar ut den i vyn
     */
    public void showRandom() {
        filler = getFiller(FILLERTYPE.NUMBERS);
        model = filler.fillWithRandom();
        updateView();
    }

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
    public void showNumbers1_7() {
        filler = getFiller(FILLERTYPE.NUMBERS);
        model = filler.fillWithInGaining();
        updateView();
    }

    /**
     * btn click from view
     * Slumpar ett tal och fyller Array7x7 med detta tal
     * därefter visar den i vyn
     */
    public void showRandomSame() {
        filler = getFiller(FILLERTYPE.NUMBERS);

        int rnd = new Random().nextInt(7)+1;
        model = filler.fillWithOneType(rnd);
        updateView();
    }

    /**
     * Hämtar vilken fyll algoritm vi ska använda
     * @param typeOfFiller val av fyllnings algoritm
     * @return fyllnings algoritmen.
     */
    private FillAlgorithm getFiller(FILLERTYPE typeOfFiller) {
        //TODO use singelton
        switch (typeOfFiller) {

            case COLORS:
                return new FillColor();
            case CHARACTERS:
                return new FillCharacter();
            case NUMBERS:
            default:
                return new FillNumbers();
        }
    }

    /**
     * btn click
     * Visa slumpade färger i vyn
     */
    public void showRandomColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithRandom();
        updateViewColor();
    }

    /**
     * uppdatera vyn med nya Array7x7
     * av typen färger
     */
    private void updateViewColor() {
        view.updateViewColor(model.getAll());
    }

    /**
     * Visa en färg i på hela Array7x7 i vyn
     */
    public void showSameColor(int color) {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithOneType(color);
        updateViewColor();
    }

    /**
     * Visar en graident färg mellan 2 färger
     * i vyn
     */
    public void showGradiantColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithInGaining();
        updateViewColor();
    }

    /**
     * Visar en slumpad bokstav i vyn
     */
    public void showRanomChar() {
        filler = getFiller(FILLERTYPE.CHARACTERS);
        model = filler.fillWithRandom();
        updateViewColor();
    }


    /**
     * Btn click
     * Visar rinande text på texy som kommer in från vyn
     * @param texy
     */
    public void flowText(String texy) {
    	filler = getFiller(FILLERTYPE.CHARACTERS);
    	for(int n = 0; n < texy.length(); n++) {
    		Array7x7 character = filler.fillWithOneType((int) texy.charAt(n));
    		message.add(character);
    	}

        //which char in msg
        shiftCounter =0;
        //column index
        shiftArrayIdx = 0;
        //start timer
    	timer = new Timer();
    	timer.schedule(new flowTextTimer(), 50, 50);
    	
    }


    /**
     * Btn click
     * Satter en ny rad i modelen.
     */
    public void setRow(int rowPos, int[] newRow) {
        //todo do test
        model.setRow(rowPos, new Array7(newRow));
    }

    /**
     * Btn click
     * Satter en ny kolumn i modelen.
     */
    public void setCol(int colPos, int[] newCol) {
        //todo do test
        model.setCol(colPos, new Array7(newCol));
    }


    /**
     * Btn click
     * Satter ett nytt element i modelen.
     */
    public void setElement(int rowPos, int colPos, int value) {
        //todo do test
        model.setElement(rowPos, colPos, value);
    }

    /**
     * Btn click
     * Hamtar rad i modelen.
     */
    public int[] getRow(int rowPos) {
        //todo do test
        return model.getRow(rowPos).getAll();
    }

    /**
     * Btn click
     * Hamtar kolumn i modelen.
     */
    public int[] getCol(int colPos) {
        //todo do test
        return model.getCol(colPos).getAll();
    }

    /**
     * Btn click
     * Hamtar element i modelen.
     */
    public int getElement(int rowPos, int colPos) {
        //todo do test
        return model.getElement(rowPos,colPos);
    }

    /**
     * Rinnande text timer
     * shiftar Strängen en kolumn i taget
     * och kallar på att uppdatera vyn
     */
    private class flowTextTimer extends TimerTask {
		@Override
		public void run() {
            //start shifting letters
			shiftString();
			updateViewColor();
		}
    }


}
