package model;

import java.sql.*;
import java.util.ArrayList;

public class TemplateDao_DB implements IDaoTemplate {
	
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public void insert(TemplateVo template) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		String query = "DELETE FROM template WHERE name = '" + template.getTemplateName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			Boolean b = stmt.execute(query);
			stmt.close();
			if(b) {
				//TODO more gui windows -> shows that delete was successful 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		return 0;
	}

}
