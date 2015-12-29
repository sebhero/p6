package mah.se.mvc.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewImpl;
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

    private final Array7x7[] colorDisplay;
    //för flowtext hålla kolla på vilket tecken i string
    private int shiftCounter;
    //för flowtext håller koll på vilken kolumn vi är i tecknet
    private int shiftArrayIdx;

    public DIRECTION getDirection() {
        return dir;
    }

    //håller koll på vilket håll vi shiftar
    public enum DIRECTION {
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
    private ViewImpl view;
    private ArrayList<Array7x7> message = new ArrayList<>();
    private Timer timer;
    
//    ViewAndroid view;

    /**
     * A controller to handles communication with the Array7x7 model and diffrent strategies for filling it.
     * @param model the Array7x7 model
     * @param view the view we are displaying the matrix on
     */
    public Controller(Array7x7 model, ViewImpl view) {
        this.model = model;
        this.view = view;
        this.view.setCtrl(this);
        colorDisplay = new Array7x7[view.getPages()];
        filler = getFiller(FILLERTYPE.NUMBERS);
        for(int n = 0; n < colorDisplay.length; n++)
            colorDisplay[n] = filler.fillWithOneType(0);
        shifter = new ShiftArray();
        updateView();
    }


     /**
     * Update the view med Array7x7 av nummer
     */
    private void updateView() {
        view.updateView(model.getAll());
    }

    private void updateView2() {
        view.updateBigView(colorDisplay);
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
        updateView();
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
        //kollar om det är nästa kolumn eller rad som ska hämtas!
        if(dir==DIRECTION.LEFT || dir==DIRECTION.RIGHT){
        nextArray = next.getCol(shiftArrayIdx);
        }
        if(dir==DIRECTION.UP || dir==DIRECTION.DOWN){
            nextArray = next.getRow(shiftArrayIdx);
            }
        //do shift
        shift(nextArray);

        //update index to shift

        if (dir == DIRECTION.LEFT) {
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
        if (dir == DIRECTION.RIGHT) {
            shiftArrayIdx--;
            //begin next char
            if (shiftArrayIdx < 0) {
                shiftCounter++;
                shiftArrayIdx=6;

                if (shiftCounter >= message.size()) {
                    //done flowing
                    message.clear();
                    timer.cancel();
                }
            }
        }
        if (dir == DIRECTION.UP) {
            shiftArrayIdx++;
            //begin next char
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
        if (dir == DIRECTION.DOWN) {
            shiftArrayIdx--;
            //begin next char
            if (shiftArrayIdx < 0) {
                shiftCounter++;
                shiftArrayIdx=6;

                if (shiftCounter >= message.size()) {
                    //done flowing
                    message.clear();
                    timer.cancel();
                }
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
        updateView();
    }

    /**
     * @deprecated use updateView()
     * uppdatera vyn med nya Array7x7
     * av typen färger
     */
//    private void updateViewColor() {
//        view.updateViewColor(model.getAll());
//    }

    /**
     * Visa en färg i på hela Array7x7 i vyn
     */
    public void showSameColor(int color) {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithOneType(color);
        updateView();
    }

    /**
     * Visar en graident färg mellan 2 färger
     * i vyn
     */
    public void showGradiantColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithInGaining();
        updateView();
    }

    /**
     * Visar en slumpad bokstav i vyn
     */
    public void showRanomChar() {
        filler = getFiller(FILLERTYPE.CHARACTERS);
        model = filler.fillWithRandom();
        updateView();
    }


    /**
     * Btn click
     * Visar rinande text på texy som kommer in från vyn
     * @param texy
     */
    public void flowText(String texy) {
    	filler = getFiller(FILLERTYPE.CHARACTERS);
        texy = texy.toUpperCase();
    	for(int n = 0; n < texy.length(); n++) {
    		Array7x7 character = filler.fillWithOneType((int) texy.charAt(n));
    		message.add(character);
    	}

        //which char in msg
        shiftCounter =0;
        //column index
        if (dir == DIRECTION.LEFT) {
            shiftArrayIdx = 0;

        }
        if(dir == DIRECTION.RIGHT)
        {
            shiftArrayIdx = 6;
        }
        //start timer
    	timer = new Timer();
    	timer.schedule(new flowTextTimer(), 50, 50);
    }

    public void flowBigText() {
        colorDisplay[view.getPages() - 1] = model;
        Array7 lastCol = colorDisplay[view.getPages() - 1].getCol(6);
        for(int n = colorDisplay.length - 1; n >= 0; n--) {
            lastCol = shifter.shift(colorDisplay[n], lastCol, dir);
        }
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
            flowBigText();
            updateView2();
        }
    }

    /**
     * Pausar timern
     */
    public void pause() {
        timer.cancel();
    }

    /**
     * Startar timern igen
     */
    public void resume() {
        timer = new Timer();    //Då shiftcounter och shiftArrayIdx inte ändras kan man bara göra en ny timer
        timer.schedule(new flowTextTimer(), 50, 50);
    }

    /**
     * Skiftar bara ett steg åt dir
     * @param dir vilket håll det ska skiftas åt
     */
    public void simpleShift(DIRECTION dir) {
        DIRECTION currentDir = this.dir;
        this.dir = dir;
        shiftString();
        this.dir = currentDir;
    }



    /**
     * Btn click
     * Satter en ny rad i modelen.
     */
    public void setRow(int rowPos, int[] newRow) {
        model.setRow(rowPos, new Array7(newRow));
        updateView();
    }

    /**
     * Btn click
     * Satter en ny kolumn i modelen.
     */
    public void setCol(int colPos, int[] newCol) {
        model.setCol(colPos, new Array7(newCol));
        updateView();
    }


    /**
     * Btn click
     * Satter ett nytt element i modelen.
     */
    public void setElement(int rowPos, int colPos, int value) {

        model.setElement(rowPos, colPos, value);
        updateView();
    }

    /**
     * Btn click
     * Hamtar rad i modelen.
     */
    public int[] getRow(int rowPos) {
        return model.getRow(rowPos).getAll();
    }

    /**
     * Btn click
     * Hamtar kolumn i modelen.
     */
    public int[] getCol(int colPos) {
        return model.getCol(colPos).getAll();
    }

    /**
     * Btn click
     * Hamtar element i modelen.
     */
    public int getElement(int rowPos, int colPos) {
        return model.getElement(rowPos,colPos);
    }


	public void showShift(Array7 input) {
		shift(input);
		updateView();

	}


}
