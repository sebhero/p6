package mah.se.mvc.view;

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.controller.Controller;
import mah.se.mvc.controller.Controller.DIRECTION;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.patterns.strategy.FillNumbers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewShiftTest extends JPanel implements ViewImpl {
	
	private FillNumbers fill;
	private Controller ctrl;
	private ShiftArray shift;
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
		ButtonListener listener = new ButtonListener();
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

		
		btnShiftLeft.addActionListener(listener);
		btnShiftRight.addActionListener(listener);
	}

	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
		ctrl.showRandom();
		
	}

	@Override
	public void updateView(int[][] all) {
		for(int i=0; i<all.length; i++) {
			for(int j=0; j<all[i].length; j++) {
				arrd2d[i][j].setText(Integer.toString(all[i][j]));
			}
		}
		
	}



	@Override
	public int getHorizontalPages() {
//		return colorDisplay.getHorizontalPages();
		return 0;
	}

	@Override
	public int getVerticalPages() {
//		return colorDisplay.getVerticalPages();
		return 0;
	}

	@Override
	public void updateView(ArrayList<int[][]> all, DIRECTION dir) {

	}

	public Array7x7 getText() {
		Array7x7 arr = new Array7x7();
		for(int i=0; i<arrd2d.length; i++) {
			for(int j=0; j<arrd2d[i].length; j++) {
				arr.setElement(i, j, Integer.parseInt(arrd2d[i][j].getText()));
			}
		}
		return arr;
	}
	
	public void setText(Array7x7 arr) {
		for(int i=0; i<arrd2d.length; i++) {
			for(int j=0; j<arrd2d[i].length; j++) {
				arrd2d[i][j].setText(String.valueOf(arr.getElement(i, j)));
			}
		}
	}
	
	public Array7 getInput() {
		Array7 arr = new Array7();
		for(int i=0; i<arr.getLength(); i++) {
			arr.setElement(i, Integer.parseInt(westInput[i].getText()));
		}
		
		return arr;
	}
			
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnShiftLeft) {
				ctrl.setDirection(DIRECTION.LEFT);
				ctrl.showShift(getInput());
			}
			if(e.getSource() == btnShiftRight){
				ctrl.setDirection(DIRECTION.RIGHT);
				ctrl.showShift(getInput());
				
			}
			
			
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
