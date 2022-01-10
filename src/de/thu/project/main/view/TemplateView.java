package de.thu.project.main.view;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import de.thu.project.main.controller.TemplateController;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.util.ArrayList;



public class TemplateView extends JFrame {

	public JPanel contentPane;

	private Image img_back = new ImageIcon(this.getClass().getResource("/blue.jpg")).getImage().getScaledInstance(220, 530, Image.SCALE_SMOOTH);
	private Image img_icon = new ImageIcon(this.getClass().getResource("/new.png")).getImage().getScaledInstance(87, 87, Image.SCALE_SMOOTH);

	public JTextField tfName;
	public JLabel lblMenCheck;
	public JLabel lblMenBack;
	public JPanel panMenCheck;
	public JPanel panMenBack; 
	
	public JTextArea taTempItems;
	public JButton btnSelect;
	public JButton btnReset;
	public JButton btnShowTemp;
	private TemplateController tempcon;

	private ArrayList<JRadioButton> radioButtons = new ArrayList<JRadioButton>();
	private JLabel lblSelectATemplate;
	private JRadioButton rdbtnNewRadioButton;
	public ButtonGroup group;
	

	public TemplateView() {
		initialize();
		tempcon.showTemplates();
	}
	public void initialize() {
	
		tempcon = new TemplateController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 585);

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(100, 100, 785, 585);	
		contentPane.setLayout(null);
		
		JScrollPane scrollFrame = new JScrollPane(contentPane);
		contentPane.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(785, 585));
		getContentPane().add(scrollFrame);
        
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 220, 530);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panMenCheck = new JPanel();
		panMenCheck.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(153, 180, 209), null, null));
		panMenCheck.setBackground(SystemColor.inactiveCaptionBorder);
		panMenCheck.setBounds(0, 188, 219, 60);
		panel.add(panMenCheck);
		panMenCheck.setLayout(null);
		panMenCheck.addMouseListener(tempcon);
		
		lblMenCheck = new JLabel("your checklists");
		lblMenCheck.setBounds(15, 21, 134, 20);
		panMenCheck.add(lblMenCheck);
		lblMenCheck.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		panMenBack = new JPanel();
		panMenBack.setBorder(new BevelBorder(BevelBorder.LOWERED, null, SystemColor.activeCaption, null, null));
		panMenBack.setBackground(SystemColor.inactiveCaptionBorder);
		panMenBack.setBounds(0, 249, 219, 60);
		panel.add(panMenBack);
		panMenBack.setLayout(null);
		panMenBack.addMouseListener(tempcon);
		
		lblMenBack = new JLabel("back to menu");
		lblMenBack.setBounds(15, 21, 136, 20);
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
		
		taTempItems = new JTextArea();
		taTempItems.setEditable(false);
		contentPane.add(taTempItems);
		
		JScrollPane spTempItems = new JScrollPane(taTempItems);
		spTempItems.setBounds(470, 105, 259, 401);
		contentPane.add(spTempItems);
		
		btnShowTemp = new JButton("Show template");
		btnShowTemp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnShowTemp.addActionListener(tempcon);
		btnShowTemp.setBounds(470, 60, 149, 29);
		contentPane.add(btnShowTemp);
		
		btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSelect.addActionListener(tempcon);
		btnSelect.setBounds(629, 63, 100, 29);
		contentPane.add(btnSelect);
		
		lblSelectATemplate = new JLabel("Select a template");
		lblSelectATemplate.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblSelectATemplate.setBounds(229, 25, 220, 20);
		contentPane.add(lblSelectATemplate);
		
		group = new ButtonGroup();

	}
	
	public void createRadioButton(int i, ArrayList<String> list) { 		
		
		JRadioButton radioBtn = new JRadioButton();
	    radioBtn.setText(list.get(i).toString());
	    radioBtn.setActionCommand(radioBtn.getText());
	    
	    // add the radioButton to the ArrayList
	    radioButtons.add(radioBtn);
	    radioButtons.get(i).setLocation(235,129+i*30); 
	    radioButtons.get(i).setSize(128,23);
	    radioButtons.get(i).setText(list.get(i));
	    radioButtons.get(i).setSelected(false);
	    radioButtons.get(i).setVisible(true);
	    group.add(radioBtn);
	    contentPane.add(radioButtons.get(i)); 
	    
    } 
}