package view;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateChecklistView extends JFrame {

	public JPanel contentPane;

	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 530, Image.SCALE_SMOOTH);
	private Image img_icon = new ImageIcon(this.getClass().getResource("/new.png")).getImage().getScaledInstance(87, 87, Image.SCALE_SMOOTH);

	public JTextField txtTypeItem;
	public JTextField tfAmount;
	public JTextField tfUnit;
	public JLabel lblMenTemp;
	public JLabel lblMenCheck;
	public JLabel lblMenBack;
	public JTextArea taChecklist;
	public JComboBox ComboBoxCat;
	public JComboBox ComboBoxItems;
	public JButton btnSave;
	public JButton btnDelete;
	public JButton btnReset;
	public JButton btnAdd;
	

	public CreateChecklistView() { 
		initialize();
	}

	
	private void initialize() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 585);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 220, 530);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panMenTemp = new JPanel();
		panMenTemp.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panMenTemp.setBackground(SystemColor.inactiveCaptionBorder);
		panMenTemp.setBounds(0, 183, 219, 60);
		panel.add(panMenTemp);
		panMenTemp.setLayout(null);
		
		lblMenTemp = new JLabel("templates");
		lblMenTemp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMenTemp.setBounds(15, 16, 112, 20);
		panMenTemp.add(lblMenTemp);
		
		JPanel panMenCheck = new JPanel();
		panMenCheck.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenCheck.setBackground(SystemColor.inactiveCaptionBorder);
		panMenCheck.setBounds(0, 243, 219, 60);
		panel.add(panMenCheck);
		panMenCheck.setLayout(null);
		
		JLabel lblMenCheck = new JLabel("your checklists");
		lblMenCheck.setBounds(15, 16, 134, 20);
		panMenCheck.add(lblMenCheck);
		lblMenCheck.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel panMenBack = new JPanel();
		panMenBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panMenBack.setBackground(SystemColor.inactiveCaptionBorder);
		panMenBack.setBounds(0, 303, 219, 60);
		panel.add(panMenBack);
		panMenBack.setLayout(null);
		
		lblMenBack = new JLabel("back to menu");
		lblMenBack.setBounds(15, 24, 136, 20);
		panMenBack.add(lblMenBack);
		lblMenBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblGoTo = new JLabel("Go To...");
		lblGoTo.setFont(new Font("Goudy Stout", Font.BOLD, 21));
		lblGoTo.setBounds(15, 124, 178, 37);
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
		taChecklist.setBounds(489, 67, 259, 401);
		contentPane.add(taChecklist);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setForeground(SystemColor.windowText);
		btnSave.setBounds(633, 484, 115, 29);
		contentPane.add(btnSave);
		
		ComboBoxCat = new JComboBox();
//		ComboBoxCat.addActionListener();
		ComboBoxCat.setBounds(245, 95, 220, 26);
		contentPane.add(ComboBoxCat);
		
		ComboBoxItems = new JComboBox();
//		ComboBoxItems.addActionListener();
		ComboBoxItems.setBounds(245, 170, 220, 26);
		contentPane.add(ComboBoxItems);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategory.setBounds(245, 67, 103, 20);
		contentPane.add(lblCategory);
		
		JLabel lblListOfItems = new JLabel("List of items");
		lblListOfItems.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListOfItems.setBounds(245, 142, 115, 20);
		contentPane.add(lblListOfItems);
		
		txtTypeItem = new JTextField();
		txtTypeItem.setText("or type in an item here");
		txtTypeItem.setBounds(245, 229, 220, 26);
		contentPane.add(txtTypeItem);
		txtTypeItem.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmount.setBounds(245, 287, 85, 20);
		contentPane.add(lblAmount);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(245, 315, 69, 26);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUnit.setBounds(353, 287, 69, 20);
		contentPane.add(lblUnit);
		
		tfUnit = new JTextField();
		tfUnit.setBounds(353, 315, 112, 26);
		contentPane.add(tfUnit);
		tfUnit.setColumns(10);
		
		JLabel lblTitle = new JLabel("Create your checklist");
		lblTitle.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblTitle.setBounds(307, 16, 357, 20);
		contentPane.add(lblTitle);
		
		btnReset = new JButton("Reset");
//		btnReset.addActionListener();
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(245, 439, 220, 29);
		contentPane.add(btnReset);
		
		btnAdd = new JButton("Add");
//		btnAdd.addActionListener();
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(245, 378, 100, 29);
		contentPane.add(btnAdd);
		
		btnDelete = new JButton("Delete");
//		btnDelete.addActionListener();
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(365, 378, 100, 29);
		contentPane.add(btnDelete);
	}
}
