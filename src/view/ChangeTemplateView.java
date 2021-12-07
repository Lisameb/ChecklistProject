package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ChangeTemplateController;
import control.ItemController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class  ChangeTemplateView extends JFrame {

	public JPanel contentPane;
	public JTextArea textArea;
	public JTextField textFieldAmount;
	public JComboBox comboBoxTemp;
	public JComboBox comboBoxCategory;
	public JComboBox comboBoxItem;
	public JButton btnAddItemTo;
	public JButton btnSaveTemp;
	public JButton btnDeleteTemp;
	public JButton btnDeleteItemTo;
	
	
	private ChangeTemplateController contro;
	

	public ChangeTemplateView() {
		initialize();
	}
	
	private void initialize() {
		
		contro = new ChangeTemplateController(this);
		
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
		
		JLabel lblChooseTemplate = new JLabel("Choose template");
		lblChooseTemplate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChooseTemplate.setBounds(12, 13, 131, 23);
		panel.add(lblChooseTemplate);
		
		comboBoxTemp = new JComboBox<String>();
		comboBoxTemp.addActionListener(contro);
		comboBoxTemp.setBounds(12, 47, 131, 22);
		contro.setComboBoxTemp();
		panel.add(comboBoxTemp);
		
		btnSaveTemp = new JButton("Save changes");
		btnSaveTemp.addActionListener(contro);
		btnSaveTemp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaveTemp.setBackground(new Color(176, 224, 230));
		btnSaveTemp.setBounds(12, 297, 131, 25);
		panel.add(btnSaveTemp);
		
		btnDeleteTemp = new JButton("Delete template");
		btnDeleteTemp.addActionListener(contro);
		btnDeleteTemp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDeleteTemp.setBackground(new Color(176, 224, 230));
		btnDeleteTemp.setBounds(0, 341, 155, 25);
		panel.add(btnDeleteTemp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(155, 0, 450, 379);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBoxItem = new JComboBox<String>();
		comboBoxItem.addActionListener(contro);
		comboBoxItem.setBackground(new Color(169, 169, 169));
		comboBoxItem.setBounds(131, 265, 262, 22);
		panel_1.add(comboBoxItem);
		
		JLabel lblListOfItems = new JLabel("List of Items");
		lblListOfItems.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblListOfItems.setBounds(12, 266, 112, 16);
		panel_1.add(lblListOfItems);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(12, 319, 75, 22);
		panel_1.add(lblAmount);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(81, 320, 52, 22);
		panel_1.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		btnAddItemTo = new JButton("Add Item");
		btnAddItemTo.addActionListener(contro);
		btnAddItemTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAddItemTo.setBackground(new Color(176, 224, 230));
		btnAddItemTo.setBounds(267, 319, 125, 25);
		panel_1.add(btnAddItemTo);
		
		JLabel lblCategories = new JLabel("Category");
		lblCategories.setBounds(12, 219, 110, 34);
		panel_1.add(lblCategories);
		lblCategories.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		comboBoxCategory = new JComboBox<String>();
		comboBoxCategory.addActionListener(contro);
		comboBoxCategory.setBounds(131, 230, 135, 22);
		contro.setComboBoxCat();
		panel_1.add(comboBoxCategory);
		comboBoxCategory.setBackground(new Color(169, 169, 169));
		
		textArea = new JTextArea();
		textArea.setBounds(26, 34, 334, 158);
		panel_1.add(textArea);
		
		JLabel lblTemplateItems = new JLabel("Template Items");
		lblTemplateItems.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTemplateItems.setBounds(26, 13, 234, 16);
		panel_1.add(lblTemplateItems);
		
		btnDeleteItemTo = new JButton("Delete Item");
		btnDeleteItemTo.addActionListener(contro);
		btnDeleteItemTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDeleteItemTo.setBackground(new Color(176, 224, 230));
		btnDeleteItemTo.setBounds(267, 354, 125, 25);
		panel_1.add(btnDeleteItemTo);
	}
}
