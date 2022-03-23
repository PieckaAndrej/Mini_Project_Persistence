package controller;

import model.SaleOrder;
import model.OrderLine;
import dal.SaleOrderDBIF;
import dal.SaleOrderDB;

public class SaleOrderController {
	private SaleOrderDBIF saleOrderDatabase;
	private PersonController personController;
	private SaleOrder currentOrder;
	
	public SaleOrderController() {
		saleOrderDatabase = new SaleOrderDB();
		personController = new PersonController();
	}
	
	public SaleOrder createOrder(String phone) {
		currentOrder = new SaleOrder(personController.getPersonByPhone(phone));
	}
	public SaleOrder addProduct(int id, int amount) {
		return null;
		
	}
	public boolean finishOrder() {
		return (Boolean) null;
	}
}
