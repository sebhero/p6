package mah.se.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;

public class ViewNumbers extends JPanel implements viewImpl{
	private Controller ctrl;
	private JPanel eastPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JLabel[][] array2D = new JLabel[7][7];
	private JTextField[] arrayInputWest = new JTextField[7];
	private JTextField[] arrayInputSouth = new JTextField[7];
	private JButton btnColRead = new JButton("L�s Col");
	private JButton btnColWrite = new JButton("Skriv Col");
	private JButton btnInputColNbr = new JButton("Input col nr");
	private JButton btnRowRead = new JButton("L�s Rad");
	private JButton btnRowWrite = new JButton("Skriv Rad");
	private JButton btnInputRowNbr = new JButton("Input Rad nr");
	
	public ViewNumbers(Array7x7 array) {
		this.setPreferredSize(new Dimension(500, 550));
		this.setLayout(new BorderLayout());
		ViewNumberListener listener = new ViewNumberListener();

		
		centerPanel.setPreferredSize(new Dimension(400, 400));
		centerPanel.setLayout(new GridLayout(7, 7, 2, 2));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				array2D[i][j] = new JLabel("");
				array2D[i][j].setBackground(Color.BLUE);
				array2D[i][j].setText("" + array.getElement(i, j));
				array2D[i][j].setOpaque(true);
				centerPanel.add(array2D[i][j]);
			}
		}

		westPanel.setLayout(new GridLayout(7, 0, 5, 0));
		southPanel.setLayout(new GridLayout(0, 7, 0, 2));
		westPanel.setSize(60, 600);

		for (int i = 0; i < arrayInputWest.length; i++) {
			arrayInputWest[i] = new JTextField();
			arrayInputSouth[i] = new JTextField();
			arrayInputWest[i].setColumns(5);
			arrayInputSouth[i].setColumns(5);
			southPanel.add(arrayInputSouth[i]);
			westPanel.add(arrayInputWest[i]);
		}

		btnColRead.addActionListener(listener);
		btnColWrite.addActionListener(listener);
		btnInputColNbr.addActionListener(listener);
		btnInputRowNbr.addActionListener(listener);
		btnRowRead.addActionListener(listener);
		btnRowWrite.addActionListener(listener);

		eastPanel.setLayout(new GridLayout(6, 0, 2, 0));
		eastPanel.add(btnRowRead);
		eastPanel.add(btnRowWrite);
		eastPanel.add(btnInputRowNbr);
		eastPanel.add(btnColRead);
		eastPanel.add(btnColWrite);
		eastPanel.add(btnInputColNbr);

		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
	}

	// TODO Kontrollera om det beh�vs mer metoder?
	/**
	 * Metoden kommer att m�la om bilden till ett nytt v�rde
	 * 
	 * @param arr
	 */
	public void rePaint(Array7x7 arr) {

		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				array2D[i][j].setText("" + arr.getElement(i, j));
			}
		}
	}

	// TODO ta bort main
	public static void main(String[] args) {
		Array7x7 arr = new Array7x7();
		ViewNumbers vn = new ViewNumbers(arr);
		JOptionPane.showMessageDialog(null, vn);
	}

	/**
	 * Inre klass f�r att det ska h�nda n�got n�r man trycker p� knapparna
	 * 
	 * @author jonatan
	 *
	 */
	private class ViewNumberListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnColRead) {

			}
			if (e.getSource() == btnColWrite) {

			}
			if (e.getSource() == btnInputColNbr) {

			}
			if (e.getSource() == btnInputRowNbr) {

			}
			if (e.getSource() == btnRowRead) {

			}
			if (e.getSource() == btnRowWrite) {

			}
		}

	}
}