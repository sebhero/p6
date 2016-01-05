package mah.se;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.MrBigViewWindowsWithFlowText;
import mah.se.mvc.view.ViewImpl;
import roffe.Color.Color;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Start the app.
 * FUCKFACE
 * Creates the model and controller and view
 */
class App {

    public static void main(String[] args, int i) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
           		ViewImpl view = new MrBigViewWindowsWithFlowText(1, 6, Color.BLACK, Color.GRAY);
                //with FlowText
                //With numbers
//                ViewImpl view = new ViewNumbers();
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

