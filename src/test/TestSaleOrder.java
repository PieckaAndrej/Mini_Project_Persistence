package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Person;
import model.Product;
import model.SaleOrder;
import model.SaleOrderLine;

class TestSaleOrder {
	
	private SaleOrder saleOrder;
	
	@BeforeEach
	void setUp() {
		Person person = new Person(null, null, null, null, null, null);
		saleOrder = new SaleOrder(person);
	}

	@Test
	void testGetAmountPrice1Quantity3ShouldReturn3() {
		int productPrice = 1;
		int productQuantity = 3;
		
		Product product = new Product(null, null, 0, productPrice, null, 0);
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		saleOrder.addOrderLine(orderLine);
		
		assertEquals(productPrice * productQuantity, saleOrder.getAmount(), 0);
	}
	
	@Test
	void testGetAmountOrderLines2Price1Quantity3ShouldReturn6() {
		int productPrice = 1;
		int productQuantity = 3;
		int orderLines = 2;
		
		Product product = new Product(null, null, 0, productPrice, null, 0);
		
		
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		for (int i = 0; i < orderLines; i++) {
			saleOrder.addOrderLine(orderLine);
		}
		
		assertEquals(productPrice * productQuantity * orderLines, saleOrder.getAmount(), 0);
	}

}
