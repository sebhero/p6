package mah.se.mvc.model;

import java.util.Arrays;

/**
 * Skapar ett Array7x7 objekt som sedan kan fyllas med siffror/färger o s v.
 * 
 * @author Anton
 *
 */

public class Array7x7 {

	private int array[][] = new int[7][7];

	public Array7x7(int array[][]) {
		this.array = array;
	}

	/**
	 * Skapar ett array7x7 objekt fylld med 0:or
	 */

	public Array7x7() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = 0;
			}
		}
	}

	/**
	 * Fyller ett array7x7 objekt med parameter
	 * 
	 * @param fillWith
	 *            vad array7x7 objektet ska fyllas med
	 */
	public Array7x7(int fillWith) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = fillWith;
			}
		}
	}

	/**
	 * Sätter en rad i array7x7 objektet
	 * 
	 * @param row
	 *            vilken rad som ska fyllas
	 * @param theRow
	 *            en rad med siffror som läggs in i array7x7
	 */
	public void setRow(int row, Array7 theRow) {
		for (int i = 0; i < array.length; i++) {
			array[row][i] = theRow.getElement(i);
		}

	}

	/**
	 * Returnerar ett array7 objektet fylld med siffrorna från rad som man har
	 * angett
	 * 
	 * @param row
	 *            vilken rad
	 * @return arr array7 objekt fylld med siffrorna från raden
	 */
	public Array7 getRow(int row) {
		Array7 arr = new Array7();
		for (int i = 0; i < array.length; i++) {
			arr.setElement(i, this.getElement(row, i));
		}

		return arr;
	}

	/**
	 * Fyller en kolumn i array7x7 objekt
	 * 
	 * @param col
	 *            vilken kolumn som ska fyllas
	 * @param theCol
	 *            array7 objekt som skrivs in i array7x7 på kolumnen som man har
	 *            matat in
	 */

	public void setCol(int col, Array7 theCol) {
		for (int i = 0; i < array.length; i++) {
			array[i][col] = theCol.getElement(i);
		}

	}

	/**
	 * Returnerar kolumn
	 * 
	 * @param col
	 *            vilken kolumn som ska returneras
	 * @return arr array7 objekt fylld med kolumnen
	 */
	public Array7 getCol(int col) {
		Array7 arr = new Array7();
		for (int i = 0; i < array.length; i++) {
			arr.setElement(i, this.getElement(i, col));
		}
		return arr;

	}

	/**
	 * Returnerar ett element ur array7x7 objekt
	 * @param row vilken rad
	 * @param col vilken kolumn
	 * @return elementet på [rad][kolumn]
	 */
	
	public int getElement(int row, int col) {
		return this.array[row][col];
	}
	/**
	 * Sätter ett element i array7x7 objekt
	 * @param row vilken rad
	 * @param col vilken kolumn
	 * @param value vad som ska sättas in i array7x7
	 */
	public void setElement(int row, int col, int value) {
		this.array[row][col] = value;
	}
	/**
	 * Returnerar hela array7x7 objektet
	 * @return array ett helt array7x7 objekt
	 */
	public int[][] getAll() {
		return array;
	}
	/**
	 * Returnerar längden på array7x7 objektet
	 * @return array.length längden på objektet
	 */
	public int getLength() {
		return array.length;
	}
	
	/**
	 * Används för testning av klassen
	 * @return returnerar textsträng som innehåller hela array7x7 objektet. Kan sedan skrivas ut
	 */
	@Override
	public String toString() {
		String txt = "";
		for (int[] row : array) {
			txt += Arrays.toString(row) + "\n";
		}
		return "Array7x7{\n" + txt + '}';
	}
}
