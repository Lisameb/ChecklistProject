package de.thu.project.main.controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.checklist.ChecklistDao_DB;
import de.thu.project.main.model.checklist.ChecklistVo;
import de.thu.project.main.model.checklist_item.Checklist_itemDao_DB;
import de.thu.project.main.model.checklist_item.Checklist_itemVo;
import de.thu.project.main.model.item.CategoryVo;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.model.item.ItemVo;
import de.thu.project.main.view.ChangeChecklistView;
import de.thu.project.main.view.ItemView;
import de.thu.project.main.view.MenuView;
import de.thu.project.main.view.TemplateView;
import de.thu.project.main.view.UseChecklistView;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.checklist.*;
import de.thu.project.main.model.checklist_item.*;
import de.thu.project.main.view.*;


/********************************************** 
 * 
 * user chooses one of their checklists, all items are displayed
 * class to 
 * 		- change the name of a checklist
 * 		- add item to a checklist, if item is already in the checklist amount is added not replaced
 *      - if user wants to add 0 items, amount is automatically set to 1 
 * 		- delete items of a checklist considering amount
 * 		- if amount is 0, item is deleted from the checklist
 * 		- delete checklist
 * 
 **********************************************/

public class ChangeChecklistController implements ActionListener, MouseListener{

	private ChangeChecklistView view;
	private ChecklistDao_DB checklistDao;
	private ItemDao_DB itemDao;
	private Checklist_itemDao_DB checklist_itemDao;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private ArrayList<String> checklistItems;
	private int result;

	public ChangeChecklistController(ChangeChecklistView view) {
		this.view = view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
		this.checklist_itemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
	}

	public void setComboBoxCheck() {
		view.comboBoxChecklist.removeAllItems();
		ArrayList<String> checkList = new ArrayList<>(); 
		checkList = checklistDao.getAllChecklist(daofactory.getCurrent_user_name());

		for(String name : checkList) {
			view.comboBoxChecklist.addItem(name);
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

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
		checklistVo.setChecklistID(checklistDao.getChecklistID(checklistVo));

		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));

		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checklist_itemDao.addItem(checklistItem, amount);
	}

	public void deleteItemFromChecklist(String checklistName, String item) {

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
		checklistVo.setChecklistID(checklistDao.getChecklistID(checklistVo));

		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));

		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checklist_itemDao.deleteItem(checklistItem);
	}

	public void updateTextArea(String checklistName) {

		view.taChecklist.setText("");		
		ArrayList<String> list = new ArrayList<String>();
		int amount;	

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
		int checklist_id = checklistDao.getChecklistID(checklistVo);
		list = checklist_itemDao.getItemsC(checklist_id);

		for(int i = 0; i < list.size(); i++) {
			ItemVo item  = new ItemVo(list.get(i));
			int item_id = itemDao.getItemID(item);
			Checklist_itemVo checkitem = new Checklist_itemVo(checklist_id, item_id);
			amount = checklist_itemDao.getAmount(checkitem);
			view.taChecklist.append(amount + " " + list.get(i) +"\n");

		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if (src == view.comboBoxChecklist) {
			String checklistName = (String) view.comboBoxChecklist.getSelectedItem();
			updateTextArea(checklistName);
		}

		if (src == view.btnSave) {

			if(view.tfName.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"Type in a new name first!");
			} else {
				ChecklistVo oldChecklist = new ChecklistVo(view.comboBoxChecklist.getSelectedItem().toString().toLowerCase(), daofactory.getCurrent_user_name());
				String newName = view.tfName.getText().toString().toLowerCase();
				ChecklistVo newNameChecklist = new ChecklistVo(newName, daofactory.getCurrent_user_name());

				if(checklistDao.getChecklistID(newNameChecklist) == 0) {
					checklistDao.changeChecklistName(oldChecklist, newName);
					setComboBoxCheck();
					view.tfName.setText("");
					view.comboBoxChecklist.setSelectedItem(newName);
					JOptionPane.showMessageDialog(null,"Name successfully changed");
				} else {
					JOptionPane.showMessageDialog(null,"Name already exists");
				}
			}
		}

		if(src == view.comboBoxCat) {
			String cat = (String)view.comboBoxCat.getSelectedItem();
			setComboBoxItems(cat);
		}

		if(src == view.btnAdd) {
			String checklist = (String)view.comboBoxChecklist.getSelectedItem();
			String item = (String)view.comboBoxItems.getSelectedItem();
			int amount;

			try {
				amount =  Integer.parseInt(view.tfAmount.getText());
			} catch(Exception ex) {
				amount = 1;
				JOptionPane.showMessageDialog(null, "Amount cannot <= 0! Will be set to 1 automatically");
			}

			String checklistName = (String) view.comboBoxChecklist.getSelectedItem();
			ChecklistVo checklistVO = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
			int checklistID = checklistDao.getChecklistID(checklistVO);
			checklistItems = new ArrayList<String>();
			checklistItems = checklist_itemDao.getItemsC(checklistID);

			for(int i = 0; checklistItems.size() > i; i++) {
				if(checklistItems.get(i).equals(item)) {
					checklistItems.get(i);
					ItemVo itemVO = new ItemVo (checklistItems.get(i));
					itemVO.setItemID(itemDao.getItemID(itemVO));
					Checklist_itemVo finalItem = new Checklist_itemVo(checklistID, itemVO.getItemID());
					int amount2 = checklist_itemDao.getAmount(finalItem);
					result = amount2 + amount;
				}

			}
			if(checklistItems.isEmpty() ) {
				checklistVO.setChecklistID(checklistDao.getChecklistID(checklistVO));
				checklistDao.delete(checklistVO);
				setComboBoxCheck();
				view.taChecklist.setText("");
			}

			addItemtoChecklist(checklist, item, result);
			updateTextArea(checklist);
			view.tfAmount.setText("");
		}


		if(src == view.btnDelete) {
			String checklist = (String)view.comboBoxChecklist.getSelectedItem();
			String item = (String)view.comboBoxItems.getSelectedItem();
			int amount;

			try {
				amount =  Integer.parseInt(view.tfAmount.getText());
			} catch(Exception ex) {
				amount = 1;
				JOptionPane.showMessageDialog(null, "Amount needs to be >=1! Will be set to 1 automatically");
			}			

			String checklistName = (String) view.comboBoxChecklist.getSelectedItem();
			ChecklistVo checklistVO = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
			int checklistID = checklistDao.getChecklistID(checklistVO);
			checklistItems = new ArrayList<String>();
			checklistItems = checklist_itemDao.getItemsC(checklistID);

			for(int i = 0; checklistItems.size() > i; i++) {
				if(checklistItems.get(i).equals(item)) {
					checklistItems.get(i);
					ItemVo itemVO = new ItemVo (checklistItems.get(i));
					itemVO.setItemID(itemDao.getItemID(itemVO));
					Checklist_itemVo finalItem = new Checklist_itemVo(checklistID, itemVO.getItemID());
					int amount2 = checklist_itemDao.getAmount(finalItem);
					result = amount2 - amount; 						
				}
			}

			if(checklistItems.isEmpty() ) {
				checklistVO.setChecklistID(checklistDao.getChecklistID(checklistVO));
				checklistDao.delete(checklistVO);
				setComboBoxCheck();
				view.taChecklist.setText("");
			}
			if(result >= 1) {
				addItemtoChecklist(checklist, item, result);
				updateTextArea(checklist);
				view.tfAmount.setText("");
			} else {
				deleteItemFromChecklist(checklist,item);
				updateTextArea(checklist);
				view.tfAmount.setText("");
			}
		} 




		if(src == view.btnDeleteChecklist) {
			ChecklistVo checklist = new ChecklistVo(view.comboBoxChecklist.getSelectedItem().toString(), daofactory.getCurrent_user_name());
			checklist.setChecklistID(checklistDao.getChecklistID(checklist));
			checklistDao.delete(checklist);
			setComboBoxCheck();
			view.taChecklist.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		Object src = evt.getSource();

		if(src == view.panMenBack) {
			MenuView mView = new MenuView();
			mView.setVisible(true);
			view.dispose();
		}
		if(src == view.panMenCheck) {
			UseChecklistView ucView = new UseChecklistView();
			ucView.setVisible(true);
			view.dispose();
		}
		if(src == view.panMenTemp) {
			TemplateView tView = new TemplateView();
			tView.setVisible(true);
			view.dispose();
		}
		if(src == view.panMenItem) {
			ItemView iView = new ItemView(view);
			iView.setVisible(true);
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
