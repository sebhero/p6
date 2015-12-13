package mah.se.mvc.model;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class Array7x7 {
    int[][] matrix;

    public Array7x7(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
}
