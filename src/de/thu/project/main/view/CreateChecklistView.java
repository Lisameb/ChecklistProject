package de.thu.project.main.view;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import de.thu.project.main.controller.CreateChecklistController;

import java.awt.SystemColor;

import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Font;


public class CreateChecklistView extends JFrame {

	public JPanel contentPane;

	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 530, Image.SCALE_SMOOTH);
	private Image img_icon = new ImageIcon(this.getClass().getResource("/new.png")).getImage().getScaledInstance(87, 87, Image.SCALE_SMOOTH);

	public JTextField tfName;
	public JTextField tfAmount;
	public JLabel lblMenTemp;
	public JLabel lblMenCheck;
	public JLabel lblMenBack;
	public JLabel lblName;
	
	public JPanel panMenTemp; 
	public JPanel panMenCheck;
	public JPanel panMenBack; 
	
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);
	
	public JTextArea taChecklist;
	public JComboBox<String> comboBoxCat;
	public JComboBox<String> comboBoxItems;
	public JButton btnDelete;
	public JButton btnReset;
	public JButton btnAdd;
	public JButton btnShowItems;

	public CreateChecklistController clController;
	public int temp_id;
	
	public CreateChecklistView(CreateChecklistController createCon) {
		this.clController = createCon;
		initialize();
	}

	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 585);
//		setExtendedState(JFrame.);
		contentPane = new JPanel();
		contentPane.setBackground(clBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 220, 530);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panMenTemp = new JPanel();
		panMenTemp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panMenTemp.setBackground(clBorder);
		panMenTemp.setBounds(0, 334, 219, 60);
		panel.add(panMenTemp);
		panMenTemp.setLayout(null);
		panMenTemp.addMouseListener(clController);
		
		lblMenTemp = new JLabel("templates");
		lblMenTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMenTemp.setBounds(15, 21, 112, 20);
		panMenTemp.add(lblMenTemp);
		
		panMenCheck = new JPanel();
		panMenCheck.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenCheck.setBackground(clBorder);
		panMenCheck.setBounds(0, 394, 219, 60);
		panel.add(panMenCheck);
		panMenCheck.setLayout(null);
		panMenCheck.addMouseListener(clController);
		
		lblMenCheck = new JLabel("your checklists");
		lblMenCheck.setBounds(15, 21, 134, 20);
		panMenCheck.add(lblMenCheck);
		lblMenCheck.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		panMenBack = new JPanel();
		panMenBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenBack.setBackground(clBorder);
		panMenBack.setBounds(0, 454, 219, 60);
		panel.add(panMenBack);
		panMenBack.setLayout(null);
		panMenBack.addMouseListener(clController);
		
		lblMenBack = new JLabel("back to menu");
		lblMenBack.setBounds(15, 21, 136, 20);
		panMenBack.add(lblMenBack);
		lblMenBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblGoTo = new JLabel("Go To...");
		lblGoTo.setFont(new Font("Goudy Stout", Font.BOLD, 21));
		lblGoTo.setBounds(15, 275, 178, 37);
		panel.add(lblGoTo);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(15, 16, 80, 69);
		lblIcon.setIcon(new ImageIcon(img_icon));
		panel.add(lblIcon);
		
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 220, 530);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);
		
		taChecklist = new JTextArea();
		taChecklist.setEditable(false);
		contentPane.add(taChecklist);
		
		JScrollPane spChecklist = new JScrollPane(taChecklist);
		spChecklist.setBounds(487, 129, 259, 401);
		contentPane.add(spChecklist);
		
		comboBoxCat = new JComboBox<String>();
		comboBoxCat.addActionListener(clController);
		comboBoxCat.setBounds(245, 177, 220, 26);
		contentPane.add(comboBoxCat);
		
		comboBoxItems = new JComboBox<String>();
		comboBoxItems.addActionListener(clController);
		comboBoxItems.setBounds(245, 247, 220, 26);
		contentPane.add(comboBoxItems);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(245, 147, 103, 20);
		contentPane.add(lblCategory);
		
		JLabel lblListOfItems = new JLabel("List of items");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfItems.setBounds(245, 217, 115, 20);
		contentPane.add(lblListOfItems);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmount.setBounds(245, 295, 85, 20);
		contentPane.add(lblAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(245, 325, 69, 26);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		JLabel lblTitle = new JLabel("Create your checklist");
		lblTitle.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblTitle.setBounds(307, 16, 357, 20);
		contentPane.add(lblTitle);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.addActionListener(clController);
		btnAdd.setBounds(245, 439, 100, 29);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.addActionListener(clController);
		btnDelete.setBounds(365, 439, 100, 29);
		contentPane.add(btnDelete);
		
		lblName = new JLabel();
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(245, 67, 501, 20);
		contentPane.add(lblName);

	}
}
