package mah.se.mvc.view;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7;
import roffe.Color.ColorDisplay;

import javax.swing.*;
import java.awt.*;

/**
 * @author Sebastian Börebäck
 * Vyn visar i swing/windows en representation av modelen
 * manipuleras modeln via kontrollern och dess hjälpklasser
 */
public class ViewWindowsWithFlowText extends ViewWindows {
    //testing
    private final JPanel pnlButtonsExtra;

    private JPanel pnlDisplay;
	private JPanel pnlButtons;
	private ColorDisplay colorDisplay;
	private Controller ctrl;
	private JButton btnLeft = new JButton("Shift vänster");
	private JButton btnRigth = new JButton("Shift höger");
	private JButton btnChar = new JButton("Lägg till char");

    private JButton btnFillColor = new JButton("Fyll med slumpfärger");
    private JButton btnFlowText = new JButton("Rinnande Text");

	private JTextField txtInput = new JTextField();

	public ViewWindowsWithFlowText(int background, int grid) {
		this(1, 1, background, grid);
	}

	public ViewWindowsWithFlowText(int verticalPages, int horizontalPages, int background, int grid) {
        super(verticalPages,  horizontalPages,  background,  grid);
        colorDisplay = new ColorDisplay(verticalPages, horizontalPages, background, grid);
		pnlDisplay = new JPanel();
		pnlButtons = new JPanel();

		pnlButtonsExtra = new JPanel();
        pnlButtonsExtra.setLayout(new GridLayout(1, 4));
        pnlButtonsExtra.add(btnFillColor);
        pnlButtonsExtra.add(btnFlowText);

		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(400, 400));

		pnlDisplay.setPreferredSize(new Dimension(400, 300));
		pnlDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		pnlDisplay.add(colorDisplay);
		
		pnlButtons.setPreferredSize(new Dimension(400, 40));
		pnlButtons.setLayout(new GridLayout(1, 4));
		
		pnlButtons.add(txtInput);
		pnlButtons.add(btnChar);
		pnlButtons.add(btnLeft);
		pnlButtons.add(btnRigth);
		//add(pnlButtons, BorderLayout.SOUTH);
        JPanel pnl = new JPanel(new GridLayout(2, 1));
        pnl.add(pnlButtons);
        pnl.add(pnlButtonsExtra);

        add(pnl, BorderLayout.SOUTH);

		add(pnlDisplay, BorderLayout.CENTER);

        initButtons();
	}

    /**
     * Init alla knappar
     */
    private void initButtons() {
        btnRigth.addActionListener(ae -> {
            //shifta allt till höger och fyller på med röd färg
            String txt = txtInput.getText();
            if (txt.length() <= 0) {
                txt = "?";
            }
            ctrl.setDirection(Controller.DIRECTION.RIGHT);
            Array7 leftOver = ctrl.shiftWithRedColor();
            txtInput.setText(leftOver.toString());
        });

        btnLeft.addActionListener(ae -> {
            //shifta allt till vänster och fyller på med röd färg
            ctrl.setDirection(Controller.DIRECTION.LEFT);
            Array7 leftOver = ctrl.shiftWithRedColor();
            txtInput.setText(leftOver.toString());
        });

        btnChar.addActionListener(ae ->{
            //visar en slumpad bokstav
            ctrl.showRanomChar();
        });


        btnFillColor.addActionListener(ae -> {
            //Visa slumpfärger
            ctrl.showRandomColor();
        });

        btnFlowText.addActionListener(ae ->{
            //visa rinnande text
            String txt = txtInput.getText();
            //inget skrivet i text fältet visa ?
            if (txt.length() <= 0) {
                txt = "?";
            }
            ctrl.flowText(txt);
        });


    }

    /**
	 * Uppdaterar view med en color matris
	 * 
	 * @param matrix
	 *            matrisen po alla farg element
	 */
	public void updateViewColor(int[][] matrix) {
        colorDisplay.setDisplay(matrix);
        colorDisplay.updateDisplay();

    }

	/**
	 * Uppdatera view med en siffer matris
	 * 
	 * @param matrix
	 *            matris med siffror
	 */
	public void updateView(int[][] matrix) {
		// TODO implement
	}

	/**
	 * Satter controller till viewen som styr viewn. den skoter all
	 * kommunikation med array7x7. hanterar knapptryckningar m.m.
	 * 
	 * @param ctrl
	 *            sjalva controllern
	 */
	public void setCtrl(Controller ctrl) {
		this.ctrl = ctrl;
	}

}
