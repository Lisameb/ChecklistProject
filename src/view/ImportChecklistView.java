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
	public JTextField textField;
	public JLabel lblNameChecklist;
	public JTextArea textArea;
	public JButton btnImport;
	public JButton btnChooseFile;
	private ImportChecklistController importCo;
	

	public ImportChecklistView() {
		initialize();
	}


	public void initialize() {
		
		importCo = new ImportChecklistController(this);
		
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
		btnImport.setBounds(12, 476, 97, 25);
		panel.add(btnImport);
		btnImport.addActionListener(importCo);
		
		textField = new JTextField();
		textField.setBounds(12, 425, 124, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		lblNameChecklist = new JLabel("Name your Checklist:");
		lblNameChecklist.setBounds(12, 404, 172, 16);
		panel.add(lblNameChecklist);
		
		
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 235, 538);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);
		
		textArea = new JTextArea();
		//JScrollPane sp = new JScrollPane(textArea);
		textArea.setBounds(247, 13, 305, 387);
		contentPane.add(textArea);
	}
}
