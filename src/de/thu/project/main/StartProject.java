package de.thu.project.main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.View;

public class StartProject {

	public static void main(String[] args) {
		//String url = "jdbc:mysql://localhost:3306/dsm_proj_01";
		//String user = "dsm_proj_01";
		//String pass = "Holy_Spirit01";
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

}
