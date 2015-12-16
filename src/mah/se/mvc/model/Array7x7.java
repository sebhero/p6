package mah.se.mvc.model;

import java.util.Arrays;

public class Array7x7 {
	
	private int array[][] = new int[7][7];
    //private int[][] matrix;


    public Array7x7(int array[][]) {
		this.array = array;
	}
	
	public Array7x7() {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				array[i][j] = 0;
			}
		}
	}
	
	public void setRow(int row, Array7 theRow) {
		for(int i=0; i<array.length; i++) {
			array[row][i] = theRow.getElement(i);
		}
		
	}
	
	public Array7 getRow(int row) {
		Array7 arr = new Array7();
		for(int i = 0; i < array.length;i++){
			arr.setElement(i, this.getElement(row, i));
		}
		
		return arr;
	}
	
	public void setCol(int col, Array7 theCol) {
		for(int i=0; i<array.length; i++) {
			array[i][col] = theCol.getElement(i);
		}
			
	}
	
	public Array7 getCol(int col) {
		Array7 arr = new Array7();
		for(int i = 0; i < array.length; i++){
			arr.setElement(i, this.getElement(i, col));
		}
		return arr;
	
	}
	
	public int getElement(int row, int col){
		return this.array[row][col];
	}
	
	public void setElement(int row, int col, int value){
		this.array[row][col] = value;
	}

    public int[][] getAll() {
        return array;
    }

    public int getLength() {
        return array.length;
    }

    @Override
    public String toString() {
        String txt="";
        for (int[] row : array) {
            txt += Arrays.toString(row)+"\n";
        }
        return "Array7x7{\n" +
                txt+
                '}';
    }
}
