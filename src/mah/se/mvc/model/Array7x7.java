package mah.se.mvc.model;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class Array7x7 {
    int[][] matrix;

    public Array7x7(int[][] matrix) {
        this.matrix = matrix;
        init();
    }

    public Array7x7() {
        matrix = new int[7][7];
        init();
    }

    private void init() {
        //fill with Zeros
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = col;
            }
        }

    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setElement(int row, int col, int value) {
        matrix[row][col] = value;
    }

    /**
     * Returnerar ett v�rde p� en specifik position
     *
     * @param row:
     *            vilken position man vill veta v�rdet i
     * @return : v�rdet man vill veta
     */
    public int getElement(int row,int col) {
        return matrix[row][col];
    }


    /**
     * Set the row with numbers
     * @param row
     * @param array7
     */
    public void setRow(int row, Array7 array7) {
        matrix[row] = array7.getAll();
    }

    /**
     * gets the row with numbers
     * @param row
     * @return
     */
    public int[] getRow(int row) {
        return matrix[row];
    }

    /**
     * Set the column with numbers
     * @param col
     * @param array
     */
    public void setCol(int col, Array7 array) {
        int i = 0;
        for (int row = 0; row < matrix.length; row++) {
            matrix[row][col] = array.getElement(i);
            i++;
        }
    }

    /**
     * get the column with numbers
     * @param col
     * @return
     */
    public Array7 getCol(int col) {
        int[] array = new int[7];
        int colIdx =0;
        for (int row = 0; row < matrix.length; row++) {
                array[colIdx] = matrix[row][col];
                colIdx++;
        }
        return new Array7(array);
    }

    public int getLength() {
        return this.matrix.length;
    }
}
