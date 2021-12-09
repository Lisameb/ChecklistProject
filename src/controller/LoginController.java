package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.UserDao_DB;
import model.UserVo;
import view.ChangeTemplateView;
import view.View;

public class LoginController implements ActionListener{

	View view;
	private String un;
	private String pas;
	private UserDao_DB userDao = new UserDao_DB();



	public LoginController(View view) {
		this.view = view;
	}

	public boolean loginUser() {
		pas = view.getTxtPassword();
		un = view.getTxtUserName();
		UserVo logUser = new UserVo(un, pas);
		if(userDao.checkPassword(logUser)) {
			return true;
		}
		return false;
	}

	public UserVo createUser() {
		un = view.getTxtUserName();
		pas = view.getTxtPassword();
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
		un = view.getTxtUserName();
		pas = view.getTxtPassword();
		UserVo login = new UserVo(un, pas);

		if (src == view.btnLogin) {
			if(this.loginUser()) {
				JOptionPane.showMessageDialog(null,"Login Successful :)");
				view.setTxtUsername("");
				view.setTxtPassword("");
				ChangeTemplateView viewChangeTemp = new ChangeTemplateView();
				viewChangeTemp.setVisible(true);
				view.getFrame().dispose();
			}
			else {
				JOptionPane.showMessageDialog(null,"Invalid Username or Password :(");
				view.setTxtUsername("");
				view.setTxtPassword("");

			}
		}
		
		if (src == view.btnReg) {
			createUser();
			view.setTxtUsername("");
			view.setTxtPassword("");
		}

	}


}
