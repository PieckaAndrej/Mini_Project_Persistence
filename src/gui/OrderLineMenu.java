package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SaleOrderController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JTable;

public class OrderLineMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SaleOrderController orderController;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the dialog.
	 */
	public OrderLineMenu(SaleOrderController orderController) {
		setModal(true);
		
		this.orderController = orderController;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				Box verticalBox = Box.createVerticalBox();
				panel.add(verticalBox);
				{
					JPanel panel_1 = new JPanel();
					verticalBox.add(panel_1);
					{
						JLabel lblNewLabel = new JLabel("Input id:");
						panel_1.add(lblNewLabel);
					}
					{
						textField = new JTextField();
						textField.setColumns(10);
						panel_1.add(textField);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					verticalBox.add(panel_1);
					{
						JLabel lblQuantity = new JLabel("Quantity:");
						panel_1.add(lblQuantity);
					}
					{
						textField_1 = new JTextField();
						textField_1.setColumns(10);
						panel_1.add(textField_1);
					}
				}
			}
			{
				table = new JTable();
				panel.add(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addOrderLine();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void addOrderLine() {
		orderController.addProduct(Integer.parseInt(textField.getText()), Integer.parseInt(textField_1.getText()));
	}

}
