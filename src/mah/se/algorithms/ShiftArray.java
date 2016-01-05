package mah.se.algorithms;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

/**
 *Klass som hanterar algoritmerna för att förflytta matriserna till; höger, vänster, upp och ner.
 *@author Johnatan Sona, Robin Johnsson
 **/
public class ShiftArray{
	/**
	 * Metod som skiftar matrisen till vänster.
	 * @param model, Matrisen som ska flyttas.
	 * @param array7, Nästa kolumn som ska visas.
     * @return skikar tillbaka den kolumn som "trillar ut".
     */
	public Array7 shiftLeft(Array7x7 model, Array7 array7){
		Array7 array = model.getCol(0);
		for(int col = 0; col<model.getLength()-1; col++){
			model.setCol(col, model.getCol(col+1));
			
		}
		model.setCol(6, array7);
		
		return array;
	}
	/**
	 * Metod som skiftar matrisen till höger.
	 * @param model, Matrisen som ska flyttas.
	 * @param array7, Nästa kolumn som ska visas.
	 * @return skikar tillbaka den kolumn som "trillar ut".
	 */
	public Array7 shiftRight(Array7x7 model, Array7 array7 ){
		Array7 array = model.getCol(6);
		for(int col = model.getLength()-1; col>0; col--){
			model.setCol(col, model.getCol(col-1));
		}
		model.setCol(0, array7);
		return array;
	}
	/**
	 * Metod som skiftar matrisen uppåt.
	 * @param model, Matrisen som ska flyttas.
	 * @param array7, Nästa rad som ska visas.
	 * @return skikar tillbaka den rad som "trillar ut".
	 */
	public Array7 shiftUp(Array7x7 model, Array7 array7){
		Array7 array = model.getRow(0);
		for(int row = 0; row<model.getLength()-1; row++){
			model.setRow(row, model.getRow(row+1));
			//Current column gets thevalue of the next column
		}
		model.setRow(6, array7); // last column of model gets the value from array7 in param

		return array;
	}
	/**
	 * Metod som skiftar matrisen nedåt.
	 * @param model, Matrisen som ska flyttas.
	 * @param array7, Nästa rad som ska visas.
	 * @return skikar tillbaka den rad som "trillar ut".
	 */
	public Array7 shiftDown(Array7x7 model, Array7 array7 ){
		Array7 array = model.getRow(6);
		for(int row = model.getLength()-1; row>0; row--){
			model.setRow(row, model.getRow(row-1));
		}
		model.setRow(0, array7);
		return array;
	}

	/**
	 * Metod som tar indata och skickar denna till korrekt riktnings-metod.
	 * @param model, Matrisen som ska flyttas.
	 * @param array7, Nästa kolumn eller rad som ska visas.
	 * @param dir, Riktning som allt ska flyttas.
     * @return Skickar tillbaka den kolumn/rad som trillat ut.
     */
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
