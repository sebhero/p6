package mah.se.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.controller.Controller;
import mah.se.mvc.controller.Controller.DIRECTION;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

/**
 * Klass skapad för att kunna Shifta Höger och Vänster.
 * 
 * @author Anton
 * 
 */

public class Räserbajs2k16 extends JPanel implements ViewImpl {

	private Controller ctrl;
	private ShiftArray shiftar;
	private Array7 overflow;
	private JPanel eastPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JLabel[][] arrd2d = new JLabel[7][7];
	private JTextField[] westInput = new JTextField[7];
	private JTextField[] eastInput = new JTextField[7];
	private JButton btnRandomInput = new JButton("Random Input");
	private JButton btnShiftLeft = new JButton("ShiftLeft");
	private JButton btnShiftRight = new JButton("ShitRight");
	Font font = new Font("Serif", Font.BOLD, 20);

	/**
	 * Konstruktor som skapar ett fönster med 2st input kolumner vardera 7
	 * JTextField. I mitten har vi en 7x7 JLabelArray som ska representera ett
	 * array7x7 objekt. Detta fylls sedan med slumpsiffror. Sedan har vi 3st
	 * knappar ShiftLeft, ShiftRight och RandomInput.
	 */

	public Räserbajs2k16() {
		shiftar = new ShiftArray();
		ButtonListener listener = new ButtonListener();
		setPreferredSize(new Dimension(550, 500));
		setLayout(new BorderLayout());

		centerPanel.setPreferredSize(new Dimension(350, 300));
		centerPanel.setLayout(new GridLayout(7, 7, 2, 2));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centerPanel.setBackground(Color.WHITE);

		for (int i = 0; i < arrd2d.length; i++) {
			for (int j = 0; j < arrd2d[i].length; j++) {
				arrd2d[i][j] = new JLabel("");
				arrd2d[i][j].setFont(font);
				arrd2d[i][j].setBackground(Color.WHITE);
				arrd2d[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				arrd2d[i][j].setVerticalAlignment(SwingConstants.CENTER);
				centerPanel.add(arrd2d[i][j]);

			}
		}

		westPanel.setLayout(new GridLayout(7, 0, 5, 0));
		eastPanel.setLayout(new GridLayout(7, 0, 5, 0));
		westPanel.setPreferredSize(new Dimension(60, 600));
		eastPanel.setPreferredSize(new Dimension(60, 600));
		westPanel.setBackground(Color.GREEN);
		eastPanel.setBackground(Color.GREEN);

		for (int i = 0; i < westInput.length; i++) {
			westInput[i] = new JTextField("");
			eastInput[i] = new JTextField("");
			eastInput[i].setFont(font);
			westInput[i].setFont(font);
			eastInput[i].setColumns(5);
			westInput[i].setColumns(5);
			westPanel.add(westInput[i]);
			eastPanel.add(eastInput[i]);

		}
		southPanel.setLayout(new GridLayout(1, 0, 3, 0));
		southPanel.add(btnShiftLeft);
		southPanel.add(btnRandomInput);
		southPanel.add(btnShiftRight);

		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);

		btnShiftLeft.addActionListener(listener);
		btnShiftRight.addActionListener(listener);
		btnRandomInput.addActionListener(listener);

		setRandomInput();
	}

	/**
	 * Fyller våra inputfält med slumpade siffror(0-9)
	 */
	public void setRandomInput() {
		Random rand = new Random();
		for (int i = 0; i < westInput.length; i++) {
			westInput[i].setText(String.valueOf(rand.nextInt(10)));
			eastInput[i].setText(String.valueOf(rand.nextInt(10)));
		}
	}

	/**
	 * Sätter controllern och fyller JLabelarrayen med slumpade siffror.
	 * 
	 * @param ctrl
	 *            controllern
	 */
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
		ctrl.showRandom();
	}

	@Override
	/** Används för uppdatera vyn (text på JLabel arrayen)
	 * @param all array7x7 objekt - jlabel i detta fallet
	 */
	public void updateView(int[][] all) {
		for (int i = 0; i < all.length; i++) {
			for (int j = 0; j < all[i].length; j++) {
				arrd2d[i][j].setText(Integer.toString(all[i][j]));
			}
		}

	}

	/**
	 * Används ej i denna klassen
	 */
	@Override
	public int getHorizontalPages() {
		// return colorDisplay.getHorizontalPages();
		return 0;
	}

	/**
	 * Används ej i denna klassen
	 */
	@Override
	public int getVerticalPages() {
		// return colorDisplay.getVerticalPages();
		return 0;
	}

	/**
	 * Används ej i denna klassen
	 */
	@Override
	public void updateView(ArrayList<int[][]> all, DIRECTION dir) {

	}

	/**
	 * Sätter text på JLabel Array med hjälp av Array7x7 objekt
	 * 
	 * @param arr
	 *            Array7x7 skickas in och sätts på JLabel Array
	 */

	public Array7x7 getText() {
		Array7x7 arr = new Array7x7();
		for (int i = 0; i < arrd2d.length; i++) {
			for (int j = 0; j < arrd2d[i].length; j++) {
				arr.setElement(i, j, Integer.parseInt(arrd2d[i][j].getText()));
			}
		}
		return arr;
	}

	public void setText(Array7x7 arr) {
		for (int i = 0; i < arrd2d.length; i++) {
			for (int j = 0; j < arrd2d[i].length; j++) {
				arrd2d[i][j].setText(String.valueOf(arr.getElement(i, j)));
			}
		}
	}

	public void setWest(Array7 arr) {
		for (int i = 0; i < arr.getLength(); i++) {
			westInput[i].setText(String.valueOf(arr.getElement(i)));
		}

	}

	public Array7 getWest() {
		Array7 arr = new Array7();
		for (int i = 0; i < arr.getLength(); i++) {
			arr.setElement(i, Integer.parseInt(westInput[i].getText()));
		}
		return arr;
	}

	public void setEast(Array7 arr) {
		for (int i = 0; i < arr.getLength(); i++) {
			eastInput[i].setText(String.valueOf(arr.getElement(i)));
		}

	}

	public Array7 getEast() {
		Array7 arr = new Array7();
		for (int i = 0; i < arr.getLength(); i++) {
			arr.setElement(i, Integer.parseInt(eastInput[i].getText()));
		}
		return arr;
	}

	/**
	 * Lyssnarklass för ShiftRight - ShiftLeft - RandomInputknapparna
	 * 
	 * @author Anton
	 *
	 */

	public void shiftLeft() {
		ctrl.setDirection(DIRECTION.LEFT);
		Array7x7 arr = new Array7x7();
		arr = getText();
		setWest(shiftar.shiftLeft(arr, getEast()));
		setText(arr);
		Array7 arr2 = new Array7();
		setEast(arr2);
	}

	public void shiftRight() {
		ctrl.setDirection(DIRECTION.RIGHT);
		Array7x7 arr = new Array7x7();
		arr = getText();
		setEast(shiftar.shiftRight(arr, getWest()));
		setText(arr);
		Array7 arr2 = new Array7();
		setWest(arr2);
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnShiftLeft) {
				shiftLeft();

			}
			if (e.getSource() == btnShiftRight) {
				shiftRight();

			}
			if (e.getSource() == btnRandomInput) {
				setRandomInput();
			}
		}
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ViewImpl view = new Räserbajs2k16();
		frame.pack();
		frame.setVisible(true);
		Array7x7 model = new Array7x7();
		new Controller(model, view);
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add((Component) view);
		frame.pack();
		frame.setVisible(true);
	}

}
