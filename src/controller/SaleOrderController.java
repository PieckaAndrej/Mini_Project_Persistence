package controller;

import dal.SaleOrderDB;
import dal.SaleOrderDBIF;
import exceptions.DatabaseAccessException;
import model.Person;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

public class SaleOrderController {
	private SaleOrderDBIF saleOrderDatabase;
	private PersonController personController;
	private SaleOrder currentOrder;
	private ProductController productController;
	
	public SaleOrderController() throws DatabaseAccessException {
		
		saleOrderDatabase = new SaleOrderDB();
		personController = new PersonController();
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
	
	public boolean addProduct(int id, int amount) {
		boolean retVal = true;
		
		Product product = productController.getProductById(id);
		
		if(product != null)
		{
			SaleOrderLine orderLine = new SaleOrderLine(product, amount);
			currentOrder.addOrderLine(orderLine);
		}
		else
		{
			retVal = false;
		}
		
		return retVal;
	}
	
	public boolean finishOrder() {
		boolean retVal = false;
		
		try {
			saleOrderDatabase.insertOrder(currentOrder);
			
			currentOrder = null;
			retVal = true;
			
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}
}
