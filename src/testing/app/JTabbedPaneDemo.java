package testing.app;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.*;
import roffe.Color.Color;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Sebastian Börebäck on 2016-01-05.
 */
public class JTabbedPaneDemo extends JPanel implements ComponentListener {

	private final Controller ctrl;
	private static JPanel currentView;
	private JFrame frame;

	@Override
	public void componentResized(ComponentEvent e) {
		//extra to handle the resize of the view.
		currentView.setSize(e.getComponent().getSize());

	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	public JTabbedPaneDemo(JFrame frame) {
		this.frame = frame;
		Array7x7 model = new Array7x7();

//		new Controller(model, view);


		JTabbedPane jtbExample = new JTabbedPane();

		JPanel numberPanel = new ViewNumbers();
		JPanel colorPanel= new ViewColor(Color.BLACK,Color.GRAY);
		JPanel shiftPanel= new ViewShift();
		JPanel mrBigPanel= new MrBigViewWindowsWithFlowText(Color.BLACK, Color.GRAY);


		jtbExample.addTab("Numbers", numberPanel);
		jtbExample.addTab("Colors", colorPanel);
		jtbExample.addTab("Shifting", shiftPanel);
		jtbExample.addTab("Mr big flow", mrBigPanel);

		jtbExample.setSelectedIndex(3);
		((MrBigViewWindowsWithFlowText)mrBigPanel).init();
		currentView = (JPanel) jtbExample.getSelectedComponent();

		ctrl = new Controller(model, (ViewImpl) jtbExample.getSelectedComponent());
//		ctrl.setMainPanel(this);
		// Add the tabbed pane to this panel.
		setLayout(new GridLayout(1, 1));

		jtbExample.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(jtbExample.getSelectedComponent() == mrBigPanel)
					((MrBigViewWindowsWithFlowText)mrBigPanel).init();
				ctrl.setView((ViewImpl) jtbExample.getSelectedComponent());
				currentView = (JPanel) jtbExample.getSelectedComponent();

				//handeling the resizing between views
				Dimension dim = jtbExample.getSelectedComponent().getPreferredSize();
				frame.setSize(dim);

			}
		});

		add(jtbExample);


	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Kirbys Hjältar");


		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JTabbedPaneDemo tabpanel = new JTabbedPaneDemo(frame);
		frame.getContentPane().add(new JTabbedPaneDemo(frame),
				BorderLayout.CENTER);
		frame.addComponentListener(tabpanel);
		frame.setSize(1300, 400);
		frame.setVisible(true);


	}


	public void refreshFrame() {
		frame.revalidate();
		frame.pack();
		frame.repaint();
	}
}
