package testing;

import java.util.Random;

import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

public class AntonTestDoc {
	
	public static void main (String[] args) {
		Array7x7 array = new Array7x7();
		Array7 arr = new Array7();
		
		Random rand = new Random();
		
		for(int i = 0; i<arr.getLength(); i++) {
			arr.setElement(i, rand.nextInt(10));
		}
		
		Array7 arr2 = new Array7();
		
		array.setRow(6, arr);
		
		arr2 = array.getRow(6);
		
		System.out.print(arr2.toString());
		
		
		
	}

}