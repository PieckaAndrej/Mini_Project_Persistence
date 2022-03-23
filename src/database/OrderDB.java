package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.SaleOrder;

public class OrderDB implements OrderDBIF {
	
	private static final String CREATE_STATEMENT = "INSERT INTO SaleOrder(date, deliveryStatus, deliveryDate,"
			+ " paymentDate, amount, customerPhoneno) VALUES(?, ?, ?, ?, ?, ?)";
	
	private PreparedStatement createStatement;
	
	public OrderDB() {
		try {
			createStatement = DbConnection.getInstance().getConnection().prepareStatement(CREATE_STATEMENT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createOrder(SaleOrder order) {
		boolean retVal = false;
		try {
			createStatement.setTimestamp(1, Timestamp.valueOf(order.getDate()));
			createStatement.setString(2, order.getDeliveryStatus());
			createStatement.setTimestamp(3, Timestamp.valueOf(order.getDeliveryDate()));
			createStatement.setTimestamp(4, Timestamp.valueOf(order.getPaymentDate()));
			createStatement.setDouble(5, order.getAmount());
			createStatement.setString(6, order.getCustomer().getPhone());
			
			retVal = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retVal;
	}

}
