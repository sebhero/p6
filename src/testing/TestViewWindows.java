package testing;

import javax.swing.JOptionPane;
import mah.se.mvc.view.ViewWindows;

/**
 * Klass för att testa den grafiska miljön
 * 
 * @author jonatan
 *
 */
public class TestViewWindows {
	public static void main(String[] args) {
//		Controller cont = new Controller();
		ViewWindows wind = new ViewWindows(1, 1);
		JOptionPane.showMessageDialog(null, wind);
	}
}
