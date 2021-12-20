package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JTextField;

import controller.LoginControllerTest;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class LoginViewTest {

	private JFrame frame;
	private JTextField txtLogUsername;
	private JPasswordField txtLogPassword;
	private JTextField txtRUsername;
	private JPasswordField txtRPassword;
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private LoginControllerTest logcon;
	public JButton btnLogin;
	public JButton btnRReg;

	
	
	/**
	 * Create the application.
	 */
	public LoginViewTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		logcon = new LoginControllerTest(this);
		
		setFrame(new JFrame());
		getFrame().getContentPane().setLayout(null);
		getFrame().setBackground(new Color(240, 240, 240));
		getFrame().setBounds(100, 100, 631, 536);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 609, 480);
		getFrame().getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(309, 0, 300, 480);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblReg = new JLabel("New here ? ");
		lblReg.setForeground(Color.BLACK);
		lblReg.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 22));
		lblReg.setBounds(70, 16, 211, 20);
		panel_1.add(lblReg);
		
		JLabel lblAddYourUsername = new JLabel("Add your username");
		lblAddYourUsername.setForeground(Color.BLACK);
		lblAddYourUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddYourUsername.setBounds(25, 87, 168, 20);
		panel_1.add(lblAddYourUsername);
		
		txtRUsername = new JTextField();
		txtRUsername.setBounds(25, 117, 146, 26);
		panel_1.add(txtRUsername);
		txtRUsername.setColumns(10);
		
		JLabel lblAddYourPassword = new JLabel("Add your password");
		lblAddYourPassword.setForeground(Color.BLACK);
		lblAddYourPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddYourPassword.setBounds(25, 159, 181, 20);
		panel_1.add(lblAddYourPassword);
		
		txtRPassword = new JPasswordField();
		txtRPassword.setBounds(25, 197, 146, 26);
		panel_1.add(txtRPassword);
		
		btnRReg = new JButton("Register");
		btnRReg.addActionListener(logcon);
		btnRReg.setFont(new Font("Verdana", Font.BOLD, 16));
		btnRReg.setBackground(new Color(255, 204, 153));
		btnRReg.setForeground(Color.BLACK);
		btnRReg.setBounds(78, 297, 117, 29);
		panel_1.add(btnRReg);
		
		
		JLabel lblRBack = new JLabel("");
		lblRBack.setBounds(-421, -16, 1072, 496);
		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("/new.png"));
		lblRBack.setIcon(icon1);
		panel_1.add(lblRBack);
		
		JLabel lblLog = new JLabel("Welcome back!");
		lblLog.setForeground(Color.WHITE);
		lblLog.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 21));
		lblLog.setBounds(15, 16, 265, 29);
		panel.add(lblLog);
		
		txtLogUsername = new JTextField();
		txtLogUsername.setBounds(123, 117, 146, 26);
		panel.add(txtLogUsername);
		txtLogUsername.setColumns(10);
		
		JLabel lblUser = new JLabel("UserName");
		lblUser.setFont(new Font("Verdana", Font.BOLD, 16));
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(15, 120, 93, 20);
		panel.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(15, 200, 93, 20);
		panel.add(lblPass);
		
		txtLogPassword = new JPasswordField();
		txtLogPassword.setBounds(123, 197, 146, 26);
		panel.add(txtLogPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(logcon);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBackground(new Color(255, 228, 225));
		btnLogin.setBounds(91, 297, 115, 29);
		panel.add(btnLogin);
		
		JLabel lblLogBack = new JLabel("");
		lblLogBack.setBounds(-404, -35,1092, 529);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/checklist1.png"));
		lblLogBack.setIcon(icon);
		panel.add(lblLogBack);
	}
	

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	
	public String getTxtLogUsername() {
		return txtLogUsername.getText();
	}
	public String getTxtLogPassword() {
		return txtLogPassword.getText();
	}
	public String getTxtRUsername() {
		return txtRUsername.getText();
	}
	public String getTxtRPassword() {
		return txtRPassword.getText();
	}
	
	public void setTxtLogUsername(String userName) {
		txtLogUsername.setText(userName);
	}
	
	public void setTxtLogPassword(String password) {
		txtLogPassword.setText(password);	
	}
	public void setTxtRUsername(String userName) {
		txtRUsername.setText(userName);
	}
	public void setTxtRPassword(String password) {
		txtRPassword.setText(password);
	}
}

