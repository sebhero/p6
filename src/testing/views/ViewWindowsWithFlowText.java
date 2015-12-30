package testing.views;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewImpl;
import roffe.Color.ColorDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Sebastian Börebäck
 * Vyn visar i swing/windows en representation av modelen
 * manipuleras modeln via kontrollern och dess hjälpklasser
 */
public class ViewWindowsWithFlowText extends JPanel implements ViewImpl {
    //testing
    private final JPanel pnlButtonsExtra;

    private JPanel pnlDisplay;
	private JPanel pnlButtons;
	private ColorDisplay colorDisplay;
	private Controller ctrl;
	//Buttons that controll the direction of the shift
	private JButton btnFlowRight  = new JButton("shift right");
	private JButton btnFlowLeft = new JButton("Shift let");
	private JButton btnFlowUp = new JButton("Shift upp");
	private JButton btnFlowDown = new JButton("Shift ner");
	
	private JButton btnChar = new JButton("Lägg till char");
	private JButton btnLoadString = new JButton("load string");

    private JButton btnFillColor = new JButton("Fyll med slumpfärger");
    private JButton btnFlowText = new JButton("Rinnande Text");

	private JTextField txtInput = new JTextField();

	public ViewWindowsWithFlowText(int background, int grid) {
		this(1,1, background, grid);
	}

	public ViewWindowsWithFlowText(int verticalPages, int horizontalPages, int background, int grid) {
        colorDisplay = new ColorDisplay(verticalPages, horizontalPages, background, grid);
		pnlDisplay = new JPanel();
		pnlButtons = new JPanel();

		pnlButtonsExtra = new JPanel();
        pnlButtonsExtra.setLayout(new GridLayout(1, 4));
        pnlButtonsExtra.add(btnFillColor);
        pnlButtonsExtra.add(btnFlowText);

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(horizontalPages*300, verticalPages*300));

		colorDisplay.setPreferredSize(new Dimension(horizontalPages*200, verticalPages*200));
//		pnlDisplay.setPreferredSize(new Dimension(400, 400));
		pnlDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		pnlDisplay.add(colorDisplay);
		
		pnlButtons.setPreferredSize(new Dimension(900, 40));
		pnlButtons.setLayout(new GridLayout(1, 4));
		
		pnlButtons.add(txtInput);
		pnlButtons.add(btnLoadString);
		pnlButtons.add(btnFlowUp);
		pnlButtons.add(btnFlowLeft);
		pnlButtons.add(btnFlowRight);
		pnlButtons.add(btnFlowDown);
		
		//add(pnlButtons, BorderLayout.SOUTH);
        JPanel pnl = new JPanel(new GridLayout(2, 1));
        pnl.add(pnlButtons);
        pnl.add(pnlButtonsExtra);

        add(pnl, BorderLayout.SOUTH);

		add(pnlDisplay, BorderLayout.CENTER);

        initButtons();
	}

    /**
     * Init alla knappar
     */
    private void initButtons() {

	    btnLoadString.addActionListener(ae ->{
		    String txt = "hej";
		    ctrl.setDirection(Controller.DIRECTION.UP);
		    ctrl.loadFlowText(txt);
	    });

	    btnFlowUp.addActionListener(ae -> {
		    //shifts everything to the left
		    ctrl.setDirection(Controller.DIRECTION.UP);
		    ctrl.loadFlowText(txtInput.getText());
//		    ctrl.flowText(txtInput.getText());
		    ctrl.flowText();
	    });

	    btnFlowLeft.addActionListener(ae -> {
            ctrl.setDirection(Controller.DIRECTION.LEFT);
		    ctrl.loadFlowText(txtInput.getText());
		    ctrl.flowText();

//            ctrl.flowText(txtInput.getText());
        });


        btnFlowRight.addActionListener(ae -> {
            //shifts everything up
            ctrl.setDirection(Controller.DIRECTION.RIGHT);
	        ctrl.loadFlowText(txtInput.getText());
	        ctrl.flowText();
        });
        btnFlowDown.addActionListener(ae -> {
            //shifts everything downwards
            ctrl.setDirection(Controller.DIRECTION.DOWN);
	        ctrl.loadFlowText(txtInput.getText());
	        ctrl.flowText();
        });

        btnChar.addActionListener(ae ->{
            //visar en slumpad bokstav
            ctrl.showRanomChar();
        });


        btnFillColor.addActionListener(ae -> {
            //Visa slumpfärger
            ctrl.showRandomColor();
        });

        btnFlowText.addActionListener(ae ->{
            //visa rinnande text
            String txt = txtInput.getText();
            //inget skrivet i text fältet visa ?
            if (txt.length() <= 0) {
                txt = "?";
            }
            ctrl.flowText(txt.toUpperCase());
        });


    }

    /**
	 * Uppdaterar view med en color matris
	 * 
	 * @param matrix
	 *            matrisen po alla farg element
	 */
	public void updateViewColor(int[][] matrix) {
        colorDisplay.setDisplay(matrix);
        colorDisplay.updateDisplay();

    }

	/**
	 * Uppdatera view med en siffer matris
	 * 
	 * @param matrix
	 *            matris med siffror
	 */
	public void updateView(int[][] matrix) {
        colorDisplay.setDisplay(matrix);
        colorDisplay.updateDisplay();
	}

	@Override
	public void updateBigView(Array7x7[] all) {

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
				case LEFT:
					colorDisplay.setDisplay(all.get(i),0,i);
					break;
				case UP:
				case DOWN:
					colorDisplay.setDisplay(all.get(i),i,0);
					break;
			}

		}
		colorDisplay.updateDisplay();
	}

	/**
	 * Satter controller till viewen som styr viewn. den skoter all
	 * kommunikation med array7x7. hanterar knapptryckningar m.m.
	 * 
	 * @param ctrl
	 *            sjalva controllern
	 */
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}

}
