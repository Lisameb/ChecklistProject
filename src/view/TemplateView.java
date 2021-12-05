package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;


public class TemplateView extends JFrame {

	private JPanel contentPane;	
	
	private Image img_white = new ImageIcon(this.getClass().getResource("weisss.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_dest = new ImageIcon(this.getClass().getResource("destinations.jpeg")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_groc = new ImageIcon(this.getClass().getResource("grocery.jpeg")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_vaca = new ImageIcon(this.getClass().getResource("vacation.jpeg")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

	
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
		setBounds(100, 100, 750, 584);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 381, 528);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTempl = new JLabel("Choose a template");
		lblTempl.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 16));
		lblTempl.setBounds(103, 16, 207, 67);
		panel.add(lblTempl);
		
		JPanel panEmpty = new JPanel();
		panEmpty.setBounds(37, 117, 90, 90);
		panel.add(panEmpty);
		panEmpty.setLayout(null);
		
		JLabel lblEmpty = new JLabel("");
		lblEmpty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblEmpty.setBounds(0, 0, 90, 90);
		lblEmpty.setIcon(new ImageIcon(img_white));
		panEmpty.add(lblEmpty);
		
		JPanel panDest = new JPanel();
		panDest.setBounds(228, 117, 90, 90);
		panel.add(panDest);
		panDest.setLayout(null);
		
		JLabel lblDest = new JLabel("");
		lblDest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		lblDest.setBounds(0, 0, 90, 90);
		lblDest.setIcon(new ImageIcon(img_dest));
		panDest.add(lblDest);
		
		JPanel panGroc = new JPanel();
		panGroc.setBounds(37, 258, 90, 90);
		panel.add(panGroc);
		panGroc.setLayout(null);
		
		JLabel lblGroc = new JLabel("");
		lblGroc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblGroc.setBounds(0, 0, 90, 90);
		lblGroc.setIcon(new ImageIcon(img_groc));
		panGroc.add(lblGroc);
		
		JPanel panVaca = new JPanel();
		panVaca.setBounds(228, 258, 90, 90);
		panel.add(panVaca);
		panVaca.setLayout(null);
		
		JLabel lblVaca = new JLabel("");
		lblVaca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblVaca.setBounds(0, 0, 90, 90);
		lblVaca.setIcon(new ImageIcon(img_vaca));
		panVaca.add(lblVaca);
		ImageIcon blue = new ImageIcon(this.getClass().getResource("/blue.jpg"));
		
		JLabel lblEmpty_1 = new JLabel("Empty");
		lblEmpty_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmpty_1.setBounds(37, 99, 90, 20);
		panel.add(lblEmpty_1);
		
		JLabel lblNewLabel = new JLabel("Destinations");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(228, 99, 90, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Groceries");
		lblNewLabel_1.setBounds(37, 236, 90, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vacation");
		lblNewLabel_2.setBounds(228, 236, 90, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblFU = new JLabel("");
		lblFU.setBounds(0, 0, 381, 528);
		lblFU.setIcon(blue);
		panel.add(lblFU);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSelect.setBackground(SystemColor.text);
		btnSelect.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 16));
		btnSelect.setBounds(587, 471, 126, 41);
		contentPane.add(btnSelect);
		
	}
}
