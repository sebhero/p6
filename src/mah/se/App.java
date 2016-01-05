package mah.se;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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
class App extends JFrame{

    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
           		ViewImpl view = new MrBigViewWindowsWithFlowText(1, 6, Color.BLACK, Color.GRAY);
                //with FlowText
                //With numbers
//                ViewImpl view = new ViewNumbers();
                Array7x7 model = new Array7x7();
                new Controller(model, view);
                BorderLayout layout = new BorderLayout();
                JFrame frame = new JFrame();
                frame.setLayout(layout);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add((Component) view, layout.CENTER);
                frame.pack();
                frame.setVisible(true);

            }
        });*/
        new App(1);
    }

    public App(int choice) {
        ViewImpl view = new MrBigViewWindowsWithFlowText(1, 6, Color.BLACK, Color.GRAY);
        //with FlowText
        //With numbers
//                ViewImpl view = new ViewNumbers();
        Array7x7 model = new Array7x7();
        new Controller(model, view);
        BorderLayout layout = new BorderLayout();
        JFrame frame = new JFrame();
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add((Component) view, layout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(choice == 1) {
                    ((Component) view).setSize(frame.getSize());
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }



}

