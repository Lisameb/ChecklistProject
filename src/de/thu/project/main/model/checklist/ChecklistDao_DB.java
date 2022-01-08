package de.thu.project.main.model.checklist;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.thu.project.main.model.DaoFactory;

/********************************************** 
 * 
 * class interacts with table 'checklist' of our database
 * methods:
 * 		- insert new checklist
 * 		- delete checklist
 * 		- get checklistID
 * 		- get all checklist of an user
 * 		- change name of checklist
 * 
 **********************************************/

public class ChecklistDao_DB implements IDaoChecklist {
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	

	@Override
	public void insert(ChecklistVo checklist) {
		
		String query = "INSERT INTO checklist (name, user_name) VALUES (?,?)";
		
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, checklist.getChecklistName());
			stmt.setString(2, daofactory.getCurrent_user());
			
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e) {
			System.err.println("Insert failed!");
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean delete(ChecklistVo checklist) {
		
		
		String query = "DELETE FROM checklist WHERE checklist_ID = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setInt(1, checklist.getChecklistID());
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.err.println("Delete failed!");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getChecklistID(ChecklistVo checklist) {
		
		String query = "SELECT checklist_ID FROM checklist WHERE name = ? AND user_name = ?";
		
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, checklist.getChecklistName());
			stmt.setString(2, checklist.getUsername());
			ResultSet resultset = stmt.executeQuery();
			
			int checklist_id;
			if(resultset.next()) {
				checklist_id = resultset.getInt("checklist_ID");
				return checklist_id;
			}
			stmt.close();
	
		}catch(SQLException e) {
			System.err.println("Failed to find ID!");
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public ArrayList<String> getAllChecklist(String username) {
		
		String query = "SELECT * FROM checklist WHERE user_name = ?";
		ArrayList<String> allChecklist = new ArrayList<>();
		
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, username);
			ResultSet resultset = stmt.executeQuery();
			
			while(resultset.next()) {
				allChecklist.add(resultset.getString("name"));
			}
			stmt.close();
	
		}catch(SQLException e) {
			System.err.println("Failed to get All Checklists!");
			e.printStackTrace();
		}
		
		return allChecklist;
		
	}
	
	public void changeChecklistName(ChecklistVo old, String newName) {
		
		String query = "UPDATE checklist SET name = ? WHERE checklist_ID = ?";
		
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, newName);
			stmt.setInt(2, this.getChecklistID(old));
			
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException e) {
			System.err.println("Change Checklist name failed!");
			e.printStackTrace();
		}
		
	}

}
