package de.thu.project.main.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import de.thu.project.main.controller.ChangeChecklistController;


public class ChangeChecklistView extends JFrame{
	public JPanel contentPane;

	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 560, Image.SCALE_SMOOTH);
	private Image img_icon = new ImageIcon(this.getClass().getResource("/new.png")).getImage().getScaledInstance(87, 87, Image.SCALE_SMOOTH);

	public JTextField tfName;
	public JTextField tfAmount;
	public JLabel lblMenTemp;
	public JLabel lblMenCheck;
	public JLabel lblMenBack;

	public JPanel panMenTemp;
	public JPanel panMenCheck;
	public JPanel panMenBack;
	public JPanel panMenItem;
	public JPanel panel;


	public JTextArea taChecklist;
	public JComboBox<String> comboBoxChecklist;
	public JComboBox<String> comboBoxCat;
	public JComboBox<String> comboBoxItems;
	public JButton btnSave;
	public JButton btnDelete;
	public JButton btnAdd;
	public JButton btnDeleteChecklist;
	private String chosenChecklist;
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);

	private ChangeChecklistController changeCo;

	public ChangeChecklistView() {
		initialize();
	}

	public ChangeChecklistView(String chosenChecklist) { 
		this.setChosenChecklist(chosenChecklist);
		initialize();
	}

	private void initialize() {
		changeCo = new ChangeChecklistController(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 585);
		contentPane = new JPanel();
		contentPane.setBackground(clBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 220, 560);
		contentPane.add(panel);
		panel.setLayout(null);

		panMenTemp = new JPanel();
		panMenTemp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenTemp.setBackground(clBorder);
		panMenTemp.setBounds(0, 322, 219, 60);
		panel.add(panMenTemp);
		panMenTemp.setLayout(null);
		panMenTemp.addMouseListener(changeCo);

		lblMenTemp = new JLabel("templates");
		lblMenTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMenTemp.setBounds(15, 21, 112, 20);
		panMenTemp.add(lblMenTemp);

		panMenCheck = new JPanel();
		panMenCheck.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenCheck.setBackground(clBorder);
		panMenCheck.setBounds(0, 382, 219, 60);
		panel.add(panMenCheck);
		panMenCheck.setLayout(null);
		panMenCheck.addMouseListener(changeCo);

		lblMenCheck = new JLabel("your checklists");
		lblMenCheck.setBounds(15, 21, 134, 20);
		panMenCheck.add(lblMenCheck);
		lblMenCheck.setFont(new Font("Tahoma", Font.BOLD, 16));

		panMenBack = new JPanel();
		panMenBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenBack.setBackground(clBorder);
		panMenBack.setBounds(0, 443, 219, 60);
		panel.add(panMenBack);
		panMenBack.setLayout(null);
		panMenBack.addMouseListener(changeCo);

		lblMenBack = new JLabel("back to menu");
		lblMenBack.setBounds(15, 21, 136, 20);
		panMenBack.add(lblMenBack);
		lblMenBack.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblGoTo = new JLabel("Go To...");
		lblGoTo.setFont(new Font("Goudy Stout", Font.BOLD, 21));
		lblGoTo.setBounds(15, 226, 178, 37);
		panel.add(lblGoTo);

		JLabel lblIcon = new JLabel("");
		lblIcon.setBounds(15, 16, 80, 69);
		lblIcon.setIcon(new ImageIcon(img_icon));
		panel.add(lblIcon);

		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 220, 560);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);

		JLabel lblTitle = new JLabel("Modify your checklist");
		lblTitle.setFont(new Font("Gill Sans Nova Cond Ultra Bold", Font.BOLD, 24));
		lblTitle.setBounds(307, 16, 357, 40);
		contentPane.add(lblTitle);

		taChecklist = new JTextArea();
		taChecklist.setEditable(false);
		contentPane.add(taChecklist);

		JScrollPane spChecklist = new JScrollPane(taChecklist);
		spChecklist.setBounds(489, 67, 259, 401);
		contentPane.add(spChecklist);

		JLabel lblName = new JLabel("Change name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(245, 112, 115, 20);
		contentPane.add(lblName);

		btnSave = new JButton("Save name");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.addActionListener(changeCo);
		btnSave.setForeground(Color.BLACK);
		btnSave.setBounds(245, 169, 127, 29);
		contentPane.add(btnSave);

		comboBoxItems = new JComboBox<String>();
		comboBoxItems.setToolTipText("");
		comboBoxItems.addActionListener(changeCo);
		comboBoxItems.setBounds(245, 331, 220, 26);
		contentPane.add(comboBoxItems);

		comboBoxCat = new JComboBox<String>();
		comboBoxCat.addActionListener(changeCo);
		comboBoxCat.setBounds(245, 250, 220, 26);
		changeCo.setComboBoxCat();
		contentPane.add(comboBoxCat);


		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(245, 221, 103, 20);
		contentPane.add(lblCategory);

		JLabel lblListOfItems = new JLabel("List of items");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfItems.setBounds(245, 304, 115, 20);
		contentPane.add(lblListOfItems);

		tfName = new JTextField();
		tfName.setBounds(245, 137, 220, 26);
		contentPane.add(tfName);
		tfName.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmount.setBounds(245, 391, 85, 20);
		contentPane.add(lblAmount);

		tfAmount = new JTextField();
		tfAmount.setBounds(245, 422, 69, 26);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(changeCo);
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(248, 496, 100, 29);
		contentPane.add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.addActionListener(changeCo);
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setBounds(365, 496, 100, 29);
		contentPane.add(btnDelete);

		btnDeleteChecklist = new JButton("Delete checklist");
		btnDeleteChecklist.addActionListener(changeCo);
		btnDeleteChecklist.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setForeground(Color.BLACK);
		btnDeleteChecklist.setBounds(538, 496, 210, 29);
		contentPane.add(btnDeleteChecklist);

		comboBoxChecklist = new JComboBox<String>();
		comboBoxChecklist.setBounds(245, 67, 220, 22);
		comboBoxChecklist.addActionListener(changeCo);
		changeCo.setComboBoxCheck();
		comboBoxChecklist.setSelectedItem(chosenChecklist);
		contentPane.add(comboBoxChecklist);

	}

	public String getChosenChecklist() {
		return chosenChecklist;
	}

	public void setChosenChecklist(String chosenChecklist) {
		this.chosenChecklist = chosenChecklist;
	}
}
