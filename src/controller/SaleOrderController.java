package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import dal.OrderLineDB;
import dal.OrderLineDBIF;
import dal.SaleOrderDB;
import dal.SaleOrderDBIF;
import exceptions.DatabaseAccessException;
import exceptions.NotEnoughInStockException;
import model.OrderLine;
import model.Person;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

public class SaleOrderController {
	private SaleOrderDBIF saleOrderDb;
	private OrderLineDBIF orderLineDb;
	private PersonController personController;
	private SaleOrder currentOrder;
	private ProductController productController;
	
	public SaleOrderController() throws DatabaseAccessException {
		
		saleOrderDb = new SaleOrderDB();
		orderLineDb = new OrderLineDB();
		personController = new PersonController();
		productController = new ProductController();
	}
	
	public boolean createOrder(String phone) {
		boolean retVal = false;
		
		Person person = personController.getPersonByPhone(phone);
		
		if (person != null) {
			currentOrder = new SaleOrder(person);
			retVal = true;
		}
		
		return retVal;
	}
	
	public boolean addProduct(int id, int quantity) throws NotEnoughInStockException {
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
	
	public BigDecimal getTotal() {
		return currentOrder.getAmount();
	}
	
	public SaleOrder getCurrentOrder() {
		return currentOrder;
	}
	
	public boolean isEmpty() {
		return currentOrder.isEmpty();
	}
	
	public void cancelOrder() {
		currentOrder = null;
	}
}
