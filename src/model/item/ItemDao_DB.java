package model.item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.DaoFactory;

/********************************************** 
 * 
 * class interacts with table 'item' of our database
 * methods:
 * 		- insert new item
 * 		- delete item
 * 		- update category of an item
 * 		- get ItemID
 * 		- geCategoryItems: get all items of a category
 * 		- get all items
 * 		- get all categories
 * 		- get CategoryID
 * 
 **********************************************/

public class ItemDao_DB implements IDaoItem {

	private DaoFactory daofactory = DaoFactory.getInstance();
	
	@Override
	public void insert(ItemVo item) {
		String query = "INSERT INTO item (name, category) VALUES ('" + item.getItemName() + "', '" + item.getCategory() + "')";
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
	public void delete(ItemVo item) {
		String query = "DELETE FROM item WHERE name = '" + item.getItemName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			Boolean b = stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
	}

	@Override
	public void updateCategory(ItemVo item, CategoryVo category) {
		String query = "UPDATE item SET category = '" + category + "' WHERE name = '" + item.getItemName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
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
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<String> getCategoryItems(String category) {
		String query = "SELECT * FROM item WHERE category = '" + category + "'";
		ArrayList<String> allItems = new ArrayList<>();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allItems.add(resultset.getString("name"));	
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("getCategoryItems fehlgeschlagen!");
			e.printStackTrace();
		}
		return allItems;
		
	}
	
	public ArrayList<String> getAllItems(){
		ArrayList<String> allItems = new ArrayList<>();
		String query = "SELECT * FROM item";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allItems.add(resultset.getString("name"));	
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("getAllItems fehlgeschlagen!");
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
	
	public String getCategoryID(ItemVo item) {

		String query = "SELECT category FROM item WHERE name = '" + item.getItemName() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			String category;
			if(resultset.next()) {
				category = resultset.getString("category");
				return category;
			}
		} catch (SQLException e) {
			System.err.println("getCategory fehlgeschlagen!");
			e.printStackTrace();
		}
		return null;
	}
	
}
