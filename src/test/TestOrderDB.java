package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.SaleOrderDB;

class TestOrderDB {
	
	private SaleOrderDB orderDB;
	
	@BeforeEach
	void setUp() {
		orderDB = new SaleOrderDB();
	}

	@Test
	void testCreateOrder() {
		// arrange
		
		
		// act
		orderDB.createOrder(null);
		
		// assert
	}

}
