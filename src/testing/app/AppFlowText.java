package testing.app;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewImpl;
import roffe.Color.Color;
import testing.views.ViewWindowsWithFlowText;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Start the app.
 * Creates the model and controller and view
 */
class AppFlowText {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewImpl view = new ViewWindowsWithFlowText(Color.BLACK, Color.YELLOW);
                Array7x7 model = new Array7x7();
                Controller ctrl = new Controller(model, view);

                ctrl.setView(view);

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add((Component) view);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}

