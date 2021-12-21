package controller;

import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

public class UseChecklistController implements ActionListener, MouseListener {

	private UseChecklistView view;
	private TemplateView tview;
	private ChangeChecklistView cclview;
	private MenuView mview;
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	private ChecklistDao_DB checklistDao;
	private Checklist_itemDao_DB checklist_itemDao;
	
	private ArrayList<String> checklistItems;
	
	public UseChecklistController(UseChecklistView view) {
		this.view = view;
		this.checklistDao = (ChecklistDao_DB) daofactory.getChecklistDao();
		this.checklist_itemDao = (Checklist_itemDao_DB) daofactory.getChecklist_itemDao();
	}
	
	public void setComboBoxCheck() {
		view.comboBox_check.removeAllItems();
		ArrayList<String> checklist = new ArrayList<String>();
		checklist = checklistDao.getAllChecklist(daofactory.getCurrent_user());
		
		for(String name : checklist) {
			view.comboBox_check.addItem(name);
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		if(src == view.comboBox_check) {
		}
		
		if(src == view.modifyButton) {
			String checklistName = (String) view.comboBox_check.getSelectedItem();
			cclview = new ChangeChecklistView(checklistName);
			cclview.setVisible(true); // I am Confusion: America explain -> geht das so auch oder muss man ein Controller-Objekt erstellen in changeChecklistView?
			view.getFrame().dispose();
			
		}
		
		if(src == view.openButton) {
			String checklistName = (String) view.comboBox_check.getSelectedItem();
			ChecklistVo checklist = new ChecklistVo(checklistName, daofactory.getCurrent_user());
			int checklistID = checklistDao.getChecklistID(checklist);
			checklistItems = new ArrayList<String>();
			checklistItems = checklist_itemDao.getItemsC(checklistID);
			
			for(int i = 0; i < checklistItems.size(); i++) {
				view.createrCheckBox(i, checklistItems);
			}
			view.panel_1.updateUI();;
			
		}
		if(src == view.exportButton) {

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
	            Export export = new Export((String)view.comboBox_check.getSelectedItem(),fileToSave.getAbsolutePath());
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
	            Export export = new Export((String)view.comboBox_check.getSelectedItem(),fileToSave.getAbsolutePath());
	            export.createPDF();
	        } else {
	        	JOptionPane.showMessageDialog(null,"Path not found :(");
	        }
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		Object src = evt.getSource();
		if(src == view.panel_2) {
			tview = new TemplateView();
			tview.setVisible(true);
			view.getFrame().dispose();
		}
		
		if(src == view.panel_3) {
			mview = new MenuView();
			mview.setVisible(true);
			view.getFrame().dispose();
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
