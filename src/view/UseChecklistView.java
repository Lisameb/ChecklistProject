package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import controller.UseChecklistController;
import model.checklist.ChecklistVo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;

/**********************************************
 * UseChecklistView-Class
 * 
 * class creates the GUI for checklist uses;
 * options for displaying or modifying checklists are available;
 * on the bottom left, you can find a small menu
 * 
 **********************************************/

public class UseChecklistView {

	private JFrame frame;

	public JComboBox<String> comboBox_check;
	public JButton modifyButton;
	public JButton openButton;
	public JButton exportButton;
	public JButton btnCreatePdf;
	public JCheckBox checkBox_check;
	private JCheckBox item = new JCheckBox();
	private ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	public JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	private UseChecklistController clcon;
	private ChecklistVo clvo;
	private Image img_sidebar;
	

	public UseChecklistView() {
		initialize();
	}

	private void initialize() {
		img_sidebar = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 546, Image.SCALE_SMOOTH);
		frame = new JFrame();
		frame.setBounds(100, 100, 785, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		clcon = new UseChecklistController(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 220, 546);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblChecklistsOfUser = new JLabel("Checklists of User");
		lblChecklistsOfUser.setBounds(20, 5, 115, 16);
		panel.add(lblChecklistsOfUser);
		
		comboBox_check = new JComboBox<String>();
		comboBox_check.addActionListener(clcon);
		comboBox_check.setBounds(20, 26, 115, 27);
		clcon.setComboBoxCheck();
		panel.add(comboBox_check);
		
		openButton = new JButton("Open");
		openButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		openButton.addActionListener(clcon);
		openButton.setBounds(20, 95, 96, 29);
		panel.add(openButton);
		
		modifyButton = new JButton("Modify");
		modifyButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modifyButton.addActionListener(clcon);
		panel.add(modifyButton);
		modifyButton.setBounds(20, 137, 96, 29);
		
		btnCreatePdf = new JButton("Create PDF");
		btnCreatePdf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCreatePdf.setBounds(20, 179, 96, 29);
		btnCreatePdf.addActionListener(clcon);
		panel.add(btnCreatePdf);
		
		exportButton = new JButton("Export");
		exportButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		exportButton.addActionListener(clcon);
		exportButton.setBounds(20, 221, 96, 29);
		panel.add(exportButton);
		
		
		JLabel lblGoTo = new JLabel("Go To...");
		lblGoTo.setFont(new Font("Goudy Stout", Font.BOLD, 21));
		lblGoTo.setBounds(20, 347, 202, 16);
		panel.add(lblGoTo);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(20, 430, 143, 27);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.addMouseListener(clcon);
		
		JLabel lblCreateChecklist = new JLabel("create checklist");
		lblCreateChecklist.setBounds(6, 6, 103, 16);
		panel_2.add(lblCreateChecklist);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(20, 486, 143, 27);
		panel.add(panel_3);
		panel_3.setLayout(null);
		panel_3.addMouseListener(clcon);
		
		JLabel lblBackToMenu = new JLabel("back to menu");
		lblBackToMenu.setBounds(6, 6, 86, 16);
		panel_3.add(lblBackToMenu);
		
		JLabel lblBack = new JLabel("");
		lblBack.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		lblBack.setBounds(0, 0, 220, 546);
		lblBack.setIcon(new ImageIcon(img_sidebar));
		panel.add(lblBack);
		
		panel_1 = new JPanel();
		panel_1.setBounds(216, 0, 551, 546);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Checklist");
		lblNewLabel.setBounds(91, 6, 113, 16);
		panel_1.add(lblNewLabel);
	}
	
	public void createrCheckBox(int i, ArrayList<String> list)
    { 		
		JCheckBox checkbox = new JCheckBox();
	    checkbox.setText(list.get(i).toString());
	    checkbox.setSelected(true);

	    // add the checkbox to the ArrayList
	    boxes.add(checkbox);
	    boxes.get(i).setLocation(20,34+i*25); 
	    boxes.get(i).setSize(128,23);
	    boxes.get(i).setText(list.get(i));
	    boxes.get(i).setSelected(false);
	    boxes.get(i).setVisible(true);
	    panel_1.add(boxes.get(i)); 
	    
    } 
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
