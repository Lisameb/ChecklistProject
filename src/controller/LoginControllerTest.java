package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.DaoFactory;
import model.user.UserDao_DB;
import model.user.UserVo;
import view.LoginViewTest;
import view.MenuView;
import view.TemplateView;

public class LoginControllerTest implements ActionListener{

	LoginViewTest view;
	private String un;
	private String pas;
	private DaoFactory daofactory = DaoFactory.getInstance();
	private UserDao_DB userDao;



	public LoginControllerTest(LoginViewTest view) {
		this.view = view;
		this.userDao = (UserDao_DB) daofactory.getUserDao();
	}

	public boolean loginUser() {
		pas = this.view.getTxtLogPassword();
		un = this.view.getTxtLogUsername();
		UserVo logUser = new UserVo(un, pas);
		if(userDao.checkPassword(logUser) && !logUser.getUsername().isEmpty()) {
			daofactory.setCurrent_user(un);
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
				view.getFrame().dispose();
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