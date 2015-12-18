package testing;
/**
 * Test av shiftArray
 * @author Johnatan Sona
 */

import mah.se.algorithms.ShiftArray;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;
import mah.se.patterns.strategy.FillNumbers;

public class TestShift {
	public static void main(String [] args){
		Array7x7 array = new Array7x7();
		FillNumbers fill = new FillNumbers();
		array = fill.fillWithInGaining();
		System.out.print(array.toString());
		ShiftArray a = new ShiftArray();
		Array7 shift = null;
		shift = a.shiftLeft(array, new Array7());

		System.out.println(array.toString());
		a.shiftRight(array, shift);
		System.out.println(array.toString());
		shift = a.shiftRight(array, new Array7());
		System.out.println(array.toString());
		a.shiftLeft(array, shift);
		System.out.println(array.toString());
		
		shift = a.shiftUp(array, new Array7());
		System.out.println(array.toString());
		a.shiftDown(array, shift);
		System.out.println(array.toString());
		a.shiftDown(array, new Array7());
		System.out.println(array.toString());
		a.shiftUp(array, shift);
		System.out.println(array.toString());
	}
}
