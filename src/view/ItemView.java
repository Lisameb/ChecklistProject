package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ItemController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemView extends JFrame {

	private JPanel contentPane;
	public JComboBox<String> comboBoxCategory;
	public JButton btnAddItemTo;
	private ItemController itemCon;
	public JTextField tfItem;
	
	//Nice to have: ausgewählte Category in Label anzeigen

	public ItemView() {
		initialize();
	}
	
	private void initialize() {
		
		itemCon = new ItemController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 153, 287);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(22, 24, 110, 34);
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblCategories);
		
		comboBoxCategory = new JComboBox<String>();
		comboBoxCategory.addActionListener(itemCon);
		comboBoxCategory.setBackground(new Color(169, 169, 169));
		comboBoxCategory.setBounds(22, 74, 110, 22);
		itemCon.setComboBoxCat();
		panel.add(comboBoxCategory);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(155, 0, 353, 287);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblListOfItems = new JLabel("Add Item");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblListOfItems.setBounds(26, 35, 132, 16);
		panel_1.add(lblListOfItems);
		
		btnAddItemTo = new JButton("Add Item to database");
		btnAddItemTo.addActionListener(itemCon);
		btnAddItemTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddItemTo.setBackground(new Color(176, 224, 230));
		btnAddItemTo.setBounds(149, 230, 182, 25);
		panel_1.add(btnAddItemTo);
		
		tfItem = new JTextField();
		tfItem.setBounds(26, 75, 200, 22);
		panel_1.add(tfItem);
		tfItem.setColumns(10);
		
	}
}
