package roffe.Color;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ColorDisplayDemo extends JPanel {
	private Controller controller;
	private ColorDisplay display;
	private JButton btnR = new JButton("R");
	private JButton btnRandom = new JButton("Slumpfärger");
	private JButton btnTimer = new JButton("Timer");
	
	public ColorDisplayDemo(int background, int grid) {
		ButtonListener bl = new ButtonListener();
		setLayout(new BorderLayout());
		display = new ColorDisplay(background, grid);
		add(display, BorderLayout.CENTER);
		add(buttonPanel(), BorderLayout.SOUTH);
		btnR.addActionListener(bl);
		btnRandom.addActionListener(bl);
		btnTimer.addActionListener(bl);
	}

	private JPanel buttonPanel() {
		JPanel panel = new JPanel(new GridLayout(1,3));
		panel.add(btnR);
		panel.add(btnRandom);
		panel.add(btnTimer);
		return panel;
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void ativateBtnTimer() {
		btnTimer.setEnabled(true);
	}

	public void disableBtnTimer() {
		btnTimer.setEnabled(false);
	}

	public void updateDisplay(int[][] colors) {
		display.setDisplay(colors);
		display.updateDisplay();
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnR) {
				controller.showR();
			} else if(e.getSource()==btnRandom) {
				controller.showRandom();
			} else if(e.getSource()==btnTimer) {
				controller.useTimer();
			}
			
		}		
	}
}
