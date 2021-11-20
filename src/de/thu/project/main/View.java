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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		frame.getContentPane().setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(335, 199, 205, 34);
		frame.getContentPane().add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(335, 257, 205, 34);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Password");
		lblUsername.setForeground(SystemColor.textHighlightText);
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 19));
		lblUsername.setBounds(214, 262, 117, 20);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblUserName = new JLabel("Username");
		lblUserName.setFont(new Font("Verdana", Font.PLAIN, 19));
		lblUserName.setForeground(SystemColor.textHighlightText);
		lblUserName.setBackground(SystemColor.window);
		lblUserName.setBounds(214, 200, 130, 29);
		frame.getContentPane().add(lblUserName);
		
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
		frame.getContentPane().add(lblUserLogin);
		btnLogin.setBounds(301, 358, 183, 34);
		frame.getContentPane().add(btnLogin);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 146, 480);
		frame.getContentPane().add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(-305, 0, 928, 480);
		frame.getContentPane().add(label);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/checklist1.png"));
		label.setIcon(icon);
		frame.setBounds(100, 100, 631, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

