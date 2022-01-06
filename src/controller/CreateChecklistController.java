package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import model.item_template.Item_tempDao_DB;
import model.item_template.Item_tempVo;
import model.template.TemplateDao_DB;
import model.template.TemplateVo;
import view.CreateChecklistView;
import view.CreateNewChecklistView;
import view.MenuView;
import view.TemplateView;
import view.UseChecklistView;

/********************************************** 
 * 
 * user chooses a template, all items are displayed
 * class to create a checklist with
 * 		- the items of the chosen template
 * 		- a name, given by the user
 * user can add more items or delete items
 * 
 **********************************************/

public class CreateChecklistController implements ActionListener,MouseListener {

	int temp_id;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private Item_tempDao_DB itemTemp;
	private Checklist_itemDao_DB checklist_itemDao;
	private ChecklistDao_DB checklistDao;
	private ItemDao_DB itemDao;
	private TemplateDao_DB tempDao;
	private CreateChecklistView view;
	private CreateNewChecklistView newView;
	private TemplateView tempView;
	
	public CreateChecklistController(TemplateView tempView, int temp_id) {
		this.temp_id = temp_id;
		this.tempView = tempView;
		newView = new CreateNewChecklistView(this);
		newView.setVisible(true);
		view = new CreateChecklistView(this);
		
		itemTemp = (Item_tempDao_DB) daofactory.getItem_tempDao();
		checklist_itemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
		checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		itemDao = (ItemDao_DB) daofactory.getItemDao();
		tempDao = (TemplateDao_DB) daofactory.getTemplateDao();
	}
	
	public void createNewChecklist() {
		ChecklistVo checkVo = new ChecklistVo(newView.tfName.getText(), daofactory.getCurrent_user());
		checklistDao.insert(checkVo);
	}
	
	public void saveAllTempItems(int temp_id) {
		
		ArrayList<String> list=new ArrayList<String>();
		list = itemTemp.getItemsT(temp_id);	
		ChecklistVo checkVo = new ChecklistVo(newView.tfName.getText(), daofactory.getCurrent_user());
		checkVo.setChecklistID(checklistDao.getChecklistID(checkVo));
		
		
		for(int i = 0; i < list.size(); i++) {
			ItemVo item = new ItemVo(list.get(i));
			item.setItemID(itemDao.getItemID(item));
			TemplateVo tempVo = new TemplateVo(temp_id, tempDao.getTemplateName(temp_id));
			Item_tempVo itemTempVo = new Item_tempVo(tempVo, item);
			int amount = itemTemp.getAmount(itemTempVo);
			
			Checklist_itemVo checkItemVo = new Checklist_itemVo(checkVo.getChecklistID(), item.getItemID());
			checkItemVo.setAmount(amount);
			checklist_itemDao.addItem(checkItemVo, amount);
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
		
		if(src == newView.btnNew) {

			if(newView.tfName.getText().equals("")) {
				newView.lblNameError.setText("Type in a name first!");
			} else {
				createNewChecklist();
				String name = newView.tfName.getText().toLowerCase();
				view.lblName.setText(name);
				view.setVisible(true);
				newView.dispose();
				tempView.dispose();
				saveAllTempItems(temp_id);
				setComboBoxCat();
				updateTextArea(name);
			}	
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
