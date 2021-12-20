			
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.checklist_item.Checklist_itemVo;
import model.item.CategoryVo;
import model.item.ItemDao_DB;
import model.item.ItemVo;
import model.item_template.Item_tempDao_DB;
import model.item_template.Item_tempVo;
import model.template.TemplateDao_DB;
import model.template.TemplateVo;
import view.ChangeTemplateView;
import view.CreateTemplateView;
import view.ItemView;
import view.MenuView;

public class ChangeTemplateController implements  ActionListener{

	private ChangeTemplateView view;
	private TemplateDao_DB tempDao;
	private ItemDao_DB itemDao;
	private Item_tempDao_DB itemTempDao;
	private CreateTemplateView createView;
	
	public ChangeTemplateController (ChangeTemplateView view) {
		this.view = view;
		this.tempDao = new TemplateDao_DB();
		this.itemDao = new ItemDao_DB();
		this.itemTempDao = new Item_tempDao_DB();
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
		itemTempDao.addItem(itemTemp, amount);
	}
	public void deleteItemFromTemp(String template, String item) {
		
		TemplateVo tempVo = new TemplateVo(template);
		tempVo.setTemplateID(tempDao.getTemplateID(tempVo));
		
		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));
		
		Item_tempVo itemTemp = new Item_tempVo(tempVo, itemVo);
		itemTempDao.deleteItem(itemTemp);
	}
	public void updateTextArea(String template) {
		
		view.itemList.setText("");		
		ArrayList<String> list = new ArrayList<String>();
		int amount;
		
		TemplateVo temp = new TemplateVo(template);
		temp.setTemplateID(tempDao.getTemplateID(temp));
	
		list = itemTempDao.getItemsT(temp.getTemplateID()); 
		
		for(int i = 0; i < list.size(); i++) {
			ItemVo item  = new ItemVo(list.get(i));
			item.setItemID(itemDao.getItemID(item));
			Item_tempVo tempItem = new Item_tempVo(temp, item);
			amount = itemTempDao.getAmount(tempItem);
			
			view.itemList.append(amount + " " + list.get(i) + "\n");
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
			TemplateVo temp = new TemplateVo(createView.tfName.getText());
			if(tempDao.getTemplateID(temp) == 0) {
				tempDao.insert(temp);
				setComboBoxTemp();
				createView.dispose();
			} else {
				createView.tfNameExists.setText("Name already exists!");
			}
			view.comboBoxTemp.setSelectedItem(temp.getTemplateName());
			updateTextArea(temp.getTemplateName());
		}
		if(src == view.btnDeleteTemp) {
			TemplateVo temp = new TemplateVo(view.comboBoxTemp.getSelectedItem().toString());
			tempDao.delete(temp);
			setComboBoxTemp();
			view.itemList.setText("");
		}
		if(src == view.btnAddItemTo) {
			String temp = (String)view.comboBoxTemp.getSelectedItem();
			String item = (String)view.comboBoxItem.getSelectedItem();
			int amount;
			
			try {
				amount =  Integer.parseInt(view.textFieldAmount.getText());
			} catch(Exception ex) {
				amount = 1;
			}
			addItemtoTemp(temp, item, amount);
			updateTextArea(temp);
			view.textFieldAmount.setText("");
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
