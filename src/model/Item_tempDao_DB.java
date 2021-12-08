package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Item_tempDao_DB implements IDaoItem_temp {
	
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public int getAmount(Item_tempVo item_temp) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if(this.checkCombo(item_temp)) {
			//int amount = this.getAmount(item_temp);
			//amount++;
			
			String query = "UPDATE item_temp SET amount = " + amountAdd + " WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_ID = " + item_temp.getItem_id();
			try {
				Statement stmt = daofactory.getCon().createStatement();
				stmt.execute(query);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				System.err.println("AddItem fehlgeschlagen!");
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void deleteItem(Item_tempVo item_temp) {
		// TODO Auto-generated method stub
		if(this.checkCombo(item_temp)) {
			int amount = this.getAmount(item_temp);
			amount--;
			if(amount > 0) {
				String query = "UPDATE item_temp SET amount = " + amount + " WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_ID = " + item_temp.getItem_id() + "";
				try {
					Statement stmt = daofactory.getCon().createStatement();
					stmt.execute(query);
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println("deleteItem fehlgeschlagen!");
					e.printStackTrace();
				}
			} else {
				String query = "DELETE FROM item_temp WHERE template_ID = " + item_temp.getTemplate_id() + " AND item_id = " + item_temp.getItem_id();
				try {
					Statement stmt = daofactory.getCon().createStatement();
					stmt.execute(query);
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println("deleteItem fehlgeschlagen!");
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.println("Du dumm");
			
		}
		
	}

	@Override
	public ArrayList<String> getItemsT(int temp_id) {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			System.err.println("deleteItem fehlgeschlagen!");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
