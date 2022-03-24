package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SaleOrderController;

public class Payment extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private SaleOrderController orderctrl;
	private boolean paid;
	

	/**
	 * Launch the application.
	 */
	public Payment(SaleOrderController ctrl) {
		paid = false;
		this.orderctrl = ctrl;
		initGui();
	}

	/**
	 * Create the dialog.
	 */
	private void initGui() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Was payment of " + orderctrl.getCurrentOrder().getAmount() + " successful?");
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Yes");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						yes();
					}
				});
				okButton.setActionCommand("Yes");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("No");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						no();
					}
				});
				cancelButton.setActionCommand("No");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * sets boolean of paid to true if the payment was successful
	 */
	private void yes() {
		paid = true;
		dispose();
	}
	
	/**
	 * Disposes the window if the payment was unsuccessful
	 */
	private void no() {
		dispose();
	}
	
	/**
	 * @return boolean of paid
	 */
	public boolean isPaid() {
		return paid;
	}

}
