package mah.se.mvc.controller;

import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;
import mah.se.strategy.StrategyFill;
import mah.se.strategy.StrategyFillNumbers;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 * 20:00 2015-12-13.
 */
public class Controller {

    Array7x7 model;
    ViewWindows view;
    StrategyFill fill;
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
        //TODO use proper just for testing
        fill = new StrategyFillNumbers();
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
        view.updateView(model.getMatrix());
    }

    /**
     * btn click
     * Show total random numbers in display
     */
    public void showRandom() {

        model.setMatrix(fill.fill(this.model, SHOW.RANDOM));
        updateView();
    }

    /**
     * btn click
     * show 1-7 numbers
     */
    public void showNumbers1_7() {
        model.setMatrix(fill.fill(this.model, SHOW.ONE_SEVEN));
        updateView();
    }

    /**
     * Btn call from view
     * Shift right the matrix
     */
    public void shiftRight() {
        model.setMatrix(fill.shiftRight(this.model));
        updateView();
    }

    /**
     * btn click from view
     * show same random number
     */
    public void showRandomSame() {
        model.setMatrix(fill.fill(this.model, SHOW.SAME));
        updateView();
    }
}
