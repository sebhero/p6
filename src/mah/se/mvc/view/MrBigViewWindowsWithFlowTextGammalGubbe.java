package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import roffe.Color.ColorDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Gustaf on 18/12/2015.
 */
public class MrBigViewWindowsWithFlowTextGammalGubbe extends JPanel implements ViewImpl {


    private TextField tfColorPanelHeight;
    private TextField tfColorPanelWidth;

    public enum STATE {
        RUNNING,
        PAUSED,
        INITIATED, UNINITIATED
    }

    @Override
    public void setSize(Dimension dimension) {
	    double newSize = dimension.getWidth()/this.getWidth();
	    //sätter storleken på coloroDispl 100 mindre för att den ska få plats
	    int newSizeW = (int) (newSize * this.getWidth()-100);
	    int newSizeH = (int) (newSize * this.getHeight()-100);
	    super.setSize(dimension);
        colorDisplay.setPreferredSize(new Dimension(newSizeW,newSizeH));
	    this.revalidate();
	    this.repaint();
//        System.out.println(colorDisplay.getWidth());
    }

    private STATE currentState = STATE.UNINITIATED;
    private Controller controller;
    private ColorDisplay colorDisplay;
    private int verticalPages, horizontalPages, backgroundColor, gridColor;
    private JTextField input;
    private JButton start, pause, displayText, simpleShiftRight, simpleShiftLeft, changeDirection, changeDirectionUpDown, stop;
    private JSlider speedSlider;
    private Controller.DIRECTION dirUpDown = Controller.DIRECTION.DOWN, dirLeftRight = Controller.DIRECTION.LEFT;
    private JFrame parent;

    private String flowText;


    /**
     * Konstruktor
     * @param backgroundColor   Bakgroundsfärgen på ColorDisplay
     * @param gridColor         Färgen mellan varje ruta i ColorDisplay
     */
    public MrBigViewWindowsWithFlowTextGammalGubbe(int backgroundColor, int gridColor) {
        this.backgroundColor = backgroundColor;
        this.gridColor = gridColor;
        this.setPreferredSize(new Dimension(1300,350));
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
     * initierar panelen med en colordisplay, knappar och en textfield
     */
    public void init() {
        verticalPages = inputVerticalPages();
        colorDisplay = new ColorDisplay(1, verticalPages, backgroundColor, gridColor);
        JPanel buttonPanel = initButtons();
        JPanel BigButtonsPanel = new JPanel(new GridLayout(2, 1));
        BigButtonsPanel.add(buttonPanel);
        setButtonsActive();
        add(colorDisplay, BorderLayout.CENTER);
        add(BigButtonsPanel, BorderLayout.SOUTH);

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
        changeDirection = new JButton("ChangeDirection: RIGHT");
        changeDirectionUpDown = new JButton("ChangeDirection: UP");
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
        changeDirection.addActionListener(buttonListener);
        changeDirectionUpDown.addActionListener(buttonListener);
        stop.addActionListener(buttonListener);
        speedSlider.addChangeListener(e -> controller.setSpeed(110 - speedSlider.getValue()));

        panel.add(input);
        panel.add(displayText);
        panel.add(start);
        panel.add(pause);
        panel.add(stop);
        panel.add(changeDirection);
        panel.add(changeDirectionUpDown);
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
                changeDirectionUpDown.setEnabled(true);
                simpleShiftLeft.setEnabled(false);
                simpleShiftRight.setEnabled(false);
                stop.setEnabled(true);
                break;
            case PAUSED:
                start.setEnabled(true);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(true);
                simpleShiftRight.setEnabled(true);
                changeDirection.setEnabled(false);
                changeDirectionUpDown.setEnabled(false);
                stop.setEnabled(true);
                break;
            case UNINITIATED:
                start.setEnabled(false);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(false);
                simpleShiftRight.setEnabled(false);
                changeDirection.setEnabled(false);
                changeDirectionUpDown.setEnabled(false);
                stop.setEnabled(false);
                break;
            case INITIATED:
                start.setEnabled(true);
                pause.setEnabled(false);
                simpleShiftLeft.setEnabled(true);
                simpleShiftRight.setEnabled(true);
                changeDirection.setEnabled(false);
                changeDirectionUpDown.setEnabled(false);
                stop.setEnabled(false);
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

    public void clearDisplay() {
        colorDisplay.clearDisplay();
        controller.clearAll();
        controller.shiftOutAll();
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
                colorDisplay.clearDisplay();
                controller.flowText();
                currentState = STATE.RUNNING;
            }
            else if(e.getSource() == pause) {
                controller.pause();
                currentState = STATE.PAUSED;
            }
            else if(e.getSource() == stop) {
                clearDisplay();
                currentState = STATE.INITIATED;
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
//fixme------------------------------------------------------------------------------------------------------------------------------------------

            else if(e.getSource() == changeDirection) {

                String dirText = "";
                Controller.DIRECTION dir = controller.getDirection();
                if(dir == Controller.DIRECTION.UP || dir == Controller.DIRECTION.DOWN) {
                    colorDisplay.setNew7x7Size(1, verticalPages);
                    setSize(new Dimension(verticalPages * 200, 200));
                    colorDisplay.clearDisplay();
                    controller.clearAll();
                    controller.refreshMainPanel();
                    controller.loadFlowText(flowText);
                    controller.flowText();
                }
                switch (dirLeftRight) {
                    case LEFT:
                        controller.setDirection(Controller.DIRECTION.RIGHT);
                        dirLeftRight = Controller.DIRECTION.RIGHT;
                        dirText = "LEFT";
                        break;
                    case RIGHT:
                        controller.setDirection(Controller.DIRECTION.LEFT);
                        dirLeftRight = Controller.DIRECTION.LEFT;
                        dirText = "RIGHT";
                        break;
                }
                changeDirection.setText("ChangeDirection: "+dirText);
            }
            else if(e.getSource() == changeDirectionUpDown) {
                String dirText = "";
                Controller.DIRECTION dir = controller.getDirection();
                if(dir == Controller.DIRECTION.LEFT || dir == Controller.DIRECTION.RIGHT) {
                    colorDisplay.setNew7x7Size(verticalPages, 1);
                    setSize(new Dimension(200, verticalPages * 200));
                    colorDisplay.clearDisplay();
                    controller.clearAll();
                    controller.refreshMainPanel();
                    //controller.loadFlowText(flowText);
                    controller.pause();
                    currentState = STATE.INITIATED;
                    setButtonsActive();

                }
                switch (dirUpDown) {
                    case UP:
                        controller.setDirection(Controller.DIRECTION.DOWN);
                        dirUpDown = Controller.DIRECTION.DOWN;
                        dirText = "UP";
                        break;
                    case DOWN:
                        controller.setDirection(Controller.DIRECTION.UP);
                        dirUpDown = Controller.DIRECTION.UP;
                        dirText = "DOWN";
                        break;
                }
                //controller.flowText();



                changeDirectionUpDown.setText("ChangeDirection: " + dirText);
            }
            setButtonsActive();
        }
    }
}
