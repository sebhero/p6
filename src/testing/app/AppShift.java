package testing.app;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewImpl;
import mah.se.mvc.view.ViewNumbers;
import mah.se.mvc.view.ViewShiftTest;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Start the app.
 * FUCKFACE
 * Creates the model and controller and view
 */
class AppShift {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewImpl view = new ViewShiftTest();
                Array7x7 model = new Array7x7();
                new Controller(model, view);
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add((Component) view);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
