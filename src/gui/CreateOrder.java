package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import model.OrderLine;
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
	private JLabel lblErrorButton;
	private JButton btnBack;
	private JTextField nameField;
	private JTextField phoneNumberField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField zipCodeField;
	private JPanel newCustomerPanel;
	private JButton btnCreate;
	
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
	public CreateOrder() throws DatabaseAccessException {
		setTitle("Create Order");
		orderCtrl = new SaleOrderController();
		backPath = new ArrayList<>();
		
		initGui();

		showPanel(selectCustomerMethodPanel);
		
	}
	
	/**
	 * Initialize gui
	 */
	private void initGui() {
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
					phoneField.setAlignmentX(0.0f);
					verticalBox.add(phoneField);
					phoneField.setColumns(10);
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
					JButton btnNewButton = new JButton("Existing customer ");
					btnNewButton.setIcon(Images.getButtonIcon(btnNewButton, ColorScheme.BACKGROUND));
					btnNewButton.addActionListener(new ActionListener() {
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
					verticalBox.add(btnNewButton);
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
					lblErrorButton = new JLabel("");
					lblErrorButton.setFont(ColorScheme.FONT);
					rightButtonPanel.add(lblErrorButton);
					lblErrorButton.setHorizontalAlignment(SwingConstants.RIGHT);
					lblErrorButton.setForeground(ColorScheme.BACKGROUND);
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
		
		{
			newCustomerPanel = new JPanel();
			newCustomerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				Box verticalBox = Box.createVerticalBox();
				newCustomerPanel.add(verticalBox);
				{
					JLabel lblNewCustomer = new JLabel("New Customer");
					lblNewCustomer.setFont(ColorScheme.FONT);
					lblNewCustomer.setAlignmentX(0.1f);
					verticalBox.add(lblNewCustomer);
				}
				{
					Component verticalStrut = Box.createVerticalStrut(20);
					verticalBox.add(verticalStrut);
				}
				{
					JPanel newCustomerTextPanel = new JPanel();
					newCustomerTextPanel.setAlignmentX(0.0f);
					verticalBox.add(newCustomerTextPanel);
					GridBagLayout gbl_newCustomerTextPanel = new GridBagLayout();
					gbl_newCustomerTextPanel.columnWidths = new int[]{45, 155, 0};
					gbl_newCustomerTextPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
					gbl_newCustomerTextPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
					gbl_newCustomerTextPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					newCustomerTextPanel.setLayout(gbl_newCustomerTextPanel);
					{
						JLabel lblCustomerName = new JLabel("Name");
						lblCustomerName.setFont(ColorScheme.FONT);
						GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
						gbc_lblCustomerName.anchor = GridBagConstraints.EAST;
						gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
						gbc_lblCustomerName.gridx = 0;
						gbc_lblCustomerName.gridy = 0;
						newCustomerTextPanel.add(lblCustomerName, gbc_lblCustomerName);
					}
					{
						nameField = new JTextField();
						nameField.setFont(ColorScheme.FONT);
						nameField.setColumns(10);
						nameField.setAlignmentX(1.0f);
						GridBagConstraints gbc_nameField = new GridBagConstraints();
						gbc_nameField.insets = new Insets(0, 0, 5, 0);
						gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
						gbc_nameField.gridx = 1;
						gbc_nameField.gridy = 0;
						newCustomerTextPanel.add(nameField, gbc_nameField);
					}
					{
						JLabel lblCustomerPhoneNumber = new JLabel("Phone number");
						lblCustomerPhoneNumber.setFont(ColorScheme.FONT);
						lblCustomerPhoneNumber.setAlignmentX(1.0f);
						GridBagConstraints gbc_lblCustomerPhoneNumber = new GridBagConstraints();
						gbc_lblCustomerPhoneNumber.anchor = GridBagConstraints.EAST;
						gbc_lblCustomerPhoneNumber.insets = new Insets(0, 0, 5, 5);
						gbc_lblCustomerPhoneNumber.gridx = 0;
						gbc_lblCustomerPhoneNumber.gridy = 1;
						newCustomerTextPanel.add(lblCustomerPhoneNumber, gbc_lblCustomerPhoneNumber);
					}
					{
						phoneNumberField = new JTextField();
						phoneNumberField.setFont(ColorScheme.FONT);
						phoneNumberField.setColumns(10);
						GridBagConstraints gbc_phoneNumberField = new GridBagConstraints();
						gbc_phoneNumberField.insets = new Insets(0, 0, 5, 0);
						gbc_phoneNumberField.fill = GridBagConstraints.HORIZONTAL;
						gbc_phoneNumberField.gridx = 1;
						gbc_phoneNumberField.gridy = 1;
						newCustomerTextPanel.add(phoneNumberField, gbc_phoneNumberField);
					}
					{
						JLabel lblCustomerAddress = new JLabel("Address");
						lblCustomerAddress.setFont(ColorScheme.FONT);
						lblCustomerAddress.setAlignmentX(1.0f);
						GridBagConstraints gbc_lblCustomerAddress = new GridBagConstraints();
						gbc_lblCustomerAddress.anchor = GridBagConstraints.EAST;
						gbc_lblCustomerAddress.insets = new Insets(0, 0, 5, 5);
						gbc_lblCustomerAddress.gridx = 0;
						gbc_lblCustomerAddress.gridy = 2;
						newCustomerTextPanel.add(lblCustomerAddress, gbc_lblCustomerAddress);
					}
					{
						addressField = new JTextField();
						addressField.setFont(ColorScheme.FONT);
						addressField.setColumns(10);
						GridBagConstraints gbc_addressField = new GridBagConstraints();
						gbc_addressField.insets = new Insets(0, 0, 5, 0);
						gbc_addressField.fill = GridBagConstraints.HORIZONTAL;
						gbc_addressField.gridx = 1;
						gbc_addressField.gridy = 2;
						newCustomerTextPanel.add(addressField, gbc_addressField);
					}
					{
						JLabel lblCustomerCity = new JLabel("City");
						lblCustomerCity.setFont(ColorScheme.FONT);
						lblCustomerCity.setAlignmentX(1.0f);
						GridBagConstraints gbc_lblCustomerCity = new GridBagConstraints();
						gbc_lblCustomerCity.anchor = GridBagConstraints.EAST;
						gbc_lblCustomerCity.insets = new Insets(0, 0, 5, 5);
						gbc_lblCustomerCity.gridx = 0;
						gbc_lblCustomerCity.gridy = 3;
						newCustomerTextPanel.add(lblCustomerCity, gbc_lblCustomerCity);
					}
					{
						cityField = new JTextField();
						cityField.setFont(ColorScheme.FONT);
						cityField.setColumns(10);
						GridBagConstraints gbc_cityField = new GridBagConstraints();
						gbc_cityField.insets = new Insets(0, 0, 5, 0);
						gbc_cityField.fill = GridBagConstraints.HORIZONTAL;
						gbc_cityField.gridx = 1;
						gbc_cityField.gridy = 3;
						newCustomerTextPanel.add(cityField, gbc_cityField);
					}
					{
						JLabel lblCustomerZipCode = new JLabel("Zip code");
						lblCustomerZipCode.setFont(ColorScheme.FONT);
						lblCustomerZipCode.setAlignmentX(1.0f);
						GridBagConstraints gbc_lblCustomerZipCode = new GridBagConstraints();
						gbc_lblCustomerZipCode.anchor = GridBagConstraints.EAST;
						gbc_lblCustomerZipCode.insets = new Insets(0, 0, 0, 5);
						gbc_lblCustomerZipCode.gridx = 0;
						gbc_lblCustomerZipCode.gridy = 4;
						newCustomerTextPanel.add(lblCustomerZipCode, gbc_lblCustomerZipCode);
					}
					{
						zipCodeField = new JTextField();
						zipCodeField.setFont(ColorScheme.FONT);
						zipCodeField.setColumns(10);
						GridBagConstraints gbc_zipCodeField = new GridBagConstraints();
						gbc_zipCodeField.fill = GridBagConstraints.HORIZONTAL;
						gbc_zipCodeField.gridx = 1;
						gbc_zipCodeField.gridy = 4;
						newCustomerTextPanel.add(zipCodeField, gbc_zipCodeField);
					}
				}
			}
		}
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
			lblErrorButton.setText("We don't talk about Bruno");
			phoneField.setBorder(new LineBorder(ColorScheme.BUTTON_HIGHTLIGHT));
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
			orderCtrl.addProduct(Integer.parseInt(textBarcode.getText()), (int)spinnerQuantity.getValue());
			removeErrorMessage();
			updateList();
			lblTotalPrice.setText("Total: " + CurrencyHandler.convertToString(orderCtrl.getTotal()));
			textBarcode.setText("");
			spinnerQuantity.setValue(1);
			
	}
	
	private void removeErrorMessage() {
		spinnerQuantity.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		textBarcode.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		phoneField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		lblErrorButton.setText("");
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
			lblErrorButton.setText("na na na na");
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
    	contentPanel.add(panel);
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
    	}
    	else {
    		if (currentPanel.equals(phoneNumberPanel)) {
    			btnConfirm.setVisible(!btnConfirm.isVisible());
    		}
    		else {
    			if (currentPanel.equals(newCustomerPanel)) {
    				btnCreate.setVisible(!btnCreate.isVisible());
    			}
    		}
    	}
    	
    	if (backPath.size() <= 1) {
    		btnBack.setVisible(false);
    	} 
    	else {
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
}