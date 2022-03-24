package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					System.setProperty("sun.java2d.uiScale", "1.0");
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
		
		JLabel lblOrders = new JLabel("Orders");
		lblOrders.setFont(ColorScheme.FONT);
		lblOrders.setAlignmentX(0.1f);
		verticalBox_1.add(lblOrders);
		
		JButton btnNewButton_2 = new JButton("Create order");
		
		btnNewButton_2.setIcon(Images.getButtonIcon(btnNewButton_2, ColorScheme.BACKGROUND));
		btnNewButton_2.setFont(ColorScheme.FONT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createOrder();
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		verticalBox_1.add(verticalStrut_1);
		verticalBox_1.add(btnNewButton_2);
		

		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		splitPane.setDividerSize(2);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(ColorScheme.TAB);
		splitPane.setLeftComponent(menuPanel);
		
		splitPane.setRightComponent(interactivePanel);
		
		
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
		btnOrders.setIcon(Images.getButtonIcon(btnOrders, ColorScheme.TAB));
		btnOrders.setFont(ColorScheme.FONT);
		btnOrders.setFocusPainted(false);
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
	}
	
	/**
	 * Handle order creation
	 */
	private void createOrder() {
		try {
			CreateOrder login = new CreateOrder();
			login.setVisible(true);
		} catch (DatabaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
