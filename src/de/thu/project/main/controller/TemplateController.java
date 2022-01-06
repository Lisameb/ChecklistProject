package de.thu.project.main.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import de.thu.project.main.model.*;
import de.thu.project.main.model.item_template.Item_tempDao_DB;
import de.thu.project.main.model.template.TemplateDao_DB;
import de.thu.project.main.model.template.TemplateVo;
import de.thu.project.main.view.MenuView;
import de.thu.project.main.view.TemplateView;

/********************************************** 
 * 
 * class displays all templates
 * template for a new checklist can be chosen
 * 
 **********************************************/

public class TemplateController implements MouseListener {
	
	
	TemplateView tempview;
	private TemplateVo template;
	
	private DaoFactory daofactory = DaoFactory.getInstance();
	private TemplateDao_DB tempDao;
	private Item_tempDao_DB item_tempDao;
	int temp_id = 0;
	
	public TemplateController(TemplateView view) {
		this.tempview = view;
		this.tempDao = (TemplateDao_DB) daofactory.getTemplateDao();
		this.item_tempDao = (Item_tempDao_DB) daofactory.getItem_tempDao();
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		ArrayList<String> list = new ArrayList<String>();
		
		tempview.taPreview.setText("");
		
		if(src == tempview.lblEmpty) {
			template = new TemplateVo("empty");
			this.temp_id = tempDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
		}
		if(src == tempview.lblDest) {
			template = new TemplateVo("destination");
			this.temp_id = tempDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.lblGroc) {
			template = new TemplateVo("groceries");
			this.temp_id = tempDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.lblParty) {
			template = new TemplateVo("party");
			this.temp_id = tempDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.lblVaca) {
			template = new TemplateVo("vacation");
			this.temp_id = tempDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.btnSelect && temp_id != 0) {
			CreateChecklistController createCheckContro = new CreateChecklistController(tempview,temp_id);
			
		}if(src == tempview.btnBack) {
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
