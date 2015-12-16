package mah.se.mvc.controller;

import mah.se.mvc.view.ViewWindowsImpl;
import roffe.Color.Color;
import mah.se.algorithms.ShiftArray;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;
import mah.se.patterns.strategy.*;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 * 20:00 2015-12-13.
 */
public class Controller {

    private final ShiftArray shifter;
    private FillAlgorithm filler;
    private Array7x7 model;
    private ViewWindowsImpl view;
//    ViewAndroid view;

    /**
     * A controller to handles communication with the Array7x7 model and diffrent strategies for filling it.
     * @param model the Array7x7 model
     * @param view the view we are displaying the matrix on
     */
    public Controller(Array7x7 model, ViewWindowsImpl view) {
        this.model = model;
        this.view = view;
        this.view.setCtrl(this);
        filler = getFiller(FILLERTYPE.NUMBERS);
        shifter = new ShiftArray();
        updateView();
    }


    /**
     * Add the view to the controller
     * @param view
     */
    public void addView(ViewWindowsImpl view) {
        this.view = view;
        this.view.setCtrl(this);

    }

    /**
     * Add the model to the controller.
     * @param model
     */
    public void addModel(Array7x7 model) {
        this.model = model;
    }

    /**
     * Update the view
     */
    private void updateView() {
        view.updateView(model.getAll());
    }



    /**
     * Btn call from view
     * Shift right the matrix
     */
    public void shiftRight() {
    	shifter.shiftRight(model,new Array7());
        updateView();
    }

    public void shiftLeft() {
        shifter.shiftLeft(model, new Array7());
        updateView();
    }

    public void flowText(String text) {
        //TODO implent flowing text
        //using array of text and timer
        /**
         * Tar en string som omvandlas till char array.
         * d'refter kor vi fillchar som skapar array av charen
         * nasta char gor vi ocksa till en matrix
         * rita ut forsta char
         * dar efter var 300ms so gor vi en shiftright och skriver ut.
         */
        text = "AB";

        //char current = text.charAt(0);
        //char next = text.charAt(1);
        filler = getFiller(FILLERTYPE.CHARACTERS);
        Array7x7 current = filler.fillWithOneType(text.charAt(0));
        Array7x7 next = filler.fillWithOneType(text.charAt(1));



    }


    /**
     * btn click
     * Show total random numbers in display
     */
    public void showRandom() {
        filler = getFiller(FILLERTYPE.NUMBERS);
        model = filler.fillWithRandom();
        updateView();
    }

    /**
     * btn click
     * show 1-7 numbers
     */
    public void showNumbers1_7() {
        filler = getFiller(FILLERTYPE.NUMBERS);
        model = filler.fillWithInGaining();
        updateView();
    }

    /**
     * btn click from view
     * show same random number
     */
    public void showRandomSame() {
        filler = getFiller(FILLERTYPE.NUMBERS);

        int rnd = new Random().nextInt(7)+1;
        model = filler.fillWithOneType(rnd);
        updateView();
    }

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

    public void showRandomColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithRandom();
        updateViewColor();
    }

    private void updateViewColor() {
        view.updateViewColor(model.getAll());
    }

    public void showSameColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithOneType(Color.BLUE);
        updateViewColor();
    }

    public void showGradiantColor() {
        filler = getFiller(FILLERTYPE.COLORS);
        model = filler.fillWithInGaining();
        updateViewColor();
    }

    public void showRanomChar() {
        filler = getFiller(FILLERTYPE.CHARACTERS);
        model = filler.fillWithRandom();
        updateViewColor();
    }

    public Array7x7 getModel() {
        return model;
    }
}
