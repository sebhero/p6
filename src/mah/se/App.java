package mah.se;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;
import mah.se.mvc.view.ViewWindowsImpl;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Start the app.
 * FUCKFACE
 * Creates the model and controller and view
 */
class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewWindowsImpl demo = new ViewWindows();
                Array7x7 model = new Array7x7();
                new Controller(model, demo);
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(demo);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}

