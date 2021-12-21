package de.thu.project.main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.ChangeChecklistView;
import view.ChangeTemplateView;
import view.ItemView;
//import view.LoginView;
import view.LoginView;
public class StartProject {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					/*
					 * For login and checklist creation GUI (true start of application):
					 */
					
//					LoginView window = new LoginView();
					LoginView window = new LoginView();
					window.setVisible(true);
					
					/*
					 * For checklist managing GUI (not fully implemented):
					 */
					
//					ChangeChecklistView window = new ChangeChecklistView();
//					window.setVisible(true);
					
					/*
					 * For template managing GUI (fully functional, but leads
					 * to add new item into global list of items GUI which isn't
					 * finished yet):
					 */
					
//					ChangeTemplateView window = new ChangeTemplateView();
//					window.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

}
