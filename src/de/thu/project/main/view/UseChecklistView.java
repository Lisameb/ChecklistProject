package de.thu.project.main.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import de.thu.project.main.controller.UseChecklistController;
import de.thu.project.main.model.checklist.ChecklistVo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;

/**********************************************
 * UseChecklistView-Class
 * 
 * class creates the GUI for checklist uses;
 * options for displaying or modifying checklists are available;
 * on the bottom left, you can find a small menu
 * 
 **********************************************/

public class UseChecklistView extends JFrame{


	public JComboBox<String> comboBoxChecklist;
	public JButton btnModify;
	public JButton btnOpen;
	public JButton btnExport;
	public JButton btnCreatePdf;
	public JCheckBox checkBox_checklist;
	//private JCheckBox item;
	public ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	public JCheckBox checkbox;
	public JPanel panel_1;
	public JPanel panMenBack;
	public JPanel panCreate;
	
	public JLabel lblMenBack;
	private UseChecklistController clcon;
	//private ChecklistVo clvo;
	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 530, Image.SCALE_SMOOTH);
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);
	

	public UseChecklistView() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 785, 585);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		clcon = new UseChecklistController(this);
		
		JPanel panel = new JPanel();
	
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 220, 546);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblChecklistsOfUser = new JLabel("Checklists");
		lblChecklistsOfUser.setBounds(20, 16, 178, 16);
		lblChecklistsOfUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lblChecklistsOfUser);
		
		comboBoxChecklist = new JComboBox<String>();
		comboBoxChecklist.addActionListener(clcon);
		comboBoxChecklist.setBounds(20, 37, 161, 27);
		clcon.setComboBoxCheck();
		panel.add(comboBoxChecklist);
		
		btnOpen = new JButton("Open");
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOpen.addActionListener(clcon);
		btnOpen.setBounds(20, 106, 161, 29);
		panel.add(btnOpen);
		
		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnModify.addActionListener(clcon);
		panel.add(btnModify);
		btnModify.setBounds(20, 148, 161, 29);
		
		btnCreatePdf = new JButton("Create PDF");
		btnCreatePdf.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCreatePdf.setBounds(20, 190, 161, 29);
		btnCreatePdf.addActionListener(clcon);
		panel.add(btnCreatePdf);
		
		btnExport = new JButton("Export");
		btnExport.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExport.addActionListener(clcon);
		btnExport.setBounds(20, 232, 161, 29);
		panel.add(btnExport);
		
		
		panMenBack = new JPanel();
		panMenBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenBack.setBackground(clBorder);
		panMenBack.setBounds(0, 456, 220, 60);
		panel.add(panMenBack);
		panMenBack.setLayout(null);
		panMenBack.addMouseListener(clcon);

		lblMenBack = new JLabel("back to menu");
		lblMenBack.setBounds(20, 20, 136, 20);
		panMenBack.add(lblMenBack);
		lblMenBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		panCreate = new JPanel();
		panCreate.setBounds(0, 400, 220, 60);
		panel.add(panCreate);
		panCreate.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panCreate.setBackground(clBorder);
		panCreate.setLayout(null);
		panCreate.addMouseListener(clcon);
		
		JLabel lblCreate = new JLabel("create checklist");
		lblCreate.setBounds(20, 20, 136, 20);
		panCreate.add(lblCreate);
		lblCreate.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				
		JLabel lblGoTo = new JLabel("Go To...");
		lblGoTo.setFont(new Font("Goudy Stout", Font.BOLD, 21));
		lblGoTo.setBounds(20, 340, 178, 37);
		panel.add(lblGoTo);
				
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 220, 530);
		lblBack.setIcon(new ImageIcon(img_back));
		panel.add(lblBack);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(551, 546));
		getContentPane().add(panel_1);
		panel_1.setBackground(clBackground);
		panel_1.setLayout(null);
		
		JScrollPane itemScroll = new JScrollPane(panel_1);
		panel_1.setAutoscrolls(true);
		itemScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		itemScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		itemScroll.setBounds(216, 0, 551, 546);
		getContentPane().add(itemScroll);
		
		JLabel lblNewLabel = new JLabel("Checklist");
		lblNewLabel.setBounds(91, 6, 113, 16);
		panel_1.add(lblNewLabel);
	}
	
	public void createrCheckBox(int i, ArrayList<String> list, boolean boo)
    { 		
		checkbox = new JCheckBox();
	    checkbox.setText(list.get(i).toString());
	    checkbox.setSelected(true);

	    // add the checkbox to the ArrayList
	    boxes.add(checkbox);
	    if(i > 19) {
	    	boxes.get(i).setLocation(200,34+(i-20)*25);
	    } else if(i > 39) {
	    	boxes.get(i).setLocation(380,34+(i-40)*25);
	    } else {
	    	boxes.get(i).setLocation(20,34+i*25);
	    } 
	    boxes.get(i).setSize(150,23);
	    boxes.get(i).setText(list.get(i));
	    if(boo) {
	    	boxes.get(i).setSelected(true);
	    } else {
	    	boxes.get(i).setSelected(false);
	    }
	    boxes.get(i).setVisible(true);
	    boxes.get(i).addActionListener(clcon);
	    panel_1.add(boxes.get(i)); 
	    
    } 

}
