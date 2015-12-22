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
	public Array7 shiftUp(Array7x7 model, Array7 array7){
		Array7 array = new Array7();
		array = model.getRow(0);//array gets the values of the first column
		for(int row = 0; row<model.getLength()-1; row++){
			model.setRow(row, model.getRow(row+1));
			//Current column gets thevalue of the next column
		}
		model.setRow(6, array7); // last column of model gets the value from array7 in param

		return array;
	}
	public Array7 shiftDown(Array7x7 model, Array7 array7 ){
		Array7 array = new Array7();
		array = model.getRow(6);
		for(int row = model.getLength()-1; row>0; row--){
			model.setRow(row, model.getRow(row-1));
		}
		model.setRow(0, array7);
		return array;
	}
	public Array7 shift(Array7x7 model, Array7 array7, Controller.DIRECTION dir) {
		switch(dir) {
		case RIGHT:
			return shiftRight(model, array7);
		case UP:
			return shiftUp(model, array7);
		case DOWN:
			return shiftDown(model,array7);
		case LEFT:
		default:
			return shiftLeft(model, array7);
		}
		
	}
}
