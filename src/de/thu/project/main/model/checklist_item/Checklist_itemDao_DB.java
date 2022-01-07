package de.thu.project.main.model.checklist_item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.thu.project.main.model.DaoFactory;

/********************************************** 
 * 
 * class interacts with table 'checklist_item' of our database
 * methods:
 * 		- get amount of a item in the checklist
 * 		- checkCombo: check if item is already in the checklist
 * 		- add item to checklist + update amount
 * 		- delete item from checklist
 * 		- get all Items of a checklist
 * 		- toggleCheck: set if item is checked
 * 		- get boolean if item is checked
 * 
 **********************************************/

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

	@Override
	public void deleteItem(Checklist_itemVo checklist_item) {
		if(this.checkCombo(checklist_item)) {
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

		} else {
			System.out.println("The item was not in the list in the first place!!!");

		}

	}

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

	@Override
	public void toggleCheck(Checklist_itemVo checklist_item) {
		if(checklist_item.isChecked()) {

			String query = "UPDATE checklist_item SET checked = 1 WHERE checklist_ID = "
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


			String query = "UPDATE checklist_item SET checked = 0 WHERE checklist_ID = "
					+ checklist_item.getChecklist_id() + " AND item_ID = "
					+ checklist_item.getItem_id();
			try {
				Statement stmt = daofactory.getCon().createStatement();
				stmt.execute(query);
				stmt.close();
			} catch (SQLException e) {
				System.err.println("ToggleItem fehlgeschlagen!");
				e.printStackTrace();
			}

		}

	}
	public boolean getChecked(Checklist_itemVo checklist_item) {
		String query = "SELECT checked FROM checklist_item WHERE checklist_ID = "
				+ checklist_item.getChecklist_id() + " AND item_ID = "
				+ checklist_item.getItem_id();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			if(resultset.next()) {
				int checked = resultset.getInt("checked");
				if(checked == 1) {
					return true;
				} else {
					return false;
				}
			}
			stmt.close();

		} catch(SQLException e) {
			System.err.println("checkCombo fehlgeschlagen!");
			e.printStackTrace();
		}
		return false;
	}

}
