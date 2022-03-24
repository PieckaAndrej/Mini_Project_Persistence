package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		int deliveryPrice = 45;
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		saleOrder.addOrderLine(orderLine);
		
		assertEquals(new BigDecimal(productPrice * productQuantity).add(new BigDecimal(deliveryPrice)).byteValueExact(),
				saleOrder.getPrice().byteValueExact());
	}
	
	@Test
	void testGetAmountOrderLines2Price1Quantity3ShouldReturn6() {
		int productPrice = 1;
		int productQuantity = 3;
		int orderLines = 2;
		int deliveryPrice = 45;
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		
		
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		for (int i = 0; i < orderLines; i++) {
			saleOrder.addOrderLine(orderLine);
		}
		
		assertEquals(new BigDecimal(productPrice * productQuantity * orderLines).add(new BigDecimal(deliveryPrice)).byteValueExact(),
				saleOrder.getPrice().byteValueExact());
	}
	
	@Test
	void testGetAmountOrderLines3Price100Quantity5ShouldReturn1445() {
		int productPrice = 100;
		int productQuantity = 5;
		int orderLines = 3;
		BigDecimal deliveryPrice = new BigDecimal(45);
		BigDecimal discount = new BigDecimal(100);
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		
		
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		for (int i = 0; i < orderLines; i++) {
			saleOrder.addOrderLine(orderLine);
		}
		
		int intTotal = productPrice * productQuantity * orderLines;
		
		BigDecimal total = new BigDecimal(productPrice * productQuantity * orderLines).setScale(4, RoundingMode.CEILING);
		
		if(intTotal >= 1500) {
			assertEquals(total.add(deliveryPrice).subtract(discount), saleOrder.getPrice());
		}
		else if(intTotal >= 2500){
			assertEquals(total.subtract(discount), saleOrder.getPrice());
		}
		else{
			assertEquals(total.add(deliveryPrice), saleOrder.getPrice());
		}
	}
	
	@Test
	void testGetAmountOrderLines5Price100Quantity5ShouldReturn2400() {
		int productPrice = 100;
		int productQuantity = 5;
		int orderLines = 5;
		BigDecimal deliveryPrice = new BigDecimal(45);
		BigDecimal discount = new BigDecimal(100);
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		
		
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		for (int i = 0; i < orderLines; i++) {
			saleOrder.addOrderLine(orderLine);
		}
		
		int intTotal = productPrice * productQuantity * orderLines;
		
		BigDecimal total = new BigDecimal(productPrice * productQuantity * orderLines).setScale(4, RoundingMode.CEILING);
		
		if(intTotal >= 1500) {
			assertEquals(total.add(deliveryPrice).subtract(discount), saleOrder.getPrice());
		}
		else if(intTotal >= 2500){
			assertEquals(total.subtract(discount), saleOrder.getPrice());
		}
		else{
			assertEquals(total.add(deliveryPrice), saleOrder.getPrice());
		}
	}
	
	@Test
	void testGetAmountOrderLines1Price100Quantity5ShouldReturn545() {
		int productPrice = 100;
		int productQuantity = 5;
		int orderLines = 1;
		BigDecimal deliveryPrice = new BigDecimal(45);
		BigDecimal discount = new BigDecimal(100);
		
		Product product = new Product(0, null, null, new BigDecimal(productPrice), null, 0, 0, null);
		
		
		SaleOrderLine orderLine = new SaleOrderLine(product, productQuantity);
		
		for (int i = 0; i < orderLines; i++) {
			saleOrder.addOrderLine(orderLine);
		}
		
		int intTotal = productPrice * productQuantity * orderLines;
		
		BigDecimal total = new BigDecimal(productPrice * productQuantity * orderLines).setScale(4, RoundingMode.CEILING);
		
		if(intTotal >= 1500) {
			assertEquals(total.add(deliveryPrice).subtract(discount), saleOrder.getPrice());
		}
		else if(intTotal >= 2500){
			assertEquals(total.subtract(discount), saleOrder.getPrice());
		}
		else{
			assertEquals(total.add(deliveryPrice), saleOrder.getPrice());
		}
	}

}
