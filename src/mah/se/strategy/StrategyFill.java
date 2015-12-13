package mah.se.strategy;

import mah.se.mvc.controller.SHOW;
import mah.se.mvc.model.Array7;
import mah.se.mvc.model.Array7x7;

/**
 * Created by Sebastian Börebäck on 2015-12-13.
 */
public interface StrategyFill {

    public int[][] fill(Array7x7 matrix, int inData);

    int[][] shiftRight(Array7x7 model);

    int[][] fill(Array7x7 model, SHOW whatToShow);
}
