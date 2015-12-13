package mah.se.mvc.controller;

import mah.se.mvc.model.ModelMatrix7x7;
import mah.se.mvc.view.ViewAndroid;
import mah.se.mvc.view.ViewWindows;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 * 20:00 2015-12-13.
 */
public class Controller {

    ModelMatrix7x7 model;
    ViewWindows view;
//    ViewAndroid view;


    public Controller() {
        System.out.println("Controller created");
    }

    public Controller(ModelMatrix7x7 model, ViewWindows view) {
        this.model = model;
        this.view = view;
    }


    public void addView(ViewWindows view) {
        this.view = view;
        this.view.addController(this);

    }

    public void addModel(ModelMatrix7x7 model) {
        this.model = model;
    }

    private void updateView() {
        view.updateView(model);
    }

}
