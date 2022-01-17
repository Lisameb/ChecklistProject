package de.thu.project.main.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import de.thu.project.main.controller.ItemController;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class ItemView extends JFrame {

	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 560, Image.SCALE_SMOOTH);
	private Image img_icon = new ImageIcon(this.getClass().getResource("/new.png")).getImage().getScaledInstance(87, 87, Image.SCALE_SMOOTH);

	private Frame preView;
	private JPanel contentPane;
	private Image img_sidebar;
	
	public JTextArea taGloballist;
	public JComboBox<String> comboBoxCat;
	
	public JButton btnDelete;
	public JButton btnAdd;
	public JButton btnAddCategory;
	public JButton btnDeleteCategory;
	
	private ItemController itemCon;
	
	private JLabel lblAddCategory;
	public JTextField tfNewItem;
	public JLabel lblAddNewCategory;
	public JTextField tfAddCategory;
	
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);
	
	public JPanel panMenTemp; 
	public JPanel panMenCheck;
	public JPanel panMenBack; 
	public JLabel lblMenTemp;
	public JLabel lblMenCheck;
	public JLabel lblMenBack;
	public JButton btnShowGloballistOf;
	private JLabel lblGlobalListOf;
	public JButton btnChangeCategory;


	public ItemView(Frame preView) {
		this.preView = preView;
		initialize();
		itemCon.setGloballist();
		
	}
	
	private void initialize() {
		
		itemCon = new ItemController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 585);

		contentPane = new JPanel();
		contentPane.setBackground(clBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 220, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panMenTemp = new JPanel();
		panMenTemp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, clBorder, null, null));
		panMenTemp.setBackground(clBorder);
		panMenTemp.setBounds(0, 334, 219, 60);
		panel.add(panMenTemp);
		panMenTemp.setLayout(null);
		panMenTemp.addMouseListener(itemCon);
		
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
		panMenCheck.addMouseListener(itemCon);
		
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
		panMenBack.addMouseListener(itemCon);
		
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
		lblBack.setBounds(0, 0, 220, 560);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);
		
		
		taGloballist = new JTextArea();
		taGloballist.setEditable(false);
		contentPane.add(taGloballist);
		
		JScrollPane spChecklist = new JScrollPane(taGloballist);
		spChecklist.setBounds(232, 158, 235, 345);
		contentPane.add(spChecklist);
		
		
		comboBoxCat = new JComboBox<String>();
		comboBoxCat.addActionListener(itemCon);
		comboBoxCat.setBounds(514, 347, 220, 26);
		contentPane.add(comboBoxCat);
		itemCon.setComboBoxCat();
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(514, 319, 103, 20);
		contentPane.add(lblCategory);
		
		JLabel lblListOfItems = new JLabel("Name of item");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfItems.setBounds(514, 386, 115, 20);
		contentPane.add(lblListOfItems);
		
		JLabel lblTitle = new JLabel("Manage categories and items");
		lblTitle.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblTitle.setBounds(307, 16, 365, 20);
		contentPane.add(lblTitle);
		
		btnAdd = new JButton("Add item");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAdd.addActionListener(itemCon);
		btnAdd.setBounds(594, 445, 125, 26);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete item");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.addActionListener(itemCon);
		btnDelete.setBounds(594, 477, 125, 26);
		contentPane.add(btnDelete);
		
		tfNewItem = new JTextField();
		tfNewItem.setBounds(514, 410, 215, 22);
		contentPane.add(tfNewItem);
		tfNewItem.setColumns(10);
		
		lblAddNewCategory = new JLabel("Name of category");
		lblAddNewCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddNewCategory.setBounds(514, 160, 173, 20);
		contentPane.add(lblAddNewCategory);
		
		tfAddCategory = new JTextField();
		tfAddCategory.setBounds(514, 187, 215, 22);
		contentPane.add(tfAddCategory);
		tfAddCategory.setColumns(10);
		
		btnAddCategory = new JButton("Add category");
		btnAddCategory.addActionListener(itemCon);
		btnAddCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddCategory.setBounds(580, 222, 154, 26);
		contentPane.add(btnAddCategory);
		
		btnDeleteCategory = new JButton("Delete category");
		btnDeleteCategory.addActionListener(itemCon);
		btnDeleteCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeleteCategory.setBounds(580, 252, 154, 26);
		contentPane.add(btnDeleteCategory);
		
		lblGlobalListOf = new JLabel("Global list of items");
		lblGlobalListOf.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGlobalListOf.setBounds(233, 125, 173, 20);
		contentPane.add(lblGlobalListOf);
		
		btnChangeCategory = new JButton("Change category");
		btnChangeCategory.addActionListener(itemCon);
		btnChangeCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnChangeCategory.setBounds(575, 508, 159, 26);
		contentPane.add(btnChangeCategory);
		
	}
}
