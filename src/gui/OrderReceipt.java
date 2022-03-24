package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.SaleOrderController;
import model.SaleOrderLine;

public class OrderReceipt extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4900436836168536626L;

	private final JPanel contentPanel = new JPanel();
	
	private SaleOrderController orderCtrl;
	private JLabel lblNewLabel_3;
	private Box verticalBox;

	private boolean finished;
	

	/**
	 * Create the dialog.
	 */
	public OrderReceipt(SaleOrderController orderCtrl) {
		this.orderCtrl = orderCtrl;
		finished = false;
		
		initGui();
	}
	
	/**
	 * Initializes the gui
	 */
	private void initGui() {
		setModal(true);
		setTitle("Order for " + orderCtrl.getCurrentOrder().getCustomer().getName());
		setBounds(100, 100, 550, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				JLabel lblNewLabel = new JLabel("Receipt");
				lblNewLabel.setFont(ColorScheme.FONT);
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				lblNewLabel_3 = new JLabel("Sending the invoice...");
				lblNewLabel_3.setFont(ColorScheme.FONT);
				lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
				panel.add(lblNewLabel_3);
				{
					JScrollPane scrollPane = new JScrollPane();
					contentPanel.add(scrollPane, BorderLayout.CENTER);
					{
						verticalBox = Box.createVerticalBox();
						scrollPane.setViewportView(verticalBox);
						{
							getItems();
						}
						{
							JLabel lblNewLabel_2 = new JLabel("Total: " + CurrencyHandler.convertToString(orderCtrl.getTotal()));
							lblNewLabel_2.setFont(ColorScheme.FONT);
							verticalBox.add(lblNewLabel_2);
						}
					}
				}
				lblNewLabel_3.setVisible(false);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton finishOrderButton = new JButton("Send invoice");
				finishOrderButton.setIcon(Images.getButtonIcon(finishOrderButton, ColorScheme.BACKGROUND));
				finishOrderButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						finishInvoice();
					}
				});
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JButton btnNewButton = new JButton("Pay here");
					btnNewButton.setIcon(Images.getButtonIcon(btnNewButton, ColorScheme.BACKGROUND));
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							finishHere();
							//waiting for the answer from terminal
							
						}
					});
					buttonPane.add(btnNewButton);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(30);
					buttonPane.add(horizontalStrut);
				}
				buttonPane.add(finishOrderButton);
				getRootPane().setDefaultButton(finishOrderButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setIcon(Images.getButtonIcon(cancelButton, ColorScheme.BACKGROUND));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});
				{
					Component horizontalStrut = Box.createHorizontalStrut(30);
					buttonPane.add(horizontalStrut);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * Handles the list of products in the order
	 */
	private void getItems() {
		for(int i  = 0; i < orderCtrl.getCurrentOrder().getOrderLines().size(); i++) {
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(ColorScheme.FONT);
			verticalBox.add(lblNewLabel);
			
			StringBuilder returnString = new StringBuilder();
			SaleOrderLine anOrderLine = (SaleOrderLine) orderCtrl.getCurrentOrder().getOrderLines().get(i);
			
			returnString.append("\n");
			returnString.append(anOrderLine.getProduct().getName());
			returnString.append("    " + anOrderLine.getProduct().getSalesPrice());
			returnString.append(" x" + anOrderLine.getQuantity());
			if (anOrderLine.getQuantity() < 10) {
				returnString.append(" " + anOrderLine.getAmount());
			}
			else {
				returnString.append(" " + (anOrderLine.getAmount()));
			}
			
			
			lblNewLabel.setText(returnString.toString());
		}
		if(orderCtrl.getCurrentOrder().getPrice().compareTo(new BigDecimal(1500)) == 1 && orderCtrl.getCurrentOrder().getPrice().compareTo(new BigDecimal(2500)) == -1) {
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(ColorScheme.FONT);
			verticalBox.add(lblNewLabel);
			
			StringBuilder returnString = new StringBuilder();
			returnString.append("Delivery price: 45");
			
			JLabel lblNewLabel1 = new JLabel("");
			lblNewLabel.setFont(ColorScheme.FONT);
			verticalBox.add(lblNewLabel);
			
			StringBuilder returnString1 = new StringBuilder();
			returnString1.append("Discount: -100");
			
			lblNewLabel.setText(returnString.toString());
		}
		else if(orderCtrl.getCurrentOrder().getPrice().compareTo(new BigDecimal(2500)) == 1) {
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(ColorScheme.FONT);
			verticalBox.add(lblNewLabel);
			
			StringBuilder returnString = new StringBuilder();
			returnString.append("Discount: -100");
			
			lblNewLabel.setText(returnString.toString());
		}
		else {
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setFont(ColorScheme.FONT);
			verticalBox.add(lblNewLabel);
			
			StringBuilder returnString = new StringBuilder();
			returnString.append("Delivery price: 45");
			
			lblNewLabel.setText(returnString.toString());
		}
		
		
	}
	
	/**
	 * Finishes the order
	 * @throws EmptyOrderException
	 */
	private void finishOrder(){
			orderCtrl.finishOrder();
			finished = true;
		
		dispose();
	}
	
	/**
	 * Asks for payment
	 */
	private void finishHere() {
		try {
			Payment dialog = new Payment(orderCtrl);
			dialog.setVisible(true);
			if (dialog.isPaid()) {
				finishOrder();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handles sending the invoice to registered customer
	 */
	private void finishInvoice() {
		//TODO where the customer is registered, send invoice 
		finishOrder();
	}
	
	/**
	 * @return boolean of finished
	 */
	public boolean isFinished() {
		return finished;
	}
	
	/**
	 * Disposes the window
	 */
	private void cancel() {
		dispose();
	}
}
