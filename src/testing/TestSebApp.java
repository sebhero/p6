package testing;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import roffe.Color.Color;

import javax.swing.*;

/**
 * Created by seb on 2015-12-16.
 */
public class TestSebApp {


        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ViewWindowsSebbe demo = new ViewWindowsSebbe(Color.BLACK, Color.GRAY);
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

