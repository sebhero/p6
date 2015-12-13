package mah.se.mvc.controller;

import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;
import mah.se.strategy.StrategyFill;
import mah.se.strategy.StrategyFillNumbers;

import java.util.Random;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 * 20:00 2015-12-13.
 */
public class Controller {

    Array7x7 model;
    ViewWindows view;
    StrategyFill fill;
//    ViewAndroid view;

    public Controller(Array7x7 model, ViewWindows view) {
        this.model = model;
        this.view = view;
        this.view.setCtrl(this);
        //TODO use proper just for testing
        fill = new StrategyFillNumbers();
        updateView();
    }


    public void addView(ViewWindows view) {
        this.view = view;
        this.view.setCtrl(this);

    }

    public void addModel(Array7x7 model) {
        this.model = model;
    }

    /**
     * Update the view
     */
    private void updateView() {
        view.updateView(model.getMatrix());
    }

    //button call
    public void showRandom() {

        model.setMatrix(fill.fill(this.model, new Random().nextInt(10) + 1));
        updateView();
    }

    //button call
    public void showNumber0() {
        model.setMatrix(fill.fill(this.model));
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
}
