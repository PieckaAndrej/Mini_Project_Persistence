package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dal.OrderLineDB;
import dal.OrderLineDBIF;
import dal.SaleOrderDB;
import dal.SaleOrderDBIF;
import exceptions.DatabaseAccessException;
import exceptions.NotEnoughInStockException;
import exceptions.ProductNotFoundException;
import model.OrderLine;
import model.Person;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

public class SaleOrderController {
	private PersonController personController;
	private ProductController productController;
	private SaleOrder currentOrder;
	private SaleOrderDBIF saleOrderDb;
	private OrderLineDBIF orderLineDb;
	
	/**
	 * Constructor for the SaleOrderController class
	 * @throws DatabaseAccessException
	 */
	public SaleOrderController() throws DatabaseAccessException {
		
		saleOrderDb = new SaleOrderDB();
		orderLineDb = new OrderLineDB();
		personController = new PersonController();
		productController = new ProductController();
	}
	
	/**
	 * Creates an order with a customer with the corresponding phone number
	 * @param phone the phone number used to search for the customer
	 * @return true if the person was found with the phone number and the order was created
	 */
	public boolean createOrder(String phone) {
		boolean retVal = false;
		
		Person person = personController.getPersonByPhone(phone);
		
		if (person != null) {
			currentOrder = new SaleOrder(person);
			retVal = true;
		}
		
		return retVal;
	}
	
	/**
	 * Adds a product to the current order
	 * @param id the id of the product that has to be added
	 * @param quantity the quantity of the product to be added
	 * @return true if the product was found and added to the order
	 * @throws NotEnoughInStockException
	 * @throws ProductNotFoundException 
	 */
	public boolean addProduct(int id, int quantity) throws NotEnoughInStockException,
			ProductNotFoundException {
		boolean retVal = false;
		
		Product product = productController.getProductById(id);
		
		if(product != null) {
			if (currentOrder.isProductPresent(product)) {
				quantity += currentOrder.getOrderLineByProduct(product).getQuantity();
				if (product.getCurrentStock() < quantity) {
					throw new NotEnoughInStockException(quantity, product.getCurrentStock());
				}
				else {
					currentOrder.removeOrderLineByProduct(product);
				}
			}
			else if (product.getCurrentStock() < quantity) {
				throw new NotEnoughInStockException(quantity, product.getCurrentStock());
			}
			
			
			SaleOrderLine orderLine = new SaleOrderLine(product, quantity);
			currentOrder.addOrderLine(orderLine);
			retVal = true;
		}

		
		return retVal;
	}
	
	/**
	 * Finishes the order and adds it to the database and adds the order line to the database
	 * @return true if the order was successfully added to the database
	 */
	public boolean finishOrder() {
		boolean retVal = false;
		
		try {
			currentOrder.setPaymentDate(LocalDateTime.now());
			currentOrder.setDeliveryDate(LocalDateTime.now());
			currentOrder.setDate(LocalDateTime.now());
			saleOrderDb.insertOrder(currentOrder);
			
			for(OrderLine element:currentOrder.getOrderLines()) {
				orderLineDb.insertOrderLine(element, currentOrder.getId());
			}
			
			currentOrder = null;
			retVal = true;
			
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
	
	/**
	 * @return the total of the current order
	 */
	public BigDecimal getTotal() {
		return currentOrder.getPrice();
	}
	
	/**
	 * @return the currentOrder
	 */
	public SaleOrder getCurrentOrder() {
		return currentOrder;
	}
	
	/**
	 * @return true if the current order is empty
	 */
	public boolean isEmpty() {
		return currentOrder.isEmpty();
	}
	
	/**
	 * Cancel the order and sets the current order to null
	 */
	public void cancelOrder() {
		currentOrder = null;
	}
}
