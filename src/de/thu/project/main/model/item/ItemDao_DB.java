package de.thu.project.main.model.item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.thu.project.main.model.DaoFactory;

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
		String query = "INSERT INTO item (name, category) VALUES (?, ?)";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, item.getItemName());
			stmt.setString(2, item.getCategory());
			stmt.executeUpdate(query);
			stmt.close();
			
		} catch(SQLException e) {
			System.err.println("Insert failed!");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ItemVo item) {
		String query = "DELETE FROM item WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, item.getItemName());
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Delete failed!");
			e.printStackTrace();
		}
	}

	@Override
	public void updateCategory(ItemVo item, CategoryVo category) {
		String query = "UPDATE item SET category = ? WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, category.getCategoryName());
			stmt.setString(2, item.getItemName());
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Update failed!");
			e.printStackTrace();
		}
	}
	
	@Override
	public int getItemID(ItemVo item) {
		
		String query = "SELECT item_ID FROM item WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, item.getItemName());
			ResultSet resultset = stmt.executeQuery(query);
			int item_id;
			if(resultset.next()) {
				item_id = resultset.getInt("item_ID");
				return item_id;
			}
		} catch (SQLException e) {
			System.err.println("Delete failed!");
			e.printStackTrace();
		}
		return 0;
	}
	
	public ArrayList<String> getCategoryItems(String category) {
		String query = "SELECT * FROM item WHERE category = ?";
		ArrayList<String> allItems = new ArrayList<>();
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, category);
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allItems.add(resultset.getString("name"));	
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Get Category Items failed!");
			e.printStackTrace();
		}
		return allItems;
		
	}
	
	public ArrayList<String> getAllItems(){
		String query = "SELECT * FROM item";
		ArrayList<String> allItems = new ArrayList<>();
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			while(resultset.next()) {
				allItems.add(resultset.getString("name"));	
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Get all Items failed!");
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
			System.err.println("Get all Categories failed!");
			e.printStackTrace();
		}
		return allCat;
	}
	
	public String getCategoryID(ItemVo item) {

		String query = "SELECT category FROM item WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, item.getItemName());
			ResultSet resultset = stmt.executeQuery(query);
			String category;
			if(resultset.next()) {
				category = resultset.getString("category");
				return category;
			}
		} catch (SQLException e) {
			System.err.println("Get Category failed!");
			e.printStackTrace();
		}
		return null;
	}
	
}
