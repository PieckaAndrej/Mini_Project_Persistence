package controller;

import dal.SaleOrderDB;
import dal.SaleOrderDBIF;
import model.Person;
import model.SaleOrder;

public class SaleOrderController {
	private SaleOrderDBIF saleOrderDatabase;
	private PersonController personController;
	private SaleOrder currentOrder;
	
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
	
	public SaleOrder addProduct(int id, int amount) {
		return null;
		
	}
	public boolean finishOrder() {
		return (Boolean) null;
	}
}
