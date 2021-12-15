//package controller;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JOptionPane;
//import model.DaoFactory;
//import model.user.UserDao_DB;
//import model.user.UserVo;
//import view.LoginView;
//import view.TemplateView;
//
//public class LoginController implements ActionListener{
//
//	LoginView view;
//	private String un;
//	private String pas;
//	private DaoFactory daofactory = DaoFactory.getInstance();
//	private UserDao_DB userDao;
//
//
//
//	public LoginController(LoginView view) {
//		this.view = view;
//		this.userDao = (UserDao_DB) daofactory.getUserDao();
//	}
//
//	public boolean loginUser() {
//		pas = view.getTxtPassword();
//		un = view.getTxtUserName();
//		UserVo logUser = new UserVo(un, pas);
//		if(userDao.checkPassword(logUser)) {
//			daofactory.setCurrent_user(un);
//			System.out.println(daofactory.getCurrent_user());
//			return true;
//		}
//		return false;
//	}
//
//	public UserVo createUser() {
//		un = view.getTxtUserName();
//		pas = view.getTxtPassword();
//		if(userDao.checkUsernameExists(un) == false) {
//			UserVo logUser = new UserVo(un, pas);
//			if (userDao.validatePassword(logUser) == true) {
//				JOptionPane.showMessageDialog(null,"Registration successful :)");
//				userDao.insert(logUser);
//				return logUser;
//			}
//		}else JOptionPane.showMessageDialog(null,"Oh no :( Username is already taken");
//		return null;
//	}
//
//	public void actionPerformed(ActionEvent e) {
//		Object src = e.getSource();
//		un = view.getTxtUserName();
//		pas = view.getTxtPassword();
//		UserVo login = new UserVo(un, pas);
//
//		if (src == view.btnLogin) {
//			if(this.loginUser()) {
//				view.setTxtUsername("");
//				view.setTxtPassword("");
//				TemplateView templateview = new TemplateView();
//				templateview.setVisible(true);
//				view.getFrame().dispose();
//			}
//			else {
//				JOptionPane.showMessageDialog(null,"Invalid Username or Password :(");
//				view.setTxtUsername("");
//				view.setTxtPassword("");
//
//			}
//		}
//		
//		if (src == view.btnReg) {
//			createUser();
//			view.setTxtUsername("");
//			view.setTxtPassword("");
//		}
//
//	}
//
//
//}
