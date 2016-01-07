package testing;

/**
 * @author Anton
 */

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class ViewAllTheWindowsNow {
	
	public int getInt(String txt) {
		int choice = 0;
		boolean inputOK = false;
		
		do{
			try {
				choice = Integer.parseInt(JOptionPane.showInputDialog(txt));
				inputOK = true;
			}catch(Exception e) {
				
			}
		} while(!inputOK);
		return choice;
	}
	
	
	
	public void AllTheWindows(){
		Class cls;
		String menu = 
				"Välj vilken app som ska startas: \n \n"
				+ "1. Starta MrBigViewWindowsWithFlowText \n"
				+ "2. Starta ViewColor \n"
				+ "3. Starta ViewNumbers \n"
				+ "4. Starta ViewShiftTest \n"
				+ "5. Starta ViewImpl \n"
				+ "0. För att avsluta \n"
				+ "Ange ditt val";
				
		/*int choice = getInt(menu);
		while(choice != 0){
		try {
			switch(choice) {
				case 1:
					App.main(new String[]{null}, 1);
					break;
				case 2:
					App.main(new String[]{null}, 2);
					break;
				case 3:
					App.main(new String[]{null}, 3);
					break;
				case 4:
					App.main(new String[]{null}, 4);
					break;
				case 5:
				break;		
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		choice = getInt(menu);*/
		
	}
	
    public void runMain(String classname) throws Exception {
        Class cls = Class.forName(classname);
        Method method = cls.getDeclaredMethod("main",new Class[]{String[].class});
        method.invoke(null,new Object[]{null}); 
    }
	
	public static void main(String[] args) {
		ViewAllTheWindowsNow p3 = new ViewAllTheWindowsNow();
		p3.AllTheWindows();
	}

}
