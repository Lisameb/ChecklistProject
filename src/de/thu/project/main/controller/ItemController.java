package de.thu.project.main.controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.item.CategoryVo;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.model.item.ItemVo;
import de.thu.project.main.view.ItemView;

/**********************************************
 *  
 * only admin has access
 * class checks if typed in item already exists in chosen category
 * 		saves item if not
 * 
 **********************************************/

public class ItemController implements ActionListener{

	private ItemView view;
	private ItemDao_DB itemDao;
	private DaoFactory daofactory = DaoFactory.getInstance();

	public ItemController(ItemView view) {
		this.view = view;
		itemDao = (ItemDao_DB) daofactory.getItemDao();
	}

	public void setComboBoxCat() {
		ArrayList<CategoryVo> catList = new ArrayList<>(); 
		catList = itemDao.getAllCategories();

		for(CategoryVo category : catList) {
			view.comboBoxCategory.addItem(category.getCategoryName());
		}
	}

	public boolean checkItems(String itemName) {
		ArrayList<String> allItems = itemDao.getAllItems();

		for (int i = 0; i < allItems.size(); i++) {
			if (itemName.equals(allItems.get(i))) {
				return false;
			}
		}
		return true;
	}
	public boolean checkCategory(String category) {
		ArrayList<CategoryVo> allCategories = itemDao.getAllCategories();

		for (int i = 0; i < allCategories.size(); i++) {
			if (category.equals(allCategories.get(i))) {
				return false;
			}
		}
		return true;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == view.btnAddCategoryTo) {
			String newCategory = view.tfCategory.getText().toLowerCase();
			
			if (view.tfCategory.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in a category!");
			}
			else if (checkCategory(newCategory)){
				itemDao.insertCategory(newCategory);
				JOptionPane.showMessageDialog(null,"Category " + newCategory +" added to database");
				view.tfCategory.setText("");
				setComboBoxCat();
			} else {
				JOptionPane.showMessageDialog(null,"Item " + newCategory +" already exists in database :(");
				view.tfCategory.setText("");
			}
		}

		if (src == view.btnAddItemTo) {
			String newItem = view.tfItem.getText().toLowerCase();
			
			if (view.tfCategory.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in an item!");
			} 
			else if (checkItems(newItem)){
				ItemVo item = new ItemVo(newItem);
				item.setCategory(view.comboBoxCategory.getSelectedItem().toString());
				itemDao.insert(item);
				view.tfItem.setText("");
				JOptionPane.showMessageDialog(null,"Item " + newItem +" added to database");
			} else {
				JOptionPane.showMessageDialog(null,"Item " + newItem +" already exists in database :(");
				view.tfItem.setText("");
			}
		}
	}
}

