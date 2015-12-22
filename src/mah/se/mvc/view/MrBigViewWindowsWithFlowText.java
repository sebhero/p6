package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import roffe.Color.ColorDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gustaf on 18/12/2015.
 */
public class MrBigViewWindowsWithFlowText extends JPanel implements ViewImpl {

    public enum STATE {
        RUNNING,
        PAUSED,
        UNINITIATED
    }

    private STATE currentState = STATE.UNINITIATED;
    private Controller controller;
    private ColorDisplay colorDisplay;
    private int verticalPages, horizontalPages, backgroundColor, gridColor;
    private JTextField input;
    private JButton start, pause, displayText, simpleShiftRight, simpleShiftLeft, changeDirection;

    private String flowText;


    /**
     * Konstruktor
     * @param verticalPages     Hur många Array7x7 objekt vertikalt
     * @param horizontalPages   Hur många Array7x7 objekt horisontellt
     * @param backgroundColor   Bakgroundsfärgen på ColorDisplay
     * @param gridColor         Färgen mellan varje ruta i ColorDisplay
     */
    public MrBigViewWindowsWithFlowText(int verticalPages, int horizontalPages, int backgroundColor, int gridColor) {
        this.verticalPages = verticalPages;
        this.horizontalPages = horizontalPages;
        this.backgroundColor = backgroundColor;
        this.gridColor = gridColor;
        init();
    }

    /**
     * initierar panelen med en colordisplay, knappar och en textfield
     */
    public void init() {
        colorDisplay = new ColorDisplay(verticalPages, horizontalPages, backgroundColor, gridColor);
        JPanel buttonPanel = initButtons();
        setButtonsActive();
        add(colorDisplay, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * skapar knapparna, initierar dom och lägger till actionlisteners
     * @return panel med knapparna och textfielden
     */
    public JPanel initButtons() {
        JPanel panel = new JPanel();
        input = new JTextField(10);
        start = new JButton("Start");
        pause = new JButton("Pause");
        displayText = new JButton("Load in text");
        simpleShiftLeft = new JButton("Shift one step left");
        simpleShiftRight = new JButton("Shift one step right");
        changeDirection = new JButton("ChangeDirection");

        ButtonListener buttonListener = new ButtonListener();
        start.addActionListener(buttonListener);
        pause.addActionListener(buttonListener);
        displayText.addActionListener(buttonListener);
        simpleShiftLeft.addActionListener(buttonListener);
        simpleShiftRight.addActionListener(buttonListener);
        changeDirection.addActionListener(buttonListener);

        panel.add(input);
        panel.add(displayText);
        panel.add(start);
        panel.add(pause);
        panel.add(changeDirection);
        panel.add(simpleShiftLeft);
        panel.add(simpleShiftRight);
        return panel;
    }

    /**
     * Sätter vilka knappar som ska vara aktiva beroende på state
     */
    public void setButtonsActive() {
        switch (currentState) {
            case RUNNING:
                start.setEnabled(false);
                pause.setEnabled(true);
                changeDirection.setEnabled(true);
                break;
            case PAUSED:
                start.setEnabled(true);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(true);
                simpleShiftRight.setEnabled(true);
                changeDirection.setEnabled(false);
                break;
            case UNINITIATED:
                start.setEnabled(false);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(false);
                simpleShiftRight.setEnabled(false);
                changeDirection.setEnabled(false);
                break;
        }
    }

    /**
     * Sätter controllern
     * @Param ctrl
     */
    @Override
    public void setCtrl(Controller ctrl) {
        controller = ctrl;
    }

   // @Override kommentera bort detta när det finns metod för att skicka in Array7x7[] ist för int[][]
    // En int[][] räcker inte då vi har flera Array7x7 objekt som visas
    public void updateView(Array7x7[] all) {
        for(int n = 0; n < horizontalPages; n++) colorDisplay.setDisplay(all[n].getAll(), 0, n);
    }

    @Override
    public void updateView(int[][] all) {

    }

    //fattar inte riktigt vad denna ska göra
    @Override
    public void updateViewColor(int[][] all) {

    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == start) {
                controller.flowText(flowText);
                currentState = STATE.RUNNING;
            }
            else if(e.getSource() == pause) {
                controller.pause();
                currentState = STATE.PAUSED;
            }
            else if(e.getSource() == start && currentState == STATE.PAUSED) {
                controller.resume();
                currentState = STATE.RUNNING;
            }
            else if(e.getSource() == displayText) {
                if((flowText = input.getText()).length() != 0) {
                    currentState = STATE.PAUSED;
                    actionPerformed(new ActionEvent(start, 0, "")); //denna e fan tvek, annars får man göra en metod
                } else
                    JOptionPane.showMessageDialog(null, "Empty input");
            }
            else if(e.getSource() == simpleShiftLeft && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.LEFT);
            }
            else if(e.getSource() == simpleShiftRight && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.RIGHT);
            }
            else if(e.getSource() == changeDirection && currentState != STATE.UNINITIATED) {
                switch (controller.getDirection()) {
                    case LEFT:
                        controller.setDirection(Controller.DIRECTION.RIGHT);
                        break;
                    case RIGHT:
                        controller.setDirection(Controller.DIRECTION.LEFT);
                        break;
                }
            }
        }
    }
}
