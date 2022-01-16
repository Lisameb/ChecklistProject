package de.thu.project.main.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import de.thu.project.main.controller.ImportChecklistController;
import java.awt.Font;


public class ImportChecklistView extends JFrame{
	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 560, Image.SCALE_SMOOTH);
	
	public JPanel contentPane;
	public JTextField tfName;
	public JLabel lblNameChecklist;
	public JTextArea taItems;
	public JButton btnImport;
	public JButton btnChooseFile;
	public JButton btnCancel;
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);
	private ImportChecklistController importCo;
	

	public ImportChecklistView() {
		initialize();
	}


	public void initialize() {
		
		importCo = new ImportChecklistController(this);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 585);
		contentPane = new JPanel();
		contentPane.setBackground(clBackground);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 223, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnChooseFile = new JButton("Choose File");
		btnChooseFile.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChooseFile.setBounds(12, 34, 172, 25);
		panel.add(btnChooseFile);
		btnChooseFile.addActionListener(importCo);
		
		btnImport = new JButton("Import");
		btnImport.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnImport.setBounds(12, 460, 97, 25);
		panel.add(btnImport);
		btnImport.addActionListener(importCo);
		
		tfName = new JTextField();
		tfName.setBounds(12, 425, 124, 22);
		panel.add(tfName);
		tfName.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancel.setBounds(12, 500, 97, 25);
		btnCancel.addActionListener(importCo);
		panel.add(btnCancel);
		
		lblNameChecklist = new JLabel("Name your Checklist:");
		lblNameChecklist.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNameChecklist.setBounds(12, 404, 196, 16);
		panel.add(lblNameChecklist);
		
		
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 235, 560);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);
		
		taItems = new JTextArea();
		taItems.setEditable(false);
		contentPane.add(taItems);
		
		JScrollPane spItems = new JScrollPane(taItems);
		spItems.setBounds(247, 13, 305, 387);
		contentPane.add(spItems);
	}
}
