package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import roffe.Color.ColorDisplay;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Gustaf on 18/12/2015.
 */
public class MrBigViewWindowsWithFlowText extends JPanel implements ViewImpl {

    public enum STATE {
        RUNNING,
        PAUSED,
        INITIATED, UNINITIATED
    }

    @Override
    public void setSize(Dimension dimension) {
        super.setSize(dimension);
        //colorDisplay.setSize((int)dimension.getWidth(), (int)dimension.getHeight() - 100);

        double newSize = dimension.getWidth()/1300;
        int newSizeW = (int) (newSize * 1200);
        int newSizeH = (int) (newSize * 200);
//        System.out.println("new size "+newSize+" dim"+dimension.getWidth());
        System.out.println(colorDisplay.getWidth());
//        colorDisplay.setSize(newSizeW, newSizeH);
        colorDisplay.setPreferredSize(new Dimension(newSizeW,newSizeH));
//        colorDisplay.revalidate();
        colorDisplay.repaint();
        System.out.println(colorDisplay.getWidth());
        //1200x200
        //1300x300
    }

    private STATE currentState = STATE.UNINITIATED;
    private Controller controller;
    private ColorDisplay colorDisplay;
    private int verticalPages, horizontalPages, backgroundColor, gridColor;
    private JTextField input;
    private JButton start, pause, displayText, simpleShiftRight, simpleShiftLeft, changeDirection;
    private JSlider speedSlider;

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
        this.setPreferredSize(new Dimension(1300,300));
        init();
    }

    /**
     * initierar panelen med en colordisplay, knappar och en textfield
     */
    public void init() {
        colorDisplay = new ColorDisplay(verticalPages, horizontalPages, backgroundColor, gridColor);
        colorDisplay.setPreferredSize(new Dimension(horizontalPages * 200, verticalPages * 200));
        JPanel buttonPanel = initButtons();
        JPanel buttonPanel2 = initButtons2();
        JPanel BigButtonsPanel = new JPanel(new GridLayout(2, 1));
        BigButtonsPanel.add(buttonPanel);
        BigButtonsPanel.add(buttonPanel2);
        setButtonsActive();
        add(colorDisplay, BorderLayout.CENTER);
        add(BigButtonsPanel, BorderLayout.SOUTH);

    }

    private JPanel initButtons2() {
        JPanel panel = new JPanel();
        JButton newButton = new JButton("Test");
        panel.add(newButton);
        return panel;
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
        speedSlider = new JSlider();
        speedSlider.setMinimum(10);
        speedSlider.setMaximum(100);
        speedSlider.setValue(60);

        ButtonListener buttonListener = new ButtonListener();
        start.addActionListener(buttonListener);
        pause.addActionListener(buttonListener);
        displayText.addActionListener(buttonListener);
        simpleShiftLeft.addActionListener(buttonListener);
        simpleShiftRight.addActionListener(buttonListener);
        changeDirection.addActionListener(buttonListener);
        speedSlider.addChangeListener(e -> controller.setSpeed(110 - speedSlider.getValue()));

        panel.add(input);
        panel.add(displayText);
        panel.add(start);
        panel.add(pause);
        panel.add(changeDirection);
        panel.add(simpleShiftLeft);
        panel.add(simpleShiftRight);
        panel.add(speedSlider);
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
                simpleShiftLeft.setEnabled(false);
                simpleShiftRight.setEnabled(false);
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
            case INITIATED:
                start.setEnabled(true);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(true);
                simpleShiftRight.setEnabled(true);
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
    public void updateAllViews(Array7x7[] all) {
        for(int n = 0; n < horizontalPages; n++) colorDisplay.setDisplay(all[n].getAll(), 0, n);
    }

    @Override
    public void updateView(int[][] all) {

    }

    @Override
    public void updateBigView(Array7x7[] all) {
        for(int n = 0; n < horizontalPages; n++)
            colorDisplay.setDisplay(all[n].getAll(), 0, n);
        colorDisplay.updateDisplay();
    }

    public void runThisShit(String texy) {
        controller.loadFlowText(texy);
        controller.flowText();
        currentState = STATE.RUNNING;
    }


    @Override
    public int getHorizontalPages() {
        return colorDisplay.getHorizontalPages();
    }

    @Override
    public int getVerticalPages() {
        return colorDisplay.getVerticalPages();
    }

    @Override
    public void updateView(ArrayList<int[][]> all, Controller.DIRECTION dir) {
        for (int i = 0; i < all.size(); i++) {
            switch (dir) {
                case RIGHT:
                    colorDisplay.setDisplay(all.get(i), 0, i);
                    break;
                case LEFT:
                    colorDisplay.setDisplay(all.get(i), 0, i);
                    break;
                case UP:
                    colorDisplay.setDisplay(all.get(i), i, 0);
                    break;
                case DOWN:
                    colorDisplay.setDisplay(all.get(i), i, 0);
                    break;
            }
        }
        colorDisplay.updateDisplay();
    }


    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == start && currentState == STATE.INITIATED) {
                controller.flowText();
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
                if((flowText = input.getText().toString()).length() != 0) {
                    currentState = STATE.INITIATED;
                    controller.setDirection(Controller.DIRECTION.LEFT);
                    controller.loadFlowText(flowText);
                } else
                    JOptionPane.showMessageDialog(null, "Empty inprutt");
            }
            else if(e.getSource() == simpleShiftLeft && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.LEFT);
            }
            else if(e.getSource() == simpleShiftRight && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.RIGHT);
            }
            else if(e.getSource() == changeDirection && currentState != STATE.UNINITIATED) {
                String dirText = "";
                switch (controller.getDirection()) {
                    case LEFT:
                        controller.setDirection(Controller.DIRECTION.RIGHT);
                        dirText = "Left";
                        break;
                    case RIGHT:
                        controller.setDirection(Controller.DIRECTION.UP);
                        dirText = "Rigt";
                        break;
                    case UP:
                        controller.setDirection(Controller.DIRECTION.DOWN);
                        dirText = "Down";
                        break;
                    case DOWN:
                        controller.setDirection(Controller.DIRECTION.LEFT);
                        dirText = "Up";
                        break;
                }
                changeDirection.setText("ChangeDirection: "+dirText);
            }
            setButtonsActive();
        }
    }
}
