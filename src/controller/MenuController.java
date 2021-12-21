package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ChangeChecklistView;
import view.ChangeTemplateView;
import view.ImportChecklistView;
import view.ItemView;
import view.LoginViewTest;
import view.MenuView;
import view.TemplateView;
import view.UseChecklistView;

/**********************************************
 * MenuController-Class
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
			ChangeTemplateView ctView = new ChangeTemplateView();
			ctView.setVisible(true);
			mView.dispose();
			
		}else if(src == mView.panel_createItem) {
			ItemView iView = new ItemView(mView);
			iView.setVisible(true);
			mView.setVisible(false);
			
		} else if(src == mView.panel_logOut) {
			LoginViewTest lgView = new LoginViewTest();
			lgView.setVisible(true);
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



}
