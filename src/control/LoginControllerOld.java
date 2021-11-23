//package control;
//
//import java.awt.Component;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JOptionPane;
//
//import model.UserVo;
//import obsolete.UserFactory;
//import obsolete.UserLogin;
//import view.View;
//
//public class LoginControllerOld implements ActionListener{
//	
//	View view;
//	private String un;
//	private String pas;
//	
//	public LoginControllerOld(View view) {
//		this.view = view;
//	}
//	
//
//	public UserVo loginUser() {
//		
//		UserFactory userfac = UserFactory.getInstance();
//		UserLogin log = new UserLogin();
//		UserVo testUser = new UserVo("admin", "123456");
//		userfac.getUsers().add((UserVo) testUser);
//		log.setName(un);
//		log.setPassword(pas);
//		if(log.checkLoginData(userfac.getUsers()))
//		{
//			return log.getMatchedUser();
//		}
//		
//		return null;
//	}
//	
//	public UserVo createUser() {
//		
//		UserFactory userfac = UserFactory.getInstance();
//		UserVo newUser = (UserVo) userfac.getUserVo(un,pas);
//		
//		return newUser;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//			//get text from user
//			//get text from password
//			
//			un = view.getTxtUserName().getText();
//			pas = view.getTxtPassword().getText();
//			
//			UserVo login = loginUser();
//			
//			if(login != null) {
//				JOptionPane.showMessageDialog(null,"Login Successful");
//				view.getTxtUserName().setText("");
//				view.getTxtPassword().setText("");
//				System.out.println(login.getUsername());
//				System.out.println(login.getPassword());
//				
//			}
//			else {
//				JOptionPane.showMessageDialog(null,"Invalid Username or Password");
//				view.getTxtUserName().setText("");
//				view.getTxtPassword().setText("");
//
//			}
//			
//		
//	}
//	
//}
