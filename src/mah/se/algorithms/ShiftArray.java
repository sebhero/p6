package mah.se.algorithms;

import java.util.Arrays;

/**
 *Moves the Arrays 1 step at a time in the chosen direction. Main focus is shifting left.
 *@author Robin Johnsson
 **/
public class ShiftArray {
	
	public int[][] shiftLeft(int[][] array){
		//Goes through the first column, to the last
		for (int col = 0; col < array.length; col++) {
			//Goes throught the first row, then second, and lastly the last row.
			  for (int row = 0; row < array.length; row++) {
				  //shifts the value of next column onto "active" column
				  if(col!=array.length-1){
					  array[row][col] = array[row][col+1];
				  }
				  //if it's the last column, changes the value of the column to 0.
				  else if(col==array.length-1){
					  array[row][col]=0;
				  }
			  }
			}
		return array;
	}
	public static void main(String[] args){

		int[][] arr2 = {{1,1,1,1,1,1,1},{2,2,2,2,2,2,2},{3,3,3,3,3,3,3},{4,4,4,4,4,4,4},{5,5,5,5,5,5,5},{6,6,6,6,6,6,6},{7,7,7,7,7,7,7}};
		ShiftArray test = new ShiftArray();
		for(int i = 0; i<arr2.length+1; i++){

			
				for(int j = 0; j<arr2.length; j++){
					System.out.println(Arrays.toString(arr2[j]));
				}
			
			test.shiftLeft(arr2);
			System.out.println();
		}
	}
}
