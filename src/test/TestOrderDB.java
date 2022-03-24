package test;

import static org.junit.Assert.assertEquals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.DbConnection;
import dal.PersonDB;
import dal.ProductDB;
import dal.SaleOrderDB;
import exceptions.DatabaseAccessException;
import model.SaleOrder;
import model.SaleOrderLine;

class TestOrderDB {
	
	private SaleOrderDB orderDB;
	
	
	@BeforeEach
	void setUp() {
		try {
			orderDB = new SaleOrderDB();
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCreateOrder() {
		// arrange
		String customerPhone = "1234567890";
		int productId = 1;
		String getLastQuery = "SELECT * FROM SaleOrder WHERE id=(SELECT max(id) FROM SaleOrder);";
		
		SaleOrder order = new SaleOrder(new PersonDB().getPersonByPhone(customerPhone));
		order.addOrderLine(new SaleOrderLine(new ProductDB().getProductByID(productId), 1));
		
		order.setDate(LocalDateTime.now());
		order.setDeliveryDate(LocalDateTime.now());
		order.setPaymentDate(LocalDateTime.now());
		
		order.setDeliveryStatus("finished");
		
		// act
		try {
			orderDB.insertOrder(order);
		} catch (DatabaseAccessException e) {
			e.printStackTrace();
		}
		
		PreparedStatement prst;
		
		try {
			prst = DbConnection.getInstance().getConnection().prepareStatement(getLastQuery);
			
			ResultSet rs = prst.getResultSet();
			
			// assert
			if (rs.next()) {
				assertEquals(Timestamp.valueOf(order.getDate()), rs.getTimestamp(1));
				assertEquals(order.getDeliveryStatus(), rs.getString(2));
				assertEquals(Timestamp.valueOf(order.getDeliveryDate()), rs.getTimestamp(3));
				assertEquals(Timestamp.valueOf(order.getPaymentDate()), rs.getTimestamp(4));
				assertEquals(order.getAmount(), rs.getBigDecimal(5));
				assertEquals(order.getCustomer().getPhoneno(), rs.getString(6));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}

}
