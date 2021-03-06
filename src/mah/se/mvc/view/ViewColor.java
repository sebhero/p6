package mah.se.mvc.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.controller.Controller.DIRECTION;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import roffe.Color.Color;
import roffe.Color.ColorDisplay;

/**
 * 
 * @author Johnatan Sona
 * 
 *         Skapar ett Color fönster med UI.
 * 
 *         2016-01-03
 */
@SuppressWarnings("serial")
public class ViewColor extends JPanel implements ViewImpl {

	private int color = 0;
	private int tick1 = 0, tick2 = 0, tick3 = 0, tick4 = 0, tick5 = 0, tick6 = 0, tick7 = 0, tickBig = 0;
	private JPanel pnlDisplay;
	private JPanel pnlDirButtons;
	private JPanel pnlFillBtn;
	private JPanel pnlSideBtn;
	private ColorDisplay colorDisplay;
	private Controller ctrl;

	private JButton btnShiftRight = new JButton("Shift right");
	private JButton btnShiftLeft = new JButton("Shift left");
	private JButton btnShiftUp = new JButton("Shift up");
	private JButton btnShiftDown = new JButton("Shift down");
	private JButton btnFillRandColor = new JButton("Fyll med slumpfärger");
	private JButton btnFill = new JButton("Fyll med en färg");
	private JButton btnChoose1 = new JButton("Välj färg: Rad 1");
	private JButton btnChoose2 = new JButton("Välj färg: Rad 2");
	private JButton btnChoose3 = new JButton("Välj färg: Rad 3");
	private JButton btnChoose4 = new JButton("Välj färg: Rad 4");
	private JButton btnChoose5 = new JButton("Välj färg: Rad 5");
	private JButton btnChoose6 = new JButton("Välj färg: Rad 6");
	private JButton btnChoose7 = new JButton("Välj färg: Rad 7");
	
	/**
	 * Konstruktor som skapar 1 x 1 fönster med display och knappar
	 * @param background, bakgrundsfärgen
	 * @param grid, gridfärgen
	 */
	public ViewColor(int background, int grid) {
		this(1, 1, background, grid);
	}

	/**
	 * Konstruktor som skapar fönster med display och knappar
	 * Man kan välja antalet horisontella och vertikala sidor
	 * 
	 * @param verticalPages, antalet vertikala sidor
	 * @param horizontalPages, antalet horisontella sidor
	 * @param background, bakgrundsfärgen
	 * @param grid, gridfärgen
	 */
	public ViewColor(int verticalPages, int horizontalPages, int background, int grid) {
		colorDisplay = new ColorDisplay(verticalPages, horizontalPages, background, grid);
		pnlDisplay = new JPanel();
		pnlDirButtons = new JPanel();
		pnlSideBtn = new JPanel();
		pnlFillBtn = new JPanel();

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(horizontalPages * 500, verticalPages * 490));

		colorDisplay.setPreferredSize(new Dimension(horizontalPages * 315, verticalPages * 315));
		pnlSideBtn.add(btnChoose1);
		pnlSideBtn.add(btnChoose2);
		pnlSideBtn.add(btnChoose3);
		pnlSideBtn.add(btnChoose4);
		pnlSideBtn.add(btnChoose5);
		pnlSideBtn.add(btnChoose6);
		pnlSideBtn.add(btnChoose7);

		pnlDisplay.add(colorDisplay);
		pnlDirButtons.setPreferredSize(new Dimension(900, 40));
		pnlDirButtons.setLayout(new GridLayout(1, 4));
		pnlFillBtn.setPreferredSize(new Dimension(400, 40));
		pnlSideBtn.setPreferredSize(new Dimension(130, 315));
		pnlSideBtn.setLayout(new GridLayout(7, 1));
		pnlFillBtn.add(btnFillRandColor);
		pnlFillBtn.add(btnFill);
		pnlDirButtons.add(btnShiftUp);
		pnlDirButtons.add(btnShiftLeft);
		pnlDirButtons.add(btnShiftRight);
		pnlDirButtons.add(btnShiftDown);

		JPanel pnl = new JPanel(new GridLayout(2, 1));
		JPanel pnlside = new JPanel();
		pnlside.add(pnlSideBtn);
		pnl.add(pnlDirButtons);
		pnl.add(pnlFillBtn);
		add(pnl, BorderLayout.SOUTH);
		add(pnlDisplay, BorderLayout.CENTER);
		add(pnlside, BorderLayout.EAST);

		initButtons();
	}

	/**
	 * Fixar problem med ändra storlek på vyn.
	 *
	 * @param dimension, dimensionen
	 */
	@Override
	public void setSize(Dimension dimension) {
		double newSize = dimension.getWidth() / this.getWidth();
		// sätter storleken på coloroDispl 100 mindre för att den ska få plats
		int newSizeW = (int) (newSize * this.getWidth() - 100);
		int newSizeH = (int) (newSize * this.getHeight() - 100);
		super.setSize(dimension);
		colorDisplay.setPreferredSize(new Dimension(newSizeW, newSizeH));
		pnlSideBtn.setPreferredSize(new Dimension(pnlSideBtn.getWidth(), newSizeH));
		this.revalidate();
		this.repaint();
	}

	/**
	 * Resetar färgskiftningen
	 */
	public void resetTick() {
		tick1 = 0;
		tick2 = 0;
		tick3 = 0;
		tick4 = 0;
		tick5 = 0;
		tick6 = 0;
		tick7 = 0;
	}

	/**
	 * Skiftar mellan olika färger
	 * 
	 * Tar emot "tick" som håller kolla på räkningen av knapptryck.
	 * 
	 * @param tick, räkningen av tryck
	 */
	public void updateColor(int tick) {
		switch (tick) {
		case 0:
			this.color = Color.BLUE;
			break;
		case 1:
			this.color = Color.RED;
			break;
		case 2:
			this.color = Color.GREEN;
			break;
		case 3:
			this.color = Color.YELLOW;
			break;
		case 4:
			this.color = Color.CYAN;
			break;
		case 5:
			this.color = Color.MAGENTA;
			break;
		case 6:
			this.color = Color.WHITE;
			break;
		case 7:
			this.color = Color.LTGRAY;
			break;
		case 8:
			this.color = Color.GRAY;
			break;
		case 9:
			this.color = Color.DKGRAY;
			break;
		case 10:
			this.color = Color.BLACK;
			break;
		}
		
	}

	/**
	 * Initierar alla knappar
	 */
	private void initButtons() {
		btnChoose1.addActionListener(ae -> {
			updateColor(tick1);
			ctrl.setElement(0, 6, color);
			tick1++;
			if (tick1 > 10)
				tick1 = 0;
		});
		btnChoose2.addActionListener(ae -> {
			updateColor(tick2);
			ctrl.setElement(1, 6, color);
			tick2++;
			if (tick2 > 10)
				tick2 = 0;
		});
		btnChoose3.addActionListener(ae -> {
			updateColor(tick3);
			ctrl.setElement(2, 6, color);
			tick3++;
			if (tick3 > 10)
				tick3 = 0;
		});
		btnChoose4.addActionListener(ae -> {
			updateColor(tick4);
			ctrl.setElement(3, 6, color);
			tick4++;
			if (tick4 > 10)
				tick4 = 0;
		});
		btnChoose5.addActionListener(ae -> {
			updateColor(tick5);
			ctrl.setElement(4, 6, color);
			tick5++;
			if (tick5 > 10)
				tick5 = 0;
		});
		btnChoose6.addActionListener(ae -> {
			updateColor(tick6);
			ctrl.setElement(5, 6, color);
			tick6++;
			if (tick6 > 10)
				tick6 = 0;
		});
		btnChoose7.addActionListener(ae -> {
			updateColor(tick7);
			ctrl.setElement(6, 6, color);
			tick7++;
			if (tick7 > 10)
				tick7 = 0;
		});

		btnShiftUp.addActionListener(ae -> {
			ctrl.setDirection(Controller.DIRECTION.UP);
			Array7 arr = new Array7();
			ctrl.showShift(arr);
		});
		btnShiftLeft.addActionListener(ae -> {
			resetTick();
			ctrl.setDirection(Controller.DIRECTION.LEFT);
			Array7 arr = new Array7();
			ctrl.showShift(arr);
		});

		btnShiftRight.addActionListener(ae -> {
			resetTick();
			ctrl.setDirection(Controller.DIRECTION.RIGHT);
			Array7 arr = new Array7();
			ctrl.showShift(arr);
		});
		btnShiftDown.addActionListener(ae -> {
			ctrl.setDirection(Controller.DIRECTION.DOWN);
			Array7 arr = new Array7();
			ctrl.showShift(arr);
		});

		btnFillRandColor.addActionListener(ae -> { // Fyller fönstret med
													// slumpade färger
			ctrl.showRandomColor();
			resetTick();
		});
		btnFill.addActionListener(ae -> { // Fyller fönstret med en färg som kan
											// skiftas
			resetTick();
			updateColor(tickBig);
			ctrl.showSameColor(color);

			tickBig++;
			if (tickBig > 10)
				tickBig = 0;
		});
	}
	
	/**
	 *Sätter Controller till variabeln ctrl
	 *@param ctrl, Controller objekt
	 */
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}
	/**
	 * Updaterar displayen
	 * @param matrix, 7x7 array som ska updateras
	 */
	@Override
	public void updateView(int[][] matrix) {
		colorDisplay.setDisplay(matrix);
		colorDisplay.updateDisplay();
	}
	/**
	 * Retunerar antalet horisontella sidor
	 * @return Retunerar antalet horisontella sidor
	 */
	@Override
	public int getHorizontalPages() {
		return colorDisplay.getHorizontalPages();
	}
	/**
	 * Retunerar antalet vertikala sidor
	 * @return Retunerar antalet vertikala sidor
	 */
	@Override
	public int getVerticalPages() {
		return colorDisplay.getVerticalPages();
	}
	/**
	 * Används ej
	 * @deprecated använd inte. är inte implementerad
	 */
	@Override
	public void updateView(ArrayList<int[][]> all, DIRECTION dir) {
	}

}
