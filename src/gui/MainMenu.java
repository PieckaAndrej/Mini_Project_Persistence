package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class MainMenu extends JFrame {

	private JPanel contentPane;

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
		setTitle("Main menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		Box verticalBox = Box.createVerticalBox();
		splitPane.setLeftComponent(verticalBox);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setAlignmentX(0.5f);
		verticalBox.add(btnNewButton);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setAlignmentX(0.5f);
		verticalBox.add(btnNewButton_1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setAlignmentX(0.5f);
		verticalBox.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Orders");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		Box verticalBox_1 = Box.createVerticalBox();
		panel_2.add(verticalBox_1);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut_3);
		
		JButton btnNewButton_3 = new JButton("Process Order");
		verticalBox_1.add(btnNewButton_3);
	}

}
