package model.item_template;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DaoFactory;

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
		String query = "SELECT amount FROM item_temp WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_ID = " + item_temp.getItem_id();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			int amount;
			if(resultset.next()) {
				amount = resultset.getInt("amount");
				return amount;
			}
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("getAmount fehlgeschlagen!");
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean checkCombo(Item_tempVo item_temp) {
		String query = "SELECT template_ID FROM item_temp WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_ID = " + item_temp.getItem_id();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			if(resultset.next()) {
				return true;
			}
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("checkCombo fehlgeschlagen!");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void addItem(Item_tempVo item_temp, int amountAdd) {
		if(this.checkCombo(item_temp)) {
			
			String query = "UPDATE item_temp SET amount = " + amountAdd + " WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_ID = " + item_temp.getItem_id();
			try {
				Statement stmt = daofactory.getCon().createStatement();
				stmt.execute(query);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("AddItem fehlgeschlagen!");
				e.printStackTrace();
			}
		} else {
			
			
			String query = "INSERT INTO item_temp (item_ID, amount, template_ID) VALUES (" + item_temp.getItem_id() + ", " + amountAdd + "," + item_temp.getTemplate_id() + ")";
			try {
				Statement stmt = daofactory.getCon().createStatement();
				stmt.execute(query);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("AddItem fehlgeschlagen!");
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void deleteItem(Item_tempVo item_temp) {
		if(this.checkCombo(item_temp)) {
				String query = "DELETE FROM item_temp WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_id = " + item_temp.getItem_id();
				try {
					Statement stmt = daofactory.getCon().createStatement();
					stmt.execute(query);
					stmt.close();
				} catch (SQLException e) {
					System.err.println("deleteItem fehlgeschlagen!");
					e.printStackTrace();
				}
			
			
		} else {
			System.out.println("The item is not in the list in the first place!!!");
			
		}
		
	}

	@Override
	public ArrayList<String> getItemsT(int temp_id) {
		String query = "SELECT i.name FROM item AS i INNER JOIN item_temp AS it ON i.item_ID = it.item_ID WHERE it.template_ID = " + temp_id;
		String itemname = "";
		ArrayList<String> itemnames = new ArrayList<String>(); 
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				itemname = resultset.getString("i.name");
				itemnames.add(itemname);
			}
			stmt.close();
			return itemnames;
		} catch (SQLException e) {
			System.err.println("deleteItem fehlgeschlagen!");
			e.printStackTrace();
		}
		return null;
	}
}
