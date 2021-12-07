package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.*;
import view.TemplateView;

public class TemplateController implements MouseListener,ActionListener {
	
	TemplateView tempview;
	private TemplateVo template;
	private TemplateDao_DB templateDao = new TemplateDao_DB();
	private Item_tempDao_DB item_tempDao = new Item_tempDao_DB();
	
	public TemplateController(TemplateView view) {
		this.tempview = view;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		Object src = evt.getSource();
		int temp_id;
		ArrayList<String> list = new ArrayList<String>();
		
		tempview.taPreview.setText("");
		
		if(src == tempview.lblEmpty) {
			template = new TemplateVo("empty");
			temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
		}
		if(src == tempview.lblDest) {
			template = new TemplateVo("destination");
			temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
		}
		if(src == tempview.lblGroc) {
			template = new TemplateVo("groceries");
			temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
		}
		if(src == tempview.lblParty) {
			template = new TemplateVo("party");
			temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
		}
		if(src == tempview.lblVaca) {
			template = new TemplateVo("vacation");
			temp_id = templateDao.getTemplateID(template);
			list = item_tempDao.getItemsT(temp_id); 
			for(int i = 0; i < list.size(); i++) {
				tempview.taPreview.append(list.get(i) + "\n");
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
