package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import controller.PasswordController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChangePasswordView extends JFrame {

	private JPanel contentPane;
	private PasswordController passCon;
	private Image img_sidebar;
	private Frame preView;
	public JPasswordField tfCurrPw;
	public JPasswordField tfNewPw1;
	public JPasswordField tfNewPw2;
	public JButton btnChange;
	public JLabel lblWrongNewPw;
	public JLabel lblWrongPw;

	public ChangePasswordView() {
		initialize();
	}
	
	private void initialize() {
		
		passCon = new PasswordController(this);
		img_sidebar = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(508, 287, Image.SCALE_SMOOTH);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 526, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 508, 287);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblChangePassword.setBounds(137, 24, 224, 20);
		panel.add(lblChangePassword);
		
		JLabel lblNewLabel = new JLabel("Type in current password:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 81, 176, 19);
		panel.add(lblNewLabel);
		
		tfCurrPw = new JPasswordField();
		tfCurrPw.setBounds(233, 77, 224, 28);
		panel.add(tfCurrPw);
		tfCurrPw.setColumns(10);
		
		JLabel lblNewPw1 = new JLabel("Type in new password:");
		lblNewPw1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewPw1.setBounds(22, 139, 176, 19);
		panel.add(lblNewPw1);
		
		tfNewPw1 = new JPasswordField();
		tfNewPw1.setColumns(10);
		tfNewPw1.setBounds(233, 135, 224, 28);
		panel.add(tfNewPw1);
		
		JLabel lblNewPw2 = new JLabel("Repeat new password:");
		lblNewPw2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewPw2.setBounds(22, 178, 176, 19);
		panel.add(lblNewPw2);
		
		tfNewPw2 = new JPasswordField();
		tfNewPw2.setColumns(10);
		tfNewPw2.setBounds(233, 174, 224, 28);
		panel.add(tfNewPw2);
		
		btnChange = new JButton("Save password");
		btnChange.addActionListener(passCon);
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnChange.setBounds(281, 234, 176, 29);
		panel.add(btnChange);
		
		lblWrongPw = new JLabel("");
		lblWrongPw.setForeground(Color.RED);
		lblWrongPw.setBounds(243, 106, 200, 16);
		panel.add(lblWrongPw);
		
		lblWrongNewPw = new JLabel("");
		lblWrongNewPw.setForeground(Color.RED);
		lblWrongNewPw.setBounds(243, 202, 200, 16);
		panel.add(lblWrongNewPw);
		
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 509, 287);
		lblBack.setIcon(new ImageIcon(img_sidebar));
		panel.add(lblBack);
		
	}
}
