package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.SaleOrderController;
import exceptions.DatabaseAccessException;
import exceptions.NotEnoughInStockException;
import exceptions.ProductNotFoundException;
import model.OrderLine;
import model.Person;
import model.SaleOrderLine;

public class CreateOrder extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320032285602649611L;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel selectCustomerMethodPanel;
	private JPanel phoneNumberPanel;
	private JPanel selectProductsPanel;

	private SaleOrderController orderCtrl;
	private boolean created;
	private ArrayList<JPanel> backPath;
	private JPanel currentPanel;
	
	private JTextField phoneField;
	private JButton btnConfirm;
	private JTextField textBarcode;
	private JButton btnFinishOrder;
	private JSpinner spinnerQuantity;
	private JTable table;
	private JLabel lblTotalPrice;
	private JLabel lblErrorMessage;
	private JButton btnBack;
	private JButton btnCreate;
	private JComboBox<Person> comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateOrder dialog = new CreateOrder();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 * @throws DatabaseAccessException 
	 */
	public CreateOrder() {
		setTitle("Create Order");
		
		backPath = new ArrayList<>();

		//showPanel(selectCustomerMethodPanel);
		
		
	}
	
	public void initOrderCtrl() {
		try {
			this.orderCtrl = new SaleOrderController();

			fillGroupList();
			
		} catch (DatabaseAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize gui
	 */
	public void initGui() {
		setModal(true);
		setBounds(100, 100, 800, 500);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(ColorScheme.BACKGROUND);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			phoneNumberPanel = new JPanel();
			phoneNumberPanel.setBackground(ColorScheme.BACKGROUND);
			phoneNumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				Box verticalBox = Box.createVerticalBox();
				phoneNumberPanel.add(verticalBox);
				{
					JLabel lblEnterPhone = new JLabel("Enter phone number");
					lblEnterPhone.setFont(ColorScheme.FONT);
					lblEnterPhone.setAlignmentX(0.1f);
					verticalBox.add(lblEnterPhone);
				}
				{
					Component verticalStrut = Box.createVerticalStrut(20);
					verticalBox.add(verticalStrut);
				}
				{
					phoneField = new JTextField();
					phoneField.setFont(ColorScheme.FONT);
					phoneField.setBorder(new LineBorder(ColorScheme.TEXT_FIELD, 10));
					phoneField.setBackground(ColorScheme.TEXT_FIELD);
					phoneField.setForeground(ColorScheme.TEXT_FIELD_FOREGROUND);
					verticalBox.add(phoneField);
					phoneField.setColumns(10);
					phoneField.setAlignmentX(0.0f);
				}
				{
					Component verticalStrut = Box.createVerticalStrut(20);
					verticalBox.add(verticalStrut);
				}
				{
					comboBox = new JComboBox<Person>();
					comboBox.setEditable(true);
					verticalBox.add(comboBox);
				}
			}
		}
		{
			selectCustomerMethodPanel = new JPanel();
			selectCustomerMethodPanel.setBackground(ColorScheme.BACKGROUND);
			FlowLayout flowLayout = (FlowLayout) selectCustomerMethodPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			{
				Box verticalBox = Box.createVerticalBox();
				selectCustomerMethodPanel.add(verticalBox);
				{
					JButton btnExistingCustomer = new JButton("Existing customer");
					btnExistingCustomer.setIcon(Images.getButtonIcon(btnExistingCustomer, ColorScheme.BACKGROUND));
					btnExistingCustomer.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							selectExistingCustomer();
						}
					});
					{
						JLabel lblFindBy = new JLabel("Find customer");
						lblFindBy.setFont(ColorScheme.FONT);
						lblFindBy.setAlignmentX(0.1f);
						verticalBox.add(lblFindBy);
					}
					{
						Component verticalStrut = Box.createVerticalStrut(20);
						verticalBox.add(verticalStrut);
					}
					verticalBox.add(btnExistingCustomer);
				}
			}
		}
		{
			selectProductsPanel = new JPanel();
			selectProductsPanel.setBackground(ColorScheme.BACKGROUND);
			selectProductsPanel.setLayout(new BorderLayout(0, 0));
			{
				JSplitPane splitPane = new JSplitPane();
				splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setContinuousLayout(true);
				splitPane.setDividerSize(2);
				selectProductsPanel.add(splitPane);
				{
					JPanel addProductPanel = new JPanel();
					addProductPanel.setBackground(ColorScheme.BACKGROUND);
					splitPane.setLeftComponent(addProductPanel);
					FlowLayout fl_addProductPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
					fl_addProductPanel.setAlignOnBaseline(true);
					addProductPanel.setLayout(fl_addProductPanel);
					{
						JLabel lblEnterBarcode = new JLabel("Enter product barcode");
						lblEnterBarcode.setFont(ColorScheme.FONT);
						addProductPanel.add(lblEnterBarcode);
					}
					{
						textBarcode = new JTextField();
						textBarcode.setFont(ColorScheme.FONT);
						textBarcode.setBorder(new LineBorder(ColorScheme.TEXT_FIELD, 10));
						textBarcode.setBackground(ColorScheme.TEXT_FIELD);
						textBarcode.setForeground(ColorScheme.TEXT_FIELD_FOREGROUND);
						textBarcode.setColumns(10);
						addProductPanel.add(textBarcode);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						addProductPanel.add(horizontalStrut);
					}
					{
						JLabel lblQuantity = new JLabel("Quantity");
						lblQuantity.setFont(ColorScheme.FONT);
						addProductPanel.add(lblQuantity);
					}
					{
						SpinnerModel sm = new SpinnerNumberModel(1, 1, null, 1);
						spinnerQuantity = new JSpinner(sm);
						spinnerQuantity.setValue(Integer.valueOf(1));
						addProductPanel.add(spinnerQuantity);
					}
					{
						Component horizontalStrut = Box.createHorizontalStrut(20);
						addProductPanel.add(horizontalStrut);
					}
					{
						JButton btnAddProduct = new JButton("Add Product");
						btnAddProduct.setIcon(Images.getButtonIcon(btnAddProduct, ColorScheme.BACKGROUND));
						btnAddProduct.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addProduct();
							}
						});
						addProductPanel.add(btnAddProduct);
					}
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBackground(ColorScheme.BACKGROUND);
					splitPane.setRightComponent(scrollPane);
					{
						table = new JTable();
						table.setBackground(ColorScheme.BACKGROUND);
						table.setDefaultEditor(Object.class, null);
						scrollPane.setViewportView(table);
					}
				}
			}
		}
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(ColorScheme.TAB);
			getContentPane().add(buttonPanel, BorderLayout.SOUTH);
			buttonPanel.setLayout(new BorderLayout(0, 0));
			{
				{
				}
			}
			{
				JPanel leftButtonPanel = new JPanel();
				leftButtonPanel.setBackground(ColorScheme.TAB);
				buttonPanel.add(leftButtonPanel, BorderLayout.WEST);
				{
					btnBack = new JButton("Back");
					btnBack.setIcon(Images.getButtonIcon(btnBack, ColorScheme.TAB));
					btnBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							back();
						}
					});
					leftButtonPanel.add(btnBack);
					btnBack.setHorizontalAlignment(SwingConstants.LEFT);
				}
			}
			{
				JPanel rightButtonPanel = new JPanel();
				rightButtonPanel.setBackground(ColorScheme.TAB);
				FlowLayout fl_rightButtonPanel = (FlowLayout) rightButtonPanel.getLayout();
				fl_rightButtonPanel.setAlignment(FlowLayout.LEFT);
				buttonPanel.add(rightButtonPanel, BorderLayout.EAST);
				{
					lblErrorMessage = new JLabel("");
					lblErrorMessage.setFont(ColorScheme.FONT);
					rightButtonPanel.add(lblErrorMessage);
					lblErrorMessage.setHorizontalAlignment(SwingConstants.RIGHT);
					lblErrorMessage.setForeground(ColorScheme.BACKGROUND);
				}
				{
					btnCreate = new JButton("Create");
					btnCreate.setIcon(Images.getButtonIcon(btnBack, ColorScheme.TAB));
					btnCreate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

						}
					});
					rightButtonPanel.add(btnCreate);
					btnCreate.setVisible(false);
				}
				btnFinishOrder = new JButton("Finish Order");
				btnFinishOrder.setIcon(Images.getButtonIcon(btnFinishOrder, ColorScheme.TAB));
				rightButtonPanel.add(btnFinishOrder);
				btnFinishOrder.setHorizontalAlignment(SwingConstants.RIGHT);
				btnConfirm = new JButton("Confirm");
				btnConfirm.setIcon(Images.getButtonIcon(btnConfirm, ColorScheme.TAB));
				rightButtonPanel.add(btnConfirm);
				btnConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
				{
					JButton btnCancel = new JButton("Cancel");
					btnCancel.setIcon(Images.getButtonIcon(btnCancel, ColorScheme.TAB));
					rightButtonPanel.add(btnCancel);
					btnCancel.setHorizontalAlignment(SwingConstants.RIGHT);
					btnCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							cancel();
						}
					});
					btnCancel.setActionCommand("Cancel");
				}
				btnConfirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkPhoneNumber();
					}
				});
				btnConfirm.setVisible(false);
				btnFinishOrder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						finishOrder();
					}
				});
				btnFinishOrder.setVisible(false);
				btnBack.setVisible(false);
			}
		}
		{
			lblTotalPrice = new JLabel("Total: 0");
			lblTotalPrice.setFont(ColorScheme.FONT);
			lblTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
			selectProductsPanel.add(lblTotalPrice, BorderLayout.SOUTH);
		}
		
	contentPanel.setLayout(new BorderLayout(0, 0));
	showPanel(phoneNumberPanel);
}
		
	/**
	 * Opens the panel for searching for customer
	 */
	private void selectExistingCustomer() {
		showPanel(phoneNumberPanel);
		FlowLayout flowLayout = (FlowLayout) phoneNumberPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
	}
	
	/**
	 * Opens panel for products searching
	 */
	private void showProductScreen() {
		showPanel(selectProductsPanel);
	}
	
	/**
	 * Searches for customer with inputed phone number
	 * @throws CustomerNotFoundException
	 */
	private void checkPhoneNumber() {
		String phone =  phoneField.getText();
		if(orderCtrl.createOrder(phone)) {
			showProductScreen();
			removeErrorMessage();
		}
			
		else {
			lblErrorMessage.setText("We don't talk about Bruno");
			phoneField.setBorder(new LineBorder(ColorScheme.BUTTON_HIGHTLIGHT, 10));
			phoneField.setBackground(ColorScheme.BUTTON_HIGHTLIGHT);
			phoneField.setForeground(ColorScheme.BUTTON);
		}
	}
	
	/**
	 * Adds products to the order lines 
	 * Adds order lines to the order
	 * @throws QuantityUnderrunException
	 * @throws ProductNotFoundException
	 * @throws NotEnoughInStockException
	 */
	private void addProduct() {
		try {
			orderCtrl.addProduct(Integer.parseInt(textBarcode.getText()), (int)spinnerQuantity.getValue());
			removeErrorMessage();
			updateList();
			lblTotalPrice.setText("Total: " + CurrencyHandler.convertToString(orderCtrl.getTotal()));
			textBarcode.setText("");
			spinnerQuantity.setValue(1);
		} catch (NumberFormatException e) {
			lblErrorMessage.setText(e.getMessage());
		} catch (NotEnoughInStockException e) {
			lblErrorMessage.setText(e.getMessage());
		} catch (ProductNotFoundException e) {
			lblErrorMessage.setText(e.getMessage());
			textBarcode.setBackground(ColorScheme.BUTTON_HIGHTLIGHT);
			textBarcode.setForeground(ColorScheme.BUTTON);
			textBarcode.setBorder(new LineBorder(ColorScheme.BUTTON_HIGHTLIGHT, 10));
		}
	}
	
	private void removeErrorMessage() {
		spinnerQuantity.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		
		textBarcode.setBorder(new LineBorder(ColorScheme.TEXT_FIELD, 10));
		textBarcode.setBackground(ColorScheme.TEXT_FIELD);
		textBarcode.setForeground(ColorScheme.TEXT_FIELD_FOREGROUND);
		
		phoneField.setBorder(new LineBorder(ColorScheme.TEXT_FIELD, 10));
		phoneField.setBackground(ColorScheme.TEXT_FIELD);
		phoneField.setForeground(ColorScheme.TEXT_FIELD_FOREGROUND);
		lblErrorMessage.setText("");
	}
	
	/**
	 * Updates the list of products added to the order
	 */
	private void updateList() {
		DefaultTableModel myTableModel = new DefaultTableModel();
		
		List<OrderLine> lists = orderCtrl.getCurrentOrder().getOrderLines();
		myTableModel.addColumn("Name");
		myTableModel.addColumn("Quantity");
		myTableModel.addColumn("Price");
		
		for (OrderLine element : lists) {
			myTableModel.addRow(new Object[] {((SaleOrderLine) element).getProduct().getName(),
					element.getQuantity(), CurrencyHandler.convertToString(element.getAmount())});
		}
		
		table.setModel(myTableModel);
	}
	
	/**
	 * Asks for payment 
	 */
	private void finishOrder() {
		if (!orderCtrl.isEmpty()) {
			OrderReceipt dialog = new OrderReceipt(orderCtrl);
			dialog.setVisible(true);
			
			if (dialog.isFinished()) {
				created = dialog.isFinished();
				
				dispose();
			}
		} 
		else {
			lblErrorMessage.setText("na na na na");
		}
	}
	
	/**
	 * Closes current panel and opens previous one 
	 */
	private void back() {
		if (currentPanel.equals(selectProductsPanel)) {
			orderCtrl.cancelOrder();
		} else {
			if (currentPanel.equals(phoneNumberPanel)) {
				phoneField.setText("");
			}
		}
		showPanel(backPath.get(backPath.size() - 2));
		backPath.remove(backPath.size() - 1);
		handleButtons();
		removeErrorMessage();
	}
	
    /**
     * Shows given panel and closes current one
     * @param panel
     */
    private void showPanel(JPanel panel) {
    	if (currentPanel != null) {
    		hidePanel(currentPanel);
    	}
    	
    	panel.setVisible(true);
    	contentPanel.add(panel, BorderLayout.CENTER);
    	currentPanel = panel;
    	
    	addToBackPath();
    	handleButtons();
    	
		if (currentPanel.equals(selectProductsPanel)) {
			updateList();
		}
    }
    
    /**
     * Hides given panel
     * @param panel
     */
    private void hidePanel(JPanel panel) {
    	panel.setVisible(false);
    	contentPanel.remove(panel);
    	currentPanel = null;
    }
    
    /**
     * Handles buttons in the UI
     */
    private void handleButtons() {
    	btnFinishOrder.setVisible(false);
    	btnConfirm.setVisible(false);
    	btnCreate.setVisible(false);
    	
    	if (currentPanel.equals(selectProductsPanel)) {
    		btnFinishOrder.setVisible(!btnFinishOrder.isVisible());
    	} else if (currentPanel.equals(phoneNumberPanel)) {
    			btnConfirm.setVisible(!btnConfirm.isVisible());
    	}
    	
    	if (backPath.size() <= 1) {
    		btnBack.setVisible(false);
    	} else {
    		btnBack.setVisible(true);
    	}
    }
    /**
     * Disposes the window
     */
	private void cancel() {
		dispose();
	}
	

	/**
	 * Updates path
	 */
	private void addToBackPath() {
		if (!backPath.contains(currentPanel)) {
			backPath.add(currentPanel);
		}
	}
	
	/**
	 * @return boolean of created
	 */
	public boolean isCreated() {
		return created;
	}
	
	public void fillGroupList() {
		this.comboBox.setRenderer(new GroupListCellRenderer());
		try {
			List<Person> ps = orderCtrl.showPerson();
			DefaultComboBoxModel<Person> dlm = new DefaultComboBoxModel<>();
			for(int i = 0; i < ps.size(); i++) {
				dlm.addElement(ps.get(i));
			}
			this.comboBox.setModel(dlm);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Oh noes! Something went wrong " + e.getMessage());
		}
		
	}
}