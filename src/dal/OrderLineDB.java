package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.DatabaseAccessException;
import model.OrderLine;
import model.SaleOrderLine;

public class OrderLineDB implements OrderLineDBIF {
	
	private static final String CREATESALEORDERLINE_STATEMENT = "INSERT INTO SaleOrderLine(quantity, productId,"
			+ " saleId) VALUES(?, ?, ?)";
	private PreparedStatement createStatement;

	public OrderLineDB() throws DatabaseAccessException {
		try {
			createStatement = DbConnection.getInstance().getConnection().prepareStatement(CREATESALEORDERLINE_STATEMENT);
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new DatabaseAccessException(DatabaseAccessException.CONNECTION_MESSAGE);
		}
	}
	
	@Override
	public void insertOrderLine(OrderLine orderLine, int saleId) throws DatabaseAccessException {
		if(orderLine instanceof SaleOrderLine) {
			SaleOrderLine saleOrderLine = (SaleOrderLine) orderLine;
			insertSaleOrderLine(saleOrderLine, saleId);
		}
	}
	
	private void insertSaleOrderLine(SaleOrderLine orderLine, int saleId) throws DatabaseAccessException {
		try {
			createStatement.setInt(1, orderLine.getQuantity());
			createStatement.setInt(2, orderLine.getProduct().getId());
			createStatement.setInt(3, saleId);
			
			createStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseAccessException(e.getMessage());
		}
	}

}
