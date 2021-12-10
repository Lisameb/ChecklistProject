package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ChangeTemplateController;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class CreateTemplateView extends JFrame{
	
	public JPanel contentPane;
	public JButton btnNew;
	public JTextField tfName;
	public JTextField tfNameExists;
	public ChangeTemplateController contro;
	

	public CreateTemplateView(ChangeTemplateController contro) {
		this.contro = contro;
		initialize();
	}
	public void initialize() {
		
		//contro = new ChangeTemplateController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 230);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 282, 183);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		btnNew = new JButton("Create new Template");
		btnNew.addActionListener(contro);
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNew.setBackground(new Color(176, 224, 230));
		btnNew.setBounds(43, 130, 184, 25);
		panel.add(btnNew);
		
		tfName = new JTextField();
		tfName.setToolTipText("Enter name");
		tfName.setBounds(88, 66, 140, 22);
		panel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(21, 69, 56, 16);
		panel.add(lblName);
		
		JLabel lblCreateANew = new JLabel("Create a new Template");
		lblCreateANew.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCreateANew.setBounds(22, 13, 206, 16);
		panel.add(lblCreateANew);
		
		tfNameExists = new JTextField();
		tfNameExists.setForeground(Color.RED);
		tfNameExists.setFont(new Font("Tahoma", Font.ITALIC, 12));
		tfNameExists.setBounds(88, 90, 139, 16);
		tfNameExists.setBackground(new Color(176, 224, 230));
		panel.add(tfNameExists);
		tfNameExists.setColumns(10);
	}
}
