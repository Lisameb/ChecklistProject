package de.thu.project.main.controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.item.CategoryVo;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.model.item.ItemVo;
import de.thu.project.main.view.ItemView;
import de.thu.project.main.view.MenuView;
import de.thu.project.main.view.TemplateView;
import de.thu.project.main.view.UseChecklistView;

/**********************************************
 *  
 * only admin has access
 * class checks if typed in item already exists in chosen category
 * 		saves item if not
 * 
 **********************************************/

public class ItemController implements MouseListener, ActionListener {

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
			view.comboBoxCat.addItem(category.getCategoryName());
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
	public boolean checkCatItems(String category) {
		ArrayList<String> allCatItems = itemDao.getCategoryItems(category);
		if(allCatItems.size() <= 0) {
			return true;
		}
		return false;
	}
	public void setGloballist() {
		ArrayList<String> allItems = itemDao.getAllItems();
		for (int i = 0; i < allItems.size(); i++) {
			view.taGloballist.append("-" + allItems.get(i) + "\n");
		}	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == view.btnAddCategory) {
			String newCategory = view.tfAddCategory.getText().toLowerCase();
			
			if (view.tfAddCategory.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in a category!");
			}
			else if (checkCategory(newCategory)){
				itemDao.insertCategory(newCategory);
				JOptionPane.showMessageDialog(null,"Category " + newCategory +" added to database");
				view.tfAddCategory.setText("");
				setComboBoxCat();
			} else {
				JOptionPane.showMessageDialog(null,"Item " + newCategory +" already exists in database :(");
				view.tfAddCategory.setText("");
			}
		}
		if (src == view.btnDeleteCategory) {
			String category = view.tfAddCategory.getText().toLowerCase();
			
			if (view.tfAddCategory.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in an item!");
			}else if (checkCategory(category)){
				if(checkCatItems(category)) {
					itemDao.deleteCategory(category);
					view.tfAddCategory.setText("");
					JOptionPane.showMessageDialog(null,"Category " + category +" deleted from database");
					setComboBoxCat();
				} else {
					JOptionPane.showMessageDialog(null,"Category " + category +" can't be deleted, still contains items");
					view.tfAddCategory.setText("");
				}
				
			}else {
				JOptionPane.showMessageDialog(null,"Category " + category +" doesn't exists in database :(");
				view.tfAddCategory.setText("");
			}
		}

		if (src == view.btnAdd) {
			String newItem = view.tfNewItem.getText().toLowerCase();
			
			if (view.tfNewItem.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in an item!");
			} 
			else if (checkItems(newItem)){
				ItemVo item = new ItemVo(newItem);
				item.setCategory(view.comboBoxCat.getSelectedItem().toString());
				itemDao.insert(item);
				view.tfNewItem.setText("");
				JOptionPane.showMessageDialog(null,"Item " + newItem +" added to database");
				setGloballist();
			} else {
				JOptionPane.showMessageDialog(null,"Item " + newItem +" already exists in database :(");
				view.tfNewItem.setText("");
			}
		}
		if (src == view.btnDelete) {
			String item = view.tfNewItem.getText().toLowerCase();
			
			if (view.tfNewItem.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in an item!");
			}else if (!checkItems(item)){
				ItemVo itemVo = new ItemVo(item);
				itemDao.delete(itemVo);
				view.tfNewItem.setText("");
				JOptionPane.showMessageDialog(null,"Item " + item +" deleted from database");
				setGloballist();
			}else {
				JOptionPane.showMessageDialog(null,"Item " + item +" doesn't exist in database :(");
				view.tfNewItem.setText("");
			}
		}
		if (src == view.btnChangeCategory) {
			String item = view.tfNewItem.getText().toLowerCase();
			
			if (view.tfNewItem.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in an item!");
			} 
			else if (!checkItems(item)){
				ItemVo itemVo = new ItemVo(item);
				String category = view.comboBoxCat.getSelectedItem().toString();
				itemDao.updateCategory(itemVo, category);
				view.tfNewItem.setText("");
				JOptionPane.showMessageDialog(null,"Category of Item " + item +" successfully changed in database");
				setGloballist();
			} else {
				JOptionPane.showMessageDialog(null,"Item " + item +" doesn't exist in database :(");
				view.tfNewItem.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		
		if(src == view.panMenTemp) {
			TemplateView tView = new TemplateView();
			tView.setVisible(true);
			view.dispose();
		}
		
		if(src == view.panMenCheck) {
			UseChecklistView uclView = new UseChecklistView();
			uclView.setVisible(true);
			view.dispose();
		}
		if(src == view.panMenBack) {
			MenuView mView = new MenuView();
			mView.setVisible(true);
			view.dispose();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

