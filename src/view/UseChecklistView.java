package view;

import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

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
	public JCheckBox checkBox_check;
	private JCheckBox item = new JCheckBox();
	private ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	private JPanel panel_1;
	public JPanel panel_2;
	public JPanel panel_3;
	private UseChecklistController clcon;
	private ChecklistVo clvo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UseChecklistView window = new UseChecklistView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UseChecklistView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		clcon = new UseChecklistController(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 155, 272);
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
		openButton.addActionListener(clcon);
		openButton.setBounds(80, 92, 69, 29);
		panel.add(openButton);
		
		modifyButton = new JButton("Modify");
		modifyButton.addActionListener(clcon);
		modifyButton.setBounds(6, 92, 68, 29);
		panel.add(modifyButton);
		
		JLabel lblGoTo = new JLabel("Go To...");
		lblGoTo.setFont(new Font("Goudy Stout", Font.BOLD, 21));
		lblGoTo.setBounds(39, 133, 78, 16);
		panel.add(lblGoTo);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(6, 154, 143, 27);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCreateChecklist = new JLabel("create checklist");
		lblCreateChecklist.setBounds(6, 6, 103, 16);
		panel_2.add(lblCreateChecklist);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(6, 180, 143, 27);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblBackToMenu = new JLabel("back to menu");
		lblBackToMenu.setBounds(6, 6, 86, 16);
		panel_3.add(lblBackToMenu);
		
		panel_1 = new JPanel();
		panel_1.setBounds(154, 0, 296, 272);
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
