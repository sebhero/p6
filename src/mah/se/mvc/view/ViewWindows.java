package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class ViewWindows extends JPanel {
    private Controller ctrl;
//    private ColorDisplay display;
    private JTextPane display;
    private JButton btnShiftR = new JButton("Shifta Right");
    private JButton btnRandom = new JButton("Slumpa tal");
    private JButton btnRandomSame = new JButton("Slumpa samma tal");
    private JButton btnVisa0 = new JButton("visa Nr 1-7");

    public ViewWindows(int background, int grid) {
        ButtonListener bl = new ButtonListener();
        setLayout(new BorderLayout());
//        display = new ColorDisplay(background, grid);
        display = new JTextPane();
        display.setPreferredSize(new Dimension(100, 120));
        add(display, BorderLayout.CENTER);
        add(buttonPanel(), BorderLayout.SOUTH);
        btnShiftR.addActionListener(bl);
        btnRandom.addActionListener(bl);
        btnRandomSame.addActionListener(bl);
        btnVisa0.addActionListener(bl);
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.add(btnShiftR);
        panel.add(btnRandom);
        panel.add(btnRandomSame);
        panel.add(btnVisa0);
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
        String txt = "";
        for (int[] row : matrix) {
            txt += Arrays.toString(row) + "\n";
        }
        display.setText(txt);

    }

    /**
     * Calling the controller using btns
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()== btnShiftR) {
                //slumpa tal
                ctrl.shiftRight();
            } else if(e.getSource()==btnRandom) {
                ctrl.showRandom();
            } else if(e.getSource()==btnRandomSame) {
                ctrl.showRandomSame();
            } else if(e.getSource()== btnVisa0) {
                //visa 0
                ctrl.showNumbers1_7();
            }

        }
    }
}
