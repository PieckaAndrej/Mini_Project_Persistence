package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.SaleOrder;

public class SaleOrderDB implements SaleOrderDBIF {
	
	private static final String CREATE_STATEMENT = "INSERT INTO SaleOrder(date, deliveryStatus, deliveryDate,"
			+ " paymentDate, amount, customerPhoneno) VALUES(?, ?, ?, ?, ?, ?)";
	
	private PreparedStatement createStatement;
	
	public SaleOrderDB() {
		try {
			createStatement = DbConnection.getInstance().getConnection().prepareStatement(CREATE_STATEMENT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public SaleOrder insertOrder(SaleOrder order) {
		try {
			createStatement.setTimestamp(1, Timestamp.valueOf(order.getDate()));
			createStatement.setString(2, order.getDeliveryStatus());
			createStatement.setTimestamp(3, Timestamp.valueOf(order.getDeliveryDate()));
			createStatement.setTimestamp(4, Timestamp.valueOf(order.getPaymentDate()));
			createStatement.setDouble(5, order.getAmount());
			createStatement.setString(6, order.getCustomer().getPhone());
			
			order.setId(DbConnection.getInstance().executeSqlInsertWithIdentity(createStatement));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order;
	}

}
