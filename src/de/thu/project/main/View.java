package de.thu.project.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

public class View {

	private JFrame frame;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private final JPanel panel = new JPanel();



	/**
	 * Create the application.
	 */
	public View() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().getContentPane().setBackground(SystemColor.activeCaption);
		getFrame().getContentPane().setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(335, 199, 205, 34);
		getFrame().getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(335, 257, 205, 34);
		getFrame().getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setForeground(SystemColor.textHighlightText);
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 19));
		lblUsername.setBounds(214, 262, 117, 20);
		getFrame().getContentPane().add(lblUsername);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("Verdana", Font.PLAIN, 19));
		lblUserName.setForeground(SystemColor.textHighlightText);
		lblUserName.setBackground(SystemColor.window);
		lblUserName.setBounds(214, 200, 130, 29);
		getFrame().getContentPane().add(lblUserName);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(255, 204, 153));
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//get text from user
				//get text from password
				
				String un = txtUserName.getText();
				String pas = txtPassword.getText();
				
			}
		});
		
		JLabel lblUserLogin = new JLabel("User LOGIN");
		lblUserLogin.setForeground(SystemColor.control);
		lblUserLogin.setFont(new Font("Verdana", Font.BOLD, 29));
		lblUserLogin.setBackground(SystemColor.scrollbar);
		lblUserLogin.setBounds(265, 72, 213, 71);
		getFrame().getContentPane().add(lblUserLogin);
		btnLogin.setBounds(301, 358, 183, 34);
		getFrame().getContentPane().add(btnLogin);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 146, 480);
		getFrame().getContentPane().add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(-305, 0, 928, 480);
		getFrame().getContentPane().add(label);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/checklist1.png"));
		label.setIcon(icon);
		getFrame().setBounds(100, 100, 631, 536);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

