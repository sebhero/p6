package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;

import java.util.ArrayList;

/**
 * Created by seb on 2015-12-18.
 */
public interface ViewImpl {

    void setCtrl(Controller ctrl);

    void updateView(int[][] all);

//    void updateView(ArrayList<int[][]> all);
//    void updateView(ArrayList<Array7x7> all);

    /**
     * @deprecated will be replaced by updateView
     * @param all
     */
//    void updateViewColor(int[][] all);
}
