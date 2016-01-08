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

	/**
	 * Hämtar vilken riktning som gäller just nu
	 * @return nuvarande riktningen
	 */
	public DIRECTION getDirection() {
		return dir;
	}

	/**
	 * ger controllern mainPanel för att kunna uppdatera framen.
	 * @param mainPanel fönstret
	 */
	public void setMainPanel(App mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * uppdatera framen från mrBig.
	 */
	public void refreshMainPanel() {
		mainPanel.refreshFrame();
	}

	/**
	 * Rensa allt i mrBig
	 */
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


	/**
	 * håller koll på vilket håll vi shiftar
	 */
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
	 * En kontroller som hanterar all kommunikation med Array7x7 modelerna och olika algoritmer för ändringar av 7x7.
	 * @param model the Array7x7 model
	 * @param view  the view we are displaying the matrix on
	 */
	public Controller(Array7x7 model, ViewImpl view) {

		modelMap = new HashMap();
		modelMap.put("mah.se.mvc.view.ViewColor",new Array7x7());
		modelMap.put("mah.se.mvc.view.ViewNumbers",new Array7x7());
		modelMap.put("mah.se.mvc.view.MrBigViewWindowsWithFlowText",new Array7x7());
		modelMap.put("mah.se.mvc.view.ViewShiftTest",new Array7x7());

		this.view = view;
		this.view.setCtrl(this);
		shifter = new ShiftArray();

	}


	/**
	 * Uppdatear vyn med Array7x7 av nummer
	 */
	public void updateView() {
		view.updateView(model.getAll());
	}


	/**
	 * Knapp tryck från vyn
	 * Shifta höger på modelen
	 * @param newArray nya arrayen som ska skiftas in
	 * @return överflödet
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
	 * knapp tryck
	 * Slumpar antal siffror och ritar ut den i vyn
	 */
	public void showRandom() {
		filler = getFiller(FILLERTYPE.NUMBERS);
		model = filler.fillWithRandom();
		updateView();
	}

	/**
	 * knapp tryck
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
	 * knapp tryck
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
	 * knapp tryck
	 * Visa slumpade färger i vyn
	 */
	public void showRandomColor() {
		filler = getFiller(FILLERTYPE.COLORS);

		System.out.println("randomcolor "+ modelMap.get(view.getClass().getName()));
		model = filler.fillWithRandom();
		modelMap.replace(view.getClass().getName(),model);

		updateView();
	}


	/**
	 * Visa en färg i på hela Array7x7 i vyn
	 * @param color färg som ska fylla displayen
	 */
	public void showSameColor(int color) {
		filler = getFiller(FILLERTYPE.COLORS);
		System.out.println("same color "+ modelMap.get(view.getClass().getName()));
		model = filler.fillWithOneType(color);
		modelMap.replace(view.getClass().getName(),model);
		updateView();
	}

	/**
	 * Knapp tryck
	 * Satter en ny rad i modelen.
	 * @param rowPos vilken rad som ska ändras
	 * @param newRow nya raden som ska in
	 */
	public void setRow(int rowPos, int[] newRow) {
		model.setRow(rowPos, new Array7(newRow));
		updateView();
	}

	/**
	 * Knapp tryck
	 * Satter en ny kolumn i modelen.
	 * @param colPos vilken kolumn som ska ändras
	 * @param newCol nya kolumnen som ska in
	 */
	public void setCol(int colPos, int[] newCol) {
		model.setCol(colPos, new Array7(newCol));
		updateView();
	}


	/**
	 * knapp tryck
	 * Satter ett nytt element i modelen.
	 * @param rowPos den valda raden
	 * @param colPos den valda kolumnen
	 * @param value värdet som ska sättas i rad/kolumn kombinationen
	 */
	public void setElement(int rowPos, int colPos, int value) {
		model = modelMap.get(view.getClass().getName());
		model.setElement(rowPos, colPos, value);
		System.out.println("setele " + modelMap.get(view.getClass().getName()));
		updateView();
	}

	/**
	 * knapp tryck
	 * Hamtar rad i modelen.
	 * @param rowPos raden som ska läsas av
	 * @return avläsna raden
	 */
	public int[] getRow(int rowPos) {
		return model.getRow(rowPos).getAll();
	}

	/**
	 * knapp tryck
	 * Hamtar kolumn i modelen.
	 * @param colPos kolumnen som ska läsas av
	 * @return avläsna kolumnen
	 */
	public int[] getCol(int colPos) {
		return model.getCol(colPos).getAll();
	}

	/**
	 * knapp tryck från ViewColor
	 * Hamtar element i modelen.
	 * @param rowPos Raden som ska läsas av
	 * @param colPos kolmnen ska läsas av
	 * @return avläsna elementet
	 */
	public int getElement(int rowPos, int colPos) {
		return model.getElement(rowPos, colPos);
	}


	/**
	 * Shiftar colorViev
	 * @param input vad som ska flyttas
	 */
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
		else if (this.dir == DIRECTION.RIGHT)
			currentDir = DIRECTION.RIGHT;
		else if(this.dir == DIRECTION.UP)
			currentDir = DIRECTION.UP;
		else
			currentDir = DIRECTION.DOWN;

		this.dir = dir;
		showStepText();
		shiftText.increaseSteps();
		this.dir = currentDir;
	}

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

	/**
	 * Initar Messageview efter ändring av storlek på mrBig
	 */
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
	 * knapp tryck för att steppa genom strängen vi har laddat in
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

	/**
	 * Mr big knapp tryck.
	 * shiftar klart MessageView
	 */
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
