package controller;

import dal.SaleOrderDB;
import dal.SaleOrderDBIF;
import model.Person;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

public class SaleOrderController {
	private SaleOrderDBIF saleOrderDatabase;
	private PersonController personController;
	private SaleOrder currentOrder;
	private ProductController productController;
	
	public SaleOrderController() {
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
	
	public boolean addProduct(String id, int amount) {
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
		
		if(saleOrderDatabase.insertOrder(currentOrder) != null)
		{
			retVal = true;
		}
		
		currentOrder = null;
		
		return retVal;
	}
}
