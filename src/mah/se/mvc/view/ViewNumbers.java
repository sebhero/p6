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

/**
 * 
 * @author jonatan
 *
 */
public class ViewNumbers extends JPanel implements ViewImpl {

	private Controller ctrl;
	private JPanel eastPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JLabel[][] array2D = new JLabel[7][7];
	private JTextField[] txtInputWest = new JTextField[7];
	private JTextField[] txtInputSouth = new JTextField[7];
	private JTextField txtElement = new JTextField();
	private JButton btnColRead = new JButton("Läs Col");
	private JButton btnColWrite = new JButton("Skriv Col");
	private JButton btnElmWrite = new JButton("Skriv Element");
	private JButton btnRowRead = new JButton("Läs Rad");
	private JButton btnRowWrite = new JButton("Skriv Rad");
	private JButton btnElmRead = new JButton("Läs Element");

	public ViewNumbers() {
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
				array2D[i][j].setOpaque(true);
				centerPanel.add(array2D[i][j]);
			}
		}

		westPanel.setLayout(new GridLayout(7, 0, 5, 0));
		southPanel.setLayout(new GridLayout(0, 7, 0, 2));
		westPanel.setSize(60, 600);

		for (int i = 0; i < txtInputWest.length; i++) {
			txtInputWest[i] = new JTextField();
			txtInputSouth[i] = new JTextField();
			txtInputWest[i].setColumns(5);
			txtInputSouth[i].setColumns(5);
			southPanel.add(txtInputSouth[i]);
			westPanel.add(txtInputWest[i]);
		}

		btnColRead.addActionListener(listener);
		btnColWrite.addActionListener(listener);
		btnElmRead.addActionListener(listener);
		btnElmWrite.addActionListener(listener);
		btnRowRead.addActionListener(listener);
		btnRowWrite.addActionListener(listener);

		eastPanel.setLayout(new GridLayout(7, 0, 2, 0));
		eastPanel.add(btnRowRead);
		eastPanel.add(btnRowWrite);
		eastPanel.add(btnColRead);
		eastPanel.add(btnColWrite);
		eastPanel.add(btnElmRead);
		eastPanel.add(btnElmWrite);
		eastPanel.add(txtElement);

		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
	}

	// TODO Kontrollera om det beh�vs mer metoder?

	@Override
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public void updateView(int[][] all) {
		for (int i = 0; i < all.length; i++) {
			for (int j = 0; j < all[i].length; j++) {
				array2D[i][j].setText(String.valueOf(all[i][j]));
			}
		}


	}

	/**
	 * Uppdaterar från matris till text i Columb listan
	 *
	 * @param arr
	 */
	public void setColumbText(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			txtInputWest[i].setText(String.valueOf(arr[i]));
		}
	}

	/**
	 * Uppdaterar från matris till text i Row listan
	 *
	 * @param arr
	 */
	public void setRowText(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			txtInputSouth[i].setText(String.valueOf(arr[i]));
		}
	}

	/**
	 * Returnerar vad det står i den södra radeninputen
	 *
	 * @return
	 */
	public int[] getTextRow() {
		int[] arr = new int[7];
		for (int i = 0; i < txtInputSouth.length; i++) {
			arr[i] = Integer.parseInt(txtInputSouth[i].getText());
		}
		return arr;
	}

	/**
	 * Retunerar en array med columb input
	 *
	 * @return
	 */
	public int[] getTextCol() {
		int[] arr = new int[7];
		for (int i = 0; i < txtInputWest.length; i++) {
			arr[i] = Integer.parseInt(txtInputWest[i].getText());
		}
		return arr;
	}
	/**
	 *
	 */
	public void setElementText(int value){
		txtElement.setText(String.valueOf(value));
	}
	/**
	 *
	 */
	public int getElement(){
		int value = Integer.parseInt(txtElement.getText());
		return value;
	}

	/**
	 * Inre klass f�r att det ska h�nda n�got n�r man trycker p�
	 * knapparna
	 * 
	 * @author jonatan
	 *
	 */
	private class ViewNumberListener implements ActionListener {

		public int getChoice(String res){
			int choice = Integer.parseInt(JOptionPane.showInputDialog(res));
			return choice;
		}

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == btnColRead) {
				setColumbText(ctrl.getCol(getChoice("Välj Col")));
			}
			if (e.getSource() == btnColWrite) {
				ctrl.setCol(getChoice("Välj Col"), getTextCol());
			}
			if (e.getSource() == btnRowRead) {
				setRowText(ctrl.getRow(getChoice("Välj Rad")));
			}
			if (e.getSource() == btnRowWrite) {
				ctrl.setRow(getChoice("Välj Rad"),getTextRow());
			}
			if(e.getSource() == btnElmWrite){
				ctrl.setElement(getChoice("Välj Rad"),getChoice("Välj Col"),getElement());
			}
			if(e.getSource() == btnElmRead){
				setElementText(ctrl.getElement(getChoice("Välj Rad"),getChoice("Välj Col")));
			}

		}

	}

}
