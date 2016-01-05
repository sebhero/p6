package mah.se;

import java.lang.reflect.Method;

import javax.swing.JOptionPane;

public class ViewAllTheWindowsNow {
	
	public int getInt(String menu) {
		int choice = 0;
		boolean inputOK = false;
		
		do{
			try {
				choice = Integer.parseInt(JOptionPane.showInputDialog(menu));
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
				
		int choice = getInt(menu);
		try {
		while(choice != 0) {
			switch(choice) {
				case 1:
					runMain("mah.se.App");
					break;
				case 2:
					
				break;
				case 3:
				break;
				case 4:
				break;
				case 5:
				break;
				
				
				
			}
			choice = getInt(menu);
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
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
