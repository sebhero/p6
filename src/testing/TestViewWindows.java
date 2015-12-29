package testing;

import javax.swing.JOptionPane;
import testing.views.ViewWindows;

/**
 * Klass f�r att testa den grafiska milj�n
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
