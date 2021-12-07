package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDao_DB implements IDaoItem {

	private DaoFactory daofactory = DaoFactory.getInstance();
	
	@Override
	public void insert(ItemVo item) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO item (name, category) VALUES ('" + item.getItemName() + "', '" + item.getCategory() + "')";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.executeQuery(query);
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("Insert fehlgeschlagen!");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ItemVo item) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM item WHERE name = '" + item.getItemName() + "'";
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

	@Override
	public void updateCategory(ItemVo item, CategoryVo category) { // überprüfen, ob database mit der Syntax klarkommt -> sonst mit category.getName() Name holen
		// TODO Auto-generated method stub
		String query = "UPDATE item SET category = '" + category + "' WHERE name = '" + item.getItemName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Update fehlgeschlagen!");
			e.printStackTrace();
		}
	}
	
	@Override
	public int getItemID(ItemVo item) {
		
		String query = "SELECT item_ID FROM item WHERE name = '" + item.getItemName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			int item_id;
			if(resultset.next()) {
				item_id = resultset.getInt("item_ID");
				return item_id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<String> getCategoryItems(CategoryVo category) {
		String query = "SELECT * FROM item WHERE category = '" + category.getCategoryName() + "'";
		ArrayList<String> allItems = new ArrayList<>();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allItems.add(resultset.getString("name"));	
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("getAll fehlgeschlagen!");
			e.printStackTrace();
		}
		return allItems;
		
	}
	
	public ArrayList<CategoryVo> getAllCategories() {
		String query = "SELECT * FROM category";
		ArrayList<CategoryVo> allCat = new ArrayList<>();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allCat.add(new CategoryVo(resultset.getString("name")));	
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("getAll fehlgeschlagen!");
			e.printStackTrace();
		}
		return allCat;
	}	
	
}
