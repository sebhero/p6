package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;

/**
 * Created by seb on 2015-12-16.
 */
public interface ViewWindowsImpl {

    void setCtrl(Controller controller);

    void updateView(int[][] matrix);

    void updateViewColor(int[][] matrix);
}
