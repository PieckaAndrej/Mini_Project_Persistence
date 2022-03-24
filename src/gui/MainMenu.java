package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import exceptions.DatabaseAccessException;



public class MainMenu extends JFrame {
	/**
	 * Serial version 
	 */
	private static final long serialVersionUID = 7694456555976937236L;
	
	private JPanel contentPane;
	private JPanel ordersPanel;
	private JPanel leasesPanel;
	private JPanel statisticsPanel;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {
		initGui();
		showOrders();
	}
	/**
	 * Initialize the MainMenu gui
	 */
	private void initGui() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(ColorScheme.BACKGROUND);
		
		JPanel interactivePanel = new JPanel();
		interactivePanel.setBackground(ColorScheme.BACKGROUND);
		
		ordersPanel = new JPanel();
		ordersPanel.setBackground(ColorScheme.BACKGROUND);
		FlowLayout flowLayout = (FlowLayout) ordersPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		ordersPanel.setVisible(false);
		interactivePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		interactivePanel.add(ordersPanel);
		
		Box verticalBox_1 = Box.createVerticalBox();
		ordersPanel.add(verticalBox_1);
		
		JLabel lblOrders = new JLabel("Orders     ");
		lblOrders.setAlignmentX(0.1f);
		verticalBox_1.add(lblOrders);
		
		JButton btnNewButton_2 = new JButton("Create order");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOrder();
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		verticalBox_1.add(verticalStrut_1);
		verticalBox_1.add(btnNewButton_2);
		
		
		Box verticalBox_2 = Box.createVerticalBox();
		leasesPanel.add(verticalBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Create");
		lblNewLabel_2.setAlignmentX(0.1f);
		verticalBox_2.add(lblNewLabel_2);
		
		JButton btnNewButton_3 = new JButton("Create Lease");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		verticalBox_2.add(verticalStrut_2);
		verticalBox_2.add(btnNewButton_3);
		
		Box verticalBox_2_1 = Box.createVerticalBox();
		leasesPanel.add(verticalBox_2_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Return");
		lblNewLabel_2_1.setAlignmentX(0.1f);
		verticalBox_2_1.add(lblNewLabel_2_1);
		
		Component verticalStrut_2_1 = Box.createVerticalStrut(5);
		verticalBox_2_1.add(verticalStrut_2_1);
		
		JButton btnNewButton_3_1 = new JButton("Return Lease");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		verticalBox_2_1.add(btnNewButton_3_1);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setDividerSize(2);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(ColorScheme.TAB);
		splitPane.setLeftComponent(menuPanel);
		
		splitPane.setRightComponent(interactivePanel);
		
		
		Box verticalBox_1_1 = Box.createVerticalBox();
		statisticsPanel.add(verticalBox_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Statistics");
		lblNewLabel_1_1.setAlignmentX(0.1f);
		verticalBox_1_1.add(lblNewLabel_1_1);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(5);
		verticalBox_1_1.add(verticalStrut_1_1);
		
		JButton btnNewButton_2_1 = new JButton("Generate statistics");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		verticalBox_1_1.add(btnNewButton_2_1);
		
		Box verticalBox_3 = Box.createVerticalBox();
		interactivePanel.add(verticalBox_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(ColorScheme.BACKGROUND);
		verticalBox_3.add(panel);
		
		lblNewLabel = new JLabel("");
		verticalBox_3.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		splitPane.setResizeWeight(0.2);
		
		Box verticalBox = Box.createVerticalBox();
		menuPanel.add(verticalBox);
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.setAlignmentX(0.5f);
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showOrders();
			}
		});
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_3);
		verticalBox.add(btnOrders);
	}
	
	/**
	 * Show orders menu
	 */
	private void showOrders() {
		hidePanels();
		ordersPanel.setVisible(true);
	}
	
	/**
	 * Hide all menu panels
	 */
	private void hidePanels() {
		lblNewLabel.setVisible(false);
		
		ordersPanel.setVisible(false);
		leasesPanel.setVisible(false);
		statisticsPanel.setVisible(false);
	}
	
	/**
	 * Handle order creation
	 */
	private void createOrder() {
		CreateOrder login;
		try {
			login = new CreateOrder();
		} catch (DatabaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
