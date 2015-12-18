package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;

/**
 * Created by seb on 2015-12-18.
 */
public interface viewImpl {

    void setCtrl(Controller ctrl);

    void updateView(int[][] all);

    void updateViewColor(int[][] all);
}
