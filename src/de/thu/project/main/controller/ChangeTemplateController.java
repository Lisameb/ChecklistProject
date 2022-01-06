			
package de.thu.project.main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.item.CategoryVo;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.model.item.ItemVo;
import de.thu.project.main.model.item_template.Item_tempDao_DB;
import de.thu.project.main.model.item_template.Item_tempVo;
import de.thu.project.main.model.template.TemplateDao_DB;
import de.thu.project.main.model.template.TemplateVo;
import de.thu.project.main.view.ChangeTemplateView;
import de.thu.project.main.view.CreateTemplateView;
import de.thu.project.main.view.ItemView;
import de.thu.project.main.view.MenuView;

/********************************************** 
 * 
 * only admin has access
 * admin chooses a template and can show all items
 * class to 
 * 		- add item to a template, if item is already in the template, the amount is replaced, not added
 * 		- delete items of a template, regardless of amount
 * 		- delete template
 * 		- create a new template
 * 
 **********************************************/

public class ChangeTemplateController implements  ActionListener{

	private CreateTemplateView createView;
	private ChangeTemplateView view;
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	private TemplateDao_DB tempDao;
	private ItemDao_DB itemDao;
	private Item_tempDao_DB item_tempDao;
	
	
	public ChangeTemplateController (ChangeTemplateView view) {
		this.view = view;
		this.tempDao = (TemplateDao_DB) daofactory.getTemplateDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
		this.item_tempDao = (Item_tempDao_DB) daofactory.getItem_tempDao();
	}
	
	public void setComboBoxTemp() {
		view.comboBoxTemp.removeAllItems();
		ArrayList<String> tempList = new ArrayList<>(); 
		tempList = tempDao.getAllTemplate();
		
		for(String name : tempList) {
			view.comboBoxTemp.addItem(name);
		}
	}
	
	public void setComboBoxCat() {
		ArrayList<CategoryVo> catList = new ArrayList<>(); 
		catList = itemDao.getAllCategories();
		
		for(CategoryVo category : catList) {
			view.comboBoxCategory.addItem(category.getCategoryName());
		}
		
	}
	public void setComboBoxItem(String category) {
		view.comboBoxItem.removeAllItems();
		
		ArrayList<String> itemList = new ArrayList<>(); 
		itemList = itemDao.getCategoryItems(category);
		
		for(String name : itemList) {
			view.comboBoxItem.addItem(name);
		}
	}
	
	public void addItemtoTemp(String template, String item, int amount) {
		
		TemplateVo tempVo = new TemplateVo(template);
		tempVo.setTemplateID(tempDao.getTemplateID(tempVo));
		
		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));
		
		Item_tempVo itemTemp = new Item_tempVo(tempVo, itemVo);
		item_tempDao.addItem(itemTemp, amount);
	}
	public void deleteItemFromTemp(String template, String item) {
		
		TemplateVo tempVo = new TemplateVo(template);
		tempVo.setTemplateID(tempDao.getTemplateID(tempVo));
		
		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));
		
		Item_tempVo itemTemp = new Item_tempVo(tempVo, itemVo);
		item_tempDao.deleteItem(itemTemp);
	}
	public void updateTextArea(String template) {
		
		view.taItemList.setText("");		
		ArrayList<String> list = new ArrayList<String>();
		int amount;
		
		TemplateVo temp = new TemplateVo(template);
		temp.setTemplateID(tempDao.getTemplateID(temp));
	
		list = item_tempDao.getItemsT(temp.getTemplateID()); 
		
		for(int i = 0; i < list.size(); i++) {
			ItemVo item  = new ItemVo(list.get(i));
			item.setItemID(itemDao.getItemID(item));
			Item_tempVo tempItem = new Item_tempVo(temp, item);
			amount = item_tempDao.getAmount(tempItem);
			
			view.taItemList.append(amount + " " + list.get(i) + "\n");
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == view.btnShowItems) {
			String template = (String)view.comboBoxTemp.getSelectedItem();
			updateTextArea(template);
		}
		if(src == view.btnAddNewItem) {
			ItemView iView = new ItemView(view);
			iView.setVisible(true);
		}
		if(src == view.btnNewTemp) {
			createView = new CreateTemplateView(this);
			createView.setVisible(true);
			
		}
		if(createView!=null && src == createView.btnNew) {
			if(createView.tfName.getText().equals("")) {
				createView.lblNameError.setText("Type in a name first!");
			} else {
				TemplateVo temp = new TemplateVo(createView.tfName.getText().toLowerCase());
				if(tempDao.getTemplateID(temp) == 0) {
					tempDao.insert(temp);
					setComboBoxTemp();
					createView.dispose();
				} else {
					createView.lblNameError.setText("Name already exists!");
				}
				view.comboBoxTemp.setSelectedItem(temp.getTemplateName());
				updateTextArea(temp.getTemplateName());
			}
			
		}
		if(src == view.btnDeleteTemp) {
			TemplateVo temp = new TemplateVo(view.comboBoxTemp.getSelectedItem().toString());
			tempDao.delete(temp);
			setComboBoxTemp();
			view.taItemList.setText("");
		}
		if(src == view.btnAddItemTo) {
			String temp = (String)view.comboBoxTemp.getSelectedItem();
			String item = (String)view.comboBoxItem.getSelectedItem();
			int amount;
			
			try {
				amount =  Integer.parseInt(view.tfAmount.getText());
			} catch(Exception ex) {
				amount = 1;
			}
			addItemtoTemp(temp, item, amount);
			updateTextArea(temp);
			view.tfAmount.setText("");
		}
		if(src == view.btnDeleteItemTo) {
			String temp = (String)view.comboBoxTemp.getSelectedItem();
			String item = (String)view.comboBoxItem.getSelectedItem();
			
			deleteItemFromTemp(temp, item);
			updateTextArea(temp);	
		}
		if(src == view.comboBoxCategory) {
			String cat = (String)view.comboBoxCategory.getSelectedItem();
			setComboBoxItem(cat);
		}
		if(src == view.btnBack) {
			MenuView mView = new MenuView();
			mView.setVisible(true);
			view.dispose();
		}
		
	}

}
