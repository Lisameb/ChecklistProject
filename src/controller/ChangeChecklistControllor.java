package controller;

import java.awt.event.*;
import java.util.ArrayList;

import model.CategoryVo;
import model.ChecklistDao_DB;
import model.ChecklistVo;
import model.Checklist_itemDao_DB;
import model.Checklist_itemVo;
import model.DaoFactory;
import model.ItemDao_DB;
import model.ItemVo;
import model.Item_tempVo;
import model.TemplateVo;
import view.ChangeChecklistView;
import view.ItemView;


public class ChangeChecklistControllor implements ActionListener{

	private ChangeChecklistView view;
	private ChecklistDao_DB checklistDao;
	private ItemDao_DB itemDao;
	private Checklist_itemDao_DB checklistItemDao;
	private DaoFactory daofactory = DaoFactory.getInstance();

	public ChangeChecklistControllor(ChangeChecklistView view) {
		this.view = view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
		this.checklistItemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
	}

	public void setComboBoxCheck() {
		view.checkComboBox.removeAllItems();
		ArrayList<String> checkList = new ArrayList<>(); 
		checkList = checklistDao.getAllChecklist(daofactory.getCurrent_user());

		for(String name : checkList) {
			view.checkComboBox.addItem(name);
		}
	}

	public void setComboBoxCat() {
		ArrayList<CategoryVo> catList = new ArrayList<>(); 
		catList = itemDao.getAllCategories();

		for(CategoryVo category : catList) {
			view.comboBoxCat.addItem(category.getCategoryName());
		}

	}

	public void setComboBoxItems(String category) {
		view.comboBoxItems.removeAllItems();

		ArrayList<String> itemList = new ArrayList<>(); 
		itemList = itemDao.getCategoryItems(category);

		for(String name : itemList) {
			view.comboBoxItems.addItem(name);
		}
	}	

	public void addItemtoChecklist(String checklistName, String item, int amount) {

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checklistDao.getChecklistID(checklistVo));

		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));

		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checklistItemDao.addItem(checklistItem, amount);
	}
	
	public void deleteItemFromChecklist(String checklistName, String item) {

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checklistDao.getChecklistID(checklistVo));
		
		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));
		
		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checklistItemDao.deleteItem(checklistItem);
	}
	
	public void updateTextArea(String checklistName) {

		view.taChecklist.setText("");		
		ArrayList<String> list = new ArrayList<String>();
		int amount;	
		
		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		int checklist_id = checklistDao.getChecklistID(checklistVo);
		list = checklistItemDao.getItemsC(checklist_id);
		
		for(int i = 0; i < list.size(); i++) {
			ItemVo item  = new ItemVo(list.get(i));
			int item_id = itemDao.getItemID(item);
			Checklist_itemVo checkitem = new Checklist_itemVo(checklist_id, item_id);
			amount = checklistItemDao.getAmount(checkitem);
			view.taChecklist.append(amount + " " + list.get(i) +"\n");
			
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (view.checkComboBox == src) {
			String checklistName = (String) view.checkComboBox.getSelectedItem();
			updateTextArea(checklistName);
		}
		
		if (view.btnSave == src) {
			ChecklistVo checklist = new ChecklistVo(view.checkComboBox.getSelectedItem().toString(), daofactory.getCurrent_user());
			checklistDao.changeChecklistName(checklist, view.tfName.getText());
			setComboBoxCheck();
			view.checkComboBox.setSelectedItem(view.tfName.getText());
		}
		
		if(src == view.comboBoxCat) {
			String cat = (String)view.comboBoxCat.getSelectedItem();
			setComboBoxItems(cat);
		}
		
		if(src == view.btnAdd) {
			String checklist = (String)view.checkComboBox.getSelectedItem();
			String item = (String)view.comboBoxItems.getSelectedItem();
			int amount = 1;
			
			try {
				amount =  Integer.parseInt(view.tfAmount.getText());
			} catch(Exception ex) {
				amount = 1;
			}
			addItemtoChecklist(checklist, item, amount);
			updateTextArea(checklist);
		}
		
		if(src == view.btnDelete) {
			String checklist = (String)view.checkComboBox.getSelectedItem();
			String item = (String)view.comboBoxItems.getSelectedItem();
			
			deleteItemFromChecklist(checklist, item);
			updateTextArea(checklist);
			
		}
		
		if(src == view.btnDeleteChecklist) {
			ChecklistVo checklist = new ChecklistVo(view.checkComboBox.getSelectedItem().toString(), daofactory.getCurrent_user());
			checklist.setChecklistID(checklistDao.getChecklistID(checklist));
			checklistDao.delete(checklist);
			setComboBoxCheck();
			view.taChecklist.setText("");
		}
	}
}
