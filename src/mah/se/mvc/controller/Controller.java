package mah.se.mvc.controller;

import mah.se.App;
import mah.se.algorithms.ShiftArray;
import mah.se.algorithms.ShiftText;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.mvc.view.ViewImpl;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillCharacter;
import mah.se.patterns.strategy.FillColor;
import mah.se.patterns.strategy.FillNumbers;
import testing.app.JTabbedPaneDemo;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */

/**
 * Kontroller styr kommunikationen mellan Vyn och modelen.
 */
public class Controller {


	private final HashMap<String,Array7x7>  modelMap;
	private App mainPanel;

	public DIRECTION getDirection() {
		return dir;
	}

	public void setMainPanel(App mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void refreshMainPanel() {
		mainPanel.refreshFrame();
	}

	public void clearAll() {

		if (this.timer != null) {
			this.pause();
			//TODO removes timer need to be check if still works with rest
			this.timer =null;
		}
		this.shiftText.resetMessage();
		this.shiftText.clearMessageView();
		this.setupMessageView();
		this.shiftText.setSteps(0);
	}


	//håller koll på vilket håll vi shiftar
	public enum DIRECTION {
		RIGHT,
		LEFT,
		UP,
		DOWN
	}

	/**
	 * Väljer vilken filler metod
	 */
	public enum FILLERTYPE {
		COLORS, CHARACTERS, NUMBERS
	}

	//Hanterar text shiftning
	private final ShiftText shiftText = new ShiftText();
	private int speed = 50;
	//vilket håll vi ska rita ut
	private DIRECTION dir = DIRECTION.LEFT;
	//våran algoritm för att skifta a7x7
	private final ShiftArray shifter;
	private FillAlgorithm filler;
	private Array7x7 model;
	private ViewImpl view;

	private Timer timer;

	/**
	 * A controller to handles communication with the Array7x7 model and diffrent strategies for filling it.
	 *
	 * @param model the Array7x7 model
	 * @param view  the view we are displaying the matrix on
	 */
	public Controller(Array7x7 model, ViewImpl view) {

		modelMap = new HashMap();
		modelMap.put("mah.se.mvc.view.ViewColor",new Array7x7());
		modelMap.put("mah.se.mvc.view.ViewNumbers",new Array7x7());
		modelMap.put("mah.se.mvc.view.MrBigViewWindowsWithFlowText",new Array7x7());
		modelMap.put("mah.se.mvc.view.ViewShiftTest",new Array7x7());

		//TODO @Deprecated Use hashmap instead
//		this.model = model;
		this.view = view;
		this.model = getViewsModel(view);

		this.view.setCtrl(this);
//		Array7x7[] colorDisplay = new Array7x7[view.getHorizontalPages()];
//		filler = getFiller(FILLERTYPE.COLORS);
//		for (int n = 0; n < colorDisplay.length; n++)
//			colorDisplay[n] = filler.fillWithOneType(Color.BLACK);
		shifter = new ShiftArray();
//		updateView();


//		modelMap.put(new Array7x7(), "mah.se.mvc.view.ViewColor");

	}


	/**
	 * Update the view med Array7x7 av nummer
	 */
	private void updateView() {
		view.updateView(model.getAll());
	}


	/**
	 * Btn call from view
	 * Shift right the matrix
	 */
	public Array7 shift(Array7 newArray) {

		return shifter.shift(model, newArray, dir);

	}

	/**
	 * Sätter vilket håll vi ska shifta
	 *
	 * @param dir riktigt vi shiftar
	 */
	public void setDirection(DIRECTION dir) {
		this.dir = dir;
	}

	/**
	 * btn click
	 * Slumpar antal siffror och ritar ut den i vyn
	 */
	public void showRandom() {
		filler = getFiller(FILLERTYPE.NUMBERS);
		model = filler.fillWithRandom();
		updateView();
	}

	/**
	 * btn click
	 * Ritar ut 1-7 i vyn
	 * 1234567
	 * 1234567
	 * 1234567
	 * 1234567
	 * 1234567
	 * 1234567
	 * 1234567
	 */
	public void showNumbers1_7() {
		filler = getFiller(FILLERTYPE.NUMBERS);
		model = filler.fillWithInGaining();
		updateView();
	}

	/**
	 * btn click from view
	 * Slumpar ett tal och fyller Array7x7 med detta tal
	 * därefter visar den i vyn
	 */
	public void showRandomSame() {
		filler = getFiller(FILLERTYPE.NUMBERS);

		int rnd = new Random().nextInt(7) + 1;
		model = filler.fillWithOneType(rnd);
		updateView();
	}

	/**
	 * Hämtar vilken fyll algoritm vi ska använda
	 *
	 * @param typeOfFiller val av fyllnings algoritm
	 * @return fyllnings algoritmen.
	 */
	private FillAlgorithm getFiller(FILLERTYPE typeOfFiller) {
		//TODO use singelton
		switch (typeOfFiller) {

			case COLORS:
				return new FillColor();
			case CHARACTERS:
				return new FillCharacter();
			case NUMBERS:
			default:
				return new FillNumbers();
		}
	}

	/**
	 * btn click
	 * Visa slumpade färger i vyn
	 */
	public void showRandomColor() {
		filler = getFiller(FILLERTYPE.COLORS);
		model = filler.fillWithRandom();
		updateView();
	}

	/**
	 * @deprecated use updateView()
	 * uppdatera vyn med nya Array7x7
	 * av typen färger
	 */
//    private void updateViewColor() {
//        view.updateViewColor(model.getAll());
//    }

	/**
	 * Visa en färg i på hela Array7x7 i vyn
	 */
	public void showSameColor(int color) {
		filler = getFiller(FILLERTYPE.COLORS);
		model = filler.fillWithOneType(color);
		updateView();
	}

	/**
	 * Visar en graident färg mellan 2 färger
	 * i vyn
	 */
	public void showGradiantColor() {
		filler = getFiller(FILLERTYPE.COLORS);
		model = filler.fillWithInGaining();
		updateView();
	}

	/**
	 * Visar en slumpad bokstav i vyn
	 */
	public void showRanomChar() {
		filler = getFiller(FILLERTYPE.CHARACTERS);
		model = filler.fillWithRandom();
		updateView();
	}


	/**
	 * Btn click
	 * Satter en ny rad i modelen.
	 */
	public void setRow(int rowPos, int[] newRow) {
		model.setRow(rowPos, new Array7(newRow));
		updateView();
	}

	/**
	 * Btn click
	 * Satter en ny kolumn i modelen.
	 */
	public void setCol(int colPos, int[] newCol) {
		model.setCol(colPos, new Array7(newCol));
		updateView();
	}


	/**
	 * Btn click
	 * Satter ett nytt element i modelen.
	 */
	public void setElement(int rowPos, int colPos, int value) {

		model.setElement(rowPos, colPos, value);
		updateView();
	}

	/**
	 * Btn click
	 * Hamtar rad i modelen.
	 */
	public int[] getRow(int rowPos) {
		return model.getRow(rowPos).getAll();
	}

	/**
	 * Btn click
	 * Hamtar kolumn i modelen.
	 */
	public int[] getCol(int colPos) {
		return model.getCol(colPos).getAll();
	}

	/**
	 * Btn click
	 * Hamtar element i modelen.
	 */
	public int getElement(int rowPos, int colPos) {
		return model.getElement(rowPos, colPos);
	}


	public void showShift(Array7 input) {
		shift(input);
		updateView();

	}


	/**
	 * Skiftar bara ett steg åt dir
	 *
	 * @param dir vilket håll det ska skiftas åt
	 */
	public void simpleShift(DIRECTION dir) {
		DIRECTION currentDir;
		if (this.dir == DIRECTION.LEFT)
			currentDir = DIRECTION.LEFT;
		else
			currentDir = DIRECTION.RIGHT;
		this.dir = dir;
		showStepText();
		this.dir = currentDir;
	}

	// TODO new FlowText using the shiftText that works in all directions

	/**
	 * Uppdaterar vyn med den nya message list<7x7>
	 */
	private void updateViewMessage() {
		view.updateView(shiftText.getMessageView(), dir);
	}

	/**
	 * Laddar in strängen från vyn till en lista av 7x7
	 *
	 * @param texy strängen av alla char
	 */
	public void loadFlowText(String texy) {
		shiftText.loadText(texy);
		this.setupMessageView();
	}

	private void setupMessageView() {
		switch (dir) {
			case RIGHT:
			case LEFT:
				//setup messageView size
				shiftText.setupMessageView(view.getHorizontalPages());
				//setup max steps how many Columns/rows
				if (view.getHorizontalPages() > shiftText.getMessageSize()) {
					shiftText.setMaxSteps(7+view.getHorizontalPages() * 7);

				} else {
					shiftText.setMaxSteps(7+shiftText.getMessageSize() * 7);
				}
				break;
			case UP:
			case DOWN:
				//setup messageView size
				shiftText.setupMessageView(view.getVerticalPages());
				//setup max steps how many Columns/rows
				if (view.getVerticalPages() > shiftText.getMessageSize()) {
					shiftText.setMaxSteps(view.getVerticalPages() * 7);
				} else {
					shiftText.setMaxSteps(shiftText.getMessageSize() * 7);
				}
				break;
		}
	}

	/**
	 * btn click för att steppa genom strängen vi har laddat in
	 */
	public void showStepText() {

		shiftText.stepText(dir);

		updateViewMessage();
	}


	/**
	 * Rullande text av strängen vi har laddat in
	 */


	public void flowText() {
		//start timer
		timer = new Timer();
		timer.schedule(new shiftTextTimer(), speed, speed);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		pause();
		resume();
	}

	/**
	 * Timmer till flowText för strängen vi har laddat in
	 */
	private class shiftTextTimer extends TimerTask {
		@Override
		public void run() {
			if (shiftText.checkIfDoneStepping()) {
				//TODO if want continues flow remove this line
				//timer.cancel();

				return;
			}

			shiftText.stepText(dir);
			shiftText.increaseSteps();
			updateViewMessage();
		}
	}

	public void shiftOutAll() {
		while(shiftText.checkIfDoneStepping()) {
			shiftText.stepText(dir);
			shiftText.increaseSteps();
		}
	}

	/**
	 * Pausar timern
	 */
	public void pause() {
		timer.cancel();
	}

	/**
	 * Startar timern igen
	 */
	public void resume() {
		timer = new Timer();    //Då shiftcounter och shiftArrayIdx inte ändras kan man bara göra en ny timer
		timer.schedule(new shiftTextTimer(), speed, speed);

	}

	/**
	 * Sätter vilken view som vi använder just nu. Används för att byta view
	 * och sätter view till den vyn och ger den våran kontroller samt ställer in rätt model.
	 * @param view nuvarnde view
	 */
	public void setView(ViewImpl view) {
		this.view = view;
		this.model = getViewsModel(view);
		view.setCtrl(this);

	}

	/**
	 * Tar reda på vilken model vi använder för just den viewn
	 * @param view vilken view vi använder
	 * @return modelen för just den viewn
	 */
	private Array7x7 getViewsModel(ViewImpl view) {
		return this.modelMap.get(view.getClass().getName());
	}
}
