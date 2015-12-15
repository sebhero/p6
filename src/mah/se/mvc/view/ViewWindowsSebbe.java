package mah.se.mvc.view;

import mah.se.Color.ColorDisplay;
import mah.se.mvc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class ViewWindowsSebbe extends JPanel {
    private Controller ctrl;
    private ColorDisplay displayColor;
    private JTextPane display;
    private JButton btnRandom = new JButton("Slumpa tal");
    private JButton btnRandomSame = new JButton("Slumpa samma tal");
    private JButton btn1_7 = new JButton("visa Nr 1-7");
    private JButton btnRandomColor = new JButton("Slumpa Color");
    private JButton btnRandomSameColor = new JButton("Slumpa samma color");
    private JButton btn1_7Color = new JButton("Color gradient");
    private JButton btnRandomChar = new JButton("Slumpa Char");

    public ViewWindowsSebbe(int background, int grid) {
        ButtonListener bl = new ButtonListener();
        setLayout(new BorderLayout());

        displayColor = new ColorDisplay(background, grid);
        displayColor.setPreferredSize(new Dimension(200, 120));
        displayColor.setSize(100, 120);
        displayColor.setVisible(false);
        add(displayColor, BorderLayout.CENTER);

        display = new JTextPane();
        display.setPreferredSize(new Dimension(100, 120));
        display.setSize(100,120);

        add(display, BorderLayout.CENTER);

        JPanel btnGrid = new JPanel(new GridLayout(2, 1));
        btnGrid.add(buttonPanel());
        btnGrid.add(buttonPanelColor());

        add(btnGrid, BorderLayout.SOUTH);
        btnRandom.addActionListener(bl);
        btnRandomSame.addActionListener(bl);
        btn1_7.addActionListener(bl);

        
//        btnRandomColor.addActionListener(ae -> {
//            ctrl.showRandomColor();
//            System.out.println("Show random color");
//        });
//
//        btnRandomSameColor.addActionListener(ar ->{
//            System.out.println("show color same");
//            ctrl.showSameColor();
//        });
//        btn1_7Color.addActionListener(ae -> {
//            System.out.println("show color gradient");
//            ctrl.showGradiantColor();
//        });
//
//        btnRandomChar.addActionListener(ae -> {
//            ctrl.showRanomChar();
//        });
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.add(btnRandom);
        panel.add(btnRandomSame);
        panel.add(btn1_7);
        return panel;
    }
    private JPanel buttonPanelColor() {
        JPanel panel = new JPanel(new GridLayout(1,4));
        panel.add(btnRandomColor);
        panel.add(btnRandomSameColor);
        panel.add(btn1_7Color);
        panel.add(btnRandomChar);
        return panel;
    }

    /**
     * Set the controller to the view
     * @param ctrl
     */
    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }


    /**
     * Updates the view from the controller
     * @param matrix the int 7x7 matrix
     */
    public void updateView(int[][] matrix) {
        //TODO fullkod
        display.setVisible(true);
        displayColor.setVisible(false);
        display.setPreferredSize(new Dimension(100, 120));
        display.setSize(100,120);

        String txt = "";
        for (int[] row : matrix) {
            txt += Arrays.toString(row) + "\n";
        }
        display.setText(txt);

    }

    public void updateViewColor(int[][] matrix) {


        //TODO fullkod
        display.setVisible(false);
        displayColor.setVisible(true);
        displayColor.setPreferredSize(new Dimension(200, 120));

        displayColor.setDisplay(matrix);
        displayColor.updateDisplay();

    }

    /**
     * Calling the controller using btns
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
             if(e.getSource()==btnRandom) {
                ctrl.showRandom();
            } else if(e.getSource()==btnRandomSame) {
                ctrl.showRandomSame();
            } else if(e.getSource()== btn1_7) {
                //visa 0
                ctrl.showNumbers1_7();
            }

        }
    }
}
