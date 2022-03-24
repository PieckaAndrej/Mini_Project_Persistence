package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

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
		Person person = new Person(null, null, null, null, null, null, null);
		saleOrder = new SaleOrder(person);
	}

	@Test
	void testGetAmountPrice1Quantity3ShouldReturn3() {
		int productPrice = 1;
		int productQuantity = 3;
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		saleOrder.addOrderLine(orderLine);
		
		assertEquals(new BigDecimal(productPrice * productQuantity), saleOrder.getAmount());
	}
	
	@Test
	void testGetAmountOrderLines2Price1Quantity3ShouldReturn6() {
		int productPrice = 1;
		int productQuantity = 3;
		int orderLines = 2;
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		
		
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		for (int i = 0; i < orderLines; i++) {
			saleOrder.addOrderLine(orderLine);
		}
		
		assertEquals(new BigDecimal(productPrice * productQuantity * orderLines), saleOrder.getAmount());
	}

}
