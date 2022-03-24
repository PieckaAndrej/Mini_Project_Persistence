package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.SaleOrder;

public class TestCorrectAmount {
	
	private SaleOrder saleOrder;
	private BigDecimal amount;
	private BigDecimal finalPrice;
	
	@BeforeEach
	void setUp() {
		saleOrder = new SaleOrder(null);
		amount = saleOrder.getPrice();
		finalPrice = saleOrder.getAmount();
	}
	
	@Test
	void TestRightAmount()
	{
		assertEquals(amount.subtract(new BigDecimal(100)), finalPrice);
	}

}
