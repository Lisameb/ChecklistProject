package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import controller.ImportChecklistController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportChecklistView extends JFrame{
	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 530, Image.SCALE_SMOOTH);
	
	public JPanel contentPane;
	//public JTextArea taChecklist;
	public JTextField tfName;
	public JLabel lblNameChecklist;
	public JTextArea taItems;
	public JButton btnImport;
	public JButton btnChooseFile;
	public JButton btnCancel;
	private ImportChecklistController importCo;
	

	public ImportChecklistView() {
		initialize();
	}


	public void initialize() {
		
		importCo = new ImportChecklistController(this);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 585);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 223, 538);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnChooseFile = new JButton("Choose File");
		btnChooseFile.setBounds(12, 34, 143, 25);
		panel.add(btnChooseFile);
		btnChooseFile.addActionListener(importCo);
		
		btnImport = new JButton("Import");
		btnImport.setBounds(12, 460, 97, 25);
		panel.add(btnImport);
		btnImport.addActionListener(importCo);
		
		tfName = new JTextField();
		tfName.setBounds(12, 425, 124, 22);
		panel.add(tfName);
		tfName.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 500, 97, 25);
		btnCancel.addActionListener(importCo);
		panel.add(btnCancel);
		
		lblNameChecklist = new JLabel("Name your Checklist:");
		lblNameChecklist.setBounds(12, 404, 172, 16);
		panel.add(lblNameChecklist);
		
		
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 235, 538);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);
		
		taItems = new JTextArea();
		//JScrollPane sp = new JScrollPane(textArea);
		taItems.setBounds(247, 13, 305, 387);
		contentPane.add(taItems);
	}
}
