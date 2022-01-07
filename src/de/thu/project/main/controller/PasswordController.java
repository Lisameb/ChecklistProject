package de.thu.project.main.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.user.UserDao_DB;
import de.thu.project.main.model.user.UserVo;
import de.thu.project.main.view.ChangePasswordView;
import de.thu.project.main.view.MenuView;

/********************************************** 
 * 
 * Class to change the password of the logged in user
 * Checks current password of user.
 * Checks if new password is valid.
 * If checks are successful, password is updated in our database.
 * 
 **********************************************/

public class PasswordController implements ActionListener{

	ChangePasswordView view;
	DaoFactory daoFactory = DaoFactory.getInstance();
	UserDao_DB userDao;


	public PasswordController(ChangePasswordView view) {
		this.view = view;
		this.userDao = (UserDao_DB) daoFactory.getUserDao();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		if(src == view.btnChange) {
			String currPw = view.tfCurrPw.getText().toString();
			String newPw1 = view.tfNewPw1.getText().toString();
			String newPw2 = view.tfNewPw2.getText().toString();
			if (currPw.equals("")) {
				view.lblWrongPw.setText("Please type in your password!");
			}else if (newPw1.equals("") || newPw2.equals("")) {
				view.lblWrongPw.setText("");
				view.lblWrongNewPw.setText("Please type in a new password!");
			}else {
				view.lblWrongPw.setText("");
				view.lblWrongNewPw.setText("");
				UserVo userVo = new UserVo(daoFactory.getCurrent_user(), currPw);
				if(userDao.checkPassword(userVo)) {
					if(newPw1.equals(newPw2)) {
						userVo.setPassword(newPw1);
						if(userDao.validatePassword(userVo)) {
							userDao.update(userVo);
							JOptionPane.showMessageDialog(null,"Password successfully changed");
							view.dispose();
							MenuView menuView = new MenuView();
							menuView.setVisible(true);
						} else {
							view.lblWrongNewPw.setText("New password too short!");
							view.tfNewPw1.setText("");
							view.tfNewPw2.setText("");
						}
					} else {
						view.lblWrongPw.setText("");
						view.lblWrongNewPw.setText("New passwords aren't congruent!");
						view.tfNewPw1.setText("");
						view.tfNewPw2.setText("");
					}
				} else {
					view.lblWrongNewPw.setText("");
					view.lblWrongPw.setText("Wrong password");
					view.tfCurrPw.setText("");
				}
			}
		}
		
		if (src==view.btnCancel) {
			view.dispose();
			MenuView menu = new MenuView();
			menu.setVisible(true);
		}
	}
}
