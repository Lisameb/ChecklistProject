package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.DaoFactory;
import model.item.CategoryVo;
import model.item.ItemDao_DB;
import model.item.ItemVo;
import view.ItemView;

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


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == view.btnAddItemTo) {
			String newItem = view.tfItem.getText().toLowerCase();
			
			if (checkItems(newItem)) {
				ItemVo item = new ItemVo(newItem);
				item.setCategory(view.comboBoxCategory.getSelectedItem().toString());
				itemDao.insert(item);
				view.tfItem.setText("");
				JOptionPane.showMessageDialog(null,"Item " + newItem +" added to database");
			}
			else {
				JOptionPane.showMessageDialog(null,"Item " + newItem +" already exists in database :(");
				view.tfItem.setText("");
			}
		}

	}

}

