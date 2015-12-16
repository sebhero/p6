package mah.se.algorithms;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

/**
 *Moves the Arrays 1 step at a time in the chosen direction. Main focus is shifting left.
 *@author Johnatan Sona, Robin Johnsson
 **/
public class ShiftArray{
	public Array7 shiftLeft(Array7x7 model, Array7 array7){
		Array7 array = new Array7();
		array = model.getCol(0);
		for(int col = 0; col<model.getLength()-1; col++){
			model.setCol(col, model.getCol(col+1));
			
		}
		model.setCol(6, array7);
		
		return array;
	}
	public Array7 shiftRight(Array7x7 model, Array7 array7 ){
		Array7 array = new Array7();
		array = model.getCol(6);
		for(int col = model.getLength()-1; col>0; col--){
			model.setCol(col, model.getCol(col-1));
		}
		model.setCol(0, array7);
		return array;
	}
	public Array7 shift(Array7x7 model, Array7 array7, Controller.DIRECTION dir) {
		switch(dir) {
		case LEFT:
			return shiftLeft(model, array7);
		case RIGHT: 
		default:
			return shiftRight(model, array7);
		}
		
	}
}
