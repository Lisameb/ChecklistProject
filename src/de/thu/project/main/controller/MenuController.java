package de.thu.project.main.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.view.ChangeChecklistView;
import de.thu.project.main.view.ChangePasswordView;
import de.thu.project.main.view.ChangeTemplateView;
import de.thu.project.main.view.ImportChecklistView;
import de.thu.project.main.view.ItemView;
import de.thu.project.main.view.LoginView;
import de.thu.project.main.view.MenuView;
import de.thu.project.main.view.TemplateView;
import de.thu.project.main.view.UseChecklistView;

/**********************************************
 * 
 * controller for MenuView, gets called in
 * MenuView.
 * 
 * used Methods:
 * 	+ void mouseClicked(ActionEvent e)
 * 
 **********************************************/

public class MenuController implements MouseListener{
	

	private MenuView mView;
	private DaoFactory daofactory = DaoFactory.getInstance();
	
	public MenuController(MenuView mView) {
		this.mView = mView;
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		Object src = e.getSource();
		
		if(src == mView.panel_checklist) {
			UseChecklistView ucView = new UseChecklistView();
			ucView.setVisible(true);
			mView.dispose();
			
		}else if(src == mView.panel_newChecklist) {
			TemplateView tView = new TemplateView();
			tView.setVisible(true);
			mView.dispose();
			
		}else if(src == mView.panel_manageChecklists) {
			ChangeChecklistView cclView = new ChangeChecklistView();
			cclView.setVisible(true);
			mView.dispose();
			
		}else if(src == mView.panel_importChecklist) {
			ImportChecklistView icView = new ImportChecklistView();
			icView.setVisible(true);
			mView.dispose();
			
		}else if(src == mView.panel_manageTemplates) {
			if(daofactory.getCurrent_user().getRole().getManageTemplates()) {
				ChangeTemplateView ctView = new ChangeTemplateView();
				ctView.setVisible(true);
				mView.dispose();
				
			}
			
		}else if(src == mView.panel_createItem) {
			ItemView iView = new ItemView(mView);
			iView.setVisible(true);
			mView.setVisible(false);
			
		} else if(src == mView.panel_logOut) {
			LoginView lgView = new LoginView();
			lgView.setVisible(true);
			mView.dispose();
			
		} else if(src == mView.panel_ChangePw) {
			ChangePasswordView pwView = new ChangePasswordView();
			pwView.setVisible(true);
			mView.dispose();
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
	
	public Boolean hasManageTemplatesPermission() {
		return daofactory.getCurrent_user().getRole().getManageTemplates();
	}
	
	public Boolean hasCreateItemPermission() {
		return daofactory.getCurrent_user().getRole().getCreateItem();
	}
}
