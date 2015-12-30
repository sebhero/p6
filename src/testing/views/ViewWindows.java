//package testing.views;
//
//import mah.se.mvc.controller.Controller;
//import mah.se.mvc.model.Array7x7;
//import mah.se.mvc.view.ViewImpl;
//import roffe.Color.ColorDisplay;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//
///**
// *
// * @author Jonatan Fridsten
// *
// */
//public class ViewWindows extends JPanel implements ViewImpl {
//	private JPanel pnlDisplay;
//	private JPanel pnlButtons;
//	private ColorDisplay colorDisplay;
//	private Controller ctrl;
//	private JButton btnLeft = new JButton("Shift vänster");
//	private JButton btnRigth = new JButton("flow text");
//	private JButton btnChar = new JButton("Lägg till char");
//	private JTextField txtInput = new JTextField();
//
//
//	public ViewWindows(int background, int grid) {
//		this(1, 1, background, grid);
//	}
//
//	public ViewWindows(int verticalPages, int horizontalPages, int background, int grid) {
//
//		//colorDisplay = new ColorDisplay(verticalPages, horizontalPages, background, grid);
//		colorDisplay = new ColorDisplay(background, grid);
//		colorDisplay .setPreferredSize(new Dimension(300, 350));
//		colorDisplay .setSize(100, 120);
//		pnlDisplay = new JPanel();
//		pnlButtons = new JPanel();
//
//		this.setLayout(new BorderLayout());
//		this.setPreferredSize(new Dimension(400, 400));
//
//		pnlDisplay.setPreferredSize(new Dimension(400, 300));
//		pnlDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
//		pnlDisplay.add(colorDisplay);
//
//		pnlButtons.setPreferredSize(new Dimension(400, 40));
//		pnlButtons.setLayout(new GridLayout(0, 4));
//
//		pnlButtons.add(txtInput);
//		pnlButtons.add(btnChar);
//		pnlButtons.add(btnLeft);
//		pnlButtons.add(btnRigth);
//		add(pnlButtons, BorderLayout.SOUTH);
//		add(pnlDisplay, BorderLayout.CENTER);
//
//		btnChar.addActionListener(ae -> {
//			ctrl.showRanomChar();
//		});
//
//		btnLeft.addActionListener(ae -> {
//			ctrl.setDirection(Controller.DIRECTION.LEFT);
////			ctrl.shiftWithRedColor();
//		});
//
//		btnRigth.addActionListener(ae -> {
//			ctrl.flowText("HEJ");
//		});
//	}
//
//	/**
//	 * Uppdaterar view med en color matris
//	 *
//	 * @param matrix
//	 *            matrisen po alla farg element
//	 */
//	public void updateViewColor(int[][] matrix) {
//
//
//	}
//
//	/**
//	 * Uppdatera view med en siffer matris
//	 *
//	 * @param matrix
//	 *            matris med siffror
//	 */
//	public void updateView(int[][] matrix) {
//        colorDisplay.setDisplay(matrix);
//        colorDisplay.updateDisplay();
//	}
//
//	@Override
//	public void updateBigView(Array7x7[] all) {
//
//	}
//
//	@Override
//	public int getPages() {
//		return 0;
//	}
//
//	@Override
//	public void updateView(ArrayList<Array7x7> messageView) {
//
//	}
//
//
//	/**
//	 * Satter controller till viewen som styr viewn. den skoter all
//	 * kommunikation med array7x7. hanterar knapptryckningar m.m.
//	 *
//	 * @param ctrl
//	 *            sjalva controllern
//	 */
//	public void setCtrl(Controller ctrl) {
//		this.ctrl = ctrl;
//	}
//
//
//
//}
