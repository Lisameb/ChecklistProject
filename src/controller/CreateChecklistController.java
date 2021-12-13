package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.CategoryVo;
import model.ChecklistDao_DB;
import model.ChecklistVo;
import model.Checklist_itemDao_DB;
import model.Checklist_itemVo;
import model.DaoFactory;
import model.ItemDao_DB;
import model.ItemVo;
import model.Item_tempDao_DB;
import model.Item_tempVo;
import model.TemplateDao_DB;
import model.TemplateVo;
import view.CreateChecklistView;
import view.CreateNewChecklistView;

public class CreateChecklistController implements ActionListener {

	int temp_id;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private Item_tempDao_DB itemTemp;
	private Checklist_itemDao_DB checkItem;
	private ChecklistDao_DB checkDao;
	private ItemDao_DB itemDao;
	private TemplateDao_DB tempDao;
	private CreateChecklistView view;
	private CreateNewChecklistView newView;
	
	public CreateChecklistController(int temp_id) {
		this.temp_id = temp_id;
		newView = new CreateNewChecklistView(this);
		newView.setVisible(true);
		itemTemp = (Item_tempDao_DB) daofactory.getItem_tempDao();
		checkItem = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
		checkDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		itemDao = (ItemDao_DB) daofactory.getItemDao();
		tempDao = (TemplateDao_DB) daofactory.getTemplateDao();
	}
	
	public void createNewChecklist() {
		ChecklistVo checkVo = new ChecklistVo(newView.tfName.getText(), daofactory.getCurrent_user());
		checkDao.insert(checkVo);
	}
	
	public void saveAllTempItems(int temp_id) {
		
		ArrayList<String> list=new ArrayList<String>();
		list = itemTemp.getItemsT(temp_id);	
		ChecklistVo checkVo = new ChecklistVo(newView.tfName.getText(), daofactory.getCurrent_user());
		checkVo.setChecklistID(checkDao.getChecklistID(checkVo));
		
		
		for(int i = 0; i < list.size(); i++) {
			ItemVo item = new ItemVo(list.get(i));
			item.setItemID(itemDao.getItemID(item));
			TemplateVo tempVo = new TemplateVo(temp_id, tempDao.getTemplateName(temp_id));
			Item_tempVo itemTempVo = new Item_tempVo(tempVo, item);
			int amount = itemTemp.getAmount(itemTempVo);
			
			Checklist_itemVo checkItemVo = new Checklist_itemVo(checkVo.getChecklistID(), item.getItemID());
			checkItemVo.setAmount(amount);
			checkItem.addItem(checkItemVo, amount);
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
		checklistVo.setChecklistID(checkDao.getChecklistID(checklistVo));

		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));

		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checkItem.addItem(checklistItem, amount);
	}
	
	public void deleteItemFromChecklist(String checklistName, String item) {

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checkDao.getChecklistID(checklistVo));
		
		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));
		
		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checkItem.deleteItem(checklistItem);
	}
	
	public void updateTextArea(String checklistName) {

		view.taChecklist.setText("");		
		ArrayList<String> list = new ArrayList<String>();
		int amount;	
		
		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		int checklist_id = checkDao.getChecklistID(checklistVo);
		list = checkItem.getItemsC(checklist_id);
		
		for(int i = 0; i < list.size(); i++) {
			ItemVo item  = new ItemVo(list.get(i));
			int item_id = itemDao.getItemID(item);
			Checklist_itemVo checkitem = new Checklist_itemVo(checklist_id, item_id);
			amount = checkItem.getAmount(checkitem);
			view.taChecklist.append(amount + " " + list.get(i) +"\n");
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == newView.btnNew) {
			createNewChecklist();
			String name = newView.tfName.getText();
			view = new CreateChecklistView(this);
			view.lblName.setText(name);
			view.setVisible(true);
			newView.dispose();
			saveAllTempItems(temp_id);
			setComboBoxCat();
			updateTextArea(name);
		}
		if(src == view.comboBoxCat) {
			String cat = (String)view.comboBoxCat.getSelectedItem();
			setComboBoxItems(cat);
		}
		
		if(src == view.btnAdd) {
			String checklist = view.lblName.getText();
			String item = (String)view.comboBoxItems.getSelectedItem();
			int amount = 1;
			
			try {
				amount =  Integer.parseInt(view.tfAmount.getText());
			} catch(Exception ex) {
				amount = 1;
			}
			addItemtoChecklist(checklist, item, amount);
			updateTextArea(checklist);
			view.tfAmount.setText("");
		}
		
		if(src == view.btnDelete) {
			String checklist = view.lblName.getText();
			String item = (String)view.comboBoxItems.getSelectedItem();
			
			deleteItemFromChecklist(checklist, item);
			updateTextArea(checklist);
			
		}
		
	}
	
}
