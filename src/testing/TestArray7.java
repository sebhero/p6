package testing;

import java.util.Random;

import mah.se.mvc.model.Array7;

public class TestArray7 {
	
	public static void main(String[] args) {
		Array7 arr = new Array7();
		for(int i=0; i<arr.getLength(); i++) {
			Random rand = new Random();
			arr.setElement(i, rand.nextInt(10));
		}
		System.out.println(arr.toString());
		System.out.println(arr.getElement(0));
		System.out.println(arr.getLength());

	}

}
