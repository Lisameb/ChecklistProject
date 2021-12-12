package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Checklist_itemDao_DB implements IDaoChecklist_item {
	
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public int getAmount(Checklist_itemVo checklist_item) {
		String query = "SELECT amount FROM checklist_item WHERE checklist_ID = "
						+ checklist_item.getChecklist_id() + " AND item_ID = "
						+ checklist_item.getItem_id();
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
	public boolean checkCombo(Checklist_itemVo checklist_item) {
		String query = "SELECT checklist_ID FROM checklist_item WHERE checklist_ID = "
						+ checklist_item.getChecklist_id() + " AND item_ID = "
						+ checklist_item.getItem_id();
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
	public void addItem(Checklist_itemVo checklist_item, int amountAdd) {
		if(this.checkCombo(checklist_item)) {
			
			String query = "UPDATE checklist_item SET amount = "
							+ amountAdd + " WHERE checklist_ID = "
							+ checklist_item.getChecklist_id() + " AND item_ID = "
							+ checklist_item.getItem_id();
			try {
				Statement stmt = daofactory.getCon().createStatement();
				stmt.execute(query);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("AddItem fehlgeschlagen!");
				e.printStackTrace();
			}
		} else {
			
			
			String query = "INSERT INTO checklist_item (item_ID, amount, checklist_ID) VALUES ("
							+ checklist_item.getItem_id() + ", "
							+ amountAdd + ","
							+ checklist_item.getChecklist_id() + ")";
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

	/*
	 * deleteItem needs a Checklist_itemVo with an amount value
	 * which determines the amount of items to be deleted from the
	 * checklist
	 * */
	@Override
	public void deleteItem(Checklist_itemVo checklist_item) {
		if(this.checkCombo(checklist_item)) {
			int amount = this.getAmount(checklist_item);
			amount -= checklist_item.getAmount();
			if(amount > 0) {
				String query = "UPDATE checklist_item SET amount = "
								+ amount + " WHERE checklist_ID = "
								+ checklist_item.getChecklist_id() + " AND item_ID = "
								+ checklist_item.getItem_id() + "";
				try {
					Statement stmt = daofactory.getCon().createStatement();
					stmt.execute(query);
					stmt.close();
				} catch (SQLException e) {
					System.err.println("deleteItem fehlgeschlagen!");
					e.printStackTrace();
				}
			} else {
				String query = "DELETE FROM checklist_item WHERE checklist_ID = "
								+ checklist_item.getChecklist_id() + " AND item_id = "
								+ checklist_item.getItem_id();
				try {
					Statement stmt = daofactory.getCon().createStatement();
					stmt.execute(query);
					stmt.close();
				} catch (SQLException e) {
					System.err.println("deleteItem fehlgeschlagen!");
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.println("The item was not in the list in the first place!!!");
			
		}

	}
	
	/*
	 * getItemsC only gets the item names in the checklist,
	 * not the amount.
	 * Use getAmount() from this class to get amount from database
	 * seperately per item
	 * 
	 * if you need to get item id with this method, then contact JK or LE
	 * we will change this method then
	 */

	@Override
	public ArrayList<String> getItemsC(int checklist_id) {
		String query = "SELECT i.name FROM item AS i INNER JOIN checklist_item AS ci ON i.item_ID = ci.item_ID WHERE ci.checklist_ID = "
						+ checklist_id;
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
			System.err.println("getItem fehlgeschlagen!");
			e.printStackTrace();
		}
		return null;
	}

}
