package de.thu.project.main.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import de.thu.project.main.model.DaoFactory;
import de.thu.project.main.model.user.RoleVo;
import de.thu.project.main.model.user.UserDao_DB;
import de.thu.project.main.model.user.UserVo;
import de.thu.project.main.view.LoginView;
import de.thu.project.main.view.MenuView;

/********************************************** 
 * 
 * class to login or register a new user
 * 
 * Login:	compares typed in password and username with database
 * 			if correct: user is logged in
 * Register: 	checks if typed in username already exists
 * 				checks if password is valid
 * 				saves user and password into the database
 * 
 **********************************************/

public class LoginController implements ActionListener{

	LoginView view;
	private String un;
	private String pas;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private UserDao_DB userDao;



	public LoginController(LoginView view) {
		this.view = view;
		this.userDao = (UserDao_DB) daofactory.getUserDao();
	}

	public boolean loginUser() {
		pas = this.view.getTxtLogPassword();
		un = this.view.getTxtLogUsername();
		UserVo logUser = new UserVo(un, pas);
		if(userDao.checkPassword(logUser) && !logUser.getUsername().isEmpty()) {
			daofactory.setCurrent_user(un);
			RoleVo role = userDao.hasRole(daofactory.getCurrent_user());
			daofactory.getCurrent_user().setRole(role);
			return true;
		}
		return false;
	}

	public UserVo createUser() {
		un = view.getTxtRUsername();
		pas = view.getTxtRPassword();
		if(userDao.checkUsernameExists(un) == false) {
			UserVo logUser = new UserVo(un, pas);
			if (userDao.validatePassword(logUser) == true) {
				JOptionPane.showMessageDialog(null,"Registration successful :)");
				userDao.insert(logUser);
				return logUser;
			}
		}else JOptionPane.showMessageDialog(null,"Oh no :( Username is already taken");
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		

		if (src == view.btnLogin) {
			if(this.loginUser()) {
				view.setTxtLogUsername("");
				view.setTxtLogPassword("");
//				TemplateView templateview = new TemplateView();
//				templateview.setVisible(true);
				MenuView menu = new MenuView();
				menu.setVisible(true);
				view.dispose();
				un = null;
				pas = null;
			}
			else {
				JOptionPane.showMessageDialog(null,"Invalid Username or Password :(");
				view.setTxtLogUsername("");
				view.setTxtLogPassword("");

			}
		}
		
		if (src == view.btnRReg) {
			createUser();
			view.setTxtRUsername("");
			view.setTxtRPassword("");
		}

	}


}