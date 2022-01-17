package de.thu.project.main.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import de.thu.project.main.model.*;
import de.thu.project.main.model.item_template.Item_tempDao_DB;
import de.thu.project.main.model.template.TemplateDao_DB;
import de.thu.project.main.model.template.TemplateVo;
import de.thu.project.main.view.MenuView;
import de.thu.project.main.view.TemplateView;
import de.thu.project.main.view.UseChecklistView;

/********************************************** 
 * 
 * class displays all templates
 * template for a new checklist can be chosen
 * 
 **********************************************/

public class TemplateController implements MouseListener, ActionListener {
	
	
	TemplateView tempview;
	//private TemplateVo template;
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	private TemplateDao_DB tempDao;
	private Item_tempDao_DB item_tempDao;
	
	public TemplateController(TemplateView view) {
		this.tempview = view;
		this.tempDao = (TemplateDao_DB) daofactory.getTemplateDao();
		this.item_tempDao = (Item_tempDao_DB) daofactory.getItem_tempDao();	
	}
	
	public void showTemplates() {
		ArrayList<String> templates = new ArrayList<String>();
			templates = tempDao.getAllTemplate();
			
			for(int i = 0; i < templates.size(); i++) {
				tempview.createRadioButton(i, templates);
			}
			tempview.contentPane.updateUI();
			
			int paneLength = 150 + templates.size()*30;
			tempview.contentPane.setPreferredSize(new Dimension(730, paneLength));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		tempview.taTempItems.setText("");
		
		if(src == tempview.btnShowTemp) {
			String template = tempview.group.getSelection().getActionCommand().toString();
			
			if(template.equals("")) {
				JOptionPane.showMessageDialog(null, "Please select a template!");
			} else {
				TemplateVo tempVo = new TemplateVo(template);
				tempVo.setTemplateID(tempDao.getTemplateID(tempVo));

				ArrayList<String> allItems = item_tempDao.getItemsT(tempVo.getTemplateID());

				for(int i = 0; i<allItems.size(); i++ ) {
					tempview.taTempItems.append(" " + allItems.get(i) + "\n"); 
				}
			}	
		}
		
		if(src == tempview.btnSelect) {
			String template = tempview.group.getSelection().getActionCommand().toString();
			
			if(template.equals("")) {
				JOptionPane.showMessageDialog(null, "Please select a template!");
			} else {
				TemplateVo tempVo = new TemplateVo(template);
				tempVo.setTemplateID(tempDao.getTemplateID(tempVo));
				CreateChecklistController createCheckContro = new CreateChecklistController(tempview, tempVo.getTemplateID());
			} 
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		
		if(src == tempview.panMenCheck) {
			UseChecklistView uclView = new UseChecklistView();
			uclView.setVisible(true);
			tempview.dispose();
		}
		if(src == tempview.panMenBack) {
			MenuView mView = new MenuView();
			mView.setVisible(true);
			tempview.dispose();
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
