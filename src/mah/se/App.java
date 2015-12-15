package mah.se;

import mah.se.Color.Color;
import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewWindows;

import javax.swing.*;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Start the app.
 * jonte
 * Creates the model and controller and view
 */
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ViewWindows demo = new ViewWindows(Color.BLACK, Color.GRAY);
                Array7x7 model = new Array7x7();
                new Controller(model,demo);
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(demo);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
