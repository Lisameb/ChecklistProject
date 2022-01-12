package de.thu.project.main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import de.thu.project.main.controller.ChangeTemplateController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class  ChangeTemplateView extends JFrame {

	public JPanel contentPane;
	public JTextField tfAmount;
	public JComboBox<String> comboBoxTemp;
	public JComboBox<String> comboBoxCategory;
	public JComboBox<String> comboBoxItem;
	public JButton btnAddItemTo;
	public JButton btnNewTemp;
	public JButton btnDeleteTemp;
	public JButton btnDeleteItemTo;
	public JButton btnAddNewItem;
	public JTextArea taItemList;
	private Image img_sidebar;
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);
	
	
	public ChangeTemplateController contro;
	public JButton btnShowItems;
	public JPanel btnBack;
	

	public ChangeTemplateView() {
		initialize();
	}
	
	private void initialize() {
		contro = new ChangeTemplateController(this);
		img_sidebar = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 546, Image.SCALE_SMOOTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 220, 546);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(clBackground);
		panel_1.setBounds(218, 0, 551, 546);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblChooseTemplate = new JLabel("Choose template");
		lblChooseTemplate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChooseTemplate.setBounds(24, 13, 168, 23);
		panel.add(lblChooseTemplate);
		
		comboBoxTemp = new JComboBox<String>();
		comboBoxTemp.setBounds(24, 47, 168, 22);
		contro.setComboBoxTemp();
		panel.add(comboBoxTemp);
		
		btnNewTemp = new JButton("New template");
		btnNewTemp.addActionListener(contro);
		btnNewTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewTemp.setBounds(24, 266, 168, 36);
		panel.add(btnNewTemp);
		
		btnDeleteTemp = new JButton("Delete template");
		btnDeleteTemp.addActionListener(contro);
		btnDeleteTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteTemp.setBounds(24, 313, 168, 38);
		panel.add(btnDeleteTemp);
		
		btnAddNewItem = new JButton("Create Item");
		btnAddNewItem.addActionListener(contro);
		btnAddNewItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddNewItem.setBounds(24, 192, 168, 25);
		panel.add(btnAddNewItem);
		
		JLabel lblAddNewItem = new JLabel("Add to global list");
		lblAddNewItem.setBounds(24, 168, 131, 23);
		lblAddNewItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblAddNewItem);
		
		btnShowItems = new JButton("Show Items");
		btnShowItems.addActionListener(contro);
		btnShowItems.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnShowItems.setBounds(24, 80, 168, 22);
		panel.add(btnShowItems);
		
		comboBoxItem = new JComboBox<String>();
		comboBoxItem.setToolTipText("");
		comboBoxItem.addActionListener(contro);
		comboBoxItem.setBounds(158, 389, 347, 22);
		panel_1.add(comboBoxItem);
		
		JLabel lblListOfItems = new JLabel("List of Items");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblListOfItems.setBounds(36, 390, 112, 16);
		panel_1.add(lblListOfItems);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAmount.setBounds(36, 443, 75, 22);
		panel_1.add(lblAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(155, 445, 52, 22);
		panel_1.add(tfAmount);
		tfAmount.setColumns(10);
		
		btnAddItemTo = new JButton("Add Item");
		btnAddItemTo.addActionListener(contro);
		btnAddItemTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddItemTo.setBounds(377, 443, 125, 25);
		panel_1.add(btnAddItemTo);
		
		JLabel lblCategories = new JLabel("Category");
		lblCategories.setBounds(36, 343, 110, 34);
		panel_1.add(lblCategories);
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		comboBoxCategory = new JComboBox<String>();
		comboBoxCategory.addActionListener(contro);
		comboBoxCategory.setBounds(158, 354, 347, 22);
		contro.setComboBoxCat();
		panel_1.add(comboBoxCategory);	
		
		JLabel lblTemplateItems = new JLabel("Template Items");
		lblTemplateItems.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblTemplateItems.setBounds(45, 16, 234, 22);
		panel_1.add(lblTemplateItems);
		
		btnDeleteItemTo = new JButton("Delete Item");
		btnDeleteItemTo.addActionListener(contro);
		btnDeleteItemTo.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDeleteItemTo.setBounds(377, 478, 125, 25);
		panel_1.add(btnDeleteItemTo);
		
		taItemList = new JTextArea();
		taItemList.setText("Choose template");
		taItemList.setEditable(false);
		panel_1.add(taItemList);
		
		JScrollPane spItemList = new JScrollPane(taItemList);
		spItemList.setBounds(46, 42, 456, 263);
		panel_1.add(spItemList);
		
		btnBack = new JPanel();
		btnBack.setLayout(null);
		btnBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		btnBack.setBackground(clBorder);
		btnBack.setBounds(0, 446, 219, 60);
		btnBack.addMouseListener(contro);
		panel.add(btnBack);
		
		JLabel label = new JLabel("back to menu");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(15, 21, 136, 20);
		btnBack.add(label);
		
		
				JLabel lblBack = new JLabel("");
				lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
				lblBack.setBounds(0, 0, 220, 546);
				lblBack.setIcon(new ImageIcon(img_sidebar));
				panel.add(lblBack);
		
	}
}
