package model.checklist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DaoFactory;

public class ChecklistDao_DB implements IDaoChecklist {
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	

	@Override
	public void insert(ChecklistVo checklist) {
		String query = "INSERT INTO checklist (name, user_name) VALUES ('"
						+ checklist.getChecklistName() + "', '" + daofactory.getCurrent_user()
						+ "')";
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
	public boolean delete(ChecklistVo checklist) {
		String query = "DELETE FROM checklist WHERE checklist_ID = " + checklist.getChecklistID();
		Boolean b = false;
		try {
			Statement stmt = daofactory.getCon().createStatement();
			b = stmt.execute(query);
			stmt.close();
			return b;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public int getChecklistID(ChecklistVo checklist) {
		String query = "SELECT checklist_ID FROM checklist WHERE name = '"
						+ checklist.getChecklistName() + "' AND user_name = '"
						+ checklist.getUsername() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			int checklist_id;
			if(resultset.next()) {
				checklist_id = resultset.getInt("checklist_ID");
				return checklist_id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<String> getAllChecklist(String username) {
		String query = "SELECT * FROM checklist WHERE user_name = '"
						+ username + "'";
		ArrayList<String> allChecklist = new ArrayList<>();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allChecklist.add(resultset.getString("name"));
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("getAll fehlgeschlagen!");
			e.printStackTrace();
		}
		return allChecklist;
		
	}
	
	public void changeChecklistName(ChecklistVo old, String newName) {
		String query = "UPDATE checklist SET name = '" + newName + "' WHERE checklist_ID = "
						+ this.getChecklistID(old);
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("changeChecklistName fehlgeschlagen!");
			e.printStackTrace();
		}
	}

}
