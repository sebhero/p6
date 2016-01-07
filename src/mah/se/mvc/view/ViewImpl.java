package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;

import java.util.ArrayList;

/**
 * Created by seb on 2015-12-18.
 */
public interface ViewImpl {

    void setCtrl(Controller ctrl);

    void updateView(int[][] all);

    int getHorizontalPages();
    int getVerticalPages();


    void updateView(ArrayList<int[][]> all, Controller.DIRECTION dir);

}
