package mah.se;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.*;
import roffe.Color.Color;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Sebastian Börebäck on 2016-01-05.
 */
public class App extends JPanel implements ComponentListener {


    private final Controller ctrl;
    private static JPanel currentView;
    private JFrame frame;

    /**
     * Uppdatera size på currentview. så att colorDisplay blir uppdaterad.
     * @param e ComponentEvent
     */
    @Override
    public void componentResized(ComponentEvent e) {
        //extra to handle the resize of the view.
        currentView.setSize(e.getComponent().getSize());

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

    /**
     * Main vyn som hanterar byta vyer
     * @param frame fönstret som det ska visas i
     */
    public App(JFrame frame) {
        this.frame = frame;
        Array7x7 model = new Array7x7();

        JTabbedPane jtbMain = new JTabbedPane();

        JPanel numberPanel = new ViewNumbers();
        JPanel colorPanel= new ViewColor(Color.BLACK,Color.GRAY);
        JPanel shiftPanel= new ViewShift();
        JPanel mrBigPanel= new MrBigViewWindowsWithFlowText(Color.BLACK, Color.GRAY);


        jtbMain.addTab("Numbers", numberPanel);
        jtbMain.addTab("Colors", colorPanel);
        jtbMain.addTab("Shifting", shiftPanel);
        jtbMain.addTab("Mr big flow", mrBigPanel);

        jtbMain.setSelectedIndex(0);
        currentView = (JPanel) jtbMain.getSelectedComponent();

        ctrl = new Controller(model, (ViewImpl) jtbMain.getSelectedComponent());
        ctrl.setMainPanel(this);
        // Add the tabbed pane to this panel.
        setLayout(new GridLayout(1, 1));

        jtbMain.addChangeListener(e -> {
            if (jtbMain.getSelectedComponent() == mrBigPanel)
                ((MrBigViewWindowsWithFlowText) mrBigPanel).init();
            ctrl.setView((ViewImpl) jtbMain.getSelectedComponent());
            currentView = (JPanel) jtbMain.getSelectedComponent();

            //handeling the resizing between views
            Dimension dim = jtbMain.getSelectedComponent().getPreferredSize();
            frame.setSize(dim);

        });

        add(jtbMain);


    }

    /**
     * Main för att starta Appen.
     * @param args arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kirbys Hjältar");
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        App tabpanel = new App(frame);
        frame.getContentPane().add(new App(frame),
                BorderLayout.CENTER);
        frame.addComponentListener(tabpanel);
        frame.setSize(400, 300);
        frame.setVisible(true);


    }

    /**
     * Uppdaterar fönstrets storlek
     */
    public void refreshFrame() {
        frame.revalidate();
        frame.pack();
        frame.repaint();
    }
}
