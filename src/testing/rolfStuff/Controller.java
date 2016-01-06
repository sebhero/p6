package testing.rolfStuff;

import roffe.Color.Color;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class Controller {
	private Timer timer;
	private final Random random = new Random();
	private final ColorDisplayDemo demo;
	private final int[][] charR = {{Color.TRANSPARENT,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT},
			{Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT},
			{Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT},
			{Color.TRANSPARENT,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT},
			{Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT},
			{Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT},
			{Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT,Color.WHITE,Color.TRANSPARENT}};
	private final int[][] stamp = new int[7][7];
	
	private Controller(ColorDisplayDemo demo) {
		this.demo = demo;
		demo.setController(this);
	}
	
	private class RandomColors extends TimerTask {
		private int counter = 0;
		public void run() {
			if(counter<10) {
				counter++;
				showRandom();
			} else {
				timer.cancel(); // Can't use Timer-instance anymore
				demo.ativateBtnTimer();
			}
		}
	}

	public void showR() {
		demo.updateDisplay(charR);
	}

	public void showRandom() {
		int red, green, blue;
		for(int row=0; row<stamp.length; row++) {
			for(int col=0; col<stamp[row].length; col++) {
				red = random.nextInt(256);
				green = random.nextInt(256);
				blue = random.nextInt(256);
				stamp[row][col] = Color.rgb(red, green, blue);
			}
		}
		demo.updateDisplay(stamp);
	}

	public void useTimer() {
		timer = new Timer();
		demo.disableBtnTimer();
		timer.schedule(new RandomColors(), 500, 500);
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            ColorDisplayDemo demo1 = new ColorDisplayDemo(Color.BLACK, Color.GRAY);
            new Controller(demo1);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(demo1);
            frame.pack();
            frame.setVisible(true);
        });
	}
}
