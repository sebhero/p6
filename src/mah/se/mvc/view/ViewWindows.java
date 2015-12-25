package mah.se.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mah.se.mvc.controller.Controller;
import roffe.Color.ColorDisplay;

/**
 * 
 * @author Jonatan Fridsten
 *
 */
public class ViewWindows extends JPanel implements ViewImpl {
	private JPanel pnlDisplay;
	private JPanel pnlButtons;
	private ColorDisplay colorDisplay;
	private Controller ctrl;
	private JButton btnLeft = new JButton("Shift vänster");
	private JButton btnRigth = new JButton("flow text");
	private JButton btnChar = new JButton("Lägg till char");
	private JTextField txtInput = new JTextField();


	public ViewWindows(int background, int grid) {
		this(1, 1, background, grid);
	}
	
	public ViewWindows(int verticalPages, int horizontalPages, int background, int grid) {

		//colorDisplay = new ColorDisplay(verticalPages, horizontalPages, background, grid);
		colorDisplay = new ColorDisplay(background, grid);
		colorDisplay .setPreferredSize(new Dimension(300, 350));
		colorDisplay .setSize(100, 120);
		pnlDisplay = new JPanel();
		pnlButtons = new JPanel();

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 400));

		pnlDisplay.setPreferredSize(new Dimension(400, 300));
		pnlDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		pnlDisplay.add(colorDisplay);

		pnlButtons.setPreferredSize(new Dimension(400, 40));
		pnlButtons.setLayout(new GridLayout(0, 4));

		pnlButtons.add(txtInput);
		pnlButtons.add(btnChar);
		pnlButtons.add(btnLeft);
		pnlButtons.add(btnRigth);
		add(pnlButtons, BorderLayout.SOUTH);
		add(pnlDisplay, BorderLayout.CENTER);

		btnChar.addActionListener(ae -> {
			ctrl.showRanomChar();
		});

		btnLeft.addActionListener(ae -> {
			ctrl.setDirection(Controller.DIRECTION.LEFT);
			ctrl.shiftWithRedColor();
		});

		btnRigth.addActionListener(ae -> {
			ctrl.flowText("HEJ");
		});
	}

	/**
	 * Uppdaterar view med en color matris
	 * 
	 * @param matrix
	 *            matrisen po alla farg element
	 */
	public void updateViewColor(int[][] matrix) {


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
