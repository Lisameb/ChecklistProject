package de.thu.project.main.model.template;

import java.sql.*;
import java.util.ArrayList;

import de.thu.project.main.model.DaoFactory;

/********************************************** 
 * 
 * class interacts with table 'template' of our database
 * methods:
 * 		- insert new template
 * 		- delete template
 * 		- get all templates
 * 		- get templateID
 * 		- get name of template by ID
 * 
 **********************************************/

public class TemplateDao_DB implements IDaoTemplate {
	
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public void insert(TemplateVo template) {
		
		String query = "INSERT INTO template (name) VALUES ('" + template.getTemplateName() + "')";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("Insert fehlgeschlagen!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(TemplateVo template) {

		String query = "DELETE FROM template WHERE name = '" + template.getTemplateName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			Boolean b = stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getAllTemplate() {
		String query = "SELECT * FROM template";
		ArrayList<String> allTemp = new ArrayList<>();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allTemp.add(resultset.getString("name"));	
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("getAll fehlgeschlagen!");
			e.printStackTrace();
		}
		return allTemp;
		
	}
	
	public int getTemplateID(TemplateVo template) {
		
		String query = "SELECT template_ID FROM template WHERE name = '" + template.getTemplateName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			int temp_id;
			if(resultset.next()) {
				temp_id = resultset.getInt("template_ID");
				return temp_id;
			}
		} catch (SQLException e) {
			System.err.println("Get ID fehlgeschlagen!");
			e.printStackTrace();
		}
		return 0;
	}
	public String getTemplateName(int temp_id) {
		String query = "SELECT name FROM template WHERE template_ID = " + temp_id;
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			String name;
			if(resultset.next()) {
				name = resultset.getString("name");
				return name;
			}
		} catch (SQLException e) {
			System.err.println("Get Name fehlgeschlagen!");
			e.printStackTrace();
		}
		return null;
	}

}
