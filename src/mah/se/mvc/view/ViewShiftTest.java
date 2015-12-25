package mah.se.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mah.se.mvc.controller.Controller;

public class ViewShiftTest extends JPanel implements ViewImpl {
	
	private Controller ctrl;
	private JPanel eastPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel westPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JLabel[][] arrd2d = new JLabel[7][7];
	private JTextField[] westInput = new JTextField[7];
	private JTextField[] eastInput = new JTextField[7];
	private JButton btnShiftLeft = new JButton("ShiftLeft");
	private JButton btnShiftRight = new JButton("ShitRight");
	
	public ViewShiftTest() {
		setPreferredSize(new Dimension(550,500));
		setLayout(new BorderLayout());
		
		
		centerPanel.setPreferredSize(new Dimension(350,300));
		centerPanel.setLayout(new GridLayout(7,7,2,2));
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		for(int i=0; i<arrd2d.length; i++) {
			for(int j=0; j<arrd2d[i].length; j++) {
			arrd2d[i][j] = new JLabel("");
			arrd2d[i][j].setBackground(Color.WHITE);
			arrd2d[i][j].setOpaque(true);
			centerPanel.add(arrd2d[i][j]);
			}
		}
		
		westPanel.setLayout(new GridLayout(7,0,5,0));
		eastPanel.setLayout(new GridLayout(7,0,5,0));
		westPanel.setSize(60,600);
		eastPanel.setSize(60,600);
		
		for(int i=0; i<westInput.length; i++) {
			westInput[i] = new JTextField();
			eastInput[i] = new JTextField();
			westInput[i].setColumns(5);
			eastInput[i].setColumns(5);
			westPanel.add(westInput[i]);
			eastPanel.add(eastInput[i]);
			
		}
		
		southPanel.setLayout(new GridLayout(2,0,2,0));
		southPanel.add(btnShiftLeft);
		southPanel.add(btnShiftRight);
		
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(southPanel, BorderLayout.SOUTH);
		add(eastPanel, BorderLayout.EAST);
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
		
	}

	@Override
	public void updateView(int[][] all) {
		// TODO Auto-generated method stub
		
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	public static void main (String args[]) {
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ViewShiftTest());
		frame.pack();
		frame.setVisible(true);
	}

}
