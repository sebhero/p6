package mah.se.mvc.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mah.se.mvc.controller.Controller;
import mah.se.mvc.model.Array7x7;
import roffe.Color.ColorDisplay;

/**
 * 
 * @author Jonatan Fridsten
 *
 */
public class ViewWindows extends JPanel {
	private ColorDisplay colorDisplay;
    private Controller ctrl;

    public ViewWindows() {
    	colorDisplay = new ColorDisplay(1, 1);
	}
    /**
     * Uppdaterar view med en color matris
     * @param matrix matrisen po alla farg element
     */
    public void updateViewColor(int[][] matrix) {
        //TODO implement
    }

    /**
     * Uppdatera view med en siffer matris
     * @param matrix matris med siffror
     */
    public void updateView(int[][] matrix) {
        //TODO implement
    }

    /**
     * Satter controller till viewen som styr viewn. den skoter all
     * kommunikation med array7x7. hanterar knapptryckningar m.m.
     * @param ctrl sjalva controllern
     */
    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

}
