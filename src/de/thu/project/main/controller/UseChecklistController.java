package de.thu.project.main.controller;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.checklist.*;
import de.thu.project.main.model.checklist_item.*;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.model.item.ItemVo;
import de.thu.project.main.view.*;

/**********************************************
 * 
 * class fills combobox with names of a users checklists (setComboBoxCheck())
 * select a checklist through the combobox and display it or modify it again
 * 
 **********************************************/

public class UseChecklistController implements ActionListener, MouseListener {

	private UseChecklistView view;
	private TemplateView tview;
	private ChangeChecklistView cclview;
	private MenuView mview;
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	private ChecklistDao_DB checklistDao;
	private Checklist_itemDao_DB checklist_itemDao;
	private ItemDao_DB itemDao;
	
	private ArrayList<String> checklistItems;
	private ArrayList<String> checklistItems2;
	
	public UseChecklistController(UseChecklistView view) {
		this.view = view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.checklist_itemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
		this.itemDao = (ItemDao_DB) daofactory.getItemDao();
	}
	
	public void setComboBoxCheck() {
		view.comboBoxChecklist.removeAllItems();
		ArrayList<String> checklist = new ArrayList<String>();
		checklist = checklistDao.getAllChecklist(daofactory.getCurrent_user_name());
		
		for(String name : checklist) {
			view.comboBoxChecklist.addItem(name);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		if(src == view.comboBoxChecklist) {
			//TODO
		}
		
		if(src == view.btnModify) {
			String checklistName = (String) view.comboBoxChecklist.getSelectedItem();
			cclview = new ChangeChecklistView(checklistName);
			cclview.setVisible(true); // I am Confusion: America explain -> geht das so auch oder muss man ein Controller-Objekt erstellen in changeChecklistView?
			view.dispose();
			
		}
		
		if(src == view.btnOpen) {
			String checklistName = (String) view.comboBoxChecklist.getSelectedItem();
			ChecklistVo checklist = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
			int checklistID = checklistDao.getChecklistID(checklist);
			checklistItems = new ArrayList<String>();
			checklistItems = checklist_itemDao.getItemsC(checklistID);
			
			for(int i = 0; i < checklistItems.size(); i++) {
				ItemVo itemVO = new ItemVo(checklistItems.get(i));
				itemVO.setItemID(itemDao.getItemID(itemVO));
				Checklist_itemVo finalItem = new Checklist_itemVo(checklistID, itemVO.getItemID());
				view.createrCheckBox(i, checklistItems, checklist_itemDao.getChecked(finalItem));
			}
			view.panel_1.updateUI();
			
		}
		if(src == view.btnExport) {

			JFileChooser file = new JFileChooser();
			int returnVal = file.showSaveDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	File fileToSave;
	            String path = file.getSelectedFile().getPath();
	             
	            if (!path.toLowerCase().endsWith(".xml")) {
	              path = path + ".xml";
	            } 
	            
	            fileToSave = new File(path);
	            fileToSave.getAbsolutePath();
	            Export export = new Export((String)view.comboBoxChecklist.getSelectedItem(),fileToSave.getAbsolutePath());
	            export.createXML();
	        } else {
	        	JOptionPane.showMessageDialog(null,"Path not found :(");
	        }
	        
		}
		
		if (src == view.btnCreatePdf) {
			JFileChooser file = new JFileChooser();
			int returnVal = file.showSaveDialog(null);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	            File fileToSave;
	            String path = file.getSelectedFile().getPath();
	             
	            if (!path.toLowerCase().endsWith(".pdf")) {
	              path = path + ".pdf";
	            } 
	            
	            fileToSave = new File(path);
	            fileToSave.getAbsolutePath();
	            Export export = new Export((String)view.comboBoxChecklist.getSelectedItem(),fileToSave.getAbsolutePath());
	            export.createPDF();
	        } else {
	        	JOptionPane.showMessageDialog(null,"Path not found :(");
	        }
		}
		
		for(int k = 0; k < view.boxes.size(); k++) {
			if (src == view.boxes.get(k)) {
				String name;
				String checklistName;
				int checklistID;
				for(int i = 0; i < view.boxes.size(); i++) {
					if(view.boxes.get(i).isSelected()) {
						name = view.boxes.get(i).getText();
						checklistName = (String) view.comboBoxChecklist.getSelectedItem();
						ChecklistVo checklistVO = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
						checklistID = checklistDao.getChecklistID(checklistVO);
						checklistItems = new ArrayList<String>();
						checklistItems = checklist_itemDao.getItemsC(checklistID);
						
						for(int j = 0; checklistItems.size() > j; j++) {
							if(checklistItems.get(j).equals(name)) {
								ItemVo itemVO = new ItemVo(checklistItems.get(j));
								itemVO.setItemID(itemDao.getItemID(itemVO));
								Checklist_itemVo finalItem = new Checklist_itemVo(checklistID, itemVO.getItemID());
								checklist_itemDao.toggleCheck2(finalItem);
								//result = amount2 + amount; 
						}
						
//						for(int j = 0; j < checklistItems2.size(); j++) {
//							if(checklistItems2.get(j).equals(name)) {
//								ItemVo itemVO = new ItemVo(checklistItems2.get(j));
//								System.out.println(itemDao.getItemID(itemVO));
//								itemVO.setItemID(itemDao.getItemID(itemVO));
//								Checklist_itemVo finalItem = new Checklist_itemVo(checklistID, itemVO.getItemID());
//								checklist_itemDao.toggleCheck(finalItem);
//							}
//						}
					}
				} else {
					name = view.boxes.get(i).getText();
					checklistName = (String) view.comboBoxChecklist.getSelectedItem();
					ChecklistVo checklistVO = new ChecklistVo(checklistName, daofactory.getCurrent_user_name());
					checklistID = checklistDao.getChecklistID(checklistVO);
					checklistItems = new ArrayList<String>();
					checklistItems = checklist_itemDao.getItemsC(checklistID);
					
					for(int j = 0; checklistItems.size() > j; j++) {
						if(checklistItems.get(j).equals(name)) {
							ItemVo itemVO = new ItemVo(checklistItems.get(j));
							itemVO.setItemID(itemDao.getItemID(itemVO));
							Checklist_itemVo finalItem = new Checklist_itemVo(checklistID, itemVO.getItemID());
							checklist_itemDao.toggleUnchecked(finalItem);
							//result = amount2 + amount; 
						}
					}
				}
				}
			}
		
		}
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		Object src = evt.getSource();
		if(src == view.panCreate) {
			tview = new TemplateView();
			tview.setVisible(true);
			view.dispose();
		}
		
		if(src == view.panMenBack) {
			mview = new MenuView();
			mview.setVisible(true);
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
