package mah.se.mvc.model;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public class ModelMatrix7x7 {

    Array7x7 theMatrix;

    public ModelMatrix7x7(Array7x7 theMatrix) {
        this.theMatrix = theMatrix;
    }

    public Array7x7 getTheMatrix() {
        return theMatrix;
    }

    public void setTheMatrix(Array7x7 theMatrix) {
        this.theMatrix = theMatrix;
    }
}
