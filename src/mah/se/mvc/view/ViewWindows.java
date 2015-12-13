package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.ModelMatrix7x7;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class ViewWindows {
    private Controller controller;

    public void updateView(ModelMatrix7x7 model) {
        System.out.println("update view with new model");
    }

    public void addController(Controller controller) {
        this.controller = controller;
    }
}
