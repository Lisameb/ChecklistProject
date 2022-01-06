package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.DaoFactory;
import model.checklist.ChecklistDao_DB;
import model.checklist.ChecklistVo;
import model.checklist_item.Checklist_itemDao_DB;
import model.checklist_item.Checklist_itemVo;
import model.item.CategoryVo;
import model.item.ItemDao_DB;
import model.item.ItemVo;
import view.ChangeChecklistView;
import view.ItemView;
import view.MenuView;
import view.TemplateView;
import view.UseChecklistView;

/********************************************** 
 * 
 * user chooses one of their checklists, all items are displayed
 * class to 
 * 		- change the name of a checklist
 * 		- add item to a checklist, if item is already in the checklist, the amount is replaced, not added
 * 		- delete items of a checklist, regardless of amount
 * 		- delete checklist
 * 
 **********************************************/

public class ChangeChecklistController implements ActionListener, MouseListener{

	private ChangeChecklistView view;
	private ChecklistDao_DB checklistDao;
	private ItemDao_DB itemDao;
	private Checklist_itemDao_DB checklist_itemDao;
	private DaoFactory daofactory = DaoFactory.getInstance();

	public ChangeChecklistController(ChangeChecklistView view) {
		this.view = view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
		this.checklist_itemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
	}

	public void setComboBoxCheck() {
		view.comboBoxChecklist.removeAllItems();
		ArrayList<String> checkList = new ArrayList<>(); 
		checkList = checklistDao.getAllChecklist(daofactory.getCurrent_user());

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

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
		checklistVo.setChecklistID(checklistDao.getChecklistID(checklistVo));

		ItemVo itemVo = new ItemVo(item);
		itemVo.setItemID(itemDao.getItemID(itemVo));

		Checklist_itemVo checklistItem = new Checklist_itemVo(checklistVo.getChecklistID(), itemVo.getItemID());
		checklist_itemDao.addItem(checklistItem, amount);
	}
	
	public void deleteItemFromChecklist(String checklistName, String item) {

		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
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
		
		ChecklistVo checklistVo = new ChecklistVo(checklistName, daofactory.getCurrent_user());
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
				ChecklistVo checklist = new ChecklistVo(view.comboBoxChecklist.getSelectedItem().toString().toLowerCase(), daofactory.getCurrent_user());
				checklistDao.changeChecklistName(checklist, view.tfName.getText());
				setComboBoxCheck();
				view.tfName.setText("");
				view.comboBoxChecklist.setSelectedItem(view.tfName.getText());
			}
		}
		
		if(src == view.comboBoxCat) {
			String cat = (String)view.comboBoxCat.getSelectedItem();
			setComboBoxItems(cat);
		}
		
		if(src == view.btnAdd) {
			String checklist = (String)view.comboBoxChecklist.getSelectedItem();
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
			String checklist = (String)view.comboBoxChecklist.getSelectedItem();
			String item = (String)view.comboBoxItems.getSelectedItem();
			
			deleteItemFromChecklist(checklist, item);
			updateTextArea(checklist);
			
		}
		
		if(src == view.btnDeleteChecklist) {
			ChecklistVo checklist = new ChecklistVo(view.comboBoxChecklist.getSelectedItem().toString(), daofactory.getCurrent_user());
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
