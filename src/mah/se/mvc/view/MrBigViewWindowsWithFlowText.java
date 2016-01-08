package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import roffe.Color.ColorDisplay;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Gustaf on 18/12/2015.
 * Innehåller en ColorDisplay och en meny för att kontrollera den
 */
public class MrBigViewWindowsWithFlowText extends JPanel implements ViewImpl {

    /**
     * An enum containing the different states of the program
     */
    public enum STATE {
        RUNNING,
        PAUSED,
        INITIATED, UNINITIATED
    }

    /**
     * Sets the size of the panel and the ColorDisplay object depending on the dimension passed in
     * @param dimension the dimension of the panel
     */
    @Override
    public void setSize(Dimension dimension) {
	    double newSize = dimension.getWidth()/this.getWidth();
	    int newSizeW = (int) (newSize * this.getWidth()-100);
	    int newSizeH = (int) (newSize * this.getHeight()-100);
	    super.setSize(dimension);
        colorDisplay.setPreferredSize(new Dimension(newSizeW,newSizeH));
	    this.revalidate();
	    this.repaint();
        System.out.println(getWidth());
        System.out.println(getHeight());

    }

    private STATE currentState = STATE.UNINITIATED;
    private Controller controller;
    private ColorDisplay colorDisplay;
    private int verticalPages, backgroundColor, gridColor;
    private JTextField input;
    private JButton start, pause, displayText, simpleShiftRight, simpleShiftLeft, simpleShiftUp, simpleShiftDown, changeDirectionLeft, changeDirectionRight, changeDirectionUp, changeDirectionDown, stop;
    private JSlider speedSlider;
    private String flowText;


    /**
     * Konstruktor
     * @param backgroundColor   Bakgroundsfärgen på ColorDisplay
     * @param gridColor         Färgen mellan varje ruta i ColorDisplay
     */
    public MrBigViewWindowsWithFlowText(int backgroundColor, int gridColor) {
        this.backgroundColor = backgroundColor;
        this.gridColor = gridColor;
        this.setPreferredSize(new Dimension(1321, 617));
        this.setBackground(new Color(136, 136, 136));
    }

    /**
     * Får en inmatning av användaren för verticalPages
     * @return  verticalPages
     */
    private int inputVerticalPages() {
        try {
            int verticalPages = Integer.parseInt(JOptionPane.showInputDialog(null, "Hur många bokstäver ska få plats (1 - 10)"));
            if(verticalPages > 0 && verticalPages < 11)
                return verticalPages;
            else {
                JOptionPane.showMessageDialog(null, "välj ett tal mellan 1 - 10");
                return inputVerticalPages();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Du måste mata in ett heltal");
            return inputVerticalPages();
        }
    }

    /**
     * initierar panelen med en colordisplay, knappar, en textfield och en slider
     */
    public void init() {
        verticalPages = inputVerticalPages();
        colorDisplay = new ColorDisplay(1, verticalPages, backgroundColor, gridColor);
        JPanel buttonPanel = initButtons();
        setButtonsActive();
        add(colorDisplay, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * skapar knapparna, lägger till actionlisteners och lägger till de i panelen
     * @return panel med knapparna och textfielden
     */
    public JPanel initButtons() {
        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.setBackground(new Color(136, 136, 136));
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(136, 136, 136));
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(136, 136, 136));
        input = new JTextField(10);
        start = new JButton("Start");
        pause = new JButton("Pause");
        displayText = new JButton("Load in text");
        simpleShiftLeft = new JButton("Shift one step left");
        simpleShiftRight = new JButton("Shift one step right");
        simpleShiftUp = new JButton("Shift one step up");
        simpleShiftDown = new JButton("Shift one step down");
        changeDirectionRight = new JButton("RIGHT");
        changeDirectionLeft = new JButton("LEFT");
        changeDirectionDown = new JButton("DOWN");
        changeDirectionUp = new JButton("UP");
        stop = new JButton("Stop");
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
        simpleShiftDown.addActionListener(buttonListener);
        simpleShiftUp.addActionListener(buttonListener);
        changeDirectionRight.addActionListener(buttonListener);
        changeDirectionLeft.addActionListener(buttonListener);
        changeDirectionDown.addActionListener(buttonListener);
        changeDirectionUp.addActionListener(buttonListener);
        stop.addActionListener(buttonListener);
        speedSlider.addChangeListener(e -> controller.setSpeed(110 - speedSlider.getValue()));

        panel1.add(input);
        panel1.add(displayText);
        panel1.add(start);
        panel1.add(pause);
        panel1.add(stop);
        panel1.add(changeDirectionLeft);
        panel1.add(changeDirectionRight);
        panel1.add(changeDirectionUp);
        panel1.add(changeDirectionDown);
        panel2.add(simpleShiftLeft);
        panel2.add(simpleShiftRight);
        panel2.add(simpleShiftUp);
        panel2.add(simpleShiftDown);
        panel2.add(speedSlider);
        panel.add(panel1);
        panel.add(panel2);
        return panel;
    }

    /**
     * Sätter vilka knappar som ska vara aktiva beroende på state
     */
    public void setButtonsActive() {
        switch (currentState) {
            case RUNNING:
                displayText.setEnabled(false);
                start.setEnabled(false);
                pause.setEnabled(true);
                changeDirectionRight.setEnabled(true);
                changeDirectionLeft.setEnabled(true);
                changeDirectionDown.setEnabled(true);
                changeDirectionUp.setEnabled(true);
                simpleShiftLeft.setEnabled(false);
                simpleShiftRight.setEnabled(false);
                simpleShiftUp.setEnabled(false);
                simpleShiftDown.setEnabled(false);
                stop.setEnabled(true);
                break;
            case PAUSED:
                displayText.setEnabled(false);
                start.setEnabled(true);
                pause.setEnabled(false);
                switch (controller.getDirection()) {
                    case LEFT:
                    case RIGHT:
                        simpleShiftLeft.setEnabled(true);
                        simpleShiftRight.setEnabled(true);
                        simpleShiftUp.setEnabled(false);
                        simpleShiftDown.setEnabled(false);
                        break;
                    case UP:
                    case DOWN:
                        simpleShiftLeft.setEnabled(false);
                        simpleShiftRight.setEnabled(false);
                        simpleShiftUp.setEnabled(true);
                        simpleShiftDown.setEnabled(true);
                        break;
                }
                changeDirectionRight.setEnabled(false);
                changeDirectionLeft.setEnabled(false);
                changeDirectionDown.setEnabled(false);
                changeDirectionUp.setEnabled(false);
                stop.setEnabled(true);
                break;
            case UNINITIATED:
                displayText.setEnabled(true);
                start.setEnabled(false);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(false);
                simpleShiftRight.setEnabled(false);
                simpleShiftDown.setEnabled(false);
                simpleShiftUp.setEnabled(false);
                changeDirectionRight.setEnabled(false);
                changeDirectionLeft.setEnabled(false);
                changeDirectionDown.setEnabled(false);
                changeDirectionUp.setEnabled(false);
                stop.setEnabled(false);
                break;
            case INITIATED:
                displayText.setEnabled(true);
                start.setEnabled(true);
                pause.setEnabled(false);
                switch (controller.getDirection()) {
                    case LEFT:
                    case RIGHT:
                        simpleShiftLeft.setEnabled(true);
                        simpleShiftRight.setEnabled(true);
                        simpleShiftUp.setEnabled(false);
                        simpleShiftDown.setEnabled(false);
                        break;
                    case UP:
                    case DOWN:
                        simpleShiftLeft.setEnabled(false);
                        simpleShiftRight.setEnabled(false);
                        simpleShiftUp.setEnabled(true);
                        simpleShiftDown.setEnabled(true);
                        break;
                }
                changeDirectionRight.setEnabled(false);
                changeDirectionLeft.setEnabled(false);
                changeDirectionDown.setEnabled(false);
                changeDirectionUp.setEnabled(false);
                stop.setEnabled(false);
                break;
        }
    }

    /**
     * Sätter controllern
     * @param ctrl
     */
    @Override
    public void setCtrl(Controller ctrl) {
        controller = ctrl;
    }

    @Override
    @Deprecated //method is not used
    public void updateView(int[][] all) {

    }

    /**
     * Tömmer displayen och nollställer alla värden som bestämmer vad som ska visas i ColorDisplayen
     */
    public void clearDisplay() {
        colorDisplay.clearDisplay();
        controller.clearAll();
        controller.shiftOutAll();
    }


    /**
     * Returnerar ColorDisplayens antal horisontella sidor
     * @return horisontella sidor
     */
    @Override
    public int getHorizontalPages() {
        return colorDisplay.getHorizontalPages();
    }

    /**
     * Returnerar ColorDisplayens antal vertikala sidor
     * @return vertikala sidor
     */
    @Override
    public int getVerticalPages() {
        return colorDisplay.getVerticalPages();
    }

    /**
     * Uppdaterar hela ColorDisplayen
     * @param all vad som ska visas i ColorDisplayen
     * @param dir vilken riktning det ska visas i
     */
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

    /**
     * Klassen sköter alla knapptryck
     */
    private class ButtonListener implements ActionListener {

        /**
         * Utför en sekvens kd beroende på vilken knapp som blev tryckt och ändrar vilken state programmet är i
         * @param e knappen som blev nedtryckts actionEvent
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            /*
             * Tömmer ColorDisplayen och startar rinnande text om
             */
            if(e.getSource() == start && currentState == STATE.INITIATED) {
                colorDisplay.clearDisplay();
                controller.flowText();
                currentState = STATE.RUNNING;
            }
            /*
             * Pausar timern
             */
            else if(e.getSource() == pause) {
                controller.pause();
                currentState = STATE.PAUSED;
            }
            /*
             * Tömmer displayen och nollställer värdena
             */
            else if(e.getSource() == stop) {
                clearDisplay();
                currentState = STATE.INITIATED;
            }
            /*
             * startar timern
             */
            else if(e.getSource() == start && currentState == STATE.PAUSED) {
                controller.resume();
                currentState = STATE.RUNNING;
            }
            /*
             * laddar texten som ska visas
             */
            else if(e.getSource() == displayText) {
                if((flowText = input.getText().toString()).length() != 0) {
                    currentState = STATE.INITIATED;
                    controller.loadFlowText(flowText);
                } else
                    JOptionPane.showMessageDialog(null, "Empty inprutt");
            }
            /*
             * skiftar ett steg år vänster
             */
            else if(e.getSource() == simpleShiftLeft && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.LEFT);
            }
            /*
             * skiftar ett steg åt höger
             */
            else if(e.getSource() == simpleShiftRight && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.RIGHT);
            }
            /*
             * skiftar ett steg upp
             */
            else if(e.getSource() == simpleShiftUp && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.UP);
            }
            /*
             * skiftar ett steg ner
             */
            else if(e.getSource() == simpleShiftDown && currentState != STATE.UNINITIATED) {
                controller.simpleShift(Controller.DIRECTION.DOWN);
            }
            /*
             * sätter colorDisplayen till horisontell, tömmer den och sätter direction till right
             */
            else if(e.getSource() == changeDirectionRight) {
                colorDisplay.setNew7x7Size(verticalPages, 1);
                controller.setDirection(Controller.DIRECTION.RIGHT);
                colorDisplay.clearDisplay();
                controller.clearAll();
                controller.refreshMainPanel();
                controller.loadFlowText(flowText);
                controller.flowText();
            }
            /*
             * sätter colorDisplayen till horisontell, tömmer den och sätter direction till left
             */
            else if(e.getSource() == changeDirectionLeft) {
                colorDisplay.setNew7x7Size(verticalPages, 1);
                controller.setDirection(Controller.DIRECTION.LEFT);
                colorDisplay.clearDisplay();
                controller.clearAll();
                controller.refreshMainPanel();
                controller.loadFlowText(flowText);
                controller.flowText();
            }
            /*
             * sätter colorDisplayen till vertikal, tömmer den och sätter direction till upp
             */
            else if(e.getSource() == changeDirectionUp) {
                colorDisplay.setNew7x7Size(1, verticalPages);
                controller.setDirection(Controller.DIRECTION.UP);
                colorDisplay.clearDisplay();
                controller.clearAll();
                controller.refreshMainPanel();
                controller.loadFlowText(flowText + " ");
                controller.flowText();
            }
            /*
             * sätter colorDisplayen till vertikal, tömmer den och sätter direction till ner
             */
            else if(e.getSource() == changeDirectionDown) {
                colorDisplay.setNew7x7Size(1, verticalPages);
                controller.setDirection(Controller.DIRECTION.DOWN);
                colorDisplay.clearDisplay();
                controller.clearAll();
                controller.refreshMainPanel();
                controller.loadFlowText(flowText + " ");
                controller.flowText();
            }
            //sätter rätt knappar aktiva
            setButtonsActive();
        }
    }
}
