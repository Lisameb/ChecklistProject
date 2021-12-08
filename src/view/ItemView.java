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
	private JTextField textFieldAmount;
	private JComboBox comboBoxCategory;
	private JComboBox comboBoxItem;
	private JButton btnAddItemTo;
	private ItemController itemCon;
	

	public ItemView() {
		initialize();
	}
	
	private void initialize() {
		
		itemCon = new ItemController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBounds(0, 0, 155, 379);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(22, 24, 110, 34);
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblCategories);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.addActionListener(itemCon);
		comboBoxCategory.setBackground(new Color(169, 169, 169));
		comboBoxCategory.setBounds(22, 74, 110, 22);
		panel.add(comboBoxCategory);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(155, 0, 450, 379);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBoxItem = new JComboBox();
		comboBoxItem.setBackground(new Color(169, 169, 169));
		comboBoxItem.setBounds(22, 74, 262, 22);
		panel_1.add(comboBoxItem);
		
		JLabel lblListOfItems = new JLabel("List of Items");
		lblListOfItems.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListOfItems.setBounds(26, 35, 262, 16);
		panel_1.add(lblListOfItems);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAmount.setBounds(26, 331, 75, 22);
		panel_1.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(100, 332, 52, 22);
		panel_1.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		btnAddItemTo = new JButton("Add Item to template");
		btnAddItemTo.addActionListener(itemCon);
		btnAddItemTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddItemTo.setBackground(new Color(176, 224, 230));
		btnAddItemTo.setBounds(229, 331, 182, 25);
		panel_1.add(btnAddItemTo);
	}
	
}
