package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *	En klass som tar imot info från en controller och skriver ut vilka nummer vi har
 * så att vi kan testa funktionen av hur det ska se ut.
 * @author Jonatan Fridsten
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
		//kommer att skapa ett label objekt med tillhörande funktion och lägga i en lista
		for (int i = 0; i < array2D.length; i++) {
			for (int j = 0; j < array2D[i].length; j++) {
				array2D[i][j] = new JLabel("");
				array2D[i][j].setBackground(Color.CYAN);
				array2D[i][j].setOpaque(true);
				centerPanel.add(array2D[i][j]);
			}
		}

		westPanel.setLayout(new GridLayout(7, 0, 5, 0));
		southPanel.setLayout(new GridLayout(0, 7, 0, 2));
		westPanel.setSize(60, 600);
		//skapar två listor som kommer att inehålla textFields
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


	/**
	 * Metoden kommer att ge den lokala controllern ett värde
	 * @param ctrl inkommande kontroller
     */
	@Override
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
		ctrl.showRandom();
	}

	/**
	 * Metoden kommer att uppdatera viewn
	 * @param all nya värdena av viewn
     */
	@Override
	public void updateView(int[][] all) {
		for (int i = 0; i < all.length; i++) {
			for (int j = 0; j < all[i].length; j++) {
				array2D[i][j].setText(String.valueOf(all[i][j]));
			}
		}


	}



	/**
	 * Används ej i denna klassen
	 * @return 0
	 * @deprecated
     */
	@Override
	public int getHorizontalPages() {
//		return colorDisplay.getHorizontalPages();
		return 0;
	}

	/**
	 * Används ej i denna klassen
	 * @return 0
	 * @deprecated
     */
	@Override
	public int getVerticalPages() {
//		return colorDisplay.getVerticalPages();
		return 0;
	}

	/**
	 * Används ej i denna klassen
	 * @param all -
	 * @param dir -
	 * @deprecated
     */
	@Override
	public void updateView(ArrayList<int[][]> all, Controller.DIRECTION dir) {

	}

	/**
	 * Uppdaterar från matris till text i Columb listan
	 *
	 * @param arr Det nya värdena
	 */
	public void setColumbText(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			txtInputWest[i].setText(String.valueOf(arr[i]));
		}
	}

	/**
	 * Uppdaterar från matris till text i Row listan
	 *
	 * @param arr De nya värdena
	 */
	public void setRowText(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			txtInputSouth[i].setText(String.valueOf(arr[i]));
		}
	}

	/**
	 * Returnerar vad det står i den södra radeninputen
	 * @param old den gamla vektorn
	 * @return lista med värdena på södra radinputen
	 */
	public int[] getTextRow(int[] old) {
		int[] arr = new int[7];
		for (int i = 0; i < txtInputSouth.length; i++) {
			try{
				arr[i] = Integer.parseInt(txtInputSouth[i].getText());
			}catch (Exception e){
				JOptionPane.showMessageDialog(null,"Felaktigt värde på Rad:" + String.valueOf(i+1));
				arr[i] = old[i];
			}

		}
		return arr;
	}

	/**
	 * Retunerar en array med columb input till vänster
	 * @param old den gamla vektorn
	 * @return lista med värdena på den västra inputen
	 */
	public int[] getTextCol(int[] old){
		int[] arr = new int[7];
		for (int i = 0; i < txtInputWest.length; i++) {
			//kontrollerar om det är en int annars sparar den det gamla värdet
			try {
				arr[i] = Integer.parseInt(txtInputWest[i].getText());
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,"Felaktigt värde på Columb: " + String.valueOf(i+1));
				arr[i] = old[i];
			}
		}
		return arr;
	}
	/**
	 * Sätter ett värde på en textfield, värdet får vi från
	 * en matrisen
	 * @param value värdet som ska sättas
	 */
	public void setElementText(int value){
		txtElement.setText(String.valueOf(value));
	}
	/**
	 * Returnerar elemnetet i textfieldet
	 * @param old det gamla värdet
	 * @return elementets värde
	 */
	public int getElement(int old){
		int value = 0;
		//Kontrollerar så att det är en int
		try{
			value = Integer.parseInt(String.valueOf(txtElement.getText()));
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Felaktigt värde, Mata in en Int");
			value = old;
		}

		return value;
	}

	/**
	 * Inre klass för att det ska hända något när man trycker på
	 * knapparna
	 * @author Jonatan Fridsten
	 */
	private class ViewNumberListener implements ActionListener {
		/**
		 * Metoden tar imot en string som ska vara huvudtext och sedan kommer den
		 * att returnera ett tal som användaren matar in
		 * @param res Medelande till användare
         * @return Ett int value som användaren matar in
         */
		public int getChoice(String res){
			int choice = 0;
			boolean inputOk = false;
			//Kontrollerar så att det är av typen int!
			do{
				try{
					choice = Integer.parseInt(JOptionPane.showInputDialog(res));
					inputOk = true;
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Felaktigt värde!\nSka vara en int!");
				}
			}while(!inputOk);
			return choice;
		}

		/**
		 * Metoden kommer att kontrollera om man trycker på knapparna och
		 * sedan få något att hända i programmet
		 * @param e där vi får händelsen
         */
		public void actionPerformed(ActionEvent e) {
			//Om man trycker på LäsCol
			if (e.getSource() == btnColRead) {
				setColumbText(ctrl.getCol(getChoice("Välj Col")));
			}
			//Om man trycker på Skriv Col
			if (e.getSource() == btnColWrite) {
				int choice = getChoice("Välj Col");
				ctrl.setCol(choice, getTextCol(ctrl.getCol(choice)));
			}
			//Om man trycker på Läs Rad
			if (e.getSource() == btnRowRead) {
				setRowText(ctrl.getRow(getChoice("Välj Rad")));
			}
			//Om man trycker på Skriv Rad
			if (e.getSource() == btnRowWrite) {
				int choice = getChoice("Välj Rad");
				ctrl.setRow(choice,getTextRow(ctrl.getRow(choice)));
			}
			//Om man trycker på Skriv Element
			if(e.getSource() == btnElmWrite){
				int choiceRow = getChoice("Välj Rad");
				int choiceCol = getChoice("Välj Col");
				ctrl.setElement(choiceRow,choiceCol,getElement(ctrl.getElement(choiceRow,choiceRow)));
			}
			//Om man trycker på Läs Element
			if(e.getSource() == btnElmRead){
				setElementText(ctrl.getElement(getChoice("Välj Rad"),getChoice("Välj Col")));
			}

		}

	}

}
