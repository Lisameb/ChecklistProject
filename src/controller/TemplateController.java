package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.*;
import model.item_template.Item_tempDao_DB;
import model.template.TemplateDao_DB;
import model.template.TemplateVo;
import view.MenuView;
import view.TemplateView;

public class TemplateController implements MouseListener {
	
	TemplateView tempview;
	private TemplateVo template;
	private TemplateDao_DB templateDao = new TemplateDao_DB();
	private Item_tempDao_DB item_tempDao = new Item_tempDao_DB();
	int temp_id = 0;
	
	public TemplateController(TemplateView view) {
		this.tempview = view;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		ArrayList<String> list = new ArrayList<String>();
		
		tempview.taPreview.setText("");
		
		if(src == tempview.lblEmpty) {
			template = new TemplateVo("empty");
			this.temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
		}
		if(src == tempview.lblDest) {
			template = new TemplateVo("destination");
			this.temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.lblGroc) {
			template = new TemplateVo("groceries");
			this.temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.lblParty) {
			template = new TemplateVo("party");
			this.temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(" - " + list.get(i) + "\n");
			}
		}
		if(src == tempview.lblVaca) {
			template = new TemplateVo("vacation");
			this.temp_id = templateDao.getTemplateID(template);
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
