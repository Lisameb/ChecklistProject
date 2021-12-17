package controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import model.DaoFactory;
import model.checklist.*;
import model.checklist_item.*;
import view.*;

/**********************************************
 * UseChecklistController-Class
 * 
 * class fills combobox with names of a users checklists (setComboBoxCheck())
 * select a checklist through the combobox and display it or modify it again
 * 
 **********************************************/

public class UseChecklistController implements ActionListener {

	private UseChecklistView view;
	private TemplateView tview;
	private ChecklistDao_DB checklistDao;
	private Checklist_itemDao_DB checklist_itemDao_DB;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private ArrayList<String> checklistItems;
	private ChangeChecklistView cclview;
	private MenuView mview;
	
	public UseChecklistController(UseChecklistView view) {
		this.view = view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.checklist_itemDao_DB = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
	}
	
	public void setComboBoxCheck() {
		view.comboBox_check.removeAllItems();
		ArrayList<String> checklist = new ArrayList<String>();
		checklist = checklistDao.getAllChecklist(daofactory.getCurrent_user());
		
		for(String name : checklist) {
			view.comboBox_check.addItem(name);
		}
	}
	
	
	// für die ComboBox: getAllChecklist(String username) von ChecklistDao_DB.java DOOOOOONE
	// für Button "Modify": modifyChecklistView DOOOOOONE
	// für Button Open: getItemsC(int checklist_id) von Checklist_itemDao_DB.java  DOOO//OOONE
	// für Go To Create Checklist: CreateChecklistView DOOOOOONE
	// für Go To Menu: MenuView DOOOOOONE
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		
		if(src == view.comboBox_check) {
			//TODO
		}
		
		if(src == view.modifyButton) {
			String checklistName = (String) view.comboBox_check.getSelectedItem();
			cclview = new ChangeChecklistView(checklistName);
			cclview.setVisible(true); // I am Confusion: America explain -> geht das so auch oder muss man ein Controller-Objekt erstellen in changeChecklistView?
		}
		
		if(src == view.openButton) {
			String checklistName = (String) view.comboBox_check.getSelectedItem();
			ChecklistVo checklist = new ChecklistVo(checklistName, daofactory.getCurrent_user());
			int checklistID = checklistDao.getChecklistID(checklist);
			checklistItems = new ArrayList<String>();
			checklistItems = checklist_itemDao_DB.getItemsC(checklistID);
			
			for(int i = 0; i < checklistItems.size(); i++) {
				view.createrCheckBox(i, checklistItems);
			}
			
		}
		if(src == view.exportButton) {
			// TODO call methode to create XML file --> Jonas, Lea, Lisa
		}
		
		if(src == view.panel_2) {
			tview = new TemplateView();
			tview.setVisible(true);
		}
		
		if(src == view.panel_3) {
			//TODO Aufruf von MenuView
			mview = new MenuView();
			mview.getFrame().setVisible(true); // Panel in MenuView fehlt -> ist bisher komplett leer
		}
		
	}
	
	// first idea to create the checkboxes for the checklist
//	public void createrCheckBox(int i)
//    {
//        view.boxes[i] = new JCheckBox();
//        //proper locations will be solved later
//        view.boxes[i].setLocation(87,34+i*25); 
//        view.boxes[i].setSize(100,50);
//        view.boxes[i].setText(checklistItems.get(i));
//        view.boxes[i].setSelected(false);
//        view.boxes[i].setVisible(true);
//        view.frame.getContentPane().add(view.boxes[i]);
//    } 

	
}
