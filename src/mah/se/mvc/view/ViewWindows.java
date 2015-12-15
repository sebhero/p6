package mah.se.mvc.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mah.se.mvc.model.Array7x7;

/**
 * 
 * @author Jonatan Fridsten
 *
 */
public class ViewWindows extends JPanel {
	private JLabel[][] lblArray = new JLabel[7][7];

	public ViewWindows() {
		this.setPreferredSize(new Dimension(300, 300));
		this.setLayout(new GridLayout(7, 7, 5, 5));

		for (int i = 0; i < lblArray.length; i++) {
			for (int j = 0; j < lblArray[i].length; j++) {
				lblArray[i][j] = new JLabel("");
				lblArray[i][j].setBackground(new java.awt.Color(0, 0, 0));
				lblArray[i][j].setOpaque(true);
				add(lblArray[i][j]);
			}
		}
	}

	public void setColor(Array7x7 array) {
		for (int i = 0; i < lblArray.length; i++) {
			for (int j = 0; j < lblArray[i].length; j++) {
				if(array.getElement(i, j) == 1){
					lblArray[i][j].setBackground(new java.awt.Color(0, 0, 0));
				}else{
					lblArray[i][j].setBackground(new java.awt.Color(255, 255, 255));
				}
			}
		}
	}

	// test för att få ut matrisen
	public static void main(String[] args) {
		ViewWindows pnl = new ViewWindows();
		JOptionPane.showMessageDialog(null, pnl);
	}
}
