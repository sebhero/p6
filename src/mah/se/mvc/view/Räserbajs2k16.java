package mah.se.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.controller.Controller.DIRECTION;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

/**
 * 
 * @author Anton Klass skapad för att kunna Shifta Höger och Vänster.
 * 
 */

public class Räserbajs2k16 extends JPanel implements ViewImpl {

	private Controller ctrl;
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

	public Räserbajs2k16() {
		ButtonListener listener = new ButtonListener();
		setPreferredSize(new Dimension(550, 500));
		setLayout(new BorderLayout());
		


		centerPanel.setPreferredSize(new Dimension(350, 300));
		centerPanel.setLayout(new GridLayout(7, 7, 2, 2));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		centerPanel.setBackground(Color.BLUE);

		for (int i = 0; i < arrd2d.length; i++) {
			for (int j = 0; j < arrd2d[i].length; j++) {
				arrd2d[i][j] = new JLabel("");
				arrd2d[i][j].setBackground(Color.BLUE);
				arrd2d[i][j].setOpaque(true);
				centerPanel.add(arrd2d[i][j]);
			}
		}

		westPanel.setLayout(new GridLayout(7, 0, 5, 0));
		eastPanel.setLayout(new GridLayout(7, 0, 5, 0));
		westPanel.setSize(60, 600);
		eastPanel.setSize(60, 600);

		for (int i = 0; i < westInput.length; i++) {
			westInput[i] = new JTextField();
			eastInput[i] = new JTextField();
			westInput[i].setColumns(5);
			eastInput[i].setColumns(5);
			westPanel.add(westInput[i]);
			eastPanel.add(eastInput[i]);
			westInput[i].setBackground(Color.GREEN);
			eastInput[i].setBackground(Color.GREEN);

		}
		southPanel.setLayout(new GridLayout(1, 0, 3, 0));
		btnShiftLeft.setSize(100,100);
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
	}

	public void setRandomInput() {
		Random rand = new Random();
		for (int i = 0; i < westInput.length; i++) {
			westInput[i].setText(String.valueOf(rand.nextInt(10)));
			eastInput[i].setText(String.valueOf(rand.nextInt(10)));
		}
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
		ctrl.showRandom();
	}

	@Override
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

	public Array7x7 getText() {
		Array7x7 arr = new Array7x7();
		for (int i = 0; i < arrd2d.length; i++) {
			for (int j = 0; j < arrd2d[i].length; j++) {
				arr.setElement(i, j, Integer.parseInt(arrd2d[i][j].getText()));
			}
		}
		return arr;
	}

	/**
	 * Sätter text på JLabel Array med hjälp av Array7x7 objekt
	 * 
	 * @param arr
	 *            - Arra7x7 skickas in och sätts på JLabel Array
	 */

	public void setText(Array7x7 arr) {
		for (int i = 0; i < arrd2d.length; i++) {
			for (int j = 0; j < arrd2d[i].length; j++) {
				arrd2d[i][j].setText(String.valueOf(arr.getElement(i, j)));
			}
		}
	}

	/**
	 * Gör om JTextField input till Int och sätter in i Array7 objekt.
	 * Felhantering finns om man försöker mata in annat än int eller om man inte
	 * matar in något överhuvudtaget
	 * 
	 * @return Array 7 arr
	 * 
	 */
	public Array7 getInputWest() {
		Array7 arr = new Array7();
		String error = "Fel på ruta: ";
		boolean hasError = false;
		for (int i = 0; i < arr.getLength(); i++) {
			try {
				arr.setElement(i, Integer.parseInt(westInput[i].getText()));

			} catch (Exception e) {
				// TODO: handle exception
				error += String.valueOf(i + 1) + " ";
				hasError = true;
			}
		}
		if (hasError) {
			JOptionPane.showMessageDialog(null, error);
			arr = null;
		}
		return arr;
	}

	/**
	 * Gör om JTextField input till Int och sätter in i Array7 objekt.
	 * Felhantering finns om man försöker mata in annat än int eller om man inte
	 * matar in något överhuvudtaget
	 * 
	 * @return Array 7 arr
	 */
	public Array7 getInputEast() {
		Array7 arr = new Array7();
		String error = "Fel på ruta: ";
		boolean hasError = false;
		for (int i = 0; i < arr.getLength(); i++) {
			try {
				arr.setElement(i, Integer.parseInt(eastInput[i].getText()));
			} catch (Exception e) {
				error += String.valueOf(i + 1) + " ";
				hasError = true;
			}
		}
		if (hasError) {
			JOptionPane.showMessageDialog(null, error);
			arr = null;
		}
		return arr;
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnShiftLeft) {
				ctrl.setDirection(DIRECTION.LEFT);
				Array7 result = getInputEast();
				if (result != null) {
					ctrl.showShift(getInputEast());
				}

			}
			if (e.getSource() == btnShiftRight) {

				ctrl.setDirection(DIRECTION.RIGHT);
				Array7 result = getInputWest();
				if (result != null) {
					ctrl.showShift(result);
				}

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
