package mah.se.algorithms;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillCharacter;
import roffe.Color.Color;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Börebäck on 2015-12-30.
 */
public class ShiftText {


	//text strängen av alla bokstäver
	private String text;
	//strängen omvandlad till 7x7 block
	private final ArrayList<Array7x7> message = new ArrayList<>();
	//colordisplay view i 7x7 block
	private ArrayList<Array7x7> messageView;
	//för att kunna shifta
	private final ShiftArray shifter = new ShiftArray();
	//antal steg alltså kolumner/rader som har gått
	private int stepps;
	//total antal steg som finns.
	private int doneStepping;
	private ArrayList<Array7x7> testMesssage;

	/**
	 * Tar ett steg i message och shiftar över det till messageView
	 * @param dir
	 */
	public void stepText(Controller.DIRECTION dir) {
		Array7 next = new Array7(Color.BLACK);

		switch (dir) {

			case LEFT:
				for (int i = message.size()-1; i >= 0 ; i--) {
					next= shifter.shift(message.get(i), next, dir);
				}
				for (int i = messageView.size()-1; i >= 0 ; i--) {
					next= shifter.shift(messageView.get(i), next, dir);
				}
				break;
			case UP:
				//System.out.println("ran step "+stepps);
				for (int i = message.size()-1; i >= 0 ; i--) {
					next= shifter.shift(message.get(i), next, dir);
				}

				//skapa mellan rum mellan bokstaverna. behovs inte eg i left right
				Array7 temp;
				if ((stepps) % 7 ==0) {
					temp =	next;
					next = new Array7(Color.BLACK);
					for (int i = messageView.size()-1; i >= 0 ; i--) {
						next= shifter.shift(messageView.get(i), next, dir);
					}
					next = temp;
				}

				for (int i = messageView.size()-1; i >= 0 ; i--) {
					next= shifter.shift(messageView.get(i), next, dir);
				}
				break;

			case RIGHT:
				for (int i = 0; i <= message.size()-1 ; i++) {
					next = shifter.shift(message.get(i), next,dir);
				}
				for (int i = 0; i <= messageView.size()-1 ; i++) {
					next = shifter.shift(messageView.get(i), next,dir);
				}
				break;
			case DOWN:

				for (int i = 0; i <= message.size()-1 ; i++) {
					next = shifter.shift(message.get(i), next,dir);
				}

				//skapa mellan rum mellan bokstaverna. behovs inte eg i left right
				if ((stepps) % 7 ==0) {
					temp =	next;
					next = new Array7(Color.BLACK);
					for (int i = 0; i <= messageView.size()-1 ; i++) {
						next = shifter.shift(messageView.get(i), next,dir);
					}
					next = temp;
				}

				for (int i = 0; i <= messageView.size()-1 ; i++) {
					next = shifter.shift(messageView.get(i), next,dir);
				}
				break;
		}
	}


	/**
	 * Genererar messageViewn
	 * @param size
	 */
	public void setupMessageView(int size) {
				this.messageView = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					messageView.add(new Array7x7(Color.BLACK));
				}
	}

	/**
	 * Hämtar messageview för colorDispalyn
	 * @return en Arraylist med int[][] likt den som används i view
	 */
	public ArrayList<int[][]> getMessageView() {
		ArrayList<int[][]> temp = messageView.stream().map(Array7x7::getAll).collect(Collectors.toCollection(ArrayList::new));
		return temp;
	}

	/**
	 * Laddar in texten och omvandlar den till en lista av 7x7
	 * @param texy inmattnings strängen
	 */
	public void loadText(String texy) {
		this.text = texy;
		message.clear();

		//save the texy
		FillAlgorithm filler = new FillCharacter();
		texy = texy.toUpperCase();
		for(int n = 0; n < texy.length(); n++) {
			Array7x7 character = filler.fillWithOneType((int) texy.charAt(n));
			message.add(character);
		}
	}

	/**
	 * Hämtar storleken på message
	 * @return
	 */
	public int getMessageSize() {
		return message.size();
	}

	/**
	 * Rensar bort det skapade message
	 */
	public void clearMessage() {
		message.clear();
	}

	/**
	 * Nollställer message till den inlästa strängen.
	 */
	public void resetMessage() {
		clearMessage();
		//messageView.clear();
		loadText(text);
	}

	/**
	 * Kollar ifall man har gått alla stegen i messageView
	 * @return true om man har gått alla steg och nollställer stegen
	 */
	public boolean checkIfDoneStepping() {
			if (stepps == doneStepping) {
				stepps=0;
				resetMessage();
				return true;
			}
			return false;
	}

	/**
	 * Sätter max stegen
	 * @param maxSteps
	 */
	public void setMaxSteps(int maxSteps) {
		this.doneStepping = maxSteps;
	}

	/**
	 * Ökar antal steg
	 */
	public void increaseSteps() {
		stepps++;
	}

	//TODO only for making the mrBig work. remove
	public Array7x7[] getMessageView7x7() {

		Array7x7[] temp= new Array7x7[messageView.size()];
		for (int i = 0; i < messageView.size(); i++) {
			temp[i] = messageView.get(i);
		}
		return temp;
	}

	public void setSteps(int stepps) {
		this.stepps = stepps;
	}

	public void clearMessageView() {
		int size = messageView.size();
		messageView.clear();
	}
}
