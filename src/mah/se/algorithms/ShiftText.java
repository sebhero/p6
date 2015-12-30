package mah.se.algorithms;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.patterns.strategy.FillAlgorithm;
import mah.se.patterns.strategy.FillCharacter;
import roffe.Color.Color;

import java.util.ArrayList;

/**
 * Created by Sebastian Börebäck on 2015-12-30.
 */
public class ShiftText {


	private ArrayList<Array7x7> message = new ArrayList<>();
	private ArrayList<Array7x7> messageView;
	private ShiftArray shifter = new ShiftArray();
	private String text;
	private FillAlgorithm filler;
	private int stepps;
	private int doneStepping;

	public void stepText(Controller.DIRECTION dir) {
		Array7 next = new Array7(Color.BLACK);

		switch (dir) {

			case LEFT:
			case UP:
				for (int i = message.size()-1; i >= 0 ; i--) {
					next= shifter.shift(message.get(i), next, dir);
				}

				for (int i = messageView.size()-1; i >= 0 ; i--) {
					next= shifter.shift(messageView.get(i), next, dir);
				}
				break;

			case RIGHT:
			case DOWN:
				for (int i = 0; i <= message.size()-1 ; i++) {
					next = shifter.shift(message.get(i), next,dir);
				}

				for (int i = 0; i <= messageView.size()-1 ; i++) {
					next = shifter.shift(messageView.get(i), next,dir);
				}
				break;
		}
	}


	public void setupMessageView(int size) {
				this.messageView = new ArrayList<Array7x7>();
				for (int i = 0; i < size; i++) {
					messageView.add(new Array7x7(Color.BLACK));
				}
	}

	public ArrayList<int[][]> getMessageView() {
		ArrayList<int[][]> temp = new ArrayList<>();
		for (Array7x7 letter : messageView) {
			temp.add(letter.getAll());
		}
		return temp;
	}

	public void loadText(String texy) {
		this.text = texy;
		message.clear();

		//save the texy
		filler = new FillCharacter();
		texy = texy.toUpperCase();
		for(int n = 0; n < texy.length(); n++) {
			Array7x7 character = filler.fillWithOneType((int) texy.charAt(n));
			message.add(character);
		}
	}

	public int getMessageSize() {
		return message.size();
	}

	public void clearMessage() {
		message.clear();
	}

	public void resetMessage() {
		clearMessage();
		loadText(text);
	}

	public boolean checkIfDoneStepping() {
			if (stepps == doneStepping) {
				stepps=0;
				resetMessage();
				return true;
			}
			return false;
	}

	public void setMaxSteps(int maxSteps) {
		this.doneStepping = maxSteps;
	}

	public void increaseSteps() {
		stepps++;
	}
}
