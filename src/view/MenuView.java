package view;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import controller.MenuController;

import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MenuView extends JFrame{
	
	private Image img_back;
	private MenuController contro;
	public JPanel panel;
	public JPanel panel_checklist;
	public JPanel panel_newChecklist;
	public JPanel panel_manageChecklists;
	public JPanel panel_importChecklist;
	public JPanel panel_manageTemplates;
	public JPanel panel_createItem;
	public JPanel panel_logOut;
	private Color clBackground = new Color(191, 205, 219);
	private Color clBorder = new Color(244, 247, 252);
	

	/**
	 * Create the application.
	 */
	public MenuView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 750, 578);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Menu");
		contro = new MenuController(this);
		
		img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(734, 539, Image.SCALE_SMOOTH);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 734, 539);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		panel_checklist = new JPanel();
		panel_checklist.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_checklist.setBackground(clBackground);
		panel_checklist.setBounds(229, 39, 287, 50);
		panel.add(panel_checklist);
		panel_checklist.setLayout(null);
		panel_checklist.addMouseListener(contro);
		
		JLabel lblChecklist = new JLabel("Checklist");
		lblChecklist.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblChecklist.setHorizontalAlignment(SwingConstants.CENTER);
		lblChecklist.setBounds(10, 11, 267, 28);
		panel_checklist.add(lblChecklist);
		
		panel_newChecklist = new JPanel();
		panel_newChecklist.setLayout(null);
		panel_newChecklist.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_newChecklist.setBackground(clBackground);
		panel_newChecklist.setBounds(229, 100, 287, 50);
		panel.add(panel_newChecklist);
		panel_newChecklist.addMouseListener(contro);
		
		JLabel lblNewChecklist = new JLabel("New Checklist");
		lblNewChecklist.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewChecklist.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewChecklist.setBounds(10, 11, 267, 28);
		panel_newChecklist.add(lblNewChecklist);
		
		panel_manageChecklists = new JPanel();
		panel_manageChecklists.setLayout(null);
		panel_manageChecklists.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_manageChecklists.setBackground(clBackground);
		panel_manageChecklists.setBounds(229, 161, 287, 50);
		panel.add(panel_manageChecklists);
		panel_manageChecklists.addMouseListener(contro);
		
		JLabel lblManageChecklists = new JLabel("Manage Checklists");
		lblManageChecklists.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageChecklists.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblManageChecklists.setBounds(10, 11, 267, 28);
		panel_manageChecklists.add(lblManageChecklists);
		
		panel_importChecklist = new JPanel();
		panel_importChecklist.setLayout(null);
		panel_importChecklist.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_importChecklist.setBackground(clBackground);
		panel_importChecklist.setBounds(229, 222, 287, 50);
		panel.add(panel_importChecklist);
		panel_importChecklist.addMouseListener(contro);
		
		JLabel lblImportChecklist = new JLabel("Import Checklist");
		lblImportChecklist.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportChecklist.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblImportChecklist.setBounds(10, 11, 267, 28);
		panel_importChecklist.add(lblImportChecklist);
		
		panel_manageTemplates = new JPanel();
		panel_manageTemplates.setLayout(null);
		panel_manageTemplates.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_manageTemplates.setBackground(clBackground);
		panel_manageTemplates.setBounds(229, 283, 287, 50);
		panel.add(panel_manageTemplates);
		panel_manageTemplates.addMouseListener(contro);
		
		JLabel lblManageTemplates = new JLabel("Manage Templates");
		lblManageTemplates.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageTemplates.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblManageTemplates.setBounds(10, 11, 267, 28);
		panel_manageTemplates.add(lblManageTemplates);
		
		panel_createItem = new JPanel();
		panel_createItem.setLayout(null);
		panel_createItem.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_createItem.setBackground(clBackground);
		panel_createItem.setBounds(229, 344, 287, 50);
		panel.add(panel_createItem);
		panel_createItem.addMouseListener(contro);
		
		JLabel lblCreateItem = new JLabel("Create Item");
		lblCreateItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateItem.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblCreateItem.setBounds(10, 11, 267, 28);
		panel_createItem.add(lblCreateItem);
		
		panel_logOut = new JPanel();
		panel_logOut.setLayout(null);
		panel_logOut.setBorder(new BevelBorder(BevelBorder.RAISED, null, clBorder, null, null));
		panel_logOut.setBackground(clBackground);
		panel_logOut.setBounds(229, 462, 287, 50);
		panel.add(panel_logOut);
		panel_logOut.addMouseListener(contro);
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblLogOut.setBounds(10, 11, 267, 28);
		panel_logOut.add(lblLogOut);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 5, 734, 539);
		panel.add(lblBackground);
		lblBackground.setIcon(new ImageIcon(img_back));
		
		
	}
	

}
