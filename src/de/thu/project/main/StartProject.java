package de.thu.project.main;

import java.awt.EventQueue;

//import view.LoginView;
import view.LoginView;

/********************************************** 
 * 
 * Class to start the application
 * 
 **********************************************/

public class StartProject {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginView window = new LoginView();
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

}
