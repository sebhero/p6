package mah.se.mvc.controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewAndroid;
import mah.se.mvc.view.ViewWindows;
import mah.se.patterns.strategy.FILLERTYPE;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillCharacter;
import mah.se.patterns.strategy.FillColor;
import mah.se.patterns.strategy.FillNumbers;
import roffe.Color.Color;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 * 20:00 2015-12-13.
 */
public class Controller extends ViewAndroid {
	
	public static enum DIRECTION {
		RIGHT,
		LEFT
	}

	private DIRECTION dir = DIRECTION.LEFT;
    private final ShiftArray shifter;
    private FillAlgorithm filler;
    private Array7x7 model;
    private ViewWindows view;
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
     * Add the view to the controller
     * @param view
     */
    public void addView(ViewWindows view) {
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
    public void shift() {
    	Array7 aids;//TODO fixa overflow
    	aids = shifter.shift(model, new Array7(), dir);
    }
    
    public void setDirection(DIRECTION dir) {
    	this.dir = dir;
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
    
    public void flowText(String texy) {
    	filler = getFiller(FILLERTYPE.CHARACTERS);
    	for(int n = 0; n < texy.length(); n++) {
    		Array7x7 character = filler.fillWithOneType((int) texy.charAt(n));
    		message.add(character);
    	}
    	model = message.get(0);
    	updateViewColor();
    	timer = new Timer();
    	timer.schedule(new timerTask(), 500, 500);
    	
    }
    
    private class timerTask extends TimerTask {
		@Override
		public void run() {
			shift();
			updateViewColor();
		}
    	
    }
}
