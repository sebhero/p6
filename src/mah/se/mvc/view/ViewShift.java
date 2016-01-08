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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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

public class ViewShift extends JPanel implements ViewImpl {

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
	private JButton btnNewBoard = new JButton("New numbers");
	Font font = new Font("Serif", Font.BOLD, 20);

	/**
	 * Konstruktor som skapar ett fönster med 2st input kolumner vardera 7
	 * JTextField. I mitten har vi en 7x7 JLabelArray som ska representera ett
	 * array7x7 objekt. Detta fylls sedan med slumpsiffror. Sedan har vi 3st
	 * knappar ShiftLeft, ShiftRight och RandomInput.
	 */

	public ViewShift() {
		shiftar = new ShiftArray();
		ButtonListener listener = new ButtonListener();
		setPreferredSize(new Dimension(550, 600));
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
			eastInput[i].setBackground(Color.GREEN);
			westInput[i].setBackground(Color.GREEN);
			westPanel.add(westInput[i]);
			eastPanel.add(eastInput[i]);

		}
		
		southPanel.setPreferredSize(new Dimension(600,50));
		southPanel.setLayout(new GridLayout(1, 0, 4, 0));
		southPanel.add(btnShiftLeft);
		southPanel.add(btnRandomInput);
		southPanel.add(btnShiftRight);
		southPanel.add(btnNewBoard);
		
		
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);

		btnShiftLeft.addActionListener(listener);
		btnShiftRight.addActionListener(listener);
		btnRandomInput.addActionListener(listener);
		btnNewBoard.addActionListener(listener);

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
	 * Set metod för JTextField i den separata kolumnen till vänster, tar emot ett array7 objekt som sedan skrivs ut
	 * @param arr array7 objekt som skrivs ut i kolumnen
	 */

	public void setWest(Array7 arr) {
		for (int i = 0; i < arr.getLength(); i++) {
			westInput[i].setText(String.valueOf(arr.getElement(i)));
		}

	}
	/**
	 * Get metod för JTextField i den vänstra kolumnen, samlar alla siffror och sedan lägger i ett array7 objekt som returneras
	 * @return arr array7 objekt
	 */
	public Array7 getWest() {
		Array7 arr = new Array7();
		boolean correctInput = false;
		String error = "Fel på vänsterkolumn ruta: ";
		for (int i = 0; i < arr.getLength(); i++) {
			try{
				
			arr.setElement(i, Integer.parseInt(westInput[i].getText()));
			
			}catch(Exception e) {
				error += (i+1) + " ";
				correctInput = true;
			}
		}
		if(correctInput) {
			JOptionPane.showMessageDialog(null, error);
			return null;
		}
		return arr;
	}

	/**
	 * Samma funktion som setWest, fast för den separata högra kolumnen
	 * @param arr array7 objekt
	 */

	public void setEast(Array7 arr) {
		for (int i = 0; i < arr.getLength(); i++) {
			eastInput[i].setText(String.valueOf(arr.getElement(i)));
		}

	}

	/**
	 * Samma som getWest
	 * @return arr array7 objekt
	 */

	public Array7 getEast() {
		Array7 arr = new Array7();
		String error = "Fel på rad högerkolumn ruta ";
		boolean correctInput = false;
		for (int i = 0; i < arr.getLength(); i++) {
			try{
				
			arr.setElement(i, Integer.parseInt(eastInput[i].getText()));
			
			}catch(Exception e) {
				error += (i+1) + " ";
				correctInput = true;
			}
		}
		if(correctInput){
			JOptionPane.showMessageDialog(null, error);
			return null;
			
		}
		return arr;
	}

	/**
	 * Metod som shiftar vyn ett steg åt vänster
	 *
	 */
	public void shiftLeft() {
		ctrl.setDirection(DIRECTION.LEFT);
		Array7 returnarr = new Array7();
		if(getEast() != null) {
		returnarr = ctrl.shift(getEast());
		setWest(returnarr);
		setEast(new Array7());
		ctrl.updateView();
		}
	}

	public void shiftRight() {
		ctrl.setDirection(DIRECTION.RIGHT);
		Array7 returnarr = new Array7();
		if(getWest() != null) {
		returnarr = ctrl.shift(getWest());
		setEast(returnarr);
		setWest(new Array7());
		ctrl.updateView();
		}
	}

	/**
	 * Lyssnarklass för ShiftRight - ShiftLeft - RandomInputknapparna
	 *
	 * @author Anton
	 *
	 */
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
			
			if(e.getSource()== btnNewBoard) {
				ctrl.showRandom();
			}
		}
	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            ViewImpl view = new ViewShift();
            Array7x7 model = new Array7x7();
            new Controller(model, view);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add((Component) view);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
