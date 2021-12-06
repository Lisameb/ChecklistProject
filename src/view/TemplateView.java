package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;


public class TemplateView extends JFrame {

	private JPanel contentPane;	
	
	private Image img_white = new ImageIcon(this.getClass().getResource("/weisss.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_dest = new ImageIcon(this.getClass().getResource("/destinations.jpeg")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_groc = new ImageIcon(this.getClass().getResource("/shopping.jpg")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_vaca = new ImageIcon(this.getClass().getResource("/vacation.jpeg")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_cake = new ImageIcon(this.getClass().getResource("/cake.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TemplateView frame = new TemplateView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TemplateView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 578);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 78, 381, 450);
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panEmpty = new JPanel();
		panEmpty.setBackground(SystemColor.activeCaption);
		panEmpty.setBounds(37, 57, 90, 90);
		panel.add(panEmpty);
		panEmpty.setLayout(null);
		
		JLabel lblEmpty = new JLabel("");
		lblEmpty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblEmpty.setBounds(0, 0, 87, 87);
		lblEmpty.setIcon(new ImageIcon(img_white));
		panEmpty.add(lblEmpty);
		
		JPanel panDest = new JPanel();
		panDest.setBackground(SystemColor.activeCaption);
		panDest.setBounds(228, 57, 90, 90);
		panel.add(panDest);
		panDest.setLayout(null);
		
		JLabel lblDest = new JLabel("");
		lblDest.setBackground(SystemColor.activeCaption);
		lblDest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		lblDest.setBounds(0, 0, 87, 87);
		lblDest.setIcon(new ImageIcon(img_dest));
		panDest.add(lblDest);
		
		JPanel panGroc = new JPanel();
		panGroc.setBackground(SystemColor.activeCaption);
		panGroc.setBounds(37, 204, 90, 90);
		panel.add(panGroc);
		panGroc.setLayout(null);
		
		JLabel lblGroc = new JLabel("");
		lblGroc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblGroc.setBounds(0, 0, 87, 87);
		lblGroc.setIcon(new ImageIcon(img_groc));
		panGroc.add(lblGroc);
		
		JPanel panParty = new JPanel();
		panParty.setLayout(null);
		panParty.setBackground(SystemColor.activeCaption);
		panParty.setBounds(37, 344, 90, 90);
		panel.add(panParty);
		
		JLabel lblParty = new JLabel("");
		lblParty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		lblParty.setBounds(0, 0, 87, 87);
		lblParty.setIcon(new ImageIcon(img_cake));
		panParty.add(lblParty);
		
		JPanel panVaca = new JPanel();
		panVaca.setBackground(SystemColor.activeCaption);
		panVaca.setBounds(228, 204, 90, 90);
		panel.add(panVaca);
		panVaca.setLayout(null);
		
		JLabel lblVaca = new JLabel("");
		lblVaca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblVaca.setBounds(0, 0, 87, 87);
		lblVaca.setIcon(new ImageIcon(img_vaca));
		panVaca.add(lblVaca);
		ImageIcon blue = new ImageIcon(this.getClass().getResource("/blue.jpg"));
		
		JLabel lblEmpty_1 = new JLabel("Empty");
		lblEmpty_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpty_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmpty_1.setBounds(37, 33, 90, 20);
		panel.add(lblEmpty_1);
		
		JLabel lblDest2 = new JLabel("Destinations");
		lblDest2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDest2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDest2.setBounds(228, 33, 90, 20);
		panel.add(lblDest2);
		
		JLabel lblVaca2 = new JLabel("Vacation");
		lblVaca2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVaca2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVaca2.setBounds(228, 182, 90, 20);
		panel.add(lblVaca2);
		
		JLabel lblGroc2 = new JLabel("Groceries");
		lblGroc2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroc2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGroc2.setBounds(37, 182, 90, 20);
		panel.add(lblGroc2);
		
		JLabel lblParty2 = new JLabel("B-day Party");
		lblParty2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblParty2.setHorizontalAlignment(SwingConstants.CENTER);
		lblParty2.setBounds(37, 321, 90, 20);
		panel.add(lblParty2);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(355, 0, 26, 450);
		panel.add(scrollBar);
		
		JLabel lblFU = new JLabel("");
		lblFU.setBackground(Color.BLACK);
		lblFU.setBounds(0, 0, 381, 450);
		lblFU.setIcon(blue);
		panel.add(lblFU);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(587, 471, 126, 41);
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelect.setBackground(SystemColor.text);
		btnSelect.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 16));
		contentPane.add(btnSelect);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(0, 0, 381, 80);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblTempl = new JLabel("Choose a template");
		lblTempl.setHorizontalAlignment(SwingConstants.CENTER);
		lblTempl.setBounds(0, 0, 381, 80);
		panel_2.add(lblTempl);
		lblTempl.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		
		JLabel lblSmallBack = new JLabel("");
		lblSmallBack.setBounds(0, 0, 381, 80);
		lblSmallBack.setIcon(blue);
		panel_2.add(lblSmallBack);
		
	}
}