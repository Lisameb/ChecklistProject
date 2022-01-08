package de.thu.project.main.model.item_template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.thu.project.main.model.DaoFactory;

/********************************************** 
 * 
 * class interacts with table 'item_temp' of our database
 * methods:
 * 		- get amount of an item in a template
 * 		- checkCombo: check if item is in template
 * 		- add Item to template + update amount
 * 		- delete item from template
 * 		- get all items of a template
 * 
 **********************************************/

public class Item_tempDao_DB implements IDaoItem_temp {
	
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public int getAmount(Item_tempVo item_temp) {
		String query = "SELECT amount FROM item_temp WHERE template_ID = ? AND item_ID = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setInt(1, item_temp.getTemplate_id());
			stmt.setInt(2, item_temp.getItem_id());
			ResultSet resultset = stmt.executeQuery(query);
			int amount;
			if(resultset.next()) {
				amount = resultset.getInt("amount");
				return amount;
			}
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("Get Amount failed!");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean checkCombo(Item_tempVo item_temp) {
		String query = "SELECT template_ID FROM item_temp WHERE template_ID = ? AND item_ID = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setInt(1, item_temp.getTemplate_id());
			stmt.setInt(2, item_temp.getItem_id());
			ResultSet resultset = stmt.executeQuery(query);
			if(resultset.next()) {
				return true;
			}
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("Check Combination failed!");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void addItem(Item_tempVo item_temp, int amountAdd) {
		if(this.checkCombo(item_temp)) {
			
			String query = "UPDATE item_temp SET amount = ? WHERE template_ID = ? AND item_ID = ?";
			try {
				PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
				stmt.setInt(1, amountAdd);
				stmt.setInt(2, item_temp.getTemplate_id());
				stmt.setInt(3, item_temp.getItem_id());
				stmt.executeUpdate(query);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Add Item failed!");
				e.printStackTrace();
			}
		} else {
			
			String query = "INSERT INTO item_temp (item_ID, amount, template_ID) VALUES (?, ?, ?)";
			try {
				PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
				stmt.setInt(1, item_temp.getItem_id());
				stmt.setInt(2, amountAdd);
				stmt.setInt(3, item_temp.getTemplate_id());
				stmt.executeUpdate(query);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Add Item failed!");
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void deleteItem(Item_tempVo item_temp) {
		if(this.checkCombo(item_temp)) {
			
				String query = "DELETE FROM item_temp WHERE template_ID = ? AND item_id = ?";
				try {
					PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
					stmt.setInt(1, item_temp.getTemplate_id());
					stmt.setInt(2, item_temp.getItem_id());
					stmt.executeUpdate(query);
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Delete Item failed!");
					e.printStackTrace();
				}
			
			
		} else {
			System.out.println("The item is not in the list in the first place!!!");
			
		}
		
	}

	@Override
	public ArrayList<String> getItemsT(int temp_id) {
		String query = "SELECT i.name FROM item AS i INNER JOIN item_temp AS it ON i.item_ID = it.item_ID WHERE it.template_ID = ?";
		String itemname = "";
		ArrayList<String> itemnames = new ArrayList<String>(); 
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setInt(1, temp_id);
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				itemname = resultset.getString("i.name");
				itemnames.add(itemname);
			}
			stmt.close();
			return itemnames;
		} catch (SQLException e) {
			System.err.println("Get Items from Template failed!");
			e.printStackTrace();
		}
		return null;
	}
}
